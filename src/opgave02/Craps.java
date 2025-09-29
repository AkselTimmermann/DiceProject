package opgave02;

import java.util.Scanner;

public class Craps {
        public static int tabte = 0; // gemmer værdien af tabte spil
        public static int vundne = 0; // gemmer værdien af vundne spil

    public static void main(String[] args) {
        System.out.println("Velkommen til spillet, craps.");
        printRules();
        System.out.println();

        playCraps();

        System.out.println();
        System.out.println("Tak for at spille craps");
    }

    public static void playCraps() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Spil craps? ('ja/nej') ");
        String answer = scanner.nextLine();
        while (!answer.equals("nej")) {
            int[] faces = rolledDice(2);// gemmer resultatet fra rolledDice i variablen faces
            int sum = faces[0] + faces[1]; // gemmer værdien af de 2 terninger
            printNumbers(faces); // printer værdierne af de to terninger
            if (sum == 7 || sum == 11) {
                System.out.println("du slog: " + sum + " Du vandt!");
                vundne++;
            } else if (sum == 2 || sum == 3 || sum == 12) {
                System.out.println("Du slog " + sum + " Du tabte..");
                tabte++;
            } else {
                rollforPoint(sum);
            }
            System.out.print("Spil craps? ('ja/nej') ");
            answer = scanner.nextLine();
        }
        System.out.println("Du vandt " + vundne + " spil");
        System.out.println("Du tabte " + tabte + " spil");
        scanner.close();
    }

    public static boolean rollforPoint(int point) {
        while (true) { //gentage dette loop indtil sum = point eller sum = 7
            int[] faces = rolledDice(2); // spiller slår terninger igen
            int sum = faces[0] + faces[1]; // gemmer værdien af de to terninger i sum
            printNumbers(faces); // printer de 2 værdier
            if (sum == point) {
                System.out.println("Du slog: " + sum + " Du vandt!");
                vundne++;
                return true;
            } else if (sum == 7) {
                System.out.println("Du slog: " + sum + " Du tabte...");
                tabte++;
                return false;
            }
        }
    }

    public static int[] rolledDice(int x) { // metode, der returner array med værdierne af rollDice
        int[] rolledDice = new int[x];
        for (int i = 0; i < x; i++) {
            rolledDice[i] = rollDice();
        }
        return rolledDice;
    }

    public static int rollDice() { // metode der fungerer som 1 terningekast
        return (int) (Math.random() * 6 + 1);
    }

    public static void printNumbers(int[] arr) {
        for (int number : arr) {

            System.out.print(number + " ");
        }
        System.out.println(" ");
    }
    public static void printRules() {
        System.out.println("=====================================================");
        System.out.println("Regler for craps");
        System.out.println("Hvis spiller slår 7 eller 11 har man vundt");
        System.out.println("hvis spiller slår 2, 3 eller 12 har spiller tabt");
        System.out.println("Hvis spiller slår en af de resterende tal er dette spillerens point. Spiller slår igen");
        System.out.println("Hvis spiller slår tallet point har spiller vundet, hvis spiller slår 7 har denne tabt");
        System.out.println("=====================================================");
    }
}
