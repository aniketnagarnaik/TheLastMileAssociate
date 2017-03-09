package com.example.nagarnai.thelastmileassociate.activity;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;

public class NotificationReceiver extends IntentService {

    public NotificationReceiver() {
        super("NotificationReceiver");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        if(intent.getAction().equals("address")) {
            return;
        } else if(intent.getAction().equals("mylocation")) {
            // tarun
        } else if(intent.getAction().equals("tomorrow")) {
            // ashruthi
        }
    }
}