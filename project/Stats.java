import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import javax.swing.JOptionPane;


/**
 * Creates the list with a lot of statistical tools written into so that we can
 * categorize the list via numbers such as mean, median, and mode.
 *
 * @author Neha Kunjal
 * @version May 24, 2016
 * @author Period: 3
 * @author Assignment: Statistics_Final
 *
 * @author Sources: none
 */
public class Stats
{
    /**
     * name of the list
     */
    private String listName;

    /**
     * data in the original order of the list ( only used in case of the user
     * wants to see original data)
     */
    private ArrayList<Double> data;

    /**
     * data organized into sorted order, ascending
     */
    private ArrayList<Double> sortedData;

    /**
     * the sum of all values in data (used in mean
     */
    private double sum;

    /**
     * number used for mode
     */
    private int numberOfFrequency;

    /**
     * the number of items in the data
     */
    private int size;

    /**
     * the sum of the square of the difference of each number with it's mean.
     */
    private double varianceSum;


    /**
     * Empty constructor
     */
    public Stats()
    {
        // empty Constructor
    }


    /**
     * enters in data into arraylist data.
     * 
     * @param fName
     *            = name of text file with data in it
     * @throws FileNotFoundException
     *             it name is not valid
     */
    public Stats( String fName ) throws FileNotFoundException
    {
        listName = fName;
        data = new ArrayList<Double>();
        size = 0;
        numberOfFrequency = 1;
        fileToData( fName );
    }


    /**
     * Gets mean of the data
     * 
     * @return numerical representation of mean
     */
    public double getMean()
    {
        return sum / size;
    }


    /**
     * Gets mode of the data
     * 
     * @return numerical representation of modes in the data
     */
    public ArrayList<Double> getMode()
    {
        return calcMode();
    }


    /**
     * Gets range of the data
     * 
     * @return numerical representation of range in the data
     */
    public double getRange()
    {
        if ( getSize() == 0 )
        {
            return 0;
        }
        return getMax() - getMin();
    }


    /**
     * Gets size of the data
     * 
     * @return numerical representation of number of items in the data
     */
    public int getSize()
    {
        return size;
    }


    /**
     * Gets smallest value of the data
     * 
     * @return numerical representation of smallest value in the data
     */
    public double getMin()
    {
        return sortedData.get( 0 );
    }


    /**
     * Gets maximum value of the data
     * 
     * @return numerical representation of maximum value in the data
     */
    public double getMax()
    {
        return sortedData.get( size - 1 );
    }


    /**
     * Gets variance of the data
     * 
     * @return numerical representation of variance
     */
    public double getVariance()
    {
        if ( getSize() == 0 )
        {
            return 0;
        }
        findVarianceSum();
        return varianceSum / size;
    }


    /**
     * Gets the name of the txt file that the Stats object represents
     * 
     * @return string representation of the name of stats object
     */
    public String getListName()
    {
        return listName;
    }


    /**
     * Gets standard deviation of the data
     * 
     * @return numerical representation of standard deviation
     */
    public double getStandardDeviation()
    {
        return Math.sqrt( getVariance() );
    }


    // find the data value between start and finish in the sorted list
    // precondition: start < end
    /**
     * Calculates the median/mean between two numbers
     * 
     * @param start
     *            = the first number
     * @param end
     *            = the second number
     * @return the number calculated
     */
    private double medBet( int start, int end )
    {
        // number of datapoints from start to end inclusive
        int r = end - start + 1;
        if ( r % 2 == 1 ) // if it's odd
            return sortedData.get( r / 2 + start );
        else
            return ( sortedData.get( r / 2 + start - 1 ) + sortedData.get( r / 2 + start ) ) * 0.5;
    }


    /**
     * Gets median of the data
     * 
     * @return numerical representation of median
     */
    public double getMedian()
    {
        if ( size == 0 )
        {
            return 0;
        }
        else if ( size == 1 )
        {
            return data.get( 0 );
        }
        else
        {
            return medBet( 0, size - 1 );
        }
    }


