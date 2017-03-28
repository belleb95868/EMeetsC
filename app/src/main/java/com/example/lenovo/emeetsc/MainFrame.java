package com.example.lenovo.emeetsc;

import android.app.Activity;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class MainFrame extends Activity {
    private ImageView touxiang1;
    private ListView myListView;
    private MyAdapter adapter;
    private EditText sousuo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_frame);

        List<forumInfo> forumList = new ArrayList<forumInfo>();
        myListView = (ListView) findViewById(R.id.MyListView);
        myListView.setTextFilterEnabled(true);  //过滤除了关键字以外的选项
        sousuo=(EditText)findViewById(R.id.search);

        sousuo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                MainFrame.this.adapter.getFilter().filter(s);

            }
        });



        forumList.add(new forumInfo("杨东春", "今天的英语作业是什么？", "求作业！！！！",
                R.drawable.tx1));
        forumList.add(new forumInfo("丁茜", "今天的英语作业是什么？", "求作业！！！！求作业！！！" +
                "！求作业" + "！！！！求作业！！！！求作业！！！！求作业！！！！求作业！！！！求作业！" +
                "！！！" + "求作业！！！！求作业！！！！求作业！！！！求作业！！！！",R.drawable.tx2));
        forumList.add(new forumInfo("Wendy　He", "今天的英语作业是什么？", "求作业！！！！",
                R.drawable.tx3));
        forumList.add(new forumInfo("邱璐莹", "你们可以帮我看看我的作文吗？谢谢！",
                "今天孙老师发了一个作业要我们写关于历史长城的故事，请问你们可以帮我看看我写的作文吗？",
                R.drawable.tx4));
        forumList.add(new forumInfo("崔梦园", "明天不想去上课啊",
                "明天2班的同学可以帮我和老师说我生病了吗？",
                R.drawable.tx1));

        adapter = new MyAdapter(MainFrame.this, forumList);
        myListView.setAdapter(adapter);

    }
}