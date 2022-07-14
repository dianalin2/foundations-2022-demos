import java.io.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GamePanel extends JPanel
{
   private ActionListener myListener;
   private JPanel screen;
   private JLabel stats;
   private int wins, streak;
   public GamePanel()
   {
      setLayout(new BorderLayout());
      setBackground(Color.WHITE);

      JPanel header = new JPanel();
      header.setLayout(new GridLayout(2, 1, 5, 5));
      header.setBackground(Color.WHITE);

      JLabel title = new JLabel("Wordle", SwingConstants.CENTER);
      title.setFont(new Font("Georgia", Font.PLAIN, 30));
      header.add(title);

      stats = new JLabel("---", SwingConstants.CENTER);
      header.add(stats);
      add(header, BorderLayout.NORTH);

      myListener = new ScreenListener();
      screen = new Start(myListener);
      add(screen, BorderLayout.CENTER);

      try
      {
         Scanner sc = new Scanner(new File("stats.txt"));
         wins = sc.nextInt();
         streak = sc.nextInt();
      }
      catch(Exception e)
      {
         wins = streak = 0;
      }

      JButton exit = new JButton("Exit");
      exit.addActionListener(new ExitListener());
      add(exit, BorderLayout.SOUTH);
   }

   private void updateStats()
   {
      if (!stats.getText().equals("---") && !((Grid)screen).isWon())
         streak = 0;
      else if (!stats.getText().equals("---"))
      {
         wins++;
         streak++;
      }
      stats.setText("Wins: "+wins+"      Streak: "+streak);
   }

   private class ScreenListener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         updateStats();

         remove(screen);
         screen = new Grid(myListener);
         add(screen, BorderLayout.CENTER);

         revalidate();
         repaint();

         screen.requestFocus();
      }
   }

   private class ExitListener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         try
         {
            PrintWriter pw = new PrintWriter(new FileWriter("stats.txt"));
            pw.println(wins+" "+streak);
            pw.close();
         }
         catch(IOException ex)
         {
            System.err.println("Stats cannot be saved");
         }
         System.exit(0);
      }
   }
}