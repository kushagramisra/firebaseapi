package com.google.firebase;

import com.google.exceptions.IllegalDeviceIdException;

import java.util.Arrays;
import java.util.List;

/**
 * Created by MKushagra on 4/11/2017.
 */
public class FirebaseNotificationMultiDevice extends FirebaseNotification
{

    FirebaseNotificationMultiDevice(String apiKey) throws IllegalDeviceIdException
    {
        this.apikey = apiKey;
    }

    public void addDevice(Object deviceIds) throws IllegalDeviceIdException {

        if(deviceIds instanceof List)
            deviceList.addAll((List<String>)deviceIds);
        else if(deviceIds instanceof String[]){
            deviceList.addAll(Arrays.<String>asList((String[]) deviceIds));
        } else {
            throw new IllegalDeviceIdException("For multiple device ids, Ids should be inserted in List");
        }
    }

}
