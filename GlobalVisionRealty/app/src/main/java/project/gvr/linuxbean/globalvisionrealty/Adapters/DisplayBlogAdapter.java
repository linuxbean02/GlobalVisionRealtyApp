package project.gvr.linuxbean.globalvisionrealty.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import project.gvr.linuxbean.globalvisionrealty.Activities.MainActivity;
import project.gvr.linuxbean.globalvisionrealty.LocalModels.BlogListings;
import project.gvr.linuxbean.globalvisionrealty.R;

/**
 * Created by user on 2/15/2018.
 */

public class DisplayBlogAdapter extends RecyclerView.Adapter<DisplayBlogAdapter.ItemHolder> {

    Context context;
    ArrayList<BlogListings> blogListings;

    public DisplayBlogAdapter(MainActivity mainActivity, ArrayList<BlogListings> blogListings) {
        context = mainActivity;
        this.blogListings = blogListings;
    }

    @Override
    public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_display_blog,parent,false);
        return new ItemHolder(v);
    }

    @Override
    public void onBindViewHolder(ItemHolder holder, int position) {
            BlogListings blog = blogListings.get(position);
            holder.BlogText.setText(blog.getBlogText());
    }

    @Override
    public int getItemCount() {
        return blogListings.size();
    }

    public class ItemHolder extends RecyclerView.ViewHolder {

        ImageView BlogImages;
        TextView BlogText;

        public ItemHolder(View itemView) {
            super(itemView);
            BlogImages = itemView.findViewById(R.id.blog_images);
            BlogText = itemView.findViewById(R.id.blog_text);
        }
    }
}
