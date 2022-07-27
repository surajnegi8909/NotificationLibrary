package com.pegasus.notificationmodule.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.core.app.RemoteInput
import com.pegasus.notificationmodule.util.MyBroadcastListener

class ActionReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        System.err.println(">>>>>>>>>> receive intent ${intent?.action}")
        val remoteInput: Bundle? = intent?.let { RemoteInput.getResultsFromIntent(it) }
        if (remoteInput != null) {
            val reply = remoteInput.getCharSequence("reply").toString()

//            myBroadcastListener.onBroadcastSuccess(reply)

            System.err.println(">>>>>>>>>>> $reply")
        }
    }

}