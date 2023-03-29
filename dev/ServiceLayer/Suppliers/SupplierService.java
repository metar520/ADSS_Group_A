package ServiceLayer.Suppliers;

import java.util.List;
import java.util.Map;

import BusinessLayer.Suppliers.ProductAgreementController;
import BusinessLayer.Suppliers.SupplierController;

public class SupplierService {
    private SupplierController supplierController;
    private ProductAgreementController productAgreementController;

    public SupplierService() {
        supplierController = new SupplierController();
        productAgreementController = ProductAgreementController.getInstance();
    }

    // Add 'Fixed days' supplier
    public String addFixedDaysSupplierBaseAgreement(String supplierName, String supplierPhone,
            String supplierBankAccount,
            List<String> supplierFields, String paymentCondition, Map<Integer, Double> amountToDiscount,
            List<String> contactNames, List<String> contactPhones, List<Integer> days) {
        try {
            supplierController.addFixedDaysSupplierBaseAgreement(supplierName, supplierPhone, supplierBankAccount,
                    supplierFields,
                    paymentCondition, amountToDiscount, contactNames, contactPhones, days);
            return "Supplier of type: 'Fixed days' added successfully";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    // Add 'On Order' supplier
    public String addOnOrderSupplierBaseAgreement(String supplierName, String supplierPhone, String supplierBankAccount,
            List<String> supplierFields, String paymentCondition, Map<Integer, Double> amountToDiscount,
            List<String> contactNames, List<String> contactPhones, int maxSupplyDays) {
        try {
            supplierController.addOnOrderSupplierBaseAgreement(supplierName, supplierPhone, supplierBankAccount,
                    supplierFields,
                    paymentCondition, amountToDiscount, contactNames, contactPhones, maxSupplyDays);
            return "Supplier of type: 'On Order' added successfully";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    // Add 'Self Pickup' supplier
    public String addSelfPickupSupplierBaseAgreement(String supplierName, String supplierPhone,
            String supplierBankAccount,
            List<String> supplierFields, String paymentCondition, Map<Integer, Double> amountToDiscount,
            List<String> contactNames, List<String> contactPhones, String address) {
        try {
            supplierController.addSelfPickupSupplierBaseAgreement(supplierName, supplierPhone, supplierBankAccount,
                    supplierFields,
                    paymentCondition, amountToDiscount, contactNames, contactPhones, address);
            return "Supplier of type: 'Self Pickup' added successfully";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    // Update supplier name
    public String setSupplierName(int supplierId, String supplierName) {
        try {
            supplierController.setSupplierName(supplierId, supplierName);
            return "Supplier name updated successfully";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    // Update supplier phone
    public String setSupplierPhone(int supplierId, String supplierPhone) {
        try {
            supplierController.setSupplierName(supplierId, supplierPhone);
            return "Supplier phone updated successfully";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    // Update supplier bank account
    public String setSupplierBankAccount(int supplierId, String supplierBankAccount) {
        try {
            supplierController.setSupplierBankAccount(supplierId, supplierBankAccount);
            return "Supplier back account updated successfully";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    // Update supplier fields
    public String setSupplierFields(int supplierId, List<String> supplierFields) {
        try {
            supplierController.setSupplierFields(supplierId, supplierFields);
            return "Supplier fields updated successfully";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    // Add supplier field
    public String addSupplierField(int supplierId, String field) {
        try {
            supplierController.addSupplierField(supplierId, field);
            return "Supplier field named: " + field + " added successfully";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    // Update supplier payment condition
    public String setSupplierPaymentCondition(int supplierId, String paymentCondition) {
        try {
            supplierController.setSupplierPaymentCondition(supplierId, paymentCondition);
            return "Supplier payment condition updated successfully";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    // Update supplier discount
    public String setSupplierAmountToDiscount(int supplierId, Map<Integer, Double> amountToDiscount) {
        try {
            supplierController.setSupplierAmountToDiscount(supplierId, amountToDiscount);
            return "Supplier field  updated successfully";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    // Update supplier contacts names and phones
    public String setSupplierContactNamesAndPhones(int supplierId, List<String> contactPhones,
            List<String> contactNames) {
        try {
            supplierController.setSupplierContactNamesAndPhones(supplierId, contactPhones, contactNames);
            return "Supplier contacts updated successfully";
        } catch (Exception e) {
            return e.getMessage();
        }

    }

    // Add supplier contact
    public String addSupplierContact(int supplierId, String contactPhone, String contactName) {
        try {
            supplierController.addSupplierContact(supplierId, contactPhone, contactName);
            return "Supplier contact added successfully";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    // Delete supplier contact
    public String deleteSupplierContact(int supplierId, String contactPhone, String contactName) {
        try {
            supplierController.deleteSupplierContact(supplierId, contactPhone, contactName);
            return "Supplier contact deleted successfully";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    // Delete all supplier contacts
    public String deleteAllSupplierContacts(int supplierId, String contactPhone, String contactName) {
        try {
            supplierController.deleteAllSupplierContact(supplierId);
            return "All supplier contacts deleted successfully";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    // TODO: PRODUCT AGREEMENT LOGIC

    public String addSupplierProductAgreement(int supplierId, int productShopId, int productSupplierId, int stockAmount,
            double basePrice, Map<Integer, Double> amountToDiscount) {
        return null;
    }

    public String setProductAgreementStockAmount(int supplierId, int productShopId, int stockAmount) {
        return null;
    }

    public String setProductAgreementBasePrice(int supplierId, int productShopId, double basePrice) {
        return null;
    }

    public String setProductAgreementAmountToDiscount(int supplierId, int productShopId,
            Map<Integer, Double> amountToDiscount) {
        return null;
    }

    public String getSupplierCard(int supplierId) {
        return null;
    }

    public String getProductAgreement(int supplierId, int productId) {
        return null;
    }
}