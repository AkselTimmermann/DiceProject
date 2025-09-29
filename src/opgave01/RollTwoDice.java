package opgave01;

import java.util.Scanner;

public class RollTwoDice {
    private static int rollCount = 0;
    private static int sum = 0; // Summen af terningekast
    private static int count = 0; // gemmer antallet af samme øjne i terningekast
    private static int højesteKast = 0; // gemmer det højeste kast
    private static int[] antal = new int[6]; // gemmer antal gange 1'ere, 2'ere osv er forekommet

    static void main(String[] args) {
        System.out.println("Velkommen til spillet, rul en terning.");
        printRules();
        System.out.println();

        playOneDie();

        System.out.println();
        System.out.println("Tak for at spille, rul en terning.");

    }

    private static void printRules() {
        System.out.println("=====================================================");
        System.out.println("Regler for rul en terning");
        System.out.println("Spilleren ruller en terning, så længde man lyster.");
        System.out.println("=====================================================");
    }

    private static void playOneDie() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Rul en terning? ('ja/nej') ");
        String answer = scanner.nextLine();
        while (!answer.equals("nej")) {
            int[] faces = rolledDice(2); // vi gemmer resultatet fra rolledDice i variablen faces.
            System.out.print("Du rullede: ");
            printNumbers(faces); // print resultat

            updateStatistics(faces); // opdater

            System.out.print("Rul en terning? ('ja/nej') ");
            answer = scanner.nextLine();
        }

        printStatistics();
        scanner.close();
    }

    private static int rollDie() {
        return (int) (Math.random() * 6 + 1);
    }

    private static int[] rolledDice(int x) { // metode, der laver nyt array, som gemmer resultatet fra rolldie.
        int[] rolledDice = new int[x];
        for (int i = 0; i < x; i++) {
            rolledDice[i] = rollDie();
        }
        return rolledDice;
    }
    public static void printNumbers(int[] integerArray) { // printer numbers
        for (int number : integerArray) {
            System.out.print(number + " ");
        }
        System.out.println(" ");
    }

    public static void printArray(int[] arr) { // printer et integer array
        for (int i = 0; i < arr.length; i++) {
            System.out.print(i + 1 + ": " + arr[i] + " gange " + " | ");
        }
    }

    private static void updateStatistics(int[] faces) {
        int tempSum = 0; //
        for (int x : faces) {
            antal[x - 1]++; // gemmer antal af hver værdi i et array
            tempSum += x; // gemmer nuværende værdi i tempSum
            sum += x; // ligger værdierne sammen og gemmer i variablen sum
        }


        if (tempSum > højesteKast) { // hvis tempSum er højere end højesteKast, så gemmes denne værdi
            højesteKast = tempSum;
        }
        if (faces[0] == faces [1]) { // hvis terning 1 har samme øjne som terning 2, gem i count
            count++;
        }

        rollCount++;
    }

    private static void printStatistics() {
        System.out.println("\nResults:");
        System.out.println("-------");
        System.out.printf("%17s %4d\n", "Antal rul:", rollCount);
        System.out.println();
        System.out.println("Summen af alle terningekast: " + sum);
        System.out.println("de to terninger havde samme antal øjne " + count + " gange");
        System.out.println("højeste terningekast: " + højesteKast);
        printArray(antal);
        System.out.println();

    }
}
