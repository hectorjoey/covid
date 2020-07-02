package mobile.fhi360.covid_19selfscreeningtool.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import mobile.fhi360.covid_19selfscreeningtool.R;
import mobile.fhi360.covid_19selfscreeningtool.activities.DetailsActivity;
import mobile.fhi360.covid_19selfscreeningtool.model.UserHealthData;

public class UserHealthAdapter extends RecyclerView.Adapter<UserHealthAdapter.ViewHolder> {
    List<UserHealthData> userHealthDataList;
    Context context;

    public UserHealthAdapter(List<UserHealthData> userHealthDataList, Context context) {
        this.userHealthDataList = userHealthDataList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.health_list, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        UserHealthData userHealthData = userHealthDataList.get(position);
        holder.tvFullname.setText(userHealthData.getFullname());
        holder.tvDate.setText(userHealthData.getDate());
        holder.tvFeverSymptom.setText(userHealthData.getFeverSymptom());
        holder.tvCoughSymptom.setText(userHealthData.getCoughSymptom());
        holder.tvDifficultyInBreathingSymptom.setText(userHealthData.getDifficultyInBreathingSymptom());
        holder.tvSneezingSymptoms.setText(userHealthData.getSneezingSymptoms());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailsActivity.class);
                intent.putExtra("Fullname", userHealthData.getFullname());
                intent.putExtra("Date", userHealthData.getDate());
                intent.putExtra("Fever Symptom", userHealthData.getFeverSymptom());
                intent.putExtra("Cough Symptom", userHealthData.getCoughSymptom());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return userHealthDataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvFullname;
        private TextView tvDate;
        private TextView tvFeverSymptom;
        private TextView tvCoughSymptom;
        private TextView tvDifficultyInBreathingSymptom;
        private TextView tvSneezingSymptoms;
        private CardView cardView;

        public ViewHolder(View itemView) {
            super(itemView);
            tvFullname = itemView.findViewById(R.id.tvFullname);
            tvDate = itemView.findViewById(R.id.tvDate);
            tvFeverSymptom = itemView.findViewById(R.id.tvFever);
            tvCoughSymptom = itemView.findViewById(R.id.tvCoughSymptom);
            tvDifficultyInBreathingSymptom = itemView.findViewById(R.id.tvDifficultyInBreathingSymptom);
            tvSneezingSymptoms = itemView.findViewById(R.id.tvSneezingSymptoms);
            cardView = itemView.findViewById(R.id.cardView);
        }
    }
}