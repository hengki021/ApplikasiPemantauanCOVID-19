package stud11418041.develops.applikasipemantauancovid_19;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected( MenuItem item) {
        if (item.getItemId()== R.id.menu_about){
            startActivity(new Intent(this, AboutActivity.class));
        } else if (item.getItemId() == R.id.menu_logout) {
            startActivity(new Intent(this, LoginActivity.class));
        } else if (item.getItemId() == R.id.menu_exit) {
            finish();
        }
        return true;
    }

}
