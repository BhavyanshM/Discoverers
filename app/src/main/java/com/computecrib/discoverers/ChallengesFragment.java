package com.computecrib.discoverers;


import android.app.Notification;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class ChallengesFragment extends Fragment {

    // Consts
    private static final String TAG = "ChallengesFragment";

    private RecyclerView recyclerView;
    private ArrayList<Challenge> challenges;
    private RecyclerView.Adapter adapter;

    // Access a Cloud Firestore instance from your Activity
    private FirebaseFirestore db = FirebaseFirestore.getInstance();


    public ChallengesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View challengesFragment =  inflater.inflate(R.layout.fragment_challenges, container, false);
        recyclerView = (RecyclerView) challengesFragment.findViewById(R.id.rv_challenges);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        challenges = new ArrayList<Challenge>();

//        for(int i = 0; i<10; i++){
//            Challenge item = new Challenge(
//                    "Challenge" + (i+1),
//                    "Image is a Image",
//                    i
//            );
//            challenges.add(item);
//
//        }

        adapter = new ChallengesRecyclerAdapter(challenges, getContext());
        recyclerView.setAdapter(adapter);

        db.collection("challenges").orderBy("timestamp", Query.Direction.ASCENDING).addSnapshotListener(new EventListener<QuerySnapshot>() {

            @Override
            public void onEvent(@Nullable QuerySnapshot snapshots,
                                @Nullable FirebaseFirestoreException e) {
                if (e != null) {
                    System.err.println("Listen failed: " + e);
                    return;
                }

                for (DocumentChange dc : snapshots.getDocumentChanges()) {
                    if (dc.getType() == DocumentChange.Type.ADDED) {
                        String title = dc.getDocument().getData().get("title").toString();
                        String desc = dc.getDocument().getData().get("desc").toString();
                        String hint = dc.getDocument().getData().get("hint").toString();
                       String loc_name = dc.getDocument().getData().get("loc_name").toString();
                        String loc_address = dc.getDocument().getData().get("loc_address").toString();
                        int score = Integer.parseInt(dc.getDocument().getData().get("score").toString());

                        Challenge challenge = new Challenge(title, desc,
                                hint, loc_name, loc_address, score);
                        challenges.add(0, challenge);
                        adapter.notifyItemInserted(      0);

                        //Notification.Builder nb = mNotificatioinUtils.getAndroidChannelNotification(title, body);
                        //mNotificatioinUtils.getManager().notify(101, nb.build());
                        //NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(MainActivity.this);
                        //notificationManagerCompat.notify(1, mBuilder.build());
                        //System.out.println("New city: " + dc.getDocument().getData());
                    }
                }
            }
        });


        return challengesFragment;

    }

}
