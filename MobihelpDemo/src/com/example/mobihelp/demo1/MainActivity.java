package com.example.mobihelp.demo1;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.freshdesk.mobihelp.FeedbackRequest;
import com.freshdesk.mobihelp.FeedbackType;
import com.freshdesk.mobihelp.Mobihelp;
import com.freshdesk.mobihelp.MobihelpCallbackStatus;
import com.freshdesk.mobihelp.MobihelpConfig;
import com.freshdesk.mobihelp.UnreadUpdatesCallback;

public class MainActivity extends ActionBarActivity {

	Button btnSupport;
	
	public void onCreate(Bundle savedInstance) {
		super.onCreate(savedInstance);
		
		setContentView(R.layout.activity_main);
		
		MobihelpConfig config = new MobihelpConfig("https://yourfreshdeskdomain.freshdesk.com",
				"your-app-id-here", "your-app-secret-here");
		config.setFeedbackType(FeedbackType.NAME_AND_EMAIL_REQUIRED);
		config.setAutoReplyEnabled(true);
		Mobihelp.init(this, config);

		// Add Custom data pertaining to your application 
		Mobihelp.addCustomData("User Type", "Paid");
		Mobihelp.addCustomData("Level Completed", "14");
		Mobihelp.addCustomData("Achievements Unlocked", "Explorer");

		// Drop BreadCrumbs to track user activity
		Mobihelp.leaveBreadCrumb(this.getLocalClassName());
		
		btnSupport = (Button) findViewById(R.id.btnSupport);
		Button btnFeedback = (Button) findViewById(R.id.btnFeedback);
		Button btnSolutions = (Button) findViewById(R.id.btnSolutions);
		Button btnAppRateDialog = (Button) findViewById(R.id.btnAppRateDialog);
		
		btnSupport.setOnClickListener(btnClickListener);
		btnFeedback.setOnClickListener(btnClickListener);
		btnSolutions.setOnClickListener(btnClickListener);
		btnAppRateDialog.setOnClickListener(btnClickListener);
	}

	OnClickListener btnClickListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.btnSupport:
				// Launch Support 
				Mobihelp.showSupport(MainActivity.this);
				break;

			case R.id.btnFeedback:
				// Launch Feedback Directly with prefilled subject and/or description
				FeedbackRequest feedbackRequest = new FeedbackRequest();
				feedbackRequest.setSubject("Order 12345"); //Can be a dynamic id like order id from your app
				feedbackRequest.setDescription("Prefilled description for the user");
				Mobihelp.showFeedback(MainActivity.this, feedbackRequest);
				break;
				
			case R.id.btnSolutions:
				Mobihelp.showSolutions(MainActivity.this);
				break;

			case R.id.btnAppRateDialog:
				// Manually prompt for an App Rating/Feedback Dialog
				Mobihelp.showAppRateDialog(MainActivity.this);
				break;
			}
		}
	};
	
	protected void onResume() {
		super.onResume();
		Mobihelp.getUnreadCountAsync(this, countUpdateCallback);
	}
	
	UnreadUpdatesCallback countUpdateCallback = new UnreadUpdatesCallback() {
		@Override
		public void onResult(MobihelpCallbackStatus statusCode, Integer count) {
			String name = "Support";
			name += count > 0 ? " (" + count + ")" : "";
			btnSupport.setText(name);
		}
	};
}
