package edu.uwb.css533.service.db;

import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import org.joda.time.DateTime;

import java.time.LocalDateTime;
import java.time.LocalTime;

public interface RoomDao {
    @SqlUpdate("insert into room_sessions (session_id, started, start_time, current_flag, " +
            "f1, f1_code, f2, f2_code, f3, f3_code, num_players, p1_id, p2_id, " +
            "p1_progress, p2_progress, winner_id, winner_time_in_seconds) " +
            "values (:session_id, :started, :start_time, :current_flag, " +
            ":f1, :f1_code, :f2, :f2_code, :f3, :f3_code, :num_players, :p1_id, :p2_id, " +
            ":p1_progress, :p2_progress, :winner_id, :winner_time_in_seconds)")
    void insert(@Bind("session_id") int session_id, @Bind("started") boolean started,
                @Bind("start_time") LocalDateTime start_time, @Bind("current_flag") String current_flag,
                @Bind("f1") String feature1, @Bind("f1_code") Integer f1_code,
                @Bind("f2") String feature2, @Bind("f2_code") Integer f2_code,
                @Bind("f3") String feature3, @Bind("f3_code") Integer f3_code,
                @Bind("num_players") Integer num_players, @Bind("p1_id") Integer player1_id,
                @Bind("p2_id") Integer player2_id, @Bind("p1_progress") Integer player1_progress,
                @Bind("p2_progress") Integer player2_progress, @Bind("winner_id") Integer winner_id,
                @Bind("winner_time_in_seconds") Integer winner_time_in_seconds);

    @SqlQuery("select num_players from room_sessions where session_id = :id")
    Integer checkNumPlayers(@Bind("id") int id);

    @SqlUpdate("update room_sessions set num_players = :num where session_id = :id")
    void updateNumPlayers(@Bind("num") int num, @Bind("id") int id);

    @SqlUpdate("update room_sessions set player2_id = :p2_id where session_id = :s_id")
    void updatePlayer2ID(@Bind("p2_id") int p2_id, @Bind("s_id") int s_id);

//    @SqlUpdate("update player_information set active_session = :s_id where user_id = :id")
//    void updatePlayerActiveSession(@Bind("s_id") int s_id, @Bind("id") int id);

}
