package receiver.service.android.vogella.com.gitscraper.restService;

import receiver.service.android.vogella.com.gitscraper.model.GitHubUser;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GitHubUserEndPoints {

    @GET("/users/{user}")
        //@Path -> substitution for API end point. the {user} needs to be uniquely taken
    Call<GitHubUser> getUser(@Path("user") String user);
}



