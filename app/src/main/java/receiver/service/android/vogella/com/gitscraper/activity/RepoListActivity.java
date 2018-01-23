package receiver.service.android.vogella.com.gitscraper.activity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.MenuItemHoverListener;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import receiver.service.android.vogella.com.gitscraper.R;
import receiver.service.android.vogella.com.gitscraper.adapter.ReposAdapter;
import receiver.service.android.vogella.com.gitscraper.model.GitHubRepo;
import receiver.service.android.vogella.com.gitscraper.model.GitHubRepoResponse;
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
    private Toolbar mToolbar;

    //add search bar
    MenuItem mSearchAction;
    boolean isSearchOpened = false;
    EditText editSearch;

    //Default Username
    String QueryKey = "vannesschancc";

    //TAG
    private final static String TAG = "RepoList Activity";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repo_list);
        setTitle("");

        //Get list of repo
        mRecyclerView = findViewById(R.id.repos_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new ReposAdapter(mDataSource, R.layout.list_item_repo, getApplicationContext());
        mRecyclerView.setAdapter(mAdapter);

        //Get custom toolbar;
        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        //Linear layout
        linearLayout = findViewById(R.id.linearLayout);


        //Check network and load data
        loadData();
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public void loadData(){
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
            loadRepositories();
        }
    }

    //load repos
    public void loadRepositories() {
        GitHubRepoEndPoints apiService = APIClient.getClient().create(GitHubRepoEndPoints.class);
        Call<GitHubRepoResponse> call = apiService.getRepo(QueryKey);
        call.enqueue(new Callback<GitHubRepoResponse>() {
            @Override
            public void onResponse(Call<GitHubRepoResponse> call, Response<GitHubRepoResponse> response) {
                mDataSource.clear();
                Log.e(TAG + "S ", String.valueOf(response.body().getItems().size()));
                mDataSource.addAll(response.body().getItems());
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<GitHubRepoResponse> call, Throwable t) {
                Log.e(TAG + "error", t.toString());
            }
        });



    }

    //Search
    protected void handleMenuSearch(){
        ActionBar action = getSupportActionBar(); //get the actionbar

        if(isSearchOpened){ //test if the search is open

            action.setDisplayShowCustomEnabled(false); //disable a custom view inside the actionbar
            action.setDisplayShowTitleEnabled(true); //show the title in the action bar

            //hides the keyboard
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(editSearch.getWindowToken(), 0);

            //add the search icon in the action bar
            mSearchAction.setIcon(getResources().getDrawable(R.drawable.ic_open_search));

            isSearchOpened = false;
        } else { //open the search entry

            action.setDisplayShowCustomEnabled(true); //enable it to display a
            // custom view in the action bar.
            action.setCustomView(R.layout.toolbar_searchbar);//add the custom view
            action.setDisplayShowTitleEnabled(false); //hide the title

            editSearch = action.getCustomView().findViewById(R.id.edtSearch); //the text editor
            editSearch.setEnabled(true);
            editSearch.setVisibility(View.VISIBLE);



            //this is a listener to do a search when the user clicks on search button
            editSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                    if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                        //doSearch();
                        QueryKey = editSearch.getText().toString();
                        loadData();
                        return true;
                    }
                    return false;
                }
            });


            editSearch.requestFocus();
            //open the keyboard focused in the edtSearch
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(editSearch, InputMethodManager.SHOW_IMPLICIT);

            //add the close icon
            mSearchAction.setIcon(getResources().getDrawable(R.drawable.ic_close_search));
            isSearchOpened = true;
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_repo_list,menu);
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int i = item.getItemId();
        if (i == R.id.settings) {
            startActivity(new Intent(getApplicationContext(), SettingsActivity.class));
            return true;
        } else if (i == R.id.refresh) {
            loadData();
            return true;
        } else if (i == R.id.search) {
            handleMenuSearch();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        mSearchAction = menu.findItem(R.id.search);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public void onBackPressed() {
        if(isSearchOpened) {
            handleMenuSearch();
            return;
        }
        super.onBackPressed();
        finish();
    }
}