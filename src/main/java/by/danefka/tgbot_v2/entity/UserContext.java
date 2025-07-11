package by.danefka.tgbot_v2.entity;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import by.danefka.tgbot_v2.model.UserStatus;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "user_context")
public class UserContext {
    @Id
    private long userId;

    private String userName;

    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    @Column(name = "user_status")
    private UserStatus userStatus;
}
