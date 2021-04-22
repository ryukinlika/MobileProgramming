package id.ac.umn.week11_29240;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder> {

    private final LayoutInflater mInflater;
    private ArrayList<PostModel> posts = new ArrayList<PostModel>();

    public PostAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.post_item_layout, parent, false);
        return new PostViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {
        if(posts != null){
            PostModel post = posts.get(position);
            holder.tvTitle.setText(post.getId() + ". " +  post.getTitle());
            holder.tvBody.setText(post.getBody());
        }
        else{
            holder.tvTitle.setText("No post yet.");
        }
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }


    class PostViewHolder extends RecyclerView.ViewHolder{
        private final TextView tvTitle, tvBody;

        public PostViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvBody = itemView.findViewById(R.id.tvBody);
        }
    }

    void setPosts(ArrayList<PostModel> newposts){
        posts = newposts;
        notifyDataSetChanged();
    }
}
