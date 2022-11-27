package ui;

import javax.swing.JPanel;

public class BottomBarPanel extends JPanel {
    ButtonDesign homeButton = new ButtonDesign("홈");
    ButtonDesign productButton = new ButtonDesign("상품");
    ButtonDesign depositButton = new ButtonDesign("송금");
    ButtonDesign setupButton = new ButtonDesign("설정");

    BottomBarPanel() {
        add(homeButton);
        add(productButton);
        add(depositButton);
        add(setupButton);

        setupButton.addActionListener(e -> WindowBuilder.card.show(WindowBuilder.bankingPane, "설정"));
        depositButton.addActionListener(e -> WindowBuilder.card.show(WindowBuilder.bankingPane, "송금"));
        homeButton.addActionListener(e -> WindowBuilder.card.show(WindowBuilder.bankingPane, "메인화면"));
        productButton.addActionListener(e -> WindowBuilder.card.show(WindowBuilder.bankingPane, "은행상품"));
    }

}
