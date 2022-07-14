import javax.swing.*;
import java.awt.*;
public class Letter extends JLabel
{
   public Letter()
   {
      setOpaque(true);
      setBackground(Color.WHITE);
      setForeground(Color.BLACK);
      setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 2));

      setHorizontalAlignment(SwingConstants.CENTER);
      setFont(new Font("Sans Serif", Font.PLAIN, 30));
   }

   public void setInFocus()
   {
      setBorder(BorderFactory.createLineBorder(Color.GRAY, 4));
   }

   public void setOutOfFocus()
   {
      setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 2));
   }
}