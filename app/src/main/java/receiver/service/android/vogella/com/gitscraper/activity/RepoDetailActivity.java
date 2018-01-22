package receiver.service.android.vogella.com.gitscraper.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import receiver.service.android.vogella.com.gitscraper.R;
import receiver.service.android.vogella.com.gitscraper.model.GitHubRepo;

public class RepoDetailActivity extends AppCompatActivity {
    private String language = "";
    private String description = "";
    private String name = "";
    private String owner = "";
    private String stars = "";
    private String watchers = "";
    private GitHubRepo gitHubRepo;

    TextView languageTV;
    TextView descriptionTV;
    TextView nameTV;
    TextView ownerTV;
    TextView startsTV;
    TextView watchersTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repo_detail);

        //getting intent extra
        gitHubRepo = getIntent().getParcelableExtra("repo");
        if (gitHubRepo != null) {
            language = gitHubRepo.getLanguage();
            description = gitHubRepo.getDescription();
            owner = gitHubRepo.getOwner();
            name = gitHubRepo.getName();
            stars= gitHubRepo.getStars();
            watchers = gitHubRepo.getWatchers();
        }

        languageTV = findViewById(R.id.repoDetailLanguage);
        descriptionTV = findViewById(R.id.repoDetailDescription);
        nameTV = findViewById(R.id.repoDetailName);
        ownerTV = findViewById(R.id.repoDetailOwner);
        startsTV = findViewById(R.id.repoDetailStars);
        watchersTV = findViewById(R.id.repoDetailWatchers);

        nameTV.setText("Name: " + name);
        languageTV.setText("Language: " + language);
        descriptionTV.setText("Description: " + description);
        ownerTV.setText("Owner: " +  owner);
        startsTV.setText("Stars: " + stars);
        watchersTV.setText("Watchers: " + watchers);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
