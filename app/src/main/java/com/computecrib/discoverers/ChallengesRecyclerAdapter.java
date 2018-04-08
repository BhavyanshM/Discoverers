package com.computecrib.discoverers;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
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
    private static final int CHALLENGE_SOLUTION_REQUEST = 101;


    public ChallengesRecyclerAdapter(List<Challenge> challenges, Context context){
        this.challenges = challenges;
        this.context = context;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
            .inflate(R.layout.challenge_item, parent, false);
        return new Holder(view);

    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        Challenge item = challenges.get(position);
        holder.itemView.setTag(position);
        holder.textViewChallengeTitle.setText(item.getTitle());
        holder.textViewChallengeDescription.setText(item.getDescription());
        holder.textViewChallengeReward.setText(Integer.toString(item.getReward()));
        holder.textViewChallengeHint.setText(item.getHint());
        holder.textViewChallengeName.setText(item.getName());
        holder.textViewChallengeAddress.setText(item.getAddress());
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });
//        Glide.with(context)
//                .load(item.getThumbPath())
//                .thumbnail(0.5f)
//                .into(holder.imageViewThumbnail);
    }

    @Override
    public int getItemCount() {
        return challenges.size();
    }

    public class Holder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView textViewChallengeTitle;
        private TextView textViewChallengeDescription;
        private TextView textViewChallengeReward;
        private TextView textViewChallengeHint;
        private TextView textViewChallengeName;
        private TextView textViewChallengeAddress;

        public Holder(View itemView){
            super(itemView);
            itemView.setOnClickListener(this);
            textViewChallengeTitle = (TextView) itemView.findViewById(R.id.tv_challenge_title);
            textViewChallengeDescription = (TextView) itemView.findViewById(R.id.tv_challenge_description);
            textViewChallengeHint = (TextView) itemView.findViewById(R.id.tv_challenge_hint);
            textViewChallengeName = (TextView) itemView.findViewById(R.id.tv_challenge_loc_name);
            textViewChallengeAddress = (TextView) itemView.findViewById(R.id.tv_challenge_loc_address);

            textViewChallengeReward = (TextView) itemView.findViewById(R.id.tv_challenge_score);

        }


        @Override
        public void onClick(View view) {
//            Intent intent = new Intent();
            String desc = textViewChallengeDescription.getText().toString();
            String title = textViewChallengeTitle.getText().toString();
            String hint = textViewChallengeHint.getText().toString();
            String name = textViewChallengeName.getText().toString();
            String address = textViewChallengeAddress.getText().toString();
            int score = Integer.parseInt(textViewChallengeReward.getText().toString());
            MainActivity.currentChallenge = new Challenge(title, desc, hint,
                    name, address, score);
            Intent intent = new Intent(view.getContext(), ChallengeDetailActivity.class);
            ((Activity)context).startActivityForResult(intent, CHALLENGE_SOLUTION_REQUEST);
        }
    }

}
