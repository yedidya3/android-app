package com.example.myapplication.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.jackandphantom.joystickview.JoyStickView;

import java.io.IOException;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {

    String aileron;
    String elevator;
    String rudder;
    String throttle;


    com.example.myapplication.viewmodel.viewmodel viewmodel;
    SeekBar sbRudder;
    SeekBar sbThrottle;
    public void connect(View view) {



        EditText etIP  = (EditText)findViewById((R.id.etIP));
        EditText etPort  = (EditText)findViewById((R.id.etPort));

        viewmodel.connect(etIP.getText().toString() ,etPort.getText().toString());

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewmodel = new com.example.myapplication.viewmodel.viewmodel();
        setContentView(R.layout.activity_main);


        JoystickView joy;
        joy = findViewById(R.id.joystickView);

       joy.setOnChange(new OnChangeImp(){
           @Override
           public void OnChange(double x, double y) {
               String a=String.valueOf(x);
               String e = String.valueOf(y);
               viewmodel.setAileron(a);
               viewmodel.setElevator(e);
           }



       });

        sbRudder = (SeekBar)findViewById(R.id.sbRudder);
        sbThrottle = (SeekBar)findViewById(R.id.sbThrottle);

        //com.example.myapplication.viewmodel.viewmodel viewmodel = (com.example.myapplication.viewmodel.viewmodel)findViewById((R.id.viewmodel));
        sbRudder.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                float progressFloat;
                if((float)progress >=1000000) {
                    progressFloat = (((float) progress)-1000000) / 1000000;
                }
                else{
                    progressFloat = -(1000000-(float) progress);
                    progressFloat = progressFloat/1000000;

                }

                rudder = String.valueOf(progressFloat);
                viewmodel.setRudder(rudder);


            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        sbThrottle.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                float progressFloat = (float)progress/1000000;
                throttle = String.valueOf(progressFloat);
                viewmodel.setThrottle(throttle);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

}