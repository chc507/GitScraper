package receiver.service.android.vogella.com.gitscraper.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import receiver.service.android.vogella.com.gitscraper.R;
import receiver.service.android.vogella.com.gitscraper.activity.RepoDetailActivity;
import receiver.service.android.vogella.com.gitscraper.model.GitHubRepo;

/**
 * Created by vanne on 1/21/2018.
 */


public class ReposAdapter extends RecyclerView.Adapter<ReposAdapter.ReposViewHolder>  {

    private List<GitHubRepo> repos;
    private int rowLayout;
    private Context context;

    public ReposAdapter(List<GitHubRepo> repos, int rowLayout, Context context) {
        this.setRepos(repos);
        this.setRowLayout(rowLayout);
        this.setContext(context);
    }

    public List<GitHubRepo> getRepos() {return repos;}

    public void setRepos(List<GitHubRepo> repos) {this.repos = repos;}

    public int getRowLayout() {return rowLayout;}

    public void setRowLayout(int rowLayout) {this.rowLayout = rowLayout;}

    public Context getContext() {return context;}

    public void setContext(Context context) {this.context = context;}

    @Override
    public ReposAdapter.ReposViewHolder onCreateViewHolder(ViewGroup parent,
                                                           int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new ReposViewHolder(view);
    }


    @Override
    public void onBindViewHolder(final ReposViewHolder holder, final int position) {
        holder.repoName.setText("Name: " + repos.get(position).getName());
        holder.repoOwner.setText("Owner: " + repos.get(position).getOwner());
        holder.repoStars.setText("Stars: " + repos.get(position).getStars());
        final GitHubRepo gitHubRepo = repos.get(position);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = holder.itemView.getContext();
                Intent intent = new Intent(context, RepoDetailActivity.class);
                intent.putExtra("repo", gitHubRepo);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() { return repos.size();}

    public class ReposViewHolder extends RecyclerView.ViewHolder {
        LinearLayout reposLayout;
        TextView repoName;
        TextView repoOwner;
        TextView repoStars;



        public ReposViewHolder(View v) {
            super(v);
            reposLayout = v.findViewById(R.id.repo_item_layout);
            repoName = v.findViewById(R.id.repoName);
            repoOwner = v.findViewById(R.id.repoOwner);
            repoStars = v.findViewById(R.id.repoStars);
        }
    }

}

