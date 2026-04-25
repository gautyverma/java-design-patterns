package org.example2.media;

public class AudioPlayer implements MediaPlayer {
  MediaAdapter mediaAdapter;

  @Override
  public void play(String format, String file) {
    if (format.equalsIgnoreCase("MP3")) {
      System.out.println("MP3 file " + file + " Playing...");
    } else if (format.equalsIgnoreCase("WAV") || format.equalsIgnoreCase("MP4")) {
      mediaAdapter = new MediaAdapter(format);
      mediaAdapter.play(format, file);
    } else {
      System.out.println("Format not supported");
    }
  }
}
