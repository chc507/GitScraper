package receiver.service.android.vogella.com.gitscraper.activity;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import receiver.service.android.vogella.com.gitscraper.R;
import receiver.service.android.vogella.com.gitscraper.adapter.ReposAdapter;
import receiver.service.android.vogella.com.gitscraper.model.GitHubRepo;
import receiver.service.android.vogella.com.gitscraper.restService.APIClient;
import receiver.service.android.vogella.com.gitscraper.restService.GitHubRepoEndPoints;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RepoListActivity extends AppCompatActivity {
    //UI Element
    RecyclerView mRecyclerView;
    List<GitHubRepo> mDataSource = new ArrayList<>();
    RecyclerView.Adapter mAdapter;
    LinearLayout linearLayout;

    //add search bar

    //Default Username
    String username = "vannesschancc";

    //TAG
    private final static String TAG = "RepoList Activity";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repo_list);
        /*
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        */

        //Get list of repo
        mRecyclerView = findViewById(R.id.repos_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new ReposAdapter(mDataSource, R.layout.list_item_repo, getApplicationContext());
        mRecyclerView.setAdapter(mAdapter);

        //Linear
        linearLayout = findViewById(R.id.linearLayout);



        //checking for network connectivity
        if (!isNetworkAvailable()) {
            Snackbar snackbar = Snackbar
                    .make(linearLayout, "No Network connection", Snackbar.LENGTH_LONG)
                    .setAction("RETRY", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            loadRepositories();
                        }
                    });

            snackbar.show();
        } else {
            //load repo
            loadRepositories();
        }
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }


    public void loadRepositories() {
        GitHubRepoEndPoints apiService = APIClient.getClient().create(GitHubRepoEndPoints.class);
        Call<List<GitHubRepo>> call = apiService.getRepo("AndroidComponents");
        Log.e(TAG, username);
        call.enqueue(new Callback<List<GitHubRepo>>() {
            @Override
            public void onResponse(Call<List<GitHubRepo>> call, Response<List<GitHubRepo>> response) {
                mDataSource.clear();
                mDataSource.addAll(response.body());
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<GitHubRepo>> call, Throwable t) {
                Log.e(TAG, t.getMessage());
            }
        });



    }


















}