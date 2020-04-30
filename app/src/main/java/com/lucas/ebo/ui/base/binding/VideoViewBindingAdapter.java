package com.lucas.ebo.ui.base.binding;

import android.media.MediaPlayer;
import android.widget.VideoView;

import androidx.annotation.BinderThread;
import androidx.core.app.NotificationCompat;
import androidx.databinding.BindingAdapter;

import com.lucas.ebo.R;

/**
 * Created by lucas
 * Date: 2020/4/27 15:53
 */
public class VideoViewBindingAdapter {

    @BindingAdapter(value = {"videoAssetPath"}, requireAll = false)
    public static void loadVideoAssetPath(VideoView videoView, String videoPath)
    {
//        String path = "file:///android_asset/" + videoPath;
        videoView.setVideoPath(videoPath);
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                videoView.start();
            }
        });
    }
}
