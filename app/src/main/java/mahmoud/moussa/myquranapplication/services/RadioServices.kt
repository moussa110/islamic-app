package mahmoud.moussa.myquranapplication.services

import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import android.os.Binder
import android.os.IBinder
import android.widget.RemoteViews
import androidx.core.app.NotificationCompat
import mahmoud.moussa.myquranapplication.MyApplication
import mahmoud.moussa.myquranapplication.R


class RadioServices:Service() {
    lateinit var notificationLayout:RemoteViews;
    var mediaPlayer=MediaPlayer();
    override fun onCreate() {
        super.onCreate()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
//        val urlToPlay: String? =intent?.getStringExtra("url");
//        if (urlToPlay!=null)
//            startRadioPlayer(urlToPlay);

        val action=intent?.getStringExtra("action");
        if (action!=null){
            if (action.equals(PLAY_ACTION))
                playPauseRadioPlayer();
            else if(action.equals(STOP_ACTION))
                stopRadioPlayer();
        }

        return START_NOT_STICKY;
    }

    private fun playPauseRadioPlayer() {
        if (mediaPlayer.isPlaying) {
            mediaPlayer.pause()
            notificationLayout.setImageViewResource(R.id.notification_play,R.drawable.ic_play)
        }
        else if (!mediaPlayer.isPlaying){
            mediaPlayer.start()
            notificationLayout.setImageViewResource(R.id.notification_play,R.drawable.ic_pause)
        }
    }

    fun startRadioPlayer(urlToPlay: String?,name:String?) {
        stopRadioPlayer()
        mediaPlayer= MediaPlayer();
        mediaPlayer.setDataSource(this, Uri.parse(urlToPlay))
        mediaPlayer.prepareAsync();
        mediaPlayer.setOnPreparedListener {
            mediaPlayer.start()
        }

         createNotificationForRadioPlayer(name)

    }

    val RADIO_NOTIFICATION_ID = 20;
    private fun createNotificationForRadioPlayer(name: String?) {
//        var builder = NotificationCompat.Builder(this, MyApplication.CHANNEL_ID)
//            .setSmallIcon(R.drawable.ic_action_radio)
//            .setContentTitle("islami radio")
//            .setContentText(name)
//            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        // Get the layouts to use in the custom notification
        notificationLayout = RemoteViews(packageName, R.layout.notification_radio_custom)
        notificationLayout.setTextViewText(R.id.notification_title,"islami app radio")
        notificationLayout.setTextViewText(R.id.notification_info,name)
        notificationLayout.setOnClickPendingIntent(R.id.notification_play,getPlayPendingIntent());
        notificationLayout.setOnClickPendingIntent(R.id.notification_stop,getStopPendingIntent());

        val customNotification = NotificationCompat.Builder(this, MyApplication.CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_action_radio)
            .setStyle(NotificationCompat.DecoratedCustomViewStyle())
            .setCustomContentView(notificationLayout)
            .build()

        startForeground(RADIO_NOTIFICATION_ID,customNotification);
    }

    val PLAY_ACTION="play";
    val STOP_ACTION="stop";
    fun getPlayPendingIntent():PendingIntent{
        val intent=Intent(this,RadioServices::class.java)
        intent.putExtra("action",PLAY_ACTION);
        val pendingIntent=PendingIntent.getService(this,0,intent,
            PendingIntent.FLAG_UPDATE_CURRENT);
        return pendingIntent;
    }
    fun getStopPendingIntent():PendingIntent{
        val intent=Intent(this,RadioServices::class.java)
        intent.putExtra("action",STOP_ACTION);
        val pendingIntent=PendingIntent.getService(this,1,intent,
            PendingIntent.FLAG_UPDATE_CURRENT);
        return pendingIntent;
    }

    fun stopRadioPlayer() {
        if (mediaPlayer.isPlaying) {
            mediaPlayer.stop();
            stopForeground(true);
        }
    }

    private val binder = RadioBinder();

    override fun onBind(intent: Intent?): IBinder? {
        return binder;
    }

    inner class RadioBinder : Binder() {
        // Return this instance of LocalService so clients can call public methods
        fun getService(): RadioServices {
           return this@RadioServices
    }
    }

}