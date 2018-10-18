package sebastian.devmonkey.capstoneproject.other;

import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;

public class GlobalVariable {

    //fontsize
    public static int fontSize = 15;

    //font
    public static Typeface font;
    public static int fontType = 0;


    //page margin
    public static int left = 0;
    public static int top = 0;
    public static int right = 0;
    public static int bottom = 0;

    //line spacing
    public static int lineSpacing = 0;


    public static int color = 0;


    public void setMargins (View view, int left, int top, int right, int bottom) {
        if (view.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
            p.setMargins(left, top, right, bottom);
            view.requestLayout();
        }
    }

}
