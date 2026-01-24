package nl.moestuin.moestuinapi.crypto;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Base64;

@Converter
public class CryptoStringConverter implements AttributeConverter<String, String> {

    private static final String PREFIX = "ENC:";
    private static final String TRANSFORMATION = "AES/GCM/NoPadding";
    private static final int IV_LENGTH_BYTES = 12;        // aanbevolen voor GCM
    private static final int TAG_LENGTH_BITS = 128;

    private static final SecureRandom RANDOM = new SecureRandom();
    private static final SecretKey KEY = loadKeyFromEnv();

    private static SecretKey loadKeyFromEnv() {
        String b64 = System.getenv("MOESTUIN_LOG_ENC_KEY");
        if (b64 == null || b64.isBlank()) {
            throw new IllegalStateException("Environment variable MOESTUIN_LOG_ENC_KEY ontbreekt (base64 32 bytes).");
        }
        byte[] keyBytes = Base64.getDecoder().decode(b64);
        if (keyBytes.length != 32) {
            throw new IllegalStateException("MOESTUIN_LOG_ENC_KEY moet 32 bytes zijn (AES-256). Was: " + keyBytes.length);
        }
        return new SecretKeySpec(keyBytes, "AES");
    }

    @Override
    public String convertToDatabaseColumn(String attribute) {
        if (attribute == null) return null;

        // voorkom dubbele encryptie
        if (attribute.startsWith(PREFIX)) return attribute;

        try {
            byte[] iv = new byte[IV_LENGTH_BYTES];
            RANDOM.nextBytes(iv);

            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            cipher.init(Cipher.ENCRYPT_MODE, KEY, new GCMParameterSpec(TAG_LENGTH_BITS, iv));

            byte[] plaintext = attribute.getBytes(StandardCharsets.UTF_8);
            byte[] ciphertext = cipher.doFinal(plaintext);

            // payload = iv || ciphertext
            byte[] payload = new byte[iv.length + ciphertext.length];
            System.arraycopy(iv, 0, payload, 0, iv.length);
            System.arraycopy(ciphertext, 0, payload, iv.length, ciphertext.length);

            return PREFIX + Base64.getEncoder().encodeToString(payload);
        } catch (Exception e) {
            throw new IllegalStateException("Encryptie mislukt", e);
        }
    }

    @Override
    public String convertToEntityAttribute(String dbData) {
        if (dbData == null) return null;

        // oude records (plaintext) blijven werken
        if (!dbData.startsWith(PREFIX)) {
            return dbData;
        }

        try {
            String b64 = dbData.substring(PREFIX.length());
            byte[] payload = Base64.getDecoder().decode(b64);

            byte[] iv = new byte[IV_LENGTH_BYTES];
            byte[] ciphertext = new byte[payload.length - IV_LENGTH_BYTES];

            System.arraycopy(payload, 0, iv, 0, IV_LENGTH_BYTES);
            System.arraycopy(payload, IV_LENGTH_BYTES, ciphertext, 0, ciphertext.length);

            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            cipher.init(Cipher.DECRYPT_MODE, KEY, new GCMParameterSpec(TAG_LENGTH_BITS, iv));

            byte[] plaintext = cipher.doFinal(ciphertext);
            return new String(plaintext, StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new IllegalStateException("Decryptie mislukt (key correct?)", e);
        }
    }
}