package com.google.firebase;

/**
 * Created by MKushagra on 4/15/2017.
 */
public class IOSNotificationPayload extends NotificationPayload
{

    public void addBadge(String badge)
    {
        payloadData.put("badge",badge);
    }
}
