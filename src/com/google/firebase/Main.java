package com.google.firebase;

import com.google.exceptions.IllegalDeviceId;

import static com.google.firebase.FirebaseNotification.*;

public class Main {

    public static void main(String[] args) {
        try {
            FirebaseNotification firebaseNotification = new FirebaseNotificationSingleDevice("tempdeviceid");
            Configuratation firebaseConfiguration = editConfiguration();
            firebaseConfiguration.setDryRun(true);

        } catch (IllegalDeviceId illegalDeviceId) {
            illegalDeviceId.printStackTrace();
        }

    }
}
