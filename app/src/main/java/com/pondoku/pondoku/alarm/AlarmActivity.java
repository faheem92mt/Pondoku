package com.pondoku.pondoku.alarm;

import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.provider.AlarmClock;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.pondoku.pondoku.HomeYeahh;
import com.pondoku.pondoku.R;
import com.pondoku.pondoku.qibla.QiblaActivity;

import java.util.Calendar;

public class AlarmActivity extends AppCompatActivity {

    EditText timeHour;
    EditText timeMinute;
    Button setTime;
    Button setAlarm;
    TimePickerDialog timePickerDialog;
    Calendar calendar;
    int currentHour;
    int currentMinute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);
        timeHour = findViewById(R.id.etHour);
        timeMinute = findViewById(R.id.etMinute);
        setTime = findViewById(R.id.btnTime);
        setAlarm = findViewById(R.id.btnAlarm);

        setTime.setOnClickListener((v) -> {
            calendar = Calendar.getInstance();
            currentHour = calendar.get(Calendar.HOUR_OF_DAY);
            currentMinute = calendar.get(Calendar.MINUTE);

            timePickerDialog = new TimePickerDialog(AlarmActivity.this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {
                    timeHour.setText(String.format("%02d", hourOfDay));
                    timeMinute.setText(String.format("%02d", minutes));
                }
            }, currentHour, currentMinute, false);

            timePickerDialog.show();
        });

        setAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!timeHour.getText().toString().isEmpty() && !timeMinute.getText().toString().isEmpty()){


//-------------------------1st Alarm---------------------------------------
                    Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM);
                    intent.putExtra(AlarmClock.EXTRA_HOUR, Integer.parseInt(timeHour.getText().toString()));
                    intent.putExtra(AlarmClock.EXTRA_MINUTES, Integer.parseInt(timeMinute.getText().toString()));
                    intent.putExtra(AlarmClock.EXTRA_MESSAGE, "1st Alarm");
//--------------------------End----------------------------------------------

//-------------------------2nd Alarm---------------------------------------
                    Intent intent2 = new Intent(AlarmClock.ACTION_SET_ALARM);
                    //If it is 7pm, simply put 19
                    //intent2.putExtra(AlarmClock.EXTRA_HOUR, 19);
                    intent2.putExtra(AlarmClock.EXTRA_HOUR, Integer.parseInt(timeHour.getText().toString()));

                    //If it is 7:20pm, simply put 20
                    //intent2.putExtra(AlarmClock.EXTRA_MINUTES, 20);
                    intent2.putExtra(AlarmClock.EXTRA_MINUTES, Integer.parseInt(timeMinute.getText().toString())+5);

                    intent2.putExtra(AlarmClock.EXTRA_MESSAGE, "2nd alarm");
//--------------------------End----------------------------------------------

//-------------------------3rd Alarm---------------------------------------
                    Intent intent3 = new Intent(AlarmClock.ACTION_SET_ALARM);
                    intent3.putExtra(AlarmClock.EXTRA_HOUR, Integer.parseInt(timeHour.getText().toString()));
                    intent3.putExtra(AlarmClock.EXTRA_MINUTES, Integer.parseInt(timeMinute.getText().toString())+10);
                    intent3.putExtra(AlarmClock.EXTRA_MESSAGE, "3rd alarm");
//--------------------------End----------------------------------------------

                    if(intent.resolveActivity(getPackageManager()) != null) {
                        startActivity(intent);
                        finish();

                        final Handler handler2 = new Handler();
                        handler2.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                startActivity(intent2);
                                finish();
                            }
                        }, 300);

                        final Handler handler3 = new Handler();
                        handler3.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                startActivity(intent3);
                                finish();
                            }
                        }, 700);

                    }else{
                        Toast.makeText(AlarmActivity.this, "There is no app that support this action", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(AlarmActivity.this,"Please choose a time", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        startActivity(new Intent(AlarmActivity.this, HomeYeahh.class));

    }

}