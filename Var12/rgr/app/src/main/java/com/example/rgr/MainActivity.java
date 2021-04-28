package com.example.rgr;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.MediaController;
import android.widget.SeekBar;
import android.widget.ToggleButton;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity implements
        CompoundButton.OnCheckedChangeListener {
    boolean loopVideo = false;
    VideoView videoView;
    SeekBar seekBar;
    Handler updateHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Установливаем свой путь к файлу
        String path = getApplicationInfo().dataDir + "/video.mp4";
        videoView = (VideoView) findViewById(R.id.VideoView);
        videoView.setVideoPath(path);
        videoView.setMediaController(new MediaController(this));
        videoView.requestFocus(0);
        videoView.seekTo(1);

        // Получаем seekbar и устанавливаем обработчик
        seekBar = (SeekBar) findViewById(R.id.seekBar2);
        seekBar.setOnSeekBarChangeListener(seekBarChangeListener);

        // Событие при загрузке видео и готовности к воспроизведению в VideoView
        videoView.setOnPreparedListener(mediaPlayer -> {
            seekBar.setProgress(0);
            seekBar.setMax(videoView.getDuration());
            updateHandler.postDelayed(updateVideoTime, 100);
        });

        // Получение кнопки переключение и установка обработчика
        ToggleButton toogleButton = (ToggleButton) findViewById(R.id.toggleButton);
        toogleButton.setOnCheckedChangeListener(this);
    }

    // Обновление позиции SeekBar относительно VideoView каждые 100мл
    private Runnable updateVideoTime = new Runnable() {
        @Override
        public void run() {
            long currentPosition = videoView.getCurrentPosition();
            seekBar.setProgress((int) currentPosition);
            updateHandler.postDelayed(this, 100);
        }
    };

    // Обновление позиции VideoView при изменении SeekBar нажатием
    private SeekBar.OnSeekBarChangeListener seekBarChangeListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {}

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
            videoView.seekTo(seekBar.getProgress());
        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            videoView.seekTo(seekBar.getProgress());
        }
    };

    // События кнопок управления видео
    public void stop(View view) {
        if (videoView.canPause()) {
            videoView.pause();
        }
    }

    public void play(View view) {
        videoView.start();
    }

    public void toStart(View view) {
        videoView.pause();
        videoView.seekTo(0);
    }

    public void toEnd(View view) {
        videoView.pause();
        videoView.seekTo(videoView.getDuration());
    }

    public void back5sec(View view) {
        videoView.seekTo(videoView.getCurrentPosition() - 5000);
    }

    public void next5sec(View view) {
        videoView.seekTo(videoView.getCurrentPosition() + 5000);
    }

    // переключение кнопки репита видео
    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) videoView.setOnCompletionListener(mp -> videoView.start());
        else videoView.setOnCompletionListener(null);
    }
}