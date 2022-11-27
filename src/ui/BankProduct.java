package ui;

import javax.swing.*;
import bank.Bank;
import bank.Savings;

public class BankProduct extends JPanel {

	JLabel lblNewLabel_1 = new JLabel("유형");
	JLabel lblNewLabel_2 = new JLabel("가입 기간");
	static JTextArea textArea = new JTextArea();
	static JTextArea textArea_1 = new JTextArea();
	static JTextArea textArea_2 = new JTextArea();

	JLabel lblNewLabel_3 = new JLabel("계산기그림");
	JButton btnNewButton = new JButton("계산해보기");
	JButton btnNewButton_2 = new JButton("단리 / 복리란?");

	static ProductList p;

	static Savings product;

	public BankProduct() {
		btnNewButton_2.setBounds(114, 305, 200, 50);
		textArea_2.setBounds(114, 237, 200, 21);
		textArea_2.setColumns(10);
		textArea_2.setEditable(false);
		textArea_1.setBounds(114, 206, 200, 21);
		textArea_1.setColumns(10);
		textArea_1.setEditable(false);
		textArea.setBounds(114, 173, 200, 21);
		textArea.setColumns(10);
		textArea.setEditable(false);

		setLayout(null);

		p = new ProductList();
		p.setBounds(0, 0, 402, 160);
		p.setBorder(null);

		add(p);

		JLabel lblNewLabel = new JLabel("연 이자");
		lblNewLabel.setBounds(25, 175, 57, 21);
		add(lblNewLabel);
		lblNewLabel_1.setBounds(25, 206, 57, 21);

		add(lblNewLabel_1);
		lblNewLabel_2.setBounds(25, 237, 57, 21);

		add(lblNewLabel_2);

		add(textArea);

		add(textArea_1);

		add(textArea_2);

		add(btnNewButton_2);

		lblNewLabel_3.setBounds(42, 428, 116, 109);

		add(lblNewLabel_3);
		btnNewButton.setBounds(193, 450, 121, 60);

		add(btnNewButton);

	}

	public static void update() {

		try {
			product = Bank.productList.get(ProductList.productIndex);
			textArea.setText("");
			textArea.append(product.name);
			textArea_1.setText("");
			textArea_1.append("" + product.rate);

		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	class ProductList extends MyAccountList {
		static int productIndex;

		@Override
		void setList() {
			for (Savings p : Bank.productList) {
				model.addElement(new AccountData(p.info));
			}
		}
	}
}
