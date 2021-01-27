package assignment3;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class MutualFund extends Investment implements Serializable {

    double bookVal;

    public MutualFund() {
        super("", "", 0, 0.0);
    }

    /*
    Function: compute the gain of an individual mutual fund
    @param: none;
    @return: double indicating value of Gain
     */
    @Override
    public double computeGain() {
        return ((quantity * price) - 45) - bookVal;
    }

    /*
    Function: compute the amount of payment recieved when selling a mutual fund
    @param: none;
    @return: double indicating value of payment recieved
     */
    @Override
    public double paymentRecieved() {
        return (quantity * price) - 45;
    }

    /*
    Function: compute the amount of payment recieved when selling a mutual fund
    @param: given quantity
    @param: given price
    @return: double indicating value of payment recieved
     */
    @Override
    public double computePayment(int theq, double thep) {
        return (theq * thep) - 45;
    }

    /*
    Function: compute the book value of a mutual fund
    @param: denominator of book value equation
    @return: double indicating book Value
     */
    @Override
    public double getBookVal(double denominator) {
        return bookVal * denominator;
    }

    /*
    Function: add a new mutual fund to the list based on updated values
    @param: list to add to
    @param: symbol of investment to add
    @param: name of investment to add
    @param: updated quantity of investment to add
    @param: price of investment to add
    @param: updated book value of investment to add
    @return: none - mutual fund is added to the list
     */
    @Override
    public void change(ArrayList<Investment> theList, String theSymbol, String theName, int theUpdateQ, double thePrice, double theUpdateBV) {
        theList.add(new MutualFund(theSymbol, theName, theUpdateQ, thePrice, theUpdateBV, 0));
    }

    /*
    Function: updated a Mutual Fund in the list based on updated values
    @param: list to add to
    @param: symbol of investment to add
    @param: total new quantity of investment
    @param: new price of investment
    @param: new quantity of investment
    @return: none - investment is updated in the list
     */
    @Override
    public void changeMe(ArrayList<Investment> theList, String theSymbol, int totalNewQuantity, double newPrice, int newQuantity) {
        theList.add(new MutualFund(theSymbol, name, totalNewQuantity, newPrice, bookVal, newQuantity));
    }

    /*
    Function: constructor for mutual fund and computing book value
    @param: mutual fund symbol
    @param: mutual fund name
    @param: mutual fund quantity
    @param: mutual fund price
    @param: mutual fund book value
    @param: price quantity
    @return: mutual fund is created
     */
    public MutualFund(String mfSymbol, String mfName, int mfQuantity, double mfPrice, double mfBookVal, int priceQuantity) {
        super(mfSymbol, mfName, mfQuantity, mfPrice);
        bookVal = (priceQuantity * mfPrice) + mfBookVal;
    }

    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("####0.00");
        return ("Symbol: " + symbol + "   Name: " + name + "   Quantity: " + quantity + "   Price: $" + df.format(price) + "   Book Value: $" + df.format(bookVal));
    }

    public boolean equals(MutualFund theMF) {
        //MutualFund theMF = (MutualFund)other;
        if (theMF == null) {
            return false;
        } else if (getClass() != theMF.getClass()) {
            return false;
        } else {
            return ((symbol.equalsIgnoreCase(theMF.symbol))
                    && (name.equalsIgnoreCase(theMF.name))
                    && (price == theMF.price)
                    && (quantity == theMF.quantity)
                    && (bookVal == theMF.bookVal));
        }
    }

}
