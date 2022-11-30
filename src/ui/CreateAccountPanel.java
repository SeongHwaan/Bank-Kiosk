package ui;

import java.awt.*;
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
		BankProduct.update();
		JButton button = new JButton("다음단계");
		JTextArea info = new JTextArea();

		productInfo.add(nameField, BorderLayout.NORTH);
		productInfo.add(info, BorderLayout.CENTER);
		productInfo.add(button, BorderLayout.SOUTH);

		button.addActionListener(e -> {
			if (nameField.getText().isBlank()) {
				JOptionPane.showMessageDialog(null, "계좌 별칭을 지어주세요.", "경고", JOptionPane.WARNING_MESSAGE);
			} else {
				createCard.show(createPane, "약관");
			}
		});

	}
	
	//약관
	void setupTerms() {
		terms.setLayout(new GridBagLayout());
		GridBagConstraints[] gbc = new GridBagConstraints[4];

		for (int i = 0; i < 4; i++) {
			/* GridBagConstraints 초기화 */
			gbc[i] = new GridBagConstraints();
		}

		JPanel group = new JPanel(new FlowLayout());

		ButtonGroup buttonGroup = new ButtonGroup();

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		JTextArea textArea_1 = new JTextArea();
		textArea_1.setRows(1);
		textArea_1.setEditable(false);
		textArea_1.setWrapStyleWord(true);
		textArea_1.setLineWrap(true);
		textArea_1.setText("""
				제1조(목적)
				이 약관은 위탁자거래, 저축자거래, 수익자거래 및 기타의 거래를 종합적으로 거래할 수
				있는 계좌의 개설 및 이용에 대해서 고객과 KGU BANK(이하 ‘회사’라 한다)간의
				권리․의무관계 등 필요한 사항을 정함을 목적으로 한다.
				
				제2조(용어의 정의)
				① ‘종합계좌’란 고객이 1개의 계좌를 이용하여 모든 계좌를 일괄 관리할 수 있는
				종합적인 계좌관리시스템을 말한다.
				② ‘종합계좌거래’란 종합계좌를 이용하는 모든 종류의 거래를 말한다.
				③ ‘종합증권카드’란 종합계좌거래를 가능하게 하는 증권카드를 말한다.
				④ ‘개별계좌’란 종합계좌 내에 포함되어 있는 각각의 계좌를 말한다.
				
				제3조(종합계좌의 이용)
				① 고객이 종합계좌약관, 매매거래계좌설정약정서에 동의하고 종합계좌개설 신청서를
				제출하여 종합거래를 개설함으로써 다음 각호의 매매거래종류를 기본으로 이용할 수
				있다.
				1. 한국거래소에 상장된 증권의 장내매매거래
				2. 한국거래소에 상장된 증권의 장외매매거래
				3. 제1호 내지 제2호에 해당되지 않는 증권의 장외매매거래
				② 고객은 제1항 각호의 거래종류 외에 관계법규 또는 감독기관 등에 의해 회사에
				취급이 허용된 종류의 거래를 개시하고자 할 경우 종합계좌 개설과 별도로 매매거래계좌
				(이하 ‘개별계좌’라 한다)를 개설하여야 한다.
				
				제4조(종합계좌의 개설)
				① 고객이 회사와 처음 거래를 개시하고자 할 경우 종합계좌를 개설하여야 한다.
				② 고객이 종합계좌를 개설한 때에는 별도의 이용신청서 없이 다음 각호의 부가서비스를
				이용할 수 있다.
				1. 종합증권카드를 이용한 개별계좌의 자산 인출
				2. 종합계좌내 개별계좌간 자금대체 업무
				3. 종합계좌내 전 개별계좌의 잔고조회서비스
				③ 고객은 종합계좌 내에 거래를 위한 개별계좌를 추가로 개설할 수 있다.
				
				*해당 약관은 프로젝트를 위한 더미데이터로 허위로 작성되었습니다.""");

		scrollPane_1.setViewportView(textArea_1);
		scrollPane_1.setBorder(null);

		JRadioButton rdBtnOk = new JRadioButton("동의");
		rdBtnOk.setBackground(Color.WHITE);
		buttonGroup.add(rdBtnOk);

		JRadioButton rdBtnCancel = new JRadioButton("미동의");
		rdBtnCancel.setBackground(Color.WHITE);
		buttonGroup.add(rdBtnCancel);
		rdBtnCancel.setSelected(true);

		group.add(rdBtnOk);
		group.add(rdBtnCancel);

		JButton btnNewButton = new JButton("다음단계");
		btnNewButton.addActionListener(e -> {
			if (rdBtnOk.isSelected()) {
				System.out.println("모두 동의하셨습니다. 다음단계로 진행");
				createCard.show(createPane, "신분증");

			} else {
				System.out.println("동의하지 않으셨습니다. 동의 후 진행해주세요");
				JOptionPane.showMessageDialog(null, "동의하지 않으셨습니다. 동의 후 진행해주세요", "경고", JOptionPane.WARNING_MESSAGE);
			}
		});
		btnNewButton.setBounds(365, 628, 178, 23);

		JLabel title = new JLabel("약관");
		title.setFont(new Font("", Font.BOLD, 28));
		title.setHorizontalAlignment(JLabel.CENTER);

		gbc[0].gridx = 0;
		gbc[0].gridy = 0;
		gbc[0].weightx = 1;
		gbc[0].fill = GridBagConstraints.BOTH;
		gbc[0].ipady = 50;
		terms.add(title, gbc[0]);

		gbc[1].gridx = 0;
		gbc[1].gridy = 1;
		gbc[1].weightx = 1;
		gbc[1].weighty = 20;
		gbc[1].fill = GridBagConstraints.BOTH;
		terms.add(scrollPane_1, gbc[1]);

		gbc[2].gridx = 0;
		gbc[2].gridy = 2;
		gbc[2].weightx = 1;
		gbc[2].weighty = 1;
		gbc[2].fill = GridBagConstraints.BOTH;
		terms.add(group, gbc[2]);

		gbc[3].gridx = 0;
		gbc[3].gridy = 3;
		gbc[3].weightx = 1;
		gbc[3].weighty = 1;
		gbc[3].fill = GridBagConstraints.BOTH;
		terms.add(btnNewButton, gbc[3]);

	}

	//신분증 확인
	void setupId() {
		id.setLayout(new BorderLayout());

		JLabel image = new JLabel();
		JButton idButton = new JButton("신분증 불러오기");
		JButton nextButton = new JButton("다음 단계");

		JFileChooser chooser = new JFileChooser();

		idButton.addActionListener(e -> {
			FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG / PNG", "jpg", "png");
			chooser.setFileFilter(filter);

			int ret = chooser.showOpenDialog(null);
			if (ret != JFileChooser.APPROVE_OPTION) {
				JOptionPane.showMessageDialog(null, "파일을 선택 하지 않음", "경고", JOptionPane.WARNING_MESSAGE);
				return;
			}

			String filePath = chooser.getSelectedFile().getPath();
			image.setIcon(new ImageIcon(filePath));

		});

		nextButton.addActionListener(e -> {
			Creation create = new Creation();
			create.setupCreation();
			create.startCreation();
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
//			JLabel text = new JLabel("계좌를 생성중...");

			image.setIcon(new ImageIcon("src/images/loading.gif"));

//			a.add(text, BorderLayout.NORTH);
			a.add(image, BorderLayout.CENTER);

			setLocationRelativeTo(null);
			setSize(198, 198);
			setVisible(true);

			Dimension frameSize = this.getSize(); // 프레임 사이즈
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); // 모니터 사이즈
			this.setLocation((screenSize.width - frameSize.width)/2, (screenSize.height - frameSize.height)/2); // 화면 중앙
		}

		void setupCreation() {
		}

		void startCreation() {
			System.out.println("메인쓰레드 START");

			Timer t = new Timer();
			TimerTask task = new TimerTask() {

				@Override
				public void run() {
					WindowBuilder.bank.createAccount(nameField.getText(), BankProduct.productIndex);
					MyAccountList.update();
					// 1. 메인화면 or 2. 계좌 개설이 완료되었다는 패널
					WindowBuilder.card.show(WindowBuilder.bankingPane, "메인화면");
					dispose();
				}
			};
			t.schedule(task, 5000);
		}
	}
}
