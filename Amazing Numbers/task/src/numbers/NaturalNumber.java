package numbers;



public class NaturalNumber {
    private final String numberString;
    private final long num;

    static String[] possibleProperties = {"BUZZ", "DUCK", "PALINDROMIC", "GAPFUL", "SPY", "EVEN", "ODD", "SQUARE", "SUNNY"};

    NaturalNumber(long num) throws NumberFormatException {
        this.num = num;
        numberString = String.valueOf(num);
    }

    public static boolean existsProperty(String propertyStr) {
        boolean propertyFound = false;
        for (String p : possibleProperties) {
            if (p.equals(propertyStr)) {
                propertyFound = true;
                break;
            }
        }

        return propertyFound;
    }
    public static String getPossiblePropertiesAsStr() {
        return String.join(", ", possibleProperties);
    }

    public static boolean arePropsMutuallyExclusive(String property1, String property2) {
        String[][] exclusivePairs = {{"ODD", "EVEN"},
                         {"SQUARE", "SUNNY"},
                         {"SPY", "DUCK"}};
        for(String[] a : exclusivePairs){
            if( (a[0].equals(property1) && a[1].equals(property2)) ||
                    (a[1].equals(property1) && a[0].equals(property2)) )
                return true;
        }

        return false;
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

        for (int i = 0; i < numberString.length() / 2; ++i) {
            if (numberString.charAt(i) != numberString.charAt(numberString.length() - 1 - i))
                return false;
        }
        return true;

    }

    boolean isGapful() {
        if(numberString.length() < 3)
            return false;

        String dividerStr = "" +
                numberString.charAt(0) +
                numberString.charAt(numberString.length() - 1);

        return num % Integer.parseInt(dividerStr) == 0;
    }

    boolean isSpy() {
        long sumOfAllDigits = 0;
        long productOfAllDigits = -1; // -1 means it hasn't been calculated yet
        for (int i = 0; i < numberString.length(); i++) {
            int currDigit = Integer.parseInt("" + numberString.charAt(i));
            sumOfAllDigits += currDigit;

            if(productOfAllDigits == -1)
                productOfAllDigits = currDigit;
            else
                productOfAllDigits = productOfAllDigits * currDigit;
        }

        return sumOfAllDigits == productOfAllDigits;
    }

    boolean isSquare() {
        return Math.sqrt(num) % 1.0 == 0.0;
    }

    boolean isSunny() {
        return new NaturalNumber(num + 1).isSquare();
    }

    boolean hasProperty(String value) {
        switch (value) {
            case "EVEN" : return isEven();
            case "ODD": return !isEven();
            case "BUZZ": return isBuzz();
            case "DUCK": return isDuck();
            case "PALINDROMIC": return isPalindromic();
            case "GAPFUL": return isGapful();
            case "SPY": return isSpy();
            case "SQUARE": return isSquare();
            case "SUNNY": return isSunny();

        }
        return false;
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
        if(isSpy()) result += ", spy";
        if(isSquare()) result += ", square";
        if(isSunny()) result += ", sunny";

        return result;
    }

    String getPropertiesAsMultilineStr() {

        return "buzz: " + (isBuzz()) + "\n" +
                "duck: " + isDuck() + "\n" +
                "palindromic: " + isPalindromic() + "\n" +
                "gapful: " + isGapful() + "\n" +
                "spy: " + isSpy() + "\n" +
                "square: " + isSquare() + "\n" +
                "sunny: " + isSunny() + "\n" +
                "even: " + isEven() + "\n" +
                "odd: " + !isEven() + "\n";
    }

}