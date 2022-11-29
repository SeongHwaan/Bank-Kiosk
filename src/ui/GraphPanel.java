package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


//추후 사용할지 결정, 현재 코드 복붙한 상태
public class GraphPanel extends JFrame {
	
	public GraphPanel() {
		buildGUI();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocation(500,200);
		setSize(400,400);
		
		setVisible(true);
	}
	
	private void buildGUI() {
		setLayout(new BorderLayout());

		Container contentpane = getContentPane();
		ResultPanel resultPanel = new ResultPanel();
        //그래프를 그릴 패널
		
		contentpane.add(resultPanel, BorderLayout.CENTER);
		
		JPanel p = new JPanel();
		JTextField e1 = new JTextField(3);
		JTextField e2 = new JTextField(3);
		JTextField e3 = new JTextField(3);
		
		JButton button = new JButton("성적");
		
		p.add(new JLabel("국어"));
		p.add(e1);
		p.add(new JLabel("영어"));
		p.add(e2);
		p.add(new JLabel("수학"));
		p.add(e3);
		p.add(button);
		
		contentpane.add(p, BorderLayout.SOUTH);
		button.addActionListener(new DrawingActionListener(e1, e2, e3, resultPanel));
		//버튼을 누를 시 작동할 리스너
	}

	public static void main(String[] args) {
		new GraphPanel();
		
	}

	//결과물(그래프)이 나타날 패널
	class ResultPanel extends JPanel{
		int kor, eng, math;
		
		public void paint(Graphics g) {
			g.clearRect(0,0,getWidth(),getHeight());
			g.drawLine(50,  250,  350,  250);
			
			for (int i = 1; i< 11 ; i++) {
				g.drawString(i*10 + "", 25, 255-(20*i));
				g.drawLine(50,  250-(20*i), 350, 250-(20*i));
			}
			
			g.drawLine(50, 20, 50, 250);
			g.drawString("국어", 100, 270);
			g.drawString("영어",  200, 270);
			g.drawString("수학", 300, 270);
			g.setColor(Color.BLUE);
			
			if(kor > 0)
				g.fillRect(110, 250 - kor*2, 10, kor*2);	
			if(eng > 0)
				g.fillRect(210, 250 - eng*2, 10, eng*2);	
			if(math > 0)
				g.fillRect(310, 250 - math*2, 10, math*2);
		}
		
		void setScore(int kor, int eng, int math) {
			this.kor = kor;
			this.eng = eng;
			this.math = math;
		}
	}

	//버튼을 눌렀을 때 동작할 리스너
	class DrawingActionListener implements ActionListener{
		JTextField e1, e2, e3;
		ResultPanel resultPanel;
		
		DrawingActionListener(JTextField e1, JTextField e2, JTextField e3, ResultPanel resultPanel){
			this.e1 = e1;
			this.e2 = e2;
			this.e3 = e3;
			this.resultPanel = resultPanel;
		}
		
		
		@Override
		public void actionPerformed(ActionEvent e) {
        //숫자를 입력하지 않았을 시 오류 해결 & 에러 메세지
			try{
				int kor = Integer.parseInt(e1.getText());
				int eng = Integer.parseInt(e2.getText());
				int math = Integer.parseInt(e3.getText());
				resultPanel.setScore(kor, eng, math);
				resultPanel.repaint();
			}
			catch(NumberFormatException nfe) {
				JOptionPane.showMessageDialog(resultPanel, "다시 입력하세요", "에러 메세지", JOptionPane.ERROR_MESSAGE);
			}
		}

	}
}