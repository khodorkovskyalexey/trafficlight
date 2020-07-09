package graphics;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public final class Window {
    
    public static JFrame frame;
    public static JLabel summLabel;
    public static JLabel firstIntLabel;
    public static JLabel secondIntLabel;
    public static JPanel mainPanel;
    public static JButton nextButton;
    private Animation animation;
    
    public Window() throws FileNotFoundException {
        frame = new JFrame("Genetic Algotihm");
        summLabel = new JLabel();
        firstIntLabel = new JLabel();
        secondIntLabel = new JLabel();
        mainPanel = new JPanel();
        nextButton = new JButton("next");
        winCreate();
        animation = new Animation();
    }
    
    private void setView(JLabel label) {
        label.setSize(label.getPreferredSize());
        label.setFont(new Font("Serif",Font.PLAIN,36));
    }
    
    private void setView(JButton button) {
        button.setSize(button.getPreferredSize());
        button.setFont(new Font("Serif",Font.PLAIN,36));
    }
    
    private void winCreate() {
        setView(summLabel);
        setView(firstIntLabel);
        setView(secondIntLabel);
        setView(nextButton);
        mainPanel.add(summLabel);
        mainPanel.add(firstIntLabel);
        mainPanel.add(secondIntLabel);
        mainPanel.add(nextButton);
        mainPanel.setVisible(true);
        frame.setContentPane(mainPanel);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setVisible(true);
        nextButton.addActionListener(buttonListener);
    }
    
    ActionListener buttonListener = new ActionListener(){

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                animation.start();
            } catch (FileNotFoundException ex) {
                System.err.println("error");
            }
        }
    };
    
}
