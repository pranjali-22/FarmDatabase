package ca.ubc.cs304.controller;

import ca.ubc.cs304.delegates.LoginWindowDelegate;
import ca.ubc.cs304.database.DatabaseConnectionHandler;
import ca.ubc.cs304.ui.Crop.CropWindow;
import ca.ubc.cs304.ui.FirstWindow;
import ca.ubc.cs304.ui.LoginWindow;
import ca.ubc.cs304.ui.Quantity.QuantityWindow;
import ca.ubc.cs304.ui.Status.StatusWindow;
import ca.ubc.cs304.ui.Supervisor.SupervisorLogin;
//import ca.ubc.cs304.ui.TerminalTransactions;

/**
 * This is the main controller class that will orchestrate everything.
 */
public class Farm implements LoginWindowDelegate {
	private DatabaseConnectionHandler dbHandler = null;
	private LoginWindow loginWindow = null;
	private FirstWindow fw = null;
	public Farm() {
		dbHandler = new DatabaseConnectionHandler();
	}
	private void start() {
		loginWindow = new LoginWindow();
		loginWindow.showFrame(this);
//		StatusWindow win= new StatusWindow(dbHandler,"dsds");
//		QuantityWindow win= new QuantityWindow(dbHandler,"dummy");
//		CropWindow win= new CropWindow(dbHandler,"sds");

	}
	public void login(String username, String password) {
		boolean didConnect = dbHandler.login(username, password);

		if (didConnect) {
			// Once connected, remove login window and start text transaction flow
			loginWindow.dispose();
			dbHandler.databaseSetup();
			fw = new FirstWindow(dbHandler);

		} else {
			loginWindow.handleLoginFailed();

			if (loginWindow.hasReachedMaxLoginAttempts()) {
				loginWindow.dispose();
				System.out.println("You have exceeded your number of allowed attempts");
				System.exit(-1);
			}
		}
	}
	public static void main(String args[]) {
		Farm bank = new Farm();
		bank.start();
	}
}
