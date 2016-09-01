Steps to make this Custom Theme Sample work

1. Make sure you have imported the latest version of Mobihelp SDK & reference the same from this sample.
2. Open Mobihelp SDK's AndroidManifest.xml file and remove all "android:theme" entries.
3. Make sure you have a theme setup in your app's AndroidManifest.xml as all the SDK activities will inherit your app's theme
4. Copy all the style elements from the right variant of Theme.MobihelpSDK to your theme.
Eg. If your theme inherits from Theme.AppCompat (Dark Theme), copy style elements from Theme.MobihelpSDK
	If your theme inherits from Theme.AppCompat.Light (Light Theme), copy style elements from Theme.MobihelpSDK.Light
5. Do a clean build to allow manifest merger to run during build process and launch the application.
