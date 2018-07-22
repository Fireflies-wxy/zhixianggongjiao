package com.bnrc.bnrcbus.network;

import com.bnrc.bnrcbus.module.bus.BusModel;
import com.bnrc.bnrcbus.module.bus.ErrorBusModel;
import com.bnrc.bnrcbus.module.user.RegisterInfo;
import com.bnrc.bnrcbus.module.version.VersionModel;
import com.bnrc.bnrcsdk.okhttp.CommonOkHttpClient;
import com.bnrc.bnrcsdk.okhttp.listener.DisposeDataHandler;
import com.bnrc.bnrcsdk.okhttp.listener.DisposeDataListener;
import com.bnrc.bnrcsdk.okhttp.request.CommonRequest;
import com.bnrc.bnrcsdk.okhttp.request.RequestParams;

import java.util.Date;

/**
 * Created by apple on 2018/5/31.
 */

public class RequestCenter {

    private static final String TAG = "RequestCenter";

    //根据参数发送所有post请求
    public static void dealPostRequest(String url, RequestParams params, DisposeDataListener listener, Class<?> clazz) {
        CommonOkHttpClient.post(CommonRequest.
                createPostRequest(url, params), new DisposeDataHandler(listener, clazz));
    }

    //处理get请求
    public static void dealGetRequest(String url, RequestParams params, DisposeDataListener listener, Class<?> clazz) {
        CommonOkHttpClient.get(CommonRequest.
                createGetRequest(url, params), new DisposeDataHandler(listener, clazz));
    }


    public static void requestVersionData(DisposeDataListener listener) {

        RequestParams params = new RequestParams();
        params.put("V", "2.3");
        params.put("appV", "1.4");
        params.put("Tm","1");

        RequestCenter.dealPostRequest(HttpConstants.VERSION_URL, params, listener, VersionModel.class);
    }

    public static void requestBusData(DisposeDataListener listener) {

        RequestParams params = new RequestParams();
        params.put("SID", "1000020761");
        params.put("LID", "1038700");
        params.put("T", Long.toString(new Date().getTime()));

        RequestCenter.dealPostRequest(HttpConstants.BUS_URL, params, listener, BusModel.class);
    }

    public static void requestErrorBusData(DisposeDataListener listener,String URL) {
        RequestCenter.dealGetRequest(URL, null, listener, ErrorBusModel.class);
    }

    public static void register(String uid,DisposeDataListener listener) {
        RequestParams params = new RequestParams();

        params.put("uid",uid);
        params.put("usertype","1"); //暂定为0，以后添加QQ、微博

        RequestCenter.dealPostRequest(HttpConstants.REGISTER_URL,params,listener, RegisterInfo.class);
    }

}
