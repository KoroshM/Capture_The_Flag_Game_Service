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
    Boolean sessionGameStatus(@Bind("id") int id);

}
