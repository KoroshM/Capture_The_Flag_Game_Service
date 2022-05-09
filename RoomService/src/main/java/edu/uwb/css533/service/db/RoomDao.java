package edu.uwb.css533.service.db;

import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import org.joda.time.DateTime;

public interface RoomDao {
    @SqlUpdate("insert into room_sessions (current_flag, session_id, num_players) values (:flag, :id, :players)")
    void insert(@Bind("flag") String flag, @Bind("id") int id, @Bind("players") int players);

}
