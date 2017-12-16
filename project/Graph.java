import java.awt.*;
import java.awt.geom.AffineTransform;
import java.text.*;

//import java.util.ArrayList;
import javax.swing.JPanel;

/**
 *  Abstract class for graphs. Graph has methods to initialize the x and y axi.
 *  It graphs the axi for you, but the graphing-the-inside part must be defined
 *  by any classes which extend Graph.
 *
 *  @author  Sinclair
 *  @version May 10, 2016
 *  @author  Period: 3
 *  @author  Assignment: Statistics_Final
 *
 *  @author  Sources: None
 */
public abstract class Graph extends JPanel
{   
    private int leftSpace = 80;
    private int rightSpace = 30;
    private int upSpace = 30;
    private int downSpace = 50;
    
    //width and height of the graph
    private int width, height;
    
    //half width of dash for the axises (axi?)
    private int dWidth = 7;
    
    private String xName, yName;
    private FontMetrics fm;
    
    private double xStart, xRange, xScale;
    private double yStart, yRange, yScale;
    
//    private DecimalFormat form = new DecimalFormat("0.##E0");
    //sig figs for the numbers you scale the axi with
    private int scaleSigFig = 2;

    /**
     * JPanel wants version numbers apparently. This one = YYYYMMDD
     */
    private static final long serialVersionUID = 20160512L;
    
    /**
     * Construct a default graph labeled x and y
     */
    public Graph()
    {
        this( "x", "y" );
    }
    
    
    /**
     * Construct a graph with certain labels
     * 
     * @param xLabel name for x axis
     * @param yLabel name for y axis
     */
    public Graph( String xLabel, String yLabel )
    {
        //default x and y name, start, range, and scaling
        initX(xLabel, 0, 10, 1);
        initY(yLabel, 0, 10, 1);
    }
    
    /**
     * Initialize the x axis
     * 
     * @param label name
     * @param start leftmost value
     * @param range length of x axis
     * @param scale distance between tick marks
     */
    public void initX(String label, double start, double range, double scale)
    {
        xName = label;
        xStart = start;
        xRange = range;
        xScale = scale;
    }
    
    /**
     * Initialize the y axis
     * 
     * @param label name
     * @param start leftmost value
     * @param range length of y axis
     * @param scale distance between tick marks
     */
    public void initY(String label, double start, double range, double scale)
    {
        yName = label;
        yStart = start;
        yRange = range;
        yScale = scale; 
    }
    
    /** get the graphical width of the graph */
    public int grWidth() { return width; }
    /** get the graphical height of the graph */
    public int grHeight() { return height; }
    
    /**
     * how many pixels it takes to display an increase in x
     * @return some number of pixels
     */
    public int xPixScale( double x ) { return (int)(width / xRange * x); }
    
    /**
     * how many pixels it takes to display an increase in y
     * @return some number of pixels
     */
    public int yPixScale( double y ) { return (int)(height / yRange * y); }
    
    /**@param x a value, 
     * @return the pixel location */
    protected int xPix( double x ) { return xPixScale(x - xStart); }
    /**@param y a value
     * @return the pixel location */
    protected int yPix( double y ) { return yPixScale(y - yStart); }
    
    /**@param pix pixel location
     * @return x */
    protected double invXPix( double pix )
    { return xRange / width * pix + xStart; }
    /**@param pix pixel location
     * @return y */
    protected double invYPix( double pix )
    { return yRange / height * pix + yStart; }
    
    /**
     * @param g graphics
     * Draw the inside, the actual graphy part
     */
    public abstract void drawInside(Graphics2D g);
    
    /**(Non-Javadoc) 
     *  
     * Calculate the height/width of inside box, save as instance variable
     * Shift the graphics over so that origin is the bottom left corner of
     * the inside box. Draw x and y axis about the new origin
     * flip the y axis so that positive is up, then draw the inside.
     *  
     * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
     * 
     * @param g graphics (drawing board object)
     */
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g); // Call JPanel's paintComponent method
                                 // to paint the background
        
        g.setColor(Color.black); 
        
        width = getWidth() - leftSpace - rightSpace;
        height = getHeight() - upSpace - downSpace;
        
        Graphics2D g2 =  (Graphics2D)g;
        
        //make 0,0 bottom left corner
        g2.translate(leftSpace, upSpace + height);

        fm = g2.getFontMetrics();
        
        drawXaxis(g2);
        drawYaxis(g2);
        
        //flip y axis
        g2.scale( 1, -1 );
        drawInside(g2);
        
    }
    
    //display string, top middle at x, y
    private void drawStringX(String s, int x, int y, Graphics g)
    {
        int strW = fm.stringWidth( s );
        int strH = 10; //right?
        g.drawString( s, x - strW/2, y + strH);
    }
    
    //display string, left middle at x, y
    private void drawStringY(String s, int x, int y, Graphics g)
    {
        int strW = fm.stringWidth( s );
        int strH = 10; //right?
        g.drawString( s, x - strW, y + strH/2);
    }

    /**
     * draw the x axis
     * @param g graphics
     */
    public void drawXaxis(Graphics2D g)
    {   
        //draw x axis
        g.drawLine(0, 0, width, 0);
        
        //scale x axis
        for (double i = 0; i <= xRange; i += xScale)
        {
            int xPix = xPixScale(i);
            g.drawLine( xPix, -dWidth, xPix, dWidth );
            String numLabel = Fun.round(i + xStart, scaleSigFig) + "";
            drawStringX( numLabel, xPix, dWidth + 1, g);
        }
        
        //label x axis
        drawStringX(xName, width/2 - 5, dWidth + 12, g);
        
    }
    
    
    /**
     * draw the y axis
     * @param g graphics
     */
    public void drawYaxis(Graphics2D g)
    {   
        //draw y axis
        g.drawLine( 0, 0, 0, -height );
        
        //scale y axis
        for (double i = 0; i <= yRange; i += yScale)
        {
            int yP = -yPixScale(i);
            g.drawLine(-dWidth, yP, dWidth, yP);
            String numLabel = Fun.round( (i + yStart), scaleSigFig ) + "";
            drawStringY( numLabel, -dWidth - 1, yP, g );
        }
        
        //label y axis, and the label has to be sideways
        
        //orig = the way g is oriented right now
        AffineTransform orig = g.getTransform();
        
        //move g and display the label
        g.translate( -leftSpace/2 - 10, -height/2 );
        g.rotate(-Math.PI / 2); //-90 degrees
        drawStringX(yName, 0, 0, g);

        //move g back
        g.setTransform(orig);
    }
}
