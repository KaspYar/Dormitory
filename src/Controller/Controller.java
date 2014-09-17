package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import Strategy.Email;
import Strategy.Print;
import View.AdminView;
import View.ComendView;
import View.Login;
import View.MainContainer;
import View.StarView;
import View.StudView;
import View.Zvit;
import View.ZvitSt;

public class Controller {

	private MainContainer frame;
	private Login login;
	private StudView studView;
	private AdminView adminView;
	private ComendView comendView;
	private Zvit zvit;
	private ZvitSt zvitSt;
	private StarView starView;

	public Controller(MainContainer mc) {
		this.frame = mc;
		frame.setVisible(true);
		
		login = new Login();
		frame.showPane(login);
		login.addListener(new LoginListener());
		
	}
private class StarostViewListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			Object source = e.getSource();
			if (source == starView.getBtnGoBack()){
				frame.showPane(comendView);
			}
			if (source == starView.getBtnDoSt()){
				Model.Model.INSTANCE.doStarost((String)starView.getBoxD().getSelectedItem(), (String)starView.getBoxB().getSelectedItem(), (String)starView.getBoxSt().getSelectedItem());
				frame.showPane(comendView);
			}
			if (source == starView.getBoxD()){
				String [] bl = Model.Model.INSTANCE.getBlocks((String)starView.getBoxD().getSelectedItem());
				starView.getBoxB().removeAllItems();
				for (int i = 0; i<bl.length;i++) starView.getBoxB().addItem(bl[i]);
			}
			if (source == starView.getBoxB()){
				String [] st = Model.Model.INSTANCE.getStBlDorm((String)starView.getBoxD().getSelectedItem(), (String)starView.getBoxB().getSelectedItem());
				starView.getBoxSt().removeAllItems();
				for (int i = 0; i<st.length;i++) starView.getBoxSt().addItem(st[i]);
				starView.getBtnDoSt().setEnabled(true);
			}
			
		}
		
	}
private class ZvitStListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			Object source = e.getSource();
			if (source == zvitSt.getBtnGoBack()){
				System.out.println("Go back");
				frame.showPane(studView);
			}
			if (source == zvitSt.getPrint()){
				String res = new String ();
				res = zvitSt.getTextArea().getText();
				Model.Model.INSTANCE.printer = new Print(res);
				Model.Model.INSTANCE.printer.doIt();					
			}
			
		}
		
	}
