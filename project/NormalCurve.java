import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import javax.swing.JFrame;

/**
 *  Draws a normal curve, and can graph a vertical line at a certain value
 *
 *  @author  Sinclair Chen
 *  @version May 17, 2016
 *  @author  Period: 2
 *  @author  Assignment: Statistics_Final
 *
 *  @author  Sources: None
 */
public class NormalCurve extends Curve
{
    //JPanel id requirement. YYYYMMDD
    private static final long serialVersionUID = 20160515L;
    private boolean drawLine = false;
    private double val;

    /**
     * Make a normal curve with a certain mean and standard deviation
     * 
     * @param mean average
     * @param stdDev standard deviation
     */
    public NormalCurve(double mean, double stdDev)
    {
        super(x -> Fun.normalPDF((x - mean) / stdDev));
        
        initX("Values", mean -stdDev*4, stdDev*8, stdDev);
        initY("Normal Pdf (x, "+mean+", "+ stdDev +")", 0, 1/Fun.ROOT2PI + 0.1, 0.1);
    }
    
    /**
     * Make a normal curve with a certain mean and standard deviation,
     * That also has the vertical line x = val
     * 
     * @param mean average
     * @param stdDev standard deviation
     * @param val some value
     */
    public NormalCurve(double mean, double stdDev, double val)
    {
        this(mean, stdDev);
        drawLine = true;
        this.val = val;
    }

    /** (non-Javadoc)
     * 
     * overRide curve's drawInside so you can draw a vertical line
     * 
     * @see Curve#drawInside(java.awt.Graphics2D)
     */
    public void drawInside(Graphics2D g)
    {
        super.drawInside(g);
        if (drawLine)
        {
            g.drawLine( xPix(val), 0, xPix(val), grHeight() );
            AffineTransform orig = g.getTransform();
            g.scale(1, -1);
            g.drawString( val + "", xPix(val), -grHeight() );
            
            g.setTransform( orig );
        }
        
    }

    
    /**
     * Just a tester
     * 
     * @param args not used
     */
    public static void main(String[] args)
    {
        JFrame w = new JFrame("Normal Curve");
        w.setBounds(100, 100, 640, 480);
        NormalCurve n = new NormalCurve(7, 2, 6);
        w.getContentPane().add(n);
        w.setVisible(true);
    }

}
