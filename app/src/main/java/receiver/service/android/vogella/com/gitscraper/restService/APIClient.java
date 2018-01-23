package receiver.service.android.vogella.com.gitscraper.restService;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import receiver.service.android.vogella.com.gitscraper.model.GitHubRepo;
import receiver.service.android.vogella.com.gitscraper.model.GitHubRepoDeserializer;
import receiver.service.android.vogella.com.gitscraper.model.GitHubRepoResponse;
import receiver.service.android.vogella.com.gitscraper.model.GitHubResponseDeserializer;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by vanne on 1/21/2018.
 */

public class APIClient {

    public static final String BASE_URL = "https://api.github.com"; // base url + user end point
    private static Retrofit retrofit = null;

    /*
    private static Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
            .registerTypeAdapter(GitHubRepo.class, new GitHubRepoDeserializer())
            .create();
    */

    private static Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
            .registerTypeAdapter(GitHubRepoResponse.class, new GitHubResponseDeserializer())
            .create();

    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        return retrofit;
    }


}

