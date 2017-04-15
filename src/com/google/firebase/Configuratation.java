package com.google.firebase;


import org.json.JSONObject;

/**
 *
 */
public interface Configuratation
{

    JSONObject jsonObject = new JSONObject();

    /*
    * This parameter identifies a group of messages (e.g., with collapse_key: "Updates Available")
    * that can be collapsed, so that only the last message gets sent when delivery can be resumed.
    * This is intended to avoid sending too many of the same messages when the device comes back online or becomes active.
    *
    * Note that there is no guarantee of the order in which messages get sent.
    *
    * Note: A maximum of 4 different collapse keys is allowed at any given time.
    * This means a FCM connection server can simultaneously store 4 different send-to-sync messages per client app.
    * If you exceed this number, there is no guarantee which 4 collapse keys the FCM connection server will keep.
    ***/
    public void setCollapseKey(String collapseKey);

    /*
     * Sets the priority of the message. Valid values are "normal" and "high." On iOS,
     * these correspond to APNs priorities 5 and 10.
     *
     * By default, notification messages are sent with high priority, and data messages
     * are sent with normal priority. Normal priority optimizes the client app's battery
     * consumption and should be used unless immediate delivery is required. For messages
     * with normal priority, the app may receive the message with unspecified delay.
     *
     * When a message is sent with high priority, it is sent immediately, and the app
     * can wake a sleeping device and open a network connection to your server.
     */
    public void setPriority(String priority);


    /*
     * On iOS, use this field to represent content-available in the APNs payload.
     * When a notification or message is sent and this is set to true, an inactive
     * client app is awoken. On Android, data messages wake the app by default.
     * On Chrome, currently not supported.*?
     */
    public void setAvailableContent(boolean isContentAvailable);

    /**
     * Currently for iOS 10+ devices only. On iOS, use this field to represent mutable-content
     * in the APNS payload. When a notification is sent and this is set to true, the content of
     * the notification can be modified before it is displayed, using a Notification Service app extension.
     * This parameter will be ignored for Android and web.
     */
    public void setMutableContent(boolean isMutableContent);

    /**
     * This parameter specifies how long (in seconds) the message should be kept in FCM storage if the device is offline.
     * The maximum time to live supported is 4 weeks, and the default value is 4 weeks.
     */
    public void setTimeToLive(long timeToLive);

    /**
     * This parameter specifies the package name of the application where the registration tokens must match in order to receive the message.
     */
    public void setRestrictedPackage(String restrictedPackage);

    /**
     * This parameter, when set to true, allows developers to test a request without actually sending a message.
     * @param isDryRun
     */
    public void setDryRun(boolean isDryRun);

}