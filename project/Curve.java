import java.awt.*;
import java.util.function.*;

import javax.swing.*;

/**
 *  Curves are drawings of math functions, (e.g. y = x^2 is a parabola)
 *  This class makes a curve given a function that eats up a double and returns
 *  a double. It is primarily used to graph normal curves.
 *
 *  @author  Sinclair Chen
 *  @version May 15, 2016
 *  @author  Period: 3
 *  @author  Assignment: Statistics_Final
 *
 *  @author  Sources: StackOverflow
 */
public class Curve extends Graph
{
    private static final long serialVersionUID = 20160515L;
    
    
    private Function<Double, Double> func;
    
    /**
     * Make a curve
     * 
     * @param function some math function
     */
    public Curve(Function<Double, Double> function)
    {
        func = function;
        //initializing x and Y left as an exercise to the user
    }
    
    /**
     * This method draws the part of the curve that is not the axes.
     * @param Graphics2D g
     */
    public void drawInside(Graphics2D g)
    {
        int xP = 1;
        int yP0 = yPix(func.apply(invXPix(xP - 1)));
        while (xP < grWidth())
        {
            int yP1 = yPix(func.apply(invXPix(xP)));
            g.drawLine(xP - 1, yP0, xP, yP1);
            xP++;
            yP0 = yP1;
        }  
    }
    

    /**
     * Just a tester
     * @param args unused
     */
    public static void main(String[] args)
    {
        JFrame w = new JFrame("Test");
        w.setBounds(100, 100, 640, 480);
//        double df = 7;
        Curve c = new Curve(x -> Fun.normalCDF(x) * 100);
//        Curve c2 = new Curve(x -> x);
        c.initX("x", -3, 6, 1);
        c.initY("y", 0, 100, 10);
        w.getContentPane().add(c);
//        w.getContentPane().add(c2);
        w.setVisible(true);
    }

}
