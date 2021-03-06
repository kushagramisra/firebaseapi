package com.google.firebase;

import com.google.exceptions.IllegalApiKeyException;
import com.google.exceptions.IllegalDeviceIdException;

/**
 * The Class is used to send notification to single device.
 *
 * Created by MKushagra on 4/11/2017.
 */
public class FirebaseNotificationSingleDevice extends FirebaseNotification
{
    public FirebaseNotificationSingleDevice(String apiKey) throws IllegalApiKeyException
    {
        if(apiKey == "")
            throw new IllegalApiKeyException("Illegal device Id");
        this.apikey = apiKey;
    }
    
    public void addDevice(Object deviceId) throws IllegalDeviceIdException
    {
        if(deviceId instanceof String)
            this.deviceList.add(String.valueOf(deviceId));
        else
            throw new IllegalDeviceIdException("For single device device id should be string");
        
    }
    
}
