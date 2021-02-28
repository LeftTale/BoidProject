import java.util.ArrayList;

public class FrameTimer extends Thread
{
    ScreenSpace boidView;
    long startTime;
    ArrayList<Boid> activeBoids;
    public FrameTimer(ScreenSpace s, ArrayList<Boid> activeBoids)
    {
        this.boidView = s;
        this.activeBoids = activeBoids;
        run();
    }

    @Override
    public void run()
    {
        while(true)
        {
            startTime = System.currentTimeMillis();

            for(Boid b : activeBoids)
            {
                b.BoundaryCheck();
            }
            boidView.repaint();

            try
            {
                sleep(20);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }
}
