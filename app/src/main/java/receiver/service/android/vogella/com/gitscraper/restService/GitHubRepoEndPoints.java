package receiver.service.android.vogella.com.gitscraper.restService;

import java.util.List;

import receiver.service.android.vogella.com.gitscraper.model.GitHubRepo;
import receiver.service.android.vogella.com.gitscraper.model.GitHubRepoResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GitHubRepoEndPoints {
    /*
    @GET("/users/{user}/repos")
    Call<List<GitHubRepo>> getRepo(@Path("user") String name);
    */

    //search/repositories?q=AndroidComponents
    /*
    @GET("search/repositories")
    Call<List<GitHubRepo>> getRepo(@Query("q") String repoName);
    */

    @GET("search/repositories")
    Call<GitHubRepoResponse> getRepo(@Query("q") String repoName);

}


