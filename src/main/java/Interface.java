import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class Interface extends JFrame
{
    /*
    The interface class is used to show graphical output to the user.
    It will contain a JFrame and will have Boids and objects painted onto it
     */

    Canvas c;
    ArrayList<Boid> boidList = new ArrayList<>();

    JFrame frame;

    public Interface(String title)
    {
        frame = new JFrame();
        frame.setTitle(title);
        frame.setSize(new Dimension(1100,650));
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);

    }

    public void init()
    {
        BoidCanvas c = new BoidCanvas();
        c.setBackground(Color.BLACK);
        frame.add(c);
        frame.setVisible(true);
    }

    public int getScreenSizeX()
    {
        return frame.getWidth();
    }

    public int getScreenSizeY()
    {
        return frame.getHeight();
    }


    class BoidCanvas extends JPanel implements MouseListener
    {


        BoidCanvas()
        {
            addMouseListener(this);
        }

        public void paintComponent(Graphics g)
        {
         super.paintComponent(g);

         for(Boid b : boidList)
         {
             g.setColor(Color.WHITE);
             //g.drawPolygon((Polygon) b.getBoidShape());
             g.fillPolygon((Polygon) b.getBoidShape());
         }
        }

        public void MoveForward()
        {
            //For every boid it calls the method which changes the boids position and repains
            for (Boid b: boidList)
            {
                b.MoveBoidForward();
                repaint();
            }
        }


        @Override
        public void mouseClicked(MouseEvent e)
        {
            Boid b = new Boid(getScreenSizeX(),getScreenSizeY());
            boidList.add(b);
            System.out.println("Click");
            repaint();
        }






        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e)
        {
            //TODO Temporary
            MoveForward();
        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }
}
