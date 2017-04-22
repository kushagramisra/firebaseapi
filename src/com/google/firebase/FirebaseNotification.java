package com.google.firebase;

import com.google.exceptions.IllegalDeviceIdException;
import com.google.exceptions.IllegalPayloadException;
import com.google.exceptions.NoDeviceAddedException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by MKushagra on 4/11/2017.
 */
public abstract class FirebaseNotification
{
    String apikey = "";
    FirebaseNotification firebaseNotification = null;
    static Configuratation configuratation = null;
    NotificationPayload payload = null;
    JSONObject finalJsonObj = new JSONObject();
    List<String> deviceList = new ArrayList<String>();


    public static Configuratation editConfiguration()
    {
        if(configuratation == null)
        {
            configuratation = new FirebaseConfiguration();
        }
        return configuratation;

    }

    public static void clearConfiguration()
    {
        configuratation = new FirebaseConfiguration();
    }

    public void addPayLoad(NotificationPayload notificationPayload) throws IllegalPayloadException {
        if(notificationPayload != null)
        {
            this.payload = notificationPayload;
        }
        else
        {
            throw new IllegalPayloadException("Payload cannot be empty");
        }
    }

    public void mergeJsonData()
    {
        JSONObject configuration = Configuratation.jsonConfigObj;
        Iterator<?> keys = configuration.keys();

        while( keys.hasNext() ) {
            String key = (String)keys.next();
            this.finalJsonObj.put(key,configuration.get(key));
        }

    }

    public abstract void addDevice(Object deviceId) throws IllegalDeviceIdException;

    public void sendNotification() throws IOException, NoDeviceAddedException {
        makeSendDeviceData();
        mergeJsonData();
        String rawData = this.finalJsonObj.toString();

        String encodedData = URLEncoder.encode(rawData, "UTF-8");

        URL u = new URL("https://fcm.googleapis.com/fcm/send");
        HttpURLConnection conn = (HttpURLConnection) u.openConnection();
        conn.setDoOutput(true);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Authorization", "key=" + apikey);
        conn.setRequestProperty("Content-Type", "application/json");
        OutputStream os = conn.getOutputStream();
        os.write(encodedData.getBytes());
        System.out.println(""+ conn.getResponseCode() + "-->>"+conn.getResponseMessage());

    }

    private void makeSendDeviceData() throws NoDeviceAddedException {
        if(this.deviceList.size()==1)
        {
            this.finalJsonObj.put("to",String.valueOf(this.deviceList.get(0)));
        }
        else if (this.deviceList.size() > 1)
        {
            String finalDeviceArray = "";
            for(String deviceId : deviceList)
            {
                finalDeviceArray = "\"" + deviceId + "\"," + finalDeviceArray  ;
            }
            finalDeviceArray = finalDeviceArray.substring(0,finalDeviceArray.lastIndexOf(","));
            this.finalJsonObj.put("registration_ids",finalDeviceArray);
        }
        else
        {
            throw new NoDeviceAddedException("No Device Added To send Notification");
        }

    }



}
