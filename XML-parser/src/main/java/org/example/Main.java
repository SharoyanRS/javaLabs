package org.example;



import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    static int totalSum=0;
    static Map<String,Integer> productAmount = new LinkedHashMap<>();
    static Map<String,Integer> categoriesAmount = new LinkedHashMap<>();


    public static void main(String[] args) throws IOException {


       File[] filesList = new File("./").listFiles();
       for(File file:filesList){
       if (file.isFile()) if(Pattern.matches("\\d+\\.xml$",file.getName())){
           String tempString="";
           FileReader fr = new FileReader(file.getAbsolutePath());
           Scanner scanner = new Scanner(fr);
           while(scanner.hasNextLine()){
               tempString+=scanner.nextLine();
           }
           fr.close();
           scanner.close();
           xmlToMaps(tempString);
       }
       }

       FileWriter fw = new FileWriter("out.txt");
       fw.write("Товаров на складе:\n");
       productAmount.entrySet().stream().forEach(e -> {
                   try {
                       fw.write(e.getKey() + ": " + e.getValue()+'\n');
                   } catch (IOException ex) {
                       throw new RuntimeException(ex);
                   }
               });
       fw.write("\nНаименований товаров:\n");
       categoriesAmount.entrySet().stream().forEach(e -> {
            try {
                fw.write(e.getKey() + ": " + e.getValue()+'\n');
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
       fw.write("\nСуммарная стоимость: "+totalSum);
       fw.close();
    }

    public static String getTagValue(String tag, String source){
        Pattern pattern;
        pattern = Pattern.compile("(<"+tag+"\\svar=\"|<"+tag+">)(.*?)(\"/>|</"+tag+">)");
        Matcher matcher = pattern.matcher(source);
        if(matcher.find()) {
           return new String(matcher.group(2));
        }
        else return null;
    }

    public static void xmlToMaps(String source){
        String type = "type";
        String quantity = "quantity";
        String price = "price";

        if (!productAmount.containsKey(getTagValue(type,source))) productAmount.put(getTagValue(type,source), Integer.parseInt(getTagValue(quantity,source)));
        else productAmount.put(getTagValue(type,source), productAmount.get(getTagValue(type,source))+ Integer.parseInt(getTagValue(quantity,source)));

        if (!categoriesAmount.containsKey(getTagValue(type,source))) categoriesAmount.put(getTagValue(type,source),1);
        else categoriesAmount.put(getTagValue(type,source), categoriesAmount.get(getTagValue(type,source))+1);

        totalSum+=Integer.parseInt(getTagValue(quantity,source))*Integer.parseInt(getTagValue(price,source));
    }

}