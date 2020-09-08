package com.example.game_.other01_app.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.game_.other01_app.Activities.NewReminderActivity;
import com.example.game_.other01_app.Activities.RemindersListActivity;
import com.example.game_.other01_app.Database.entities.Reminder;
import com.example.game_.other01_app.R;
import com.example.game_.other01_app.ViewModels.ReminderViewModel;

import java.text.DecimalFormat;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ReminderListAdapter extends RecyclerView.Adapter<ReminderListAdapter.ReminderViewHolder>{

    private final ReminderViewModel reminderViewModel;

    class ReminderViewHolder extends RecyclerView.ViewHolder {

        private final TextView reminderDateTextView;
        private final TextView reminderTimeTextView;
        private final Button reminderEditBtn;
        private final Button reminderDeleteBtn;

        private ReminderViewHolder(View itemView) {
            super(itemView);
            reminderDateTextView = itemView.findViewById(R.id.reminder_list_item_date_textview);
            reminderTimeTextView = itemView.findViewById(R.id.reminder_list_item_time_textview);
            reminderEditBtn = itemView.findViewById(R.id.reminder_list_item_edit_btn);
            reminderDeleteBtn = itemView.findViewById(R.id.reminder_list_item_delete_btn);
        }
    }

    private final LayoutInflater mInflater;
    private List<Reminder> mReminders; //Cached copy of reminders
    private Context mContext;
    private DecimalFormat formatter;

    public ReminderListAdapter(Context context, ReminderViewModel mReminderViewModel) {
        mContext = context;
        reminderViewModel = mReminderViewModel;
        mInflater = LayoutInflater.from(context);
        formatter = new DecimalFormat("00");
    }

    @NonNull
    @Override
    public ReminderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.list_item_reminders, parent, false);
        return new ReminderViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ReminderViewHolder holder, int position) {
        if(mReminders != null) {
            Reminder current = mReminders.get(position);
          //  String reminderTimeString = formatter.format(current.getTimeHrs()) + ":" + formatter.format(current.getTimeMins());
        //    holder.reminderDateTextView.setText(current.getRepeating());
        //    holder.reminderTimeTextView.setText(reminderTimeString);
            holder.reminderEditBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(mContext, NewReminderActivity.class);
                    intent.putExtra("id", current.getId());
             //       intent.putExtra("daysRepeating", current.getRepeating());
//                    intent.putExtra("editing", true);
                    ((RemindersListActivity)mContext).startActivityForResult(intent,
                            NewReminderActivity.EDIT_REMINDER_ACTIVITY_REQUEST_CODE);
                }
            });
            holder.reminderDeleteBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mReminders.remove(position);
                    reminderViewModel.deleteReminder(current);
                    notifyItemRemoved(position);
                }
            });
        } else {
            //Covers the case of data not being ready yet.
            holder.reminderDateTextView.setText(R.string.no_reminders_set);
        }
    }

    public void setReminders(List<Reminder> reminders){
        mReminders = reminders;
        notifyDataSetChanged();
    }

    // getItemCount() is called many times, and when it is first called,
    // mReminders has not been updated (means initially, its null, and we can't return null).

    @Override
    public int getItemCount() {
        if(mReminders != null)
            return mReminders.size();
        return 0;
    }
}
