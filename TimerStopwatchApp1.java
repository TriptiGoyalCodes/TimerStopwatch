import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

public class TimerStopwatchApp1 extends JFrame {
    private JLabel timeLabel;
    private JButton stopwatchButton;
    private Font f1 = new Font("Arial", Font.PLAIN, 70);
    private DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
    private Timer stopwatchTimer;
    private int stopwatchSecond, stopwatchMinute;
    private String stopwatchFormattedSecond, stopwatchFormattedMinute;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                TimerStopwatchApp1 timerStopwatchApp = new TimerStopwatchApp1();
                timerStopwatchApp.startApp();
            }
        });
    }

    public void startApp() {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }

    private void createAndShowGUI() {
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(2, 1));

        JPanel clockPanel = new JPanel(new FlowLayout());
        JPanel stopwatchPanel = new JPanel(new FlowLayout());

        timeLabel = new JLabel();
        timeLabel.setFont(f1);
        updateTimeLabel();
        clockPanel.add(timeLabel);

        stopwatchButton = new JButton("Stopwatch");
        stopwatchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setupStopwatchUI();
            }
        });
        stopwatchPanel.add(stopwatchButton);

        add(clockPanel);
        add(stopwatchPanel);

        setVisible(true);
        startClock();
    }

    private void startClock() {
        Timer clockTimer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateTimeLabel();
            }
        });
        clockTimer.start();
    }

    private void updateTimeLabel() {
        LocalTime currentTime = LocalTime.now();
        String formattedTime = currentTime.format(timeFormatter);
        timeLabel.setText(formattedTime);
    }

    private void setupStopwatchUI() {
        JFrame stopwatchWindow = new JFrame("Stopwatch");
        stopwatchWindow.setSize(400, 200);
        stopwatchWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        stopwatchWindow.setLayout(new FlowLayout());

        JButton startButton = new JButton("Start");
        stopwatchWindow.add(startButton);

        JButton stopButton = new JButton("Stop");
        stopwatchWindow.add(stopButton);

        JButton resetButton = new JButton("Reset");
        stopwatchWindow.add(resetButton);

        JLabel counterLabel = new JLabel();
        counterLabel.setFont(f1);
        stopwatchWindow.add(counterLabel);

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startStopwatch(counterLabel);
            }
        });

        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stopStopwatch();
            }
        });

        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetStopwatch(counterLabel);
            }
        });

        stopwatchWindow.setVisible(true);
    }

    private void startStopwatch(JLabel counterLabel) {
        stopwatchSecond = 0;
        stopwatchMinute = 0;
        stopwatchFormattedSecond = "00";
        stopwatchFormattedMinute = "00";
        counterLabel.setText(stopwatchFormattedMinute + ":" + stopwatchFormattedSecond);

        stopwatchTimer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stopwatchSecond++;
                if (stopwatchSecond == 60) {
                    stopwatchSecond = 0;
                    stopwatchMinute++;
                }
                stopwatchFormattedSecond = new DecimalFormat("00").format(stopwatchSecond);
                stopwatchFormattedMinute = new DecimalFormat("00").format(stopwatchMinute);
                counterLabel.setText(stopwatchFormattedMinute + ":" + stopwatchFormattedSecond);
            }
        });
        stopwatchTimer.start();
    }

    private void stopStopwatch() {
        if (stopwatchTimer != null) {
            stopwatchTimer.stop();
        }
    }

    private void resetStopwatch(JLabel counterLabel) {
        stopStopwatch();
        stopwatchSecond = 0;
        stopwatchMinute = 0;
        stopwatchFormattedSecond = "00";
        stopwatchFormattedMinute = "00";
        counterLabel.setText(stopwatchFormattedMinute + ":" + stopwatchFormattedSecond);
    }
}
