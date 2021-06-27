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
    //Variables that are sent to viewmodel when they change
    String aileron;
    String elevator;
    String rudder;
    String throttle;

    //the viewmodel
    com.example.myapplication.viewmodel.viewmodel viewmodel;
    //seekbaars
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

        //the joystick
        JoystickView joy;
        joy = findViewById(R.id.joystickView);
        //enter object of onchange to joystick
        //the object on change Activates the function of what will happen when the joystick moves
        //This is where the strategy pattern actually comes into play
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

         sbRudder.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            //We want the seek bar to be in the middle in 0 mode so we are making such changes
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
         //What happens when the seekbar moves
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