package ui;

import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import bank.*;

public class AccountTable extends JPanel implements ListSelectionListener {
	JTable table;
	DefaultTableModel tableModel;
	static int selectedIndex = 0;
	Savings account;
	String[] columnNames;

	public AccountTable() {
		setModel();
		table = new JTable(tableModel);
		table.setPreferredScrollableViewportSize(new Dimension(150, 250));
		table.setFillsViewportHeight(true); // 속성 크기 고정
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // 한 개의 튜플만 선택
		ListSelectionModel rowSM = table.getSelectionModel();
		rowSM.addListSelectionListener(this);
	}

	public void valueChanged(ListSelectionEvent e) {
		ListSelectionModel lsm = (ListSelectionModel) e.getSource();
		if (!lsm.isSelectionEmpty()) {
			selectedIndex = lsm.getMinSelectionIndex();
		}
	}

	void setModel() {
		columnNames = new String[] { "계좌유형", "계좌번호", "금액" };
		tableModel = new DefaultTableModel(columnNames, 0) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		for (Savings s : Bank.loginAccountList)
			tableModel.addRow(s.getTexts());
	}

	public void update() {
		tableModel.setRowCount(0);
		for (Savings s : Bank.loginAccountList)
			tableModel.addRow(s.getTexts());
	}
}
