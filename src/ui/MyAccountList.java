package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.RoundRectangle2D;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

import bank.Bank;
import bank.Savings;

public class MyAccountList extends JPanel {

	ArrayList<Savings> myAccount = new ArrayList<>();
	static int selectedIndex = -1;
	static Savings account;
	DefaultListModel model;
	JList accountList;
	
	public MyAccountList() {
		
		myAccount = Bank.accountMgr.list;

		setLayout(null);
		model = new DefaultListModel();
		setList();
		accountList = new JList(model);
		accountList.setCellRenderer(new CustomListRenderer(accountList));
		accountList.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		accountList.setBackground(new Color(255, 255, 255));

		JScrollPane sp = new JScrollPane(accountList);
		sp.setBounds(12,37,361,493);
		
		add(sp);
		
		//setBackground(new Color(32, 32, 44));
		setBorder(null);
	}
	
	void setList() {
		for (Savings s : myAccount) {
			model.addElement(new AccountData(s.number));
		}
	}

	class CustomListRenderer extends DefaultListCellRenderer {
		private CustomLabel renderer;

		public CustomListRenderer(final JList list) {
			super();
			renderer = new CustomLabel();

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
			private static final Color selectionColor = new Color(182, 128, 22);

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

				g2d.setPaint(data.geticonColor());
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
		private String name;

		public AccountData(String name) {

			this.name = name;
		}

		private Color geticonColor() {
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