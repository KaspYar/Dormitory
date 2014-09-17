package View;

import javax.swing.JLabel;
import javax.swing.JButton;

import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import java.awt.Color;

import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JDesktopPane;
import javax.swing.JTextField;
import javax.swing.JLayeredPane;
import javax.swing.JToolBar;
import javax.swing.JInternalFrame;
import javax.swing.SpinnerListModel;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

import java.awt.BorderLayout;

import javax.swing.JSpinner;
import javax.swing.JComboBox;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

public class ComendView extends MyPanel {
	private JLabel signAs;
	private JButton logOut;
	private JSeparator separator;
	private JTabbedPane tabbedPane;
	private JPanel general;
	private JPanel in;
	private JPanel out;
	private JButton aboutRoom;
	private JButton accomodate;
	private JButton evict;
	private JButton evictNotPayed;
	private JButton changeSt;
	private JButton free;
	private JButton headMan;
	private JTextArea txtPrinterComend;
	private JScrollPane scrollPane;
	private JComboBox boxSelBl2;
	private JButton btnGo2;
	private JComboBox boxRoom3;
	private JButton btnGo3;
	private JButton zvit;
	private JButton btnDoEvict;
	private JComboBox boxEvict;

	private JTextField name1;
	
	public JButton getChangeSt() {
		return changeSt;
	}


	public JSpinner getPaySum() {
		return paySum;
	}

	private JTextField surname;
	private JTextField id_code;
	private JComboBox accRoom;
	private JSpinner paySum;
	private JLabel lblNewLabel;
	private JSpinner payedToD;
	private JSpinner PayedToM;
	private JSpinner payedToY;
	private JButton startosta;
	private JButton btnOkSt;
	private JComboBox boxStud;
	private JButton btnAboutSt;
	private JButton btnChange;
	private JButton btnUpdate;
	private JLabel labelR1;
	private JLabel labelR2;
	private JSpinner spinnerD;
	private JSpinner spinnerM;
	private JSpinner spinnerY;
	private JSpinner spinnerS;

	public JButton getBtnAboutSt() {
		return btnAboutSt;
	}


	public JButton getBtnOkSt() {
		return btnOkSt;
	}


	public JComboBox getBoxStud() {
		return boxStud;
	}


	public JComboBox getBoxSelBl2() {
		return boxSelBl2;
	}


	public JButton getStartosta() {
		return startosta;
	}


	public JTextField getSurname() {
		return surname;
	}

	public JTextField getId_code() {
		return id_code;
	}

	public JComboBox getAccRoom() {
		return accRoom;
	}


	public JButton getBtnGo2() {
		return btnGo2;
	}

	public JComboBox getBoxRoom3() {
		return boxRoom3;
	}

	public JButton getBtnGo3() {
		return btnGo3;
	}

	public JButton getAboutRoom() {
		return aboutRoom;
	}

	public JButton getAccomodate() {
		return accomodate;
	}

	public JButton getEvict() {
		return evict;
	}

	public JButton getEvictNotPayed() {
		return evictNotPayed;
	}


	public JButton getLogOut() {
		return logOut;
	}

	public JButton getFree() {
		return free;
	}

	public JButton getHeadMan() {
		return headMan;
	}

