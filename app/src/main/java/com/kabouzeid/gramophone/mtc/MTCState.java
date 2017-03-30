package com.kabouzeid.gramophone.mtc;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;

import com.kabouzeid.gramophone.App;

/**
 * Created by SimonFi on 15/03/2017.
 */

public class MTCState {
    private static boolean shouldAutoPlay = true;

    private Context context;
    private AudioManager audioManager;

    public MTCState(Context context) {
        this.context = context;
        this.audioManager = (AudioManager)context.getSystemService(Context.AUDIO_SERVICE);
    }

    public void AvChannelEnter() {
        this.audioManager.setParameters("av_channel_enter=sys");
        Log.d("Phonograph::MTCState", "AvChannelEnter");
    }

    public void AvChannelExit() {
        this.audioManager.setParameters("av_channel_exit=sys");
        Log.d("Phonograph::MTCState", "AvChannelExit");
    }

    public void setIsPlaying(boolean isPlaying) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putBoolean("mtcIsPlaying", isPlaying).apply();
    }

    public boolean getIsPlaying() {
        boolean isPlaying = PreferenceManager.getDefaultSharedPreferences(context).getBoolean("mtcIsPlaying", false);
        return isPlaying;
    }

    public static boolean getShouldAutoPlay() {
        return MTCState.shouldAutoPlay;
    }

    public static void setShouldAutoPlay(boolean shouldAutoPlay) {
        MTCState.shouldAutoPlay = shouldAutoPlay;
    }
}
