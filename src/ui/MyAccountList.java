package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.RoundRectangle2D;
import java.util.ArrayList;

import bank.Bank;
import bank.Savings;

public class MyAccountList extends JPanel {
	static ArrayList<Savings> myAccount = Bank.loginAccountList;
	static int selectedIndex = 0;

	// static Savings account;
	static DefaultListModel<AccountData> model = new DefaultListModel<>();
	static JList<AccountData> accountList = new JList<>(model);

	public MyAccountList() {
		setLayout(new GridBagLayout());
		GridBagConstraints[] gbc = new GridBagConstraints[2];

		for (int i = 0; i < 2; i++) {
			/* GridBagConstraints 초기화 */
			gbc[i] = new GridBagConstraints();
		}

		JLabel title = new JLabel("자산");
		title.setHorizontalAlignment(JLabel.CENTER);
		title.setFont(new Font("", Font.BOLD, 28));

		setList();
		accountList.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		accountList.setBackground(new Color(255, 255, 255));

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
		add(accountList, gbc[1]);

		setBorder(null);
	}
	
	
	static class ListModel extends DefaultListModel {
	    public void update(int index)
	    {
	        fireContentsChanged(this, index, index);
	    }
	}

	static void setList() {
		model.clear();
		myAccount = Bank.loginAccountList;
		for (Savings s : myAccount) {
			model.addElement(new AccountData(s.number));	
		}
	}

	static void update() {
		setList();
		accountList.setCellRenderer(new CustomListRenderer(accountList));
	}

	static class CustomListRenderer extends DefaultListCellRenderer {
		private final CustomLabel renderer;

		public CustomListRenderer(final JList<AccountData> list) {
			super();
			renderer = new CustomLabel();
			list.setSelectedIndex(0);

			list.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseReleased(MouseEvent e) {
					if (SwingUtilities.isLeftMouseButton(e)) {
						selectedIndex = list.getSelectedIndex();
						AccountManage.update();
						int index = list.locationToIndex(e.getPoint());

						if (index != -1 && list.isSelectedIndex(index)) {
							Rectangle rect = list.getCellBounds(index, index);
						}

						WindowBuilder.card.show(WindowBuilder.bankingPane, "계좌관리");
					}
				}
			});
		}

		@Override
		public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected,
				boolean cellHasFocus) {
			renderer.setSelected(isSelected);
			renderer.setData((AccountData) value);
			return renderer;
		}

		public static class CustomLabel extends JLabel {
			private static final Color selectionColor = new Color(0, 100, 255);

			private boolean selected;
			private AccountData data;

			public CustomLabel() {
				setOpaque(false);
				setBorder(BorderFactory.createEmptyBorder(0, 120, 0, 120));
			}

			private void setSelected(boolean selected) {
				this.selected = selected;
				setForeground(selected ? Color.WHITE : Color.BLACK);
			}

			private void setData(ui.MyAccountList.AccountData data) {
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
				// g2d.drawImage ( tipIcon.getImage (), 5 + 13 - tipIcon.getIconWidth () / 2, 5
				// + 13 - tipIcon.getIconHeight () / 2, null );

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
