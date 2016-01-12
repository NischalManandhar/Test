package com.nischal.forpresentation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.nischal.forpresentation.database.MyDatabaseHelper;
import com.nischal.forpresentation.database.ObjectTable;

import java.util.ArrayList;
/*
* 1) Download jdk:
* http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html
*
* 2) Set JAVA_HOME path in environmental variables: With variable name=JAVA_HOME and path=C:\Program Files\Java\jdk1.8.0_40\
*
* 3) Download android studio:
* http://developer.android.com/sdk/index.html?gclid=CjwKEAiAws20BRCs-P-ssLbSlg4SJABbVcDpoN7ibC-As25568haBfOP04wBPrXLcNdjtMpS_pyWnBoCXGrw_wcB
*
*
* */

public class MainActivity extends AppCompatActivity {
    private EditText editTextUsername;
    private TextView textViewResult;
    private Button buttonSave, buttonDisplay;
    private MyDatabaseHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        helper=new MyDatabaseHelper(this);
        helper.createTable();
        editTextUsername = (EditText) findViewById(R.id.edittext_user_name);
        textViewResult = (TextView) findViewById(R.id.textview_result);
        buttonSave = (Button) findViewById(R.id.button_save);
        buttonDisplay = (Button) findViewById(R.id.button_display);
    }

    public void onClickView(View view) {
        int id=view.getId();
        String inputText;
        if(id==R.id.button_save){
            inputText=editTextUsername.getText().toString();
            Toast.makeText(getApplicationContext(), "input_text= "+ inputText, Toast.LENGTH_SHORT).show();
            helper.insertIntoTable(inputText);
        }
        if(id==R.id.button_display){
            ArrayList<ObjectTable> list=helper.getTableData();
            textViewResult.setText("");
            for(int i=0;i<list.size();i++){
                String result="id= "+list.get(i).id+"  text= "+list.get(i).text+"\n";
                textViewResult.append(result);
            }
        }
        if(id==R.id.button_delete){
            helper.deleteTable();
        }
    }
}
