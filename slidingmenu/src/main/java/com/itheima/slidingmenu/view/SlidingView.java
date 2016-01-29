package com.itheima.slidingmenu.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Scroller;



public class SlidingView extends ViewGroup {

    private View mMenuChild;
    private View mHomeChild;
    private Scroller mScroller;
    /**
     * View往左滑动最大距离
     */
    private int mLeftMax;
    /**
     * View往右滑动最大距离
     */
    private int mRightMax;
    /**
     * View滑动距离
     */
    private int mLeft;

    /**
     * 菜单蓝是否打开,默认关闭
     */
    private boolean isOpened = false;
    private float mDownX;
    private float mDownY;

    public SlidingView(Context context) {
        super(context);
        initView();
    }

    public SlidingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public SlidingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {

    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mMenuChild = getChildAt(0);
        mHomeChild = getChildAt(1);
        mScroller = new Scroller(getContext());
    }

    /**
     * Measuer children
     *
     * @param
     * @param
     */
    @Override

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        LayoutParams params = mMenuChild.getLayoutParams();
        int menuWidthMeasureSpec = MeasureSpec.makeMeasureSpec(params.width, MeasureSpec.EXACTLY);
        mMenuChild.measure(menuWidthMeasureSpec, heightMeasureSpec);

        mHomeChild.measure(widthMeasureSpec, heightMeasureSpec);

        mLeftMax = -mMenuChild.getMeasuredWidth();
        mRightMax = 0;


    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        if (changed) {

            mMenuChild.layout(-mMenuChild.getMeasuredWidth(), 0, 0, b);

            //  mHomeChild.layout(0,0,r,b);
            mHomeChild.layout(0, 0, mHomeChild.getMeasuredWidth(), mHomeChild.getMeasuredHeight());

        }
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mDownX = ev.getX();
                mDownY = ev.getY();
                break;

            case MotionEvent.ACTION_MOVE:
                float moveX = ev.getX();
                float moveY = ev.getY();

                //判定为水平滑动的时候拦截事件,不向子控件传递
                if (Math.abs(moveX - mDownX) > Math.abs(moveY - mDownY)) {

                    return true;
                }
                break;
        }

        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:

                mDownX = event.getX();

                break;

            case MotionEvent.ACTION_MOVE:
                float moveX = event.getX();
                float diffX = moveX - mDownX;

                float newScrollX = getScrollX() - diffX;

                if (newScrollX < mLeftMax) {
                    newScrollX = mLeftMax ;
                } else if (newScrollX > mRightMax) {
                    newScrollX = mRightMax ;
                }

                scrollTo((int) newScrollX, 0);

                break;

            case MotionEvent.ACTION_UP:

                int middle = -mMenuChild.getWidth() / 2;
                if (getScrollX() < middle) {
                    open();
                } else {
                    close();
                }

                break;

        }
        return true;
    }

    /**
     * 打开侧边栏
     */
    private void open() {

        int startX = getScrollX();
        int endX = -mMenuChild.getWidth();
        int dx = endX - startX;

        mScroller.startScroll(getScrollX(), 0, -mMenuChild.getWidth() - getScrollX(), 0, 500);
        invalidate();
    }

    /**
     * 关闭侧边栏
     */
    private void close() {

        mScroller.startScroll(getScrollX(), 0, 0 - getScrollX(), 0, 500);
        invalidate();

    }

    @Override
    public void computeScroll() {
        super.computeScroll();

        //true的话表示滑动还未结束,界面View失效但还可见,不要不停的重绘
        if (mScroller.computeScrollOffset()) {

            scrollTo(mScroller.getCurrX(), 0);
            invalidate();
        }

    }

    //对外暴漏方法提供点击开关侧边栏
    public void toggle() {

        /*if(isOpened){
            //开着的,要关闭
            close();
            isOpened = false;
        }else{
            //关着的,要打开
            open();
            isOpened = true;
        }*/

        if (getScrollX() == 0) {
            open();
        } else {
            close();
        }


    }

}
