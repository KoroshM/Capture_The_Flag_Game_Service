package edu.uwb.css533.service.db;

import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

public interface FlagDao {

    @SqlQuery("select flag_name from flags where num_flag = :num")
    String getFlag(@Bind("num") int num);
}
