package com.chogge.speaker.myapplication.facebook.rebound;

public abstract class SpringLooper {
    protected BaseSpringSystem mSpringSystem;

    public abstract void start();

    public abstract void stop();

    public void setSpringSystem(BaseSpringSystem springSystem) {
        this.mSpringSystem = springSystem;
    }
}
