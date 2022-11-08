package ui;

import java.awt.BorderLayout;

import javax.swing.*;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class SearchPane extends JPanel {
	JTable table;

	public SearchPane(UserTable middle) {
		table = middle.table;
	}

	void init() {
		JPanel pane = new JPanel();
		JTextField kwdBox = new JTextField("", 20);
		pane.add(kwdBox, BorderLayout.WEST);
		JButton search = new JButton("검색");
		pane.add(search, BorderLayout.EAST);

		// 고객 찾기
		search.addActionListener(e -> {
			if (e.getActionCommand().equals("검색")) {
				TableRowSorter<TableModel> sorter = new TableRowSorter<>(table.getModel());
				sorter.setRowFilter(RowFilter.regexFilter(kwdBox.getText()));
				table.setRowSorter(sorter);
			}
		});
		add(pane);
	}
}
