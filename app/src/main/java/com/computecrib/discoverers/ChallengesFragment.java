package com.computecrib.discoverers;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class ChallengesFragment extends Fragment {
    private RecyclerView recyclerView;
    private ArrayList<Challenge> challenges;
    private RecyclerView.Adapter adapter;

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

        for(int i = 0; i<10; i++){
            Challenge item = new Challenge(
                    "Challenge" + (i+1),
                    "Image is a Image",
                    i
            );
            challenges.add(item);

        }

        adapter = new ChallengesRecyclerAdapter(challenges, getContext());
        recyclerView.setAdapter(adapter);
        return challengesFragment;

    }

}
