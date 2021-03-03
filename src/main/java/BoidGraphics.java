import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class BoidGraphics extends JPanel
{
    /*
    The interface class is used to show graphical output to the user.
    It will contain a JFrame and will have Boids and objects painted onto it
     */

    final JFrame frame = new JFrame();
    ArrayList<Boid> activeBoid;
    Rectangle2D[] boundaryBox = new Rectangle2D[4];
    Font name = new Font("Hello",1,20);

    public BoidGraphics()
    {
       frame.setTitle("Boids");
       frame.setSize(new Dimension(1920,1000));
       frame.setLocationRelativeTo(null);
       frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
       boundaryBox[0] = new Rectangle(0,0,getScreenSizeX(),20);
       boundaryBox[1] = new Rectangle(0,0,20,getScreenSizeY());
       boundaryBox[2] = new Rectangle(0,getScreenSizeY()-55,getScreenSizeX(),20);
       boundaryBox[3] = new Rectangle(getScreenSizeX()-35,0,20,getScreenSizeY());
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

        g2.setColor(Color.WHITE);
        g2.fill(boundaryBox[0]);
        g2.fill(boundaryBox[1]);
        g2.fill(boundaryBox[2]);
        g2.fill(boundaryBox[3]);

        for (Boid boid : activeBoid)
        {
            g.setColor(boid.getBoidColor());
            g2.draw(boid.getBoidShape());
            g2.fill(boid.getBoidShape());
            g2.draw(boid.getLineOfSight());

            g2.setFont(name);
            g2.setColor(Color.GREEN);
            g2.drawString(boid.getBoidName(),(int)boid.getCenterX()-20,(int)boid.getCenterY() - 25);

            if(CheckWallCollision(boid))
                boid.BoidReroute();


        }
    }

    boolean CheckWallCollision(Boid boid)
    {
        if(boid.getLineOfSight().intersects(boundaryBox[0])||
                boid.getLineOfSight().intersects(boundaryBox[1])||
                boid.getLineOfSight().intersects(boundaryBox[2])||
                boid.getLineOfSight().intersects(boundaryBox[3]))
        {
            return true;
        }
        return false;
    }
    void CheckCollision(Boid boid)
    {
        if(boid.getLineOfSight().intersects(boundaryBox[0])||
                    boid.getLineOfSight().intersects(boundaryBox[1])||
                    boid.getLineOfSight().intersects(boundaryBox[2])||
                    boid.getLineOfSight().intersects(boundaryBox[3]))
        {
            boid.BoidReroute();
            CheckCollision(boid);
        }
        else
        {
            boid.setPreviousState(boid.moveState);
            boid.setMoveState(Direction.STRAIGHT);

        }
    }


}
