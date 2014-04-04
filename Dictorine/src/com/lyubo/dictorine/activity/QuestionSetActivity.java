package com.lyubo.dictorine.activity;

import com.lyubo.dictorine.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;


public class QuestionSetActivity extends Activity{
	
	EditText question;
	EditText choice1;
	EditText choice2;
	EditText choice3;
	EditText choice4;
	int value =0;
	CheckBox checkbox1;
	CheckBox checkbox2;
	CheckBox checkbox3;
	CheckBox checkbox4;
	
	TextView tv1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_questionset);
	    
		
		//Log.i("QUESTION ACTIVITY", "VLIZA");
		
		Bundle b = getIntent().getExtras();
		int value = b.getInt("questionid");
		Log.i("QUESTION ACTIVITY", Integer.toString(value));
		
		tv1 = (TextView)findViewById(R.id.header1);
		switch(value) {
        case 1:
        	tv1.setText("Set your first question");
        break;
        case 2:
        	tv1.setText("Set second question");
        break;
        
        case 3:
        	tv1.setText("Set third question");
            break;
        
		}
	
	}
	
	
	
	    
	
	
	public void backButton(View view){
		
		Intent i = new Intent(QuestionSetActivity.this, UserActivity.class);
		startActivity(i);
        finish();
	}

	
	
	public void submitButton(View view){
		
		
		question = (EditText) findViewById(R.id.questionText);
		choice1 = (EditText) findViewById(R.id.choice1);
		choice2 = (EditText) findViewById(R.id.choice2);
		choice3 = (EditText) findViewById(R.id.choice3);
		choice4 = (EditText) findViewById(R.id.choice4);
		
		Log.e("Question", question.getText().toString());
		
		Intent i = new Intent(QuestionSetActivity.this, UserActivity.class);
		Bundle b = new Bundle();
		b.putInt("questionid", value);
		i.putExtras(b);
		startActivity(i);
        finish();
	}
}
