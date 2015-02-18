package com.example.mobihelp.materialthemedemo1;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.freshdesk.mobihelp.FeedbackType;
import com.freshdesk.mobihelp.Mobihelp;
import com.freshdesk.mobihelp.MobihelpCallbackStatus;
import com.freshdesk.mobihelp.MobihelpConfig;
import com.freshdesk.mobihelp.UnreadUpdatesCallback;

/**
 * Material Theme Demo - Steps to use toolbar with material theme
 * 
 * 1. Android Target version 5.0 or later 
 * 2. Support library - appcompat-v7 revision 21 or later 
 * 3. Uncomment Toolbar widget and delete the dummy view from the xml file in MobihelpSDK/res/layout/mobihelp_toolbar.xml
 * 4. Theme set to *.NoActionBar variant of AppCompat theme
 * 
 * Custom Font Support
 * 1. Ensure Calligraphy library is included as a dependency
 * 2. Include the ttf font in assets
 * 3. Set the font using calligrahy (Eg. Refer Application class -> DemoApp.java) 
 * 
 * Calligraphy Github Repo : https://github.com/chrisjenx/Calligraphy
 * 
 */
public class MainActivity extends ActionBarActivity {

	Button btnSupport;	
	public void onCreate(Bundle savedInstance) {
		super.onCreate(savedInstance);
		
		setContentView(R.layout.activity_main);

		MobihelpConfig config = new MobihelpConfig("https://yourfreshdeskdomain.freshdesk.com", "your-app-id-here", "your-app-secret-here");
		config.setFeedbackType(FeedbackType.NAME_AND_EMAIL_REQUIRED);
		Mobihelp.init(this, config);

		// Add Custom data pertaining to your application 
		Mobihelp.addCustomData("User Type", "Paid");
		Mobihelp.addCustomData("Level Completed", "14");
		Mobihelp.addCustomData("Achievements Unlocked", "Explorer");
		
		// Drop BreadCrumbs to track user activity
		Mobihelp.leaveBreadCrumb(this.getLocalClassName());
		
		btnSupport = (Button) findViewById(R.id.btnSupport);
		Button btnTalkToUs = (Button) findViewById(R.id.btnTalkToUs);
		Button btnAppRateDialog = (Button) findViewById(R.id.btnAppRateDialog);
		
		btnSupport.setOnClickListener(btnClickListener);
		btnTalkToUs.setOnClickListener(btnClickListener);
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

			case R.id.btnTalkToUs:
				// Launch Feedback Directly
				Mobihelp.showFeedback(MainActivity.this);
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
