import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StopWatchGUI extends JFrame implements ActionListener {
    private JLabel timerLabel;
    private JButton startButton, stopButton, resetButton;
    private Timer timer;
    private int seconds = 0, minutes = 0, hours = 0;

    public StopWatchGUI() {
        setTitle("Stopwatch");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        getContentPane().setBackground(Color.LIGHT_GRAY);

        timerLabel = new JLabel("00:00:00", JLabel.CENTER);
        timerLabel.setFont(new Font("Verdana", Font.PLAIN, 45 ));
        timerLabel.setPreferredSize(new Dimension(300, 100));
        timerLabel.setVerticalAlignment(JLabel.CENTER);

        Image icon = Toolkit.getDefaultToolkit().getImage("icon.png");    
        setIconImage(icon);  

        startButton = new JButton("Start");
        stopButton = new JButton("Stop");
        resetButton = new JButton("Reset");

        startButton.addActionListener(this);
        stopButton.addActionListener(this);
        resetButton.addActionListener(this);

        startButton.setFocusable(false);
        stopButton.setFocusable(false);
        resetButton.setFocusable(false);

        timer = new Timer(1000, this);

        add(timerLabel);
        add(startButton);
        add(stopButton);
        add(resetButton);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startButton) {
            timer.start();
        } else if (e.getSource() == stopButton) {
            timer.stop();
        } else if (e.getSource() == resetButton) {
            timer.stop();
            seconds = 0;
            minutes = 0;
            hours = 0;
        } else if (e.getSource() == timer) {
            seconds++;
            if (seconds == 60) {
                seconds = 0;
                minutes++;
                if (minutes == 60) {
                    minutes = 0;
                    hours++;
                }
            }
        }

        // Format the time and update the label
        String time = String.format("%02d:%02d:%02d", hours, minutes, seconds);
        timerLabel.setText(time);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new StopWatchGUI();
        });
    }
}
