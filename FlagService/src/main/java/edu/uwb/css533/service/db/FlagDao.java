package edu.uwb.css533.service.db;

import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

public interface FlagDao {

    @SqlQuery("select flag_name from flags where flag_id = :id")
    String getFlag(@Bind("id") int id);

    @SqlUpdate("update room_sessions set current_flag = :flag where session_id = :id")
    void updateFlag(@Bind("flag") String flag, @Bind("id") int id);
}
