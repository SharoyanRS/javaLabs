package org.example;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Scanner;

//записать в третий файл только те строки которые есть в первом файле

public class Main {
    public static void main(String[] args) throws IOException {
        Collection<String> collection = new LinkedHashSet<>();
        String firstFileName="in1.txt";
        String secondFileName="in2.txt";
        String resulFileName="out.txt";

        addToCollectionFromFile(collection,secondFileName);

        if(new File(firstFileName).exists()) {
            FileReader fr = new FileReader(firstFileName);
            Scanner scanner = new Scanner(fr);
            FileWriter fw = new FileWriter(resulFileName);

            while (scanner.hasNextLine()) {
                String temp=scanner.nextLine();
                if(!collection.contains(temp)) {fw.write(temp+'\n');
                }
            }
            fr.close();
            scanner.close();
            fw.close();
        }
    }

    public static void addToCollectionFromFile(Collection<String> c, String fileName) throws IOException {
        if(new File(fileName).exists()) {
            FileReader fr = new FileReader(fileName);
            Scanner scanner = new Scanner(fr);
            while (scanner.hasNextLine()) {
                c.add(scanner.nextLine());
            }
            fr.close();
            scanner.close();
        }
    }
}