package View;
import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

public class StudView extends MyPanel{
	private JButton getRoom;
	private JButton getAmount;
	private JButton getHeadMan;
	private JButton btnDorm;
	private JLabel logAs;
	private JButton logOut;
	private JLabel youCan;
	private JSeparator separator;
	private JTextArea txtPrinter;
	private JScrollPane scrollPane;
	private JButton btnZvit;
	
	
	public JButton getBtnZvit() {
		return btnZvit;
	}


	public JScrollPane getScrollPane() {
		return scrollPane;
	}


	public JTextArea getTxtPrinter() {
		return txtPrinter;
	}




	public StudView(String surname) {
		getAttentLbl().setLocation(187, 511);
		setToolTipText("");
		
		getRoom = new JButton("\u0412\u0437\u043D\u0430\u0442\u0438 \u043D\u043E\u043C\u0435\u0440 \u0441\u0432\u043E\u0454\u0457 \u043A\u0456\u043C\u043D\u0430\u0442\u0438");
		getRoom.setBounds(100, 130, 270, 25);
		add(getRoom);
		
		getAmount = new JButton("\u041C\u0456\u0441\u0442\u043A\u0456\u0441\u0442\u044C \u043A\u0456\u043C\u043D\u0430\u0442\u0438/\u043A-\u0442\u044C \u0432\u0456\u043B\u044C\u043D\u0438\u0445 \u043C\u0456\u0441\u0446\u044C");
		getAmount.setBounds(100, 180, 270, 25);
		add(getAmount);
		
		getHeadMan = new JButton("\u0412\u0437\u043D\u0430\u0442\u0438 \u0441\u0442\u0430\u0440\u043E\u0441\u0442\u0443 \u0431\u043B\u043E\u043A\u0443");
		getHeadMan.setBounds(100, 230, 270, 25);
		add(getHeadMan);
		
		btnDorm = new JButton("\u0412\u0437\u043D\u0430\u0442\u0438 \u0430\u0434\u0440\u0435\u0441\u0443 \u0433\u0443\u0440\u0442\u043E\u0436\u0438\u0442\u043A\u0430");
		btnDorm.setBounds(100, 280, 270, 25);
		add(btnDorm);
		
		logAs = new JLabel("\u0412\u0438 \u0437\u0430\u0439\u0448\u043B\u0438 \u044F\u043A \u0441\u0442\u0443\u0434\u0435\u043D\u0442");
		logAs.setForeground(Color.WHITE);
		logAs.setHorizontalAlignment(SwingConstants.RIGHT);
		logAs.setFont(new Font("Tahoma", Font.PLAIN, 14));
		logAs.setBounds(220, 10, 400, 30);
		logAs.setText(surname);
		add(logAs);
		
		logOut = new JButton("\u0417\u043C\u0456\u043D\u0438\u0442\u0438 \u043A\u043E\u0440\u0438\u0441\u0442\u0443\u0432\u0430\u0447\u0430");
		logOut.setBounds(630, 10, 160, 30);
		add(logOut);
		
		youCan = new JLabel("\u0412\u0438 \u043C\u043E\u0436\u0435\u0442\u0435: ");
		youCan.setFont(new Font("Arial", Font.PLAIN, 20));
		youCan.setForeground(Color.BLACK);
		youCan.setBounds(60, 75, 184, 50);
		add(youCan);
		
		separator = new JSeparator();
		separator.setBounds(0, 56, 800, 14);
		add(separator);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(461, 130, 290, 320);
		add(scrollPane);
		
		txtPrinter = new JTextArea();
		scrollPane.setViewportView(txtPrinter);
		
		btnZvit = new JButton("\u0414\u0440\u0443\u043A\u0443\u0432\u0430\u0442\u0438 \u0437\u0432\u0456\u0442");
		btnZvit.setBounds(280, 511, 228, 40);
		add(btnZvit);
		scrollPane.setVisible(false);
		
		
	}






	public void setLogAs(JLabel logAs) {
		this.logAs = logAs;
	}


	public JButton getGetRoom() {
		return getRoom;
	}


	public JButton getGetAmount() {
		return getAmount;
	}


	public JButton getGetHeadMan() {
		return getHeadMan;
	}


	public JButton getBtnDorm() {
		return btnDorm;
	}


	public JLabel getLogAs() {
		return logAs;
	}


	public JButton getLogOut() {
		return logOut;
	}
	public void addListener(ActionListener l) {
		logOut.addActionListener(l);
		getRoom.addActionListener(l);
		getAmount.addActionListener(l);
		getHeadMan.addActionListener(l);
		btnDorm.addActionListener(l);
		btnZvit.addActionListener(l);

	}
}