    /**
     * Gets the Q1 of data
     * 
     * @return numerical representation of Q1
     */
    public double getQ1()
    {
        if ( size <= 1 )
        {
            return 0;
        }
        return medBet( 0, ( size - 2 ) / 2 );
    }


    /**
     * Gets the Q3 of data
     * 
     * @return numerical representation of Q3
     */
    public double getQ3()
    {
        if ( size == 0 )
        {
            return 0;
        }
        else if ( size == 1 )
        {
            return sortedData.get( 0 );
        }
        return medBet( ( size + 1 ) / 2, size - 1 );
    }


    /**
     * Gets the Q3 - Q1
     * 
     * @return numerical representation of IQR
     */
    public double getIqr()
    {
        if ( size <= 0 )
        {
            return 0;
        }
        return getQ3() - getQ1();
    }


    // binary search

    /**
     * Finds the index of a particular value
     * 
     * @param dataVal
     *            = value trying to find the index of
     * @return index of value of -1 if not found
     */
    public int indexOf( double dataVal )
    {
        int start = -1;
        int end = size;
        while ( end - start > 1 )
        {
            int mid = ( end + start ) / 2;
            double midVal = sortedData.get( mid );
            if ( dataVal == midVal )
                return mid;
            else if ( midVal > dataVal )
                end = mid;
            else
                start = mid;
        }
        return start;
    }


    /**
     * Gets the original data
     * 
     * @return original data
     */
    public ArrayList<Double> getData()
    {
        return data;
    }


    /**
     * Gets the sorted data
     * 
     * @return sorted data
     */
    public ArrayList<Double> getSortedData()
    {
        return sortedData;
    }


    /**
     * Helper method that calculates mode
     * 
     * @return = the modes in the data
     */
    private ArrayList<Double> calcMode()
    {
        HashMap<Double, Integer> values = new HashMap<Double, Integer>();
        Iterator<Double> iter = sortedData.iterator();
        ArrayList<Double> result = new ArrayList<Double>();
        while ( iter.hasNext() )
        {
            double next = iter.next();
            if ( values.get( next ) == null )
            {
                values.put( next, 1 );
                if ( numberOfFrequency == 1 )
                {
                    result.add( next );
                }
            }
            else
            {
                int oldValue = values.get( next );
                values.put( next, oldValue + 1 );
                if ( numberOfFrequency < oldValue + 1 )
                {
                    numberOfFrequency = oldValue + 1;
                    result = new ArrayList<Double>();
                    result.add( next );
                }
                else if ( numberOfFrequency == oldValue + 1 )
                {
                    result.add( next );
                }

            }

        }
        return result;
    }


    /**
     * Uses a sort to sort out the list
     */
    public void sortList()
    {
        sortedData = new ArrayList<Double>();
        for ( int i = 0; i < size; i++ )
        {
            sortedData.add( data.get( i ) );
        }
        for ( int n = 1; n < size; n++ )
        {
            double aTemp = sortedData.get( n );
            int i = n;
            while ( i > 0 && aTemp < sortedData.get( i - 1 ) )
            {
                sortedData.set( i, sortedData.get( i - 1 ) );
                i--;
            }
            sortedData.set( i, aTemp );
        }
    }


    /**
     * Calculates the sum of the variances
     */
    private void findVarianceSum()
    {
        for ( int i = 0; i < size; i++ )
        {
            double temp = sortedData.get( i ) - getMean();
            varianceSum += temp * temp;
        }
    }


    /**
     * Converts file into data
     * 
     * @param fileName
     *            = txt file that is being inserted in
     * @throws FileNotFoundException
     *             if file does not exist
     */
    private void fileToData( String fileName ) throws FileNotFoundException
    {
        Scanner in = new Scanner( new File( fileName ) );
        while ( in.hasNextDouble() )
        {
            double value = in.nextDouble();
            data.add( value );
            size++;
            sum += value;
        }
        sortList();
        in.close();

    }
}
