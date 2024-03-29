package com.chogge.speaker.myapplication.facebook.rebound;

public class SynchronousLooper extends SpringLooper {
    public static double SIXTY_FPS = 16.6667d;
    private boolean mRunning;
    private double mTimeStep = SIXTY_FPS;

    public double getTimeStep() {
        return this.mTimeStep;
    }

    public void setTimeStep(double timeStep) {
        this.mTimeStep = timeStep;
    }

    public void start() {
        this.mRunning = true;
        while (!this.mSpringSystem.getIsIdle() && this.mRunning) {
            this.mSpringSystem.loop(this.mTimeStep);
        }
    }

    public void stop() {
        this.mRunning = false;
    }
}
