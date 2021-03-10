package stud11418041.develops.applikasipemantauancovid_19.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import stud11418041.develops.applikasipemantauancovid_19.model.User;

@Dao
public interface UserDao {
    @Insert
    Long insert(User user);

    @Query("SELECT * FROM `User` WHERE `username` =:username")
    User getUser(String username);
}
