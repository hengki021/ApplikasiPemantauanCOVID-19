package stud11418041.develops.applikasipemantauancovid_19;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.lang.ref.WeakReference;

import stud11418041.develops.applikasipemantauancovid_19.database.AppDatabase;
import stud11418041.develops.applikasipemantauancovid_19.model.User;

public class RegisterActivity extends AppCompatActivity {
    private EditText usernameTf, passwordTf, namaTf;
    private Button registerBtn, batalBtn;
    private RadioGroup rgState;
    private RadioButton rbAdmin, rbUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        namaTf = findViewById(R.id.namaTf);
        usernameTf = findViewById(R.id.usernameTf);
        passwordTf = findViewById(R.id.passwordTf);
        this.rgState = findViewById(R.id.rgState);
        this.rbAdmin = findViewById(R.id.rbAdmin);
        this.rbUser = findViewById(R.id.rbUser);

        registerBtn = findViewById(R.id.registerBtn);
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (namaTf.getText().toString().length() == 0) {
                    namaTf.setError("masukkan nama!");
                } else if (usernameTf.getText().toString().length() == 0) {
                    usernameTf.setError("masukkan username!");
                } else if (passwordTf.getText().toString().length() == 0) {
                    passwordTf.setError("masukkan password!");
                } else {

                    String nama = namaTf.getText().toString().trim();
                    String username = usernameTf.getText().toString().trim();
                    String password = passwordTf.getText().toString().trim();
                    String state = null;
                    int selectedId = rgState.getCheckedRadioButtonId();

                    if (selectedId == rbAdmin.getId()){
                        state ="admin";
                    } else if (selectedId == rbUser.getId()){
                        state = "user";
                    } else {
                        Toast.makeText(getApplicationContext(), "Pilih status anda ingin mendaftar sebagai admin atau user.", Toast.LENGTH_SHORT).show();
                    }

                    User user = new User();
                    user.setNama(nama);
                    user.setUsername(username);
                    user.setPassword(password);
                    user.setState(state);

                    new CreateUser(RegisterActivity.this, user, username).execute();

                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
        batalBtn = findViewById(R.id.batalBtn);
        batalBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }


    static class CreateUser extends AsyncTask<Void, Void, Void> {
        private User user;
        private WeakReference<Context> context;
        private String username;

        public CreateUser(Context context, User user, String username) {
            this.context = new WeakReference<>(context);
            this.user = user;
            this.username = username;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            AppDatabase db = AppDatabase.getAppDatabase(context.get());
            db.userDao().insert(user);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Toast.makeText(context.get(), "Berhasil melakukan registrasi", Toast.LENGTH_SHORT).show();
            ((Activity) context.get()).finish();
        }
    }
}
