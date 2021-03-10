package stud11418041.develops.applikasipemantauancovid_19.model;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Berita {
        @PrimaryKey
        @ColumnInfo(name = "id")
        private int id;

        @ColumnInfo(name = "judul")
        private String judul;

        @ColumnInfo(name = "waktu")
        private String waktu;

        @ColumnInfo(name = "gambar")
        private String gambar;

        @ColumnInfo(name = "isi")
        private String isi;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getJudul() {
        return judul;
    }

    public String getWaktu() {
        return waktu;
    }

    public void setWaktu(String waktu) {
        this.waktu = waktu;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }



    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }

    public String getIsi() {
        return isi;
    }

    public void setIsi(String isi) {
        this.isi = isi;
    }
}
