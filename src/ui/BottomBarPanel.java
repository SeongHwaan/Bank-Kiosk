package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

public class BottomBarPanel extends JPanel {
    ButtonDesign homeButton;
    ButtonDesign productButton;
    ButtonDesign depositButton;
    ButtonDesign setupButton;

    BottomBarPanel() {
        // bottomPane.setBorder(null);
        homeButton = new ButtonDesign("홈");
        productButton = new ButtonDesign("상품");
        depositButton = new ButtonDesign("송금");
        setupButton = new ButtonDesign("설정");

        add(homeButton);
        add(productButton);
        add(depositButton);
        add(setupButton);

        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                WindowBuilder.card.show(WindowBuilder.bankingPane, "메인화면");

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
