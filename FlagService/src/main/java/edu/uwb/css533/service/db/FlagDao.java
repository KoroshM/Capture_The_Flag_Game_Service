package edu.uwb.css533.service.db;

import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

public interface FlagDao {

    @SqlQuery("select flag_name from flags where flag_id = :id")
    String getFlag(@Bind("id") int id);

    @SqlQuery("select feature1 from flags where flag_id = :id")
    String getFeature1(@Bind("id") int id);

    @SqlQuery("select feature2 from flags where flag_id = :id")
    String getFeature2(@Bind("id") int id);

    @SqlQuery("select feature3 from flags where flag_id = :id")
    String getFeature3(@Bind("id") int id);

    @SqlUpdate("update room_sessions set current_flag = :flag where session_id = :id")
    void updateFlag(@Bind("flag") String flag, @Bind("id") int id);

    @SqlUpdate("update room_sessions set feature1 = :f1 where session_id = :id")
    void updateFeature1(@Bind("f1") String f1, @Bind("id") int id);

    @SqlUpdate("update room_sessions set feature2 = :f2 where session_id = :id")
    void updateFeature2(@Bind("f2") String f2, @Bind("id") int id);

    @SqlUpdate("update room_sessions set feature3 = :f3 where session_id = :id")
    void updateFeature3(@Bind("f3") String f1, @Bind("id") int id);
}
