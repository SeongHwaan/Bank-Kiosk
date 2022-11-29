package ui;


import bank.Bank;

import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame{
    static JTextField userText;
    static JPasswordField passwordText;

    
    public static void main() {
    	new LoginFrame();
    }


    public LoginFrame(){
        JFrame LoginFrame = new JFrame("로그인");
        JPanel LoginPanel = new JPanel(null);

        LoginFrame.setSize(480, 720);
        LoginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Dimension frameSize = this.getSize(); // 프레임 사이즈
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); // 모니터 사이즈
        this.setLocation((screenSize.width - frameSize.width)/2, (screenSize.height - frameSize.height)/2); // 화면 중앙

        LoginFrame.add(LoginPanel);

        Image logo = new ImageIcon("src/images/logo.png").getImage();
        ImageIcon logoIcon = new ImageIcon(logo);
        JLabel logoLabel = new JLabel(logoIcon);
        logoLabel.setBounds(0, 120, 480, 144);
        LoginPanel.add(logoLabel);

        JLabel userLabel = new JLabel("아이디");
        userLabel.setBounds(86, 400, 80, 40);
        userLabel.setFont(new Font("", Font.PLAIN, 16));
        LoginPanel.add(userLabel);

        userText = new JTextField();
        userText.setBounds(146, 400, 150, 40);
        LoginPanel.add(userText);

        JLabel passwordLabel = new JLabel("비밀번호");
        passwordLabel.setBounds(86, 440, 80, 40);
        passwordLabel.setFont(new Font("", Font.PLAIN, 16));
        LoginPanel.add(passwordLabel);

        passwordText = new JPasswordField();
        passwordText.setBounds(146, 440, 150, 40);
        LoginPanel.add(passwordText);

        JButton button = new JButton("로그인");
        button.setBounds(300, 400, 100, 81);
        button.setFont(new Font("", Font.BOLD, 16));

        button.addActionListener(e -> {
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
        });

        LoginPanel.add(button);
        LoginFrame.setLocationRelativeTo(null);
        LoginFrame.setVisible(true);
    }
}