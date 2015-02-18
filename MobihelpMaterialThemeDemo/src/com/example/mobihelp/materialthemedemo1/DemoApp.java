package com.example.mobihelp.materialthemedemo1;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import android.app.Application;

public class DemoApp extends Application {

	@Override
	public void onCreate() {
		super.onCreate();
		CalligraphyConfig calligraphyConfig = new CalligraphyConfig.Builder()
				.setDefaultFontPath("fonts/CursiveSans.ttf")
				.setFontAttrId(R.attr.fontPath)
				.build();

		CalligraphyConfig.initDefault(calligraphyConfig);
	}
}
