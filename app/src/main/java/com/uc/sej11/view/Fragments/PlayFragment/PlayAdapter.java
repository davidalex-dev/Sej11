package com.uc.sej11.view.Fragments.PlayFragment;

import android.content.Context;
import android.content.Intent;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.uc.sej11.R;
import com.uc.sej11.model.Materi;
import com.uc.sej11.view.Activities.MateriReadyActivity.MateriReadActivity;
import com.uc.sej11.view.Activities.PlayPilganActivity.PlayPilganActivity;

import java.util.List;

public class PlayAdapter extends RecyclerView.Adapter<PlayAdapter.CardViewViewHolder>{
    private Context context;
    private List<Materi.Data> dataList;
    LayoutInflater layoutInflater;

    private static final String TAG = "PlayAdapter";

    public PlayAdapter(Context context){
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    public List<Materi.Data> getDataList(){
        return dataList;
    }

    public void setDataList(List<Materi.Data> dataList){
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public PlayAdapter.CardViewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.cv_play, parent, false);
        return new CardViewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlayAdapter.CardViewViewHolder holder, int position) {
        final Materi.Data results = getDataList().get(position);
        holder.txt_name.setText(results.getJudul_sub_bab());
        Log.d(TAG, "Hello from PlayAdapter. Judul is: " + results.getJudul_sub_bab());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, PlayPilganActivity.class);
                i.putExtra("materi_id", ""+results.getId());
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class CardViewViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        TextView txt_name;

        public CardViewViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.cv_layout_play);
            txt_name = itemView.findViewById(R.id.textView_materi_name);

        }
    }

}
