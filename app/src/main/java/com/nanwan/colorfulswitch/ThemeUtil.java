package com.nanwan.colorfulswitch;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import colorful.Colorful;

/**
 * Created by nan on 2016/5/20.
 */
public class ThemeUtil {
    private SharedPreferences mSp;
    private static final String SAVE_THEME = "save_theme";
    private Colorful mColorful;

    public ThemeUtil(Context mContext, Colorful mColorful) {
        this.mColorful = mColorful;
        mSp = mContext.getSharedPreferences(SAVE_THEME, Activity.MODE_PRIVATE);
    }


    public void setTheme() {
        SharedPreferences.Editor editor = mSp.edit();
        boolean isNight = mSp.getBoolean("isNight", false);
        if (isNight) {
            mColorful.setTheme(R.style.DayTheme);
            editor.putBoolean("isNight", false);
        } else {
            mColorful.setTheme(R.style.NightTheme);
            editor.putBoolean("isNight", true);
        }
        editor.commit();
    }

    public void setThemeOnCreate() {
        boolean isNight = mSp.getBoolean("isNight", false);
        if (!isNight) {
            mColorful.setTheme(R.style.DayTheme);
        } else {
            mColorful.setTheme(R.style.NightTheme);
        }
    }
}
