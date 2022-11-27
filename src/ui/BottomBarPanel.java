package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import java.awt.CardLayout;

public class BottomBarPanel extends JPanel {
    ButtonDesign manageButton;
    ButtonDesign productButton;
    ButtonDesign createButton;
    ButtonDesign setupButton;

    void setup() {
        WindowBuilder.card = (CardLayout) WindowBuilder.bankingPane.getLayout();

        // bottomPane.setBorder(null);

        manageButton = new ButtonDesign("관리");
        productButton = new ButtonDesign("상품");
        createButton = new ButtonDesign("개설");
        setupButton = new ButtonDesign("설정");

        add(manageButton);
        add(productButton);
        add(createButton);
        add(setupButton);

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
