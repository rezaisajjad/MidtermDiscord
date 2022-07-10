package ClientController;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

public class RecordSound extends Thread {
    public static String fileName = "voice.wav";
    private final File wavFile = new File(fileName);

    // format of audio file
    private final AudioFileFormat.Type fileType = AudioFileFormat.Type.WAVE;

    // the line from which audio data is captured
    private TargetDataLine line;

    /**
     * Defines an audio format
     */
    AudioFormat getAudioFormat() {
        float sampleRate = 16000;
        int sampleSizeInBits = 8;
        int channels = 2;
        boolean signed = true;
        boolean bigEndian = true;
        return new AudioFormat(sampleRate, sampleSizeInBits, channels, signed, bigEndian);
    }

    /**
     * Captures the sound and record into a WAV file
     */
    public void run() {
        try {
            AudioFormat format = getAudioFormat();
            DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);
            line = (TargetDataLine) AudioSystem.getLine(info);
            line.open(format);
            line.start();   // start capturing
            AudioInputStream ais = new AudioInputStream(line);  // start recording
            AudioSystem.write(ais, fileType, wavFile);

        } catch (LineUnavailableException | IOException ex) {
            ex.printStackTrace();
        }
    }
    public byte[] bytes=new byte[6000];


    /**
     * Closes the target data line to finish capturing and recording
     */
   public void finish() {
        line.stop();
        line.close();
        System.out.println("Finished");
    }

    public void playSound() {
        // Create a new AudioInputStream object.
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(wavFile);
            // Create a new AudioFormat object.

            AudioFormat format = audioInputStream.getFormat();
            // Create a new DataLine.Info object.
            DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);
            // Create a new SourceDataLine object.
            SourceDataLine sourceDataLine = (SourceDataLine) AudioSystem.getLine(info);
            // Open the sourceDataLine.
            sourceDataLine.open(format);
            // Start the sourceDataLine.
            sourceDataLine.start();
            // Create a new byte array.
            byte[] data = new byte[1024];
            // Read the data from the file.
            int count;
            while ((count = audioInputStream.read(data, 0, data.length)) != -1) {
                // Write the data to the SourceDataLine.
                sourceDataLine.write(data, 0, count);
            }
            // Stop the sourceDataLine.
            sourceDataLine.drain();
            // Close the sourceDataLine.
            sourceDataLine.close();
            // Close the AudioInputStream object.
            audioInputStream.close();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }
    //a method to play a sound from a byte array
//    public void playSound(byte[] data) {
//        // Create a new AudioInputStream object.
//        try {
//            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new ByteArrayInputStream(data));
//            // Create a new AudioFormat object.
//
//            // Create a new DataLine.Info object.
//            DataLine.Info info = new DataLine.Info(SourceDataLine.class, this.getAudioFormat());
//            // Create a new SourceDataLine object.
//            SourceDataLine sourceDataLine = (SourceDataLine) AudioSystem.getLine(info);
//            // Open the sourceDataLine.
//            sourceDataLine.open(this.getAudioFormat());
//            // Start the sourceDataLine.
//            sourceDataLine.start();
//            // Create a new byte array.
//            byte[] data1 = new byte[1024];
//            // Read the data from the file.
//            int count;
//            while ((count = audioInputStream.read(data1, 0, data1.length)) != -1) {
//                // Write the data to the SourceDataLine.
//                sourceDataLine.write(data1, 0, count);
//            }
//            // Stop the sourceDataLine.
//            sourceDataLine.drain();
//            // Close the sourceDataLine.
//            sourceDataLine.close();
//            // Close the AudioInputStream object.
//            audioInputStream.close();
//        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
//            e.printStackTrace();
//        }
//    }

    // creates a new thread that waits for 10 milliseconds, then stops recording
//    public void finisher(){
//        Thread stopper  = new Thread (() -> {try {Thread.sleep(1000);} catch (InterruptedException ex){ex.printStackTrace();} this.finish();});
//        stopper.start();
//    }
//    public void runb() {
//        try {
//            AudioFormat format = getAudioFormat();
//            DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);
//            line = (TargetDataLine) AudioSystem.getLine(info);
//            line.open(format);
//            line.start();   // start capturing
//            AudioInputStream ais = new AudioInputStream(line);  // start recording
//            getTargetDataLine().read(bytes, 0,6000);
//            System.out.println(bytes);
//        } catch (LineUnavailableException ex) {
//            ex.printStackTrace();
//        }
//    }
//    public TargetDataLine getTargetDataLine(){
//        return line;
//    }
}