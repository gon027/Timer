import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

public class MainPanel implements ActionListener{
	private final int width = 400;
	private final int height = 200;
	
	//メインパネル
	private JFrame mainpanel;
	private JButton StartButton;		//スタートボタン
	private JButton StopButton;			//ストップボタン
	private JButton ResetButton;		//リセットボタン
	private JLabel lab;
	private int hour;
	private int second;
	
	private boolean timecount = true;
	
	MainPanel(int t, int m){
		//各ボタンの設定
		StartButton = new JButton("スタート");
		StartButton.setPreferredSize(new Dimension(100,50));
		StopButton = new JButton("ストップ");
		StopButton.setPreferredSize(new Dimension(100, 50));
		ResetButton = new JButton("リセット");
		ResetButton.setPreferredSize(new Dimension(100, 50));
		
		//mainpanelの設定
		mainpanel = new JFrame("タイマー");
		mainpanel.setSize(width, height);
		mainpanel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainpanel.setLocationRelativeTo(null);
		mainpanel.setResizable(false);

		//タイマーを設定
		hour = t;
		second = m;
		String s = t < 10  ? ("0" + t + ":" + "0" + m) : (t + ":" + m);
		lab = new JLabel(s, SwingConstants.CENTER);
		lab.setFont(new Font("Arial", Font.PLAIN, 120));
		mainpanel.getContentPane().add(lab);
		mainpanel.setVisible(true);
		
		startTimer();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		//ボタンが押された時のアクションを起こすメソッド
		if(e.getSource() == StartButton) {
			//スタートボタンが押された時の処理
			System.out.println("Start");
			timecount = true;
			
		}else if(e.getSource() == StopButton) {
			//ストップボタンが押された時の処理
			System.out.println("Stop");
			timecount = false;
			
		}else if(e.getSource() == ResetButton) {
			//リセットボタンが押された時の処理
			System.out.println("Reset");
		}
	}
	
	public void showMsg() {
		JOptionPane.showMessageDialog(null, "Time up!", "終了", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public void startTimer() {
		while(timecount) {
			try {
				Thread.sleep(1000);
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
			
			if(second == 0) {
				second = 59;
				hour--;
			} else {
				second--;
			}
			
			 String h = hour < 10 ? ("0" + hour) : Integer.toString(hour);
			 String m = second < 10 ? ("0" + second) : Integer.toString(second);
			 lab.setText(h + ":" + m);
			 
			 if(hour == 0 && second == 0) {
				 showMsg();
				 break;
			 }
		}
	}
	
	public static void main(String[] args) {
		Setting form = new Setting();
		form.setVisible(true);
		
		if(form.getTime() != -1) {
			new MainPanel(form.getTime(), form.getMinute());
		}
	}
}
