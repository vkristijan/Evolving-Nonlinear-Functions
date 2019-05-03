package hr.fer.zemris.diplproj;

import hr.fer.zemris.diplproj.walsh.FWT;
import hr.fer.zemris.diplproj.walsh.ITransform;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Simple program that calculates the Walsh coefficients of the given boolean function.
 * The function is read from standard input, defined by it's truth table.
 *
 * Example input:
 *    4
 *    0111000010111101
 *
 * @author Kristijan Vulinovic
 */

public class CalculateWalsh {
    /**
     * Entry point.
     *
     * @param args console arguments, ignored.
     */
    public static void main(String[] args) {
        BoolFunction f = readFunction();

        ITransform transform = new FWT();
        f.setWalshTransform(transform);

        f.getWalshSpectrum().forEach(x -> {
            System.out.print(x + " ");
        });
    }

    /**
     * Reads the boolean function from standard input.
     *
     * @return the boolean function from standard input.
     */
    private static BoolFunction readFunction() {
        Scanner scanner = new Scanner(System.in);

        int degree = scanner.nextInt();
        int n = (1 << degree);

        String s = scanner.next();
        char[] c = s.toCharArray();
        List<Integer> truthTable = new ArrayList<>(n);
        for (int i = 0; i < n; ++i){
            truthTable.add(c[i] - '0');
        }
        scanner.close();

        return new BoolFunction(degree, truthTable);
    }
}
