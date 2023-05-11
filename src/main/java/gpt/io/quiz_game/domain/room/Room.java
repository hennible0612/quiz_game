package gpt.io.quiz_game.domain.room;

import gpt.io.quiz_game.domain.room_user.RoomUser;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
public class Room {

    @Id @GeneratedValue
    private Integer id;

    @OneToMany(mappedBy = "room")
    private List<RoomUser> roomUsers = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private HeadCount headCount;

    @Enumerated(EnumType.STRING)
    private RoomStatus roomStatus;

    private String roomName;

    private Integer round;

    @Enumerated(EnumType.STRING)
    private Topic topic;

    private Integer limitTime;
}
