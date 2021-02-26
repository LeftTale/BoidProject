import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class ScreenSpace extends JPanel
{
    /*
    The interface class is used to show graphical output to the user.
    It will contain a JFrame and will have Boids and objects painted onto it
     */

    JFrame frame = new JFrame();
    ArrayList<Boid> activeBoid;

    public ScreenSpace()
    {
       frame.setTitle("Boids");
       frame.setSize(new Dimension(1280,650));
       frame.setLocationRelativeTo(null);
       frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
       frame.addKeyListener(new KeyChecker());

    }

    void init()
    {
        this.setBackground(Color.BLACK);
        frame.add(this);
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

    public void setActiveBoid(ArrayList<Boid> activeBoid)
    {
        this.activeBoid = activeBoid;
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        for (Boid boid : activeBoid)
        {
            g.setColor(boid.getBoidColor());
            g.fillPolygon((Polygon) boid.getBoidShape());

        }
    }


    class KeyChecker implements KeyListener
    {

        @Override
        public void keyTyped(KeyEvent e)
        {
        }

        @Override
        public void keyPressed(KeyEvent e)
        {
            int keyCode = e.getKeyCode();
            switch( keyCode )
            {
                case KeyEvent.VK_LEFT:
                    for(Boid b : activeBoid)

                        break;
                case KeyEvent.VK_RIGHT:
                    for(Boid b : activeBoid)
                        b.addRotation();
                        break;
                case KeyEvent.VK_UP:
                    for(Boid b : activeBoid)
                        b.printcoords();
                        break;
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {

        }
    }
}