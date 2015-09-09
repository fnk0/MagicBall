package com.gabilheri.magicball;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.annotation.IntDef;
import android.support.annotation.NonNull;

/**
 * Created by <a href="mailto:marcusandreog@gmail.com">Marcus Gabilheri</a>
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 8/31/15.
 */
public class ShakeListener implements SensorEventListener {

    @IntDef({NO_SHAKE, SHAKE_HORIZONTAL, SHAKE_VERTICAL})
    public @interface ShakeEvent {}
    public static final int NO_SHAKE = 0;
    public static final int SHAKE_HORIZONTAL = 1;
    public static final int SHAKE_VERTICAL = 2;

    private static final String LOG_TAG = "ShakeListener";
    private ShakeSensorCallback mShakeSensorCallback;

    public ShakeListener(@NonNull ShakeSensorCallback mShakeSensorCallback,
                         @NonNull SensorManager mSensorManager) {
        this.mShakeSensorCallback = mShakeSensorCallback;
        mSensorManager.registerListener(this,
                mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_NORMAL
        );
    }

    /* Here we store the current values of acceleration, one for each axis */
    private float xAccel;
    private float yAccel;
    private float zAccel;

    /* And here the previous ones */
    private float xPreviousAccel;
    private float yPreviousAccel;
    private float zPreviousAccel;

    /* Used to suppress the first shaking */
    private boolean firstUpdate = true;

    /*What acceleration difference would we assume as a rapid movement? */
    private final float yShakeThreshold = 1.5f;
    private final float zShakeThreshold = 5f;
    private final float xShakeThreshold = 7f;

    /* Has a shaking motion been started (one direction) */
    private boolean shakeInitiated = false;

    @Override
    public void onSensorChanged(SensorEvent event) {
        updateAccelParameters(event.values[0], event.values[1], event.values[2]);
        @ShakeEvent int shakeAction = getAccelerationChanged();
        if ((!shakeInitiated) && shakeAction > NO_SHAKE) {
            shakeInitiated = true;
        } else if ((shakeInitiated) && shakeAction > NO_SHAKE) {
            mShakeSensorCallback.executeShakeAction(shakeAction);
        } else if ((shakeInitiated)) {
            shakeInitiated = false;
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    /* Store the acceleration values given by the sensor */
    private void updateAccelParameters(float xNewAccel, float yNewAccel, float zNewAccel) {
        /* we have to suppress the first change of acceleration,
         it results from first values being initialized with 0 */
        if (firstUpdate) {
            xPreviousAccel = xNewAccel;
            yPreviousAccel = yNewAccel;
            zPreviousAccel = zNewAccel;
            firstUpdate = false;
        } else {
            xPreviousAccel = xAccel;
            yPreviousAccel = yAccel;
            zPreviousAccel = zAccel;
        }
        xAccel = xNewAccel;
        yAccel = yNewAccel;
        zAccel = zNewAccel;
    }

    /**
     *
     * If the values of acceleration have changed on at least two axises,
     * we are probably in a shake motion
     *
     * @return
     *      The ShakeEvent associated with this change
     */
    private @ShakeEvent int getAccelerationChanged() {
        float deltaX = Math.abs(xPreviousAccel - xAccel);
        float deltaY = Math.abs(yPreviousAccel - yAccel);
        float deltaZ = Math.abs(zPreviousAccel - zAccel);
//        Log.d(LOG_TAG, "DeltaX: " + deltaX + " -- DeltaY: " + deltaY + " -- DeltaZ: " + deltaZ);

        if ((deltaX > xShakeThreshold && deltaY > yShakeThreshold)
            || (deltaX > xShakeThreshold && deltaZ > zShakeThreshold)) {
            return SHAKE_HORIZONTAL;
        } else if (deltaY > yShakeThreshold && deltaZ > zShakeThreshold) {
            return SHAKE_VERTICAL;
        }
        return NO_SHAKE;
    }
}

