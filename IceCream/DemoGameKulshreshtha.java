//Kulshreshtha, Mihir  7/11/2022

import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;
import java.io.*;
import javax.imageio.*;
import javax.sound.sampled.*;

public class DemoGameKulshreshtha
{
   public static void main( String[] args )
   {
      JFrame frame = new JFrame("Demo Game");
      frame.setSize(450, 520);
      frame.setLocation(200, 100);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setResizable(false);
      frame.setContentPane( new DemoPanel() );
      frame.setVisible(true);
   }
   
   public static class DemoPanel extends JPanel
   {
      private BufferedImage image;
      private Graphics buffer;
      private Timer t;
      private Keys key;
      private int bgx, bgy, bgdy;
      private boolean moveRight, moveLeft, moveUp;
      private Character cream;
      private JPanel panel;
      private JLabel endLabel;
      private Clip clip;
      public DemoPanel()
      {
         //setLayout( new BorderLayout() );
         //add image background
         try
         {
            image = ImageIO.read(new File("pixel_art.png"));
         }
         catch(Exception e)
         {
            System.err.println("File not found. Sorry.");
            System.exit(1);
         }
         buffer = image.getGraphics();
         //initialize variables to move background
         bgx = 0;
         bgy = -78;
         
         moveRight = false;
         moveLeft = false;
         moveUp = false;
         
         cream = new Character();
         
         //KeyListener
         key = new Keys();
         addKeyListener(key);
         setFocusable(true);
         
         //Timer
         t = new Timer(1, new TimerListener() );
         t.start();
         
         playAudio("ice-cream-background.wav");
         
         //Panel for end of game
//          panel = new JPanel();
//          panel.setLayout(null);
//          panel.setSize( 200, 200 );
//          panel.setLocation( 125, 75 );
//          panel.setBackground(Color.BLACK);
         
//          endLabel = new JLabel("Journey complete!");
//          endLabel.setFont( new Font("MONOSPACED", Font.BOLD, 17));
//          endLabel.setForeground( Color.YELLOW );
//          endLabel.setBounds( 5, 30, 170, 170);
//          panel.add(endLabel);
      }
      
      public void paintComponent( Graphics g )
      {
         g.drawImage(image, bgx, bgy, null);
         
         cream.draw(g);
         
      }
      
      private class TimerListener implements ActionListener
      {
         public void actionPerformed( ActionEvent e )
         {
            if(moveRight)
            {
               bgx-=4;
               cream.setMyImage("icecream2.png");
            }
            if(!moveRight)
               cream.setMyImage("icecream1.png");
            if(moveLeft)
               bgx+=2;
            
            if(moveUp)
            {
               if( bgy <= -78 )
                  bgdy = 20;
               else
                  bgdy--;
               
               bgy += bgdy;
            }
            if(!moveUp && bgy > -78 )
            {
               bgdy--;
               bgy += bgdy;
            }   
            
            if( bgx < -3700 )
               gameEnd();
            
            repaint();
         }
      }
      
      private class Keys extends KeyAdapter
      {
         public void keyPressed(KeyEvent e)
         {
            if( e.getKeyCode() == KeyEvent.VK_D )
               moveRight = true;
            if( e.getKeyCode() == KeyEvent.VK_A )
               moveLeft = true;
            if( e.getKeyCode() == KeyEvent.VK_W )
               moveUp = true;
         }
         public void keyReleased(KeyEvent e)
         {
            if( e.getKeyCode() == KeyEvent.VK_D )
               moveRight = false;
            if( e.getKeyCode() == KeyEvent.VK_A )
               moveLeft = false;
            if( e.getKeyCode() == KeyEvent.VK_W )
            {
               moveUp = false;
            }
         
         }
      }
      public void gameEnd()
      {
         moveRight = false;
         moveUp = false;
         removeKeyListener( key );     
      
      }
      
      public void playAudio( String filename )
      {
         try
         {
            File file = new File( filename );
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(file);
            
            DataLine.Info lineInfo = new DataLine.Info(Clip.class, inputStream.getFormat() );
            clip = (Clip)AudioSystem.getLine(lineInfo);
            clip.open(inputStream);
            
            clip.start();
            clip.loop(Clip.LOOP_CONTINUOUSLY);
         }
         catch( Exception e )
         {
            System.out.println( e.getMessage() );
            System.exit(1);
         }
      }
   }
   
   public static class Character
   {
      BufferedImage myImage;
      int width;
      int height;
      public Character()
      {
         try
         {
            myImage = ImageIO.read(new File("icecream1.png"));
         }
         catch(Exception e)
         {
            System.err.println("Ice Cream not found. Sorry.");
            System.exit(1);
         }         
         
         width = 21;
         height = 30;
      }
      public void draw( Graphics g )
      {
         g.drawImage(myImage, 150, 325, 63, 90, null );
      }
      public void setMyImage(String filename)
      {
         try
         {
            myImage = ImageIO.read(new File( filename ));
         }
         catch(Exception e)
         {
            System.err.println("Ice Cream not found. Sorry.");
            System.exit(1);
         }    
      }
   }
}