import java.io.PrintStream;
import java.util.Scanner;
public class Main {
    public static Scanner in = new Scanner(System.in);
    public static PrintStream out = System.out;
    public static void main(String[] args) {
        int n = in.nextInt();
        in.nextLine();
        String[] words = new String[n];
        for (int i = 0; i < n; i++) {
            words[i] = in.nextLine();
        }
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                boolean f = false;
                if (words[j].length() > words[j + 1].length()) {
                    f = true;
                }
                else if (words[j].length() == words[j + 1].length()) {
                    for (int k = 0; k < words[j].length(); k++) {
                        if (words[j].charAt(k) > words[j + 1].charAt(k)) {
                            f = true;
                            break;
                        } else if (words[j].charAt(k) < words[j + 1].charAt(k)) {
                            break;
                        }
                    }
                }
                if (f) {
                    String z = words[j];
                    words[j] = words[j + 1];
                    words[j + 1] = z;
                }
            }
        }
        String longestWord = words[0];
        for (int i = 1; i < n; i++) {
            if (words[i].length() > longestWord.length()) {
                longestWord = words[i];
            }
        }
        out.println(longestWord);

        boolean hasAnyAnagrams = false;
        boolean[] visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                boolean hasAnagrams = false;
                visited[i] = true;
                for (int j = i + 1; j < n; j++) {
                    if (!visited[j]) {
                        if (words[i].length() == words[j].length()) {
                            int[] charCount = new int[256];
                            boolean areAnagrams = true;
                            for (int k = 0; k < words[i].length(); k++) {
                                charCount[words[i].charAt(k)]++;
                                charCount[words[j].charAt(k)]--;
                            }
                            for (int k = 0; k < 256; k++) {
                                if (charCount[k] != 0) {
                                    areAnagrams = false;
                                    break;
                                }
                            }
                            if (areAnagrams) {
                                if (!hasAnagrams) {
                                    out.print(words[i] + " ");
                                    hasAnagrams = true;
                                }
                                out.print(words[j] + " ");
                                visited[j] = true;
                            }
                        }
                    }
                }
                if (hasAnagrams) {
                    out.println();
                    hasAnyAnagrams = true;
                }
            }
        }
        if (!hasAnyAnagrams) {
            out.println("Нет анаграмм");
        }
        char[] vowels = {'a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'};
        for (int i = 0; i < n; i++) {
            String newWord = "";
            for (int j = 0; j < words[i].length(); j++) {
                char ch = words[i].charAt(j);
                boolean isVowel = false;
                for (int k = 0; k < vowels.length; k++) {
                    if (ch == vowels[k]) {
                        isVowel = true;
                        break;
                    }
                }
                if (isVowel) {
                    ch = (char) (ch + 1);
                }
                newWord += ch;
            }
            words[i] = newWord;
        }
        for (int i = 0; i < n; i++) {
            out.println(words[i]);
        }
    }
}
