package com.chogge.speaker.myapplication.facebook.rebound;

public interface SpringSystemListener {
    void onAfterIntegrate(BaseSpringSystem baseSpringSystem);

    void onBeforeIntegrate(BaseSpringSystem baseSpringSystem);
}
