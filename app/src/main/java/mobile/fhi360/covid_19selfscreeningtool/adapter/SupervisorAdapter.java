package mobile.fhi360.covid_19selfscreeningtool.adapter;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import mobile.fhi360.covid_19selfscreeningtool.R;
import mobile.fhi360.covid_19selfscreeningtool.model.Supervisor;

public class SupervisorAdapter extends RecyclerView.Adapter<SupervisorAdapter.ViewHolder> {
    List<Supervisor> supervisorList;
    Context context;

    public SupervisorAdapter(List<Supervisor> supervisorList, Context context) {
        this.supervisorList = supervisorList;
        this.context = context;
    }

    @NonNull
    @Override
    public SupervisorAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.health_list, parent, false);
        return new SupervisorAdapter.ViewHolder(v);
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onBindViewHolder(@NonNull SupervisorAdapter.ViewHolder holder, int position) {
        Supervisor supervisor = supervisorList.get(position);
        holder.tvFirstName.setText(supervisor.getName());
        holder.tvLastName.setText(supervisor.getEmail());

    }

    @Override
    public int getItemCount() {
        return supervisorList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvFirstName;
        private TextView tvLastName;
        private TextView tvSupervisor;


        public ViewHolder(View itemView) {
            super(itemView);
            tvFirstName = itemView.findViewById(R.id.supervisorFirstname);
            tvLastName = itemView.findViewById(R.id.tvSupervisorLastname);
            tvSupervisor = itemView.findViewById(R.id.tvSupervisorId);
        }
    }
}