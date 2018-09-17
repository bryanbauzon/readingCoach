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
            R.drawable.hhome,
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
 "This feature contains a brief history of reading comprehension. You can also easily access the main menus of the application",
    "Read and learn through this passage comprehension and word pronunciation tests",
            "Save and read your favorite  stories and  poems anytime, anywhere you want.",
            "Take a certain important details in every stories and poems.",
            "Gain and acquire new terminologies and vocabulary from your favorite stories and poems.",
            "Informs you about the developers and application information.",
            "Ease and guide you to effectively and efficiently use the feature of the application.",
            "Alter and adjust your stories and poems with your own style when reading."
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
