package com.lyubo.dictorine.activity;



import com.lyubo.dictorine.R;
import com.lyubo.dictorine.services.SessionManager;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.Button;
import android.widget.EditText;

 
public class LoginActivity extends FragmentActivity {
  
	
	
	// Email, password edittext
	
    EditText txtUsername, txtPassword;
     
    // login button
    Button btnLogin;
     
    // Alert Dialog Manager
    //AlertDialogManager alert = new AlertDialogManager();
     
    // Session Manager Class
    SessionManager session;
    Context c;
    private MainFragment mainFragment;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login); 
        
        if (savedInstanceState == null) {
	        // Add the fragment on initial activity setup
	        mainFragment = new MainFragment();
	        getSupportFragmentManager()
	        .beginTransaction()
	        .add(android.R.id.content, mainFragment)
	        .commit();
	    } else {
	        // Or set the fragment from restored state info
	        mainFragment = (MainFragment) getSupportFragmentManager()
	        .findFragmentById(android.R.id.content);
	    }
        
        
         
//        facebook = new Facebook(APP_ID);
//        mAsyncRunner = new AsyncFacebookRunner(facebook);
//        
//        // Session Manager
//        session = new SessionManager(getApplicationContext());                
//         
//        // Email, Password input text
//        txtUsername = (EditText) findViewById(R.id.txtUsername);
//        txtPassword = (EditText) findViewById(R.id.txtPassword); 
//         
//        Toast.makeText(getApplicationContext(), "User Login Status: " + session.isLoggedIn(), Toast.LENGTH_LONG).show();
//         
//         
//        // Login button
//        btnLogin = (Button) findViewById(R.id.btnLogin);
//         
//         
//        // Login button click event
//        btnLogin.setOnClickListener(new View.OnClickListener() {
//             
//            @Override
//            public void onClick(View arg0) {
//                // Get username, password from EditText
//                String username = txtUsername.getText().toString();
//                String password = txtPassword.getText().toString();
//                 
//                // Check if username, password is filled                
//                if(username.trim().length() > 0 && password.trim().length() > 0){
//                    // For testing puspose username, password is checked with sample data
//                    // username = test
//                    // password = test
//                    if(username.equals("test") && password.equals("test")){
//                         
//                        // Creating user login session
//                        // For testing i am stroing name, email as follow
//                        // Use user real data
//                        session.createLoginSession("Android Hive", "anroidhive@gmail.com");
//                         
//                        // Staring MainActivity
//                        Intent i = new Intent(getApplicationContext(), UserActivity.class);
//                        startActivity(i);
//                        finish();
//                         
//                    }else{
//                        // username / password doesn't match
//                       // alert.showAlertDialog(LoginActivity.this, "Login failed..", "Username/Password is incorrect", false);
//                    	Log.e("LOGIN ERROR", "LOgin doesn't match!!!!!");
//                    }               
//                }else{
//                    // user didn't entered username or password
//                    // Show alert asking him to enter the details
//                   // alert.showAlertDialog(LoginActivity.this, "Login failed..", "Please enter username and password", false);
//                	Log.e("LOGIN ERROR", "LOgin failed Enter username and password");
//                }
//                 
//            }
//        });
//    }        
 //   public void loginFacebook(View view){
    	
    	//mPrefs = getPreferences(MODE_PRIVATE);
        //String access_token = mPrefs.getString("access_token", null);
        //long expires = mPrefs.getLong("access_expires", 0);
     
        //if (access_token != null) {
        //    facebook.setAccessToken(access_token);
        //}
     
        //if (expires != 0) {
        //    facebook.setAccessExpires(expires);
        //}
     
//        if (!facebook.isSessionValid()) {
//            facebook.authorize(this,
//                    new String[] { "email", "publish_stream" },
//                    new DialogListener() {
//     
//                        @Override
//                        public void onCancel() {
//                            // Function to handle cancel event
//                        }
//     
//                        @Override
//                        public void onComplete(Bundle values) {
//                        	
//                        	AsyncFacebookRunner myAsyncRunner = new AsyncFacebookRunner(facebook);
//                        	myAsyncRunner.request("me", new meRequestListener());
//                            
//                        	 session.createLoginSession("Android Hive", "anroidhive@gmail.com");
//                             
//                             // Staring MainActivity
//                             Intent i = new Intent(getApplicationContext(), UserActivity.class);
//                             startActivity(i);
//                             finish();
////                            SharedPreferences.Editor editor = mPrefs.edit();
////                            editor.putString("access_token",
////                                    facebook.getAccessToken());
////                            editor.putLong("access_expires",
////                                    facebook.getAccessExpires());
////                            editor.commit();
//                        }
//     
//                        @Override
//                        public void onError(DialogError error) {
//                            // Function to handle error
//     
//                        }
//     
//                        @Override
//                        public void onFacebookError(FacebookError fberror) {
//                            // Function to handle Facebook errors
//     
//                        }
//     
//                    });
//        }
//		
//		
	}
}
