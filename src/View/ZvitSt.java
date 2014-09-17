package View;
import java.awt.Component;

import java.awt.Component;
import java.awt.event.ActionListener;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JSeparator;

public class ZvitSt extends MyPanel {
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
	public void addListener(ActionListener l) {
		btnGoBack.addActionListener(l);
		print.addActionListener(l);

	}
	public ZvitSt() {
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(78, 73, 620, 328);
		add(scrollPane);
		
		textArea = new JTextArea();
		((JScrollPane) scrollPane).setViewportView(textArea);
		
		print = new JButton("\u0414\u0440\u0443\u043A\u0443\u0432\u0430\u0442\u0438");
		print.setBounds(336, 428, 159, 23);
		add(print);
		
		btnGoBack = new JButton("\u041D\u0430\u0437\u0430\u0434");
		btnGoBack.setBounds(679, 11, 89, 23);
		add(btnGoBack);

		separator = new JSeparator();
		separator.setBounds(0, 44, 800, 23);
		add(separator);
	}
}
