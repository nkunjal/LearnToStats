import java.lang.reflect.Field;

/**
 *  
 *  This class contains methods necessary to perform a two sample t test.
 *  
 *  @author  Durga Ganesh
 *  @version May 12, 2016
 *  @author  Period: 3
 *  @author  Assignment: Statistics_Final
 *
 *  @author  Sources: Power Team
 */
public class TwoSampleTTest implements InferenceTest
{
    //private double populationDiff; assuems is 0
    private double sampleMean1;
    private double sampleMean2;
    private double sampleSD1;
    private double sampleSD2;
    private int sampleSize1;
    private int sampleSize2;

    /**
     * no params constructor 
     */
    public TwoSampleTTest()
    {
       //empty
    }
    
    /**
     * other constructor
     * 
     * @param list1 of type Stats
     * @param list2 of type Stats
     */
    public TwoSampleTTest(Stats list1, Stats list2)
    {
        sampleMean1 = list1.getMean();
        sampleMean2 = list2.getMean();
        sampleSize1 = list1.getSize();
        sampleSize2 = list2.getSize();
        sampleSD1 = Math.sqrt( list1.getVariance()*list1.getSize()/(list1.getSize()-1));
        sampleSD2 = Math.sqrt( list2.getVariance()*list2.getSize()/(list2.getSize()-1));
        //populationDiff = myPopulationDiff;
    }

//    public double getPopulationDiff()
//    {
//        return populationDiff;
//    }
    
    /**
     * accessor method
     * @return sampleMean1 of type double
     */
    public double getSampleMean1()
    {
        return sampleMean1;
    }
    
    /**
     * accessor method
     * @return sampleSD1 of type double
     */
    public double getSampleSD1()
    {
        return sampleSD1;
    }
    
    /**
     * accessor method
     * @return sampleSize1 of type int
     */
    public int getSampleSize1()
    {
        return sampleSize1;
    }
    
    /**
     * accessor method
     * @return sampleMean2 of type double
     */
    public double getSampleMean2()
    {
        return sampleMean2;
    }
    
    /**
     * accessor method
     * @return sampleSD2 of type double
     */
    public double getSampleSD2()
    {
        return sampleSD2;
    }
    
    /**
     * accessor method
     * @return sampleSize2 of type int
     */
    public int getSampleSize2()
    {
        return sampleSize2;
    }
    
    /**
     * This method is the definition for the previously declared method in the 
     * interface InferenecTest.
     * @return the standard error of the difference of the means of the two 
     * samples of type double
     */
    public double deviationOrError()
    {
       double q1 = (sampleSD1*sampleSD1)/sampleSize1;
       double q2 = (sampleSD2*sampleSD2)/sampleSize2;
       return Math.sqrt( q1+q2 );
    }
    
    /**
     * This method is the definition for the previously declared method in the 
     * interface InferenecTest.
     * @return the t-value of the 
     * sample of type double
     */
    public double testStatistic()
    {
        return (sampleMean1-sampleMean2)/deviationOrError();
    }
    
    /**
     * Find degrees of freedom for using the formula described here:
     * 
     * http://apcentral.collegeboard.com/apc/public/repository/
     *          ap05_stats_allwood_fin4prod.pdf
     * 
     * @return degrees of freedom
     */
    private double df()
    {
        double n1 = sampleSize1;
        double n2 = sampleSize2;
        double s1 = sampleSD1;
        double s2 = sampleSD2;
        
        double vn1 = s1 * s1 / n1; //variance 1 / n1
        double vn2 = s2 * s2 / n2; //variance 2 / n2
        return (vn1 + vn2)*(vn1 + vn2) / (vn1*vn1/(n1 - 1) +  vn2*vn2/(n2 -1));
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
        double df = df();
        
        if (t < 0)
        {
            p = Fun.tCDF( t, df );
        }
        
        if (t >= 0)
        {
            p = 1 - Fun.tCDF( t, df );
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
    
//    public static void main (String args[])
//    {
//        InferenceTest sample = new TwoSampleTTest(new Stats("test.txt"), new Stats("test2.txt"));
//        //sample.toString(); 
//        System.out.println(sample);
//        double p = sample.calculatePValue();
//        System.out.println("p " + p);
//        System.out.println("tcdf " + sample.testStatistic());
//        System.out.println("se " + sample.deviationOrError());
//        //System.out.println("sampleSD " + sample.getSampleSD2());
//    }

}