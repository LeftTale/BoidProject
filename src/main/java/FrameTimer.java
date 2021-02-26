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
                b.centers();

            }
//            for(Boid b : activeBoids)
//            {
//                b.MoveBoidVertical();
//                b.MoveBoidHorizontal();
//            }
            boidView.repaint();

            try
            {
                sleep(5);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }
}