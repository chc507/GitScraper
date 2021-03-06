package receiver.service.android.vogella.com.gitscraper.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;

public class GitHubRepo implements Parcelable {
    private String name;
    private String owner;
    private String stars;
    private String description;
    private String language;
    private String watchers;

    public GitHubRepo() {

    }

    protected GitHubRepo(Parcel in) {
        name = in.readString();
        owner = in.readString();
        stars = in.readString();
        description = in.readString();
        language = in.readString();
        watchers = in.readString();
        Log.e("here", "description");
    }

    public static final Creator<GitHubRepo> CREATOR = new Creator<GitHubRepo>() {
        @Override
        public GitHubRepo createFromParcel(Parcel in) {
            return new GitHubRepo(in);
        }

        @Override
        public GitHubRepo[] newArray(int size) {
            return new GitHubRepo[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getStars() {
        return stars;
    }

    public void setStars(String stars) {
        this.stars = stars;
    }

    public String getWatchers() {
        return watchers;
    }

    public void setWatchers(String watchers) {
        this.watchers = watchers;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(owner);
        dest.writeString(stars);
        dest.writeString(description);
        dest.writeString(language);
        dest.writeString(watchers);
    }
}
