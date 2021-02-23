package com.sneydr.roomr_tenant.SocketIO.Callbacks;

import com.github.nkzawa.emitter.Emitter;
import com.google.gson.JsonParser;
import com.sneydr.roomr_tenant.Network.JSONParser;
import com.sneydr.roomr_tenant.SocketIO.Observables.SocketIOObservable;
import com.sneydr.roomr_tenant.SocketIO.Observers.SocketIOObserver;

public abstract class SocketCallback implements SocketIOObservable, Emitter.Listener {

    protected SocketIOObserver observer;
    protected JSONParser jsonParser;

    public SocketCallback() {
        this.jsonParser = JSONParser.getInstance();
    }

    @Override
    public void registerObserver(SocketIOObserver observer) {
        this.observer = observer;
    }

    @Override
    public void clearObserver() {
        this.observer = null;
    }




}
