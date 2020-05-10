package com.example.instantnews;

import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

public class VerticalViewPager extends ViewPager {
    private OnItemClickListener mOnItemClickListener;

    public VerticalViewPager(@NonNull Context context) {
        super(context);
        init();
    }

    public VerticalViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public void init() {
        setPageTransformer(true,new ViewPagerTransform());
        setOverScrollMode(OVER_SCROLL_NEVER);//this give bounce effect when we scroll up or down

        final GestureDetector tapGestureDetector = new    GestureDetector(getContext(), new TapGestureListener());

        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                tapGestureDetector.onTouchEvent(event);
                return false;
            }
        });

    }
    private MotionEvent swappXY(MotionEvent event)
    {
        float x=getWidth();
        float y=getHeight();
        float newX=(event.getY()/y)*y;
        float newY=(event.getX()/x)*x;
        event.setLocation(newX,newY);
        return event;
    }
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean intercept=super.onInterceptTouchEvent(swappXY(ev));
        swappXY(ev);
        return intercept;

    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return super.onTouchEvent(swappXY(ev));
    }


    public class ViewPagerTransform implements ViewPager.PageTransformer{

        private static final float MIN_SCALE=0.65f;
        @Override
        public void transformPage(@NonNull View page, float position) {

            if (position<-1)
            {
                page.setAlpha(0);
            }else if (position<=0)
            {
                page.setAlpha(1);
                page.setTranslationX(page.getWidth() * -position);
                page.setTranslationY(page.getHeight()*position);
                page.setScaleX(1);
                page.setScaleY(1);

            }else if (position<=1)
            {
                page.setAlpha(1-position);
                page.setTranslationX(page.getWidth() * -position);
                page.setTranslationY(0);
                float scaleFactor=MIN_SCALE + (1-MIN_SCALE)*(1-Math.abs(position));
                page.setScaleX(scaleFactor);
                page.setScaleY(scaleFactor);
            }else if (position>1)
            {
                page.setAlpha(0);
            }
        }
    }
    //ViewPager doesn't have any inbuilt onClickListener ,so we have to
    //write code for our own method to perform click event
    // All methods below all to perform Click event on ViewPager
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    private class TapGestureListener extends GestureDetector.SimpleOnGestureListener {

        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            if(mOnItemClickListener != null) {
                mOnItemClickListener.onItemClick(getCurrentItem());
            }
            return true;
        }
    }
}
