package com.example.thinkpad.wsj20186191601v;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.thinkpad.wsj20186191601v.model.Bean.Regbean;
import com.example.thinkpad.wsj20186191601v.presccse.Regpresccse;
import com.example.thinkpad.wsj20186191601v.view.IRegView;

public class MainActivity extends AppCompatActivity implements IRegView{

    private TextView reg_user;
    private TextView reg_password;
    private TextView reg_qrpassword;
    private Regpresccse regpresccse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        reg_user = findViewById(R.id.reg_user);
        reg_password = findViewById(R.id.reg_password);
        reg_qrpassword = findViewById(R.id.reg_qrpassword);
        regpresccse = new Regpresccse(this);
    }

    public void reg(View view) {
        String name = reg_user.getText().toString();
        String password = reg_password.getText().toString();
        regpresccse.reg(name,password);
    }

    @Override
    public void onFailure(String e) {
        Toast.makeText(this,e,Toast.LENGTH_LONG);
    }

    @Override
    public void onResponse(Regbean regbean) {
        Toast.makeText(this,"注册成功",Toast.LENGTH_LONG);
        Intent intent = new Intent(MainActivity.this, Main2Activity.class);
        startActivity(intent);
    }
}
