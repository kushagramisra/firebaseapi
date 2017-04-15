package com.google.firebase;

import static com.google.firebase.FirebaseNotification.*;

public class Main {

    public static void main(String[] args) {
        FirebaseNotification firebaseNotification = new FirebaseNotificationSingleDevice();
        Configuratation firebaseConfiguration = editConfiguration();
        firebaseConfiguration.setDryRun(true);
    }
}
