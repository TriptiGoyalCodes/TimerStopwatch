import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TimerStopwatch {
    private JFrame frame;
    private JLabel timerLabel;
    private JTextField durationInput;
    private JLabel stopwatchLabel;
    private Timer timer;
    private int stopwatchSeconds;

    public TimerStopwatch() {
        frame = new JFrame("Timer and Stopwatch");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());

        // Timer panel
        JPanel timerPanel = new JPanel();
        timerPanel.setLayout(new BoxLayout(timerPanel, BoxLayout.Y_AXIS));

        JLabel timerTitleLabel = new JLabel("Timer");
        timerLabel = new JLabel();
        durationInput = new JTextField(10);
        JButton startTimerButton = new JButton("Start");
        startTimerButton.addActionListener(new TimerActionListener());

        timerPanel.add(timerTitleLabel);
        timerPanel.add(durationInput);
        timerPanel.add(startTimerButton);
        timerPanel.add(timerLabel);

        // Stopwatch panel
        JPanel stopwatchPanel = new JPanel();
        stopwatchPanel.setLayout(new BoxLayout(stopwatchPanel, BoxLayout.Y_AXIS));

        JLabel stopwatchTitleLabel = new JLabel("Stopwatch");
        stopwatchLabel = new JLabel("00:00:00");
        JButton startStopwatchButton = new JButton("Start");
        startStopwatchButton.addActionListener(new StopwatchActionListener());
        JButton stopStopwatchButton = new JButton("Stop");
        stopStopwatchButton.addActionListener(new StopwatchStopActionListener());

        stopwatchPanel.add(stopwatchTitleLabel);
        stopwatchPanel.add(startStopwatchButton);
        stopwatchPanel.add(stopStopwatchButton);
        stopwatchPanel.add(stopwatchLabel);

        // Add panels to the main frame
        frame.add(timerPanel);
        frame.add(stopwatchPanel);

        frame.pack();
        frame.setVisible(true);
    }

    private void startTimer(final int duration) {
        timer = new Timer(1000, new ActionListener() {
            int remainingTime = duration;

            public void actionPerformed(ActionEvent e) {
                if (remainingTime >= 0) {
                    timerLabel.setText("Time remaining: " + remainingTime + " seconds");
                    remainingTime--;
                } else {
                    timerLabel.setText("Timer completed!");
                    timer.stop();
                }
            }
        });

        timer.start();
    }

    private void startStopwatch() {
        timer = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                stopwatchSeconds++;
                stopwatchLabel.setText(formatTime(stopwatchSeconds));
            }
        });

        timer.start();
    }

    private void stopStopwatch() {
        timer.stop();
    }

    private String formatTime(int seconds) {
        int hours = seconds / 3600;
        int minutes = (seconds % 3600) / 60;
        int remainingSeconds = seconds % 60;

        return String.format("%02d:%02d:%02d", hours, minutes, remainingSeconds);
    }

    private class TimerActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            int duration = Integer.parseInt(durationInput.getText());
            startTimer(duration);
        }
    }

    private class StopwatchActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            stopwatchSeconds = 0;
            startStopwatch();
        }
    }

    private class StopwatchStopActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            stopStopwatch();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new TimerStopwatch();
            }
        });
    }
}
