package com.gabilheri.magicball;

import android.animation.Animator;
import android.animation.FloatEvaluator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements ShakeSensorCallback, Animator.AnimatorListener {

    SensorManager mSensorManager;
    ShakeListener mListener;
    Random mRandom = new Random();
    String[] mAnswers;
    TextView mAnswerTextView;

    ImageView mImageView;

    ObjectAnimator mObjectAnimator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        mAnswerTextView = (TextView) findViewById(R.id.answer);
        mAnswers = getResources().getStringArray(R.array.magic_answers);
        mImageView = (ImageView) findViewById(R.id.imageBall);
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mListener = new ShakeListener(this, mSensorManager);

        mObjectAnimator = ObjectAnimator.ofFloat(mImageView, "rotation", 360f);
        mObjectAnimator.setInterpolator(new LinearInterpolator());
        mObjectAnimator.setRepeatCount(1);
        mObjectAnimator.setRepeatMode(ObjectAnimator.RESTART);
        mObjectAnimator.addListener(this);
        mObjectAnimator.setEvaluator(new FloatEvaluator());
        mObjectAnimator.setDuration(1500);
    }

    @Override
    public void onAnimationStart(Animator animation) {

    }

    @Override
    public void onAnimationEnd(Animator animation) {
        mAnswerTextView.setText(mAnswers[mRandom.nextInt(mAnswers.length)]);
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
            if(mAnswerTextView.getText().toString().equals("")) {
                mObjectAnimator.start();
            }
        } else if(shakeAction == ShakeListener.SHAKE_VERTICAL) {
            mAnswerTextView.setText("");
        }

    }
}
