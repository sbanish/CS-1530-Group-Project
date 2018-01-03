import java.awt.*;
import java.util.*;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.*;

public class Timer extends JPanel{
    JLabel clock;
    long secondsElapsed;
    Boolean continueToRun;

    public Timer() throws Exception{
        secondsElapsed = 0;
        clock = new JLabel("Time Elapsed: " + convertToHHMMSS(secondsElapsed));
        add(clock);
        continueToRun = true;

        Thread t = new Thread(() -> {
            try {
                while(continueToRun){
                    Thread.sleep(1000);
                    secondsElapsed++;
                    clock.setText("Time Elapsed: " + convertToHHMMSS(secondsElapsed));
                }
        	}
            catch (InterruptedException iex) {}
        });
        t.start();
    }

    public Timer(long secs) throws Exception{
        secondsElapsed = secs;
        clock = new JLabel("Time Elapsed: " + convertToHHMMSS(secondsElapsed));
        add(clock);
        continueToRun = true;

        Thread t = new Thread(() -> {
            try {
                while(continueToRun){
                    Thread.sleep(1000);
                    secondsElapsed++;
                    clock.setText("Time Elapsed: " + convertToHHMMSS(secondsElapsed));
                }
        	}
            catch (InterruptedException iex) {}
        });
        t.start();
    }

    public void stopTimer(){
        continueToRun = false;
    }

    private String convertToHHMMSS(Long secondsElapsed){
        final long HOUR = 3600;
        final long MIN = 60;
        final long SECOND = 1;

        String hrs = "00";
        String mins = "00";
        String secs = "00";

        if(secondsElapsed > HOUR){
            Long h = secondsElapsed/HOUR;
            hrs = Long.toString(h);
            if(h < 10){hrs = '0' + hrs;}
            secondsElapsed = secondsElapsed - (h * HOUR);
        }
        if(secondsElapsed > MIN){
            Long m = secondsElapsed/MIN;
            mins = Long.toString(m);
            if(m < 10){mins = '0' + mins;}
            secondsElapsed = secondsElapsed - (m * MIN);
        }
        if(secondsElapsed >= SECOND){
            secs = Long.toString(secondsElapsed);
            if(secondsElapsed < 10){secs = '0' + secs;}
        }

        return hrs+':'+mins+':'+secs;
    }
}
