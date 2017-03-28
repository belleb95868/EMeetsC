package com.example.lenovo.emeetsc;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.lenovo.emeetsc.bean.Result;
import com.example.lenovo.emeetsc.bean.UserInfo;
import com.example.lenovo.emeetsc.http.HttpInfo;
import com.example.lenovo.emeetsc.http.OkHttpUtil;
import com.example.lenovo.emeetsc.http.callback.CallbackOk;
import com.example.lenovo.emeetsc.utils.JsonUtil;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class LoginFrame extends AppCompatActivity {

    private ImageView loginBtn;
    private ImageView registerBtn;
    private EditText username;
    private EditText password;
    private String authorization;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_frame);

        loginBtn=(ImageView) findViewById(R.id.LoginButton);
        registerBtn=(ImageView)findViewById(R.id.registerButton);
        username= (EditText) findViewById(R.id.usernameEdt);
        password= (EditText) findViewById(R.id.passwordEdt);

        Log.i("tag","进入1");

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("tag","进入2");
                Intent intent = new Intent();
                intent.setClass(LoginFrame.this, MainFrame.class);
                LoginFrame.this.startActivity(intent);

//                String usernameStr=username.getText().toString();
//                String passwordStr=password.getText().toString();
//                Map<String,String> map=new HashMap<String, String>();
//                map.put("account",usernameStr);
//                map.put("password",passwordStr);
//                doHttpAsync(map);
            }
        });
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                                intent.setClass(LoginFrame.this, RegisterFrame.class);
                                LoginFrame.this.startActivity(intent);

//                scoreMatch();
            }
        });
    }
//    private void scoreMatch(){
//        String url="http://192.168.1.103:8080/internships/22/match";
//        OkHttpUtil.getDefault(this).doGetAsync(HttpInfo.Builder()
//                        .setUrl(url)
//                .addHead("authorization",authorization)
//                        .build(), new CallbackOk() {
//                    @Override
//                    public void onResponse(HttpInfo info) throws IOException {
//                        if(info.isSuccessful()){
//                            String result=info.getRetDetail();
//                            Type resultType = new TypeToken<Result<String>>(){}.getType();
//                            Result<String> matchResult = JsonUtil.fromJson(result,resultType);
//                            Log.i("code", String.valueOf(matchResult.code));
//                            Log.i("msg",matchResult.msg);
//                            Log.i("rank",matchResult.result);
//
//                        }
//                    }
//                }
//        );
//    }
    private void doHttpAsync(Map<String,String> map) {   //传入登录参数
        String url="http://192.168.1.103:8080/tokens";
        String json= JsonUtil.map2Json(map);
        Log.i("json",json);
        OkHttpUtil.getDefault(this).doPostAsync(
                HttpInfo.Builder()
                        .setUrl(url)
                        .setJsonStr(json)
                        //.addParams(map)
                        //.addHead("Content-Type","application/json")
                        .build(),
                new CallbackOk() {
                    @Override
                    public void onResponse(HttpInfo info) throws IOException {
                        if (info.isSuccessful()) {
                            String result = info.getRetDetail();
                            Type resultType = new TypeToken<Result<UserInfo>>(){}.getType();
                            Result<UserInfo> userResult = JsonUtil.fromJson(result,resultType);
                            Log.i("code", String.valueOf(userResult.code));

                            if(userResult.code==100){    //如果登录成功就跳转页面
                                Toast.makeText(getApplicationContext(),"登录成功",Toast.LENGTH_SHORT).show();
//                                Intent intent = new Intent();
//                                intent.setClass(LoginFrame.this, RegisterFrame.class);
//                                LoginFrame.this.startActivity(intent);
                            }

                            Log.i("msg",userResult.msg);
                            UserInfo userInfo=userResult.result;
                            Log.i("userId", String.valueOf(userInfo.getUserId()));
                            Log.i("token",userInfo.getToken());
                            Log.i("authorization",userInfo.getAuthorization());
                            authorization=userInfo.getAuthorization();

                            Log.i("result",result);
                        }
                    }
                });

    }



}
