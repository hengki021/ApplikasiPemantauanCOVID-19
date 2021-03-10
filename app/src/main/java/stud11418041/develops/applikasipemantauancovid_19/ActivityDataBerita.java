package stud11418041.develops.applikasipemantauancovid_19;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.ref.WeakReference;
import java.util.List;

import stud11418041.develops.applikasipemantauancovid_19.adapter.BeritaAdapter;
import stud11418041.develops.applikasipemantauancovid_19.database.AppDatabase;
import stud11418041.develops.applikasipemantauancovid_19.model.Berita;

import static stud11418041.develops.applikasipemantauancovid_19.R.*;

public class ActivityDataBerita extends AppCompatActivity {
    private RecyclerView beritaRv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_data_berita);

        beritaRv = findViewById(id.beritaRv);
        LinearLayoutManager layoutManager = new LinearLayoutManager(ActivityDataBerita.this);
        beritaRv.setLayoutManager(layoutManager);

        new GetAllBerita(ActivityDataBerita.this).execute();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected( MenuItem item) {
        if (item.getItemId()== id.menu_about){
            startActivity(new Intent(this, AboutActivity.class));
            finish();
        } else if (item.getItemId() == id.menu_logout) {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        } else if (item.getItemId() == id.menu_exit) {
            finish();
        }
        return true;
    }

    public static class GetAllBerita extends AsyncTask<Void, Void, List<Berita>> {
        private WeakReference<Context> c;

        public GetAllBerita(Context c) {
            this.c = new WeakReference<>(c);
        }

        @Override
        protected List<Berita> doInBackground(Void... voids) {
            AppDatabase ud = AppDatabase.getAppDatabase(c.get());
            return ud.kuisDao().getAllBerita();
        }

        @Override
        protected void onPostExecute(List<Berita> beritas) {
            super.onPostExecute(beritas);
            RecyclerView rv = ((Activity) c.get()).findViewById(id.beritaRv);

            BeritaAdapter ua = new BeritaAdapter(c.get(), beritas);
            rv.setAdapter(ua);
        }
    }
}
