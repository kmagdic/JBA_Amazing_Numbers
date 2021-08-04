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
                "- separate the parameters with one space;\n" +
                "- enter 0 to exit.");

        long num = 0;

        do {
            System.out.print("Enter a request: > ");

            String[] inputParams = scanner.nextLine().strip().split(" ");


            if (inputParams.length == 2) {
                try {
                    num = Long.parseLong(inputParams[0]);
                    long counter = Long.parseLong(inputParams[1]);

                    if (counter <= 0) {
                        System.out.println("Second parameter should be a natural number.");
                        continue;
                    }
                    for (long i = 0; i < counter; i++) {
                        NaturalNumber n = new NaturalNumber(num + i);
                        System.out.println(n.getValue() + " is " + n.getPropertiesAsStr());
                    }

                } catch (ArithmeticException e) {
                    System.out.println("The first parameter should be a natural number or zero.");
                }

            } else if (inputParams.length == 1) {

                try {
                    num = Long.parseLong(inputParams[0]);
                    if (num < 1) {
                        System.out.println("The first parameter should be a natural number or zero.");
                        continue;
                    }
                } catch (Exception e) {
                    System.out.println("The first parameter should be a natural number or zero.");
                    continue;
                }
                System.out.println("Properties of " + num);
                System.out.println(new NaturalNumber(num).getPropertiesAsMultilineStr());

            }

        } while (num != 0);

    }
}

