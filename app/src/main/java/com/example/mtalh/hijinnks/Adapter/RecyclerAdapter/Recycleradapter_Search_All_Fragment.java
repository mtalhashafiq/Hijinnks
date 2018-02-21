package com.example.mtalh.hijinnks.Adapter.RecyclerAdapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.mtalh.hijinnks.Models.Model_Search_ALL;
import com.example.mtalh.hijinnks.R;

import java.util.ArrayList;

/**
 * Created by mtalh on 26-Oct-17.
 */

public class Recycleradapter_Search_All_Fragment extends RecyclerView.Adapter<Recycleradapter_Search_All_Fragment.RecyclerViewHolder> {
    static Recycleradapter_Search_All_Fragment.ItemClickListener itemClickListener;
    Context context;
    ArrayList<Model_Search_ALL> arrayList_search = new ArrayList<>();

    public Recycleradapter_Search_All_Fragment(ArrayList<Model_Search_ALL> arrayList,Context context) {
        this.context=context;
        this.arrayList_search = arrayList;
    }

    @Override
    public Recycleradapter_Search_All_Fragment.RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listrow_search_all_fragment, parent, false);
        Recycleradapter_Search_All_Fragment.RecyclerViewHolder recyclerviewholder = new Recycleradapter_Search_All_Fragment.RecyclerViewHolder(view);
       /* RecyclerView.LayoutParams lp = (RecyclerView.LayoutParams) view.getLayoutParams();
        lp.height = parent.getMeasuredHeight()/4;
        view.setLayoutParams(lp);*/
        return recyclerviewholder;

    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {


        holder.Search_profile_image.setImageResource(arrayList_search.get(position).getProfile_image());
        holder.Search_profile_name.setText(arrayList_search.get(position).getProfile_name());
        holder.Search_number_of_invites.setText(arrayList_search.get(position).getNumber_of_invites());
        holder.Search_text_follow.setText(arrayList_search.get(position).getText_follow());
        holder.Search_follow_background.setBackgroundResource(arrayList_search.get(position).getFollow_background());
    }


    @Override
    public int getItemCount() {
        return arrayList_search.size();
    }
    public void SsetClickListener_NavigationDrawer(Recycleradapter_Search_All_Fragment.ItemClickListener listener) {
        this.itemClickListener = listener;
    }
    public void setFilter(ArrayList newList) {

        arrayList_search=newList;
        notifyDataSetChanged();
    }
    public interface ItemClickListener {
        public void OnItemClick(int Pos);
    }
    public static class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        de.hdodenhof.circleimageview.CircleImageView Search_profile_image;
        TextView Search_profile_name, Search_number_of_invites, Search_text_follow;
        LinearLayout Search_follow_background;
        public RecyclerViewHolder(View view) {
            super(view);

            Search_profile_image = (de.hdodenhof.circleimageview.CircleImageView) view.findViewById(R.id.search_profile_image);
            Search_profile_name = (TextView) view.findViewById(R.id.search_profile_name);
            Search_number_of_invites = (TextView) view.findViewById(R.id.search_numberof_invites);
            Search_text_follow = (TextView) view.findViewById(R.id.search_follow_text);
            Search_follow_background = (LinearLayout) view.findViewById(R.id.search_backgroung_follow);


            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            itemClickListener.OnItemClick(getAdapterPosition());
        }
    }

}