package com.kabouzeid.gramophone.mtc;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.WakefulBroadcastReceiver;
import android.widget.Toast;

import com.kabouzeid.gramophone.helper.MusicPlayerRemote;
import com.kabouzeid.gramophone.service.MusicService;
import com.kabouzeid.gramophone.ui.activities.MainActivity;

/**
 * Created by SimonFi on 15/03/2017.
 */

public class MTCKeysBroadcastReceiver extends BroadcastReceiver {

    public static final int KEYCODE_PLAYPAUSE = 3;
    public static final int KEYCODE_PREV = 45;
    public static final int KEYCODE_NEXT = 46;
    public static final int KEYCODE_MUSIC = 84;

    public IntentFilter getIntentFilter() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.microntek.irkeyDown");
        intentFilter.addAction("com.microntek.irkeyUp");
        return intentFilter;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        int keyCode = intent.getIntExtra("keyCode", 0);

        if (/*!action.equals("com.microntek.irkeyDown") && */!action.equals("com.microntek.irkeyUp")) {
            return;
        }

        switch (keyCode) {
            case KEYCODE_PLAYPAUSE:
                Toast.makeText(context, "Play/Pause", Toast.LENGTH_SHORT).show();
                if (MusicPlayerRemote.isPlaying()) {
                    MusicPlayerRemote.pauseSong();
                } else {
                    MusicPlayerRemote.resumePlaying();
                }
                break;
            case KEYCODE_PREV:
                Toast.makeText(context, "Previous", Toast.LENGTH_SHORT).show();
                MusicPlayerRemote.playPreviousSong();
                break;
            case KEYCODE_NEXT:
                Toast.makeText(context, "Next", Toast.LENGTH_SHORT).show();
                MusicPlayerRemote.playNextSong();
                break;
            case KEYCODE_MUSIC:
                Toast.makeText(context, "Music", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(context, MainActivity.class);
                i.setAction(Intent.ACTION_MAIN);
                i.addCategory(Intent.CATEGORY_LAUNCHER);
                context.startActivity(i);
                break;
            default:
                Toast.makeText(context, String.format("Phonograph: Unhandled KeyCode %s", keyCode), Toast.LENGTH_SHORT).show();
                return;
        }
    }
}