/**
 *
 * This interface contains methods universally needed to hypotehsis tests.
 * The methods will be appropriately defined in each of 4 hypothesis test classes.
 * 
 * @author Durga Ganesh
 * @version May 10, 2016
 * @author Period: 3
 * @author Assignment: Statistics_Final
 *
 * @author Sources: Power Team
 */
public interface InferenceTest
{
    /**
     * This method will calculate the p value.
     * @return p'value of type double
     */
    double calculatePValue();

    /**
     * This method will either calculate the standard deviation or 
     * the standard error based on the test.
     * @return value as double
     */
    double deviationOrError();

    /**
     * This method will either calculate the z'score or 
     * t'statistic based on whether the data is categorical or quantitative.
     * @return value as double
     */
    double testStatistic();

}
