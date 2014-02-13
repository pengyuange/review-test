package com.flextrade.review.to;

public class Order { // A comment/author should be added for javadoc to pick it up

    private double dPrice; // the type of this field should not be prefixed in it's name (correct naming would be: 'price')
    private int iSize; // same as above (correct naming would be: 'size')
    private String sSymbol; // same as above (correct naming would be: 'symbol')

    public double getPrice() {
        return dPrice;
    }

    public void setPrice(double price) {
        dPrice = price; // if the correct name is used, this should become this.price = price
    }

    public int getSize() {
        return iSize;
    }

    public void setSize(int size) {
        iSize = size; // same as above
    }

    public String getSymbol() {
        return sSymbol;
    }

    public void setSymbol(String symbol) {
        sSymbol = symbol; // same as above
    }

    public int toNumber(String Input) { // variable name should not start with an upper-case letter
        boolean canBeConverted = false; // this is a redundant flag, if we can parse the given String to an Integer, we just return it, else an exception is thrown and we return 0
        int n = 0; // no need to create a variable for this
        try {
            n = Integer.parseInt(Input); // you can simply return Integer.parseInt(input) here (then the 'n' variable won't be included in the GC)
            canBeConverted = true;
        } catch (Exception ex) { // overly broad exception, you should use NumberFormatException (this also applies for null/empty Strings)
            // empty exception block, a suggestion would be to return 0 here.
        }

        if (canBeConverted == true) { // this part of the code can be removed if the suggested changes are made
            return n;
        } else {
            return 0; // the name of this method does not suggest that 0 is an invalid value, it suggest a simple String to int conversion, are we sure we want to return 0 if the conversion is not successfull?
        }
    }
}
