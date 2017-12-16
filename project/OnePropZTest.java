import java.lang.reflect.Field;

/**
 *  
 *  This class contains methods necessary to perform a one proportion z test.
 *  
 *  @author  Durga Ganesh
 *  @version May 12, 2016
 *  @author  Period: 3
 *  @author  Assignment: Statistics_Final
 *
 *  @author  Sources: Power Team
 */
public class OnePropZTest implements InferenceTest
{
    private int success;
    private int failure;
    private double populationSuccess;
    
    /**
     * no parameters constructor
     */
    public OnePropZTest()
    {
        //empty
    }
    
    /**
     * other constructor
     * 
     * @param mySuccess of type int
     * @param myFailure of type int
     * @param myPopulationSuccess of type double
     */
    public OnePropZTest(int mySuccess, int myFailure, double myPopulationSuccess)
    {
        success = mySuccess;
        failure = myFailure;
        populationSuccess = myPopulationSuccess;
        
    }
       
    /**
     * accessor method
     * @return success of type int
     */
    public int getSuccess()
    {
        return success;
    }
    
    /**
     * accessor method
     * @return failure of type int
     */
    public int getFailure()
    {
        return failure;
    }
    
    /**
     * accessor method
     * @return populationSucccess of type double
     */
    public double getPSuccess()
    {
        return populationSuccess;
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
     * This method is the definition for the previously declared method in the 
     * interface InferenecTest.
     * @return the standard deviation of the proportion of success of the 
     * sample of type double
     */
    public double deviationOrError()
    {
        return Math.sqrt( (populationSuccess*
            (1-populationSuccess))/(success+failure) );
    }

    /**
     * This method is the definition for the previously declared method in the 
     * interface InferenecTest.
     * @return the z-score of the 
     * sample of type double
     */
    public double testStatistic()
    {
        return ((pHat()-populationSuccess)/deviationOrError());
    }
    
    /**
     * This method calculates the proportion of success of the sample.
     * @return result as a double
     */
    protected double pHat()
    {
        return (double)success/(success+failure);
    }

    /**
     * This method calculates the proportion of failure of the sample.
     * @return result as a double
     */
    private double qHat()
    {
        return (double)failure/(success+failure);
    }
    
    /**
     * This method is the definition for the previously declared method in the 
     * interface InferenecTest.
     * @return the calculated p-value
     */
    public double calculatePValue()
    {
        double z = testStatistic();
        double p = 0.0;
        
        if (z < 0)
        {
            p = Fun.normalCDF( z );
        }
        
        if (z >= 0)
        {
            p = 1 - Fun.normalCDF( z );
        }   
        return p*2;
    }
    
    /**
     * The main method is used for testing purposes.
     * @param args of type String
     */
    public static void main (String args[])
    {
        InferenceTest sample = new OnePropZTest(10, 10, .4);
        //sample.toString(); 
        System.out.println(sample);
        double p = sample.calculatePValue();
        System.out.println("p " + p);
    }
}