package sebastian.devmonkey.capstoneproject.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Typeface;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import sebastian.devmonkey.capstoneproject.other.DatabaseHelper;
import sebastian.devmonkey.capstoneproject.other.GlobalVariable;
import sebastian.devmonkey.capstoneproject.other.HowToUse;
import sebastian.devmonkey.capstoneproject.R;

public class SplashScreen extends AppCompatActivity {

    private HowToUse howToUse;
    private DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        db = new DatabaseHelper(this);

        if(!db.SettingshasObject()){
            db.insertDataSettings(GlobalVariable.fontSize, GlobalVariable.fontType, GlobalVariable.color, GlobalVariable.left, GlobalVariable.top, GlobalVariable.right, GlobalVariable.bottom, GlobalVariable.lineSpacing);
        } else {
            UpdateSettings();
        }



           new Handler().postDelayed(new Runnable() {
               @Override
               public void run() {
                   Intent intent =  new Intent();
                   //startActivity(new Intent(SplashScreen.this,MainActivity.class));




                   SharedPreferences prefs = getSharedPreferences("prefs",MODE_PRIVATE);
                   boolean firstStart = prefs.getBoolean("firstStart",true);
//
                   if(!firstStart) {
                       intent.setClass(SplashScreen.this,MainActivity.class);

                   }else{
                       intent.setClass(SplashScreen.this, HowToUse.class);

                   }
                   startActivity(intent);
                   overridePendingTransition(R.anim.fadein,R.anim.fadeout);
                   finish();


               }
           },5000);

           getSupportActionBar().hide();


//        getActionBar().hide();
    }



    private void UpdateSettings() {

        Cursor cursor = db.viewDataSettings();


        while (cursor.moveToNext()) {

            GlobalVariable.fontSize = cursor.getInt(1);
            GlobalVariable.fontType = cursor.getInt(2);
            GlobalVariable.color = cursor.getInt(3);
            GlobalVariable.left = cursor.getInt(4);
            GlobalVariable.top = cursor.getInt(5);
            GlobalVariable.right = cursor.getInt(6);
            GlobalVariable.bottom = cursor.getInt(7);
            GlobalVariable.lineSpacing = cursor.getInt(8);
        }



        if (GlobalVariable.fontType == 0) {
            GlobalVariable.font = Typeface.createFromAsset(this.getAssets(), "fonts/serif.ttf");
        } else if (GlobalVariable.fontType == 1) {
            GlobalVariable.font = Typeface.createFromAsset(this.getAssets(), "fonts/sanserif.ttf");
        }



    }

}
