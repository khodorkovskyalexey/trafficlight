package graphics;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import javax.swing.Timer;
import trafficlight.Iterate;

public final class Animation {
    
    private Timer timer;

    public Animation() throws FileNotFoundException {
        setDefaultLocation();
        timer = new Timer(1, timerListener);
        Iterate iterate = new Iterate();

        setSumm(iterate.simulator.summ);
        start();
    }
    
    public void start() throws FileNotFoundException {
        Iterate iterate = new Iterate(0);
        setSumm(iterate.simulator.summ);
        timer.start();
    }
    
    private void setDefaultLocation() {
        Window.summLabel.setLocation(280, 10);
        Window.firstIntLabel.setLocation(210, 70);
        Window.secondIntLabel.setLocation(350, 70);
        Window.nextButton.setLocation(500, 70);
    }

    private void setLocation(Point[] point) {
        Window.firstIntLabel.setLocation(point[0]);
        Window.secondIntLabel.setLocation(point[1]);
    }
    
    private void setSumm(int summ) {
        Window.summLabel.setText(String.valueOf(summ));
        Window.firstIntLabel.setText(String.valueOf(summ / 2));
        Window.secondIntLabel.setText(String.valueOf(halfSummMaking(summ)));
    }
    
    private int halfSummMaking(int summ) {
        if(summ % 2 == 0) {
            return summ;
        } else {
            return ++summ;
        }
    }
    
    private Point[] newPoint(int count) {
        Point[] point = new Point[count];
        for (int i = 0; i < count; i++) {
            point[i] = new Point();
        }
        return point;
    }
    
    ActionListener timerListener = new ActionListener(){//для таймера
        int x1=210,y1=70,x2=350,y2=70;//координаты лейблов
        @Override
            public void actionPerformed(ActionEvent e){
                Point[] point = newPoint(2);
                point[0].setLocation(x1, y1);
                point[1].setLocation(x2, y2);
                setLocation(point);
                x1--; y1++;
                x2++;y2++;
                if(y1 == 150) {
                timer.stop();
                x1 = 210;
                y1 = 70;
                x2 = 350;
                y2 = 70;
            }
        }
    };

}
