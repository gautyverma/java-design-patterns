package org.example2;

import org.example2.media.AudioPlayer;

public class Main {
  public static void main(String[] args) {
    AudioPlayer audioPlayer = new AudioPlayer();
    audioPlayer.play("mp3","music.mp3");
    audioPlayer.play("mp4","music.mp4");
    audioPlayer.play("wav","music.wav");
    audioPlayer.play("avi","music.avi");
  }
}
