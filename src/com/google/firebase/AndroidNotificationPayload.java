package com.google.firebase;

/**
 * Created by MKushagra on 4/15/2017.
 */
public class AndroidNotificationPayload extends NotificationPayload
{

    /**
     * The notification's icon.
     * Sets the notification icon to myicon for drawable resource myicon. If you don't send this key in the request,
     * FCM displays the launcher icon specified in your app manifest. The notification's body text.
     * @param iconLoaction
     */
    public void addIcon(String iconLoaction)
    {
        payloadData.put("icon",iconLoaction);
    }

    /**
     * Identifier used to replace existing notifications in the notification drawer.
     * If not specified, each request creates a new notification.
     * If specified and a notification with the same tag is already being shown, the new notification replaces the existing one in the notification drawer.
     * @param tag
     */
    public void addtag(String tag)
    {
        payloadData.put("tag",tag);
    }

    /**
     * The notification's icon color, expressed in #rrggbb format.
     * @param color
     */
    public void addColor(String color)
    {
        payloadData.put("color",color);
    }

}
