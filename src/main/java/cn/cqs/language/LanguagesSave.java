package cn.cqs.language;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Locale;

/**
 *    author : Android 轮子哥
 *    github : https://github.com/getActivity/MultiLanguages
 *    time   : 2019/05/03
 *    desc   : 配置保存类
 */
final class LanguagesSave {

    private static final String KEY_LANGUAGE = "key_language";
    private static final String KEY_COUNTRY = "key_country";

    private static String sSharedPreferencesName = "language_setting";
    private static Locale sCurrentLanguage;

    static void setSharedPreferencesName(String name) {
        sSharedPreferencesName = name;
    }

    private static SharedPreferences getSharedPreferences(Context context) {
        return context.getSharedPreferences(sSharedPreferencesName, Context.MODE_PRIVATE);
    }

    static void saveAppLanguage(Context context, Locale locale) {
        sCurrentLanguage = locale;
        getSharedPreferences(context).edit()
                .putString(KEY_LANGUAGE, locale.getLanguage())
                .putString(KEY_COUNTRY, locale.getCountry())
                .apply();
    }

    static Locale getAppLanguage(Context context) {
        if (sCurrentLanguage == null) {
            String language = getSharedPreferences(context).getString(KEY_LANGUAGE, null);
            String country = getSharedPreferences(context).getString(KEY_COUNTRY, null);
            if (language != null && !"".equals(language)) {
                sCurrentLanguage = new Locale(language, country);
            } else {
                sCurrentLanguage = Locale.getDefault();
            }
        }
        return sCurrentLanguage;
    }

    static void clearLanguage(Context context) {
        sCurrentLanguage = LanguagesChange.getSystemLanguage();
        getSharedPreferences(context).edit().remove(KEY_LANGUAGE).remove(KEY_COUNTRY).apply();
    }
}
