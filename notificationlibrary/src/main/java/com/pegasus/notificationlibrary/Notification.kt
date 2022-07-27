package com.pegasus.notificationlibrary

import android.app.Activity
import android.app.NotificationChannel
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.app.RemoteInput
import androidx.core.app.TaskStackBuilder
import androidx.navigation.NavDeepLinkBuilder

class Notification {
    companion object {
        private var mNotificationManagerCompat: NotificationManagerCompat? = null

        fun createNotificationChannel(
            context: Context,
            channelId: String,
            channelName: String,
            channelDescription: String,
            importance: Int,
        ) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val channel = NotificationChannel(channelId, channelName, importance).apply {
                    description = channelDescription
                }
                mNotificationManagerCompat = NotificationManagerCompat.from(context)
                mNotificationManagerCompat?.createNotificationChannel(channel)
            }
        }

        fun basicNotification(
            context: Context,
            channelId: String,
            smallIcon: Int,
            title: String,
            descriptionText: String,
            notificationId: Int,
            activityName: Class<*>?,
            fragmentActivity: Class<out Activity?>?,
            usesFragment: Boolean,
            setNavGraph: Int?,
            setDestination: Int?,
            setArgument: Bundle?,
        ) {

            var pendingIntent: PendingIntent?= null
            if(usesFragment){
                pendingIntent = setNavGraph?.let {
                    setDestination?.let { it1 ->
                        fragmentActivity?.let { it2 ->
                            NavDeepLinkBuilder(context)
                                .setComponentName(it2)
                                .setGraph(setNavGraph)
                                .setDestination(it1)
                                .setArguments(setArgument)
                                .createPendingIntent()
                        }
                    }
                }
            }
            else {
                val intentOne = Intent(context, activityName)
                intentOne.action = title
                //passing notificationId to receiver class through intent
                intentOne.putExtra("id", notificationId)
                intentOne.putExtra("title", "Basic Notification")
                pendingIntent = TaskStackBuilder.create(context).run {
                    // Add the intent, which inflates the back stack
                    addNextIntentWithParentStack(intentOne)
                    // Get the PendingIntent containing the entire back stack
                    getPendingIntent(0,
                        PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)
                }
            }

            val builder = NotificationCompat.Builder(context, channelId)
                .setSmallIcon(smallIcon)
                .setContentTitle(title)
                .setContentText(descriptionText)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
            with(NotificationManagerCompat.from(context)) {
                notify(notificationId, builder.build())
            }
        }

        fun basicCustomNotification(
            context: Context,
            channelId: String,
            smallIcon: Int,
            title: String,
            descriptionText: String,
            notificationId: Int,
            activityName: Class<*>?,
            fragmentActivity: Class<out Activity?>?,
            usesFragment: Boolean,
            setNavGraph: Int?,
            setDestination: Int?,
            setArgument: Bundle?,
        ) {

            var pendingIntent: PendingIntent?= null
            if(usesFragment){
                pendingIntent = setNavGraph?.let {
                    setDestination?.let { it1 ->
                        fragmentActivity?.let { it2 ->
                            NavDeepLinkBuilder(context)
                                .setComponentName(it2)
                                .setGraph(setNavGraph)
                                .setDestination(it1)
                                .setArguments(setArgument)
                                .createPendingIntent()
                        }
                    }
                }
            }
            else {
                val intentOne = Intent(context, activityName)
                intentOne.action = title
                //passing notificationId to receiver class through intent
                intentOne.putExtra("id", notificationId)
                intentOne.putExtra("title", "Basic Notification")
//                pendingIntent = TaskStackBuilder.create(context).run {
//                    // Add the intent, which inflates the back stack
//                    addNextIntentWithParentStack(intentOne)
//                    // Get the PendingIntent containing the entire back stack
//                    getPendingIntent(0,
//                        PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)
//                }

                  pendingIntent = PendingIntent.getBroadcast(context, 0, intentOne,
                PendingIntent.FLAG_UPDATE_CURRENT)
            }

            val replyLabel = "Enter your reply here"

            //Initialise RemoteInput
            val remoteInput: RemoteInput = RemoteInput.Builder("reply")
                .setLabel(replyLabel)
                .build()

            val action = NotificationCompat.Action.Builder(android.R.drawable.sym_action_chat,
                "REPLY", pendingIntent).addRemoteInput(remoteInput)
                .setAllowGeneratedReplies(true)
                .build()

            val builder = NotificationCompat.Builder(context, channelId)
                .setSmallIcon(smallIcon)
                .setContentTitle(title)
                .setContentText(descriptionText)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .addAction(action)
                .setOnlyAlertOnce(true)
                .setAutoCancel(true)
            with(NotificationManagerCompat.from(context)) {
                notify(notificationId, builder.build())
            }
        }

        fun bigTextNotification(
            context: Context,
            channelId: String,
            smallIcon: Int,
            subHeading: String,
            title: String,
            longDescriptionText: String,
            notificationId: Int,
            activityName: Class<*>?,
            fragmentActivity: Class<out Activity?>?,
            usesFragment: Boolean,
            setNavGraph: Int?,
            setDestination: Int?,
            setArgument: Bundle?,
        ) {
            var pendingIntent: PendingIntent?= null
            if(usesFragment){
                pendingIntent = setNavGraph?.let {
                    setDestination?.let { it1 ->
                        fragmentActivity?.let { it2 ->
                            NavDeepLinkBuilder(context)
                                .setComponentName(it2)
                                .setGraph(setNavGraph)
                                .setDestination(it1)
                                .setArguments(setArgument)
                                .createPendingIntent()
                        }
                    }
                }
            }
            else {
                val intentOne = Intent(context, activityName)
                intentOne.action = title
                //passing notificationId to receiver class through intent
                intentOne.putExtra("id", notificationId)
                intentOne.putExtra("title", "Basic Notification")
                pendingIntent = TaskStackBuilder.create(context).run {
                    // Add the intent, which inflates the back stack
                    addNextIntentWithParentStack(intentOne)
                    // Get the PendingIntent containing the entire back stack
                    getPendingIntent(0,
                        PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)
                }
            }
//            val pendingIntent = PendingIntent.getActivity(context, 0, intentOne,
//                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_ONE_SHOT)

            val bitmap = BitmapFactory.decodeResource(context.resources,
                smallIcon)

            val style = NotificationCompat.BigTextStyle()
                .bigText(longDescriptionText) //set different title in expanded mode.
                .setBigContentTitle(null) //needed if an app sends notification from multiple sources(accounts).
                .setSummaryText(subHeading)

            val builder = NotificationCompat.Builder(context, channelId)
                .setSmallIcon(smallIcon)
                .setContentTitle(title)
                .setContentText(longDescriptionText) //set Big text template
                .setStyle(style) //Set the large icon in the notification.
                .setLargeIcon(bitmap)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent)

            with(NotificationManagerCompat.from(context)) {
                notify(notificationId, builder.build())
            }
        }

        fun bigPictureNotification(
            context: Context,
            channelId: String,
            smallIcon: Int,
            title: String,
            descriptionText: String,
            summeryText: String,
            notificationId: Int,
            activityName: Class<*>?,
            fragmentActivity: Class<out Activity?>?,
            usesFragment: Boolean,
            setNavGraph: Int?,
            setDestination: Int?,
            setArgument: Bundle?,
        ) {

            var pendingIntent: PendingIntent?= null
            if(usesFragment){
                pendingIntent = setNavGraph?.let {
                    setDestination?.let { it1 ->
                        fragmentActivity?.let { it2 ->
                            NavDeepLinkBuilder(context)
                                .setComponentName(it2)
                                .setGraph(setNavGraph)
                                .setDestination(it1)
                                .setArguments(setArgument)
                                .createPendingIntent()
                        }
                    }
                }
            }
            else {
                val intentOne = Intent(context, activityName)
                intentOne.action = title
                //passing notificationId to receiver class through intent
                intentOne.putExtra("id", notificationId)
                intentOne.putExtra("title", "Basic Notification")


                pendingIntent= TaskStackBuilder.create(context).run {
                    // Add the intent, which inflates the back stack
                    addNextIntentWithParentStack(intentOne)
                    // Get the PendingIntent containing the entire back stack
                    getPendingIntent(0,
                        PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)
                }
            }

            val bitmap = BitmapFactory.decodeResource(context.resources,
                smallIcon)

            val style = NotificationCompat.BigPictureStyle() //set big picture
                .bigPicture(bitmap) //set the content text in expanded form.
                .setSummaryText(summeryText) //set different title in expanded mode.
                .setBigContentTitle(null) //set different large icon in expanded mode.
                .bigLargeIcon(null)

            val builder = NotificationCompat.Builder(context, channelId)
                .setSmallIcon(smallIcon)
                .setContentTitle(title)
                .setContentText(descriptionText) //Set the large icon in the notification.
                .setLargeIcon(bitmap) //set Big picture template
                .setStyle(style)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent)

            with(NotificationManagerCompat.from(context)) {
                notify(notificationId, builder.build())
            }
        }

        fun multiLineNotification(
            context: Context,
            channelId: String,
            smallIcon: Int,
            subHeading: String,
            title: String,
            descriptionText: String,
            multiline: ArrayList<String>,
            notificationId: Int,
            activityName: Class<*>?,
            fragmentActivity: Class<out Activity?>?,
            usesFragment: Boolean,
            setNavGraph: Int?,
            setDestination: Int?,
            setArgument: Bundle?,
        ) {

            var pendingIntent: PendingIntent?= null
            if(usesFragment){
                pendingIntent = setNavGraph?.let {
                    setDestination?.let { it1 ->
                        fragmentActivity?.let { it2 ->
                            NavDeepLinkBuilder(context)
                                .setComponentName(it2)
                                .setGraph(setNavGraph)
                                .setDestination(it1)
                                .setArguments(setArgument)
                                .createPendingIntent()
                        }
                    }
                }
            }
            else {
                val intentOne = Intent(context, activityName)
                intentOne.action = title
                //passing notificationId to receiver class through intent
                intentOne.putExtra("id", notificationId)
                intentOne.putExtra("title", "Basic Notification")
                pendingIntent = TaskStackBuilder.create(context).run {
                    // Add the intent, which inflates the back stack
                    addNextIntentWithParentStack(intentOne)
                    // Get the PendingIntent containing the entire back stack
                    getPendingIntent(0,
                        PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)
                }
            }

            val style = NotificationCompat.InboxStyle() //To add n lines, call it n times
                .setSummaryText(subHeading) //set different title in expanded mode.
                .setBigContentTitle(null)

            multiline.forEach {
                style.addLine(it)
            }

            val builder = NotificationCompat.Builder(context, channelId)
                .setSmallIcon(smallIcon)
                .setContentTitle(title)
                .setContentText(descriptionText) //set inbox style in notification
                .setStyle(style)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent)


            with(NotificationManagerCompat.from(context)) {
                notify(notificationId, builder.build())
            }
        }

        fun actionNotification(
            context: Context,
            channelId: String,
            smallIcon: Int,
            title: String,
            descriptionText: String,
            bigDescriptionText: String,
            notificationId: Int,
            actionOneName: String,
            actionTwoName: String,
            className: Class<*>,
        ) {
            //removes all previously shown notifications.
            mNotificationManagerCompat?.cancelAll()

            val intentOne = Intent(context, className)
            intentOne.action = actionOneName
            //passing notificationId to receiver class through intent
            intentOne.putExtra("id", notificationId)
            intentOne.putExtra("title", "Action Notification")

            val intentTwo = Intent(context, className)
            intentTwo.action = actionTwoName
            //passing notificationId to receiver class through intent
            intentTwo.putExtra("id", notificationId)
            intentTwo.putExtra("title", "Action Notification")

            val pendingIntentOne = PendingIntent.getBroadcast(context, 0, intentOne,
                PendingIntent.FLAG_UPDATE_CURRENT)

            val pendingIntentTwo = PendingIntent.getBroadcast(context, 0, intentTwo,
                PendingIntent.FLAG_UPDATE_CURRENT)

            //action fire on click of notification Like action button.
            val actionLike = NotificationCompat.Action.Builder(0,
                actionOneName, pendingIntentOne)
                .build()

            //action fire on click of notification Delete action button.
            val actionDelete = NotificationCompat.Action.Builder(0,
                actionTwoName, pendingIntentTwo)
                .build()

            val builder = NotificationCompat.Builder(context, channelId)
                .setSmallIcon(smallIcon)
                .setContentTitle(title)
                .setContentText(descriptionText)
                .setStyle(NotificationCompat.BigTextStyle()
                    .bigText(bigDescriptionText)) //Add actions Dismiss and Delete to this notification.
                .addAction(actionLike)
                .addAction(actionDelete)

            with(NotificationManagerCompat.from(context)) {
                notify(notificationId, builder.build())
            }
        }

    }
}
