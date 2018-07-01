package com.bnrc.bnrcsdk.okhttp.response;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.bnrc.bnrcsdk.okhttp.RequestCenter;
import com.bnrc.bnrcsdk.okhttp.exception.OkHttpException;
import com.bnrc.bnrcsdk.okhttp.listener.DisposeDataHandler;
import com.bnrc.bnrcsdk.okhttp.listener.DisposeDataListener;
import com.bnrc.bnrcsdk.okhttp.listener.DisposeHandleCookieListener;
import com.bnrc.bnrcsdk.util.ResponseEntityToModule;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.Response;

/**
 * @author vision
 * @function 专门处理JSON的回调
 */
public class CommonJsonCallback implements Callback {

    private static final String TAG = "CommonJsonCallback";

    /**
     * the logic layer exception, may alter in different app
     */
    protected final String RESULT_CODE = "errorCode"; // 有返回则对于http请求来说是成功的，但还有可能是业务逻辑上的错误
    protected final int RESULT_CODE_VALUE = 0;
    protected final String ERROR_MSG = "emsg";
    protected final String EMPTY_MSG = "";
    protected final String COOKIE_STORE = "Set-Cookie"; // decide the server it
    // can has the value of
    // set-cookie2


    /**
     * the java layer exception, do not same to the logic error
     */
    protected final int NETWORK_ERROR = -1; // the network relative error
    protected final int JSON_ERROR = -2; // the JSON relative error,如果申请自己的服务器没有数据，需要申请第三方服务器
    protected final int OTHER_ERROR = -3; // the unknow error

    /**
     * 将其它线程的数据转发到UI线程
     */
    private Handler mDeliveryHandler;
    private DisposeDataListener mListener;
    private Class<?> mClass;

    public CommonJsonCallback(DisposeDataHandler handler) {
        this.mListener = handler.mListener;
        this.mClass = handler.mClass;
        this.mDeliveryHandler = new Handler(Looper.getMainLooper());
    }

    @Override
    public void onFailure(final Call call, final IOException ioexception) {

        Log.i(TAG,"Network connection fails.");

        /**
         * 此时还在非UI线程，因此要转发
         */
        mDeliveryHandler.post(new Runnable() {
            @Override
            public void run() {
                mListener.onFailure(new OkHttpException(NETWORK_ERROR, ioexception));
            }
        });
    }

    @Override
    public void onResponse(final Call call, final Response response) throws IOException {
        final String result = response.body().string();
        Log.i(TAG,result);
        final ArrayList<String> cookieLists = handleCookie(response.headers());

        mDeliveryHandler.post(new Runnable() {
            @Override
            public void run() {
                handleResponse(result);
                /**
                 * handle the cookie
                 */
                if (mListener instanceof DisposeHandleCookieListener) {
                    ((DisposeHandleCookieListener) mListener).onCookie(cookieLists);
                }
            }
        });
    }

    //处理cookie
    private ArrayList<String> handleCookie(Headers headers) {
        ArrayList<String> tempList = new ArrayList<String>();
        for (int i = 0; i < headers.size(); i++) {
            if (headers.name(i).equalsIgnoreCase(COOKIE_STORE)) {
                tempList.add(headers.value(i));
            }
        }
        return tempList;
    }

    private void handleResponse(Object responseObj) {
        if (responseObj == null || responseObj.toString().trim().equals("")) {
            mListener.onFailure(new OkHttpException(NETWORK_ERROR, EMPTY_MSG)); //如果返回json为空则报错
            return;
        }

        //json不为空往下进行

        try {
            JSONObject result = new JSONObject(responseObj.toString());
            if(result.has(RESULT_CODE)){
                mListener.onSuccess(result); //如果返回码有errorcode，直接处理JSONObject
            }else{
                if (mClass == null) {
                    mListener.onSuccess(result); //不需要将json转换为实体对象
                } else { //需要转化json
                    Object obj = ResponseEntityToModule.parseJsonObjectToModule(result, mClass);
                    if (obj != null) {
                        mListener.onSuccess(obj);
                    } else {
                        mListener.onFailure(new OkHttpException(JSON_ERROR, EMPTY_MSG));
                    }
                }
            }

        } catch (Exception e) {
            mListener.onFailure(new OkHttpException(OTHER_ERROR, e.getMessage()));
            e.printStackTrace();
        }
    }
}