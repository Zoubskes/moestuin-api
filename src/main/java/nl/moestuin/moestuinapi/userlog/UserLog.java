package nl.moestuin.moestuinapi.userlog;

import jakarta.persistence.*;

import java.time.OffsetDateTime;

@Entity
@Table(name = "user_log")
public class UserLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "log_id")
    private Long logId;

    @Column(name = "user", nullable = false)
    private String user;

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

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
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
