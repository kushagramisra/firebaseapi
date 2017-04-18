package com.google.firebase;

import com.google.exceptions.IllegalDeviceId;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by MKushagra on 4/11/2017.
 */
public class FirebaseNotificationMultiDevice extends FirebaseNotification
{
    List<String> arrayList = new ArrayList<String>();

    FirebaseNotificationMultiDevice(String[] deviceIds) throws IllegalDeviceId
    {
        if(deviceIds.length == 0)
        {
            throw  new IllegalDeviceId("Device array cannot be empty");
        }
        Collections.addAll(arrayList,deviceIds);
    }

    FirebaseNotificationMultiDevice(List<String> deviceIds) throws IllegalDeviceId
    {
        if(deviceIds ==  null)
        {
            throw  new IllegalDeviceId("Device list cannot be null");
        }
        if(deviceIds.size() == 0)
        {
            throw  new IllegalDeviceId("Device list cannot be empty");
        }
        arrayList = deviceIds;
    }

    public void sendNotification()
    {

    }
}
