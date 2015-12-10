package com.android.lvxin.bean;

/**
 * @ClassName: CommentInfo
 * @Description: 评论信息
 * @Author: lvxin
 * @Date: 12/1/15 10:55
 */
public class CommentInfo {
    private long id;
    private String avatarUrl;
    private String content;
    private long createdTime;
    private String replyTo;
    private String replayFrom;

    public String getReplyTo() {
        return replyTo;
    }

    public void setReplyTo(String replyTo) {
        this.replyTo = replyTo;
    }

    public String getReplayFrom() {
        return replayFrom;
    }

    public void setReplayFrom(String replayFrom) {
        this.replayFrom = replayFrom;
    }

    public long getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(long createdTime) {
        this.createdTime = createdTime;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
