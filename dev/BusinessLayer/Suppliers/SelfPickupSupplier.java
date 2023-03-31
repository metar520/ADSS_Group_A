package BusinessLayer.Suppliers;

import java.util.List;
import java.util.Map;
import java.util.Queue;

class SelfPickupSupplier extends Supplier {

    private String address;

    // Copy constructor

    public SelfPickupSupplier(int id, String name, String phone, String bankAcc, List<String> fields,
            String paymentCondition,
            Map<Integer, Double> amountToDiscount, Queue<Integer> reservationHistory, List<Contact> contacts,
            String address) {
        super(id, name, phone, bankAcc, fields, paymentCondition, amountToDiscount, reservationHistory, contacts);
        this.address = address;
    }

    // Constructor without contacts, reservation history and fields
    public SelfPickupSupplier(int id, String name, String phone, String bankAcc, String paymentCondition,
            Map<Integer, Double> amountToDiscount, String address) {
        super(id, name, phone, bankAcc, paymentCondition, amountToDiscount);
        this.address = address;
    }

    // Constructor without reservation history
    public SelfPickupSupplier(int id, String name, String phone, String bankAcc, List<String> fields,
            String paymentCondition,
            Map<Integer, Double> amountToDiscount, List<Contact> contacts, String address) {
        super(id, name, phone, bankAcc, fields, paymentCondition, amountToDiscount, contacts);
        this.address = address;
    }

    // Constructor without reservation history and contacts
    public SelfPickupSupplier(int id, String name, String phone, String bankAcc, List<String> fields,
            String paymentCondition,
            Map<Integer, Double> amountToDiscount, String address) {
        super(id, name, phone, bankAcc, fields, paymentCondition, amountToDiscount);
        this.address = address;
    }

}
