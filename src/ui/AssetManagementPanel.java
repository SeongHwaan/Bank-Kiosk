package ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import bank.Bank;
import bank.Savings;

public class AssetManagementPanel extends JPanel implements ListSelectionListener {

	JList<String> productList;

	JTextArea description;
	JTextArea answer;

	JTextField cashInput;
	JTextField monthInput;

	JLabel amount;
	JLabel month;

	JButton create;
	JButton calculate;

	JPanel textAreaPanel;
	JPanel inputPanel;

	String info;

	Savings product;

	DefaultListModel<String> listModel;
	private int selectedIndex = -1;

	public AssetManagementPanel() {
		setLayout(new BorderLayout());
		createList();

		JScrollPane productPanel = new JScrollPane(productList);
		add(productPanel, BorderLayout.NORTH);

		textAreaPanel = new JPanel(new BorderLayout());
		inputPanel = new JPanel(new FlowLayout());

		description = new JTextArea(10, 10);
		answer = new JTextArea(10, 10);

		amount = new JLabel("원금");
		month = new JLabel("기간");

		create = new JButton("계좌개설");
		calculate = new JButton("계산");

		textAreaPanel.add(description, BorderLayout.NORTH);
		textAreaPanel.add(answer, BorderLayout.SOUTH);

		cashInput = new JTextField(20);
		monthInput = new JTextField(20);

		inputPanel.add(amount);
		inputPanel.add(cashInput);
		// inputPanel.add(month);
		// inputPanel.add(monthInput);
		// inputPanel.add(create);
		// inputPanel.add(calculate);

		add(textAreaPanel, BorderLayout.CENTER);
		add(inputPanel, BorderLayout.SOUTH);

		calculate.addActionListener(e -> {
			if (selectedIndex != 0) {
				try {
					product = Bank.productList.get(selectedIndex);
					int c = Integer.parseInt(cashInput.getText());
					int m = Integer.parseInt(monthInput.getText());
					String interest = Integer.toString(product.calcInterest(m));
					String amount = Integer.toString(product.calcEstimatedAmount(m));
					answer.setText("");
					answer.append("이자: " + interest + "예상 금액: " + amount);
				} catch (NumberFormatException e1) {
					System.out.println("정수를 입력하십시오");
				}
			}
		});
	}

	private void createList() {
		setList();
		productList = new JList<>(listModel);
		productList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		ListSelectionModel listSM = productList.getSelectionModel();
		listSM.addListSelectionListener(this);
	}

	private void setList() {
		listModel = new DefaultListModel<>();
		for (Savings p : Bank.productList)
			listModel.addElement(p.name);
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		description.setText("");
		if (!e.getValueIsAdjusting()) {
			ListSelectionModel lsm = (ListSelectionModel) e.getSource();
			if (!lsm.isSelectionEmpty()) {
				selectedIndex = lsm.getMinSelectionIndex();
			}
			info = Bank.productList.get(selectedIndex).getInfo();
			description.append(info);
		}
	}

	// 계좌개설
	void createAccount() {

	}

}
