package com.google.firebase;

/**
 * Created by MKushagra on 4/15/2017.
 */
public class IOSNotificationPayload extends NotificationPayload
{
    
    /**
     * The value of the badge on the home screen app icon.
     * If not specified, the badge is not changed.
     * If set to 0, the badge is removed.
     * @param badge
     */
    public void addBadge(String badge)
    {
        payloadData.put("badge", badge);
    }
}
