package edu.uwb.css533.service;

import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

public interface UserDao {

    @SqlUpdate("insert into player_credentials (username, password, user_id) values (:name, :pass, :id)")
    void insert(@Bind("name") String name, @Bind("pass") String pass, @Bind("id") int id);

    @SqlQuery("select user_id from player_credentials where username = :name")
    Integer findUserIdByUsername(@Bind("name") String name);

    @SqlQuery("select username from player_credentials where user_id = :id")
    String findUsernameByUserId(@Bind("id") int id);
}
