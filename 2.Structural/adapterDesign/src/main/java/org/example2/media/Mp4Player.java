package org.example2.media;

public class Mp4Player implements AdvanceMediaPlayer {
  @Override
  public void playMp4(String file) {
    System.out.println("MP4 File " + file + " Playing...");
  }

  @Override
  public void playWav(String file) {
    // Do Nothin
  }
}
