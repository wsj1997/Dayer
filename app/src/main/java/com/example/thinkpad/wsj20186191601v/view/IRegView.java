package com.example.thinkpad.wsj20186191601v.view;

import com.example.thinkpad.wsj20186191601v.model.Bean.Regbean;

public interface IRegView {
    void onFailure(String e);
    void  onResponse(Regbean regbean);
}
