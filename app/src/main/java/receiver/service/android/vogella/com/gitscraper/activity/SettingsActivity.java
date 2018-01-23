package receiver.service.android.vogella.com.gitscraper.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import receiver.service.android.vogella.com.gitscraper.R;

public class SettingsActivity extends AppCompatActivity implements View.OnClickListener{
    Button logoutBtn;
    SharedPreferences sp;
    SharedPreferences.Editor editor;
    TextView currentUserTV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_session);


        logoutBtn = findViewById(R.id.logout);
        logoutBtn.setOnClickListener(this);

        currentUserTV = findViewById(R.id.currentUser);

        sp = getApplicationContext().getSharedPreferences("GitScraper", 0);
        editor = sp.edit();
        currentUserTV.setText(sp.getString("userEmail","Nothing"));

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.logout) {
            editor.clear();
            editor.commit();
            Intent intent = new Intent(SettingsActivity.this, LogInActivity.class);
            startActivity(intent);
            finish();
        }
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(SettingsActivity.this, RepoListActivity.class);
        startActivity(intent);
        finish();
    }
}
