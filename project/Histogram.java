import java.util.*;
import java.awt.*;
import java.io.FileNotFoundException;

import javax.swing.*;

/**
 *  Makes a Histogram. All the important stuff will be in the drawInside() method.
 *
 *  @author  Sinclair Chen
 *  @version May 5, 2016
 *  @author  Period: 4
 *  @author  Assignment: Statistics_Final Histogram
 *
 *  @author  Sources: None so far
 */
public class Histogram extends Graph
{
    
    /**
     * JPanel wants version numbers apparently. This one = YYYYMMDD
     */
    private static final long serialVersionUID = 20160508L;
    
    private Stats data; //should be sorted list, min to max

    private double binWidth;
    private double start;
    
    // heights of the bars
    private int[] heights;
    
    /**
     * Create a Histogram Object.
     * 
     * @param name Name of list
     * @param list Sorted list of all the data values
     */
    public Histogram( Stats list )
    {
        super();
        this.data = list;
        binWidth = defaultBinWidth();
        start = defaultStartPos();
        calcHeights();
        
        //find largest height of bar
        int highestY = 0;
        for (int h : heights)
        {
            if (h > highestY)
                highestY = h;
        }
        
        String xName = list.getListName();
        //cut off the '.txt'
        xName = xName.substring( 0, xName.indexOf('.') );
        //xRange = numBins() * binWidth, 
        initX(xName, start, numBins() * binWidth, binWidth);
        initY("Frequency", 0, highestY, 1);
        
    }
    
    //return number of bins
    private int numBins() { return (int)( data.getRange() / binWidth ) +  2; }
    
    //defaut bin width based on default number of bins = sqrt(n)
    private double defaultBinWidth()
    {   
        int defNumBins = (int)Math.sqrt( data.getSize() );
        
        double width = data.getRange() / defNumBins;
        
        width = Fun.oldRound( width );
//        System.out.println("width: " + width);
        return width;
    }
    
    //default start position
    private double defaultStartPos()
    {
        double start = Fun.oldRound( data.getMin() ) - binWidth;
//        System.out.println("Start: " + start);
        return start;
    }
    
    // find the height of each bar, 
    // put into heights[] such that heights[k] == the hieght of the kth bar
    private void calcHeights()
    {
        heights = new int[numBins()];
        int binNum = 0;
        double cutoff = start + binWidth;
        ArrayList<Double> arr = data.getSortedData();
        for (double d : arr)
        {
            while (d > cutoff)
            {
                cutoff += binWidth;
                binNum++;
            }
            if (binNum >= numBins())
            {
                System.out.print("Not enough bins");
                break;
            }
            heights[binNum]++;
        }
    }
    
    /**
     * Display a histogram of <code>data</code> using <code>binWidth</code>
     * The x axis should start with <code>start</code>
     * and be labeled with <code>listName</code>.
     * 
     * @param g graphics (drawing board object)
     */
    public void drawInside(Graphics2D g)
    {
        g.setColor( Color.black );

//        double scaledBinWidth = xPix(binWidth);
        //draw bars
        for (int i = 0; i < numBins(); i++)
        {
            double x = i * binWidth;
            int yP = yPixScale(heights[i]);
            g.drawRect( xPixScale(x), 0, xPixScale(x + binWidth) - xPixScale(x), yP);
        }
        
    }
    
    /** set the bin width */
    public void setBinWidth( double newWidth ) { binWidth = newWidth; }
    /** set the start */
    public void setStart( double newStart ) { start = newStart; }
    
    /** get the bin width */
    public double getBinWidth() { return binWidth; }
    /** get the start */
    public double getStart() { return start; }
    
    public static void main(String[] args) throws FileNotFoundException
    {
        Stats s = new Stats("List1.txt");
        Histogram h = new Histogram(s);
        JFrame w = new JFrame("Test Histogram");
        w.setBounds(100, 100, 640, 480);
        w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        h.setBackground( new Color(200, 80, 255));
        w.getContentPane().add( h );
        w.setResizable( true );
        w.setVisible( true );
    }

}
