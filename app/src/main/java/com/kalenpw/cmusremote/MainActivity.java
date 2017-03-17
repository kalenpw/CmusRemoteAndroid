/*
    kalenpw
    kalenpwilliams@gmail.com

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    For a copy of the GNU General Public License see
    http://www.gnu.org/licenses/
 */

package com.kalenpw.cmusremote;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //Buttons for easy access
    Button _BtnPlayPause;
    Button _BtnNext;
    Button _BtnPrev;
    Button _BtnCmusVolPlusFive;
    Button _BtnCmusVolPlusOne;
    Button _BtnCmusVolMinusFive;
    Button _BtnCmusVolMinusOne;
    Button _BtnSysVolMinusFive;
    Button _BtnSysVolMinusOne;
    Button _BtnSysVolPlusOne;
    Button _BtnSysVolPlusFive;
    Button _BtnSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Give utility classes context
        NotificationUtils.setContext(this);
        AppSettings.setContext(this);
        AppSettings.updateAppSettings();
        HostInfo.setContext(this);
        HostInfo.updateUserInfo();

        //Display notification if need
        NotificationUtils.handleDisplayNotifications();

        //required for SSH to work/Possibly because using debug mode?
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        HostInfo.updateUserInfo(this);
        setupButtons();
    }

    /**
     * Method called on button clicks
     * @param View view - current view
     */
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
            //Sys volume
            case R.id.btnSysVolMinusFive:
                sshManager.executeCommand(Commands.CHANGE_SYSTEM_VOLUME_BY(-5));
                break;
            case R.id.btnSysVolMinusOne:
                sshManager.executeCommand(Commands.CHANGE_SYSTEM_VOLUME_BY(-1));
                break;
            case R.id.btnSysVolPlusFive:
                sshManager.executeCommand(Commands.CHANGE_SYSTEM_VOLUME_BY(5));
                break;
            case R.id.btnSysVolPlusOne:
                sshManager.executeCommand(Commands.CHANGE_SYSTEM_VOLUME_BY(1));
                break;
            case R.id.btnSettings:
                launchSettings();
                break;
        }
    }

    /**
     * Launches the preferences fragment
     */
    private void launchSettings(){
        Intent intent = new Intent(this, PrefFragment.class);
        startActivity(intent);
    }

    /**
     * Assigns buttons to class level variables and sets up onclick listeners
     */
    private void setupButtons(){
        //Audio controls
        _BtnPlayPause = (Button) findViewById(R.id.btnPlayPause);
        _BtnPlayPause.setOnClickListener(this);
        _BtnNext = (Button) findViewById(R.id.btnNext);
        _BtnNext.setOnClickListener(this);
        _BtnPrev = (Button) findViewById(R.id.btnPrev);
        _BtnPrev.setOnClickListener(this);
        //Cmus volume
        _BtnCmusVolMinusFive = (Button) findViewById(R.id.btnCmusVolMinusFive);
        _BtnCmusVolMinusFive.setOnClickListener(this);
        _BtnCmusVolMinusOne = (Button) findViewById(R.id.btnCmusVolMinusOne);
        _BtnCmusVolMinusOne.setOnClickListener(this);
        _BtnCmusVolPlusFive = (Button) findViewById(R.id.btnCmusVolPlusFive);
        _BtnCmusVolPlusFive.setOnClickListener(this);
        _BtnCmusVolPlusOne = (Button) findViewById(R.id.btnCmusVolPlusOne);
        _BtnCmusVolPlusOne.setOnClickListener(this);
        //System volume
        _BtnSysVolMinusFive = (Button) findViewById(R.id.btnSysVolMinusFive);
        _BtnSysVolMinusFive.setOnClickListener(this);
        _BtnSysVolMinusOne = (Button) findViewById(R.id.btnSysVolMinusOne);
        _BtnSysVolMinusOne.setOnClickListener(this);
        _BtnSysVolPlusFive = (Button) findViewById(R.id.btnSysVolPlusFive);
        _BtnSysVolPlusFive.setOnClickListener(this);
        _BtnSysVolPlusOne = (Button) findViewById(R.id.btnSysVolPlusOne);
        _BtnSysVolPlusOne.setOnClickListener(this);
        //Settings
        _BtnSettings = (Button) findViewById(R.id.btnSettings);
        _BtnSettings.setOnClickListener(this);
    }
}
