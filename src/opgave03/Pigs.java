package opgave03;

import java.util.Scanner;

public class Pigs {
    public static int turnTotal = 0; // Hver turs værdier lagt sammen
    public static int[] score = {0, 0}; // array til spillers scores
    public static int[] slag = {0, 0}; // array der gemmer antal slag for spilleren
    public static int[] ture = {0, 0}; // array der gemmmer antal ture for spilleren

    public static void main(String[] args) {
        System.out.print("Velkommen til spillet Pigs");
        System.out.println();
        printRules();
        playPigs();
        statistics();
        System.out.println("tak for at spille pigs!");
    }

    public static void playPigs() {
        int currentPlayer = 0; // variable til at styre hvilken spillers tur det er
        Scanner scanner = new Scanner(System.in);
        System.out.print("Spil pigs? ('ja/nej') ");
        String answer = scanner.nextLine();
        if (!answer.equalsIgnoreCase("ja")) {
            scanner.close();
            return;
        }

        System.out.print("Hvor mange point vil i spille til? "); // gemmer point de vil spille til i variablen point
        int point = scanner.nextInt();
        scanner.nextLine();

        boolean gameRunning = true;
        while (gameRunning) {
            turnTotal = 0; // nulstil turnTotal ved start af hver runde
            System.out.println("Det er spiller " + (currentPlayer + 1) + "s tur");
            boolean playersTurn = true;

            while (playersTurn) { // indre while loop, der bruges til at skifte turen alt afhængig af turens gang
                int[] faces = rolledDice(2);
                int sum = faces[0] + faces[1];
                slag[currentPlayer]++;

                System.out.print("Du slog: ");
                printNumbers(faces);
                System.out.println();

                if (faces[0] == 1 && faces[1] == 1) {
                    System.out.println("Du slog to 1'ere. Du har mistet alle dine point");
                    ture[currentPlayer]++;
                    turnTotal = 0;
                    score[currentPlayer] = 0; // nulstiller spillerens totale point værdi, ikke kun rundens
                    playersTurn = false; // indre løkke er nu false, så ydre loop kører igen
                    currentPlayer = 1 - currentPlayer; // skift spiller

                } else if (faces[0] == 1 || faces[1] == 1) {
                    System.out.println("Du slog 1. Du har mistet denne turs point");
                    ture[currentPlayer]++;
                    turnTotal = 0;
                    playersTurn = false;
                    currentPlayer = 1 - currentPlayer;
                } else {
                    turnTotal += sum; // plus summen af de 2 terninger til turntotal
                    System.out.print("Vil du fortsætte? ('ja/nej') ");
                    String input = scanner.nextLine();
                    if (input.equalsIgnoreCase("nej")) {// Hvis spiller ikke vil fortsætte ligger vi turnTotal til den totale score
                        ture[currentPlayer]++;
                        score[currentPlayer] += turnTotal;
                        tempStatistics();
                        if (score[currentPlayer] >= point) { // Hvis spiller når værdien point har spilleren vundet
                            System.out.println("spiller" + (currentPlayer + 1) + " har vundet");
                            gameRunning = false; //Spillet er nu færdigt
                        }
                        currentPlayer = 1 - currentPlayer; // skifter mellem spiller 1 og 2
                        playersTurn = false;
                    }
                }
            }
        }
        scanner.close();
    }

    public static void tempStatistics() {
        System.out.println("Du har " + turnTotal + " point i denne tur");
        System.out.println();
        System.out.println("Spiller 1 har " + score[0] + " point");
        System.out.println("Spiller 2 har " + score[1] + " point");
        System.out.println();
    }

    public static void statistics() {
        double[] gennemsnit = new double[2];
        for (int i = 0; i < gennemsnit.length; i++) {
            gennemsnit[i] = (double) slag[i] / ture[i];
        }
        System.out.println("gennemsnittet af slag pr tur for spiller 1: " + gennemsnit[0]);
        System.out.println("gennemsnitte af slag pr tur for spiller 2: " + gennemsnit[1]);

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
    }

    public static void printRules() {
        System.out.println("=====================================================");
        System.out.println("Regler for pigs");
        System.out.println("Spillere vælger antal point, der spilles til");
        System.out.println("Hvis spiller slår to 1'ere mistes alle opnåede point");
        System.out.println("hvis spiller slår en 1'er mistes opnåede point i runden");
        System.out.println("=====================================================");
    }

}
