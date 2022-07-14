import javax.swing.*;

public class Main
{
   public static void main(String[] args)
   {
      JFrame frame = new JFrame("Wordle");
      frame.setSize(500, 550);
      frame.setLocation(100, 100);
      frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
      frame.setContentPane(new GamePanel());
      frame.setResizable(false);
      frame.setVisible(true);
   }
}