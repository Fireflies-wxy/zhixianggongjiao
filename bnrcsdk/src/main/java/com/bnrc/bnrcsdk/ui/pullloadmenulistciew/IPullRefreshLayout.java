package com.bnrc.bnrcsdk.ui.pullloadmenulistciew;

/**
 * Created by frank on 16/1/3.
 */
public interface IPullRefreshLayout {

    void onRefreshing();

    void onPullToRefresh();

    void onReleaseToRefresh();

    void onInit();

    int getRefreshingPosition();

    int getInitialPosition();
}
