import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class Start extends JPanel
{
   public Start(ActionListener al)
   {
      setLayout(new FlowLayout(FlowLayout.CENTER, 100, 50));
      setBackground(Color.WHITE);
      setBorder(BorderFactory.createCompoundBorder(
                  BorderFactory.createEmptyBorder(15, 80, 20, 80),
                  BorderFactory.createLineBorder(Color.BLACK)));

      JLabel instruct = new JLabel("INSTRUCTIONS");
      instruct.setFont(new Font("Sans Serif", Font.BOLD, 20));
      add(instruct);

      JLabel info = new JLabel("Press \"Play\" to begin. Same as original.");
      info.setFont(new Font("Sans Serif", Font.PLAIN, 15));
      add(info);

      JButton button = new JButton("Play");
      button.addActionListener(al);
      add(button);
   }
}