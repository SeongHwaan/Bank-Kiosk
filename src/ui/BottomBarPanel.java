package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

public class BottomBarPanel extends JPanel {
    ButtonDesign homeButton;
    ButtonDesign manageButton;
    ButtonDesign productButton;
    ButtonDesign depositButton;
    ButtonDesign setupButton;

    BottomBarPanel() {
        // bottomPane.setBorder(null);
        manageButton = new ButtonDesign("관리");

        homeButton = new ButtonDesign("홈");
        productButton = new ButtonDesign("상품");
        depositButton = new ButtonDesign("송금");
        setupButton = new ButtonDesign("설정");

        add(homeButton);
        add(manageButton);
        add(productButton);
        add(depositButton);
        add(setupButton);

        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                WindowBuilder.card.show(WindowBuilder.bankingPane, "메인화면");

            }
        });

        manageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (MyAccountList.selectedIndex != -1)
                    WindowBuilder.card.show(WindowBuilder.bankingPane, "계좌관리");

            }
        });

        productButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                WindowBuilder.card.show(WindowBuilder.bankingPane, "은행상품");
            }
        });
    }

}
