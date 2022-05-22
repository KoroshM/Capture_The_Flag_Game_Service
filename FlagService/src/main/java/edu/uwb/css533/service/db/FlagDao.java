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

    @SqlQuery("select feature_id from features where feature_name = :feature_name")
    Integer getFeatureCode(@Bind("feature_name") String feature_name);

    @SqlUpdate("update room_sessions set current_flag = :flag where session_id = :sid")
    void updateFlag(@Bind("flag") String flag, @Bind("id") int sid);

    @SqlUpdate("update room_sessions set feature1 = :f1 where session_id = :sid")
    void updateFeature1(@Bind("f1") String f1, @Bind("id") int sid);

    @SqlUpdate("update room_sessions set feature2 = :f2 where session_id = :sid")
    void updateFeature2(@Bind("f2") String f2, @Bind("id") int sid);

    @SqlUpdate("update room_sessions set feature3 = :f3 where session_id = :sid")
    void updateFeature3(@Bind("f3") String f1, @Bind("id") int sid);

    @SqlUpdate("update room_sessions set f1_code = :f1_code where session_id = :sid")
    void updateFeature1Code(@Bind("f1_code") Integer f1_code, @Bind("sid") int sid);

    @SqlUpdate("update room_sessions set f2_code = :f2_code where session_id = :sid")
    void updateFeature2Code(@Bind("f2_code") Integer f2_code, @Bind("sid") int sid);

    @SqlUpdate("update room_sessions set f3_code = :f3_code where session_id = :sid")
    void updateFeature3Code(@Bind("f3_code") Integer f3_code, @Bind("sid") int sid);
}
