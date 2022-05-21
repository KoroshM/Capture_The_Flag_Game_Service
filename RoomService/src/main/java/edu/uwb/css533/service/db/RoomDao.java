package edu.uwb.css533.service.db;

import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import org.joda.time.DateTime;

public interface RoomDao {
    @SqlUpdate("insert into room_sessions (session_id, game_started, " +
            "current_flag, feature1, " +
            "feature2, feature3, " +
            "num_players, player1_id, player2_id, p1f1, p2f1, p1f2, p2f2, p1f3, p2f3) " +
            "values (:session_id, :game_started, " +
            ":current_flag, :feature1, " +
            ":feature2, :feature3, " +
            ":num_players, :player1_id, :player2_id, :p1f1, :p2f1, :p1f2, :p2f2, :p1f3, :p2f3)")
    void insert(@Bind("session_id") int session_id, @Bind("game_started") boolean game_started,
                @Bind("current_flag") String current_flag, @Bind("feature1") String feature1,
                @Bind("feature2") String feature2, @Bind("feature3") String feature3,
                @Bind("num_players") int players, @Bind("player1_id") int id_1, @Bind("player2_id") int id_2,
                @Bind("p1f1") boolean p1f1, @Bind("p2f1") boolean p2f1, @Bind("p1f2") boolean p1f2,
                @Bind("p2f2") boolean p2f2, @Bind("p1f3") boolean p1f3, @Bind("p2f3") boolean p2f3);

    @SqlQuery("select num_players from room_sessions where session_id = :id")
    Integer checkNumPlayers(@Bind("id") int id);

    @SqlUpdate("update room_sessions set num_players = :num where session_id = :id")
    void updateNumPlayers(@Bind("num") int num, @Bind("id") int id);

    @SqlUpdate("update room_sessions set player2_id = :p2_id where session_id = :s_id")
    void updatePlayer2ID(@Bind("p2_id") int p2_id, @Bind("s_id") int s_id);

}
