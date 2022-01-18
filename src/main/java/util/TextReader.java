package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TextReader {

    public static String searchStringInTheTXT(String filePath, String fileName, String startWord, String endWord) {
        File fileToRead = new File(filePath + File.separator + fileName);
        List<String> list = new ArrayList<>();

        try (FileReader reader = new FileReader(fileToRead);
             BufferedReader bufferedReader = new BufferedReader(reader)) {

            List<String> listFromTXT = new ArrayList<>();
            bufferedReader.lines().forEach(listFromTXT::add);
            List<String> listFoundText = new ArrayList<>();
            listFromTXT.forEach(s -> listFoundText.add(getStringByStartAndEndWords(s, startWord, endWord)));
            list = listFoundText.stream().filter(s -> s != null).collect(Collectors.toList());

        } catch (IOException e) {
            e.printStackTrace();
        }
        return list.get(0);
    }

    public static String searchStringInTheString(String textForSearch, String startWord, String endWord) {
        List<String> listFoundText = new ArrayList<>();
        Arrays.asList(textForSearch.split("\n"))
                .forEach(s -> listFoundText.add(getStringByStartAndEndWords(s, startWord, endWord)));
        List<String> list = listFoundText.stream().filter(s -> s != null).collect(Collectors.toList());
        return list.get(0);
    }

    private static String getStringByStartAndEndWords(String string, String startWord, String endWord) {

        if (string.contains(startWord) && string.contains(endWord)) {
            String[] arrayStringStart = string.split(startWord);
            String[] arrayStringEnd = arrayStringStart[1].split(endWord);
            return startWord + arrayStringEnd[0] + endWord;
        } else return null;
    }

}
