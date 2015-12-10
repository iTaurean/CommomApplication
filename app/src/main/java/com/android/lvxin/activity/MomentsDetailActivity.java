package com.android.lvxin.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;

import com.android.lvxin.R;
import com.android.lvxin.adapter.CommentAdapter;
import com.android.lvxin.bean.CommentInfo;
import com.android.lvxin.bean.MomentsInfo;
import com.android.lvxin.emoticon.EmoticonsKeyBoardBar;
import com.android.lvxin.emoticon.utils.EmoticonsUtils;
import com.android.lvxin.listener.ItemClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MomentsDetailActivity extends AppCompatActivity {

    @Bind(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @Bind(R.id.moment_detail_layout)
    EmoticonsKeyBoardBar mEmoticonsKeyBoard;
    CommentAdapter mAdapter;
    List<CommentInfo> comments;
    MomentsInfo momentsInfo;
    private boolean isHide = false;

    public static void start(Context context) {
        Intent intent = new Intent(context, MomentsDetailActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moments_detail);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initData();
        initView();
        loadDataFromServer();

    }

    private void initData() {

    }

    private void initView() {
        initEmoticonsKeyBoard();

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setHasFixedSize(true                 );
        mAdapter = new CommentAdapter(this);
        mAdapter.setOnItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                doComment(position);
            }
        });
        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                mEmoticonsKeyBoard.resetFaceBtn();
                mEmoticonsKeyBoard.hideAutoView();
            }
        });

    }

    private void doComment(int position) {
        mRecyclerView.requestFocusFromTouch();
//        mRecyclerView.scrollToPosition(position);
        mRecyclerView.smoothScrollToPosition(position);
        showDiscuss();
    }

    /**
     * <pre>
     * 显示回复评论框
     * </pre>
     */
    private void showDiscuss() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mEmoticonsKeyBoard.changeEditTextFocus(true);
            }
        }, 500);
        mEmoticonsKeyBoard.changeEditTextFocus(true);
        // 显示键盘
        EmoticonsUtils.openSoftKeyboard(mEmoticonsKeyBoard.getContentEdit());
    }

    private void initEmoticonsKeyBoard() {
        LinearLayout mSwitchLayout = (LinearLayout) findViewById(R.id.view_switch_bar);
        mSwitchLayout.setVisibility(View.GONE);
        mEmoticonsKeyBoard.setBuilder(EmoticonsUtils.getSimpleBuilder(this));
        mEmoticonsKeyBoard.setOnKeyBoardBarViewListener(new EmoticonsKeyBoardBar.KeyBoardBarViewListener() {
            @Override
            public void OnKeyBoardStateChange(int state, int height) {
                // step1:state = 103, height = 540>0 时，双键盘显示
                if (state == 103 && height > 0) {
                    isHide = true;
                }
                //  step2:双键盘显示时，点击返回键收起两个键盘
                if (isHide && state == 102 && height == 0) {
                    mEmoticonsKeyBoard.hideAutoView();
                    //isHide = false;
                }
                // 非上述两种前后组合时，均不做操作
                if (!(state == 103 && height > 0)) {
                    isHide = false;
                }
            }

            @Override
            public void OnSendBtnClick(String msg) {
                mEmoticonsKeyBoard.resetFaceBtn();
                // 隐藏键盘
                mEmoticonsKeyBoard.hideAutoView();
                doSend(msg);
            }
        });
    }

    private void doSend(String msg) {

    }

    private void loadDataFromServer() {
        comments = buildComments();
        momentsInfo = buildMomentsDetail();
        mAdapter.update(momentsInfo, comments);
    }

    private MomentsInfo buildMomentsDetail() {
        MomentsInfo info = new MomentsInfo();
        info.setId(1);
        info.setContent("这是一段文字文字文字这是一段文字文字文字这是一段文字文字文字这是一段文字文字文字");

        return info;
    }

    private List buildComments() {

        List list = new ArrayList(10);

        for (int i = 0; i < 10; i++) {
            CommentInfo info = new CommentInfo();
            info.setId(i);
            info.setReplayFrom("我是好人" + i);
            info.setContent("这是评论的内容内容内容内容内容内容内容" + i);
            list.add(info);
        }
        return list;
    }

}
