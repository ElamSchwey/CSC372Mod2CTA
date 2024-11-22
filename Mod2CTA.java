import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Mod2CTA {
    private double balance;
    private JLabel balanceLabel;

    public Mod2CTA() {
        JFrame frame = new JFrame("Bank Balance App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(250, 400);
        frame.setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1, 5, 5));

        //Label
        balanceLabel = new JLabel("Balance: $0.00");
        balanceLabel.setHorizontalAlignment(SwingConstants.CENTER); //Alignment
        balanceLabel.setFont(new Font("Helvetica", Font.BOLD, 22)); //Fonts
        panel.add(balanceLabel);

        //Deposit
        JButton depositButton = new JButton("Deposit");
        depositButton.setFont(new Font("Helvetica", Font.BOLD, 16));
        panel.add(depositButton);

        //Withdraw
        JButton withdrawButton = new JButton("Withdraw");
        withdrawButton.setFont(new Font("Helvetica", Font.BOLD, 16));
        panel.add(withdrawButton);

        //Exit
        JButton exitButton = new JButton("Exit");
        exitButton.setFont(new Font("Helvetica", Font.BOLD, 16));
        panel.add(exitButton);

        frame.add(panel, BorderLayout.CENTER);

        depositButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = JOptionPane.showInputDialog("Deposit amount:");
                if (input != null && !input.isEmpty()) {
                    try {
                        double amount = Double.parseDouble(input);
                        if (amount > 0) {
                            balance += amount;
                            updateBalanceLabel();
                        } else {
                            JOptionPane.showMessageDialog(frame, "Deposits must be positive.");
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(frame, "Enter a valid number.");
                    }
                }
            }
        });

        withdrawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = JOptionPane.showInputDialog("Withdrawal amount:");
                if (input != null && !input.isEmpty()) {
                    try {
                        double amount = Double.parseDouble(input);
                        if (amount > 0 && amount <= balance) {
                            balance -= amount;
                            updateBalanceLabel();
                        } else if (amount > balance) {
                            JOptionPane.showMessageDialog(frame, "Insufficient funds.");
                        } else {
                            JOptionPane.showMessageDialog(frame, "Withdrawals must be positive.");
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(frame, "Enter a valid number.");
                    }
                }
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "Balance: $" + String.format("%.2f", balance));
                frame.dispose();
            }
        });

        frame.setVisible(true);
    }

    private void updateBalanceLabel() {
        balanceLabel.setText("Balance: $" + String.format("%.2f", balance));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Mod2CTA::new);
    }
}
