package com.acmp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {

    private static int sequenceLength;
    private static int[] numbers;
    private static int sequence;

    public static void main(String[] args) {
        try {
            readAndCheckFile();
            findSequenceLength();
            writeFile();
        } catch (FileNotFoundException | TaskException e) {
            e.printStackTrace();
        }
    }

    private static void readAndCheckFile() throws FileNotFoundException, TaskException {
        FileInputStream file = new FileInputStream("INPUT.txt");
        Scanner inputFile = new Scanner(file);
        sequenceLength = Integer.parseInt(inputFile.nextLine());
        numbers = new int[sequenceLength];
        final String[] array = inputFile.nextLine().split(" ");
        checkInputFile();
        for (int i = 0; i < array.length; i++) {
            numbers[i] = Integer.parseInt(array[i]);
        }
    }

    private static void checkInputFile() throws TaskException {
        if (2 > sequenceLength || sequenceLength > 30000) {
            throw new TaskException("Please, check INPUT.txt file.");
        }
    }

    private static void findSequenceLength() {
        for (int period = 1; period <= sequenceLength / 2; period++) {
            if ((sequenceLength - 1) % period == 0) {
                for (int i = period; i < sequenceLength; i++) {
                    if (numbers[i - period] != numbers[i]) {
                        break;
                    } else if (i == sequenceLength - 1) {
                        sequence = period;
                        return;
                    }
                }
            }
        }
        sequence = sequenceLength - 1;
    }

    private static void writeFile() {
        File file = new File("OUTPUT.txt");
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(file);
            writer.print(sequence);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            writer.flush();
            writer.close();
        }
    }

}

class TaskException extends Exception {

    public TaskException(String message) {
        super(message);
    }

}
