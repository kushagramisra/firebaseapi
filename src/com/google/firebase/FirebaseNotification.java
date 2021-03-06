package com.google.firebase;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.json.JSONObject;

import com.google.exceptions.IllegalDeviceIdException;
import com.google.exceptions.IllegalPayloadException;
import com.google.exceptions.NoDeviceAddedException;

/**
 * This is base class to send notification to any device.
 * <p>
 * Created by MKushagra on 4/11/2017.
 */
public abstract class FirebaseNotification {
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
    private Proxy proxy = null;
    private String responseData = "";

    /**
     * Method is used to get Response String
     * @return
     */
    public String getResponseData(){
        return this.responseData;
    }

    private void setResponseData(String responseData){
        this.responseData = responseData;
    }

    private Proxy getProxy() {
        return proxy;
    }

    private void setProxy(Proxy proxy) {
        this.proxy = proxy;
    }

    /**
     * This method is used to get the firebase configuration object and edit them if required.
     *
     * @return Firebase Configuration Details like collapse_key,priority etc..
     */
    public Configuratation editConfiguration() {
        if (configuratation == null) {
            configuratation = new FirebaseConfiguration();
        }
        return configuratation;
    }

    /**
     * The method is used to re-initialize firebase configuratation.
     */
    public void reinitConfiguration() {
        configuratation = new FirebaseConfiguration();
    }

    /**
     * The method is used to add payload Object to notification.
     *
     * @param notificationPayload add notificaition payload like title,body,sound
     * @throws IllegalPayloadException theow IllegalPayload in case null.
     */
    public void addPayLoad(NotificationPayload notificationPayload) throws IllegalPayloadException {
        if (notificationPayload != null) {
            this.payload = notificationPayload;
        } else {
            throw new IllegalPayloadException("Payload cannot be empty");
        }
    }

    private void mergeJsonData() {
        JSONObject configuration = Configuratation.jsonConfigObj;
        Iterator<?> keys = configuration.keys();

        while (keys.hasNext()) {
            String key = (String) keys.next();
            this.finalJsonObj.put(key, configuration.get(key));
        }

        JSONObject payloadData = payload.payloadData;
        Iterator<?> keys2 = payloadData.keys();

        while (keys2.hasNext()) {
            String key2 = (String) keys2.next();
            this.finalJsonObj.put(key2, payloadData.get(key2));
        }

    }

    /**
     * The method is used to add device/Devices to send notification to.
     *
     * @param deviceId
     * @throws IllegalDeviceIdException
     */
    public abstract void addDevice(Object deviceId) throws IllegalDeviceIdException;

    /**
     * This methdd is used to send the notification to Firebase.
     *
     * @throws IOException
     * @throws NoDeviceAddedException
     */
    public void sendNotification() throws IOException, NoDeviceAddedException {
        makeSendDeviceData();
        mergeJsonData();
        String firebaseJsonString = this.finalJsonObj.toString();
        String encodedData = URLEncoder.encode(firebaseJsonString, "UTF-8");
        HttpURLConnection conn = getHttpConnection();
        conn.setDoOutput(true);
        conn.setRequestMethod(POST);
        conn.setRequestProperty(AUTHORIZATION, KEY + apikey);
        conn.setRequestProperty(CONTENT_TYPE, APPLICATION_JSON);
        OutputStream os = conn.getOutputStream();
        os.write(firebaseJsonString.getBytes());
        os.flush();
        os.close();
        getResponse(conn);
    }

    private String getResponse(HttpURLConnection conn) throws IOException {
        String responseString = "";

        StringBuffer response = null;

        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String inputLine;
        response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        responseString = response.toString();
        setResponseData(responseString);
        return responseString;
    }

    private HttpURLConnection getHttpConnection() throws IOException {
        Proxy proxy = getProxy();
        HttpURLConnection conn = null;
        URL firebaseUrl = new URL("https://fcm.googleapis.com/fcm/send");
        if (proxy != null) {
            conn = (HttpURLConnection) firebaseUrl.openConnection(proxy);
        } else {
            conn = (HttpURLConnection) firebaseUrl.openConnection();
        }
        return conn;
    }

    /**
     * Add proxy settings to firebase notification in case server is using proxy.
     *
     * @param hostname
     * @param port
     */
    public void proxySettings(String hostname, String port) {
        proxySettings(hostname, Integer.valueOf(port));
    }

    /**
     * Add proxy settings to firebase notification in case server is using proxy.
     *
     * @param hostname
     * @param port
     */
    public void proxySettings(String hostname, Integer port) {
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(hostname, port));
        setProxy(proxy);
    }

    private void makeSendDeviceData() throws NoDeviceAddedException {
        if (this.deviceList.size() == 1) {
            this.finalJsonObj.put(TO, String.valueOf(this.deviceList.get(0)));
        } else if (this.deviceList.size() > 1) {
            String finalDeviceArray = "";
            for (String deviceId : deviceList) {
                finalDeviceArray = "\"" + deviceId + "\"," + finalDeviceArray;
            }
            finalDeviceArray = finalDeviceArray.substring(0, finalDeviceArray.lastIndexOf(","));
            this.finalJsonObj.put(REGISTRATION_IDS, finalDeviceArray);
        } else {
            throw new NoDeviceAddedException("No Device Added To send Notification");
        }

    }


}
