package com.example.connecty;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.connecty.ListOfGroup;
import com.example.connecty.MapsActivity;
import com.example.connecty.R;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;

import java.io.Serializable;
import java.util.ArrayList;

public class ListOfGroupAdapter extends RecyclerView.Adapter<ListOfGroupAdapter.GroupViewHolder> {

    private final ArrayList<Group> groupName;
    private final LayoutInflater mInflater;
    public final FragmentActivity fragmentActivity;

    public class GroupViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public final TextView groupNameView;
        public final ImageView iconView;
        final ListOfGroupAdapter mAdapter;

        public GroupViewHolder(View itemView, ListOfGroupAdapter adapter) {
            super(itemView);
            groupNameView = (TextView) itemView.findViewById(R.id.smallDescrip);
            iconView = (ImageView) itemView.findViewById(R.id.iconView);

            this.mAdapter = adapter;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

            Log.d("Test","Enter");

            Intent intent = new Intent(v.getContext(),InfomationActivity.class);
            intent.putExtra("Group",groupName.get(getLayoutPosition()));
            fragmentActivity.startActivity(intent);


        }
    }

    public ListOfGroupAdapter(Context context, ArrayList<Group> groupName, FragmentActivity fragmentActivity) {
        mInflater = LayoutInflater.from(context);
        this.groupName = groupName;
        this.fragmentActivity = fragmentActivity;
    }


    @NonNull
    @Override
    public ListOfGroupAdapter.GroupViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = mInflater.inflate(R.layout.group_item,viewGroup,false);
        return new GroupViewHolder(itemView,this);
    }

    @Override
    public void onBindViewHolder(GroupViewHolder groupViewHolder, int i) {
        String mCurrent = groupName.get(i).Description;
        Context context = groupViewHolder.iconView.getContext();

        String name = "a" + String.valueOf(groupName.get(i).ImageID);
        int id = context.getResources().getIdentifier(name, "drawable", context.getPackageName());

        groupViewHolder.iconView.setImageResource(id);
        groupViewHolder.groupNameView.setText(mCurrent);
    }

    @Override
    public int getItemCount() {
        return groupName.size();
    }


}
