package ui;



import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import bank.User;

public class TopBarPanel extends JPanel {
	
	JButton bankIcon;
	JButton logout;
	JLabel name;
	
    void setupTopBarPanel() {
    	JPanel mainPane = new JPanel(new BorderLayout());
    	JPanel rightPane = new JPanel(new FlowLayout());
    	
        bankIcon = new JButton("메인화면");
    	logout = new JButton("로그아웃");
    	name = new JLabel("홍길동님");
    	
    	rightPane.add(name);
    	rightPane.add(logout);
    	
        mainPane.add(bankIcon, BorderLayout.WEST);
        mainPane.add(rightPane, BorderLayout.EAST);
        
        add(mainPane);
    }
}
