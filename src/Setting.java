import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class Setting extends JDialog implements ActionListener{
	
	private int width, height;
	private Container cnt;
	private JButton SetButton;			//セットボタン
	private JButton CancelButton;		//キャンセルボタン
	private JTextField hourTime;		//時間を決めるテキスト
	private JTextField minuteTime;		//分を決めるボタン
	private JPanel panel;
	private JLabel Timelab;
	private JLabel Minutelab;
	private int time;		
	private int minute;
	
	public Setting() {
		width = 400;
		height = 150;
		this.setSize(width, height);
		this.setLocationRelativeTo(null);
		this.setTitle("Setting");
		this.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		this.setResizable(false);	
		cnt = this.getContentPane();
		cnt.setLayout(new BorderLayout());
	
		Timelab = new JLabel(" hour:");
		Minutelab = new JLabel(" minite:");
		hourTime = new JTextField();
		minuteTime = new JTextField();
		SetButton = new JButton("Set");
		SetButton.setPreferredSize(new Dimension(100,50));
		CancelButton = new JButton("Cancel");
		CancelButton.setPreferredSize(new Dimension(100,50));
		
		panel = new JPanel();
		panel.setLayout(new GridLayout(4, 2));
		panel.add(Timelab);
		panel.add(hourTime);
		panel.add(Minutelab);
		panel.add(minuteTime);
		panel.add(SetButton);
		panel.add(CancelButton);
		
		cnt.add(panel, BorderLayout.CENTER);
		
		SetButton.addActionListener(this);
		CancelButton.addActionListener(this);
		
		time = -1;
	}
	
	//エラーメッセージを出すための関数
	public void showErrorMag(String msg) {
		JOptionPane.showMessageDialog(null, msg,
				"Error", JOptionPane.ERROR_MESSAGE);
	}
	
	//ボタンが押された時の処理
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == SetButton) {
			try {
				//時間の設定
				time = Integer.parseInt(hourTime.getText());
				//分の設定
				minute = Integer.parseInt(minuteTime.getText());
				
				if(time < 0 || time > 120) {
					showErrorMag("0 - 120までの数値を入力");
					time = -1;
					return;
				}
				
				if(time < 0 || minute > 59) {
					showErrorMag("00 - 59までの数値を入力");
					return;
				}
				
			}catch(Exception ex){
				showErrorMag("数値を入力");
				return;
			}
			dispose();
		}else if(e.getSource() == CancelButton) {
			System.exit(0);
			dispose();
		}
	}
	
	public int getTime() {
		return time;
	}
	
	public int getMinute() {
		return minute;
	}
}
