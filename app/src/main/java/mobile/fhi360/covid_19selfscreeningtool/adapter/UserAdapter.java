package mobile.fhi360.covid_19selfscreeningtool.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import mobile.fhi360.covid_19selfscreeningtool.R;
import mobile.fhi360.covid_19selfscreeningtool.activities.DashboardActivity;
import mobile.fhi360.covid_19selfscreeningtool.activities.DetailsActivity;
import mobile.fhi360.covid_19selfscreeningtool.activities.SupervisorActivity;
import mobile.fhi360.covid_19selfscreeningtool.model.Users;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {
//    List<Users> userList;
    Context context;
    List<Users> userList;

    public UserAdapter(List<Users> userList, Context context) {
        this.userList = userList;
        this.context = context;
    }


    @NonNull
    @Override
    public UserAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.userlist, parent, false);
        return new UserAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapter.ViewHolder holder, int position) {
        Users users = userList.get(position);
        holder.tvFirstName.setText(users.getFirstname());
        holder.tvLastName.setText(users.getLastname());
        holder.tvState.setText(users.getState());
        holder.tvDesignation.setText(users.getDesignation());
        holder.tvUserType.setText(users.getUserType());
        holder.tvStatus.setText(users.isEnabled());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, DetailsActivity.class);
                i.putExtra("tvFirstname", users.getFirstname());
                i.putExtra("tvLastName", users.getLastname());
                i.putExtra("tvState", users.getState());
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvFirstName;
        private TextView tvLastName;
        private TextView tvState;
        private TextView tvDesignation;
        private TextView tvUserType;
        private TextView tvStatus;
        CardView cardView;


        public ViewHolder(View itemView) {
            super(itemView);
//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Intent i = new Intent(v.getContext(), DetailsActivity.class);
//            v.getContext().startActivity(i);
//
//                }
//            });
            tvFirstName = itemView.findViewById(R.id.tvFirstname);
            tvLastName = itemView.findViewById(R.id.tvLastname);
            tvState = itemView.findViewById(R.id.tvState);
            tvDesignation = itemView.findViewById(R.id.tvDesignation);
            tvUserType = itemView.findViewById(R.id.tvUserType);
            tvStatus = itemView.findViewById(R.id.tvStatus);

            cardView = itemView.findViewById(R.id.cardView);

        }
    }
}
