package stud11418041.develops.applikasipemantauancovid_19.dao;

import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

import stud11418041.develops.applikasipemantauancovid_19.model.Berita;

@Dao
public interface BeritaDao {
    @Query("SELECT * FROM `Berita` ORDER BY `id` DESC")
    List<Berita> getAllBerita();

}
