package receiver.service.android.vogella.com.gitscraper.model;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

/**
 * Created by vanne on 1/21/2018.
 */

public class GithubRepoDeserializer implements JsonDeserializer<GitHubRepo> {
    @Override
    public GitHubRepo deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        GitHubRepo githubRepo = new GitHubRepo();

        JsonObject repoJsonObject =  json.getAsJsonObject();
        githubRepo.setName(repoJsonObject.get("name").getAsString());
        JsonElement ownerJsonElement = repoJsonObject.get("owner");
        JsonObject ownerJsonObject = ownerJsonElement.getAsJsonObject();
        githubRepo.setOwner(ownerJsonObject.get("login").getAsString());

        return githubRepo;
    }
}
