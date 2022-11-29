package ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class ManageAccount extends JPanel {

	HistoryTable historyTable;
	JButton check = new JButton("조회");

	public ManageAccount() {

		setLayout(new BorderLayout());

		historyTable = new HistoryTable();
		JScrollPane bottom = new JScrollPane(historyTable.table);
		add(bottom, BorderLayout.CENTER);
		add(check, BorderLayout.EAST);

		check.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				historyTable.update();
			}
		});
	}
}
