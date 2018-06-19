package com.example.thinkpad.wsj20186191601v.model;

import com.example.thinkpad.wsj20186191601v.model.Bean.Regbean;
import com.example.thinkpad.wsj20186191601v.model.util.OkhttpUtil;
import com.google.gson.Gson;

public class Regmodel {
    public void  Login(String mobile, String password, final IRegmodel iRegmodel){
          String url="https://www.zhaoapi.cn/user/reg"+"?mobile="+mobile+"&password="+password;
        final OkhttpUtil instance = OkhttpUtil.getInstance();
        instance.doget(url, new OkhttpUtil.OkCallback() {
            @Override
            public void onFailure(Exception e) {

            }

            @Override
            public void onResponse(String json) {
                Gson gson = new Gson();
                Regbean regbean = gson.fromJson(json, Regbean.class);
                String code = regbean.getCode();
                String msg = regbean.getMsg();
                if("0".equalsIgnoreCase(code)){
                    if(iRegmodel!=null){
                         iRegmodel.onResponse(regbean);
                    }
                }else {
                    iRegmodel.onFailure(msg);
                }

            }
        });

    }
    public  interface  IRegmodel{
        void onFailure(String e);
        void  onResponse(Regbean regbean);
    }
}