private class ZvitListener implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source == zvit.getBtnGoBack()){
			System.out.println("Go back");
			frame.showPane(comendView);
		}
		if (source == zvit.getPrint()){			
			String res = new String ();
			res = zvit.getTextArea().getText();
			Model.Model.INSTANCE.printer = new Print(res);
			Model.Model.INSTANCE.printer.doIt();			
		}
		if (source == zvit.getBtnSend()){
			System.out.println("Email me");
			String res = new String ();
			res = zvit.getTextArea().getText();
			Model.Model.INSTANCE.printer = new Email(res);
			Model.Model.INSTANCE.printer.doIt();	
		}
		
	}
	
}
private class ComendViewListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			Object source = e.getSource();
			if (source == comendView.getLogOut()){
				frame.showPane(login);
			}
			if (source == comendView.getAccomodate()){
				if (comendView.getName1().getText().equals("") || comendView.getSurname().getText().equals("") || comendView.getId_code().getText().equals("")){
					comendView.getLblNewLabel().setVisible(true);
				}else{
					comendView.getLblNewLabel().setVisible(false);
					Model.Model.INSTANCE.accomodateStudent(comendView.getName1().getText(), comendView.getSurname().getText(),
							comendView.getId_code().getText(), (String)comendView.getAccRoom().getSelectedItem(),
							(int)comendView.getPayedToD().getValue(),(int)comendView.getPayedToM().getValue(),
					(int)comendView.getPayedToY().getValue(),(int)comendView.getPaySum().getValue());
					comendView = new ComendView();
					comendView.addListener(new ComendViewListener());
					frame.showPane(comendView);
					
				}
			}
			if (source == comendView.getStartosta()){
				starView = new StarView();
				starView.addListener(new StarostViewListener());
				frame.showPane(starView);
			}
			if (source == comendView.getBtnDoEvict()){
				comendView.getBoxEvict().setVisible(false);;
				comendView.getBtnDoEvict().setVisible(false);
				Model.Model.INSTANCE.evictStudent((String)comendView.getBoxEvict().getSelectedItem());
				comendView = new ComendView();
				comendView.addListener( new ComendViewListener());
				frame.showPane(comendView);
			}
			if (source == comendView.getEvict()){
				System.out.println("getEvict");
				comendView.getBtnChange().setVisible(false);
				comendView.getLabelR1().setVisible(false);
				comendView.getLabelR2().setVisible(false);
				comendView.getSpinnerD().setVisible(false);
				comendView.getSpinnerY().setVisible(false);
				comendView.getSpinnerM().setVisible(false);
				comendView.getSpinnerS().setVisible(false);
				comendView.getBtnUpdate().setVisible(false);
				
				comendView.getBoxEvict().setVisible(true);;
				comendView.getBtnDoEvict().setVisible(true);
				
			}
			if (source == comendView.getEvictNotPayed()){
				System.out.println("getEvictNotPayed");
			}
			if (source == comendView.getBtnGo2()){
				comendView.getTxtPrinterComend().append("Староста: ");
				comendView.getTxtPrinterComend().append(Model.Model.INSTANCE.getHeadManToComend((String)comendView.getBoxSelBl2().getSelectedItem()));
				comendView.getTxtPrinterComend().append("-----------------------------------------------------------------------\n");
			}
			if (source == comendView.getZvit()){
				zvit = new Zvit();
				zvit.addListener(new ZvitListener());
				zvit.getTextArea().append("\t\t\tЗвіт\n");
				zvit.getTextArea().append("Інформація про вільні місця:");
				zvit.getTextArea().append("\n");
				zvit.getTextArea().append(Model.Model.INSTANCE.getFreeToComend());
				zvit.getTextArea().append("-----------------------------------------------------------------------\n");
				
				zvit.getTextArea().append(Model.Model.INSTANCE.getRFToComend());
				zvit.getTextArea().append("-----------------------------------------------------------------------\n");
				
				zvit.getTextArea().append("Боржники:");
				zvit.getTextArea().append("\n");
				zvit.getTextArea().append(Model.Model.INSTANCE.getBadStudent());
				zvit.getTextArea().append("-----------------------------------------------------------------------\n");
				frame.showPane(zvit);
			}
			if (source == comendView.getBtnGo3()){				
				comendView.getTxtPrinterComend().append("Відомості про кімнату:");
				comendView.getTxtPrinterComend().append("\n");
				comendView.getTxtPrinterComend().append(Model.Model.INSTANCE.getAboutRoomToComend((String)comendView.getBoxRoom3().getSelectedItem()));
				comendView.getTxtPrinterComend().append("-----------------------------------------------------------------------\n");
				
				
			}
			if (source == comendView.getAboutRoom()){
				comendView.getScrollPane().setVisible(true);	
				
				comendView.getBtnGo2().setVisible(false);
				comendView.getBoxSelBl2().setVisible(false);
				comendView.getBtnOkSt().setVisible(false);
				comendView.getBoxStud().setVisible(false);
				
				comendView.getBoxRoom3().setVisible(true);
				comendView.getBtnGo3().setVisible(true);
			}
			if (source == comendView.getFree()){
				comendView.getScrollPane().setVisible(true);	
								
				comendView.getBoxRoom3().setVisible(false);
				comendView.getBtnGo3().setVisible(false);				
				comendView.getBtnGo2().setVisible(false);
				comendView.getBoxSelBl2().setVisible(false);	
				
				comendView.getTxtPrinterComend().append("Вільні місця: ");
				comendView.getTxtPrinterComend().append("\n");
				comendView.getTxtPrinterComend().append(Model.Model.INSTANCE.getFreeToComend());
				comendView.getTxtPrinterComend().append("-----------------------------------------------------------------------\n");
			}
			if (source == comendView.getHeadMan()){
				comendView.getScrollPane().setVisible(true);
				
				comendView.getBoxRoom3().setVisible(false);
				comendView.getBtnGo3().setVisible(false);
				comendView.getBtnOkSt().setVisible(false);
				comendView.getBoxStud().setVisible(false);
				
				comendView.getBtnGo2().setVisible(true);
				comendView.getBoxSelBl2().setVisible(true);	
			}
			if (source == comendView.getBtnAboutSt()){
				comendView.getScrollPane().setVisible(true);	
				
				comendView.getBtnGo2().setVisible(false);
				comendView.getBoxSelBl2().setVisible(false);
				
				comendView.getBoxRoom3().setVisible(false);
				comendView.getBtnGo3().setVisible(false);
				
				comendView.getBtnOkSt().setVisible(true);
				comendView.getBoxStud().setVisible(true);
				
				comendView.getBoxStud().removeAllItems();
				String [] students = Model.Model.INSTANCE.getAllSt();
				for (int i = 0; i<students.length;i++)comendView.getBoxStud().addItem(students[i]);
			}
			if (source == comendView.getBtnOkSt()){
				comendView.getTxtPrinterComend().append("Про студента: ");
				comendView.getTxtPrinterComend().append("\n");
				comendView.getTxtPrinterComend().append(Model.Model.INSTANCE.getAboutSt((String)comendView.getBoxStud().getSelectedItem()));
				comendView.getTxtPrinterComend().append("-----------------------------------------------------------------------\n");
			}
			if (source == comendView.getChangeSt()){
				comendView.getBtnChange().setVisible(true);
				comendView.getBoxEvict().setVisible(true);
				
				comendView.getBtnDoEvict().setVisible(false);
				comendView.getLabelR1().setVisible(false);
				comendView.getLabelR2().setVisible(false);
				comendView.getSpinnerD().setVisible(false);
				comendView.getSpinnerY().setVisible(false);
				comendView.getSpinnerM().setVisible(false);
				comendView.getSpinnerS().setVisible(false);
				comendView.getBtnUpdate().setVisible(false);
			}
			if (source == comendView.getBtnChange()){
				
				int [] pay = Model.Model.INSTANCE.getDataOfStudent((String)comendView.getBoxEvict().getSelectedItem());
				
				comendView.getSpinnerD().setValue(pay[0]);
				comendView.getSpinnerM().setValue(pay[1]);
				comendView.getSpinnerY().setValue(pay[2]);
				comendView.getSpinnerS().setValue(pay[3]);
				
				comendView.getLabelR1().setVisible(true);
				comendView.getLabelR2().setVisible(true);
				comendView.getSpinnerD().setVisible(true);
				comendView.getSpinnerY().setVisible(true);
				comendView.getSpinnerM().setVisible(true);
				comendView.getSpinnerS().setVisible(true);
				comendView.getBtnUpdate().setVisible(true);
			}
			if (source == comendView.getBtnUpdate()){
				
				Model.Model.INSTANCE.updateStudent((String)comendView.getBoxEvict().getSelectedItem(),(int)comendView.getSpinnerD().getValue(), (int)comendView.getSpinnerM().getValue(),
													(int)comendView.getSpinnerY().getValue(), (int)comendView.getSpinnerS().getValue());
				comendView = new ComendView();
				comendView.addListener(new ComendViewListener());
				frame.showPane(comendView);
				
			}
		}
		
	}
