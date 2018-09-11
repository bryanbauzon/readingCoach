package sebastian.devmonkey.capstoneproject.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;

import sebastian.devmonkey.capstoneproject.HowToUse;
import sebastian.devmonkey.capstoneproject.R;

public class SplashScreen extends AppCompatActivity {

    private HowToUse howToUse;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);




        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent =  new Intent();
                //startActivity(new Intent(SplashScreen.this,MainActivity.class));


                SharedPreferences prefs = getSharedPreferences("prefs",MODE_PRIVATE);
                boolean firstStart = prefs.getBoolean("firstStart",true);
//
                if(firstStart){
                    intent.setClass(SplashScreen.this,HowToUse.class);

                }else{
                    intent.setClass(SplashScreen.this,MainActivity.class);

                }
                startActivity(intent);
                overridePendingTransition(R.anim.fadein,R.anim.fadeout);
                finish();


            }
        },5000);

        getSupportActionBar().hide();

//        getActionBar().hide();
    }
}
