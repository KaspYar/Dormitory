package View;
import java.awt.Component;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JSeparator;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class StarView extends MyPanel{
	private JButton btnGoBack;
	private JButton btnOkDorm;
	private JButton btnOkBlock;
	private JButton btnDoSt;
	private JComboBox boxD;
	private JComboBox boxB;
	private JComboBox boxSt;
	
	public JButton getBtnGoBack() {
		return btnGoBack;
	}
	public JButton getBtnOkDorm() {
		return btnOkDorm;
	}
	public JButton getBtnOkBlock() {
		return btnOkBlock;
	}
	public JButton getBtnDoSt() {
		return btnDoSt;
	}
	public JComboBox getBoxD() {
		return boxD;
	}
	public JComboBox getBoxB() {
		return boxB;
	}
	public JComboBox getBoxSt() {
		return boxSt;
	}
	public StarView() {
		
		btnGoBack = new JButton("\u041D\u0430\u0437\u0430\u0434");
		btnGoBack.setBounds(679, 11, 89, 23);
		add(btnGoBack);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 44, 800, 23);
		add(separator);
		
		btnOkDorm = new JButton("\u041F\u0440\u0438\u0439\u043D\u044F\u0442\u0438 \u0433\u0443\u0440\u0442\u043E\u0436\u0438\u0442\u043E\u043A");
		btnOkDorm.setBounds(420, 80, 180, 30);
		add(btnOkDorm);
		
		btnOkBlock = new JButton("\u041F\u0440\u0438\u0439\u043D\u044F\u0442\u0438 \u0431\u043B\u043E\u043A");
		btnOkBlock.setBounds(420, 140, 180, 30);
		add(btnOkBlock);
		
		btnDoSt = new JButton("\u041F\u0440\u0438\u0437\u043D\u0430\u0447\u0438\u0442\u0438 \u0441\u0442\u0430\u0440\u043E\u0441\u0442\u043E\u044E");
		btnDoSt.setBounds(160, 266, 198, 30);
		add(btnDoSt);
		
		String [] d = Model.Model.INSTANCE.getDorms();
		boxD = new JComboBox(d);
		boxD.setBounds(160, 80, 198, 30);
		add(boxD);
		
		boxB = new JComboBox();
		boxB.setBounds(160, 140, 198, 30);
		add(boxB);
		
		boxSt = new JComboBox();
		boxSt.setBounds(160, 200, 198, 30);
		add(boxSt);
		
		JLabel label = new JLabel("\u0412\u0438\u0431\u0435\u0440\u0456\u0442\u044C \u0433\u0443\u0440\u0442\u043E\u0436\u0438\u0442\u043E\u043A");
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		label.setBounds(10, 80, 140, 30);
		add(label);
		
		JLabel label_1 = new JLabel("\u0412\u0438\u0431\u0435\u0440\u0456\u0442\u044C \u0431\u043B\u043E\u043A");
		label_1.setHorizontalAlignment(SwingConstants.RIGHT);
		label_1.setBounds(21, 140, 129, 30);
		add(label_1);
		
		JLabel label_2 = new JLabel("\u0412\u0438\u0431\u0435\u0440\u0456\u0442\u044C \u0441\u0442\u0443\u0434\u0435\u043D\u0442\u0430");
		label_2.setHorizontalAlignment(SwingConstants.RIGHT);
		label_2.setBounds(10, 200, 140, 30);
		add(label_2);
		btnOkDorm.setVisible(false);
		btnOkBlock.setVisible(false);
		btnDoSt.setEnabled(false);
	}
	public void addListener(ActionListener l) {
		btnGoBack.addActionListener(l);
		btnOkDorm.addActionListener(l);
		btnOkBlock.addActionListener(l);
		btnDoSt.addActionListener(l);
		boxD.addActionListener(l);
		boxB.addActionListener(l);
		boxSt.addActionListener(l);

	}
}
