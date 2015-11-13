package hackathon.merdeka.publicinfrastructurereport.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import hackathon.merdeka.publicinfrastructurereport.R;
import hackathon.merdeka.publicinfrastructurereport.helper.SQLiteHandler;
import hackathon.merdeka.publicinfrastructurereport.helper.SessionManager;

public class SplashScreenActivity extends Activity {

    // Splash screen timer
    private static int SPLASH_TIME_OUT = 3000;

    private SessionManager session;
    private SQLiteHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                db = new SQLiteHandler(getApplicationContext());

                session = new SessionManager(getApplicationContext());

                // Check if user is already logged in or not
                if (session.isLoggedIn()) {
                    // User is already logged in. Take him to main activity
                    Intent intent = new Intent(SplashScreenActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Intent i = new Intent(SplashScreenActivity.this, LoginActivity.class);
                    startActivity(i);

                    finish();
                }
            }
        }, SPLASH_TIME_OUT);
    }

}