package receiver.service.android.vogella.com.gitscraper.model;

import android.util.Log;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vanne on 1/21/2018.
 */

public class GitHubResponseDeserializer implements JsonDeserializer<GitHubRepoResponse> {
    @Override
    public GitHubRepoResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        //
        GitHubRepoResponse gitHubRepoResponse = new GitHubRepoResponse();

        List<GitHubRepo> gitHubRepos = new ArrayList<>();

        JsonObject responseJsonObject =  json.getAsJsonObject();
        gitHubRepoResponse.setTotal_count(responseJsonObject.get("total_count").getAsInt());

        JsonArray repoArray = responseJsonObject.getAsJsonArray("items");
        for (int i = 0; i < repoArray.size(); i++) {
            JsonElement repoElement =  repoArray.get(i);
            JsonObject repoJsonObject = repoElement.getAsJsonObject();

            GitHubRepo githubRepo = new GitHubRepo();
            githubRepo.setName(repoJsonObject.get("name").toString());

            JsonElement ownerJsonElement = repoJsonObject.get("owner");
            JsonObject ownerJsonObject = ownerJsonElement.getAsJsonObject();
            githubRepo.setOwner(ownerJsonObject.get("login").toString());

            githubRepo.setStars(repoJsonObject.get("stargazers_count").toString());
            githubRepo.setLanguage(repoJsonObject.get("language").toString());
            githubRepo.setDescription(repoJsonObject.get("description").toString());
            githubRepo.setWatchers(repoJsonObject.get("watchers_count").toString());
            gitHubRepos.add(githubRepo);
        }
        Log.e("Here,", String.valueOf(gitHubRepos.size()));
        gitHubRepoResponse.setItems(gitHubRepos);
        return gitHubRepoResponse;
    }
}
