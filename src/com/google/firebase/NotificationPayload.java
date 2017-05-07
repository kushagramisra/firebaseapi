package com.google.firebase;

import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by MKushagra on 4/15/2017.
 */
public abstract class NotificationPayload
{
    JSONObject payloadData = new JSONObject();
    
    /**
     * The notification's title.
     * @param title
     */
    public void addTitle(String title)
    {
        payloadData.put("title", title);
    }
    
    
    /**
     * The notification's body text.
     * @param body
     */
    public void addBody(String body)
    {
        payloadData.put("body", body);
    }
    
    /**
     *  The sound to play when the device receives the notification.
     *  Supports "default" or the filename of a sound resource bundled in the app. Sound files must reside in /res/raw/.
     *
     * @param sound
     */
    public void addSound(String sound)
    {
        payloadData.put("sound", sound);
    }
    
    /**
     *  The action associated with a user click on the notification.
     *  If specified, an activity with a matching intent filter is launched when a user clicks on the notification.
     *
     *  @param clickAction
     */
    public void addClickAction(String clickAction)
    {
        payloadData.put("click_action", clickAction);
    }
    
    /**
     * The key to the body string in the app's string resources to use to localize the body text to the user's current localization.
     *
     * @param bodyLocKey
     */
    public void addBodyLocKey(String bodyLocKey)
    {
        payloadData.put("body_loc_key", bodyLocKey);
    }
    
    /**
     * Variable string values to be used in place of the format specifiers in body_loc_key to use to localize the body text to the user's current localization.
     *
     * @param bodyLocArgs
     */
    public void addBodyLocArgs(String bodyLocArgs)
    {
        payloadData.put("body_loc_args", bodyLocArgs);
    }
    
    /**
     * The key to the title string in the app's string resources to use to localize the title text to the user's current localization.
     *
     * @param titleLocKey
     */
    public void addTitleLocKey(String titleLocKey)
    {
        payloadData.put("title_loc_key", titleLocKey);
    }
    
    /**
     * Variable string values to be used in place of the format specifiers in title_loc_key to use to localize the title text to the user's current localization.
     *
     * @param titleLocArgs
     */
    public void addTitleLocArgs(String titleLocArgs)
    {
        payloadData.put("title_loc_args", titleLocArgs);
    }
    
    /**
     * Add custom payload map for Firebase push notification.
     * @param customPayload
     */
    public void addCustomPayloadJson(JSONObject customPayload)
    {
        this.payloadData = mergeJSONObjects(customPayload,this.payloadData);
    }
    
    /**
     * Add custom payload map for Firebase push notification.
     * @param customPayloadMap
     */
    public void addCustomPayloadMap(Map<String,String> customPayloadMap)
    {
        for(String o : customPayloadMap.keySet())
        {
            this.payloadData.put(o,customPayloadMap.get(o));
        }
    }
    
    private JSONObject mergeJSONObjects(JSONObject json1, JSONObject json2) {
        JSONObject mergedJSON = new JSONObject();
        mergedJSON = new JSONObject(json1, JSONObject.getNames(json1));
        for (String crunchifyKey : JSONObject.getNames(json2))
        {
            mergedJSON.put(crunchifyKey, json2.get(crunchifyKey));
        }
        return mergedJSON;
    }
    
}
