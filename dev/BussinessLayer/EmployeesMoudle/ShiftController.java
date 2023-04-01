package BussinessLayer.EmployeesMoudle;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.LinkedList;

import Misc.Role;
import Misc.ShiftTime;

public class ShiftController {
    private EmployeeController employeeController;
    private LinkedList<Shift> shifts;
    private static int shiftIdConuter = 0;

    // constructor
    public ShiftController(EmployeeController employeeController){
        this.employeeController = employeeController;
        shifts = new LinkedList<Shift>();
    }

    public void addShift(int superBranchNumer, LocalDate date, int startHour, int endHour, ShiftTime time){
        shifts.add(new Shift(shiftIdConuter, superBranchNumer, date, startHour, endHour, time));
        shiftIdConuter++;
    }
    
    // add cancelation to shift

    // add constaint to shift

    // aprove function for the HR manager to a final shift
    public void approveFinalShift(int managerID, HashMap<Employee, Role> hrAssigns){}
    // check: exist super branch for all users
    // check: no employee have a shift on the same day
    // check: all the role are existing in the employees that needed
    // check: no over employees then needed
    // needs to send a message if some role are missing people, and how mach -> function: missingStaffToRole

//-------------------------------------Getters And Setters--------------------------------------------------------

    // throw Error if there is not shift with this ID
    public Shift getShift(int id){
        for (Shift shift : shifts) {
            if(shift.getID() == id){return shift;}
        }
        throw new Error("No such a shift in this system by the id " + id);
    }
}