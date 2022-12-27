package org.example;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.Scanner;
import java.util.TreeSet;

//Даны два текстовых файла, записать в третий файл только те строки которые есть и в первом текстовом файле и во втором.
public class Main {
    public static void main(String[] args) throws IOException {
        Collection<String> collection = new TreeSet<>();
        String firstFileName="in1.txt";
        String secondFileName="in2.txt";
        String resulFileName="out.txt";

        addToCollectionFromFile(collection,firstFileName);

        if(new File(secondFileName).exists()) {
            FileReader fr = new FileReader(secondFileName);
            Scanner scanner = new Scanner(fr);
            FileWriter fw = new FileWriter(resulFileName);

            while (scanner.hasNextLine()) {
               if(collection.contains(scanner.nextLine())) {fw.write(scanner.nextLine()+'\n');
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