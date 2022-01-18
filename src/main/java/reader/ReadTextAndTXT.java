package reader;

import util.TextReader;

public class ReadTextAndTXT {
    public static void main(String[] args) {

        System.out.println(TextReader.searchStringInTheTXT("sh_scripts", "testTXT.txt", "start", "end"));

        String string = "1 some text start false some text\n" +
                "2 some text some text test start my text is good end some text\n" +
                "3 some text some text end some text";
        System.out.println(TextReader.searchStringInTheString(string, "start", "end"));
    }
}
