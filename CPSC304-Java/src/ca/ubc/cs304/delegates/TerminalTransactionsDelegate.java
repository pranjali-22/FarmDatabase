//package ca.ubc.cs304.delegates;
//
//import ca.ubc.cs304.model.FarmAndSupervisorModel;
//import ca.ubc.cs304.model.OldModel.FarmingEquipmentsModel;
//
///**
// * This interface uses the delegation design pattern where instead of having
// * the TerminalTransactions class try to do everything, it will only
// * focus on handling the UI. The actual logic/operation will be delegated to the
// * controller class (in this case Bank).
// *
// * TerminalTransactions calls the methods that we have listed below but
// * Bank is the actual class that will implement the methods.
// */
//public interface TerminalTransactionsDelegate {
//	public void databaseSetup();
//
//	public void deleteEquipment(int Tool_id);
//	public void deleteFarm(int Farm_id) ;
//	public void insertEquipments(FarmingEquipmentsModel model);
//	public void insertFarmAndSupervisor(FarmAndSupervisorModel model);
//	public void showFarmingEquipments();
//	public void showFarmAndSupervisor();
//	public void updateEquipments(int tool_id, int farm_id);
//	public void updateSupervisor(int farm_id, int supervisor_id, String supervisor_name, int salary, int bonus);
//
//	public void terminalTransactionsFinished();
//}
