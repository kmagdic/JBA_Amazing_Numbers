package numbers;

import java.util.ArrayList;
import java.util.List;

public class NaturalNumber {
    private String numberString;
    private long num;

    NaturalNumber(long num) throws NumberFormatException {
        numberString = String.valueOf(num);
    }

    boolean isEven() {
        return num % 2 == 0;
    }

    boolean isBuzz() {
        return num % 7 == 0 || num % 10 == 7;
    }

    boolean isDuck() {
        for (int i = 0; i < numberString.length(); i++) {
            if (numberString.charAt(i) == '0')
                return true;
        }
        return false;
    }

    boolean isPalindromic() {

        String s = String.valueOf(num);

        String sPart1 = s.substring(0, s.length()/2);
        String sPart2 = s.substring(s.length()/2);

        return num <= 9 || sPart1.equals(sPart2);
    }

    boolean isGapful() {
        int divider = Integer.parseInt(String.valueOf(numberString.charAt(0)) +
                numberString.charAt(numberString.length() - 1), 10);

        return num % divider == 0;
    }

    String getFormattedValue() {
        return String.format("%,d", num);
    }


    public long getValue() {
        return num;
    }

    String getPropertiesAsStr() {

        String result = isEven() ? "even" : "odd";
        if(isBuzz()) result += ", buzz";
        if(isDuck()) result += ", duck";
        if(isPalindromic()) result += ", palindromic";
        if(isGapful()) result += ", gapful";

        return result;
    }

    String getPropertiesAsMultilineStr() {
        String result = "buzz: " + (isBuzz()) + "\n" +
                "duck: " + isDuck() + "\n" +
                "palindromic: " + isPalindromic() + "\n" +
                "gapful: " + isGapful() + "\n" +
                "even: " + isEven() + "\n" +
                "odd: " + !isEven() + "\n";

        return result;
    }

}