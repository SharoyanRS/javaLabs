package org.example;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

//получить 10 пользователей с наибольшим трафиком
// получить 10 популярных сайтов

public class Main {
    public static void main(String[] args) throws IOException {
        Map<String, Integer> firstMap=new HashMap<>();
        Map<String, Integer> secondMap=new HashMap<>();
        String inputName="access.log";
        String outputName="out.txt";
        File file = new File(inputName);

        if (file.exists()) {
            String[] temp;
            FileReader fr = new FileReader(file);
            Scanner scanner = new Scanner(fr);
            while (scanner.hasNextLine()) {
                temp = scanner.nextLine().split("\\s+");

                if (firstMap.containsKey(temp[2]))
                    firstMap.put(temp[2], Integer.parseInt(temp[4]) + firstMap.get(temp[2]));
                else firstMap.put(temp[2], Integer.parseInt(temp[4]));

                if (secondMap.containsKey(temp[6])) secondMap.put(temp[6], secondMap.get(temp[6]) + 1);
                else secondMap.put(temp[6], 1);
            }
            fr.close();
            scanner.close();

            FileWriter fw = new FileWriter(outputName);
            fw.write("10 пользователей с наибольшим трафиком:\n");
            fw.write("  IP адрес пользователя            Использованный трафик\n");
            firstMap.entrySet().stream().sorted(Map.Entry.<String, Integer>comparingByValue().reversed()).limit(10).
                    forEach(e -> {
                        try {
                            fw.write(e.getKey() + "               " + e.getValue());
                            fw.write('\n');
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                    });
            fw.write("\n\n10 самых популярных сайтов:\n");
            fw.write("  URL адрес                              Число вхождений\n");
            secondMap.entrySet().stream().sorted(Map.Entry.<String, Integer>comparingByValue().reversed()).limit(10).
                    forEach(e -> {
                        try {
                            fw.write(e.getKey() + "   " + e.getValue());
                            fw.write('\n');
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                    });
            fw.close();
        }
    }

}