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
import project.gvr.linuxbean.globalvisionrealty.LocalModels.FeaturedListing;
import project.gvr.linuxbean.globalvisionrealty.R;

/**
 * Created by user on 2/14/2018.
 */

public class FeaturedListingAdapter extends RecyclerView.Adapter<FeaturedListingAdapter.ItemHolder>{

    ArrayList<FeaturedListing> featuredListings;
    Context context;

    public FeaturedListingAdapter(MainActivity mainActivity, ArrayList<FeaturedListing> featuredListings) {
        context = mainActivity;
        this.featuredListings = featuredListings;
    }

    @Override
    public FeaturedListingAdapter.ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_featured_listing,parent,false);
        return new ItemHolder(v);
    }

    @Override
    public void onBindViewHolder(FeaturedListingAdapter.ItemHolder holder, int position) {
             FeaturedListing f = featuredListings.get(position);
             holder.NoOfPhotos.setText(f.getNoOfPhotos());
             holder.RateIt.setText(f.getRateIt());
             holder.AddressText.setText(f.getAddress());
             holder.MLSCode.setText(f.getMlsCode());
             holder.Price.setText(f.getPrice());
             holder.Beds.setText(f.getBeds());
             holder.Baths.setText(f.getBaths());
    }

    @Override
    public int getItemCount() {
        return featuredListings.size();
    }

    public class ItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        ImageView FeaturedImage;
        TextView NoOfPhotos,RateIt,AddressText,MLSCode,Price,Beds,Baths;

        public ItemHolder(View itemView) {
            super(itemView);
            FeaturedImage = itemView.findViewById(R.id.featured_image);
            NoOfPhotos = itemView.findViewById(R.id.no_of_photos);
            RateIt = itemView.findViewById(R.id.rate_it);
            AddressText = itemView.findViewById(R.id.address_text);
            MLSCode = itemView.findViewById(R.id.mls_code);
            Price = itemView.findViewById(R.id.price);
            Beds = itemView.findViewById(R.id.beds);
            Baths = itemView.findViewById(R.id.baths);
            Clickables();
        }

        public void Clickables(){
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

        }
    }

}
