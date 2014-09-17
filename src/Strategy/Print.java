package Strategy;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

public class Print implements Printable,Str {

	String [] text;
	public Print(String  s){
		text = s.split("\n");
	}
	public int print(Graphics g, PageFormat pf, int page) throws
	PrinterException {
	
		if (page > 0) {
		return NO_SUCH_PAGE;
		}
		Graphics2D g2d = (Graphics2D)g;
		g2d.translate(pf.getImageableX(), pf.getImageableY());
		g.drawString("Çâ³ò\n", 250, 50);
		for(int i = 0;i<text.length;i++){
			g.drawString(this.text[i]+"\n", 100, 100 + i*20);
		}
		
		return PAGE_EXISTS;
	}
	@Override
	public void doIt() {
		PrinterJob job = PrinterJob.getPrinterJob();
		job.setPrintable(this);
		boolean ok = job.printDialog();
		if (ok) {
			try {
			job.print();
			} catch (PrinterException ex) {
			}
		}
		
	}
}
