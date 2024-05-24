//package ca.ubc.cs304.ui;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//
////import ca.ubc.cs304.delegates.TerminalTransactionsDelegate;
//import ca.ubc.cs304.model.FarmAndSupervisorModel;
////import ca.ubc.cs304.model.OldModel.FarmingEquipmentsModel;
//
///**
// * The class is only responsible for handling terminal text inputs.
// */
//public class TerminalTransactions {
//	private static final String EXCEPTION_TAG = "[EXCEPTION]";
//	private static final String WARNING_TAG = "[WARNING]";
//	private static final int INVALID_INPUT = Integer.MIN_VALUE;
//	private static final int EMPTY_INPUT = 0;
//
//	private BufferedReader bufferedReader = null;
////	private TerminalTransactionsDelegate delegate = null;
//
//	public TerminalTransactions() {
//	}
//
//	/**
//	 * Sets up the database to have a branch table with two tuples so we can insert/update/delete from it.
//	 * Refer to the databaseSetup.sql file to determine what tuples are going to be in the table.
//	 */
//	public void setupDatabase(TerminalTransactionsDelegate delegate) {
//		this.delegate = delegate;
//
//		bufferedReader = new BufferedReader(new InputStreamReader(System.in));
//		int choice = INVALID_INPUT;
//
//		while(choice != 1 && choice != 2) {
//			System.out.println("If you have a table called Branch in your database (capitialization of the name does not matter), it will be dropped and a new Branch table will be created.\nIf you want to proceed, enter 1; if you want to quit, enter 2.");
//
//			choice = readInteger(false);
//
//			if (choice != INVALID_INPUT) {
//				switch (choice) {
//				case 1:
//					delegate.databaseSetup();
//					break;
//				case 2:
//					handleQuitOption();
//					break;
//				default:
//					System.out.println(WARNING_TAG + " The number that you entered was not a valid option.\n");
//					break;
//				}
//			}
//		}
//	}
//
//	/**
//	 * Displays simple text interface
//	 */
//	public void showMainMenu(TerminalTransactionsDelegate delegate) {
//		this.delegate = delegate;
//
//	    bufferedReader = new BufferedReader(new InputStreamReader(System.in));
//		int choice = INVALID_INPUT;
//
//		while (choice != 9) {
//			System.out.println();
//			System.out.println("1. Insert Farm and Supervisor");
//			System.out.println("2. Insert Farming Equipments");
//			System.out.println("3. Delete Farming Equipments");
//			System.out.println("4. Delete Farm");
//			System.out.println("5. Update Equipments ");
//			System.out.println("6. Update Supervisor ");
//			System.out.println("7. Show Farming Equipments");
//			System.out.println("8. Show Farm and Supervisor");
//			System.out.println("9. Quit");
//			System.out.print("Please choose one of the above 9 options: ");
//
//			choice = readInteger(false);
//
//			System.out.println(" ");
//
//			if (choice != INVALID_INPUT) {
//				switch (choice) {
//				case 2:
//					handleEquipmentsInsertOption();
//					break;
//				case 3:
//					handleToolDeleteOption();
//					break;
//				case 5:
//					handleTool_idUpdateOption();
//					break;
//				case 4:
//					handleFarmDeleteOption();
//					break;
//				case 7:
//					delegate.showFarmingEquipments();
//					break;
//				case 9:
//					handleQuitOption();
//					break;
//				case 1:
//					handleFarmInsertOption();
//					break;
//				case 8:
//					delegate.showFarmAndSupervisor();
//					break;
//				case 6:
//					handleSupervisorUpdateOption();
//					break;
//				default:
//					System.out.println(WARNING_TAG + " The number that you entered was not a valid option.");
//					break;
//				}
//			}
//		}
//	}
//
//
//	private void handleToolDeleteOption() {
//		int Tool_id = INVALID_INPUT;
//		while (Tool_id == INVALID_INPUT) {
//			System.out.print("Please enter the Tool ID you wish to delete: ");
//			Tool_id = readInteger(false);
//			if (Tool_id != INVALID_INPUT) {
//				delegate.deleteEquipment(Tool_id);
//			}
//		}
//	}
//	private void handleFarmDeleteOption() {
//		int Farm_id = INVALID_INPUT;
//		while (Farm_id == INVALID_INPUT) {
//			System.out.print("Please enter the Farm ID you wish to delete: ");
//			Farm_id = readInteger(false);
//			if (Farm_id != INVALID_INPUT) {
//				delegate.deleteFarm(Farm_id);
//			}
//		}
//	}
//
//	private void handleEquipmentsInsertOption() {
//		String Tool_name = null;
//		while (Tool_name == null) {
//			System.out.print("Please enter the Tool name you wish to insert: ");
//			Tool_name = readLine().trim();
//		}
//		String Tool_usage = null;
//		while (Tool_usage == null) {
//			System.out.print("Please enter the Tool usage you wish to insert: ");
//			Tool_usage = readLine().trim();
//		}
//
//		String Manufacturer = null;
//		while (Manufacturer == null || Manufacturer.length() <= 0) {
//			System.out.print("Please enter the Manufacturer you wish to insert: ");
//			Manufacturer = readLine().trim();
//		}
//		/**
//		// branch address is allowed to be null so we don't need to repeatedly ask for the address
//		System.out.print("Please enter the branch address you wish to insert: ");
//		String address = readLine().trim();
//		if (address.length() == 0) {
//			address = null;
//		}*/
//
//		int Tool_id = INVALID_INPUT;
//		while (Tool_id == INVALID_INPUT ) {
//			System.out.print("Please enter the Tool_id you wish to insert: ");
//			Tool_id = readInteger(true);
//		}
//
//
//		int Price = INVALID_INPUT;
//		while (Price == INVALID_INPUT) {
//			System.out.print("Please enter the Price you wish to insert: ");
//			Price = readInteger(true);
//		}
//
//		int Farm_id = INVALID_INPUT;
//		while (Farm_id == INVALID_INPUT) {
//			System.out.print("Please enter the Farm_id you wish to insert: ");
//			Farm_id = readInteger(true);
//		}
//
//		FarmingEquipmentsModel model = new FarmingEquipmentsModel(Tool_name,
//				Tool_usage,
//				Manufacturer,
//				Tool_id,
//				Price,
//				Farm_id);
//		delegate.insertEquipments(model);
//	}
//	private void handleFarmInsertOption() {
//		String area = null;
//		while (area == null) {
//			System.out.print("Please enter the Farm area you wish to insert: ");
//			area = readLine().trim();
//		}
//		String location = null;
//		while (location == null) {
//			System.out.print("Please enter the Farm location you wish to insert: ");
//			location = readLine().trim();
//		}
//
//		String supervisor_name = null;
//		while (supervisor_name == null) {
//			System.out.print("Please enter the Supervisor name you wish to insert: ");
//			supervisor_name = readLine().trim();
//		}
//
//		String phone_number = null;
//		while (phone_number == null) {
//			System.out.print("Please enter the Supervisor phone_number you wish to insert: ");
//			phone_number = readLine().trim();
//		}
//
//
//		int farm_id = INVALID_INPUT;
//		while (farm_id == INVALID_INPUT) {
//			System.out.print("Please enter the farm_id you wish to insert: ");
//			farm_id = readInteger(false);
//		}
//
//
//		int supervisor_id = INVALID_INPUT;
//		while (supervisor_id == INVALID_INPUT) {
//			System.out.print("Please enter the supervisor_id you wish to insert: ");
//			supervisor_id = readInteger(false);
//		}
//
//		int salary = INVALID_INPUT;
//		while (salary == INVALID_INPUT) {
//			System.out.print("Please enter the salary you wish to insert: ");
//			salary = readInteger(true);
//		}
//		int bonus = INVALID_INPUT;
//		while (bonus == INVALID_INPUT) {
//			System.out.print("Please enter the bonus you wish to insert: ");
//			bonus = readInteger(true);
//		}
//
//
//
//		FarmAndSupervisorModel model = new FarmAndSupervisorModel(area,
//				location,
//				supervisor_name,
//				phone_number,
//				farm_id,
//				supervisor_id,
//				salary,
//				bonus);
//		delegate.insertFarmAndSupervisor(model);
//	}
//	private void handleQuitOption() {
//		System.out.println("Good Bye!");
//
//		if (bufferedReader != null) {
//			try {
//				bufferedReader.close();
//			} catch (IOException e) {
//				System.out.println("IOException!");
//			}
//		}
//
//		delegate.terminalTransactionsFinished();
//	}
//
//	private void handleTool_idUpdateOption() {
//		int tool_id = INVALID_INPUT;
//		while (tool_id == INVALID_INPUT) {
//			System.out.print("Please enter the tool ID you wish to update: ");
//			tool_id = readInteger(false);
//		}
//		int farm_id = INVALID_INPUT;
//		while (farm_id == INVALID_INPUT ) {
//			System.out.print("Please enter the farm ID you wish to update: ");
//			farm_id = readInteger(false);
//		}
//
//		delegate.updateEquipments(tool_id, farm_id);
//	}
//	private void handleSupervisorUpdateOption() {
//		int farm_id = INVALID_INPUT;
//		while (farm_id == INVALID_INPUT ) {
//			System.out.print("Please enter the farm ID you wish to update: ");
//			farm_id = readInteger(false);
//		}
//		int supervisor_id = INVALID_INPUT;
//		while (supervisor_id == INVALID_INPUT) {
//			System.out.print("Please enter the supervisor ID you wish to update: ");
//			supervisor_id = readInteger(false);
//		}
//		String supervisor_name = null;
//		while (supervisor_name == null) {
//			System.out.print("Please enter the supervisor_name area you wish to update: ");
//			supervisor_name = readLine().trim();
//		}
//		int salary = INVALID_INPUT;
//		while (salary == INVALID_INPUT) {
//			System.out.print("Please enter the salary you wish to update: ");
//			salary = readInteger(false);
//		}
//		int bonus = INVALID_INPUT;
//		while (bonus == INVALID_INPUT) {
//			System.out.print("Please enter the bonus you wish to update: ");
//			bonus = readInteger(false);
//		}
//
//
//		delegate.updateSupervisor(farm_id, supervisor_id, supervisor_name, salary, bonus);
//	}
//
//	private int readInteger(boolean allowEmpty) {
//		String line = null;
//		int input = INVALID_INPUT;
//		try {
//			line = bufferedReader.readLine();
//			input = Integer.parseInt(line);
//		} catch (IOException e) {
//			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
//		} catch (NumberFormatException e) {
//			if (allowEmpty && line.length() == 0) {
//				input = EMPTY_INPUT;
//			} else {
//				System.out.println(WARNING_TAG + " Your input was not an integer");
//			}
//		}
//		return input;
//	}
//
//	private String readLine() {
//		String result = null;
//		try {
//			result = bufferedReader.readLine();
//		} catch (IOException e) {
//			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
//		}
//		return result;
//	}
//}
