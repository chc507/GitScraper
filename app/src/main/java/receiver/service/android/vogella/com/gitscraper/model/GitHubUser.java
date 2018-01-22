package receiver.service.android.vogella.com.gitscraper.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class GitHubUser implements Parcelable {

    @SerializedName("login")
    private String login;

    @SerializedName("name")
    private String name;

    @SerializedName("followers")
    private String followers;

    @SerializedName("following")
    private String following;

    @SerializedName("avatar_url")
    private String avatar;

    @SerializedName("email")
    private String email;

    public GitHubUser(String email,
                      String avatar,
                      String following,
                      String followers,
                      String name,
                      String login) {
        this.setEmail(email);
        this.setAvatar(avatar);
        this.setFollowing(following);
        this.setFollowers(followers);
        this.setName(name);
        this.setLogin(login);
    }

    protected GitHubUser(Parcel in) {
        login = in.readString();
        name = in.readString();
        followers = in.readString();
        following = in.readString();
        avatar = in.readString();
        email = in.readString();
    }

    public static final Creator<GitHubUser> CREATOR = new Creator<GitHubUser>() {
        @Override
        public GitHubUser createFromParcel(Parcel in) {
            return new GitHubUser(in);
        }

        @Override
        public GitHubUser[] newArray(int size) {
            return new GitHubUser[size];
        }
    };

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFollowers() {
        return followers;
    }

    public void setFollowers(String followers) {
        this.followers = followers;
    }

    public String getFollowing() {
        return following;
    }

    public void setFollowing(String following) {
        this.following = following;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(login);
        dest.writeString(name);
        dest.writeString(followers);
        dest.writeString(following);
        dest.writeString(avatar);
        dest.writeString(email);
    }
}
