import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.exceptions.IllegalApiKeyException;
import com.google.exceptions.IllegalDeviceIdException;
import com.google.exceptions.IllegalPayloadException;
import com.google.exceptions.NoDeviceAddedException;
import com.google.firebase.AndroidNotificationPayload;
import com.google.firebase.Configuratation;
import com.google.firebase.FirebaseNotification;
import com.google.firebase.FirebaseNotificationMultiDevice;
import com.google.firebase.FirebaseNotificationSingleDevice;
import com.google.firebase.NotificationPayload;

public class Main
{
    static FirebaseNotification firebaseNotification;
    
    public static void main(String[] args)
    {
        try
        {
            sendToSingleDevice();
            firebaseNotification.reinitConfiguration();
            sendToMultipleDevice();
            firebaseNotification.reinitConfiguration();
            sendToMultipleDeviceInList();
        }
        catch(IllegalDeviceIdException illegalDeviceId)
        {
            illegalDeviceId.printStackTrace();
        }
        catch(IllegalPayloadException illegalPayload)
        {
            illegalPayload.printStackTrace();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        catch(NoDeviceAddedException e)
        {
            e.printStackTrace();
        }
        catch(IllegalApiKeyException e)
        {
            e.printStackTrace();
        }
        
    }
    
    public static void sendToMultipleDevice() throws IllegalDeviceIdException, IllegalPayloadException, IOException, NoDeviceAddedException
    {
        firebaseNotification = new FirebaseNotificationMultiDevice("tempApiKey");
        Configuratation firebaseConf = firebaseNotification.editConfiguration();
        firebaseConf.setRestrictedPackage("test");
        firebaseConf.setAvailableContent(true);
        String[] temp = {"aaaa", "bbbb"};
        firebaseNotification.addDevice(temp);
        NotificationPayload notificationPayload = new AndroidNotificationPayload();
        notificationPayload.addBody("Temp body");
        notificationPayload.addTitle("Temp title");
        firebaseNotification.addPayLoad(notificationPayload);
        firebaseNotification.sendNotification();
    }
    
    public static void sendToMultipleDeviceInList() throws IllegalDeviceIdException, IllegalPayloadException, IOException, NoDeviceAddedException
    {
        firebaseNotification = new FirebaseNotificationMultiDevice("tempApiKey");
        Configuratation firebaseConf = firebaseNotification.editConfiguration();
        firebaseConf.setRestrictedPackage("test");
        firebaseConf.setAvailableContent(true);
        List<String> deviceList = new ArrayList<String>();
        deviceList.add("aaaa");
        deviceList.add("bbbb");
        deviceList.add("ccccc");
        deviceList.add("ddddd");
        firebaseNotification.addDevice(deviceList);
        NotificationPayload notificationPayload = new AndroidNotificationPayload();
        notificationPayload.addBody("Temp body");
        notificationPayload.addTitle("Temp title");
        firebaseNotification.addPayLoad(notificationPayload);
        firebaseNotification.sendNotification();
    }
    
    public static void sendToSingleDevice() throws IllegalDeviceIdException, IllegalPayloadException, IOException, IllegalApiKeyException, NoDeviceAddedException
    {
        firebaseNotification = new FirebaseNotificationSingleDevice("tempApiKey");
        Configuratation firebaseConfiguration = firebaseNotification.editConfiguration();
        firebaseConfiguration.setDryRun(true);
        firebaseNotification.addDevice("TempDevice");
        firebaseConfiguration.setRestrictedPackage("test.pkg");
        NotificationPayload notificationPayload = new AndroidNotificationPayload();
        notificationPayload.addTitle("Test Payload");
        notificationPayload.addBody("Test Body");
        firebaseNotification.addPayLoad(notificationPayload);
        firebaseNotification.sendNotification();
    }
}
