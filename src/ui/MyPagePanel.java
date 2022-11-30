package ui;

import bank.Bank;
import bank.Savings;

import javax.swing.*;
import java.awt.*;

public class MyPagePanel extends JPanel {
    static JPanel infoPanel;
    Image originProfileImage = new ImageIcon("src/images/user.png").getImage();
    Image resizedProfileImage = originProfileImage.getScaledInstance(128, 128, Image.SCALE_SMOOTH);
    ImageIcon calcIcon = new ImageIcon(resizedProfileImage);
    JLabel profileImage = new JLabel(calcIcon);
    static JLabel lblNewLabel = new JLabel();
    JLabel lblNewLabel_1 = new JLabel("이름");
    JLabel lblNewLabel_2 = new JLabel("생년월일");
    JLabel lblNewLabel_3 = new JLabel("전화번호");
    JLabel lblNewLabel_4 = new JLabel("이메일");
    static JLabel textArea_1 = new JLabel();
    static JLabel textArea_2 = new JLabel();
    static JLabel textArea_3 = new JLabel();
    static JLabel textArea_4 = new JLabel();

    MyPagePanel() {
        infoPanel = new JPanel();
        setLayout(new GridBagLayout());
        infoPanel.setLayout(new GridLayout(4, 2));
        GridBagConstraints[] gbc = new GridBagConstraints[5];

        for (int i = 0; i < 5; i++) {
            gbc[i] = new GridBagConstraints();
        }

        JLabel title = new JLabel("마이페이지");
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setFont(new Font("", Font.BOLD, 28));
        lblNewLabel.setHorizontalAlignment(JLabel.CENTER);
        lblNewLabel.setFont(new Font("", Font.BOLD, 20));
        update();

        gbc[0].gridx = 0;
        gbc[0].gridy = 0;
        gbc[0].weightx = 1;
        gbc[0].fill = GridBagConstraints.BOTH;
        gbc[0].ipady = 50;
        add(title, gbc[0]);

        gbc[1].gridx = 0;
        gbc[1].gridy = 1;
        gbc[1].weightx = 1;
        gbc[1].weighty = 1;
        gbc[1].fill = GridBagConstraints.BOTH;
        add(profileImage, gbc[1]);

        gbc[2].gridx = 0;
        gbc[2].gridy = 2;
        gbc[2].weightx = 1;
        gbc[2].weighty = 1;
        gbc[2].fill = GridBagConstraints.BOTH;
        add(lblNewLabel, gbc[2]);

        lblNewLabel_1.setHorizontalAlignment(JLabel.CENTER);
        lblNewLabel_1.setFont(new Font("", Font.BOLD, 15));
        lblNewLabel_2.setHorizontalAlignment(JLabel.CENTER);
        lblNewLabel_2.setFont(new Font("", Font.BOLD, 15));
        lblNewLabel_3.setHorizontalAlignment(JLabel.CENTER);
        lblNewLabel_3.setFont(new Font("", Font.BOLD, 15));
        lblNewLabel_4.setHorizontalAlignment(JLabel.CENTER);
        lblNewLabel_4.setFont(new Font("", Font.BOLD, 15));
        infoPanel.add(lblNewLabel_1);
        infoPanel.add(textArea_1);
        infoPanel.add(lblNewLabel_2);
        infoPanel.add(textArea_2);
        infoPanel.add(lblNewLabel_3);
        infoPanel.add(textArea_3);
        infoPanel.add(lblNewLabel_4);
        infoPanel.add(textArea_4);

        gbc[3].gridx = 0;
        gbc[3].gridy = 3;
        gbc[3].weightx = 1;
        gbc[3].weighty = 3;
        gbc[3].fill = GridBagConstraints.BOTH;
        add(infoPanel, gbc[3]);

        ButtonDesign delUser = new ButtonDesign("회원탈퇴");
        delUser.addActionListener(e -> {
            WindowBuilder.mainFrame.dispose();
            javax.swing.SwingUtilities.invokeLater(LoginFrame::main);
            Bank.userMgr.list.remove(Bank.loginUser);
            for (Savings s : Bank.loginAccountList) {
                Bank.accountMgr.list.remove(s);
            }
            MyAccountList.selectedIndex = 0;
            Bank.loginUser = null;
            Bank.loginAccountList.clear();
        });

        gbc[4].gridx = 0;
        gbc[4].gridy = 4;
        gbc[4].weightx = 1;
        gbc[4].weighty = 1;
        add(delUser, gbc[4]);
    }

    public static void update() {
        lblNewLabel.setText(Bank.loginUser.id);
        textArea_1.setText(Bank.loginUser.name);
        textArea_2.setText(Bank.loginUser.birthDay);
        textArea_3.setText(Bank.loginUser.phone);
        textArea_4.setText(Bank.loginUser.email);
    }
}
