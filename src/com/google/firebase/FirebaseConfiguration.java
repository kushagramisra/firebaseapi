package com.google.firebase;

/**
 * Created by MKushagra on 4/15/2017.
 */
public class FirebaseConfiguration implements Configuratation
{
    
    public void setCollapseKey(String collapseKey)
    {
        jsonConfigObj.put("collapse_key", collapseKey);
    }
    
    public void setPriority(String priority)
    {
        jsonConfigObj.put("priority", priority);
    }
    
    public void setAvailableContent(boolean isContentAvailable)
    {
        jsonConfigObj.put("content_available", isContentAvailable);
    }
    
    public void setMutableContent(boolean isMutableContent)
    {
        jsonConfigObj.put("mutable_content", isMutableContent);
    }
    
    public void setTimeToLive(long timeToLive)
    {
        jsonConfigObj.put("time_to_live", timeToLive);
    }
    
    public void setRestrictedPackage(String restrictedPackage)
    {
        jsonConfigObj.put("restricted_package_name", restrictedPackage);
    }
    
    public void setDryRun(boolean isDryRun)
    {
        jsonConfigObj.put("dry_run", isDryRun);
    }
    
    
}
