package View;
import javax.swing.JLabel;
import javax.swing.JButton;

import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import java.awt.Color;

import javax.swing.JList;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.JTextArea;

public class AdminView  extends MyPanel{
	private JLabel signAs;
	private JButton logOut;
	private JButton aboutDormBtn;
	private JButton getComendBtn;
	private JLabel label;
	private JSeparator separator;
	private JComboBox boxSelD;
	private JButton okDorm;
	private JButton getInfo;
	private JTextArea textArea;
	
	public JComboBox getBoxSelD() {
		return boxSelD;
	}
	public JButton getOkDorm() {
		return okDorm;
	}
	public JButton getLogOut() {
		return logOut;
	}
	public JButton getAboutDormBtn() {
		return aboutDormBtn;
	}
	public JButton getGetComendBtn() {
		return getComendBtn;
	}
	public AdminView() {
		
		signAs = new JLabel("\u0412\u0438 \u0443\u0432\u0456\u0439\u0448\u043B\u0438 \u044F\u043A \u0430\u0434\u043C\u0456\u043D\u0456\u0441\u0442\u0440\u0430\u0442\u043E\u0440");
		signAs.setForeground(Color.WHITE);
		signAs.setHorizontalAlignment(SwingConstants.RIGHT);
		signAs.setFont(new Font("Tahoma", Font.PLAIN, 14));
		signAs.setBounds(220, 10, 400, 30);
		add(signAs);
		
		logOut = new JButton("\u0417\u043C\u0456\u043D\u0438\u0442\u0438 \u043A\u043E\u0440\u0438\u0441\u0442\u0443\u0432\u0430\u0447\u0430");
		logOut.setBounds(630, 10, 160, 30);
		add(logOut);
		
		aboutDormBtn = new JButton("\u0414\u043E\u0434\u0430\u0442\u0438 \u0431\u043B\u043E\u043A");
		aboutDormBtn.setBounds(100, 130, 310, 30);
		add(aboutDormBtn);
		
		getComendBtn = new JButton("\u0417\u0432'\u044F\u0437\u0430\u0442\u0438\u0441\u044C \u0437 \u043A\u043E\u043C\u0435\u043D\u0434\u0430\u043D\u0442\u043E\u043C");
		getComendBtn.setBounds(100, 180, 310, 30);
		add(getComendBtn);
		
		label = new JLabel("\u0412\u0438 \u043C\u043E\u0436\u0435\u0442\u0435: ");
		label.setFont(new Font("Arial", Font.PLAIN, 20));
		label.setBounds(60, 75, 184, 50);
		add(label);
		
		separator = new JSeparator();
		separator.setBounds(0, 56, 800, 14);
		add(separator);
		
		String [] d = Model.Model.INSTANCE.getDorms();
		boxSelD = new JComboBox(d);
		boxSelD.setBounds(445, 130, 230, 30);
		add(boxSelD);

		okDorm = new JButton("\u0414\u043E\u0434\u0430\u0442\u0438");
		okDorm.setBounds(685, 130, 89, 30);
		add(okDorm);
		
		getInfo = new JButton("\u0412\u0437\u043D\u0430\u0442\u0438");
		getInfo.setBounds(685, 130, 89, 30);
		add(getInfo);
		
		textArea = new JTextArea();
		textArea.setBounds(455, 180, 319, 279);
		add(textArea);
		boxSelD.setVisible(false);
		okDorm.setVisible(false);
		textArea.setVisible(false);
		getInfo.setVisible(false);
		
		getComendBtn.setVisible(false);
		
	}
	public JButton getGetInfo() {
		return getInfo;
	}
	public JTextArea getTextArea() {
		return textArea;
	}
	public void addListener(ActionListener l) {
		logOut.addActionListener(l);
		aboutDormBtn.addActionListener(l);
		getComendBtn.addActionListener(l);
		okDorm.addActionListener(l);
		getInfo.addActionListener(l);
	}
}
