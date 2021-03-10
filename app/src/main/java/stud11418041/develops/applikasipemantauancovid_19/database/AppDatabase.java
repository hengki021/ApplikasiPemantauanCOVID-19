package stud11418041.develops.applikasipemantauancovid_19.database;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;

import stud11418041.develops.applikasipemantauancovid_19.dao.BeritaDao;
import stud11418041.develops.applikasipemantauancovid_19.dao.UserDao;
import stud11418041.develops.applikasipemantauancovid_19.model.Berita;
import stud11418041.develops.applikasipemantauancovid_19.model.User;

@androidx.room.Database(entities = {User.class, Berita.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();
    public abstract BeritaDao kuisDao();




    private static AppDatabase INSTANCE;

    public static AppDatabase getAppDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "myDatabase").build();
        }
        return INSTANCE;
    }


    public static void destroyInstance() {
        INSTANCE = null;
    }

}
