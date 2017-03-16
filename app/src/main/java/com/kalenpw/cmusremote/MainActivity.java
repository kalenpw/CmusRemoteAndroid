package com.kalenpw.cmusremote;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sshTest();
    }

    public void sshTest(){

        try {
            JSch jsch = new JSch();
            String userName = "kalenpw";
            String ip = "67.61.102.26";
            int port = 24;
            String password = "PASSWORD";
            String command = "cmus-remote -u";

            Session session = jsch.getSession(userName, ip, port);
            session.setPassword(password);

            java.util.Properties config = new java.util.Properties();
            config.put("StrictHostKeyChecking", "no");
            session.setConfig(config);

            session.connect(3000);
            Channel channel = session.openChannel("exec");

            ((ChannelExec)channel).setCommand(command);
            channel.setInputStream(null);
            channel.connect(10000);

//            channel.setInputStream(System.in);
//            channel.setOutputStream(System.out);
//
//            channel.connect(3000);
//
//            System.out.println("_____________________________________--");
//            System.out.println(session.getUserName());
        }
        catch(Exception e){
            e.printStackTrace();
        }




    }
}
