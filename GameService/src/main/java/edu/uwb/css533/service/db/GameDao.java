package edu.uwb.css533.service.db;

import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

public interface GameDao {

    @SqlQuery("select num_players from room_sessions where session_id = :id")
    Integer checkNumPlayers(@Bind("id") int id);

    @SqlQuery("select current_flag from room_sessions where session_id = :id")
    String getFlag(@Bind("id") int id);

    @SqlQuery("select game_started from room_sessions where session_id = :id")
    Boolean getGameStatus(@Bind("id") int id);

    @SqlUpdate("update room_sessions set game_started = :status where session_id = :id")
    void updateGameStatus(@Bind("status") boolean status, @Bind("id") int id);

    @SqlQuery("select feature1 from room_sessions where session_id = :id")
    String getFeature1(@Bind("id") int id);

    @SqlQuery("select feature2 from room_sessions where session_id = :id")
    String getFeature2(@Bind("id") int id);

    @SqlQuery("select feature3 from room_sessions where session_id = :id")
    String getFeature3(@Bind("id") int id);

    @SqlQuery("select player1_id from room_sessions where session_id = :id")
    Integer getPlayer1ID(@Bind("id") int id);

    @SqlQuery("select player2_id from room_sessions where session_id = :id")
    Integer getPlayer2ID(@Bind("id") int id);

    @SqlQuery("select player1_progress from room_sessions where session_id = :id")
    Integer getPlayer1Progress(@Bind("id") int id);

    @SqlQuery("select player2_progress from room_sessions where session_id = :id")
    Integer getPlayer2Progress(@Bind("id") int id);

    @SqlUpdate("update room_sessions set player1_progress = :progress where session_id = :id")
    void updatePlayer1Progress(@Bind("progress") int progress, @Bind("id") int id);

    @SqlUpdate("update room_sessions set player2_progress = :progress where session_id = :id")
    void updatePlayer2Progress(@Bind("progress") int progress, @Bind("id") int id);

    @SqlUpdate("update player_information set in_game = :in_game where user_id = :id")
    void updatePlayerGameStatus(@Bind("in_game") boolean in_game, @Bind("id") int id);

    @SqlQuery("select winner_id from room_sessions where session_id = :id")
    Integer getWinner(@Bind("id") int id);

    @SqlUpdate("update room_sessions set winner_id = :wid where session_id = :id")
    void updateWinner(@Bind("wid") int wid, @Bind("id") int id);

    @SqlUpdate("update player_information set active_session = :s_id where user_id = :id")
    void endPlayerActiveSession(@Bind("s_id") int s_id, @Bind("id") int id);

}
