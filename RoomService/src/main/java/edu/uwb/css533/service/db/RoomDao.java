package edu.uwb.css533.service.db;

import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import org.joda.time.DateTime;

public interface RoomDao {
    @SqlUpdate("insert into room_sessions (session_id, game_started, " +
            "current_flag, feature1, " +
            "feature2, feature3, " +
            "num_players, player1_id, player2_id, player1_progress, player2_progress, winner_id) " +
            "values (:session_id, :game_started, " +
            ":current_flag, :feature1, " +
            ":feature2, :feature3, " +
            ":num_players, :player1_id, :player2_id, :p1_progress, :p2_progress, wid)")
    void insert(@Bind("session_id") int session_id, @Bind("game_started") boolean game_started,
                @Bind("current_flag") String current_flag, @Bind("feature1") String feature1,
                @Bind("feature2") String feature2, @Bind("feature3") String feature3,
                @Bind("num_players") int players, @Bind("player1_id") int id_1, @Bind("player2_id") int id_2,
                @Bind("p1_progress") int p1_progress, @Bind("p2_progress") int p2_progress, @Bind("wid") int wid);

    @SqlQuery("select num_players from room_sessions where session_id = :id")
    Integer checkNumPlayers(@Bind("id") int id);

    @SqlUpdate("update room_sessions set num_players = :num where session_id = :id")
    void updateNumPlayers(@Bind("num") int num, @Bind("id") int id);

    @SqlUpdate("update room_sessions set player2_id = :p2_id where session_id = :s_id")
    void updatePlayer2ID(@Bind("p2_id") int p2_id, @Bind("s_id") int s_id);


    @SqlUpdate("update player_information set active_session = :s_id where user_id = :id")
    void updatePlayerActiveSession(@Bind("s_id") int s_id, @Bind("id") int id);

}
