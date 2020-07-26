package com.ypasoft.contactracing;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.ypasoft.contactracing.helpers.PermissionHelper;
import com.ypasoft.contactracing.tools.TransparentProgressDialog;

public class SplashActivity extends AppCompatActivity {

    public TransparentProgressDialog transparentProgressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        transparentProgressDialog = new TransparentProgressDialog(SplashActivity.this);
        startAnimation();

    }

    public void goToMain() {
        startActivity(new Intent(this, MainActivity.class)
                .setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY |
                        Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS |
                        Intent.FLAG_ACTIVITY_CLEAR_TOP)
                .setAction(Intent.ACTION_MAIN));
        finish();
    }

    private void startAnimation() {
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.alpha);
        anim.reset();
        LinearLayout l = (LinearLayout) findViewById(R.id.main_layout_splash);
        if (l != null) {
            l.clearAnimation();
            l.startAnimation(anim);
        }
        anim = AnimationUtils.loadAnimation(this, R.anim.translate);
        anim.reset();
        ImageView iv = (ImageView) findViewById(R.id.splash);
        if (iv != null) {
            iv.clearAnimation();
            iv.startAnimation(anim);
        }

        int SPLASH_ANIMATION_LENGTH = 3000;

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                goToMain();

            }
        }, SPLASH_ANIMATION_LENGTH);
    }


}