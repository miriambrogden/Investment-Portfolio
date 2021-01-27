package assignment3;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class Stock extends Investment implements Serializable {

    double bookVal;

    public Stock() {
        super("", "", 0, 0.0);
    }

    /*
    Function: constructor for stock and computing book value
    @param: stock symbol
    @param: stock name
    @param: stock quantity
    @param: stock price
    @param: stock book value
    @param: price quantity
    @return: stock is created
     */
    public Stock(String stockSymbol, String stockName, int stockQuantity, double stockPrice, double stockBookVal, int priceQuantity) {
        super(stockSymbol, stockName, stockQuantity, stockPrice);
        bookVal = (priceQuantity * stockPrice) + 9.99 + stockBookVal;
    }

    /*
    Function: compute the gain of an individual stock
    @param: none;
    @return: double indicating value of Gain
     */
    @Override
    public double computeGain() {
        return ((quantity * price) - 9.99) - bookVal;
    }

    /*
    Function: compute the amount of payment recieved when selling a stock
    @param: none;
    @return: double indicating value of payment recieved
     */
    @Override
    public double paymentRecieved() {
        return (quantity * price) - 9.99;
    }

    /*
    Function: compute the book value of a stock
    @param: denominator of book value equation
    @return: double indicating book Value
     */
    @Override
    public double getBookVal(double denominator) {
        return (bookVal * denominator) - 9.99;
    }

    /*
    Function: compute the amount of payment recieved when selling a stock
    @param: given quantity
    @param: given price
    @return: double indicating value of payment recieved
     */
    @Override
    public double computePayment(int theq, double thep) {
        return (theq * thep) - 9.99;
    }

    /*
    Function: add a new stock to the list based on updated values
    @param: list to add to
    @param: symbol of investment to add
    @param: name of investment to add
    @param: updated quantity of investment to add
    @param: price of investment to add
    @param: updated book value of investment to add
    @return: none - stock is added to the list
     */
    @Override
    public void change(ArrayList<Investment> theList, String theSymbol, String theName, int theUpdateQ, double thePrice, double theUpdateBV) {
        theList.add(new Stock(theSymbol, theName, theUpdateQ, thePrice, theUpdateBV, 0));
    }

    /*
    Function: updated a stock in the list based on updated values
    @param: list to add to
    @param: symbol of investment to add
    @param: total new quantity of investment
    @param: new price of investment
    @param: new quantity of investment
    @return: none - investment is updated in the list
     */
    @Override
    public void changeMe(ArrayList<Investment> theList, String theSymbol, int totalNewQuantity, double newPrice, int newQuantity) {
        theList.add(new Stock(theSymbol, name, totalNewQuantity, newPrice, bookVal, newQuantity));
    }

    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("####0.00");
        return ("Symbol: " + symbol + "   Name: " + name + "   Quantity: " + quantity + "   Price: $" + df.format(price) + "   Book Value: $" + df.format(bookVal));
    }

    public boolean equals(Stock theStock) {
//      Stock theStock = (Stock)other;
        if (theStock == null) {
            return false;
        } else if (getClass() != theStock.getClass()) {
            return false;
        } else {
            return ((symbol.equalsIgnoreCase(theStock.symbol))
                    && (name.equalsIgnoreCase(theStock.name))
                    && (price == theStock.price)
                    && (quantity == theStock.quantity)
                    && (bookVal == theStock.bookVal));
        }
    }

}
