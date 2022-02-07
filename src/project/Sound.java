package project;
import java.io.*;
import sun.audio.*;
public class Sound 
{
    private String path_file;
     InputStream in;
     AudioStream as;


    public String getPath_file() {
        return path_file;
    }
    public Sound(String path_file)  throws FileNotFoundException, IOException{
        this.path_file=path_file;
         try {
        in = new FileInputStream(path_file);
        as = new AudioStream(in);
    } catch (FileNotFoundException e) { 
        System.out.println("Audio file not found.");
    } catch (IOException e) {
        System.out.println("Incorrect input.");
    }
    }
    public void play_sound(){

    AudioPlayer.player.start(as);
    }
   public void Stop_sound(){
       AudioPlayer.player.stop(as);

   }
}
