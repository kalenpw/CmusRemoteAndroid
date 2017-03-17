package com.kalenpw.cmusremote;

/**
 * Created by kalenpw on 3/16/17.
 */

public class Commands {
    public final static String PLAYPAUSE = "cmus-remote -u";
    public final static String NEXT = "cmus-remote -n";
    public final static String PREVIOUS = "cmus-remote -r";

    /**
     * Changes the Cmus volume by given amount.
     * Note: horrible naming convention is so you can think of this method as a string that
     * takes a parameter
     * @param int volAmount - % amount to change volume by
     * @return String command to be executed
     */
    public static String CHANGE_CMUS_VOLUME_BY(int volAmount){
        String command = "cmus-remote -v";
        String commandSuffix = "";
        if(volAmount < 0){
            commandSuffix = " " + volAmount + "%";
        }
        else{
            commandSuffix = " +" + volAmount + "%";
        }
        command += commandSuffix;
        return command;
    }

    /**
     * Changes the System volume by given amount
     * Note: horrible naming convention is so you can think of this  method as a string that
     * takes a parameter so it matches format of other commands
     * Note: as of now user must be using pulse audio for this work. Will at some point make it
     * more portable but that is not high priority
     * @param int volAmount - % amount to change volume by. Pass in negative value to decreases volume
     * @return String command - command which will be executed to adjust volume
     */
    public static String CHANGE_SYSTEM_VOLUME_BY(int volAmount){
        String command = "amixer -D pulse sset Master";
        String commandSuffix = "";
        if(volAmount < 0){
            commandSuffix = " " + Math.abs(volAmount) + "%-";
        }
        else{
            commandSuffix = " " + volAmount + "%+";
        }
        command += commandSuffix;

        return command;
    }
}
