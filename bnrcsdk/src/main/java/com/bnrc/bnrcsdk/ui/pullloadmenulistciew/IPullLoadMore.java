package com.bnrc.bnrcsdk.ui.pullloadmenulistciew;

/**
 * Created by frank on 15/12/26.
 */
public interface IPullLoadMore {
    void startLoadMore();
    void stopLoadMore();
    void setPullLoadMoreListener(PullLoadMoreListener listener);
    interface PullLoadMoreListener {
        void onLoadMore();
    }
}
