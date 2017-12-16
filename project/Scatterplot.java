import java.awt.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javax.swing.JFrame;

/**
 *  Make a scatterplot
 *
 *  @author  Sinclair Chen
 *  @version May 12, 2016
 *  @author  Period: 3
 *  @author  Assignment: Statistics_Final
 *
 *  @author  Sources: None
 */
public class Scatterplot extends Graph
{
    //JPanel stuff YYYYMMDD
    private static final long serialVersionUID = 20160512;

    //lists for x and y, respectively
    private Stats s1;
    private Stats s2;
    
    private FontMetrics f;

    /**
     * Construct a scatterplot
     */
    public Scatterplot( Stats list1, Stats list2)
    {
        s1 = list1;
        s2 = list2;
        
        initX(s1.getListName(), s1.getMin() - 1, s1.getRange() + 1, 1);
        initY(s2.getListName(), s2.getMin() - 1, s2.getRange() + 1, 1);
    }
    
    /** (non-Javadoc)
     * @param g graphics
     * 
     * draw the scatterplot
     * 
     * @see Graph#drawInside(java.awt.Graphics2D)
     */
    @Override
    public void drawInside( Graphics2D g )
    {
        int min = Math.min( s1.getSize(), s2.getSize() );
        
        if (min == 0)
        {
            g.drawString( "Error: empty list", grWidth() / 2, grHeight() / 2 );
        }
        
        ArrayList<Double> l1 = s1.getData();
        ArrayList<Double> l2 = s2.getData();
        
        f = g.getFontMetrics();
        String s = ":D";
        int sWidth = f.stringWidth(s);
        int sHeight = f.stringWidth("m");
        
        for (int i = 0; i < min; i++)
        {
            
            g.drawString( s, xPix(l1.get(i)) - sWidth/2, yPix(l2.get(i)) + sHeight/2 );
        }
        
    }


    /**
     * Just for testing
     * @param args not used
     * @throws FileNotFoundException 
     */
    public static void main( String[] args ) throws FileNotFoundException
    {
        Scatterplot s = new Scatterplot(new Stats("List1.txt"), new Stats("List2.txt"));
        JFrame w = new JFrame("Test Scatterplot");
        w.setBounds(100, 100, 1000, 480);
        w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        s.setBackground( Color.pink );
        w.getContentPane().add( s );
        w.setResizable( true );
        w.setVisible( true );

    }

}
