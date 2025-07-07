package by.danefka.tgbot_v2.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "game_sessions")
public class GameSession {
    @Id
    @GeneratedValue
    private long id;

    private long gameId;
    private String gameTitle;
    private LocalDateTime dateTime;
}
