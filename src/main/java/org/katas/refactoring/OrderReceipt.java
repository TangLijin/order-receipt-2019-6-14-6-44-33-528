package org.katas.refactoring;

/**
 * OrderReceipt prints the details of order including customer name, address, description, quantity,
 * price and amount. It also calculates the sales tax @ 10% and prints as part
 * of order. It computes the total order amount (amount of individual lineItems +
 * total sales tax) and prints it.
 */
public class OrderReceipt {
    private Order order;

    public OrderReceipt(Order order) {
        this.order = order;
    }

    public String printReceipt() {
        StringBuilder output = new StringBuilder();

        // print headers
        output.append("======Printing Orders======\n");
        output.append(order.getCustomerName());
        output.append(order.getCustomerAddress());

        // prints lineItems
        output.append(getLineItems(order));

        return output.toString();
    }

    public StringBuilder getLineItems(Order order){
        StringBuilder result = new StringBuilder();
        double totalSalesTax = 0d;
        double totalAmount = 0d;
        for (LineItem lineItem : order.getLineItems()) {
            result.append(lineItem.getDescription()).append('\t').append(lineItem.getPrice()).append('\t');
            result.append(lineItem.getQuantity()).append('\t').append(lineItem.totalAmount()).append('\n');

            // calculate sales tax @ rate of 10%
            double salesTax = lineItem.totalAmount() * .10;
            totalSalesTax += salesTax;

            // calculate total amount of lineItem = price * quantity + 10 % sales tax
            totalAmount += lineItem.totalAmount() + salesTax;
        }

        // prints the state tax
        result.append("Sales Tax").append('\t').append(totalSalesTax);
        // print total amount
        result.append("Total Amount").append('\t').append(totalAmount);

        return result;
    }

}