package reader;

import util.TextReader;

public class ReadTXT {
    public static void main(String[] args) {
        System.out.println(TextReader.searchStringInTheTXT("sh_scripts", "testTXT.txt", "start", "end"));
    }
}
