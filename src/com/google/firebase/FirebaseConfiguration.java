package com.google.firebase;

/**
 * Created by MKushagra on 4/15/2017.
 */
public class FirebaseConfiguration implements Configuratation
{

    public void setCollapseKey(String collapseKey) {
        jsonObject.put("collapse_key",collapseKey);
    }

    public void setPriority(String priority) {
        jsonObject.put("priority",priority);
    }

    public void setAvailableContent(boolean isContentAvailable) {
        jsonObject.put("content_available",isContentAvailable);
    }

    public void setMutableContent(boolean isMutableContent) {
        jsonObject.put("mutable_content",isMutableContent);
    }

    public void setTimeToLive(long timeToLive) {
        jsonObject.put("time_to_live",timeToLive);
    }

    public void setRestrictedPackage(String restrictedPackage) {
        jsonObject.put("restricted_package_name",restrictedPackage);
    }

    public void setDryRun(boolean isDryRun) {
        jsonObject.put("dry_run",isDryRun);
    }


}
