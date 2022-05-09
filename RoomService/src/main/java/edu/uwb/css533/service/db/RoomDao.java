package edu.uwb.css533.service.db;

import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import org.joda.time.DateTime;

public interface RoomDao {
    @SqlUpdate("insert into room_sessions (current_flag, session_id, num_players) values (:flag, :id, :players)")
    void insert(@Bind("flag") String flag, @Bind("id") int id, @Bind("players") int players);

    @SqlQuery("select num_players from room_sessions where session_id = :id")
    Integer checkNumPlayers(@Bind("id") int id);

    @SqlUpdate("update room_sessions set num_players = :num where session_id = :id")
    void updateNumPlayers(@Bind("num") int num, @Bind("id") int id);

    @SqlUpdate("update player_credentials set logged_in = :logged_in where user_id = :id")
    void logIn(@Bind("logged_in") boolean logged_in, @Bind("id") int id);
}
