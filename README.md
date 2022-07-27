# Notification module

Features:
Use this module for different types of Notifications.

To import this module, First download it.
Now open your project in which you want to use this module.

Goto File -> New -> Import Module
Select the source directory of the Module you want to import and click Finish.
Open Project Structure Dialog (You can open the PSD by selecting File > Project Structure) and from 
the left panel click on Dependencies.
Open the Dependencies tab.
Click on <All Module> now in right side you will see add dependencies tab.
Click the (+) icon from Dependencies section and click Module Dependency.
Add Module Dependency dialog will open, Now select app module and click Ok.
Then you will see another Add Module Dependency dialog and it will show you notiftcationlibrary module. 
Click on CheckBox then click ok.
Open your build.gradle file and check that the module is now listed under dependencies as shown below. 
implementation project(path: ':notiftcationlibrary')

After Successfully importing notiftcationlibrary module, Now let's implement it.


Now open AndroidManifest.xml file 
Paste this below activity tag.
You have to create a Receiver if you are using Action Notification. 

        <receiver
            android:name = "com.pegasus.notificationmodule.receiver.ActionReceiver"
            android:enabled = "true"
            android:exported = "true" >
        </receiver>

Open the Activity/fragment in which you want to implement Notification.

Inside onCreate function.

    Notification.createNotificationChannel(this,
            "1234",
            "firstNoti",
            "Welcome to First Notification",
            NotificationManagerCompat.IMPORTANCE_DEFAULT)

//here we are initializing the Notification channel and passing ChannelID, channelName,
channelDescription and importance.

[1 : Basic Notification]

     Notification.basicNotification(this,
                "1234",
                R.drawable.noti_icon,
                "welcome to the world",
                "1234",
                2,
                SecondActivity::class.java)

Here, we are passing context, channelId, smallIcon, title, descriptionText, notificationId and
activity (when we click on notification, which activity to open).

[2 : Big Text Notification]

    Notification.bigTextNotification(this,
                "1234",
                R.drawable.noti_icon,
                "BigText",
                "welcome to the world",
                "this is used for generating large-format notifications that include a lot of text.",
                1,SecondActivity::class.java)

Here, we are passing context, channelId, smallIcon,subtitle, title, descriptionText, notificationId and
activity (when we click on notification, which activity to open).
[(subtitle is optional)]


[3 : Big Picture Notification]

    Notification.bigPictureNotification(this,
                "1234",
                R.drawable.noti_icon,
                "Big picture",
                "hello big picture",
                "BigPicture style is used to show large image.",
                3,SecondActivity::class.java)

Here, we are passing context, channelId, smallIcon, title, subtitle, descriptionText, notificationId and
activity (when we click on notification, which activity to open).
[(subtitle is optional)]

[4 : Multi Line Notification]

      Notification.multiLineNotification(this,
                "1234",
                R.drawable.noti_icon,
                "InboxStyle",
                "welcome to the world",
                "It is used for notification includes a list of ( up to 5 ) strings.",
                multiline,
                4,SecondActivity::class.java)

Here, we are passing context, channelId, smallIcon, subtitle, title, descriptionText, arrayOfString,
notificationId and activity (when we click on notification, which activity to open).
[(subtitle is optional)]


[5 : Action Notification]

      Notification.actionNotification(this,
                "1234",
                R.drawable.noti_icon,
                "welcome to the world",
                "1234",
                "This is an example of BigTextStyle notification with action.",
                5,
                "Like",
                "Delete", ActionReceiver::class.java)

Here, we are passing context, channelId, smallIcon, title,subDescription, descriptionText,
notificationId, actionButtonNameFirst,actionButtonNameSecond, and Receiver 
(when we click on action button what will happen).