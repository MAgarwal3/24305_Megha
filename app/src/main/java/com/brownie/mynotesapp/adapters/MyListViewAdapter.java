package com.brownie.mynotesapp.adapters;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.brownie.mynotesapp.R;
import com.brownie.mynotesapp.model.Reminder;
import com.brownie.mynotesapp.receivers.AlarmReceiver;

import java.util.ArrayList;
import java.util.Calendar;

public class MyListViewAdapter extends ArrayAdapter<Reminder> implements View.OnClickListener, TimePickerDialog.OnTimeSetListener {

    private int reminderListLayout;

    private int hour, minute;

    private Calendar calendar;

    private ViewHolder viewHolder;

    private Reminder reminder;

    private Context mContext;

    private final static int RQS_1 = 1;

    public MyListViewAdapter(@NonNull Context context, int resourceId, ArrayList<Reminder> reminderList) {
        super(context, resourceId, reminderList);
        mContext = context;
        reminderListLayout = resourceId;

        calendar = Calendar.getInstance();
        hour = calendar.get(Calendar.HOUR);
        minute = calendar.get(Calendar.MINUTE);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        reminder = getItem(position);

        if(convertView == null)
        {
            viewHolder = new ViewHolder();

            LayoutInflater inflater = LayoutInflater.from(mContext);

            convertView = inflater.inflate(reminderListLayout, parent, false);

            viewHolder.reminderDesc = convertView.findViewById(R.id.list_item_text);
            viewHolder.definedTime = convertView.findViewById(R.id.list_time_details);

            viewHolder.alarmImg = convertView.findViewById(R.id.img_add_alarm);
            viewHolder.alarmImg.setOnClickListener(this);

            convertView.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.reminderDesc.setText(reminder.getrDescription());
        viewHolder.definedTime.setText(reminder.getrTime());

        return convertView;
    }

    @Override
    public void onClick(View v) {

        if(v.getId() == R.id.img_add_alarm)
        {
            TimePickerDialog timePickerDialog = new TimePickerDialog(mContext, android.R.style.Theme_Material_Dialog_Alert, this, hour, minute, true);
            timePickerDialog.setTitle("Set a time");
            timePickerDialog.show();
        }
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        String time = hourOfDay + ":" + minute;

        reminder.setrTime(time);

        notifyDataSetChanged();

        Calendar calNow = Calendar.getInstance();
        Calendar calSet = (Calendar) calNow.clone();

        calSet.set(Calendar.HOUR_OF_DAY, hourOfDay);
        calSet.set(Calendar.MINUTE, minute);
        calSet.set(Calendar.SECOND, 0);
        calSet.set(Calendar.MILLISECOND, 0);

        if (calSet.compareTo(calNow) <= 0) {

            calSet.add(Calendar.DATE, 1);
        }

        setAlarm(calSet);

    }

    private void setAlarm(Calendar targetCal) {


        Toast.makeText(mContext, "Alarm is set at" + targetCal.getTime(),Toast.LENGTH_LONG).show();

        Intent intent = new Intent(mContext, AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(mContext, RQS_1, intent, 0);
        AlarmManager alarmManager = (AlarmManager) mContext.getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, targetCal.getTimeInMillis(),
                pendingIntent);

    }

    private static class ViewHolder
    {
        TextView reminderDesc;
        TextView definedTime;
        ImageView alarmImg;
    }

}
