package ui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

import ui.BankProduct.ProductList;

public class CreateAccountPanel extends JPanel {

	JPanel createPane;
	JPanel productInfo;
	JPanel terms;
	JPanel id;
	JTextField nameField;
	CardLayout createCard;

	public CreateAccountPanel() {
		setLayout(new BorderLayout());

		createPane = new JPanel();
		createPane.setLayout(new CardLayout());
		createCard = (CardLayout) createPane.getLayout();

		productInfo = new JPanel(new BorderLayout());
		terms = new JPanel(new BorderLayout());
		id = new JPanel();

		setupProduct();
		setupTerms();
		setupId();

		createPane.add(productInfo);
		createPane.add(terms, "약관");
		createPane.add(id, "신분증");

		add(createPane, BorderLayout.CENTER);
	}

	//상품설명
	void setupProduct() {

		nameField = new JTextField();

		JLabel info = new JLabel("계좌 정보:~~");
		JButton button = new JButton("다음단계");

		productInfo.add(nameField, BorderLayout.NORTH);
		productInfo.add(info, BorderLayout.CENTER);
		productInfo.add(button, BorderLayout.SOUTH);

		button.addActionListener(e -> createCard.show(createPane, "약관"));

	}
	
	//약관
	void setupTerms() {
		JPanel group = new JPanel(new FlowLayout());

		ButtonGroup buttonGroup = new ButtonGroup();

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

		JTextArea textArea_1 = new JTextArea();
		textArea_1.setRows(1);
		textArea_1.setEditable(false);
		textArea_1.setWrapStyleWord(true);
		textArea_1.setLineWrap(true);
		textArea_1.setText("약관:\n1항~~~dsfsdfsfdsadfsfa");

		scrollPane_1.setViewportView(textArea_1);

		JRadioButton rdBtnOk = new JRadioButton("동의");
		rdBtnOk.setBackground(Color.WHITE);
		buttonGroup.add(rdBtnOk);
		rdBtnOk.setSelected(true);

		JRadioButton rdBtnCancel = new JRadioButton("반대");
		rdBtnCancel.setBackground(Color.WHITE);
		buttonGroup.add(rdBtnCancel);

		group.add(rdBtnCancel);
		group.add(rdBtnOk);

		JButton btnNewButton = new JButton("다음단계");
		btnNewButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (rdBtnOk.isSelected()) {
					System.out.println("모두 동의하셨습니다. 다음단계로 진행");
					createCard.show(createPane, "신분증");

				} else {
					System.out.println("동의하지 않으셨습니다. 동의 후 진행해주세요");
					JOptionPane.showMessageDialog(null, "동의하지 않으셨습니다. 동의 후 진행해주세요");
				}
			}
		});
		btnNewButton.setBounds(365, 628, 178, 23);

		terms.add(group, BorderLayout.SOUTH);
		terms.add(btnNewButton, BorderLayout.NORTH);
		terms.add(scrollPane_1, BorderLayout.CENTER);

	}

	//신분증 확인
	void setupId() {
		id.setLayout(new BorderLayout());

		JLabel image = new JLabel();
		JButton idButton = new JButton("신분증 불러오기");
		JButton nextButton = new JButton("다음 단계");

		JFileChooser chooser = new JFileChooser();

		idButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG / PNG", "jpg", "png");
				chooser.setFileFilter(filter);

				int ret = chooser.showOpenDialog(null);
				if (ret != JFileChooser.APPROVE_OPTION) {
					JOptionPane.showMessageDialog(null, "파일을 선택 하지 않음", "경고", JOptionPane.WARNING_MESSAGE);
					return;
				}

				String filePath = chooser.getSelectedFile().getPath();
				image.setIcon(new ImageIcon(filePath));

			}
		});

		nextButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Creation create = new Creation();
				create.setupCreation();
				create.startCreation();
			}

		});

		id.add(idButton, BorderLayout.NORTH);
		id.add(image, BorderLayout.CENTER);
		id.add(nextButton, BorderLayout.SOUTH);

	}

	//테스트
	public static void main(String[] args) {
		CreateAccountPanel a = new CreateAccountPanel();

		JFrame f = new JFrame();

		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setLocation(500, 200);
		f.setSize(400, 400);

		// f.pack();
		f.setVisible(true);

		f.add(a);

	}
	
	//계좌개설
	class Creation extends JFrame {

		JPanel a;

		Creation() {
			a = new JPanel(new BorderLayout());
			add(a);
			JLabel image = new JLabel();
			JLabel text = new JLabel("계좌를 생성중...");

			image.setIcon(new ImageIcon("src/images/loading.gif"));

			a.add(text, BorderLayout.NORTH);
			a.add(image, BorderLayout.CENTER);

			setLocationRelativeTo(null);
			setSize(400, 300);
			setVisible(true);
		}

		void setupCreation() {
		}

		void startCreation() {
			System.out.println("메인쓰레드 START");

			Timer t = new Timer();
			TimerTask task = new TimerTask() {

				@Override
				public void run() {
					WindowBuilder.bank.createAccount(nameField.getText(), ProductList.productIndex);
					// 1. 메인화면 or 2. 계좌 개설이 완료되었다는 패널
					WindowBuilder.card.show(WindowBuilder.bankingPane, "메인화면");
					dispose();
				}
			};
			t.schedule(task, 5000);
		}
	}
}
