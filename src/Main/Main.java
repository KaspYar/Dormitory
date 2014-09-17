package Main;

import Controller.Controller;
import View.MainContainer;

public class Main {

	public static void main(String[] args) {

		MainContainer mc = new MainContainer();
		Controller contr = new Controller(mc);

	}

}
