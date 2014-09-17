package View;
import java.awt.Component;
import java.awt.event.ActionListener;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JSeparator;

public class Zvit extends MyPanel {	
	private Component scrollPane;
	private JTextArea textArea;
	private JButton print;
	private JButton btnGoBack;
	
	

	public Component getScrollPane() {
		return scrollPane;
	}

	public JTextArea getTextArea() {
		return textArea;
	}

	public JButton getPrint() {
		return print;
	}

	public JButton getBtnGoBack() {
		return btnGoBack;
	}

	public JSeparator getSeparator() {
		return separator;
	}

	private JSeparator separator;
	private JButton btnSend;
	public void addListener(ActionListener l) {
		btnGoBack.addActionListener(l);
		print.addActionListener(l);
		btnSend.addActionListener(l);

	}
	public JButton getBtnSend() {
		return btnSend;
	}

	public Zvit() {
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(78, 73, 620, 328);
		add(scrollPane);
		
		textArea = new JTextArea();
		((JScrollPane) scrollPane).setViewportView(textArea);
		
		print = new JButton("\u0414\u0440\u0443\u043A\u0443\u0432\u0430\u0442\u0438");
		print.setBounds(400, 420, 160, 30);
		add(print);
		
		btnGoBack = new JButton("\u041D\u0430\u0437\u0430\u0434");
		btnGoBack.setBounds(679, 11, 89, 23);
		add(btnGoBack);

		separator = new JSeparator();
		separator.setBounds(0, 44, 800, 23);
		add(separator);
		
		btnSend = new JButton("\u041D\u0430\u0434\u0456\u0441\u043B\u0430\u0442\u0438");
		btnSend.setBounds(180, 420, 160, 30);
		add(btnSend);
	}
}
