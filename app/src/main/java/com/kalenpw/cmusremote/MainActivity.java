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
    Button _BtnPause;
    Button _BtnNext;
    Button _BtnPrev;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        _BtnPause = (Button) findViewById(R.id.btnPause);
        _BtnPause.setOnClickListener(this);
        _BtnNext = (Button) findViewById(R.id.btnNext);
        _BtnNext.setOnClickListener(this);
        _BtnPrev = (Button) findViewById(R.id.btnPrev);
        _BtnPrev.setOnClickListener(this);


    }

    @Override
    public void onClick(View view){
        SshManager sshManager = new SshManager();
        String pauseCommand = "cmus-remote -u";
        String nextCommand = "cmus-remote -n";
        String prevCommand = "cmus-remote -r";

        switch(view.getId()){
            case R.id.btnPause:
                sshManager.setCommand(pauseCommand);
                break;
            case R.id.btnNext:
                sshManager.setCommand(nextCommand);
                break;
            case R.id.btnPrev:
                sshManager.setCommand(prevCommand);
                break;
        }
        sshManager.executeCommand();

    }



}
