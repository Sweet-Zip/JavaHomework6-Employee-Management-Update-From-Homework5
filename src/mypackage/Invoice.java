package mypackage;

public class Invoice implements Payable{
    private String partNumber;
    private String partDescription;
    private int quantity;
    private double pricePerItem;

    @Override
    public double getPaymentAmount() {
        return 0;
    }
}
