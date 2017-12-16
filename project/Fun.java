import java.text.*;
import java.util.function.*;
import org.apache.commons.math3.special.*;

/**
 *  Used to store widely used static math FUNctions
 *
 *  @author  Sinclair Chen
 *  @version May 9, 2016
 *  @author  Period: 3
 *  @author  Assignment: Statistics_Final
 *
 *  @author  Sources: Wolfram Mathworld (for some of the equations)
 */
public class Fun
{
    /**
     * square root of 2pi
     */
    public final static double ROOT2PI = Math.sqrt(2 * Math.PI);
    
    /**
     * Probability density function for a normalized gaussian distribution
     * 
     * @param z your normalized value (val - mean)/(standard deviation)
     * @return relative frequency of getting z
     */
    public static double normalPDF(double z)
    {
        return Math.exp(-z*z / 2) / ROOT2PI;
    }
    
//    /**
//     * Probability density function for a gaussian distribution. Not normalized
//     * 
//     * @param x your value
//     * @param avg mean
//     * @param stdDev standard deviation
//     * @return relative frequency of getting x
//     */
//    public static double normalPDF(double x, double avg, double stdDev)
//    {
//        return normalPDF((x - avg) / stdDev);
//    }
    
    /**
     * Cumulative density function for a normalized gaussian distribution
     * Returns area under the curve between -infinity and z of the gaussian
     * (aka normal) curve
     * 
     * @param z normalized value (val - mean) / stdDev
     * @return probability of getting a value of z or less
     */
    public static double normalCDF(double z)
    {
        return 0.5 + 0.5 * Erf.erf(z / Math.sqrt(2));
    }

//unused

//    //standardized. Area under standard normal curve from z1 to z2
//    public static double normalCDF(double z1, double z2)
//    {
//        return normalCDF(z2) - normalCDF(z1);
//    }
//    
//    public static double normalCDF(double x1, double x2, double avg, double stdDev)
//    {
//        double z1 = (x1 - avg) / stdDev;
//        double z2 = (x2 - avg) / stdDev;
//        return normalCDF(z1, z2);
//    }
    
    /**
     * Probability density function for a student's t distribution.
     * 
     * @param t your normalized test statistic
     * @param df degrees of freedom
     * @return relative frequency of getting t
     */
    public static double tPDF(double t, double df)
    {
        double denom = Math.sqrt(df * Math.PI) * Gamma.gamma(0.5 * df)
                        * Math.pow(1 + t*t/df, (df + 1)/2);
        return Gamma.gamma(0.5 * (df + 1)) / denom;
    }
    
    /**
     * Cumulative density function for a student's t distribution.
     * 
     * @param t your normalized test statistic
     * @param df degrees of freedom
     * @return area under standard t curve between -infinity and t
     */
    public static double tCDF(double t, double df)
    {
        double b1 = Beta.regularizedBeta(1, .5*df, .5);
        double b2 = Beta.regularizedBeta( df/(df + t*t), .5*df, .5);
        return .5 * (1 + Math.signum(t) * (b1 - b2));
    }    
    
//unused    
    
//    //given a function, return the derivative
//    public static Function<Double, Double> derivative(Function<Double, Double> f)
//    {
//        double dx = 1e-10;
//        return (Double x) -> (f.apply(x + dx) - f.apply(x)) / dx;
//    }
//    
//    //This doesn't really work
//    //from zero to x
//    public static Function<Double, Double> integral(Function<Double, Double>f)
//    {
//        return (Double x) ->
//        {
//            double avg = f.apply(0.0);
//            double dx = 1e-10;
//            int numBars = (int)(x/dx);
//            for (int i = 1; i < numBars; i++)
//            {
//                avg = avg * ((double)i / i+1) + f.apply(i*dx)/i+1;
//            }
//            return avg * x;
//        };
//    }
    
    /**
     * For the sake of scaling, this method eats up a decimal and returns
     * another decimal that is one of the following:
     * 1 * 10^n
     * 2 * 10^n
     * 2.5 * 10^n
     * 5 * 10^n
     * 
     * @param num the scaling that you want to use
     * @return new scaling that follows Dr. Rocklin's graph scaling guidelines
     */
    public static double oldRound(double num)
    {
        if (num < 0)
        {
            return -oldRound(-num);
        }
        
        //round to scientific notation
        String numString = new DecimalFormat("0.##E0").format(num);
//        System.out.println("round " + numString);
        
        int eIndex = numString.indexOf('E');
        
        double mantissa = Double.parseDouble(numString.substring(0, eIndex));
        int orderOfMag = Integer.parseInt(numString.substring(eIndex + 1));
        double power = Math.pow(10, orderOfMag);
        
        if (mantissa < 1.5)  // if m in [0, 1.5) round m to 1
            return power;
        if (mantissa < 2.25) // if m in [1.5, 2.25) round m to 2
            return 2 * power;
        if (mantissa < 2.75) // if m in [2.25, 2.75) round m to 2.5
            return 2.5 * power;
        if (mantissa < 5.5)  // if m in [2.75, 7) round m to 5
            return 5 * power;
        else                 // if m in [7, 10) round m to 10
            return 10 * power;
    }
    
    /**
     * Rounds a number to a certain amount of significant digits
     * precondition: sigFigs > 0
     * 
     * @param num your value
     * @param sigFigs number of significant digits
     * @return rounded value
     */
    protected static double round(double num, int sigFigs)
    {
        double tenToN = Math.pow( 10, sigFigs );
        return Math.round( tenToN * num ) / tenToN;
    }
    
    /**
     * Just a tester class that isn't very important
     * @param args unused
     */
    public static void main(String[] args)
    {
        System.out.println(1);
        System.out.println(-1);
        System.out.println(1.01);
        System.out.println(1.01E7);
        
        double x = 0.000012;
        
        System.out.println(x);
        System.out.println(oldRound(x));
        
//        System.out.println(derivative(i -> i*i / 2).apply(Math.PI));
//        System.out.println(integral(i -> 7.0).apply(1.0));
    }
}
