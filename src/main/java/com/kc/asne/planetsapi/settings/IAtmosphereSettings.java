package com.kc.asne.planetsapi.settings;


public interface IAtmosphereSettings {
    int getTemperature();
    int getDensity();
    int getPressure();
    int getHumidity();
    boolean getIsBreathable();
    float getGravityMultiplier();
}
