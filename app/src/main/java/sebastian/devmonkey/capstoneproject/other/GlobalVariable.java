package sebastian.devmonkey.capstoneproject.other;

import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;

public class GlobalVariable {

    //fontsize
    public static int fontSize = 15;
    //font
    public static Typeface font;


    //page margin
    public static int left;
    public static int top;
    public static int right;
    public static int bottom;

    //line spacing
    public static float lineSpacing = 0;


    public static int color;


    public void setMargins (View view, int left, int top, int right, int bottom) {
        if (view.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
            p.setMargins(left, top, right, bottom);
            view.requestLayout();
        }
    }

}
