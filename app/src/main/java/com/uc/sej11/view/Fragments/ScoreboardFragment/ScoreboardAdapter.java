package com.uc.sej11.view.Fragments.ScoreboardFragment;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.uc.sej11.R;
import com.uc.sej11.model.Materi;
import com.uc.sej11.model.UserLevel;

import java.util.List;

public class ScoreboardAdapter extends RecyclerView.Adapter<ScoreboardAdapter.CardViewViewHolder> {
    private Context context;
    private List<UserLevel.AllLevel> dataList;
    private List<Materi.Data> materiList;

    private static final String TAG = "ScoreboardAdapter";

    public ScoreboardAdapter(Context context){
        this.context = context;
    }

    public List<UserLevel.AllLevel> getDataList(){
        return dataList;
    }

    public void setDataList(List<UserLevel.AllLevel> dataList){
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public CardViewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cv_scoreboard, parent, false);
        return new CardViewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewViewHolder holder, int position) {
        final UserLevel.AllLevel results = getDataList().get(position);

        String stringUserID = Integer.toString(getDataList().get(position).getUser_id());
        String stringlevelID = Integer.toString(getDataList().get(position).getSej11__level_id());
        String stringScore = Integer.toString(getDataList().get(position).getScore());

        holder.score.setText("Score: " + stringScore);
        holder.material.setText(stringlevelID);
        holder.username.setText(stringUserID);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class CardViewViewHolder extends RecyclerView.ViewHolder{
        TextView username, material, score;

        public CardViewViewHolder(@NonNull View itemView) {
            super(itemView);

            username = itemView.findViewById(R.id.textView_scoreboard_username);
            material = itemView.findViewById(R.id.textView_scoreboard_materi);
            score = itemView.findViewById(R.id.textView_scoreboard_score);
        }
    }

}
