package org.blendin.blendin.mvp;

import org.greenrobot.eventbus.EventBus;

public class Presenter {

    protected EventBus bus;

    public Presenter(EventBus bus) {
        this.bus = bus;
    }

    public void onStart() {
        bus.register(this);
    }

    public void onStop() {
        if(bus.isRegistered(this)) {
            bus.unregister(this);
        }
    }
}
