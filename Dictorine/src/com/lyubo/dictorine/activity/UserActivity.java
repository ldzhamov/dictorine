package com.lyubo.dictorine.activity;


import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.facebook.Session;
import com.facebook.widget.ProfilePictureView;
import com.lyubo.dictorine.R;
import com.lyubo.dictorine.services.ServiceHandler;
import com.lyubo.dictorine.services.SessionManager;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;


public class UserActivity extends Activity {
	
	private ProgressDialog pDialog;
	JSONArray users = null;
	JSONArray questions = null;
	JSONArray choices = null;
	Session sessionfb;
	JSONObject userjson;
	private static int question = 0;
    // URL to get contacts JSON
    private static String url = "http://192.241.175.161:8000/test/adduser/5/";
    SessionManager session;
    View view;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user);
		session = new SessionManager(getApplicationContext());
		
		sessionfb = Session.getActiveSession();
		//Log.d("ONCREATE ", "SEssion created-check login");
		session.checkLogin();
		//Log.d("ONCREATE ", "SEssion created-checked login");
		// get user data from session
        HashMap<String, String> user = session.getUserDetails();
        
        Bundle b = getIntent().getExtras();
        if (b!=null){
        	int questvalue = b.getInt("questionid");
        	Log.i("QUESTION ACTIVITY", Integer.toString(questvalue));
        	if (questvalue !=0){
        		//Set the question
        	}
        	else{
        		//Not comming from questions
        	}
        	
        }
        else{
        	//initial
        }
        //int value = b.getInt("questionid");
		//Log.i("QUESTION ACTIVITY", Integer.toString(value));
        //user.put(SessionManager.KEY_QUESTIONS, value)
         
        // name
        //Log.i("EMAILLLL!!!!!!!!!!", user.get(SessionManager.KEY_EMAIL));
        String face_id = user.get(SessionManager.KEY_FACEBOOKID);
        String name = user.get(SessionManager.KEY_NAME);
        String email = user.get(SessionManager.KEY_EMAIL);

        ProfilePictureView profilePicture = (ProfilePictureView) findViewById(R.id.profilePicture);
        profilePicture.setProfileId(face_id);
        userjson = new JSONObject();

        try {
            userjson.put("username", email);
            

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        new GetContacts().execute();
        
		Log.i("USERJSONNNNNNNNNNN", userjson.toString());
        
         
        // email
        
       // String id = user.get(SessionManager.KEY_NAME);
         
        //Log.i("USERNAME", "Name: " + name + "");
        //Log.i("EMAIL", "Name: " + email + "");
        // displaying user data
       // lblName.setText(Html.fromHtml("Name: <b>" + name + "</b>"));
        //lblEmail.setText(Html.fromHtml("Email: <b>" + email + "</b>"));
        
		
	}

	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.user, menu);
		return true;
	}
	
	
	public void getData(View view){
		if (isNetworkAvailable()){
			new GetContacts().execute();
		}
		else{
			Log.i("NETWORK", "No access to network");
		}
	}
	
	public void logOut(View view){
		
		session.logoutUser();
		if (sessionfb != null){
			if (sessionfb.isOpened()){
				Log.d("FACEBOOK", "SESSION OPENED");
				sessionfb.close();
			}
		}
		
	}
	public void questionSet(View view){
		
		switch(view.getId()) {
        case R.id.question1:
        	question = 1;
        break;
        case R.id.question2:
        	question = 2;
        break;
        
        case R.id.question3:
        	question = 3;
            break;
        
		}
		Intent i = new Intent(UserActivity.this, QuestionSetActivity.class);
		Bundle b = new Bundle();
		b.putInt("questionid", question);
		i.putExtras(b);
        startActivity(i);
        finish();
		
	}
	
	
	private boolean isNetworkAvailable() {
		ConnectivityManager cm =
		        (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
		 
		NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
		boolean isConnected = activeNetwork != null &&
		                      activeNetwork.isConnectedOrConnecting();
		return isConnected;
	}
	
	public class GetContacts extends AsyncTask<Void, Void, Void> {
		 
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(UserActivity.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();
 
        }
        
        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog
            if (pDialog.isShowing())
                pDialog.dismiss();
            
        }
        
        @Override
        protected Void doInBackground(Void... arg0) {
            // Creating service handler class instance
            ServiceHandler sh = new ServiceHandler();
 
            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall(url, ServiceHandler.POST, userjson.toString());
 
            Log.d("Response: ", "> " + jsonStr);
//            if (jsonStr!=null){
//	            	try {
//						JSONObject jsonObj = new JSONObject(jsonStr);
//						users = jsonObj.getJSONArray("users");
//						for (int i = 0; i < users.length(); i++){
//							//users = jsonObj.getJSONArray("questions");
//							JSONObject c = users.getJSONObject(i);
//							Log.i("User: ", "> " + c.getString("username"));
//							//Log.d("Questions: ", "> " + c.getString("questions"));
//							questions = c.getJSONArray("questions");
//							for (int j = 0; j < questions.length(); j++){
//								JSONObject q = questions.getJSONObject(j);
//								Log.i("Question: ", "> " + q.getString("question"));
//								choices = q.getJSONArray("choices");
//								for (int v = 0; v < choices.length(); v++){
//									JSONObject z = choices.getJSONObject(v);
//									Log.d("Choice: ", "> " + z.getString("choice") + "  Correct: " +  z.getString("correct"));
//								}
//							}
//						}
//							
//						
//				} catch (JSONException e) {
//						
//						e.printStackTrace();
//					}
//            	
//            	
//            }
            
            return null;
        }
    }
	
	

}
