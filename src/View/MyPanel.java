package View;

import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class MyPanel extends JPanel {
	private JButton exitButton;
	private JLabel attentLbl;

	public JLabel getAttentLbl() {
		return attentLbl;
	}


	public MyPanel() {
		setLayout(null);
		setBackground(new Color(102, 178, 255));

		exitButton = new JButton("Вийти");
		exitButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Model.Model.INSTANCE.closeCon();
				System.exit(0);

			}
		});
		exitButton.setBounds(668, 511, 100, 40);
		add(exitButton);
		
		Component horizontalStrut = Box.createHorizontalStrut(20);
		horizontalStrut.setBounds(802, 511, -869, -35);
		add(horizontalStrut);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 480, 800, 20);
		add(separator);
		
		attentLbl = new JLabel("");
		attentLbl.setHorizontalAlignment(SwingConstants.CENTER);
		attentLbl.setForeground(Color.RED);
		attentLbl.setFont(new Font("Tahoma", Font.PLAIN, 22));
		attentLbl.setBounds(191, 511, 432, 40);
		add(attentLbl);
	}

	public JButton getExitButton() {
		return exitButton;
	}

	public void addListener(ActionListener l) {
		exitButton.addActionListener(l);

	}
}
