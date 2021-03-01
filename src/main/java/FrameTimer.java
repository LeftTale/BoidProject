import java.util.ArrayList;

public class FrameTimer extends Thread
{
    /*
    This class runs important methods every 200 milliseconds
    The methods do things like move the boids and also runs the boids internal logic
     */

    BoidGraphics boidGraphics;
    ArrayList<Boid> activeBoids;
    public FrameTimer(BoidGraphics s, ArrayList<Boid> activeBoids)
    {
        this.boidGraphics = s;
        this.activeBoids = activeBoids;
        start();
    }

    @SuppressWarnings("InfiniteLoopStatement")
    @Override
    public void run()
    {
        while(true)
        {
            //Runs methods for every boid that exists
            for(Boid b : activeBoids)
            {
                b.BoundaryCheck();
            }
            boidGraphics.repaint();

            try
            {
                sleep(10);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }
}
