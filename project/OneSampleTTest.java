import java.io.FileNotFoundException;
import java.lang.reflect.Field;

/**
 *  This class contains methods necessary to perform a one sample t test. 
 *  
 * 
 *  @author  Durga Ganesh
 *  @version May 12, 2016
 *  @author  Period: 3
 *  @au
 *  thor  Assignment: Statistics_Final
 *
 *  @author  Sources: Power Team
 */
public class OneSampleTTest implements InferenceTest
{
    private double populationMean;
    private double sampleMean;
    private double sampleSD;
    private int sampleSize;
    
    /**
     * no params constructor 
     */
    public OneSampleTTest()
    {
        //empty
    }
    
    /**
     * other constructor
     * 
     * @param list1 of type Stats
     * @param myPopulationMean of type double
     */
    public OneSampleTTest(Stats list1, double myPopulationMean)
    {
        sampleMean = list1.getMean();
        sampleSD = Math.sqrt( list1.getVariance()*list1.getSize()/(list1.getSize()-1));
        sampleSize = list1.getSize();
        populationMean = myPopulationMean;
    }
    
    /**
     * accessor method
     * @return populationMean of type double
     */
    public double getPopulationMean()
    {
        return populationMean;
    }

    /**
     * accessor method
     * @return sampleMean of type double
     */
    public double getSampleMean()
    {
        return sampleMean;
    }
    
    /**
     * accessor method
     * @return sampleSD of type double
     */
    public double getSampleSD()
    {
        return sampleSD;
    }
    
    /**
     * accessor method
     * @return sampleSize of type int
     */
    public int getSampleSize()
    {
        return sampleSize;
    }
    
    /**
     * This method is the definition for the previously declared method in the 
     * interface InferenecTest.
     * @return the standard error of the mean of the 
     * sample of type double
     */
    public double deviationOrError()
    {
        return sampleSD/Math.sqrt( sampleSize );
    }
    
    /**
     * This method is the definition for the previously declared method in the 
     * interface InferenecTest.
     * @return the t-value of the 
     * sample of type double
     */
    public double testStatistic()
    {
        return (sampleMean-populationMean)/deviationOrError();
    }
    
    /**
     * This method is the definition for the previously declared method in the 
     * interface InferenecTest.
     * @return the calculated p-value
     */
    public double calculatePValue()
    {
        double t = testStatistic();
        double p = 0.0;
        
        if (t < 0)
        {
            p = Fun.tCDF( t, sampleSize );
        }
        
        if (t >= 0)
        {
            p = 1 - Fun.tCDF( t, sampleSize );
        }   
        return p*2;
    }  
    
    /**
     *This method prints the name and fields of the class.
     *@return result as a String
     */
    public String toString()
    {
        String str = this.getClass().getName() + "[";
        String separator = "";

        Field[] fields = this.getClass().getDeclaredFields();

        for ( Field field : fields )
        {
            try
            {
                str += separator + field.getType().getName() + " " + field.getName() + ":" + field.get( this );
            }
            catch ( IllegalAccessException ex )
            {
                System.out.println( ex );
            }

            separator = ", ";
        }

        return str + "]";
        
        //return ("int success: " + success + " int failure: " + failure + 
        //                " int populationSuccess: " + populationSuccess);
    }

    /**
     * The main method is used for testing purposes.
     * @param args of type String
     */
    public static void main (String args[]) throws FileNotFoundException
    {
        InferenceTest sample = new OneSampleTTest(new Stats("test.txt"), 0.5);
        //sample.toString(); 
        System.out.println(sample);
        double p = sample.calculatePValue();
        System.out.println("p " + p);
    }
}