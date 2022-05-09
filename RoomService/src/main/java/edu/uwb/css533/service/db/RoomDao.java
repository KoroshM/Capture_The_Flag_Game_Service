package edu.uwb.css533.service.db;

import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import org.joda.time.DateTime;

public interface RoomDao {
    @SqlUpdate("insert into room_sessions (current_flag, time_since_game_began, session_id, num_players) values (:flag, :time, :id, :players)")
    void insert(@Bind("flag") String flag, @Bind("time") DateTime time, @Bind("id") int id, @Bind("players") int players);

    @SqlQuery("select flag_name from flags where num_flag = :num")
    Integer getFlag(@Bind("num") int num);

}
