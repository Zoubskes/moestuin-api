package nl.moestuin.moestuinapi.userlog;

import jakarta.persistence.*;
import nl.moestuin.moestuinapi.crypto.CryptoStringConverter;

import java.time.OffsetDateTime;

@Entity
@Table(name = "user_log")
public class UserLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "log_id")
    private Long logId;

    @Convert(converter = CryptoStringConverter.class)
    @Column(name = "username", nullable = false)
    private String username;

    @Convert(converter = CryptoStringConverter.class)
    @Column(name = "gebeurtenis", nullable = false)
    private String gebeurtenis;

    @Column(name = "timestamp")
    private OffsetDateTime timestamp;

    public UserLog() {
    }

    public Long getLogId() {
        return logId;
    }

    public void setLogId(Long logId) {
        this.logId = logId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String user) {
        this.username = user;
    }

    public String getGebeurtenis() {
        return gebeurtenis;
    }

    public void setGebeurtenis(String gebeurtenis) {
        this.gebeurtenis = gebeurtenis;
    }

    public OffsetDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(OffsetDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
