package com.google.firebase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MKushagra on 4/11/2017.
 */
public abstract class FirebaseNotification
{
    FirebaseNotification firebaseNotification = null;
    private static Configuratation configuratation = null;

    public static Configuratation editConfiguration()
    {
        if(configuratation == null)
        {
            configuratation = new FirebaseConfiguration();
        }
        return configuratation;

    }


}
