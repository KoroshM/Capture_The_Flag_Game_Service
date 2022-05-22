package edu.uwb.css533.service.db;

import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

public interface PlayerDao {

    @SqlUpdate("insert into player_information (username, password, user_id, logged_in) values (:username, :password, :user_id, :logged_in)")
    void insert(@Bind("username") String username, @Bind("pass") String password, @Bind("user_id") int user_id, @Bind("logged_in") boolean logged_in);

    @SqlQuery("select user_id from player_information where username = :username")
    Integer findUserIdByUsername(@Bind("username") String username);

//    @SqlQuery("select username from player_information where user_id = :id")
//    String findUsernameByUserId(@Bind("id") int id);

    @SqlQuery("select password from player_information where user_id = :user_id")
    String findPasswordByUserId(@Bind("user_id") int user_id);

    @SqlUpdate("update player_information set logged_in = :logged_in where user_id = :user_id")
    void logIn(@Bind("logged_in") boolean logged_in, @Bind("user_id") int user_id);
}
