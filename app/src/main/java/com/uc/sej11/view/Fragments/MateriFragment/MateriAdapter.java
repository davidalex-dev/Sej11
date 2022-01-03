package com.uc.sej11.view.Fragments.MateriFragment;

import android.content.Context;
import android.content.Intent;
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

import java.util.List;

public class MateriAdapter extends RecyclerView.Adapter<MateriAdapter.CardViewViewHolder>{
    private Context context;
    private List<Materi.Data> dataList;

    private static final String TAG = "MateriAdapter";

    public MateriAdapter(Context context){
        this.context = context;
    }

    public List<Materi.Data> getDataList(){
        return dataList;
    }

    public void setDataList(List<Materi.Data> dataList){
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public MateriAdapter.CardViewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cv_materi, parent, false);
        return new CardViewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MateriAdapter.CardViewViewHolder holder, int position) {
        final Materi.Data results = getDataList().get(position);
        holder.txt_name.setText(results.getJudul_sub_bab());
        holder.txt_level.setText("Level " + results.getId());
        Log.d(TAG, "Judul is: " + results.getJudul_sub_bab());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(context, "Hello there!", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(context, MateriReadActivity.class);
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
        TextView txt_name, txt_level;

        public CardViewViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.cv_layout_materi);
            txt_name = itemView.findViewById(R.id.textView_materi_name);
            txt_level = itemView.findViewById(R.id.textView_materi_level);
        }
    }
}
