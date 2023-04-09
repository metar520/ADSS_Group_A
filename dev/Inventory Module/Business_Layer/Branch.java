package Business_Layer;

public class Branch {

  private int branchId;
  private ProductController productController;
  private ReportController reportController;

  public Branch(int branchId) {
    this.branchId = branchId;
    this.productController = new ProductController();
    this.reportController = new ReportController(productController);
  }

  public int getId() {
    return this.branchId;
  }

  public ProductController getProductController() {
    return productController;
  }

  public ReportController getReportController() {
    return reportController;
  }
}