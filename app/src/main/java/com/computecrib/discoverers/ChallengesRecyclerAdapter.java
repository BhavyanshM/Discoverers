package com.computecrib.discoverers;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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

        public Holder(View itemView){
            super(itemView);
            textViewChallengeTitle = (TextView) itemView.findViewById(R.id.tv_challenge_title);
            textViewChallengeDescription = (TextView) itemView.findViewById(R.id.tv_challenge_description);
            textViewChallengeReward = (TextView) itemView.findViewById(R.id.tv_challenge_score);

        }


        @Override
        public void onClick(View view) {
//            Intent intent = new Intent();
            Log.d("ChallengeAdapter","CLICKTHEBUTTON");
        }
    }

}
