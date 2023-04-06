package ServiceLayer.EmployeesMoudle;

import java.security.interfaces.RSAPrivateCrtKey;
import java.time.LocalDate;
import java.util.HashMap;

import BussinessLayer.EmployeesMoudle.BranchController;
import BussinessLayer.EmployeesMoudle.EmployeeController;
import BussinessLayer.EmployeesMoudle.ShiftController;
import Misc.*;

public class BranchService {
    private EmployeeController employeeController;
    private ShiftController shiftController;
    private BranchController branchController;

    public BranchService(EmployeeController employeeController, ShiftController shiftController, BranchController branchController){
        this.employeeController = employeeController;
        this.shiftController = shiftController;
        this.branchController = branchController;
    }

    public void addBranch(int managerId, String address){
        branchController.addBranch(managerId, address);
    }
    
    public void addNewEmployee(int managerId, String firstName, String lastName, int id, String password, int bankNum,
    int bankBranch, int bankAccount, int salary, int bonus, LocalDate startDate, License driverLicense, String role, int branch){
        branchController.addNewEmployee(managerId, firstName, lastName, id, password, bankNum,
        bankBranch, bankAccount, salary, bonus, startDate, driverLicense, role, branch);
    }

    public void addForeignEmployee(int managerId, int idEmployee, int branch){
        branchController.addForeignEmployee(managerId, idEmployee, branch);
    }

    public void addNotAllowEmployees(int managerId, int idEmployee, int branchId){
        branchController.addNotAllowEmployees(managerId, idEmployee, branchId);
    }

    public void deleteEmployee(int managerId, int id){
        branchController.deleteEmployee(managerId, id);
    }

    public void addShift(int managerId, int branch, LocalDate date, int startHour, int endHour, ShiftTime time){
        branchController.addShift(managerId, branch, date, startHour, endHour, time);
    }
    
    public void addConstraint(int branch, int idEmployee, int shift){
        branchController.addConstraint(branch, idEmployee, shift);
    }
    
    public void approveFinalShift(int managerID, int shiftID, int branchID, HashMap<Integer, String> hrAssigns){
        branchController.approveFinalShift(managerID, shiftID, branchID, hrAssigns);
    }
}