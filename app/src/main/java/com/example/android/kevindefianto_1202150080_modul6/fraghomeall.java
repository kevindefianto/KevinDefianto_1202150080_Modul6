package com.example.android.kevindefianto_1202150080_modul6;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class fraghomeall extends Fragment {
    RecyclerView rv;
    DatabaseReference dataref;
    ArrayList<databasePost> list;
    adapterPost ap;


    public fraghomeall() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_fraghomeall, container, false);
        rv = v.findViewById(R.id.rvhomeall);
        list = new ArrayList<>();
        dataref = FirebaseDatabase.getInstance().getReference().child("post");
        ap = new adapterPost(list, this.getContext());

        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this.getContext()));
        rv.setAdapter(ap);

        dataref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot data : dataSnapshot.getChildren()){
                    databasePost post = data.getValue(databasePost.class);
                    post.key = data.getKey();
                    list.add(post);
                    ap.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return v;
    }

}
