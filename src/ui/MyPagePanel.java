package ui;

import javax.swing.*;
import java.awt.*;

public class MyPagePanel extends JPanel {

    MyPagePanel() {
        setLayout(new GridBagLayout());
        GridBagConstraints[] gbc = new GridBagConstraints[5];

        for (int i = 0; i < 5; i++) {
            gbc[i] = new GridBagConstraints();
        }

        JLabel title = new JLabel("마이페이지");
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setFont(new Font("", Font.BOLD, 28));

        gbc[0].gridx = 0;
        gbc[0].gridy = 0;
        gbc[0].weightx = 1;
        gbc[0].fill = GridBagConstraints.BOTH;
        gbc[0].ipady = 50;
        add(title, gbc[0]);
    }
}
