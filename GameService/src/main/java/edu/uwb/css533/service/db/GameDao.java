package edu.uwb.css533.service.db;

import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

public interface GameDao {

    @SqlQuery("select num_players from room_sessions where session_id = :id")
    Integer checkNumPlayers(@Bind("id") int id);

    @SqlQuery("select flag_name from room_sessions where session_id = :id")
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

}
