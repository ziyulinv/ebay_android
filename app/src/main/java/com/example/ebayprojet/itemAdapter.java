package com.example.ebayprojet;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class itemAdapter extends RecyclerView.Adapter<itemAdapter.MyViewHolder> {
    private ArrayList<ItemClass> mItemClass;
    private Context mContext;
    private OnItemClickListener mListener;

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.mListener = listener;
    }

    public itemAdapter(Context context, ArrayList<ItemClass> itemclass){
        this.mItemClass = itemclass;
        this.mContext = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View itemView = LayoutInflater.from(this.mContext)
                .inflate(R.layout.cardview, viewGroup, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder viewHolder, int i) {
        ItemClass currentItem = this.mItemClass.get(i);

        viewHolder.titlePreview.setText(currentItem.gettitle());
        if(currentItem.getshippingPrice().equals("$0.0")){
            viewHolder.free.setVisibility(View.VISIBLE);
            viewHolder.freeShipping.setVisibility(View.VISIBLE);
            viewHolder.shipsFor.setVisibility(View.GONE);
            viewHolder.shippingPricePreview.setVisibility(View.GONE);
        }
        else {
            viewHolder.shippingPricePreview.setText(currentItem.getshippingPrice());
        }
        viewHolder.conditionPreview.setText(currentItem.getcondition());
        viewHolder.pricePreview.setText(currentItem.getprice());
        if(currentItem.getMtopRated().equals("true"))
            viewHolder.topRatedPreview.setVisibility(View.VISIBLE);
        if(currentItem.getImageUrl().equals("https://thumbs1.ebaystatic.com/pict/04040_0.jpg")){
            viewHolder.imagePreview.setImageDrawable(mContext.getDrawable(R.drawable.ebay_default));
        }
        else {
            Picasso.with(this.mContext).load(currentItem.getImageUrl()).fit().centerInside().into(viewHolder.imagePreview);
        }
    }

    @Override
    public int getItemCount() {
        return this.mItemClass.size();
    }
    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView titlePreview,shippingPricePreview,conditionPreview,pricePreview,topRatedPreview;
        TextView shipsFor, free, freeShipping;
        ImageView imagePreview;
        LinearLayout parent;
        public MyViewHolder(View itemView) {
            super(itemView);
            parent = itemView.findViewById(R.id.parent);
            imagePreview = itemView.findViewById(R.id.imagePreview);
            titlePreview = itemView.findViewById(R.id.titlePreview);
            shippingPricePreview = itemView.findViewById(R.id.shippingPricePreview);
            conditionPreview = itemView.findViewById(R.id.conditionPreview);
            pricePreview = itemView.findViewById(R.id.pricePreview);
            topRatedPreview = itemView.findViewById(R.id.topRatedPreview);
            shipsFor = itemView.findViewById(R.id.shipsFor);
            free =itemView.findViewById(R.id.free);
            freeShipping = itemView.findViewById(R.id.freeShipping);

            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    if (mListener != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            mListener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }
}
