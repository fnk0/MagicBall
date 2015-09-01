package com.gabilheri.magicball;

import android.animation.Animator;
import android.animation.FloatEvaluator;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.wearable.view.WatchViewStub;
import android.view.WindowManager;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends Activity implements ShakeSensorCallback, Animator.AnimatorListener {

    SensorManager mSensorManager;
    ShakeListener mListener;
    TextView answer;
    Random random = new Random();
    String[] answers;
    ImageView image;
    ObjectAnimator objectAnimator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        final WatchViewStub stub = (WatchViewStub) findViewById(R.id.watch_view_stub);
        stub.setOnLayoutInflatedListener(new WatchViewStub.OnLayoutInflatedListener() {
            @Override
            public void onLayoutInflated(WatchViewStub stub) {
                answer = (TextView) findViewById(R.id.answer);
                answers = getResources().getStringArray(R.array.magic_answers);
                image = (ImageView) findViewById(R.id.imageBall);
                startAnimator();
            }
        });
    }

    void startAnimator() {
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mListener = new ShakeListener(this, mSensorManager);
        objectAnimator = ObjectAnimator.ofFloat(image, "rotation", 360f);
        objectAnimator.setInterpolator(new LinearInterpolator());
        objectAnimator.setRepeatCount(1);
        objectAnimator.setRepeatMode(ObjectAnimator.RESTART);
        objectAnimator.addListener(this);
        objectAnimator.setEvaluator(new FloatEvaluator());
        objectAnimator.setDuration(1500);
    }

    @Override
    public void onAnimationStart(Animator animation) {

    }

    @Override
    public void onAnimationEnd(Animator animation) {
        answer.setText(answers[random.nextInt(answers.length)]);
    }

    @Override
    public void onAnimationCancel(Animator animation) {

    }

    @Override
    public void onAnimationRepeat(Animator animation) {

    }

    @Override
    public void executeShakeAction(int shakeAction) {
        if(shakeAction == ShakeListener.SHAKE_HORIZONTAL) {
            if(answer.getText().toString().equals("")) {
                objectAnimator.start();
            }
        } else if(shakeAction == ShakeListener.SHAKE_VERTICAL) {
            answer.setText("");
        }

    }
}
