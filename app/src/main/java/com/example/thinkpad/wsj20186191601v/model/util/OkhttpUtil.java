package com.example.thinkpad.wsj20186191601v.model.util;

import android.os.Handler;
import android.os.Looper;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OkhttpUtil {
    private  static  OkhttpUtil mokhttpUtil;
    private  final OkHttpClient mokHttpClient;
    private final Handler mHandler;
    public  OkhttpUtil(){
        mHandler = new Handler(Looper.getMainLooper());
        mokHttpClient = new OkHttpClient.Builder()
                .writeTimeout(3000, TimeUnit.MILLISECONDS)
                .readTimeout(3000,TimeUnit.MILLISECONDS)
                .connectTimeout(3000,TimeUnit.MILLISECONDS)
                .build();
    }
    public static  OkhttpUtil getInstance(){
        if (mokhttpUtil==null){
            synchronized (OkhttpUtil.class){
                if(mokhttpUtil==null){
                    return new OkhttpUtil();
                }
            }
        }
      return mokhttpUtil;
    }
    public void doget(String url, final OkCallback okCallback){
        Request request = new Request.Builder()
                .get()
                .url(url)
                .build();
        Call call = mokHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                if(okCallback!=null){
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            okCallback.onFailure(e);
                        }
                    });
                }
            }
            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            if(response !=null&&response.isSuccessful()){
                                String  json = response.body().string();
                                if(okCallback !=null){
                                    okCallback.onResponse(json);
                                    return;
                                }
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        if(okCallback!=null){
                            okCallback.onFailure(new Exception("网络异常"));
                        }
                    }
                });
            }
        });
    }
    public void  dopost(String url, Map<String, String> map, final OkCallback okCallback)throws IOException {
        FormBody.Builder builder = new FormBody.Builder();
        for (String key : map.keySet()) {
            builder.add(key, map.get(key));
        }
        FormBody formBody = builder.build();
        Request request = new Request.Builder()
                .post(formBody)
                .url(url)
                .build();
        Call call = mokHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                if(okCallback!=null){
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            okCallback.onFailure(e);
                        }
                    });
                }
            }
            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            if(response !=null&&response.isSuccessful()){
                                String  json = response.body().string();
                                if(okCallback !=null){
                                    okCallback.onResponse(json);
                                    return;
                                }
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        if(okCallback!=null){
                            okCallback.onFailure(new Exception("网络异常"));
                        }
                    }
                });
            }
        });
    }
    public  interface  OkCallback{
        void onFailure(Exception e);
        void  onResponse(String json);
    }
}
