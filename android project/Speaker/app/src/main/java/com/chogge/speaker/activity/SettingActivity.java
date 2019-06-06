package com.chogge.speaker.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;
import com.chogge.speaker.R;

import static android.content.pm.ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE;

public class SettingActivity extends AppCompatActivity implements OnClickListener {
    Button cancel;
    EditText duration;
    EditText frequency;
    EditText interval_factor;
    EditText rotate_fator;
    Button save;
    RadioButton square;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(1);
    //    setRequestedOrientation(SCREEN_ORIENTATION_SENSOR_LANDSCAPE);
        setContentView((int) R.layout.activity_setting);
        this.square = (RadioButton) findViewById(R.id.square);
        this.square.setChecked(true);
        this.save = (Button) findViewById(R.id.save);
        this.cancel = (Button) findViewById(R.id.cancel);
        this.frequency = (EditText) findViewById(R.id.frequency);
        this.interval_factor = (EditText) findViewById(R.id.interval_factor);
        this.duration = (EditText) findViewById(R.id.duration);
        this.rotate_fator = (EditText) findViewById(R.id.rotate_fator);
        SharedPreferences sp = getSharedPreferences("bstar", 0);
        int hz = sp.getInt("frequency", 30);
        int durationValue = sp.getInt("duration", 10);
        int interval_factorValue = sp.getInt("interval_factor", 5);
        int rotate_fatorValue = sp.getInt("rotate_fator", 5);
        this.frequency.setText(hz + "");
        this.duration.setText(durationValue + "");
        this.interval_factor.setText(interval_factorValue + "");
        this.rotate_fator.setText(rotate_fatorValue + "");
        this.save.setOnClickListener(this);
        this.cancel.setOnClickListener(this);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.cancel /*2131558532*/:
                finish();
                return;
            case R.id.save /*2131558533*/:
                Toast.makeText(this, "Save Success!", 0).show();
                int hz = Integer.valueOf(this.frequency.getText().toString()).intValue();
                int durationValue = Integer.valueOf(this.duration.getText().toString()).intValue();
                int interval_factorValue = Integer.valueOf(this.interval_factor.getText().toString()).intValue();
                getSharedPreferences("bstar", 0).edit().putInt("frequency", hz).putInt("duration", durationValue).putInt("interval_factor", interval_factorValue).putInt("rotate_fator", Integer.valueOf(this.rotate_fator.getText().toString()).intValue()).commit();
                finish();
                return;
            default:
                return;
        }
    }
}
