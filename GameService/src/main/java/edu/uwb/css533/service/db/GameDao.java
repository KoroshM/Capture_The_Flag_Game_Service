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

    @SqlQuery("select f1 from room_sessions where session_id = :sid")
    String getFeature1(@Bind("sid") int sid);

    @SqlQuery("select f1_code from room_sessions where session_id = :sid")
    Integer getFeature1Code(@Bind("sid") int sid);

    @SqlQuery("select f2 from room_sessions where session_id = :sid")
    String getFeature2(@Bind("sid") int sid);

    @SqlQuery("select f2_code from room_sessions where session_id = :sid")
    Integer getFeature2Code(@Bind("sid") int sid);

    @SqlQuery("select f3 from room_sessions where session_id = :sid")
    String getFeature3(@Bind("sid") int sid);

    @SqlQuery("select f3_code from room_sessions where session_id = :sid")
    Integer getFeature3Code(@Bind("sid") int sid);



    @SqlQuery("select p1_id from room_sessions where session_id = :sid")
    Integer getPlayer1ID(@Bind("sid") int sid);

    @SqlQuery("select p2_id from room_sessions where session_id = :sid")
    Integer getPlayer2ID(@Bind("sid") int sid);

    @SqlQuery("select p1_progress from room_sessions where session_id = :sid")
    Integer getPlayer1Progress(@Bind("sid") int sid);

    @SqlQuery("select p2_progress from room_sessions where session_id = :sid")
    Integer getPlayer2Progress(@Bind("sid") int sid);

    @SqlUpdate("update room_sessions set p1_progress = :progress where session_id = :sid")
    void updatePlayer1Progress(@Bind("progress") int progress, @Bind("sid") int sid);

    @SqlUpdate("update room_sessions set p2_progress = :progress where session_id = :sid")
    void updatePlayer2Progress(@Bind("progress") int progress, @Bind("sid") int sid);


    @SqlQuery("select winner_id from room_sessions where session_id = :sid")
    Integer getWinner(@Bind("sid") int sid);

    @SqlUpdate("update room_sessions set winner_id = :wid where session_id = :sid")
    void updateWinner(@Bind("wid") int wid, @Bind("sid") int sid);

    @SqlQuery("select winner_time_in_seconds from room_sessions where session_id = :sid")
    Integer getWinnerTime(@Bind("sid") int sid);

    @SqlUpdate("update room_sessions set winner_time_in_seconds = :w_time where session_id = :sid")
    void updateWinnerTime(@Bind("w_time") Integer w_time, @Bind("sid") int sid);

    @SqlQuery("select best_time_in_seconds from flags where flag_name = :flag_name")
    Integer getBestTime(@Bind("flag_name") String flag_name);

    @SqlUpdate("update flags set best_time_in_seconds = :b_time where flag_name = :flag_name")
    void updateBestTime(@Bind("b_time") Integer b_time, @Bind("flag_name") String flag_name);


}
