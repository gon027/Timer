import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;


public class MainPanel implements ActionListener, Runnable{
	
	//メインパネル
	private JFrame mainpanel;
	private JButton StartButton;		//スタートボタン
	private JButton StopButton;			//ストップボタン
	private JButton ResetButton;		//リセットボタン
	private JPanel UpPanel;
	private JPanel DownPanel;
	private JLabel lab;
	private int hour;
	private int minute;
	
	Setting f;
		
	MainPanel(int t, int m){
		int width = 400;
		int height = 250;
		
		//各ボタンの設定
		StartButton = new JButton("スタート");
		StartButton.setPreferredSize(new Dimension(100,50));
		StopButton = new JButton("ストップ");
		StopButton.setPreferredSize(new Dimension(100, 50));
		ResetButton = new JButton("リセット");
		ResetButton.setPreferredSize(new Dimension(100, 50));
		
		//パネルの設定
		UpPanel = new JPanel();
		//UpPanel.setBackground(Color.red);
		
		DownPanel = new JPanel();
		//DownPanel.setBackground(Color.yellow);
		
		//mainpanelの設定
		mainpanel = new JFrame("タイマー");
		mainpanel.setSize(width, height);
		mainpanel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainpanel.setLocationRelativeTo(null);
		mainpanel.setResizable(false);
		
		//panelが上下に来るように設定
		mainpanel.setLayout(new FlowLayout());
		
		//mainpanelにpanelを追加
		mainpanel.add(UpPanel);
		mainpanel.add(DownPanel);
		
		//タイマーを表示させる
		hour = t;
		minute = m;
		String s = t < 10  ? ("0" + t + ":" + "0" + m) : (t + ":" + m);
		lab = new JLabel(s, SwingConstants.CENTER);
		lab.setFont(new Font("Arial", Font.PLAIN, 120));
		//mainpanel.getContentPane().add(lab);
		//これでも動く
		UpPanel.add(lab);
		
		DownPanel.add(StartButton);
		DownPanel.add(StopButton);
		DownPanel.add(ResetButton);
		
		StartButton.addActionListener(this);
		StopButton.addActionListener(this);
		ResetButton.addActionListener(this);
		
		mainpanel.setVisible(true);
		
//		startTimer();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		//ボタンが押された時のアクションを起こすメソッド
		if(e.getSource() == StartButton) {
			//スタートボタンが押された時の処理
			System.out.println("Start");
		}else if(e.getSource() == StopButton) {
			//ストップボタンが押された時の処理
			System.out.println("Stop");
			
		}else if(e.getSource() == ResetButton) {
			//リセットボタンが押された時の処理
			System.out.println("Reset");
			
		}
	}
	
	public void showMsg() {
		JOptionPane.showMessageDialog(null, "Time up!", "終了",
				JOptionPane.INFORMATION_MESSAGE);
	}
	
	public void startTimer() {
		while(true) {
			try {
				Thread.sleep(1000);
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
			
			if(minute == 0) {
				minute = 59;
				hour--;
			} else {
				minute--;
			}
			 String h = hour < 10 ? ("0" + hour) : Integer.toString(hour);
			 String m = minute < 10 ? ("0" + minute) : Integer.toString(minute);
			 lab.setText(h + ":" + m);
			 
			 if(hour == 0 && minute == 0) {
				 showMsg();
				 break;
			 }
		}
	}
	
	public static void main(String[] args) {
		Setting form = new Setting();
		form.setVisible(true);
		if(form.getTime() != -1) new MainPanel(form.getTime(), form.getMinute());
	}

	@Override
	public void run() {
		while(true) {
			try {
				Thread.sleep(1000);
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
			
			if(minute == 0) {
				minute = 59;
				hour--;
			} else {
				minute--;
			}
			 String h = hour < 10 ? ("0" + hour) : Integer.toString(hour);
			 String m = minute < 10 ? ("0" + minute) : Integer.toString(minute);
			 lab.setText(h + ":" + m);
			 
			 if(hour == 0 && minute == 0) {
				 showMsg();
				 break;
			 }
		}
	}
}