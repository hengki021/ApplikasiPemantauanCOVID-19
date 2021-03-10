package stud11418041.develops.applikasipemantauancovid_19;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import stud11418041.develops.applikasipemantauancovid_19.controller.BeritaController;
import stud11418041.develops.applikasipemantauancovid_19.model.Berita;
import stud11418041.develops.applikasipemantauancovid_19.response.ResponseBerita;

public class LoadingDataActivity extends AppCompatActivity {
    Context myContext;
    APIBerita apiBerita;
    ProgressDialog progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading_data);

        myContext = getApplicationContext();
        progress = ProgressDialog.show(LoadingDataActivity.this, "Insialisasi Data", "Sedang Mengunduh Data Untuk Aplikasi", true);

        Callback<ResponseBerita> dataBerita = new Callback<ResponseBerita>() {
            @Override
            public void onResponse(Call<ResponseBerita> call, Response<ResponseBerita> response) {
                if (response.isSuccessful()) {
                    List<Berita> beritas = response.body().getData();
                    int jumlahData = response.body().getData().size();
                    if (jumlahData > 0) {
                        BeritaController mc = new BeritaController(myContext);
                        mc.open();
                        mc.deleteData();
                        for (int x = 0; x < jumlahData; x++) {
                            Berita tmpHasil = beritas.get(x);
                            mc.insertData(tmpHasil.getId(), tmpHasil.getJudul() , tmpHasil.getWaktu(), tmpHasil.getGambar(),tmpHasil.getIsi());
                        }
                        mc.close();

                        Intent sendIntent = new Intent(myContext, ActivityDataBerita.class);
                        startActivity(sendIntent);
                        finish();
                    } else {
                        Toast.makeText(getApplicationContext(), "DATA SEDANG TIDAK TERSEDIA", Toast.LENGTH_LONG).show();
                        Intent sendIntent = new Intent(myContext, LoginActivity.class);
                        startActivity(sendIntent);
                        finish();
                    }
                    progress.dismiss();

                } else {
                    Log.e("onResponse failure", "Code: " + response.code() + " , Message: " + response.message());
                    Intent sendIntent = new Intent(myContext, LoginActivity.class);
                    startActivity(sendIntent);
                    finish();
                }
            }

            @Override
            public void onFailure(Call<ResponseBerita> call, Throwable t) {
                // TODO Auto-generated method stub
                progress.dismiss();
                Toast.makeText(getApplicationContext(), "AKSES KE SERVER GAGAL" + t.getMessage(), Toast.LENGTH_LONG).show();
                Intent sendIntent = new Intent(myContext, LoginActivity.class);
                startActivity(sendIntent);
                finish();
            }
        };

        //consuming web service here
        apiBerita = RESTClient.get();
        Call<ResponseBerita> callData = apiBerita.getBerita();
        callData.enqueue(dataBerita);
    }

}
