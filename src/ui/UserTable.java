package ui;

import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import bank.*;

public class UserTable extends JPanel implements ListSelectionListener {

	private static final long serialVersionUID = 1L;

	JTable table;
	DefaultTableModel tableModel;
	int selectedIndex = -1;
	final String[] columnNames = { "아이디", "비밀번호", "이름", "생년월일", "전화번호", "이메일" };

	void init() {
		tableModel = new DefaultTableModel(columnNames, 0);
		for (User u : Bank.userMgr.list)
			tableModel.addRow(u.getTexts());

		table = new JTable(tableModel);
		table.setPreferredScrollableViewportSize(new Dimension(700, 250));
		table.setFillsViewportHeight(true); // 속성 크기 고정
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // 한 개의 튜플만 선택
		ListSelectionModel rowSM = table.getSelectionModel();
		rowSM.addListSelectionListener(this);
	}

	// fillDataToBox에 고객 정보 전달
	public void valueChanged(ListSelectionEvent e) {
		ListSelectionModel lsm = (ListSelectionModel) e.getSource();
		if (!lsm.isSelectionEmpty()) {
			selectedIndex = lsm.getMinSelectionIndex();
			String[] rowTexts = new String[tableModel.getColumnCount()];
			for (int i = 0; i < rowTexts.length; i++)
				rowTexts[i] = (String) tableModel.getValueAt(selectedIndex, i);
			Admin.bottom.fillDataToBox(rowTexts);
		}
	}

}
