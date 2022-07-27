package com.pegasus.notificationmodule

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationManagerCompat
import com.pegasus.notificationlibrary.Notification
import com.pegasus.notificationmodule.receiver.ActionReceiver

class MainActivity : AppCompatActivity() {
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
                1,SecondActivity::class.java)
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
                3,SecondActivity::class.java)
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
                4,SecondActivity::class.java)
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

//        findViewById<Button>(R.id.button6).setOnClickListener {
//            Notification.groupNotification(this,
//                "1234",
//                R.drawable.noti_icon,
//                "welcome to the world",
//                "hello there... how are you?",
//                2,SecondActivity::class.java)
//        }
    }
}