package graphics;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import trafficlight.Iterate;


public class Graphics {
    
    private static JFrame frame;
    private static Timer timer;
    private static JLabel summLabel;
    private static JLabel firstIntLabel;
    private static JLabel secondIntLabel;
    private static JPanel mainPanel;
    private static JButton nextButton;
    private final Iterate iter;

    public Graphics() throws FileNotFoundException {
        iter = new Iterate();
        frame = new JFrame("Genetic Algorihm");
        mainPanel = new JPanel(null);
        nextButton = new JButton("go");
        summLabel = new JLabel(String.valueOf(iter.lastSumm));
        firstIntLabel = new JLabel(String.valueOf(iter.lastSumm / 2));
        secondIntLabel = new JLabel(String.valueOf(halfSummMaker(
                iter.lastSumm)));
        timer = new Timer(10, timerListener);
        nextButton.addActionListener(buttonListener);
        create();
    }

    private void setAutoSize(JLabel label) {
        label.setSize(label.getPreferredSize());
    }
    
    private int halfSummMaker(int summ) {
        if(summ % 2 == 0) {
            return summ / 2;
        } else {
            return (summ / 2) + 1;
        }
    }

    private void create() {
        nextButton.setLocation(500, 300);
        nextButton.setSize(nextButton.getPreferredSize());
        summLabel.setFont(new Font("Serif", Font.BOLD, 36));
        setAutoSize(summLabel);
        summLabel.setLocation(280, 10);
        firstIntLabel.setFont(new Font("Serif", Font.BOLD, 36));
        setAutoSize(firstIntLabel);
        firstIntLabel.setLocation(210, 70);
        secondIntLabel.setFont(new Font("Serif", Font.BOLD, 36));
        setAutoSize(secondIntLabel);
        secondIntLabel.setLocation(350, 70);
        mainPanel.add(summLabel);
        mainPanel.add(firstIntLabel);
        mainPanel.add(secondIntLabel);
        mainPanel.add(nextButton);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setContentPane(mainPanel);
        frame.setVisible(true);
        firstIntLabel.setVisible(false);
        secondIntLabel.setVisible(false);
    }
    
    private void start() throws FileNotFoundException{
        iter.next();
        summLabel.setText(String.valueOf(iter.lastSumm));
        firstIntLabel.setText(String.valueOf(iter.lastSumm / 2));
        secondIntLabel.setText(String.valueOf(halfSummMaker(
                iter.lastSumm)));
        timer.start();
    }

    ActionListener buttonListener = (ActionEvent e) -> {
        try {//для кнопки
            start();
        } catch (FileNotFoundException ex) {
            System.err.println("error");
        }
    };

    ActionListener timerListener = new ActionListener(){//для таймера
        int x1=210,y1=70,x2=350,y2=70;//координаты лейблов
        @Override
        public void actionPerformed(ActionEvent e){
            firstIntLabel.setVisible(true);
            setAutoSize(firstIntLabel);
            secondIntLabel.setVisible(true);
            setAutoSize(secondIntLabel);
            firstIntLabel.setLocation(x1, y1);
            secondIntLabel.setLocation(x2, y2);
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
