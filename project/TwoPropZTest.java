import java.lang.reflect.Field;

/**
 *  This class contains methods necessary to perform a two proportion z test.
 *  
 *  @author  Durga Ganesh
 *  @version May 12, 2016
 *  @author  Period: 3
 *  @author  Assignment: Statistics_Final
 *
 *  @author  Sources: Power Team
 */
public class TwoPropZTest implements InferenceTest
{
    private int success1;
    private int success2;
    private int failure1;
    private int failure2;
    //don't have variable for actual difference as assume it's zero
       
    /**
     * no params constructor
     */
    public TwoPropZTest()
    {
        //empty
    }
    
    /**
     * other constructor
     * 
     * @param mySuccess1 of type int
     * @param myFailure1 of type int
     * @param mySuccess2 of type int
     * @param myFailure2 of type int
     */
    public TwoPropZTest(int mySuccess1, int myFailure1, int mySuccess2, int myFailure2)
    {
        success1 = mySuccess1;
        failure1 = myFailure1;
        success2 = mySuccess2;
        failure2 = myFailure2;
    }
    
    /**
     * accessor method
     * @return success1 of type int
     */
    public int getSuccess1()
    {
        return success1;
    }
    
    /**
     * accessor method
     * @return failure1 of type int
     */
    public int getFailure1()
    {
        return failure1;
    }    
    
    /**
     * accessor method
     * @return success2 of type int
     */
    public int getSuccess2()
    {
        return success2;
    }
    
    /**
     * accessor method
     * @return failure2 of type int
     */
    public int getFailure2()
    {
        return failure2;
    }
    
    /**
     * This method is the definition for the previously declared method in the 
     * interface InferenecTest.
     * @return the standard error (pooled) of the 
     * two sample of type double
     */
    public double deviationOrError() //works
    {
        double q1;
        double q2;
        q1 = (pPooled()*qPooled())/(success1+failure1);
        q2 = (pPooled()*qPooled())/(success2+failure2);
        return Math.sqrt( q1 + q2 );
    }
    
    /**
     * calculates the size of the sample (n)
     * @return the total sample size as int
     */
    private int sampleSize()
    {
        return (success1 + success2 + failure1 + failure2);
    }
    
    /**
     * calculates the overall success of the sample
     * @return pooled success as double
     */
    private double pPooled() //works
    {
        return (success1+success2)/(double)sampleSize();
    }
    
    /**
     * calculates the overall failure of the sample
     * @return pooled failure as double
     */
    private double qPooled()
    {
        return 1-pPooled();
    }
    
    /**
     * This method is the definition for the previously declared method in the 
     * interface InferenecTest.
     * @return the z-score of the difference of the 
     * samples of type double
     */
    public double testStatistic()
    {

        double pHat1 = (double)success1/(success1+failure1);
        double pHat2 = (double)success2/(success2+failure2);
        return (pHat1 - pHat2)/deviationOrError(); //assume that the actual 
    }                                              //difference is zero
    
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
     }
    
    /**
     * The main method is used for testing purposes.
     * @param args of type String
     */
    public static void main (String args[])
    {
        TwoPropZTest sample = new TwoPropZTest(10, 10, 7, 13);
        //sample.toString(); 
        System.out.println(sample); 
        System.out.println("deviation " + sample.deviationOrError());
        System.out.println("z " + sample.testStatistic());
        System.out.println("p pooled " + sample.pPooled());
        double p = sample.calculatePValue();
        System.out.println("p " + p);
    }

}