	public ComendView() {
		getAttentLbl().setSize(433, 40);
		getAttentLbl().setLocation(185, 511);

		signAs = new JLabel(
				"\u0412\u0438 \u0443\u0432\u0456\u0439\u0448\u043B\u0438 \u044F\u043A \u043A\u043E\u043C\u0435\u043D\u0434\u0430\u043D\u0442");
		signAs.setForeground(Color.WHITE);
		signAs.setHorizontalAlignment(SwingConstants.RIGHT);
		signAs.setFont(new Font("Tahoma", Font.PLAIN, 14));
		signAs.setBounds(220, 10, 400, 30);
		add(signAs);

		logOut = new JButton(
				"\u0417\u043C\u0456\u043D\u0438\u0442\u0438 \u043A\u043E\u0440\u0438\u0441\u0442\u0443\u0432\u0430\u0447\u0430");
		logOut.setBounds(630, 10, 160, 30);
		add(logOut);

		separator = new JSeparator();
		separator.setBounds(0, 56, 800, 14);
		add(separator);

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(21, 66, 769, 406);
		add(tabbedPane);

		general = new JPanel();
		general.setBackground(new Color(102, 178, 255));
		general.setLayout(null);
		tabbedPane.addTab("Загальна інформація", null, general, null);

		free = new JButton(
				"\u0406\u043D\u0444\u043E\u0440\u043C\u0430\u0446\u0456\u044F \u043F\u0440\u043E \u0432\u0456\u043B\u044C\u043D\u0456 \u043C\u0456\u0441\u0446\u044F");
		free.setBounds(10, 40, 240, 30);
		general.add(free);

		headMan = new JButton(
				"\u0412\u0437\u043D\u0430\u0442\u0438 \u0441\u0442\u0430\u0440\u043E\u0441\u0442\u0443 \u0431\u043B\u043E\u043A\u0443");
		headMan.setBounds(10, 100, 240, 30);
		general.add(headMan);

		aboutRoom = new JButton(
				"\u0412\u0456\u0434\u043E\u043C\u043E\u0441\u0442\u0456 \u043F\u0440\u043E \u043A\u0456\u043C\u043D\u0430\u0442\u0443");
		aboutRoom.setBounds(10, 160, 240, 30);
		general.add(aboutRoom);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(358, 40, 396, 327);
		general.add(scrollPane);

		String[] block = Model.Model.INSTANCE.getDormsAndBlocks();

		String[] room = Model.Model.INSTANCE.getDormsAndRooms();

		txtPrinterComend = new JTextArea();
		scrollPane.setViewportView(txtPrinterComend);
		scrollPane.setVisible(false);
		boxSelBl2 = new JComboBox(block);
		boxSelBl2.setBounds(10, 340, 240, 30);
		general.add(boxSelBl2);
		boxSelBl2.setVisible(false);

		btnGo2 = new JButton("\u0417\u043D\u0430\u0439\u0442\u0438");
		btnGo2.setBounds(258, 340, 90, 30);
		general.add(btnGo2);
		btnGo2.setVisible(false);
		boxRoom3 = new JComboBox(room);
		boxRoom3.setBounds(10, 340, 240, 30);
		general.add(boxRoom3);
		boxRoom3.setVisible(false);

		btnGo3 = new JButton("\u0417\u043D\u0430\u0439\u0442\u0438");
		btnGo3.setBounds(258, 340, 90, 30);
		general.add(btnGo3);
		
		startosta = new JButton("\u041F\u0440\u0438\u0437\u043D\u0430\u0447\u0438\u0442\u0438 \u0441\u0442\u043E\u0440\u043E\u0441\u0442\u0443");
		startosta.setBounds(10, 220, 240, 30);
		general.add(startosta);
		
		btnAboutSt = new JButton("\u041F\u0440\u043E \u0441\u0442\u0443\u0434\u0435\u043D\u0442\u0430");
		btnAboutSt.setBounds(10, 280, 240, 30);
		general.add(btnAboutSt);
		
		boxStud = new JComboBox();
		boxStud.setBounds(10, 340, 240, 30);
		general.add(boxStud);
		boxStud.setVisible(false);
		
		btnOkSt = new JButton("\u0412\u0437\u043D\u0430\u0442\u0438");
		btnOkSt.setBounds(258, 340, 90, 30);
		general.add(btnOkSt);
		btnOkSt.setVisible(false);
		btnGo3.setVisible(false);

		in = new JPanel();
		in.setBackground(new Color(102, 178, 255));
		in.setLayout(null);
		tabbedPane.addTab("Поселення студента", null, in, null);

		accomodate = new JButton(
				"\u041F\u043E\u0441\u0435\u043B\u0438\u0442\u0438 \u0441\u0442\u0443\u0434\u0435\u043D\u0442\u0430");
		accomodate.setBounds(243, 321, 240, 30);
		in.add(accomodate);

		name1 = new JTextField();
		name1.setBounds(150, 40, 201, 30);
		in.add(name1);
		name1.setColumns(10);

		surname = new JTextField();
		surname.setText("");
		surname.setBounds(150, 85, 201, 30);
		in.add(surname);
		surname.setColumns(10);

		id_code = new JTextField();
		id_code.setBounds(150, 130, 201, 30);
		in.add(id_code);
		id_code.setColumns(10);

		String[] roomFree = Model.Model.INSTANCE.getDormsAndRoomsFree();
		accRoom = new JComboBox(roomFree);
		accRoom.setBounds(150, 175, 201, 30);
		in.add(accRoom);

		SpinnerModel mdl = new SpinnerNumberModel(0,0,2,1);
		paySum = new JSpinner(mdl);
		paySum.setBounds(150, 265, 201, 30);
		in.add(paySum);

		JLabel label = new JLabel("\u0406\u043C'\u044F");
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		label.setBounds(20, 40, 120, 30);
		in.add(label);

		JLabel label_1 = new JLabel(
				"\u041F\u0440\u0456\u0437\u0432\u0438\u0449\u0435");
		label_1.setHorizontalAlignment(SwingConstants.RIGHT);
		label_1.setBounds(20, 85, 120, 37);
		in.add(label_1);

		JLabel label_2 = new JLabel(
				"\u0406\u0434\u0435\u043D\u0442\u0438\u0444\u0456\u043A\u0430\u0446\u0456\u0439\u043D\u0438\u0439 \u043A\u043E\u0434");
		label_2.setHorizontalAlignment(SwingConstants.RIGHT);
		label_2.setBounds(0, 120, 140, 48);
		in.add(label_2);

		JLabel label_3 = new JLabel(
				"\u041A\u0456\u043C\u043D\u0430\u0442\u0430");
		label_3.setHorizontalAlignment(SwingConstants.RIGHT);
		label_3.setBounds(20, 175, 120, 30);
		in.add(label_3);

		JLabel label_4 = new JLabel(
				"\u041E\u043F\u043B\u0430\u0447\u0435\u043D\u043E \u0434\u043E(\u0434-\u043C-\u0440)");
		label_4.setHorizontalAlignment(SwingConstants.RIGHT);
		label_4.setBounds(20, 220, 120, 30);
		in.add(label_4);

		JLabel label_5 = new JLabel(
				"\u041E\u043F\u043B\u0430\u0442\u0430 \u0437\u0430 \u043B\u0456\u0442\u043E(\u043C\u0456\u0441\u044F\u0446\u0456\u0432)");
		label_5.setHorizontalAlignment(SwingConstants.RIGHT);
		label_5.setBounds(0, 265, 140, 30);
		in.add(label_5);
		
		lblNewLabel = new JLabel("\u0412\u0438 \u043D\u0435 \u0437\u0430\u043F\u043E\u0432\u043D\u0438\u043B\u0438 \u0443\u0441\u0456 \u043F\u043E\u043B\u044F!");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 21));
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setBounds(403, 85, 351, 143);
		in.add(lblNewLabel);
		
		SpinnerModel mdlD = new SpinnerNumberModel(1,1,31,1);
		payedToD = new JSpinner(mdlD);
		payedToD.setBounds(150, 216, 40, 30);
		in.add(payedToD);
		
		SpinnerModel mdlM = new SpinnerNumberModel(1,1,12,1);
		PayedToM = new JSpinner(mdlM);
		PayedToM.setBounds(200, 216, 46, 30);
		in.add(PayedToM);
		
		SpinnerModel mdlY = new SpinnerNumberModel(2013,2013,2020,1);
		payedToY = new JSpinner(mdlY);
		payedToY.setBounds(256, 216, 95, 30);
		in.add(payedToY);
		lblNewLabel.setVisible(false);

		out = new JPanel();
		out.setBackground(new Color(102, 178, 255));
		out.setLayout(null);
		tabbedPane.addTab("\u0420\u0435\u0434\u0430\u0433\u0443\u0432\u0430\u0442\u0438 \u0441\u0442\u0443\u0434\u0435\u043D\u0442\u0430", null, out, "\u0420\u0435\u0434\u0430\u0433\u0443\u0432\u0430\u0442\u0438 \u0434\u0430\u043D\u0456 \u0441\u0442\u0443\u0434\u0435\u043D\u0442\u0430 \u0430\u0431\u043E \u0432\u0438\u0441\u0435\u043B\u0438\u0442\u0438 \u0439\u043E\u0433\u043E");

		evict = new JButton(
				"\u0412\u0438\u0441\u0435\u043B\u0438\u0442\u0438 \u043F\u0435\u0432\u043D\u043E\u0433\u043E \u0441\u0442\u0443\u0434\u0435\u043D\u0442\u0430");
		evict.setBounds(10, 40, 240, 30);
		out.add(evict);

		evictNotPayed = new JButton(
				"\u0412\u0438\u0441\u0435\u043B\u0438\u0442\u0438 \u0432\u0441\u0456\u0445 \u0431\u043E\u0440\u0436\u043D\u0438\u043A\u0456\u0432");
		evictNotPayed.setBounds(10, 100, 240, 30);
		out.add(evictNotPayed);

		changeSt = new JButton(
				"\u0420\u0435\u0434\u0430\u0433\u0443\u0432\u0430\u0442\u0438 \u0441\u0442\u0443\u0434\u0435\u043D\u0442\u0430");
		changeSt.setBounds(10, 160, 240, 30);
		out.add(changeSt);

		btnDoEvict = new JButton(
				"\u0412\u0438\u0441\u0435\u043B\u0438\u0442\u0438");
		btnDoEvict.setBounds(570, 40, 130, 30);
		btnDoEvict.setVisible(false);
		out.add(btnDoEvict);

		String[] stud = Model.Model.INSTANCE.getAllStud();
		boxEvict = new JComboBox(stud);
		boxEvict.setBounds(347, 40, 173, 30);
		boxEvict.setVisible(false);
		out.add(boxEvict);
		
		btnChange = new JButton("\u0420\u0435\u0434\u0430\u0433\u0443\u0432\u0430\u0442\u0438");
		btnChange.setBounds(570, 40, 130, 30);
		btnChange.setVisible(false);
		out.add(btnChange);
		
		labelR1 = new JLabel("\u041E\u043F\u043B\u0430\u0447\u0435\u043D\u043E \u0434\u043E(\u0434-\u043C-\u0440)");
		labelR1.setHorizontalAlignment(SwingConstants.RIGHT);
		labelR1.setBounds(340, 100, 120, 30);
		out.add(labelR1);
		
		labelR2 = new JLabel("\u041E\u043F\u043B\u0430\u0442\u0430 \u0437\u0430 \u043B\u0456\u0442\u043E(\u043C\u0456\u0441\u044F\u0446\u0456\u0432)");
		labelR2.setHorizontalAlignment(SwingConstants.RIGHT);
		labelR2.setBounds(340, 141, 120, 30);
		out.add(labelR2);
		
		spinnerD = new JSpinner(mdlD);
		spinnerD.setBounds(471, 100, 40, 30);
		out.add(spinnerD);
		
		spinnerM = new JSpinner(mdlM);
		spinnerM.setBounds(521, 100, 40, 30);
		out.add(spinnerM);
		
		spinnerY = new JSpinner(mdlY);
		spinnerY.setBounds(570, 100, 95, 30);
		out.add(spinnerY);
		
		spinnerS = new JSpinner(mdl);
		spinnerS.setBounds(471, 141, 40, 30);
		out.add(spinnerS);
		
		btnUpdate = new JButton("\u0417\u0430\u0441\u0442\u043E\u0441\u0443\u0432\u0430\u0442\u0438");
		btnUpdate.setBounds(439, 196, 134, 30);
		out.add(btnUpdate);
		
		labelR1.setVisible(false);
		labelR2.setVisible(false);
		spinnerD.setVisible(false);
		spinnerY.setVisible(false);
		spinnerM.setVisible(false);
		spinnerS.setVisible(false);
		btnUpdate.setVisible(false);

		zvit = new JButton(
				"\u041D\u0430\u0434\u0440\u0443\u043A\u0443\u0432\u0430\u0442\u0438 \u0437\u0432\u0456\u0442");
		zvit.setBounds(280, 511, 228, 40);
		add(zvit);

		inProcess();
		
	

	}

	
	
	
	public JButton getBtnChange() {
		return btnChange;
	}


	public JSpinner getPayedToD() {
		return payedToD;
	}


	public JSpinner getPayedToM() {
		return PayedToM;
	}


	public JSpinner getPayedToY() {
		return payedToY;
	}


	public JLabel getLblNewLabel() {
		return lblNewLabel;
	}


	public JTextField getName1() {
		return name1;
	}


	public JButton getBtnDoEvict() {
		return btnDoEvict;
	}

	public JComboBox getBoxEvict() {
		return boxEvict;
	}

	public void setBoxSelBl2(JComboBox boxSelBl2) {
		this.boxSelBl2 = boxSelBl2;
	}

	private void inProcess() {
		evictNotPayed.setEnabled(false);
		
	}

	public JScrollPane getScrollPane() {
		return scrollPane;
	}

	public void addListener(ActionListener l) {
		logOut.addActionListener(l);
		aboutRoom.addActionListener(l);
		accomodate.addActionListener(l);
		evict.addActionListener(l);
		evictNotPayed.addActionListener(l);
		changeSt.addActionListener(l);
		free.addActionListener(l);
		headMan.addActionListener(l);
		btnGo2.addActionListener(l);
		btnGo3.addActionListener(l);
		zvit.addActionListener(l);
		btnDoEvict.addActionListener(l);
		startosta.addActionListener(l);
		btnOkSt.addActionListener(l);
		btnAboutSt.addActionListener(l);
		btnChange.addActionListener(l);
		btnUpdate.addActionListener(l);
		
	}

	public JButton getBtnUpdate() {
		return btnUpdate;
	}


	public JLabel getLabelR1() {
		return labelR1;
	}


	public JLabel getLabelR2() {
		return labelR2;
	}


	public JSpinner getSpinnerD() {
		return spinnerD;
	}


	public JSpinner getSpinnerM() {
		return spinnerM;
	}


	public JSpinner getSpinnerY() {
		return spinnerY;
	}


	public JSpinner getSpinnerS() {
		return spinnerS;
	}


	public JButton getZvit() {
		return zvit;
	}

	public JTextArea getTxtPrinterComend() {
		return txtPrinterComend;
	}
}
