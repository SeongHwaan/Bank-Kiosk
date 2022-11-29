package ui;

import javax.swing.*;
import bank.Bank;
import bank.Savings;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.RoundRectangle2D;
import java.util.ArrayList;

public class BankProduct extends JPanel {

	JLabel lblNewLabel_1 = new JLabel("유형");
	JLabel lblNewLabel_2 = new JLabel("가입 기간");
	static JTextArea textArea = new JTextArea();
	static JTextArea textArea_1 = new JTextArea();
	static JTextArea textArea_2 = new JTextArea();

	JLabel lblNewLabel_3 = new JLabel("계산기그림");
	JButton btnNewButton = new JButton("계산해보기");
	JButton btnNewButton_2 = new JButton("계좌개설");

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
		
		
		btnNewButton_2.addActionListener(e -> WindowBuilder.card.show(WindowBuilder.bankingPane, "계좌개설"));

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

	static class ProductList extends JPanel {
		static int productIndex;
		static int selectedIndex = 0;
		DefaultListModel model = new DefaultListModel();
		JList accountList = new JList(model);

		public ProductList() {
			setLayout(new GridBagLayout());
			GridBagConstraints[] gbc = new GridBagConstraints[2];

			for (int i = 0; i < 2; i++) {
				/* GridBagConstraints 초기화 */
				gbc[i] = new GridBagConstraints();
			}

			JLabel title = new JLabel("상품");
			title.setHorizontalAlignment(JLabel.CENTER);
			title.setFont(new Font("", Font.BOLD, 28));

			setList();
			accountList.setCellRenderer(new ProductList.CustomListRenderer(accountList));
			accountList.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
			accountList.setBackground(new Color(255, 255, 255));

			JScrollPane sp = new JScrollPane(accountList);

			gbc[0].gridx = 0;
			gbc[0].gridy = 0;
			gbc[0].weightx = 1;
			gbc[0].fill = GridBagConstraints.BOTH;
			gbc[0].ipady = 50;
			add(title, gbc[0]);

			gbc[1].gridx = 0;
			gbc[1].gridy = 1;
			gbc[1].weightx = 1;
			gbc[1].weighty = 2;
			gbc[1].fill = GridBagConstraints.BOTH;
			add(sp, gbc[1]);

			// setBackground(new Color(32, 32, 44));
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
							selectedIndex = list.getSelectedIndex();
							int index = list.locationToIndex(e.getPoint());

							if (index != -1 && list.isSelectedIndex(index)) {
								Rectangle rect = list.getCellBounds(index, index);
							}
						}
					}
				});
			}

			@Override
			public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
				renderer.setSelected(isSelected);
				renderer.setData((ProductList.AccountData) value);
				return renderer;
			}

			public static class CustomLabel extends JLabel {
				private static final Color selectionColor = new Color(182, 128, 22);

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
			private String name;

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
