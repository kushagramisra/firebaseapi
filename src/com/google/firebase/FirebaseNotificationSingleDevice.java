package com.google.firebase;

import com.google.exceptions.IllegalDeviceId;

/**
 * Created by MKushagra on 4/11/2017.
 */
public class FirebaseNotificationSingleDevice extends FirebaseNotification
{
    String deviceId = "";
    FirebaseNotificationSingleDevice(String deviceId) throws IllegalDeviceId
    {
        if(deviceId == "")
            throw new IllegalDeviceId("Illegal device Id");
        this.deviceId = deviceId;
    }

    public void sendNotification() {

    }
}
