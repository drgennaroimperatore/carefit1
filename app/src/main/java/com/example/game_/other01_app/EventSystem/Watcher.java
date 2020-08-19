package com.example.game_.other01_app.EventSystem;

public abstract class Watcher {
    protected Watched mWatched;
    public abstract void update(DatabaseEvents event) throws WatcherNotInitialised;
}
