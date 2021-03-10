package stud11418041.develops.applikasipemantauancovid_19;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.lang.ref.WeakReference;

import stud11418041.develops.applikasipemantauancovid_19.database.AppDatabase;
import stud11418041.develops.applikasipemantauancovid_19.model.User;

public class LoginActivity extends AppCompatActivity {
    Button login;
    EditText usernameTf, passwordTf;
    TextView registerLink;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login = findViewById(R.id.registerBtn);
        usernameTf = findViewById(R.id.usernameTf);
        passwordTf = findViewById(R.id.passwordTf);

        registerLink = findViewById(R.id.registerLink);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (usernameTf.getText().toString().length() == 0) {
                    usernameTf.setError("masukkan username!");
                } else if (passwordTf.getText().toString().length() == 0) {
                    passwordTf.setError("masuukan password!");
                } else {
                    String username = usernameTf.getText().toString();
                    String password = passwordTf.getText().toString();

                    new Login(LoginActivity.this, username, password).execute();
                }
            }
        });

        registerLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });


    }

    class Login extends AsyncTask<Void, Void, User> {
        private WeakReference<Context> c;
        private String username;
        private String password;

        public Login(Context c, String username, String password) {
            this.c = new WeakReference<>(c);
            this.username = username;
            this.password = password;
        }

        @Override
        protected User doInBackground(Void... voids) {
            AppDatabase db = AppDatabase.getAppDatabase(c.get());
            User user = db.userDao().getUser(username);
            return user;
        }

        @Override
        protected void onPostExecute(User u) {
            super.onPostExecute(u);

            EditText usernameTf = ((Activity) c.get()).findViewById(R.id.usernameTf);
            try {
                usernameTf.setText(u.getUsername());
                if (u.getUsername() != null && password.equals(u.getPassword())) {
                    if(u.getState().equals("user")) {
                        Toast.makeText(c.get(), "Berhasil login sebagai user",
                                Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(c.get(),DashboardUserActivity.class);
                        startActivity(intent);
                        finish();
                    }else if(u.getState().equals("admin")){
                        Toast.makeText(c.get(), "Berhasil login sebagai admin",
                                Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(c.get(),DashboardAdminActivity.class);
                        startActivity(intent);
                        finish();
                    }
                } else {
                    AlertDialog.Builder alert = new AlertDialog.Builder(LoginActivity.this);
                    alert.setMessage("Username atau password yang anda masukkan salah!")
                            .setNegativeButton("Retry", null).create().show();
                }

            } catch (NullPointerException ex) {
                AlertDialog.Builder alert = new AlertDialog.Builder(LoginActivity.this);
                alert.setMessage("Username atau password yang anda masukkan tidak terdaftar!")
                        .setNegativeButton("Coba lagi", null).create().show();
            }


        }
    }


}
