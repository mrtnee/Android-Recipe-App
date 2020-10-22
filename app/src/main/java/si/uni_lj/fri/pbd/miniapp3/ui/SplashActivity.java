package si.uni_lj.fri.pbd.miniapp3.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import si.uni_lj.fri.pbd.miniapp3.R;

public class SplashActivity extends AppCompatActivity {

    private static final int SPLASH_DELAY = 2000;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_splash);

        // Start MainActivity after 2 seconds
        new Handler().postDelayed(() -> {
            Intent mainActivity = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(mainActivity);
            finish();
        }, SPLASH_DELAY);
    }
}
