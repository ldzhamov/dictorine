package com.lyubo.dictorine.activity;



import java.util.Arrays;

import com.facebook.Request;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.UiLifecycleHelper;
import com.facebook.model.GraphUser;
import com.facebook.widget.LoginButton;


import com.lyubo.dictorine.R;
import com.lyubo.dictorine.activity.UserActivity.GetContacts;
import com.lyubo.dictorine.services.SessionManager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MainFragment extends Fragment {
	
	public String url = "http://192.241.175.161:8000/test/5/";
	SessionManager session1;
	private UiLifecycleHelper uiHelper;
	private static final String TAG = "FaACEBOOK TAG";
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		
	    View view = inflater.inflate(R.layout.activity_login, container, false);
	    LoginButton authButton = (LoginButton) view.findViewById(R.id.authButton);
	    authButton.setReadPermissions(Arrays.asList("user_likes", "user_status"));
	    authButton.setFragment(this);
	    


	    return view;
	}
	
	private void onSessionStateChange(Session session, SessionState state, Exception exception) {
		
		session1 = new SessionManager(getActivity());
		if (state.isOpened()) {
		   // userInfoTextView.setVisibility(View.VISIBLE);
		
		    // Request user data and show the results
		    Request.newMeRequest(session, new Request.GraphUserCallback() {
				
				@Override
				public void onCompleted(GraphUser user, Response response) {
					
		                // Display the parsed user info
		                //userInfoTextView.setText(buildUserInfoDisplay(user));
						
					
						session1.createLoginSession(user.getName(), (String) user.asMap().get("email"), user.getId(), null);
						Intent i = new Intent(getActivity(), UserActivity.class);
                        startActivity(i);
                        getActivity().finish();
		            
				
					
				}
			}).executeAsync();
				
				
		
		}
		else if (state.isClosed()) {
	        Log.i(TAG, "Logged out...");
	    }
	}
	
	private Session.StatusCallback callback = new Session.StatusCallback() {
	    @Override
	    public void call(Session session, SessionState state, Exception exception) {
	        onSessionStateChange(session, state, exception);
	    }
	};
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    uiHelper = new UiLifecycleHelper(getActivity(), callback);
	    uiHelper.onCreate(savedInstanceState);
	}
	
	
	
	@Override
	public void onResume() {
	    super.onResume();
	    uiHelper.onResume();
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
	    super.onActivityResult(requestCode, resultCode, data);
	    uiHelper.onActivityResult(requestCode, resultCode, data);
	}

	@Override
	public void onPause() {
	    super.onPause();
	    uiHelper.onPause();
	}

	@Override
	public void onDestroy() {
	    super.onDestroy();
	    uiHelper.onDestroy();
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
	    super.onSaveInstanceState(outState);
	    uiHelper.onSaveInstanceState(outState);
	}

}

