package receiver.service.android.vogella.com.gitscraper.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GitHubRepoResponse {
    //@SerializedName("items")
    private List<GitHubRepo> items;
    //@SerializedName("total_count")
    private int total_count;

    public List<GitHubRepo> getItems() {
        return items;
    }

    public void setItems(List<GitHubRepo> items) {
        this.items = items;
    }

    public int getTotal_count() {
        return total_count;
    }

    public void setTotal_count(int total_count) {
        this.total_count = total_count;
    }
}
