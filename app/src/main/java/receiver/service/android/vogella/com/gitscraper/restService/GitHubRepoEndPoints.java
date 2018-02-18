package receiver.service.android.vogella.com.gitscraper.restService;

import java.util.List;

import receiver.service.android.vogella.com.gitscraper.model.GitHubRepo;
import receiver.service.android.vogella.com.gitscraper.model.GitHubRepoResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GitHubRepoEndPoints {
    //Query for GitHub Api
    @GET("search/repositories")
    Call<GitHubRepoResponse> getRepo(@Query("q") String repoName);

}


