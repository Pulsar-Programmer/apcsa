///Note:
/// Triple Comments (///) are intended to be read.
/// Double Comments (//) are for me, and don't have to do with any documentation.

import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        ///Create a scanner to obtain user input.
        final Scanner user_input = new Scanner(System.in);
        System.out.println("Type in the name of the input file to translate. Ensure you include .txt at the end of the file name.");
        ///Sanitizing user input is unnecessary, so we simply store the answer in a String.
        final String input = user_input.nextLine();
        user_input.close();

        ///A file reader is initialized which is hooked up to the aforementioned file.
        ///IMPORTANT: MAKE SURE YOU HAVE THIS FILE IN YOUR FS.
        final Scanner file_reader = new Scanner(new File(input));

        ///A file writer is initialized which is hooked up to the output file.
        final PrintWriter file_writer = new PrintWriter(new File("piglatin.txt"));

        ///An empty string is created for the loop.
        String str = "";
        ///Now, we loop over the corresponding file_reader and add all the words to the previous string.
        ///We also convert each word to pig latin then output it.
        while (file_reader.hasNext()) str += (to_pig_latin(file_reader.next()) + " ");

        // System.out.println(str);

        ///We add the output to the file_writer. Remember: this is the output.txt file.
        file_writer.print(str);

        ///We must finally close both the file_reader and the file_writer to ensure program safety.
        file_writer.close(); file_reader.close();


    }

    public static String to_pig_latin(String str) {
        ///We must check if the string is empty and return nothing if it is.
        if (str.isEmpty()) return "";

        ///We must make an empty string for the suffix. If the suffix is either of these characters,
        ///we subtract it and then add it back later.
        String suffix = "";
        if (str.endsWith(".") || str.endsWith("?") || str.endsWith("!")){
            suffix = Character.toString(str.charAt(str.length() - 1));
            str = str.substring(0, str.length()-1);
        }

        ///If there is a vowel, we use the pig_latin_vowel function. Otherwise, we use the consonant function.
        if ("hyaiueo".contains(Character.toString(str.charAt(0)).toLowerCase())) {
            return pig_latin_vowel(str) + suffix;
        } else {
            return pig_latin_otherwise(str) + suffix;
        }

    }

    static String pig_latin_vowel(String str) {
        ///Here, we simply add `way` if it is a vowel.
        return str + "way";
    }

    static String pig_latin_otherwise(String str) {
        ///We first keep track whether there is an uppercase in the beginning. If so, we keep track of this as a boolean.
        boolean uppercase = Character.isUpperCase(str.charAt(0));
        String string = str.toLowerCase();
        ///Find the portion where the splitting on the vowel occurs.
        int portion = 0;
        for (int i = 0; i < string.length(); i++) {
            ///If this char is a vowel, we break and we set the portion. Otherwise, the loop increments until it elapses.
            if ("hyaiueo".contains(Character.toString(string.charAt(i)))) {
                portion = i;
                break;
            }
        }
        ///We construct a slice up to the vowel to move.
        String slice = string.substring(0, portion);
        ///We select the remaining of the string.
        String remaining = string.substring(portion);
        ///We construct the result via the three portions we made. This is the general pattern.
        String result = remaining + slice + "ay";
        ///Don't forget to conserve the uppercasing at the beginning! Here, we add the rest of the result to the first character, but this time uppercased.
        if (uppercase) {
            result = Character.toUpperCase(result.charAt(0)) + result.substring(1);
        }
        return result;
    }
}
