package assignment3;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;

public abstract class Investment implements Serializable {

    public String symbol;
    public String name;
    public int quantity;
    public double price;

    /*
    Function: constructor for new investment
    @param: symbol
    @param: name
    @param: quantity
    @param: price
    @return: investment is created
     */
    public Investment(String stockSymbol, String stockName, int stockQuantity, double stockPrice) {
        symbol = stockSymbol;
        name = stockName;
        quantity = stockQuantity;
        price = stockPrice;
    }

    public abstract double computeGain();

    public abstract double paymentRecieved();

    public abstract double getBookVal(double denominator);

    public abstract double computePayment(int theq, double thep);

    public abstract void change(ArrayList<Investment> theList, String theSymbol, String theName, int theUpdateQ, double thePrice, double theUpdateBV);

    public abstract void changeMe(ArrayList<Investment> theList, String theSymbol, int totalNewQuantity, double newPrice, int newQuantity);

    @Override
    public boolean equals(Object other) {
        Investment otherInv = (Investment) other;
        if (otherInv == null) {
            return false;
        } else if (getClass() != otherInv.getClass()) {
            return false;
        } else {
            return ((symbol.equalsIgnoreCase(otherInv.symbol))
                    && (name.equalsIgnoreCase(otherInv.name))
                    && (price == otherInv.price)
                    && (quantity == otherInv.quantity));
        }
    }
}
