package by.danefka.tgbot_v2.entity;

import by.danefka.tgbot_v2.model.UserStatus;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "user_context")
public class UserContextPostgres {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true, nullable = false)
    private long userId;

    private String userName;

    @Enumerated(EnumType.STRING)
    private UserStatus status;
}
