package com.pegasus.notificationmodule.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class ActionReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        System.err.println(">>>>>>>>>> receive intent ${intent?.action}")
    }
}