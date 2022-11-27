package ui;


import bank.Bank;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrame extends JFrame{
    static JTextField userText;
    static JPasswordField passwordText;

    
    public static void main() {
    	new LoginFrame();
    }


    public LoginFrame(){
        JFrame LoginFrame = new JFrame("로그인");
        JPanel LoginPanel = new JPanel(null);

        LoginFrame.setSize(270, 155);
        LoginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        LoginFrame.add(LoginPanel);
        JLabel userLabel = new JLabel("User");
        userLabel.setBounds(10, 20, 80, 25);
        LoginPanel.add(userLabel);

        userText = new JTextField();
        userText.setBounds(100, 20, 165, 25);
        LoginPanel.add(userText);

        JLabel passwordlabel = new JLabel("Password");
        passwordlabel.setBounds(10, 50, 80, 25);
        LoginPanel.add(passwordlabel);

        passwordText = new JPasswordField();
        passwordText.setBounds(100, 50, 165, 25);
        LoginPanel.add(passwordText);

        JButton button = new JButton("로그인");
        button.setBounds(5, 80, 260, 40);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = userText.getText();
                String pw = String.valueOf(passwordText.getPassword());
                if (!WindowBuilder.bank.login(id, pw)) {
                    if (Bank.loginUser == null) {
                        JOptionPane.showMessageDialog(null,
                                "사용자를 찾을 수 없습니다.",
                                "시스템", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    if (!Bank.loginUser.password.contentEquals(pw)) {
                        Bank.loginUser = null;
                        JOptionPane.showMessageDialog(null,
                                "비밀번호가 잘못되었습니다.",
                                "시스템", JOptionPane.ERROR_MESSAGE);
                    }
                }
                else {
                	LoginFrame.setVisible(false);
                    WindowBuilder.startGUI();  
                }
            }
        });

        LoginPanel.add(button);
        LoginFrame.setLocationRelativeTo(null);
        LoginFrame.setVisible(true);
    }
}