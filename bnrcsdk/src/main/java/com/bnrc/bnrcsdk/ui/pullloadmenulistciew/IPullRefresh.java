package com.bnrc.bnrcsdk.ui.pullloadmenulistciew;

/**
 * Created by frank on 15/12/26.
 */
public interface IPullRefresh {

    void startRefresh();
    void stopRefresh();
    void setPullRefreshListener(PullRefreshListener listener);

    interface PullRefreshListener {
        void onRefresh();
    }
}
