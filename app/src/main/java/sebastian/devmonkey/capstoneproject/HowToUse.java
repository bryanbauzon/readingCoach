package sebastian.devmonkey.capstoneproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import sebastian.devmonkey.capstoneproject.activity.MainActivity;
import sebastian.devmonkey.capstoneproject.activity.SliderAdapter;

public class HowToUse extends AppCompatActivity {

    //onBoarding
    private ViewPager mSliderViewPager;
    private LinearLayout mDotLayout;
    private  Button finish,back;
    private SliderAdapter sliderAdapter;
    private TextView[] dots;
    private int currentPage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_how_to_use);
        test();
        getSupportActionBar().hide();
    }

    public void test(){

        mSliderViewPager = (ViewPager)findViewById(R.id.slideViewPager);
        mDotLayout = (LinearLayout)findViewById(R.id.dotLayout);

        finish = (Button)findViewById(R.id.finish);
        back = (Button)findViewById(R.id.back);
         finish.setText("Next");
        dots = new TextView[8];
        sliderAdapter = new SliderAdapter(this);
        mSliderViewPager.setAdapter(sliderAdapter);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSliderViewPager.setCurrentItem(currentPage - 1);
            }
        });
            finish.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(finish.getText().toString().equals("Next")){
                        mSliderViewPager.setCurrentItem(currentPage + 1);
                    }else{
                        startActivity(new Intent(HowToUse.this,MainActivity.class));
                        System.exit(0);
                    }
                }
            });

        addDotIndicator(0);
        mSliderViewPager.addOnPageChangeListener(viewListener);


        SharedPreferences prefs = getSharedPreferences("prefs",MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean("firstStart",false);
        editor.apply();
    }

    public void addDotIndicator(int position){

        mDotLayout.removeAllViews();
        for(int i = 0 ; i < dots.length; i++){
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(getResources().getColor(R.color.transparentWhite));
            mDotLayout.addView(dots[i]);
        }
        if(dots.length > 0){
            dots[position].setTextColor(getResources().getColor(R.color.white));
        }
    }
    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            currentPage = position;
            addDotIndicator(position);
            if(position == 0  ){
                back.setVisibility(View.INVISIBLE);
                finish.setText("Next");
                back.setText(null);
              //  finish.setEnabled(false);

            }else if(currentPage == dots.length - 1){
                finish.setEnabled(true);
                back.setVisibility(View.VISIBLE);
                finish.setText("Finish");
                back.setText("Back");

            }

            else{
                back.setVisibility(View.VISIBLE);
                finish.setText("Next");
                back.setText("Back");

            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

}
