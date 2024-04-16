package com.example.mediaplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    MediaPlayer player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //we create the player every time we press play  so we won't take too many resources
    public void play(View v){
        if(player==null){
            player= MediaPlayer.create(this,R.raw.minimalism);
            player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    stopPlayer();
                }
            });
        }
        player.start();
    }
    public void pause(View v){
        if(player!=null){
            player.pause();
        }
    }
    public void stop(View v){
        stopPlayer();

    }

    //this special method will release the system resources
    private void stopPlayer(){
        if(player!=null){
            player.release();
            player = null;
            Toast.makeText(this, "Media Player released", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onStop(){
        super.onStop();
        stopPlayer();
    }

}

