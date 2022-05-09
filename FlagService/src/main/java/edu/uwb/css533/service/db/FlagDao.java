package edu.uwb.css533.service.db;

import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

public interface FlagDao {

    @SqlQuery("select flag_name from flags where num_flag = :num")
    String getFlag(@Bind("num") int num);

    @SqlUpdate("update room_sessions set current_flag = :flag where session_id = :id")
    void updateFlag(@Bind("flag") String flag, @Bind("id") int id);
}
