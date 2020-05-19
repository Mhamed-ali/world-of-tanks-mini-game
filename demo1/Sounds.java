package demo1;

import sun.audio.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.FileInputStream;
import java.io.IOException;


/**
 *
 * @author lenovo
 */
public class Sounds  {
    
    public static void main( String[] args)
    {
        JFrame frame=new JFrame();
        frame.setSize(200,200);
        JButton button=new JButton("sound button");
        frame.add(button);
        button.addActionListener(new AL());
        frame.show(true);
        
    }
    public static class AL implements ActionListener{
        public final void actionPerformed(ActionEvent e){
            music();
        }
    }
    public static void music()
    {
        AudioPlayer MGP = AudioPlayer.player;
        AudioStream BGM;
        AudioData MD;       
        ContinuousAudioDataStream loop= null;
        try{
        BGM= new AudioStream(new FileInputStream("2019-04-20_-_Quiet_Time_-_David_Fesliyan.mp3"));
        MD=BGM.getData();
        loop =new ContinuousAudioDataStream(MD);
        }catch(IOException error){}
        MGP.start(loop);
    }
}    