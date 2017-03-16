package com.kalenpw.cmusremote;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //Buttons for easy access
    Button _BtnPlayPause;
    Button _BtnNext;
    Button _BtnPrev;
    Button _BtnCmusVolPlusFive;
    Button _BtnCmusVolPlusOne;
    Button _BtnCmusVolMinusFive;
    Button _BtnCmusVolMinusOne;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupButtons();
        SshManager sshManager = new SshManager();
        System.out.println("*********************************************");
        System.out.println(sshManager.getTrackInformation());

    }

    private void updateTrackInfo(){

    }

    @Override
    public void onClick(View view){
        SshManager sshManager = new SshManager();

        switch(view.getId()){
            case R.id.btnPlayPause:
                sshManager.executeCommand(Commands.PLAYPAUSE);
                break;
            case R.id.btnNext:
                sshManager.executeCommand(Commands.NEXT);
                break;
            case R.id.btnPrev:
                sshManager.executeCommand(Commands.PREVIOUS);
                break;
            //Volumes
            case R.id.btnCmusVolMinusFive:
                sshManager.executeCommand(Commands.CHANGE_CMUS_VOLUME_BY(-5));
                break;
            case R.id.btnCmusVolMinusOne:
                sshManager.executeCommand(Commands.CHANGE_CMUS_VOLUME_BY(-1));
                break;
            case R.id.btnCmusVolPlusFive:
                sshManager.executeCommand(Commands.CHANGE_CMUS_VOLUME_BY(5));
                break;
            case R.id.btnCmusVolPlusOne:
                sshManager.executeCommand(Commands.CHANGE_CMUS_VOLUME_BY(1));
                break;
        }

    }

    /**
     * Assigns buttons to class level variables and sets up onclick listeners
     */
    private void setupButtons(){
        _BtnPlayPause = (Button) findViewById(R.id.btnPlayPause);
        _BtnPlayPause.setOnClickListener(this);
        _BtnNext = (Button) findViewById(R.id.btnNext);
        _BtnNext.setOnClickListener(this);
        _BtnPrev = (Button) findViewById(R.id.btnPrev);
        _BtnPrev.setOnClickListener(this);
        _BtnCmusVolMinusFive = (Button) findViewById(R.id.btnCmusVolMinusFive);
        _BtnCmusVolMinusFive.setOnClickListener(this);
        _BtnCmusVolMinusOne = (Button) findViewById(R.id.btnCmusVolMinusOne);
        _BtnCmusVolMinusOne.setOnClickListener(this);
        _BtnCmusVolPlusFive = (Button) findViewById(R.id.btnCmusVolPlusFive);
        _BtnCmusVolPlusFive.setOnClickListener(this);
        _BtnCmusVolPlusOne = (Button) findViewById(R.id.btnCmusVolPlusOne);
        _BtnCmusVolPlusOne.setOnClickListener(this);
    }
}
