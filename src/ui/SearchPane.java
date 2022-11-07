package ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SearchPane extends JPanel {
	private static final long serialVersionUID = 1L;

	void init() {
		JPanel pane = new JPanel();
		JTextField kwdBox = new JTextField("", 20);
		pane.add(kwdBox, BorderLayout.WEST);
		JButton search = new JButton("검색");
		pane.add(search, BorderLayout.EAST);

		// 고객 찾기 미구현
		search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getActionCommand().equals("검색")) {

				}
			}
		});
		add(pane);
	}

}
