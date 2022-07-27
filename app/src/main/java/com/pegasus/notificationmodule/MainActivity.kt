package com.pegasus.notificationmodule

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationManagerCompat
import androidx.core.app.RemoteInput
import com.pegasus.notificationlibrary.Notification
import com.pegasus.notificationmodule.receiver.ActionReceiver
import com.pegasus.notificationmodule.util.MyBroadcastListener

class MainActivity : AppCompatActivity(), MyBroadcastListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Notification.createNotificationChannel(this,
            "1234",
            "Hello there",
            "Welcome to First Notification",
            NotificationManagerCompat.IMPORTANCE_DEFAULT)

        findViewById<Button>(R.id.button2).setOnClickListener {
            Notification.bigTextNotification(this,
                "1234",
                R.drawable.noti_icon,
                "BigText",
                "welcome to the world",
                "this is used for generating large-format notifications that include a lot of text.",
                1, SecondActivity::class.java, SecondActivity::class.java,
                false,
                null,
                null,
                null)
        }
        findViewById<Button>(R.id.button).setOnClickListener {
            Notification.basicNotification(this,
                "1234",
                R.drawable.noti_icon,
                "welcome to the world",
                "1234",
                2,
                SecondActivity::class.java,
                SecondActivity::class.java,
                false,
                null,
                null,
                null)


        }
        findViewById<Button>(R.id.button3).setOnClickListener {
            Notification.bigPictureNotification(this@MainActivity,
                "1234",
                R.drawable.noti_icon,
                "welcome to the world",
                "1234",
                "BigPicture style is used to show large image.",
                3, SecondActivity::class.java, SecondActivity::class.java,
                false,
                null,
                null,
                null)
        }
        findViewById<Button>(R.id.button4).setOnClickListener {
            val multiline = ArrayList<String>()
            multiline.add("This is line1 ")
            multiline.add("This is line2 ")
            multiline.add("This is line3 ")
            multiline.add("This is line4 ")
            multiline.add("This is line5 ")
            multiline.add("This is line6 ")
            multiline.add("This is line7 ")

            Notification.multiLineNotification(this,
                "1234",
                R.drawable.noti_icon,
                "InboxStyle",
                "welcome to the world",
                "It is used for notification includes a list of ( up to 5 ) strings.",
                multiline,
                4, SecondActivity::class.java, SecondActivity::class.java,
                false,
                null,
                null,
                null)
        }

        findViewById<Button>(R.id.button5).setOnClickListener {
            Notification.actionNotification(this,
                "1234",
                R.drawable.noti_icon,
                "welcome to the world",
                "1234",
                "This is an example of BigTextStyle notification with action.",
                5,
                "Like",
                "Delete", ActionReceiver::class.java)
        }

        findViewById<Button>(R.id.button6).setOnClickListener {
            Notification.basicCustomNotification(this,
                "1234",
                R.drawable.noti_icon,
                "welcome to the world",
                "1234",
                2,
                ActionReceiver::class.java,
                SecondActivity::class.java,
                false,
                null,
                null,
                null)
        }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        processInlineReply(intent)
    }

    private fun processInlineReply(intent: Intent?) {
        val remoteInput: Bundle? = intent?.let { RemoteInput.getResultsFromIntent(it) }
        if (remoteInput != null) {
            val reply = remoteInput.getCharSequence(
                "reply").toString()

            System.err.println(">>>>>>>>>>> $reply")
        }
    }

    override fun onBroadcastSuccess(message: String) {
        Toast.makeText(this, "message is > $message", Toast.LENGTH_SHORT).show()
    }

    class ActionReceiver : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            System.err.println(">>>>>>>>>> receive intent ${intent?.action}")
            val remoteInput: Bundle? = intent?.let { RemoteInput.getResultsFromIntent(it) }
            if (remoteInput != null) {
                val reply = remoteInput.getCharSequence("reply").toString()

                Toast.makeText(context, "message is $reply", Toast.LENGTH_SHORT).show()
                System.err.println(">>>>>>>>>>>@@ $reply")
            }
        }
    }
}