package ui;

<<<<<<< HEAD
import bank.Bank;

=======
>>>>>>> GUI
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrame extends JFrame{
    JTextField userText;
    JPasswordField passwordText;
<<<<<<< HEAD
    
    public static void main(String[] args) {
    	LoginFrame a = new LoginFrame();
    	a.LoginFrame();
    }
=======
>>>>>>> GUI

    public void LoginFrame(){
        JFrame LoginFrame = new JFrame("로그인");
        JPanel LoginPanel = new JPanel(null);

        LoginFrame.setSize(350,200);
        LoginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        LoginFrame.add(LoginPanel);
        JLabel userlabel = new JLabel("User");
        userlabel.setBounds(10,20,80,25);
        LoginPanel.add(userlabel);

        userText = new JTextField();
        userText.setBounds(100,20,165,25);
        LoginPanel.add(userText);

        JLabel passwordlabel = new JLabel("Password");
        passwordlabel.setBounds(10,50,80,25);
        LoginPanel.add(passwordlabel);

        passwordText = new JPasswordField();
        passwordText.setBounds(100,50,165,25);
        LoginPanel.add(passwordText);

        JButton button = new JButton("LOG IN");
        button.setBounds(10,80,80,25);
        JLabel success = new JLabel("");
        success.setBounds(10,110,300,25);
        LoginPanel.add(success);
        success.setText(null);

<<<<<<< HEAD
        //button.addActionListener(new ActionListener() {
            //@Override
            //public void actionPerformed(ActionEvent e) {
                //String usertext = userText.getText();
               // String passwordtext = passwordText.getSelectedText();
                //WindowBuilder.bank.loginUser = WindowBuilder.bank.findUser(usertext);
                //if (WindowBuilder.bank.loginUser == null) {
                //    success.setText("[시스템] 사용자를 찾을 수 없습니다.");
                //}
                //if (!WindowBuilder.bank.loginUser.password.contentEquals(passwordtext)) {
                //    success.setText("[시스템] 비밀번호가 잘못되었습니다.");
                //    WindowBuilder.bank.loginUser = null;
                //}

            //}
        //});
=======
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String usertext = userText.getText();
                String passwordtext = String.valueOf(passwordText.getPassword());
                if (!WindowBuilder.bank.login(usertext, passwordtext)) {
                    if (WindowBuilder.bank.loginUser == null)
                        success.setText("[시스템] 사용자를 찾을 수 없습니다.");
                    if (!WindowBuilder.bank.loginUser.password.contentEquals(passwordtext)) {
                        success.setText("[시스템] 비밀번호가 잘못되었습니다.");
                        WindowBuilder.bank.loginUser = null;
                    }
                }
                else {
                    WindowBuilder main = new WindowBuilder();
                    main.createAndShowMain();
                }
            }
        });

>>>>>>> GUI
        LoginPanel.add(button);
        LoginFrame.setVisible(true);
    }
}