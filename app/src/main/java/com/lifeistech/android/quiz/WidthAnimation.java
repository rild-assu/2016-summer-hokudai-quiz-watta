package com.lifeistech.android.quiz;

import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;

public class WidthAnimation extends Animation {

    int targetWidth;
    int startWidth;

    View view;

    // The gesture threshold expressed in dip
    private static final float GESTURE_THRESHOLD_DIP = 16.0f;

    private int concertDipsToPixels(int dips, Context context) {
        // Convert the dips to pixels
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dips * scale);
    }

    public WidthAnimation(View view, int startWidth, int targetWidth) {
        this.view = view;
        this.targetWidth = concertDipsToPixels(targetWidth, view.getContext());
        this.startWidth = concertDipsToPixels(startWidth, view.getContext());
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        int newWidth = (int) (startWidth + (targetWidth - startWidth) * interpolatedTime);
        view.getLayoutParams().width = newWidth;
        view.requestLayout();
    }

    @Override
    public void initialize(int width, int height, int parentWidth,
                           int parentHeight) {
        super.initialize(width, height, ((View) view.getParent()).getWidth(), parentHeight);
    }

    @Override
    public boolean willChangeBounds() {
        return true;
    }
}