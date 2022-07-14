import java.io.*;
import java.util.*;
public class Dictionary
{
   private static String[] dictionary;
   private static String[] answers;
   static
   {
      if (dictionary == null || answers == null)
      {
         try
         {
            Scanner sc = new Scanner(new File("words.txt"));
            dictionary = new String[sc.nextInt()];
            for (int i = 0; i < dictionary.length; i++)
               dictionary[i] = sc.nextLine().toUpperCase();

            sc = new Scanner(new File("answers.txt"));
            answers = new String[sc.nextInt()];
            for (int i = 0; i < answers.length; i++)
               answers[i] = sc.nextLine().toUpperCase();
         }
         catch (FileNotFoundException e)
         {
            System.exit(1);
         }
      }
   }

   public static String randomAnswer()
   {
      return answers[(int)(Math.random()*answers.length)];
   }

   public static boolean isWord(String arg)
   {
      for (int i = 0; i < dictionary.length; i++)
         if (dictionary[i].equals(arg))
            return true;
      return false;
   }
}