package com.kalenpw.cmusremote;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

/**
 * Created by kalenpw on 3/15/17.
 */

public class SshManager{
    private String _Command;

    public SshManager(){

    }

    public void setCommand(String value){
        _Command = value;
    }

    public void executeCommand(){
        //handleCommand();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                handleCommand();
            }
        });
        thread.start();

    }

    private void handleCommand(){
        try {
            JSch jsch = new JSch();
            String userName = "kalenpw";
            String ip = "67.61.102.26";
            int port = 24;
            String password = "PASSWORD";

            Session session = jsch.getSession(userName, ip, port);
            session.setPassword(password);

            java.util.Properties config = new java.util.Properties();
            config.put("StrictHostKeyChecking", "no");
            session.setConfig(config);

            session.connect(3000);
            Channel channel = session.openChannel("exec");

            ((ChannelExec)channel).setCommand(_Command);
            channel.setInputStream(null);
            channel.connect(10000);

        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
