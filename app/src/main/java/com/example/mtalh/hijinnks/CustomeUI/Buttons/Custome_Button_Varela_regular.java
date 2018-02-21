package com.example.mtalh.hijinnks.CustomeUI.Buttons;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

/**
 * Created by CP on 2/2/2018.
 */

public class Custome_Button_Varela_regular extends android.support.v7.widget.AppCompatButton {
    public Custome_Button_Varela_regular(Context context) {
        super(context);
        applyCustome_fonts(context);
    }

    public Custome_Button_Varela_regular(Context context, AttributeSet attrs) {
        super(context, attrs);
        applyCustome_fonts(context);
    }

    public Custome_Button_Varela_regular(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        applyCustome_fonts(context);
    }

    private void applyCustome_fonts(Context context){
        Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "VarelaRound-Regular.ttf");
        setTypeface(tf ,1);
    }
}
