package com.example.rasam.bookhubadmins.maintanance.sharedPrefs;

import android.content.Context;
import android.content.SharedPreferences;


/**
 * Created by mediaHamrahHabibpoor on 2/11/2017.
 */

public class SharedPreferencesManager {
    // TODO: CHANGE THIS TO SOMETHING MEANINGFUL
    private static final String SETTINGS_NAME = "default_settings";
    private static SharedPreferencesManager sSharedPrefs;
    private SharedPreferences mPref;
    private SharedPreferences.Editor mEditor;
    private boolean mBulkUpdate = false;

    /**
     * Class for keeping all the keys used for shared preferences in one place.
     */
    public static class Key {
        /* Recommended naming convention:
         * ints, floats, doubles, longs:
         * SAMPLE_NUM or SAMPLE_COUNT or SAMPLE_INT, SAMPLE_LONG etc.
         *
         * boolean: IS_SAMPLE, HAS_SAMPLE, CONTAINS_SAMPLE
         * 
         * String: SAMPLE_KEY, SAMPLE_STR or just SAMPLE
         */

        //profile detail

        public static final String USER_NAME = "user_name";
        public static final String USER_GENDER = "user_gender";
        public static final String USER_BIRTH_DATE = "user_birth_date";
        public static final String USER_CITY = "user_city";
        public static final String USER_IMAGE_LINK = "user_image_link";
        public static final String USER_PHONE_NUMBER = "user_phone_number";
        public static final String CAMPAIGN_GAME_ID = "campaign_game_id";

        //for music setting
        public static final String IS_BACKGROUND_MUSIC_ACTIVE = "is_background_music_active";


        public static final String IS_GAME_MUSIC_ACTIVE = "is_game_music_active";


        public static final String IS_BUTTON_EFFECT_ACTIVE = "is_button_effect_active";


        //for tour guide
        public static final String TUTORIAL_STATE  = "tutorials_state";
        public static final String IS_TUTORIAL_PASSED  = "isPassed";


        //for invite

        public static final String INVITE_VERIFY_CODE = "invite_verify_code";


        //for db transaction
        public static final String CITIES_SAVED_FLAG = "cities_saved_flag";
        public static final String CITIES_SAVED_FLAG_TRUE = "cities_saved_flag_true";

        //operator
        public static final String OPERATOR_TYPE = "operator";

        //userType
        public static final String USER_TYPE = "userType";

    }

    private SharedPreferencesManager(Context context) {
        mPref = context.getSharedPreferences(SETTINGS_NAME, Context.MODE_PRIVATE);
    }



    public static SharedPreferencesManager getInstance(Context context) {
        if (sSharedPrefs == null) {
            sSharedPrefs = new SharedPreferencesManager(context.getApplicationContext());
        }
        return sSharedPrefs;
    }

    public static SharedPreferencesManager getInstance() {
        if (sSharedPrefs != null) {
            return sSharedPrefs;
        }

        //Option 1:
        throw new IllegalArgumentException("Should use getInstance(Context) at least once before using this method.");

        //Option 2:
        // Alternatively, you can create a new instance here
        // with something like this:
        // getInstance(MyCustomApplication.getAppContext());
    }

    public void put(String key, String val) {
        doEdit();
        mEditor.putString(key, val);
        doCommit();
    }

    public void put(String key, int val) {
        doEdit();
        mEditor.putInt(key, val);
        doCommit();
    }

    public void put(String key, boolean val) {
        doEdit();
        mEditor.putBoolean(key, val);
        doCommit();
    }

    public void put(String key, float val) {
        doEdit();
        mEditor.putFloat(key, val);
        doCommit();
    }

    /**
     * Convenience method for storing doubles.
     * <p>
     * There may be instances where the accuracy of a double is desired.
     * SharedPreferences does not handle doubles so they have to
     * cast to and from String.
     *
     * @param key The name of the preference to store.
     * @param val The new value for the preference.
     */
    public void put(String key, double val) {
        doEdit();
        mEditor.putString(key, String.valueOf(val));
        doCommit();
    }

    public void put(String key, long val) {
        doEdit();
        mEditor.putLong(key, val);
        doCommit();
    }

    public String getString(String key, String defaultValue) {
        return mPref.getString(key, defaultValue);
    }

    public String getString(String key) {
        return mPref.getString(key, null);
    }

    public int getInt(String key) {
        return mPref.getInt(key, 0);
    }

    public int getInt(String key, int defaultValue) {
        return mPref.getInt(key, defaultValue);
    }

    public long getLong(String key) {
        return mPref.getLong(key, 0);
    }

    public long getLong(String key, long defaultValue) {
        return mPref.getLong(key, defaultValue);
    }

    public float getFloat(String key) {
        return mPref.getFloat(key, 0);
    }

    public float getFloat(String key, float defaultValue) {
        return mPref.getFloat(key, defaultValue);
    }

    /**
     * Convenience method for retrieving doubles.
     * <p>
     * There may be instances where the accuracy of a double is desired.
     * SharedPreferences does not handle doubles so they have to
     * cast to and from String.
     *
     * @param key The name of the preference to fetch.
     */
    public double getDouble(String key) {
        return getDouble(key, 0);
    }

    /**
     * Convenience method for retrieving doubles.
     * <p>
     * There may be instances where the accuracy of a double is desired.
     * SharedPreferences does not handle doubles so they have to
     * cast to and from String.
     *
     * @param key The name of the preference to fetch.
     */
    public double getDouble(String key, double defaultValue) {
        try {
            return Double.valueOf(mPref.getString(key, String.valueOf(defaultValue)));
        } catch (NumberFormatException nfe) {
            return defaultValue;
        }
    }

    public boolean getBoolean(String key, boolean defaultValue) {
        return mPref.getBoolean(key, defaultValue);
    }

    public boolean getBoolean(String key) {
        return mPref.getBoolean(key, false);
    }

    /**
     * Remove keys from SharedPreferences.
     *
     * @param keys The name of the key(s) to be removed.
     */
    public void remove(String... keys) {
        doEdit();
        for (String key : keys) {
            mEditor.remove(key);
        }
        doCommit();
    }

    /**
     * Remove all keys from SharedPreferences.
     */
    public void clear() {
        doEdit();
        mEditor.clear();
        doCommit();
    }

    public void edit() {
        mBulkUpdate = true;
        mEditor = mPref.edit();
    }

    public void commit() {
        mBulkUpdate = false;
        mEditor.commit();
        mEditor = null;
    }

    private void doEdit() {
        if (!mBulkUpdate && mEditor == null) {
            mEditor = mPref.edit();
        }
    }

    private void doCommit() {
        if (!mBulkUpdate && mEditor != null) {
            mEditor.commit();
            mEditor = null;
        }
    }

    public boolean userDataExist() {
        return !(SharedPreferencesManager.getInstance().getString(Key.USER_NAME) == null
                & SharedPreferencesManager.getInstance().getString(Key.USER_GENDER) == null
                & SharedPreferencesManager.getInstance().getString(Key.USER_BIRTH_DATE) == null
                & SharedPreferencesManager.getInstance().getString(Key.USER_BIRTH_DATE) == null
                & SharedPreferencesManager.getInstance().getString(Key.USER_CITY) == null);

    }



}