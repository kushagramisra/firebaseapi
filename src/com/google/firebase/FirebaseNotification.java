package com.google.firebase;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.JSONObject;

import com.google.exceptions.IllegalDeviceIdException;
import com.google.exceptions.IllegalPayloadException;
import com.google.exceptions.NoDeviceAddedException;

/**
 * This is base class to send notification to any device.
 *
 * Created by MKushagra on 4/11/2017.
 */
public abstract class FirebaseNotification
{
    private static final String POST = "POST";
    private static final String AUTHORIZATION = "Authorization";
    private static final String CONTENT_TYPE = "Content-Type";
    private static final String APPLICATION_JSON = "application/json";
    private static final String KEY = "key=";
    private static final String TO = "to";
    private static final String REGISTRATION_IDS = "registration_ids";
    String apikey = "";
    List<String> deviceList = new ArrayList<String>();
    private Configuratation configuratation = null;
    private NotificationPayload payload = null;
    private JSONObject finalJsonObj = new JSONObject();
    
    /**
     * This method is used to get the firebase configuration object and edit them if required.
     * @return Firebase Configuration Details like collapse_key,priority etc..
     */
    public Configuratation editConfiguration()
    {
        if(configuratation == null)
        {
            configuratation = new FirebaseConfiguration();
        }
        return configuratation;
        
    }
    
    /**
     * The method is used to re-initialize firebase configuratation.
     */
    public void reinitConfiguration()
    {
        configuratation = new FirebaseConfiguration();
    }
    
    /**
     * The method is used to add payload Object to notification.
     * @param notificationPayload add notificaition payload like title,body,sound
     * @throws IllegalPayloadException theow IllegalPayload in case null.
     */
    public void addPayLoad(NotificationPayload notificationPayload) throws IllegalPayloadException
    {
        if(notificationPayload != null)
        {
            this.payload = notificationPayload;
        }
        else
        {
            throw new IllegalPayloadException("Payload cannot be empty");
        }
    }
    
    private void mergeJsonData()
    {
        JSONObject configuration = Configuratation.jsonConfigObj;
        Iterator<?> keys = configuration.keys();
        
        while(keys.hasNext())
        {
            String key = (String)keys.next();
            this.finalJsonObj.put(key, configuration.get(key));
        }
        
        JSONObject payloadData = payload.payloadData;
        Iterator<?> keys2 = payloadData.keys();
        
        while(keys2.hasNext())
        {
            String key2 = (String)keys2.next();
            this.finalJsonObj.put(key2, payloadData.get(key2));
        }
        
    }
    
    /**
     * The method is used to add device/Devices to send notification to.
     * @param deviceId
     * @throws IllegalDeviceIdException
     */
    public abstract void addDevice(Object deviceId) throws IllegalDeviceIdException;
    
    /**
     * This methdd is used to send the notification to Firebase.
     * @throws IOException
     * @throws NoDeviceAddedException
     */
    public void sendNotification() throws IOException, NoDeviceAddedException
    {
        makeSendDeviceData();
        mergeJsonData();
        String firebaseJsonString = this.finalJsonObj.toString();
        String encodedData = URLEncoder.encode(firebaseJsonString, "UTF-8");
        URL firebaseUrl = new URL("https://fcm.googleapis.com/fcm/send");
        HttpURLConnection conn = (HttpURLConnection)firebaseUrl.openConnection();
        conn.setDoOutput(true);
        conn.setRequestMethod(POST);
        conn.setRequestProperty(AUTHORIZATION, KEY + apikey);
        conn.setRequestProperty(CONTENT_TYPE, APPLICATION_JSON);
        OutputStream os = conn.getOutputStream();
        os.write(encodedData.getBytes());
        System.out.println("" + conn.getResponseCode() + "-->>" + conn.getResponseMessage());
        
    }
    
    private void makeSendDeviceData() throws NoDeviceAddedException
    {
        if(this.deviceList.size() == 1)
        {
            this.finalJsonObj.put(TO, String.valueOf(this.deviceList.get(0)));
        }
        else if(this.deviceList.size() > 1)
        {
            String finalDeviceArray = "";
            for(String deviceId : deviceList)
            {
                finalDeviceArray = "\"" + deviceId + "\"," + finalDeviceArray;
            }
            finalDeviceArray = finalDeviceArray.substring(0, finalDeviceArray.lastIndexOf(","));
            this.finalJsonObj.put(REGISTRATION_IDS, finalDeviceArray);
        }
        else
        {
            throw new NoDeviceAddedException("No Device Added To send Notification");
        }
        
    }
    
    
}
