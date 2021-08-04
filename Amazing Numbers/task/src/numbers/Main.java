package numbers;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to Amazing Numbers!\n");
        System.out.println("Supported requests:\n" +
                "- enter a natural number to know its properties;\n" +
                "- enter two natural numbers to obtain the properties of the list:\n" +
                "  * the first parameter represents a starting number;\n" +
                "  * the second parameters show how many consecutive numbers are to be processed;\n" +
                "- two natural numbers and a property to search for;\n" +
                "- two natural numbers and two properties to search for;\n" +
                "- separate the parameters with one space;\n" +
                "- enter 0 to exit.");

        long inputNum = 0;

        do {
            System.out.print("Enter a request: > ");
            String[] inputParams = scanner.nextLine().strip().split(" ");

            try {
                inputNum = Long.parseLong(inputParams[0]);
                if (inputNum < 1) {
                    System.out.println("The first parameter should be a natural number or zero.");
                    continue;
                }
            } catch (Exception e) {
                System.out.println("The first parameter should be a natural number or zero.");
                continue;
            }

            if (inputParams.length == 1) {

                System.out.println("Properties of " + inputNum);
                System.out.println(new NaturalNumber(inputNum).getPropertiesAsMultilineStr());

            } else if (inputParams.length == 2) {
                long counter = Long.parseLong(inputParams[1]);

                if (counter <= 0) {
                    System.out.println("The second parameter should be a natural number or zerro.");
                    continue;
                }
                for (long i = 0; i < counter; i++) {
                    NaturalNumber n = new NaturalNumber(inputNum + i);
                    System.out.println(n.getValue() + " is " + n.getPropertiesAsStr());
                }


            } else if (inputParams.length == 3) {
                long numberOfResults = Long.parseLong(inputParams[1]);

                // second parameter
                if (numberOfResults <= 0) {
                    System.out.println("Second parameter should be a natural number.");
                    continue;
                }

                // third parameter
                String property = inputParams[2].toUpperCase();
                if (!NaturalNumber.existsProperty(property)) {
                    System.out.printf("The property [%s] is wrong.\n" +
                            "Available properties: [%s]\n", property, NaturalNumber.getPossiblePropertiesAsStr());
                    continue;

                }

                // go through number and find numbers with that property
                int counterOfResults = 0;
                long currNumber = inputNum;
                do {
                    NaturalNumber n = new NaturalNumber(currNumber);
                    if (n.hasProperty(property)) {
                        System.out.println(n.getValue() + " is " + n.getPropertiesAsStr());
                        counterOfResults ++;
                    }
                    currNumber ++;

                    if(currNumber == inputNum + 1000000) {
                        System.out.println("Stopping after one million number calculated!");
                        break;
                    }

                } while (counterOfResults < numberOfResults );
            } else if (inputParams.length == 4) {
                long numberOfResults = Long.parseLong(inputParams[1]);

                // second parameter
                if (numberOfResults <= 0) {
                    System.out.println("Second parameter should be a natural number.");
                    continue;
                }

                // third and forth
                String property1 = inputParams[2].toUpperCase();
                String property2 = inputParams[3].toUpperCase();

                if (NaturalNumber.arePropsMutuallyExclusive(property1, property2)) {
                    System.out.printf("The request contains mutually exclusive properties: [%s, %s]", property1, property2);
                    continue;
                }

               boolean property1Found = NaturalNumber.existsProperty(property1);
               boolean property2Found = NaturalNumber.existsProperty(property2);

               if (!property1Found && !property2Found) {
                    System.out.printf("The properties [%s, %s] are wrong.\n" +
                            "Available properties: [%s]\n", property1, property2, NaturalNumber.getPossiblePropertiesAsStr());
                    continue;

                } else if (!property1Found) {
                    System.out.printf("The property [%s] is wrong.\n" +
                            "Available properties: [%s]\n", property1, NaturalNumber.getPossiblePropertiesAsStr());
                    continue;
                } else if (!property2Found) {
                    System.out.printf("The property [%s] is wrong.\n" +
                            "Available properties: [%s]\n", property2, NaturalNumber.getPossiblePropertiesAsStr());
                    continue;
                }

                // go through number and find numbers with that properties
                int counterOfResults = 0;
                long currNumber = inputNum;
                do {
                    NaturalNumber n = new NaturalNumber(currNumber);
                    if (n.hasProperty(property1) && n.hasProperty(property2)) {
                        System.out.println(n.getValue() + " is " + n.getPropertiesAsStr());
                        counterOfResults ++;
                    }
                    currNumber ++;

                    if(currNumber == inputNum + 1000000) {
                        System.out.println("Stopping after one million number calculated!");
                        break;
                    }

                } while (counterOfResults < numberOfResults );
            }
        } while (inputNum != 0);


    }


}
