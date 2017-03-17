//Code based off this stackoverflow answer by http://stackoverflow.com/users/1956632/tony-wickham
//http://stackoverflow.com/questions/11270898/how-to-execute-a-method-by-clicking-a-notification

package com.kalenpw.cmusremote;

import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.NotificationCompat;

public class NotificationUtils {
    private static final int NOTIFICATION_ID = 42;

    private static final String PREVIOUS = "previous";
    private static final String PLAY_PAUSE = "play_pause";
    private static final String NEXT = "next";

    private static Context context;


    /**
     * Displays configured notification to user
     * @param Context newContext - the context notification was called from
     */
    public static void displayNotification(Context newContext) {
        context = newContext;

        Intent playPauseIntent = createIntent(PLAY_PAUSE);
        PendingIntent playPausePendingIntent = createPendingIntent(playPauseIntent);

        Intent prevIntent = createIntent(PREVIOUS);
        PendingIntent pendingPrevIntent = createPendingIntent(prevIntent);

        Intent nextIntent = createIntent(NEXT);
        PendingIntent pendingNextIntent = createPendingIntent(nextIntent);

        NotificationCompat.Builder notificationBuilder = (android.support.v7.app.NotificationCompat.Builder)
                new NotificationCompat.Builder(context)
                        .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                        .setSmallIcon(R.drawable.ic_library_music_black_24dp)
                        .setContentTitle("Cmus Remote")
                        .setContentText("")
                        .setStyle(new NotificationCompat.MediaStyle()
                            .setShowActionsInCompactView(0,1,2))
                        .addAction(new NotificationCompat.Action(R.drawable.prev50, "", pendingPrevIntent))
                        .addAction(new NotificationCompat.Action(R.drawable.play50,
                                "", playPausePendingIntent))
                        .addAction(new NotificationCompat.Action(R.drawable.end50, "", pendingNextIntent));

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        notificationManager.notify(NOTIFICATION_ID, notificationBuilder.build());
    }

    /**
     * Creates an intent of given type
     * @param String type - type of Intent to create, handle command in onHandleIntent
     * @return Intent
     */
    private static Intent createIntent(String type){
        Intent intent = new Intent(context, NotificationActionService.class)
                .setAction(type);
        return intent;
    }

    /**
     * Creates a pendingIntent from given intent
     * @param Intent intent - intent to be used for creating PendingIntent
     * @return PendingIntent
     */
    private static PendingIntent createPendingIntent(Intent intent){
        PendingIntent pendingIntent = PendingIntent.getService(context, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
        return  pendingIntent;
    }

    /**
     * Sets the Context used throughout this class
     * @param Context value - Context to set
     */
    public static void setContext(Context value){
        context = value;
    }

    /**
     * Decides if notification should be displayed or not based off user preferences
     */
    public static void handleDisplayNotifications(){
        AppSettings.updateAppSettings(context);
        if(AppSettings.NOTIFICATION_TURNED_ON){
            NotificationUtils.displayNotification(context);
        }
        else{
            NotificationManager notificationManager = (NotificationManager) context.getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.cancelAll();
        }

    }

    public static class NotificationActionService extends IntentService {
        public NotificationActionService() {
            super(NotificationActionService.class.getSimpleName());
        }

        @Override
        protected void onHandleIntent(Intent intent) {
            String action = intent.getAction();
            SshManager sshManager = new SshManager();

            switch(action){
                case PLAY_PAUSE:
                    sshManager.executeCommand(Commands.PLAYPAUSE);
                    break;
                case PREVIOUS:
                    sshManager.executeCommand(Commands.PREVIOUS);
                    break;
                case NEXT:
                    sshManager.executeCommand(Commands.NEXT);
                    break;
            }

        }
    }
}