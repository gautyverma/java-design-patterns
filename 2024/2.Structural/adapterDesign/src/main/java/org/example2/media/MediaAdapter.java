package org.example2.media;

public class MediaAdapter implements MediaPlayer {
  AdvanceMediaPlayer advancePlayer;

  public MediaAdapter(String format) {
    if (format.equalsIgnoreCase("MP4")) {
      advancePlayer = new Mp4Player();
    } else if (format.equalsIgnoreCase("WAV")) {
      advancePlayer = new WavPlayer();
    }
  }

  @Override
  public void play(String format, String file) {
    if (format.equalsIgnoreCase("MP4")) {
      advancePlayer.playMp4(file);
    } else if (format.equalsIgnoreCase("WAV")) {
      advancePlayer.playWav(file);
    }
  }
}
