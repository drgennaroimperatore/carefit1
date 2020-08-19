package com.example.game_.other01_app.EventSystem;

import java.util.ArrayList;
import java.util.List;

public class Watched {

    List<Watcher> mWatchers = new ArrayList<>();

    public void addWatcher(Watcher watcher)
    {
        mWatchers.add(watcher);
    }

    public void removeWatcher (Watcher watcher)
    {
        mWatchers.remove(watcher);
    }

    public void notifyWatchers(DatabaseEvents event)
    {
        for(Watcher w: mWatchers)
        {
            try {
                w.update(event);
            } catch (WatcherNotInitialised watcherNotInitialised) {
                watcherNotInitialised.printStackTrace();
            }
        }
    }

}
