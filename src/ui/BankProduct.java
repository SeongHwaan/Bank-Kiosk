package ui;

import javax.swing.*;
import bank.Bank;
import bank.Savings;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.RoundRectangle2D;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class BankProduct extends JPanel {
	static JPanel infoPanel;
	static JPanel calcPanel;
	static JPanel calcButtonPanel;
	JLabel lblNewLabel = new JLabel("연 이자");
	JLabel lblNewLabel_1 = new JLabel("유형");
	JLabel lblNewLabel_2 = new JLabel("가입 기간");
	static JLabel textArea = new JLabel();
	static JLabel textArea_1 = new JLabel();
	static JLabel textArea_2 = new JLabel();
	ButtonDesign btnNewButton = new ButtonDesign("계좌개설");
	ButtonDesign btnNewButton_1 = new ButtonDesign("단리/복리 이해하기");
	ButtonDesign btnNewButton_2 = new ButtonDesign("계산해보기");
	Image originCalcImage = new ImageIcon("src/images/calculator.png").getImage();
	Image resizedCalcImage = originCalcImage.getScaledInstance(128, 128, Image.SCALE_SMOOTH);
	ImageIcon calcIcon = new ImageIcon(resizedCalcImage);
	JLabel calcImage = new JLabel(calcIcon);
	static int productIndex = 0;

	static ProductList p;

	static Savings product;

	public BankProduct() {
		infoPanel = new JPanel();
		calcPanel = new JPanel();
		calcButtonPanel = new JPanel();
		setLayout(new GridBagLayout());
		infoPanel.setLayout(new GridLayout(0, 2));
		calcPanel.setLayout(new GridLayout(0, 2));
		calcButtonPanel.setLayout(new GridLayout(2, 0));
		GridBagConstraints[] gbc = new GridBagConstraints[5];

		for (int i = 0; i < 5; i++) {
			gbc[i] = new GridBagConstraints();
		}

		JLabel title = new JLabel("상품");
		title.setHorizontalAlignment(JLabel.CENTER);
		title.setFont(new Font("", Font.BOLD, 28));

		gbc[0].gridx = 0;
		gbc[0].gridy = 0;
		gbc[0].weightx = 1;
		gbc[0].fill = GridBagConstraints.BOTH;
		gbc[0].ipady = 50;
		add(title, gbc[0]);

		p = new ProductList();
		p.setBorder(null);

		gbc[1].gridx = 0;
		gbc[1].gridy = 1;
		gbc[1].weightx = 1;
		gbc[1].weighty = 1;
		gbc[1].fill = GridBagConstraints.BOTH;
		add(p, gbc[1]);

		lblNewLabel.setHorizontalAlignment(JLabel.CENTER);
		textArea.setHorizontalAlignment(JLabel.CENTER);
		lblNewLabel_1.setHorizontalAlignment(JLabel.CENTER);
		textArea_1.setHorizontalAlignment(JLabel.CENTER);
		lblNewLabel_2.setHorizontalAlignment(JLabel.CENTER);
		textArea_2.setHorizontalAlignment(JLabel.CENTER);

		infoPanel.add(lblNewLabel);
		lblNewLabel.setFont(new Font("", Font.BOLD, 15));
		infoPanel.add(textArea);
		infoPanel.add(lblNewLabel_1);
		lblNewLabel_1.setFont(new Font("", Font.BOLD, 15));
		infoPanel.add(textArea_1);
		infoPanel.add(lblNewLabel_2);
		lblNewLabel_2.setFont(new Font("", Font.BOLD, 15));
		infoPanel.add(textArea_2);

		gbc[2].gridx = 0;
		gbc[2].gridy = 2;
		gbc[2].weightx = 1;
		gbc[2].weighty = 2;
		gbc[2].fill = GridBagConstraints.BOTH;
		add(infoPanel, gbc[2]);

		btnNewButton.addActionListener(e -> {
			WindowBuilder.card.show(WindowBuilder.bankingPane, "계좌개설");
			CreateAccount.setImage("src/images/idCard.png");
		});

		btnNewButton_1.addActionListener(e -> {
			try {
				Desktop.getDesktop().browse(new URI("https://www.youtube.com/watch?v=5c8x2YqppTo"));
			} catch (IOException | URISyntaxException ex) {
				ex.printStackTrace();
			}
		});

		btnNewButton_2.addActionListener(e -> createGraphFrame());

		gbc[3].gridx = 0;
		gbc[3].gridy = 3;
		gbc[3].weightx = 1;
		gbc[3].weighty = 1;
		gbc[3].fill = GridBagConstraints.BOTH;
		add(btnNewButton, gbc[3]);

		calcPanel.add(calcImage);
		calcButtonPanel.add(btnNewButton_1);
		calcButtonPanel.add(btnNewButton_2);
		calcPanel.add(calcButtonPanel);

		gbc[4].gridx = 0;
		gbc[4].gridy = 4;
		gbc[4].weightx = 1;
		gbc[4].weighty = 4;
		gbc[4].fill = GridBagConstraints.BOTH;
		add(calcPanel, gbc[4]);

		update();
	}

	private void createGraphFrame() {
		List<Double> compound = new ArrayList<>();
<<<<<<< Updated upstream
		List<Double> simple = new ArrayList<>();
		double sRate = Bank.productList.get(1).rate;
		double cRate = Bank.productList.get(2).rate;
		int cash = 100;
		for (int month = 0; month < 50; month++) {
			compound.add(cash * (Math.pow((100 + (float) cRate) / 100, month) - 1));
			simple.add((double) (cash * ((float) sRate * month / 1200)));
		}

		LineGraph mainPanel = new LineGraph(compound, simple);
		mainPanel.setPreferredSize(new Dimension(800, 600));
		JFrame frame = new JFrame("DrawGraph");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().add(mainPanel);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
=======
        List<Double> simple = new ArrayList<>();
        double sRate = Bank.productList.get(1).rate;
        double cRate = Bank.productList.get(2).rate;
        int cash = 100;
        for (int month = 0; month < 50; month++) {
        	compound.add((double) (cash * (Math.pow((100 + (float) cRate) / 100, month) - 1)));
        	simple.add((double) (cash * ((float) sRate * month / 120)));
        }
        
        LineGraph mainPanel = new LineGraph(compound, simple);
        mainPanel.setPreferredSize(new Dimension(800, 600));
        JFrame frame = new JFrame("DrawGraph");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().add(mainPanel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
>>>>>>> Stashed changes
	}

	public static void update() {
		product = Bank.productList.get(productIndex);
		textArea.setText(String.valueOf(product.rate));
		textArea_1.setText(product.name);
		CreateAccount.info.setText(
				"고객님께서 새로 계설하고자 하시는 상품은 '" + product.name + "'입니다. 아래 사항은 새로 발급 될 계좌정\n" + "보입니다. 확인바랍니다.\n\n" +
						"상품유형 : " + product.name + "\n" +
						"계좌번호 : " + product.getNumber() + "\n" +
						"연 이자 : " + product.rate + "\n\n" +
						"제1조(적용범위) 이 약관은 입출금이 자유로운 예금, 거치식예금 및 적립식예금 거래에 적용한다\n\n" +
						"제2조 (실명거래)\n" +
						"① 거래처는 실명으로 거래하여야 한다\n" +
						"② 은행은 거래처의 실명확인을 위하여 주민등록증․사업자등록증 등 실명확인증표 또는 그밖에\n" +
						"필요한 서류의 제시나 제출을 요구할 수 있고, 거래처는 이에 따라야 한다\n\n" +
						"제3조(거래장소) 거래처는 예금계좌를 개설한 영업점(이하 “개설점”이라 한다)에서 모든 예금거래\n" +
						"를 한다. 다만, 은행이 정하는 바에 따라 다른 영업점이나 다른 금융기관, 또는 현금자동지급기․\n" +
						"현금자동입출금기․컴퓨터․전화기 등(이하 “전산통신기기”)을 통하여 거래할 수 있다\n\n" +
						"제4조(거래방법) 거래처는 은행에서 내준 통장(증서․전자통장을 포함한다) 또는 수표․어음용지로\n" +
						"거래하여야 한다. 그러나 입금할 때와, 자동이체약정․전산통신기기이용약정 등에 따라 거래하는\n" +
						"경우 및 기등록된 생체정보(이하“바이오정보”), 실명확인증표 등을 통해 본인확인된 경우에는 통장\n" +
						"없이(이하“무통장”)도 거래할 수 있다.\n\n" +
						"*해당 약관은 프로젝트를 위한 더미데이터로 허위로 작성되었습니다.");
	}

	static class ProductList extends JPanel {

		DefaultListModel<AccountData> model = new DefaultListModel<>();
		JList<AccountData> accountList = new JList<>(model);

		public ProductList() {
			setLayout(new GridBagLayout());
			GridBagConstraints[] gbc = new GridBagConstraints[1];
			gbc[0] = new GridBagConstraints();

			setList();
			accountList.setCellRenderer(new ProductList.CustomListRenderer(accountList));
			accountList.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
			accountList.setBackground(new Color(255, 255, 255));

			gbc[0].gridx = 0;
			gbc[0].gridy = 0;
			gbc[0].weightx = 1;
			gbc[0].weighty = 1;
			gbc[0].fill = GridBagConstraints.BOTH;
			add(accountList, gbc[0]);

			setBorder(null);
		}

		void setList() {
			for (Savings p : Bank.productList) {
				model.addElement(new AccountData(p.color, p.info));
			}
		}

		static class CustomListRenderer extends DefaultListCellRenderer {
			private final ProductList.CustomListRenderer.CustomLabel renderer;

			public CustomListRenderer(final JList<AccountData> list) {
				super();
				renderer = new ProductList.CustomListRenderer.CustomLabel();
				list.setSelectedIndex(0);

				list.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseReleased(MouseEvent e) {
						if (SwingUtilities.isLeftMouseButton(e)) {
							productIndex = list.getSelectedIndex();
							BankProduct.update();
						}
					}
				});
			}

			@Override
			public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected,
					boolean cellHasFocus) {
				renderer.setSelected(isSelected);
				renderer.setData((ProductList.AccountData) value);
				return renderer;
			}

			public static class CustomLabel extends JLabel {
				private static final Color selectionColor = new Color(0, 100, 255);

				private boolean selected;
				private ProductList.AccountData data;

				public CustomLabel() {
					setOpaque(false);
					setBorder(BorderFactory.createEmptyBorder(0, 120, 0, 120));
				}

				private void setSelected(boolean selected) {
					this.selected = selected;
					setForeground(selected ? Color.WHITE : Color.BLACK);
				}

				private void setData(ui.BankProduct.ProductList.AccountData data) {
					this.data = data;
					setText(data.getName());
				}

				@Override
				protected void paintComponent(Graphics g) {
					Graphics2D g2d = (Graphics2D) g;
					g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

					if (selected) {
						Area area = new Area(new Ellipse2D.Double(0, 0, 36, 36));
						area.add(new Area(new RoundRectangle2D.Double(18, 3, getWidth() - 18, 29, 6, 6)));
						g2d.setPaint(selectionColor);
						g2d.fill(area);

						g2d.setPaint(Color.WHITE);
						g2d.fill(new Ellipse2D.Double(2, 2, 32, 32));
					}

					g2d.setPaint(data.getIconColor());
					g2d.fill(new Ellipse2D.Double(5, 5, 26, 26));

					super.paintComponent(g);
				}

				@Override
				public Dimension getPreferredSize() {
					final Dimension ps = super.getPreferredSize();
					ps.height = 36;
					return ps;
				}
			}

		}

		static class AccountData {
			private final Color iconColor;
			private final String name;

			public AccountData(Color circleColor, String name) {
				iconColor = circleColor;
				this.name = name;
			}

			private Color getIconColor() {
				return iconColor;
			}

			private String getName() {
				return name;
			}

			@Override
			public String toString() {
				return name;
			}
		}
	}
}
