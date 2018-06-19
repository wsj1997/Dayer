package com.example.thinkpad.wsj20186191601v.presccse;

import com.example.thinkpad.wsj20186191601v.model.Bean.Regbean;
import com.example.thinkpad.wsj20186191601v.model.Regmodel;
import com.example.thinkpad.wsj20186191601v.view.IRegView;

public class Regpresccse {
    IRegView miRegView;
    private final Regmodel regmodel;

    public  Regpresccse(IRegView iRegView){
       miRegView=iRegView;
        regmodel = new Regmodel();
    }
    public  void reg(String username,String password){
        regmodel.Login(username, password, new Regmodel.IRegmodel() {
            @Override
            public void onFailure(String e) {
                     if (miRegView!=null){
                         miRegView.onFailure(e);
                     }
            }

            @Override
            public void onResponse(Regbean regbean) {
                if (miRegView!=null){
                    miRegView.onResponse(regbean);
                }
            }
        });
    }
  //  public  void onDel(){
  //      miRegView==null;
  //  }
}
