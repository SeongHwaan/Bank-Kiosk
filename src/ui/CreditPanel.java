package ui;

import javax.swing.*;
import java.awt.*;

public class CreditPanel extends JPanel {

    CreditPanel() {
        setLayout(new GridBagLayout());
        GridBagConstraints[] gbc = new GridBagConstraints[11];

        for (int i = 0; i < 11; i++) {
            gbc[i] = new GridBagConstraints();
        }

        JLabel title = new JLabel("프로그램 정보");
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setFont(new Font("", Font.BOLD, 28));

        gbc[0].gridx = 0;
        gbc[0].gridy = 0;
        gbc[0].weightx = 1;
        gbc[0].fill = GridBagConstraints.BOTH;
        gbc[0].ipady = 50;
        add(title, gbc[0]);

        JLabel textArea = new JLabel();

        gbc[1].gridx = 0;
        gbc[1].gridy = 1;
        gbc[1].weightx = 1;
        gbc[1].weighty = 2;
        gbc[1].fill = GridBagConstraints.BOTH;
        add(textArea, gbc[1]);

        JLabel textArea_1 = new JLabel("2022 경기대학교 객체지향프로그래밍");
        textArea_1.setFont(new Font("", Font.BOLD, 20));
        textArea_1.setHorizontalAlignment(JLabel.CENTER);
        JLabel textArea_2 = new JLabel("C조 팀프로젝트 - KGU BANK");
        textArea_2.setFont(new Font("", Font.BOLD, 15));
        textArea_2.setHorizontalAlignment(JLabel.CENTER);
        JLabel textArea_3 = new JLabel("201810391 김성환");
        textArea_3.setFont(new Font("", Font.PLAIN, 15));
        textArea_3.setHorizontalAlignment(JLabel.CENTER);
        JLabel textArea_4 = new JLabel("201912023 김관식");
        textArea_4.setFont(new Font("", Font.PLAIN, 15));
        textArea_4.setHorizontalAlignment(JLabel.CENTER);
        JLabel textArea_5 = new JLabel("201912036 김영중");
        textArea_5.setFont(new Font("", Font.PLAIN, 15));
        textArea_5.setHorizontalAlignment(JLabel.CENTER);
        JLabel textArea_6 = new JLabel("201912139 조성원");
        textArea_6.setFont(new Font("", Font.PLAIN, 15));
        textArea_6.setHorizontalAlignment(JLabel.CENTER);
        JLabel textArea_7 = new JLabel("202016036 박재형");
        textArea_7.setFont(new Font("", Font.PLAIN, 15));
        textArea_7.setHorizontalAlignment(JLabel.CENTER);

        gbc[2].gridx = 0;
        gbc[2].gridy = 2;
        gbc[2].weightx = 1;
        gbc[2].weighty = 1;
        gbc[2].fill = GridBagConstraints.BOTH;
        add(textArea_1, gbc[1]);
        gbc[3].gridx = 0;
        gbc[3].gridy = 3;
        gbc[3].weightx = 1;
        gbc[3].weighty = 1;
        gbc[3].fill = GridBagConstraints.BOTH;
        add(textArea_2, gbc[2]);
        gbc[4].gridx = 0;
        gbc[4].gridy = 4;
        gbc[4].weightx = 1;
        gbc[4].weighty = 1;
        gbc[4].fill = GridBagConstraints.BOTH;
        add(textArea_3, gbc[3]);
        gbc[5].gridx = 0;
        gbc[5].gridy = 5;
        gbc[5].weightx = 1;
        gbc[5].weighty = 1;
        gbc[5].fill = GridBagConstraints.BOTH;
        add(textArea_4, gbc[4]);
        gbc[6].gridx = 0;
        gbc[6].gridy = 6;
        gbc[6].weightx = 1;
        gbc[6].weighty = 1;
        gbc[6].fill = GridBagConstraints.BOTH;
        add(textArea_5, gbc[5]);
        gbc[7].gridx = 0;
        gbc[7].gridy = 7;
        gbc[7].weightx = 1;
        gbc[7].weighty = 1;
        gbc[7].fill = GridBagConstraints.BOTH;
        add(textArea_6, gbc[6]);
        gbc[8].gridx = 0;
        gbc[8].gridy = 8;
        gbc[8].weightx = 1;
        gbc[8].weighty = 1;
        gbc[8].fill = GridBagConstraints.BOTH;
        add(textArea_7, gbc[7]);

        JLabel textArea_8 = new JLabel();

        gbc[9].gridx = 0;
        gbc[9].gridy = 9;
        gbc[9].weightx = 1;
        gbc[9].weighty = 1;
        gbc[9].fill = GridBagConstraints.BOTH;
        add(textArea_8, gbc[9]);

        JLabel textArea_9 = new JLabel();
        textArea_9.setText("Copyright © 2022. KGU OOP TEAM C. All rights reserved.");
        textArea_9.setHorizontalAlignment(JLabel.CENTER);

        gbc[10].gridx = 0;
        gbc[10].gridy = 10;
        gbc[10].weightx = 1;
        gbc[10].weighty = 1;
        gbc[10].fill = GridBagConstraints.BOTH;
        add(textArea_9, gbc[10]);
    }

}
