package com.computecrib.discoverers;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by bhavy on 4/7/2018.
 */

public class ChallengesRecyclerAdapter extends RecyclerView.Adapter<ChallengesRecyclerAdapter.Holder>{
    private List<Challenge> challenges;
    private Context context;

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View view = layoutInflater.from(parent.getContentDescription())
//            .inflate(R.layout.challenge_item, parent, false);

    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class Holder extends RecyclerView.ViewHolder {
        private TextView textViewTitle;
        private TextView textViewDescription;
        private TextView textViewReward;

        public Holder(View itemView){
            super(itemView);
            textViewTitle = (TextView) itemView.findViewById(R.id.tv_challenge_title);
            textViewDescription = (TextView) itemView.findViewById(R.id.tv_challenge_description);
            textViewReward = (TextView) itemView.findViewById(R.id.tv_challenge_score);

        }
    }

}
