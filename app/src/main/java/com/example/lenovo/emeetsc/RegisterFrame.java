package com.example.lenovo.emeetsc;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

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

/**
 * Created by lenovo on 2017/3/8.
 */

public class RegisterFrame extends AppCompatActivity {
    private ImageView registerBtn;
    private EditText userid;
    private EditText password;
    private EditText repassword;
    private String authorization;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_frame);
        registerBtn=(ImageView) findViewById(R.id.registerButton);
        userid= (EditText) findViewById(R.id.usernameEdt);
        password= (EditText) findViewById(R.id.passwordEdt);
        repassword=(EditText)findViewById(R.id.querenmimaEdt);

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usernameStr=userid.getText().toString();
                String passwordStr=password.getText().toString();
                String repasswordStr=repassword.getText().toString();
                Map<String,String> map=new HashMap<String, String>();
                map.put("account",usernameStr);
                map.put("password",passwordStr);
                map.put("repassword",repasswordStr);

                doHttpAsync(map);
            }
        });
    }
//    private void scoreMatch(){
//        String url="http://192.168.1.103:8080/internships/22/match";
//        OkHttpUtil.getDefault(this).doGetAsync(HttpInfo.Builder()
//                        .setUrl(url)
//                        .addHead("authorization",authorization)
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
    private void doHttpAsync(Map<String,String> map) {
        String url="http://192.168.1.103:8080/users";  //我改了users (原token)
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

//                            if(userResult.code==100){
//                                //Toast.makeText(getApplicationContext(),"跳转",Toast.LENGTH_LONG).show();
//                                Intent intent = new Intent();
//                                intent.setClass(LoginFrame.this, RegisterFrame.class);
//                                LoginFrame.this.startActivity(intent);
//                            }

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
