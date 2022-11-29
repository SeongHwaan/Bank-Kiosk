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

public class BankProduct extends JPanel {
	static JPanel infoPanel = new JPanel();
	static JPanel calcPanel = new JPanel();
	static JPanel calcButtonPanel = new JPanel();
	JLabel lblNewLabel = new JLabel("연 이자");
	JLabel lblNewLabel_1 = new JLabel("유형");
	JLabel lblNewLabel_2 = new JLabel("가입 기간");
	static JLabel textArea = new JLabel();
	static JLabel textArea_1 = new JLabel();
	static JLabel textArea_2 = new JLabel();
	JButton btnNewButton = new JButton("계좌개설");
	JButton btnNewButton_1 = new JButton("단리/복리 이해하기");
	JButton btnNewButton_2 = new JButton("계산해보기");
	Image originCalcImage = new ImageIcon("src/images/calculator.png").getImage();
	Image resizedCalcImage = originCalcImage.getScaledInstance(128, 128, Image.SCALE_SMOOTH);
	ImageIcon calcIcon = new ImageIcon(resizedCalcImage);
	JLabel calcImage = new JLabel(calcIcon);
	static int productIndex = 0;

	static ProductList p;

	static Savings product;

	public BankProduct() {
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
		});

		btnNewButton_1.addActionListener(e -> {
			try {
				Desktop.getDesktop().browse(new URI("https://www.youtube.com/watch?v=5c8x2YqppTo"));
			} catch (IOException | URISyntaxException ex) {
				ex.printStackTrace();
			}
		});

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

	public static void update() {
		try {
			product = Bank.productList.get(productIndex);
			textArea.setText(String.valueOf(product.rate));
			textArea_1.setText(product.name);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	static class ProductList extends JPanel {

		DefaultListModel model = new DefaultListModel();
		JList accountList = new JList(model);

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
				model.addElement(new AccountData(p.info));
			}
		}

		static class CustomListRenderer extends DefaultListCellRenderer {
			private final ProductList.CustomListRenderer.CustomLabel renderer;

			public CustomListRenderer(final JList list) {
				super();
				renderer = new ProductList.CustomListRenderer.CustomLabel();
				list.setSelectedIndex(0);

				list.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseReleased(MouseEvent e) {
						if (SwingUtilities.isLeftMouseButton(e)) {
							productIndex = list.getSelectedIndex();
							BankProduct.update();
							int index = list.locationToIndex(e.getPoint());

							if (index != -1 && list.isSelectedIndex(index)) {
								Rectangle rect = list.getCellBounds(index, index);
							}
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
			private Color iconColor;
			private final String name;

			public AccountData(String name) {
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
