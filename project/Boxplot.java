import java.awt.Color;
import java.awt.Graphics2D;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javax.swing.JFrame;


/**
 * Draw a boxplots
 *
 * @author Sinclair
 * @version May 12, 2016
 * @author Period: 3
 * @author Assignment: Statistics_Final
 *
 * @author Sources: 3
 */
public class Boxplot extends Graph
{
    private Stats list;

    private int i1, i2, i3, i4;


    /**
     * Make a boxplot
     */
    public Boxplot( Stats list )
    {
        this.list = list;
        if ( list.getSize() == 0 )
        {
            initX( list.getListName(), 0, 2, 1 );
        }
        else
        {
            initX( list.getListName(), list.getMin(), list.getRange(), 2 );
            initY( "", 0, 3, 1 );
            initCalculate();
        }

    }


    /**
     * (non-Javadoc) Override Graph's drawYaxis method so no y axis is drawn
     * 
     * @see Graph#drawYaxis(java.awt.Graphics2D)
     */
    public void drawYaxis( Graphics2D g ) { /* Do nothing */ }


    /**
     * (non-Javadoc)
     * 
     * Draw the boxplot
     * 
     * @param g graphics
     * @see Graph#drawInside(java.awt.Graphics2D)
     */
    public void drawInside( Graphics2D g )
    {
        // deal with edge cases of making a boxplot from a list of 0 or 1 items
        switch ( list.getSize() )
        {
            case 0:
                return;
            case 1:
                int x = xPix( list.getMin() );
                g.drawLine( x, yPix( 2 ), x, yPix( 1 ) );
                return;
        }

        // draw box
        g.drawRect( xPix( list.getQ1() ), yPix( 1 ), xPixScale( list.getIqr() ), yPix( 1 ) );
        int scaleMedian = xPix( list.getMedian() );

        // draw median line
        g.drawLine( scaleMedian, yPix( 1 ), scaleMedian, yPix( 2 ) );

        ArrayList<Double> data = list.getSortedData();

        // draw left outliers
        for ( int i = 0; i <= i2; i++ )
        {
            g.drawString( i < i1 ? "*" : "O", xPix( data.get( i ) ), yPix( 1.5 ) );
        }

        // draw left whisker
        int leftDashX = xPix( data.get( i2 + 1 ) );
        g.drawLine( leftDashX, yPix( 1 ), leftDashX, yPix( 2 ) );
        g.drawLine( leftDashX, yPix( 1.5 ), xPix( list.getQ1() ), yPix( 1.5 ) );

        // draw right whisker
        int rightDashX = xPix( data.get( i3 ) );
        g.drawLine( rightDashX, yPix( 1 ), rightDashX, yPix( 2 ) );
        g.drawLine( xPix( list.getQ3() ), yPix( 1.5 ), rightDashX, yPix( 1.5 ) );

        // draw right outliers
        for ( int i = i3 + 1; i < list.getSize(); i++ )
        {
            g.drawString( i < i4 ? "O" : "*", xPix( data.get( i ) ), yPix( 1.5 ) );
        }
    }


    // do a lot of math
    private void initCalculate()
    {
        double iqrScaled = 1.5 * list.getIqr();

        double bottomCutoff = list.getQ1() - iqrScaled;
        double bottomCutoff2 = bottomCutoff - iqrScaled;
        double topCutoff = list.getQ3() + iqrScaled;
        double topCutoff2 = topCutoff + iqrScaled;

        i1 = list.indexOf( bottomCutoff2 );
        i2 = list.indexOf( bottomCutoff );
        i3 = list.indexOf( topCutoff );
        i4 = list.indexOf( topCutoff2 );
    }


    /**
     * Just for testing
     * 
     * @param args
     *            not used
     */
    public static void main( String[] args ) throws FileNotFoundException
    {
        JFrame w = new JFrame( "Test" );
        w.setBounds( 100, 100, 640, 480 );
        w.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        Stats s = new Stats( "list1.txt" );
        // System.out.println(s.getQ1() + " " + s.getMedian() + " " + s.getQ3()
        // + " " + s.getIqr());
        Boxplot b = new Boxplot( s );
        b.setBackground( Color.white );
        w.getContentPane().add( b );
        w.setResizable( true );
        w.setVisible( true );
    }

}
