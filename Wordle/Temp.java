import java.io.*;
import java.util.*;

public class Temp
{
   public static void main(String[] args) throws IOException
   {
      BufferedReader br = new BufferedReader(new FileReader("answers.txt"));

      ArrayList<String> words = new ArrayList<String>();
      StringTokenizer st = new StringTokenizer(br.readLine(), "\",\"");
      while (st.hasMoreTokens())
         words.add(st.nextToken());

      br.close();

      PrintWriter pw = new PrintWriter(new FileWriter("answers.txt"));

      pw.println(words.size());
      for (String s : words)
         pw.println(s);

      pw.close();
   }
}