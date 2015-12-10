package com.android.lvxin.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.lvxin.R;
import com.android.lvxin.bean.CommentInfo;
import com.android.lvxin.bean.MomentsInfo;
import com.android.lvxin.listener.ItemClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @ClassName: CommentAdapter
 * @Description: 评论列表适配
 * @Author: lvxin
 * @Date: 12/1/15 10:50
 */
public class CommentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int VIEW_TYPE_HEADER = 0;
    private static final int VIEW_TYPE_ITEM = 1;

    private Context mContext;
    private LayoutInflater mInflater;
    private MomentsInfo moments;
    private List<CommentInfo> comments;
    private ItemClickListener mItemClickListener;

    public CommentAdapter(Context context) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
    }

    public void setOnItemClickListener(ItemClickListener listener) {
        mItemClickListener = listener;
    }

    public void update(MomentsInfo moments, List<CommentInfo> comments) {
        if (null != moments) {
            this.moments = moments;
        }
        if (null == comments) {
            this.comments = new ArrayList<>(0);
        } else {
            this.comments = comments;
        }
        notifyDataSetChanged();
    }

    public void updateContent(MomentsInfo moments) {
        this.moments = moments;
        notifyDataSetChanged();
    }

    public void updateComments(List<CommentInfo> data) {
        if (null == data) {
            this.comments = new ArrayList<>(0);
        } else {
            this.comments = data;
        }
        notifyDataSetChanged();
    }

    public void updateComments(CommentInfo info) {
        if (null == comments) {
            comments = new ArrayList<>();
        }
        comments.add(info);
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (VIEW_TYPE_HEADER == viewType) {
            View view = mInflater.inflate(R.layout.layout_moments_detail, parent, false);
            return new HeaderViewHolder(view);

        } else {
            View view = mInflater.inflate(R.layout.layout_comment_item, parent, false);
            return new ItemViewHolder(view, mItemClickListener);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof HeaderViewHolder) {
            HeaderViewHolder viewHolder = (HeaderViewHolder) holder;
            viewHolder.contentTv.setText(moments.getContent());
        } else if (holder instanceof ItemViewHolder) {
            ItemViewHolder viewHolder = (ItemViewHolder) holder;
            CommentInfo comment = comments.get(position - 1);
            viewHolder.commentBy.setText(comment.getReplayFrom());
            viewHolder.commentContent.setText(comment.getContent());
        }
    }

    @Override
    public int getItemCount() {
        return comments.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        return (0 == position) ? VIEW_TYPE_HEADER : VIEW_TYPE_ITEM;
    }

    static class HeaderViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.content_tv)
        EditText contentTv;

        HeaderViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            contentTv.setKeyListener(null);
        }
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.comment_avatar)
        ImageView commentAvatar;
        @Bind(R.id.comment_by)
        TextView commentBy;
        @Bind(R.id.comment_content)
        TextView commentContent;

        ItemViewHolder(final View view, final ItemClickListener listener) {
            super(view);
            ButterKnife.bind(this, view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (null != listener) {
                        if (VIEW_TYPE_ITEM == getItemViewType()) {
                            listener.onItemClick(view, getLayoutPosition() - 1);
                        }
                    }
                }
            });
        }
    }
}
