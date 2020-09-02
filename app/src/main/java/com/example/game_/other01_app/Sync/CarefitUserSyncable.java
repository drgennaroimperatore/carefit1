package com.example.game_.other01_app.Sync;

import java.util.HashMap;
import java.util.Map;

public class CarefitUserSyncable extends Syncable {

    private String mName;
    private String mUUID;
    public CarefitUserSyncable(String name, String uuid)
    {
        mName = name;
        mUUID = uuid;
    }
    @Override
    public String sync() {
        Map<String, Object> carefitUserData = new HashMap<>();
        carefitUserData.put("name", mName);
        carefitUserData.put("uuid",mUUID);

        return new SyncManager().sendPost("createUser.php",carefitUserData);
    }
}
