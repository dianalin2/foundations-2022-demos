import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
public class Grid extends JPanel
{
   private ActionListener listener;
   private JButton startButton;
   private String ans, word, guessed;
   private Letter[][] letters;
   private int row, col;
   private boolean isWon;
   public Grid(ActionListener al)
   {
      setLayout(new GridLayout(6, 1, 10, 10));
      setBorder(BorderFactory.createEmptyBorder(15, 80, 20, 80));
      setBackground(Color.WHITE);

      listener = al;

      ans = Dictionary.randomAnswer();
      word = "";
      guessed = "";

      letters = new Letter[6][5];
      for (int i = 0; i < 6; i++)
      {
         for (int j = 0; j < 5; j++)
         {
            letters[i][j] = new Letter();
            add(letters[i][j]);
         }
      }

      row = col = 0;
      letters[0][0].setInFocus();

      setFocusable(true);
      addKeyListener(new Keys());

      requestFocus();
   }

   private class Keys extends KeyAdapter
   {
      public void keyPressed(KeyEvent e)
      {
         if (row >= 6)
            return;
 
         int key = e.getKeyCode();
         if (key == KeyEvent.VK_BACK_SPACE)
            deleteLetter();
         else if (key == KeyEvent.VK_ENTER)
            enter();
         else if (key >= KeyEvent.VK_A && key <= KeyEvent.VK_Z)
            writeLetter((char)('A' + (key-KeyEvent.VK_A)));
      }
   }

   public void writeLetter(char c)
   {
      if (col >= 5)
         return;

      letters[row][col].setOutOfFocus();
      letters[row][col].setText(c+"");
      col++;
      if (col < 5)
         letters[row][col].setInFocus();

      word += c;
   }

   public void deleteLetter()
   {
      if (col <= 0)
         return;

      if (col < 5)
         letters[row][col].setOutOfFocus();
      col--;
      letters[row][col].setText("");
      letters[row][col].setInFocus();

      word = word.substring(0, word.length()-1);
   }

   public void enter()
   {
      if (col < 5 || !Dictionary.isWord(word) || guessed.contains(word))
         return;
      if (word.equals(ans) || row == 5)
      {
         isWon = word.equals(ans);
         listener.actionPerformed(null);
         return;
      }

      int[] letterCount = new int[26];
      for (int i = 0; i < 5; i++)
         if (word.charAt(i) != ans.charAt(i))
            letterCount[ans.charAt(i)-'A']++;

      for (int i = 0; i < 5; i++)
      {
         if (word.charAt(i) == ans.charAt(i))
            letters[row][i].setBackground(Color.GREEN);
         else if (letterCount[word.charAt(i)-'A']-- > 0)
            letters[row][i].setBackground(Color.YELLOW);
         else
            letters[row][i].setBackground(Color.LIGHT_GRAY);
         letters[row][i].setBorder(null);
      }

      col = 0;
      row++;
      if (row < 6)
         letters[row][0].setInFocus();

      guessed += word + " ";
      word = "";
   }

   public boolean isWon() { return isWon; }
}