private class AdminViewListener implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source == adminView.getLogOut()){
			frame.showPane(login);
		}
		if (source == adminView.getOkDorm()){
			adminView.getOkDorm().setVisible(false);
			adminView.getBoxSelD().setVisible(false);
			Model.Model.INSTANCE.addBlock((String)adminView.getBoxSelD().getSelectedItem());
		}
		if (source == adminView.getAboutDormBtn()){
			adminView.getOkDorm().setVisible(true);
			adminView.getBoxSelD().setVisible(true);
			
			adminView.getTextArea().setVisible(false);
			adminView.getGetInfo().setVisible(false);
		}
		if (source == adminView.getGetComendBtn()){
			System.out.println("Info about Comend");
			adminView.getOkDorm().setVisible(false);
			adminView.getTextArea().setVisible(true);
			adminView.getBoxSelD().setVisible(true);
			adminView.getGetInfo().setVisible(true);
			
		}
		if (source == adminView.getGetInfo()){
			//adminView.getTextArea().append(Model.Model.INSTANCE.getInfoToAdmin((String)adminView.getBoxSelD().getSelectedItem()));
		}
		
	}
	
}
private class StudViewListener implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		String log = login.getLogin().getText();
		if(source == studView.getLogOut()){
			studView.getScrollPane().setVisible(true);
			login.getLogin().setText("");
			frame.showPane(login);
		}
		if (source == studView.getBtnZvit()){
			zvitSt = new ZvitSt();
			zvitSt.addListener(new ZvitStListener());
			zvitSt.getTextArea().append("\t\t\tЗвіт\n");
			zvitSt.getTextArea().append("Адреса гуртожитка: ");
			zvitSt.getTextArea().append(Model.Model.INSTANCE.getDormToStudent(log));
			zvitSt.getTextArea().append("\n");
			zvitSt.getTextArea().append("-----------------------------------------------------------------------\n");
			
			zvitSt.getTextArea().append("Староста блоку: ");
			zvitSt.getTextArea().append(Model.Model.INSTANCE.getHeadToStudent(log));
			zvitSt.getTextArea().append("\n");
			zvitSt.getTextArea().append("-----------------------------------------------------------------------\n");
			
			zvitSt.getTextArea().append("Ваша кімната: ");
			zvitSt.getTextArea().append(Model.Model.INSTANCE.getRoomToStudent(log));
			zvitSt.getTextArea().append("\n");
			zvitSt.getTextArea().append("-----------------------------------------------------------------------\n");
			
			zvitSt.getTextArea().append("Місткість кімнати \\ вільних місць: ");
			zvitSt.getTextArea().append(Model.Model.INSTANCE.getCapacToStudent(log));
			zvitSt.getTextArea().append("\n");
			zvitSt.getTextArea().append("-----------------------------------------------------------------------\n");
			
			frame.showPane(zvitSt);
		}
		if (source == studView.getGetRoom()){
			studView.getScrollPane().setVisible(true);	
			
			studView.getTxtPrinter().append("Ваша кімната: ");
			studView.getTxtPrinter().append(Model.Model.INSTANCE.getRoomToStudent(log));
			studView.getTxtPrinter().append("\n");
			studView.getTxtPrinter().append("-----------------------------------------------------------------------\n");
			
			
		}
		if (source == studView.getGetAmount()){
			studView.getScrollPane().setVisible(true);
			studView.getTxtPrinter().append("Місткість кімнати \\ вільних місць: ");
			studView.getTxtPrinter().append(Model.Model.INSTANCE.getCapacToStudent(log));
			studView.getTxtPrinter().append("\n");
			studView.getTxtPrinter().append("-----------------------------------------------------------------------\n");
			
		}
		if (source == studView.getGetHeadMan()){
			studView.getScrollPane().setVisible(true);
			studView.getTxtPrinter().append("Староста блоку: ");
			studView.getTxtPrinter().append(Model.Model.INSTANCE.getHeadToStudent(log));
			studView.getTxtPrinter().append("\n");
			studView.getTxtPrinter().append("-----------------------------------------------------------------------\n");
		}
		if (source == studView.getBtnDorm()){
			studView.getScrollPane().setVisible(true);
			studView.getTxtPrinter().append("Адреса гуртожитка: ");
			studView.getTxtPrinter().append(Model.Model.INSTANCE.getDormToStudent(log));
			studView.getTxtPrinter().append("\n");
			studView.getTxtPrinter().append("-----------------------------------------------------------------------\n");
		}
	}
	
}
private class LoginListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Object source = e.getSource();
			if(source == login.getSpravka()){
				login.getInfoTool().setVisible(true);
			}
			if (source == login.getLogInBtn()){
				System.out.println("Pressed Log In");
				String log = login.getLogin().getText();
				char[]  pswd = login.getPswd().getPassword();
				String pswdStr = new String(pswd);
				login.getInfoTool().setVisible(false);
				
				if (log.equals("") || pswdStr.equals("")){
					login.getPswd().setText("");
					login.getAttentLbl().setText("Перевірте правильність даних!");
					
					
					
				}
				else if (Model.Model.INSTANCE.doLogInAdmin(log, pswdStr)){
					adminView = new AdminView();
					adminView.addListener(new AdminViewListener());
					login.getLogin().setText("");
					login.getPswd().setText("");
					login.getAttentLbl().setText("");
					frame.showPane(adminView);
				} else
					try {
						if (Model.Model.INSTANCE.doLogInStudent(log, pswdStr)){
							studView = new StudView("Ви увійшли як " + log);
							studView.addListener(new StudViewListener());
							login.getPswd().setText("");
							login.getAttentLbl().setText("");
							frame.showPane(studView);
						}
						else if (Model.Model.INSTANCE.doLogInComend(log, pswdStr)){
							comendView = new ComendView();
							comendView.addListener(new ComendViewListener() );
							login.getLogin().setText("");
							login.getPswd().setText("");
							login.getAttentLbl().setText("");
							frame.showPane(comendView);
						}
						else{
							login.getPswd().setText("");
							login.getAttentLbl().setText("Перевірте правильність даних!");
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					
							
			}
		}

	}
}
