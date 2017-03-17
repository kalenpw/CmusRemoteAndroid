package com.kalenpw.cmusremote;

import com.jcraft.jsch.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

/**
 * Created by kalenpw on 3/15/17.
 */

public class SshManager{
    private String _Command;

    //Constructors

    /**
     * No arg constructor, ensure either setCommand(String value) or executeCommand(String cmd)
     * are called before trying to access command.
     */
    public SshManager(){

    }

    /**
     * Creates an instance of SshManger with a command set
     * @param String newCommand - the command to be executed later
     */
    public SshManager(String newCommand){
        _Command = newCommand;
    }

    //Getters & Setters

    /**
     * Sets the command which SshManager uses to execute
     * @param String value - value to be set as the new command
     */
    public void setCommand(String value){
        _Command = value;
    }

    //Methods

    /**
     * Executes a given command on configured server
     * @param String cmdToExecute - the command to be executed
     */
    public void executeCommand(String cmdToExecute){
        _Command = cmdToExecute;
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                handleCommand();
            }
        });
        thread.start();
    }

    /**
     * Executes command stored in _Command ensure the SshManger(String newCommand) constructor
     * or setCommand(String newCommand) were called before trying to execute command
     */
    public void executeCommand(){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                handleCommand();
            }
        });
        thread.start();

    }

    /**
     * Will return a list of track information which can then be parsed for displaying to user
     * //TODO in progress currently not functional
     * @return String commandOutput - the output of whatever command was executed
     */
    public String getTrackInformation(){
        String cmusInfoCommand = "cmus-remote -Q";
        String commandOutput = "";

        try{
            JSch jsch = new JSch();

            Session session = jsch.getSession(HostInfo.USER_NAME, HostInfo.HOST, HostInfo.PORT);
            session.setPassword(HostInfo.PASSWORD);

            java.util.Properties config = new java.util.Properties();
            config.put("StrictHostKeyChecking", "no");
            session.setConfig(config);

            session.connect(3000);
            Channel channel = session.openChannel("shell");
            byte[] byteCommand = cmusInfoCommand.getBytes();
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteCommand);
            channel.setInputStream(byteArrayInputStream);
            InputStream inputStream = channel.getInputStream();
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            channel.setOutputStream(byteArrayOutputStream);
            commandOutput = byteArrayOutputStream.toString();

            channel.connect();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return commandOutput;
    }

    /**
     * Method that actually creates Jsch connection and executes command
     */
    private void handleCommand(){
        try {
            JSch jsch = new JSch();

            Session session = jsch.getSession(HostInfo.USER_NAME, HostInfo.HOST, HostInfo.PORT);
            session.setPassword(HostInfo.PASSWORD);

            java.util.Properties config = new java.util.Properties();
            config.put("StrictHostKeyChecking", "no");
            session.setConfig(config);

            session.connect(3000);
            Channel channel = session.openChannel("exec");

            ((ChannelExec)channel).setCommand(_Command);
            channel.setInputStream(null);
            channel.connect(3000);

            channel.disconnect();
            session.disconnect();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
