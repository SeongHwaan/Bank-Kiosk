package ui;

import javax.swing.*;
import java.awt.*;

public class SetupPanel extends JPanel {

    SetupPanel() {
        setLayout(new GridBagLayout());
        GridBagConstraints[] gbc = new GridBagConstraints[2];

        for (int i = 0; i < 2; i++) {
            gbc[i] = new GridBagConstraints();
        }

        JLabel title = new JLabel("설정");
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
