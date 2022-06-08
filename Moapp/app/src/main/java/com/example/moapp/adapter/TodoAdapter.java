package com.example.moapp.adapter;

import android.content.Context;
import android.transition.Fade;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moapp.R;
import com.example.moapp.model.Schedule;
import com.example.moapp.model.TodoList;

import org.w3c.dom.Text;

import java.util.List;

public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.ViewHolder> {
    private List<Schedule> scheduleList;
    private int selectedPosition = -1;

    public TodoAdapter(List<Schedule> tempScheduleList) {
        this.scheduleList = tempScheduleList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.activity_recycler_view_item, parent, false);
        TodoAdapter.ViewHolder viewHolder = new TodoAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TodoAdapter.ViewHolder holder, int position) {
        Schedule scheduleListTempList = scheduleList.get(position);
        holder.bind(scheduleListTempList);


    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView todoTime, todoWhat;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.checkImg);
            todoTime =  itemView.findViewById(R.id.todoTime);
            todoWhat = itemView.findViewById(R.id.todoWhat);
        }

        private void bind(Schedule scheduleList) {
            todoTime.setText(scheduleList.getStartTime()+"~~"+scheduleList.getEndTime());
            todoWhat.setText(scheduleList.getSummary());
        }
    }
}



