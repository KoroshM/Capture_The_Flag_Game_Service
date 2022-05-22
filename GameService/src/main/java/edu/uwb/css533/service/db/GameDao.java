package edu.uwb.css533.service.db;

import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.time.LocalDateTime;

public interface GameDao {

    @SqlQuery("select num_players from room_sessions where session_id = :sid")
    Integer checkNumPlayers(@Bind("sid") int sid);

    @SqlQuery("select started from room_sessions where session_id = :sid")
    Boolean getGameStatus(@Bind("sid") int sid);

    @SqlUpdate("update room_sessions set started = :status where session_id = :sid")
    void updateGameStatus(@Bind("status") boolean status, @Bind("sid") int sid);

    @SqlQuery("select start_time from room_sessions where session_id = :sid")
    LocalDateTime getGameStartTime(@Bind("sid") int sid);

    @SqlUpdate("update room_sessions set start_time = :start_time where session_id = :sid")
    void updateGameStartTime(@Bind("start_time") LocalDateTime start_time, @Bind("sid") int sid);



    @SqlQuery("select current_flag from room_sessions where session_id = :sid")
    String getFlag(@Bind("sid") int sid);

    @SqlQuery("select feature1 from room_sessions where session_id = :sid")
    String getFeature1(@Bind("sid") int sid);

    @SqlQuery("select f1_code from room_sessions where session_id = :sid")
    Integer getFeature1Code(@Bind("sid") int sid);

    @SqlQuery("select feature2 from room_sessions where session_id = :sid")
    String getFeature2(@Bind("id") int sid);

    @SqlQuery("select f2_code from room_sessions where session_id = :sid")
    Integer getFeature2Code(@Bind("sid") int sid);

    @SqlQuery("select feature3 from room_sessions where session_id = :sid")
    String getFeature3(@Bind("sid") int sid);

    @SqlQuery("select f3_code from room_sessions where session_id = :sid")
    Integer getFeature3Code(@Bind("sid") int sid);



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


    @SqlQuery("select winner_id from room_sessions where session_id = :id")
    Integer getWinner(@Bind("id") int id);

    @SqlUpdate("update room_sessions set winner_id = :wid where session_id = :id")
    void updateWinner(@Bind("wid") int wid, @Bind("id") int id);


}
