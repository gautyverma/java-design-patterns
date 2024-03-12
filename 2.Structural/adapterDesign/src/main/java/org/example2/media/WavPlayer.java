package org.example2.media;

public class WavPlayer implements AdvanceMediaPlayer {
  @Override
  public void playMp4(String file) {
    // Do Nothing
  }

  @Override
  public void playWav(String file) {
    System.out.println("Wav File " + file + " Playing...");
  }
}
