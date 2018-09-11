package sebastian.devmonkey.capstoneproject.activity;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import sebastian.devmonkey.capstoneproject.R;

public class SliderAdapter extends PagerAdapter {
    private Context context;
    private LayoutInflater inflater;

    public SliderAdapter(Context context){
        this.context = context;
    }
    //arrays
    public int[] slideImages  = {
            R.drawable.sample1,
            R.drawable.sample1,
            R.drawable.sample1,
            R.drawable.sample1,
            R.drawable.sample1,
            R.drawable.sample1,
            R.drawable.sample1,
            R.drawable.sample1
    };

    public String[] slideHeading = {
            "Dashboard",
            "Reading Plans",
            "Bookmark",
            "Terminologies",
            "Journal",
            "About",
            "Help",
            "Settings"
    };
    public String[] slideContents = {
 "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur eget quam in ligula malesuada accumsan. Donec tincidunt pulvinar sapien at mattis.",
    "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur eget quam in ligula malesuada accumsan. Donec tincidunt pulvinar sapien at mattis.",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur eget quam in ligula malesuada accumsan. Donec tincidunt pulvinar sapien at mattis.",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur eget quam in ligula malesuada accumsan. Donec tincidunt pulvinar sapien at mattis.",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur eget quam in ligula malesuada accumsan. Donec tincidunt pulvinar sapien at mattis.",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur eget quam in ligula malesuada accumsan. Donec tincidunt pulvinar sapien at mattis.",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur eget quam in ligula malesuada accumsan. Donec tincidunt pulvinar sapien at mattis.",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur eget quam in ligula malesuada accumsan. Donec tincidunt pulvinar sapien at mattis.",

    };

    @Override
    public int getCount() {
        return slideHeading.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == (LinearLayout) object;
    }

    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        inflater = (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.slidelayout,container,false);

        ImageView slideImage = view.findViewById(R.id.slideImages);
        TextView slideTitle = view.findViewById(R.id.slideTitle);
        TextView slideContent = view.findViewById(R.id.slideContent);

        slideImage.setImageResource(slideImages[position]);
        slideTitle.setText(slideHeading[position]);
        slideContent.setText(slideContents[position]);


        container.addView(view);


        return  view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((LinearLayout) object);

    }
}
