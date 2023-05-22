package org.example;

import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        FormulaChecking.reference();
        String formula = null;
        Scanner input = new Scanner(System.in);
        while (!Objects.equals(formula, "0")) {
            System.out.print("Введите формулу: ");
            formula = input.nextLine();

            FormulaChecking check = new FormulaChecking(formula);
            System.out.println(check.getFormula());
            System.out.println(check.getResult());
        }
    }
}