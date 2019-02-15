import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Anagrammer {
    public static void main(String[] args) throws IOException {
        if (args.length != 2) {
            System.out.println("Failure: Expected 2 arguments!");
            return;
        }
        long startTime = System.currentTimeMillis();
        List<String> anagrams = getAnagrams(extractWords(args[0]), args[1]);
        long duration = (System.currentTimeMillis() - startTime) * 1000;
        System.out.println(duration + "," + anagrams.stream().collect(Collectors.joining(",")));
    }

    public static List<String> extractWords(String filePath) throws IOException {
        String line;
        List<String> result = new ArrayList();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            while ((line = br.readLine()) != null) result.add(line);
        }
        return result;
    }

    public static boolean charactersMatch(Map<Character, Integer> charDict1, Map<Character, Integer> charDict2) {
        for (Character key : charDict1.keySet())
            if (charDict1.get(key) != charDict2.get(key)) return false;
        return true;
    }

    public static List<String> getAnagrams(List<String> words, String word) {
        List<String> result = new ArrayList();

        Map<Character, Integer> charDict1 = CountCharacters(word.toLowerCase().toCharArray());
        Map<Character, Integer> charDict2;

        int i;
        String word2;
        for (i = 0; i < words.size(); i++) {
            word2 = words.get(i);
            if (word.length() == word2.length()) {
                charDict2 = CountCharacters(word2.toLowerCase().toCharArray());
                if (charactersMatch(charDict1, charDict2))
                    result.add(words.get(i));
            }
        }

        return result;
    }

    public static Map<Character, Integer> CountCharacters(char[] word) {
        Map<Character, Integer> result = new HashMap();
        int i;
        char charI;
        for (i = 0; i < word.length; i++) {
            charI = word[i];
            if (result.containsKey(charI)) result.put(charI, result.get(charI) + 1);
            else result.put(charI, 1);
        }
        return result;
    }
}
