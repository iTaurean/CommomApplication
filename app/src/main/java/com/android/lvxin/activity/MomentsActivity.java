package com.android.lvxin.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.android.lvxin.R;
import com.android.lvxin.common.AndroidTools;
import com.android.lvxin.emoticon.EmoticonsKeyBoardPopWindow;
import com.android.lvxin.emoticon.utils.EmoticonsUtils;
import com.android.lvxin.emoticon.view.EmoticonsEditText;

public class MomentsActivity extends AppCompatActivity {

    private static final String TAG = "MomentsActivity";
    private EmoticonsKeyBoardPopWindow mKeyBoardPopWindow;
    private EmoticonsEditText mContentEditText;
    private LinearLayout mSwitchLayout;

    public static void start(Context context) {
        Intent intent = new Intent(context, MomentsActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moments);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        initData();
        initView();
    }

    private void initData(){
     //初始化数据
    }

    private void initView() {
        initRootView();
        initContentText();
        mSwitchLayout = (LinearLayout) findViewById(R.id.view_switch_bar);
        ImageView mSwitchBtn = (ImageView) findViewById(R.id.btn_switch);
        mSwitchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSwitchLayout.setVisibility(View.GONE);
                mKeyBoardPopWindow.showPopupWindow();
            }
        });
        initKeyBoardPopWindow();
    }

    private void initRootView() {
        final View rootView = findViewById(R.id.layout_moments);
        AndroidTools.setupUI(this, rootView);
        // 获取键盘显示隐藏的事件
        rootView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

            @Override
            public void onGlobalLayout() {
                int heightDiff = rootView.getRootView().getHeight() - rootView.getHeight();
                Log.i(TAG, "onGlobalLayout.heightDiff: " + heightDiff);
                // 高度变化超过100时视为键盘的显示，反正就是键盘的隐藏
                if ((100 < heightDiff)) {
                    mSwitchLayout.setVisibility(View.VISIBLE);
                } else {
                    mSwitchLayout.setVisibility(View.GONE);
                }
            }
        });
    }

    private void initKeyBoardPopWindow() {
        mKeyBoardPopWindow = new EmoticonsKeyBoardPopWindow(this);
        mKeyBoardPopWindow.setBuilder(EmoticonsUtils.getSimpleBuilder(this));
        mKeyBoardPopWindow.setEditText(mContentEditText);
        mKeyBoardPopWindow.setOnKeyBoardBarPopupListner(new EmoticonsKeyBoardPopWindow.KeyBoardBarPopupListener() {

            @Override
            public void onSwitchBtnClick() {
                mSwitchLayout.setVisibility(View.VISIBLE);
                mKeyBoardPopWindow.dismiss();
                EmoticonsUtils.openSoftKeyboard(mContentEditText);
            }
        });
    }

    private void initContentText() {
        mContentEditText = (EmoticonsEditText) findViewById(R.id.moments_text);
        mContentEditText.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View view, MotionEvent event) {
                if (view == mContentEditText) {
                    view.getParent().requestDisallowInterceptTouchEvent(true);
                    switch (event.getAction() & MotionEvent.ACTION_MASK) {
                        case MotionEvent.ACTION_UP:
                            view.getParent().requestDisallowInterceptTouchEvent(false);
                            mSwitchLayout.setVisibility(View.VISIBLE);
                            break;

                        default:
                            break;
                    }
                }
                return false;
            }
        });
    }
}
