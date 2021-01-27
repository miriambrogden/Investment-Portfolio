package assignment3;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class Portfolio implements Serializable {

    /*
    Function: checks to see if an investment exists in the specified list
    @param: symbol of object to see if exists
    @param: list to check
    @return: returns index if it exists in the list and -1 if not
     */
    public int isInList(String symbol, ArrayList<Investment> theList) {
        for (int i = 0; i < theList.size(); i++) {
            if (symbol.equals(theList.get(i).symbol)) {
                return i;
            }
        }
        return -1;
    }

    /*
    Function: updates the price of a specific investment in the list
    @param: list to check
    @param: symbol of investment to update
    @param: new price
    @param: index of investment item
    @return: string indicating success or fail
     */
    public String update(ArrayList<Investment> list, String symbol, double price, int x) {
        if (list.get(x).symbol.equals(symbol)) {
            list.get(x).price = price;
            return "The investment price was successfully updated\n";
        }
        return "There was an error updating the price of the investment\n";
    }

    /*
    Function: checks to see if an object exists in the specified list
    @param: object to see if exists
    @param: list to check
    @return: returns true if it exists in the list and false if not
     */
    public boolean inList(Investment object, ArrayList<Investment> wordList) {
        for (int i = 0; i < wordList.size(); i++) {
            if (object.equals(wordList.get(i))) {
                return true;
            }
        }
        return false;
    }

    /*
    Function: sell parts or all of an investment in the list
    @param: list to check
    @param: symbol of investment to sell
    @param: amount of investment to sell
    @param: price to sell investment at
    @return: string indicating amount of payment recieved
     */
    public String sell(ArrayList<Investment> list, String symbol, int quantity, double price) {
        int x = isInList(symbol, list);
        DecimalFormat df = new DecimalFormat("####0.00");
        if (x != -1) {
            if (quantity >= list.get(x).quantity) {
                double paymentRec1 = list.get(x).paymentRecieved();
                list.remove(list.get(x));
                return "All your stocks were sold. Payment Recieved: $" + df.format(paymentRec1) + "\n";
            } else {
                double paymentRec = 0;
                paymentRec = list.get(x).computePayment(quantity, price);
                double updatedQuantity = (double) list.get(x).quantity - quantity;
                double denominator = updatedQuantity / list.get(x).quantity;
                double updatedBookVal = 0;
                updatedBookVal = list.get(x).getBookVal(denominator);
                String stockSymbol = list.get(x).symbol;
                String stockName = list.get(x).name;
                if (updatedQuantity != 0) {
                    list.get(x).change(list, stockSymbol, stockName, (int) updatedQuantity, price, updatedBookVal);
                }
                list.remove(list.get(x));
                return "Payment Recieved: $" + df.format(paymentRec) + "\n";
            }
        } else {
            return "Sorry, that investment does not exist\n";
        }
    }

    /*
    Function: get the total gain of the entire list
    @param: list to get gain
    @return: string indicating amount of total gain
     */
    public String getTotalGain(ArrayList<Investment> list) {
        double totalGain = 0;
        if (list.size() != 0) {
            for (int i = 0; i < list.size(); i++) {
                double gain = list.get(i).computeGain();
                totalGain = totalGain + gain;
            }
            DecimalFormat df = new DecimalFormat("####0.00");
            return "$" + df.format(totalGain);
        } else {
            return "N/A";
        }
    }

    /*
    Function: get list of individual gain for each investment in the list
    @param: list to get individual gain from
    @return: string indicating each individual investments symbol, name, and gain
     */
    public String getIndividualGain(ArrayList<Investment> list) {
        String output = "";
        DecimalFormat df = new DecimalFormat("####0.00");
        if (list.size() != 0) {
            for (int i = 0; i < list.size(); i++) {
                double gain = list.get(i).computeGain();
                output = output + "Symbol: " + list.get(i).symbol + "   Name: " + list.get(i).name + "   Gain: $" + df.format(gain) + "\n";
            }
            return output;
        } else {
            return "There are no investments in the list\n";
        }
    }

    /*
    Function: buy a new investment or add to an existing investment
    @param: list to add to 
    @param: name of investment to buy
    @param: symbol of investment to buy
    @param: price of investment to buy
    @param: quantity of investment to buy
    @param: which drop down item was chosen
    @return: string indicating success or fail
     */
    public String buy(ArrayList<Investment> list, String name, String symbol, double price, int quantity, String type) {
        int x = isInList(symbol, list);
        if (x != -1) {
            int newQuantity = quantity;
            double newPrice = price;
            double bookVal = 0;
            int totalNewQuantity = newQuantity + list.get(x).quantity;
            list.get(x).changeMe(list, symbol, totalNewQuantity, newPrice, newQuantity);
            list.remove(list.get(x));
            return "The investment was updated\n";
        } else if (isInList(symbol, list) == -1) {
            if (type.equals("Stock")) {
                list.add(new Stock(symbol, name, quantity, price, 0, quantity));
            } else if (type.equals("MF")) {
                list.add(new MutualFund(symbol, name, quantity, price, 0, quantity));
            }
            return "The investment was added to the portfolio\n";
        } else {
            return "Sorry, there was an error adding the stock\n";
        }
    }

    /*
    Function: display all investments in a list
    @param: list to display 
    @return: string displaying all investments in the list
     */
    public String listInvestments(ArrayList<Investment> list) {
        String output;
        if (list.isEmpty()) {
            output = "No investments to list";
        } else {
            output = "Investments: \n";
            for (int i = 0; i < list.size(); i++) {
                output += list.get(i).toString() + "\n";
            }
        }
        return output;
    }

    /*
    Function: updates the hashmap based on current values in the investment list
    @param: investment list
    @return: updated hashmap
     */
    public HashMap<String, ArrayList<Integer>> updateHash(ArrayList<Investment> list) {
        HashMap<String, ArrayList<Integer>> investments = new HashMap<>();
        String delimiters = "[ ]{1,}";
        int flag = 0;
        for (int i = 0; i < list.size(); i++) {
            String theName = list.get(i).name;
            String[] names = theName.split(delimiters);
            for (String name : names) {
                Set<HashMap.Entry<String, ArrayList<Integer>>> entries = investments.entrySet();
                for (HashMap.Entry<String, ArrayList<Integer>> entry : entries) {
                    if (entry.getKey().equalsIgnoreCase(name)) {
                        ArrayList<Integer> hi = new ArrayList<>();
                        hi = entry.getValue();
                        hi.add(i);
                        flag++;
                    }
                }
                if (flag <= 0) {
                    ArrayList<Integer> hello = new ArrayList<>();
                    hello.add(i);
                    name = name.toLowerCase();
                    investments.put(name, hello);
                }
                flag = 0;
            }
        }
        return investments;
    }

    /*
    Function: check a string to see if it is empty or not
    @param: string to check
    @return: throws an exception if the string is empty, else returns the non empty string
     */
    public String checkString(String message) throws PortfolioExceptions {
        if (message.equals("")) {
            throw new PortfolioExceptions("The field must not be empty");
        } else {
            return message;
        }
    }

    /*
    Function: check an integer to see if it is non empty and greater than 0
    @param: string containing integer to check
    @return: throws an exception if the string is empty, else returns the integer
     */
    public int checkInt(String message) throws PortfolioExceptions {
        int aquant = -10;
        if (message.equals("")) {
            throw new PortfolioExceptions("The field must not be empty");
        } else {
            if (message.matches("[0-9]+")) {
                aquant = Integer.parseInt(message);
            }
        }
        if (aquant < 1) {
            throw new PortfolioExceptions("The field is in the wrong format");
        } else {
            return aquant;
        }
    }

    /*
    Function: check a double to see if it is non empty and greater than 0
    @param: string containing double to check
    @return: throws an exception if the string is empty, else returns the double
     */
    public Double checkDouble(String message) throws PortfolioExceptions {
        double aprice = -10;
        if (message.equals("")) {
            throw new PortfolioExceptions("The field must not be empty");
        } else {
            if (message.matches("(^[0-9]*[1-9]+[0-9]*\\.[0-9]*$)|(^[0-9]*\\.[0-9]*[1-9]+[0-9]*$)|(^[0-9]*[1-9]+[0-9]*$)")) {
                aprice = Double.parseDouble(message);
            }
        }
        if (aprice < 0) {
            throw new PortfolioExceptions("The field is in the wrong format");
        } else {
            return aprice;
        }
    }

    /*
    Function: write a list to a file
    @param: list to write to file
    @param: name of file to write to 
    @return: string indicating success or fail
     */
    public String writeFile(ArrayList<Investment> list, String fileName) {
        try {
            FileOutputStream fileOutput = new FileOutputStream(new File(fileName));
            ObjectOutputStream objOutput = new ObjectOutputStream(fileOutput);
            objOutput.writeObject(list);
            objOutput.flush();
            objOutput.close();
            return "Successfully wrote to file";
        } catch (IOException e) {
            e.printStackTrace();
            return "Failed to write.";
        }
    }

    /*
    Function: read a list from a file
    @param: list - information from the file is added to this list
    @param: name of file to read from 
    @return: list containing information from the file
     */
    public ArrayList<Investment> readMe(ArrayList<Investment> thelist, String fileName) {
        File f = new File(fileName);
        if (f.exists() == true) {
            try {
                FileInputStream fileIn = new FileInputStream(fileName);
                ObjectInputStream objectIn = new ObjectInputStream(fileIn);
                Object obj = objectIn.readObject();
                objectIn.close();
                ArrayList<Investment> newStore = (ArrayList<Investment>) obj;
                thelist = newStore;
                return thelist;

            } catch (Exception ex) {
                ex.printStackTrace();
                return null;
            }
        }
        return null;

    }

    /*
    Function: searches through a list based on passed in values
    @param: investment list
    @param: hashmap
    @param: symbol to search for
    @param: keyword(s) to search for
    @param: lower price range
    @param: higher price range
    @return: String containing search results
     */
    public String search(ArrayList<Investment> list, HashMap<String, ArrayList<Integer>> investments, String symbolInput, String keywordInput, double priceLow, double priceHigh) {
        String output = "";
        String priceInput = "";
        String low = "";
        String high = "";
        low = Double.toString(priceLow);
        high = Double.toString(priceHigh);
        if (priceLow != -10 && priceHigh != -10) {
            priceInput = low + "-" + high;
        } else if (priceLow != -10 && priceHigh == -10) {
            priceInput = priceLow + "-";
        } else if (priceLow == -10 && priceHigh != -10) {
            priceInput = "-" + priceHigh;
        }

        //User only enters a symbol
        if (!symbolInput.equals("") && keywordInput.equals("") && priceInput.equals("")) {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).symbol.equalsIgnoreCase(symbolInput)) {
                    output = output + list.get(i).toString() + "\n";
                }
            }
            //User only enters a price range
        } else if (symbolInput.equals("") && keywordInput.equals("") && !priceInput.equals("")) {
            String delimiters2 = "[-]{1,}";
            String[] thePrices = priceInput.split(delimiters2);
            if (thePrices.length == 2) {
                char firstChar = priceInput.charAt(0);
                if (firstChar == '-') { //upper
                    double upperPrice2 = Double.parseDouble(thePrices[1]);

                    for (int i = 0; i < list.size(); i++) {
                        if (list.get(i).price <= upperPrice2) {
                            output = output + list.get(i).toString() + "\n";
                        }
                    }
                } else { // upper and lower
                    double upperPrice = Double.parseDouble(thePrices[1]);
                    double lowerPrice = Double.parseDouble(thePrices[0]);
                    for (int i = 0; i < list.size(); i++) {
                        if (list.get(i).price <= upperPrice && list.get(i).price >= lowerPrice) {
                            output = output + list.get(i).toString() + "\n";
                        }
                    }
                }
            }
            if (thePrices.length == 1) {
                char lastChar = priceInput.charAt(priceInput.length() - 1);
                if (lastChar == '-') { // lower range
                    double lowerPrice2 = Double.parseDouble(thePrices[0]);
                    for (int i = 0; i < list.size(); i++) {
                        if (list.get(i).price >= lowerPrice2) {
                            output = output + list.get(i).toString() + "\n";
                        }
                    }
                } else { //equal
                    double absolutePrice = Double.parseDouble(thePrices[0]);
                    for (int i = 0; i < list.size(); i++) {
                        if (list.get(i).price == absolutePrice) {
                            output = output + list.get(i).toString() + "\n";
                        }
                    }
                }
            }
            //User doesn't enter any search options
        } else if (symbolInput.equals("") && keywordInput.equals("") && priceInput.equals("")) {
            for (int i = 0; i < list.size(); i++) {
                output = output + list.get(i).toString() + "\n";
            }
            //user enters only keyword
        } else if (symbolInput.equals("") && !keywordInput.equals("") && priceInput.equals("")) {
            String delimiters = "[ ]{1,}";
            ArrayList<Investment> wordList = new ArrayList<>();
            String[] names = keywordInput.split(delimiters);
            for (int i = 0; i < names.length; i++) {
                names[i] = names[i].toLowerCase();
                if (investments.containsKey(names[i])) {
                    for (int hi : investments.get(names[i])) {
                        if (inList(list.get(hi), wordList) == false) {
                            wordList.add(list.get(hi));
                        }
                    }
                }
            }
            int check = 0;
            for (int i = 0; i < wordList.size(); i++) {
                String theName = wordList.get(i).name;
                String[] names2 = theName.split(delimiters);
                String[] words = null;
                for (int j = 0; j < names2.length; j++) {
                    words = keywordInput.split(delimiters);
                    for (int k = 0; k < words.length; k++) {
                        if (names2[j].equalsIgnoreCase(words[k])) {
                            check++;
                        }
                    }
                }
                if (check == words.length) {
                    output = output + wordList.get(i).toString() + "\n";
                }
                check = 0;
            }
            //user enters keyword and symbol
        } else if (!symbolInput.equals("") && !keywordInput.equals("") && priceInput.equals("")) {
            String delimiters = "[ ]{1,}";
            ArrayList<Investment> wordList = new ArrayList<>();
            String[] names = keywordInput.split(delimiters);
            for (int i = 0; i < names.length; i++) {
                names[i] = names[i].toLowerCase();
                if (investments.containsKey(names[i])) {
                    for (int hi : investments.get(names[i])) {
                        if (inList(list.get(hi), wordList) == false) {
                            wordList.add(list.get(hi));
                        }
                    }
                }
            }
            int check = 0;
            for (int i = 0; i < wordList.size(); i++) {
                String theName = wordList.get(i).name;
                String[] names2 = theName.split(delimiters);
                String[] words = null;
                for (int j = 0; j < names2.length; j++) {
                    words = keywordInput.split(delimiters);
                    for (int k = 0; k < words.length; k++) {
                        if (names2[j].equalsIgnoreCase(words[k])) {
                            check++;
                        }
                    }
                }
                if (check == words.length) {
                    if (wordList.get(i).symbol.equalsIgnoreCase(symbolInput)) {
                        output = output + wordList.get(i).toString() + "\n";
                    }
                }
                check = 0;
            }
            //user enters symbol and price
        } else if (!symbolInput.equals("") && keywordInput.equals("") && !priceInput.equals("")) {
            String delimiters2 = "[-]{1,}";
            String[] thePrices = priceInput.split(delimiters2);
            if (thePrices.length == 2) {
                char firstChar = priceInput.charAt(0);
                if (firstChar == '-') { //upper
                    double upperPrice2 = Double.parseDouble(thePrices[1]);

                    for (int i = 0; i < list.size(); i++) {
                        if (list.get(i).price <= upperPrice2) {
                            if (list.get(i).symbol.equalsIgnoreCase(symbolInput)) {
                                output = output + list.get(i).toString() + "\n";
                            }
                        }
                    }
                } else { // upper and lower
                    double upperPrice = Double.parseDouble(thePrices[1]);
                    double lowerPrice = Double.parseDouble(thePrices[0]);
                    for (int i = 0; i < list.size(); i++) {
                        if (list.get(i).price <= upperPrice && list.get(i).price >= lowerPrice) {
                            if (list.get(i).symbol.equalsIgnoreCase(symbolInput)) {
                                output = output + list.get(i).toString() + "\n";
                            }
                        }
                    }
                }
            }
            if (thePrices.length == 1) {
                char lastChar = priceInput.charAt(priceInput.length() - 1);
                if (lastChar == '-') { // lower range
                    double lowerPrice2 = Double.parseDouble(thePrices[0]);
                    for (int i = 0; i < list.size(); i++) {
                        if (list.get(i).price >= lowerPrice2) {
                            if (list.get(i).symbol.equalsIgnoreCase(symbolInput)) {
                                output = output + list.get(i).toString() + "\n";
                            }
                        }
                    }
                } else { //equal
                    double absolutePrice = Double.parseDouble(thePrices[0]);
                    for (int i = 0; i < list.size(); i++) {
                        if (list.get(i).price == absolutePrice) {
                            if (list.get(i).symbol.equalsIgnoreCase(symbolInput)) {
                                output = output + list.get(i).toString() + "\n";
                            }
                        }
                    }
                }
            }
            //user enters keyword and price and symbol
        } else if (!symbolInput.equals("") && !keywordInput.equals("") && !priceInput.equals("")) {
            String delimiters = "[ ]{1,}";
            ArrayList<Investment> wordList = new ArrayList<>();
            String[] names = keywordInput.split(delimiters);
            for (int i = 0; i < names.length; i++) {
                names[i] = names[i].toLowerCase();
                if (investments.containsKey(names[i])) {
                    for (int hi : investments.get(names[i])) {
                        if (inList(list.get(hi), wordList) == false) {
                            wordList.add(list.get(hi));
                        }
                    }
                }
            }
            String delimiters2 = "[-]{1,}";
            String[] thePrices = priceInput.split(delimiters2);
            if (thePrices.length == 2) {
                char firstChar = priceInput.charAt(0);
                if (firstChar == '-') { //upper
                    double upperPrice2 = Double.parseDouble(thePrices[1]);
                    for (int i = 0; i < wordList.size(); i++) {
                        if (wordList.get(i).price <= upperPrice2) {
                            if (wordList.get(i).symbol.equalsIgnoreCase(symbolInput)) {
                                output = output + wordList.get(i).toString() + "\n";
                            }
                        }
                    }
                } else { // upper and lower
                    double upperPrice = Double.parseDouble(thePrices[1]);
                    double lowerPrice = Double.parseDouble(thePrices[0]);
                    for (int i = 0; i < wordList.size(); i++) {
                        if (wordList.get(i).price <= upperPrice && wordList.get(i).price >= lowerPrice) {
                            if (wordList.get(i).symbol.equalsIgnoreCase(symbolInput)) {
                                output = output + wordList.get(i).toString() + "\n";
                            }
                        }
                    }
                }
            }
            if (thePrices.length == 1) {
                char lastChar = priceInput.charAt(priceInput.length() - 1);
                if (lastChar == '-') { // lower range
                    double lowerPrice2 = Double.parseDouble(thePrices[0]);
                    for (int i = 0; i < wordList.size(); i++) {
                        if (wordList.get(i).price >= lowerPrice2) {
                            if (wordList.get(i).symbol.equalsIgnoreCase(symbolInput)) {
                                output = output + wordList.get(i).toString() + "\n";
                            }
                        }
                    }
                } else { //equal
                    double absolutePrice = Double.parseDouble(thePrices[0]);
                    for (int i = 0; i < wordList.size(); i++) {
                        if (wordList.get(i).price == absolutePrice) {
                            if (wordList.get(i).symbol.equalsIgnoreCase(symbolInput)) {
                                output = output + wordList.get(i).toString() + "\n";
                            }
                        }
                    }
                }
            }
            //user enters keyword and price
        } else if (symbolInput.equals("") && !keywordInput.equals("") && !priceInput.equals("")) {
            String delimiters = "[ ]{1,}";
            ArrayList<Investment> wordList = new ArrayList<>();
            String[] names = keywordInput.split(delimiters);
            for (int i = 0; i < names.length; i++) {
                names[i] = names[i].toLowerCase();
                if (investments.containsKey(names[i])) {
                    for (int hi : investments.get(names[i])) {
                        if (inList(list.get(hi), wordList) == false) {
                            wordList.add(list.get(hi));
                        }
                    }
                }
            }
            String delimiters2 = "[-]{1,}";
            String[] thePrices = priceInput.split(delimiters2);
            if (thePrices.length == 2) {
                char firstChar = priceInput.charAt(0);
                if (firstChar == '-') { //upper
                    double upperPrice2 = Double.parseDouble(thePrices[1]);
                    for (int i = 0; i < wordList.size(); i++) {
                        if (wordList.get(i).price <= upperPrice2) {
                            output = output + wordList.get(i).toString() + "\n";
                        }
                    }
                } else { // upper and lower
                    double upperPrice = Double.parseDouble(thePrices[1]);
                    double lowerPrice = Double.parseDouble(thePrices[0]);
                    for (int i = 0; i < wordList.size(); i++) {
                        if (wordList.get(i).price <= upperPrice && wordList.get(i).price >= lowerPrice) {
                            output = output + wordList.get(i).toString() + "\n";
                        }
                    }
                }
            }
            if (thePrices.length == 1) {
                char lastChar = priceInput.charAt(priceInput.length() - 1);
                if (lastChar == '-') { // lower range
                    double lowerPrice2 = Double.parseDouble(thePrices[0]);
                    for (int i = 0; i < wordList.size(); i++) {
                        if (wordList.get(i).price >= lowerPrice2) {
                            output = output + wordList.get(i).toString() + "\n";
                        }
                    }
                } else { //equal
                    double absolutePrice = Double.parseDouble(thePrices[0]);
                    for (int i = 0; i < wordList.size(); i++) {
                        if (wordList.get(i).price == absolutePrice) {
                            output = output + wordList.get(i).toString() + "\n";
                        }
                    }
                }
            }
        }
        return output;
    }
}
