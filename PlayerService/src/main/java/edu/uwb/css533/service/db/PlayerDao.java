package edu.uwb.css533.service.db;

import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

public interface PlayerDao {

    @SqlUpdate("insert into player_credentials (username, password, user_id, logged_in, active_session, in_game) values (:name, :pass, :id, :logged_in, :session, :in_game)")
    void insert(@Bind("name") String name, @Bind("pass") String pass, @Bind("id") int id, @Bind("logged_in") boolean logged_in, @Bind("session") int session, @Bind("in_game") boolean game);

    @SqlQuery("select user_id from player_credentials where username = :name")
    Integer findUserIdByUsername(@Bind("name") String name);

    @SqlQuery("select username from player_credentials where user_id = :id")
    String findUsernameByUserId(@Bind("id") int id);

    @SqlQuery("select password from player_credentials where user_id = :id")
    String findPasswordByUserId(@Bind("id") int id);

    @SqlUpdate("update player_credentials set logged_in = :logged_in where user_id = :id")
    void logIn(@Bind("logged_in") boolean logged_in, @Bind("id") int id);
}
