import org.junit.*;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import junit.framework.JUnit4TestAdapter;


public class JUnitTest
{
    private String fName = "test.txt";

    private int success = 10;

    private int failure = 10;

    private int success2 = 7;

    private int failure2 = 13;

    private double populationSuccess = .4;

    private double populationMean = 3.0;

    private double sampleMean = 2.5;

    private double sampleSD = 1.516575;

    private int sampleSize = 6;

    // private double populationDiff = 0.5; assumes is 0

    private double sampleMean2 = 3.5;

    private double sampleSD2 = 1.8708286933869707;

    private int sampleSize2 = 6;


    @Test
    public void statsConstructor() throws FileNotFoundException
    {
        Stats s = new Stats( fName );
        assertEquals( s.getListName(), "test.txt" );

    }


    @Test
    public void statsGetData() throws FileNotFoundException
    {
        Stats s = new Stats( fName );
        assertEquals( s.getData().toString(), "[1.0, 2.0, 3.0, 3.0, 1.0, 5.0]" );
    }


    @Test
    public void statsGetSortedData() throws FileNotFoundException
    {
        Stats s = new Stats( fName );
        assertEquals( s.getSortedData().toString(), "[1.0, 1.0, 2.0, 3.0, 3.0, 5.0]" );
    }


    @Test
    public void statsGetMode() throws FileNotFoundException
    {
        Stats s = new Stats( fName );
        assertEquals( s.getMode().toString(), "[1.0, 3.0]" );
    }


    @Test
    public void statsGetMean() throws FileNotFoundException
    {
        Stats s = new Stats( fName );
        assertEquals( s.getSortedData().toString(), "[1.0, 1.0, 2.0, 3.0, 3.0, 5.0]" );
    }


    @Test
    public void statsGetRange() throws FileNotFoundException
    {
        Stats s = new Stats( fName );
        assertEquals( ( s.getRange() + "" ), "4.0" );
    }


    @Test
    public void statsGetMin() throws FileNotFoundException
    {
        Stats s = new Stats( fName );
        assertEquals( ( s.getMin() + "" ), "1.0" );
    }


    @Test
    public void statsGetMax() throws FileNotFoundException
    {
        Stats s = new Stats( fName );
        assertEquals( ( s.getMax() + "" ), "5.0" );
    }


    @Test
    public void statsGetSize() throws FileNotFoundException
    {
        Stats s = new Stats( fName );
        assertEquals( ( s.getSize() + "" ), "6" );
    }


    @Test
    public void statsGetVariance() throws FileNotFoundException
    {
        Stats s = new Stats( fName );
        assertEquals( ( s.getVariance() + "" ), "1.9166666666666667" );
    }


    @Test
    public void statsGetStandardDeviation() throws FileNotFoundException
    {
        Stats s = new Stats( fName );
        assertEquals( ( s.getStandardDeviation() + "" ), "1.3844373104863459" );
    }


    @Test
    public void statsGetIQR() throws FileNotFoundException
    {
        Stats s = new Stats( fName );
        assertEquals( ( s.getIqr() + "" ), "2.0" );
    }

    @Test
    public void statsGetQ1() throws FileNotFoundException
    {
        Stats s = new Stats( fName );
        assertEquals( ( s.getQ1() + "" ), "1.0" );
    }

    @Test
    public void statsGetIndexOf() throws FileNotFoundException
    {
        Stats s = new Stats( fName );
        assertEquals( ( s.indexOf(-1) + "" ), "-1" );
        assertEquals( ( s.indexOf(5) + "" ), "5" );
        assertEquals( ( s.indexOf(2) + "" ), "2" );
    }
    @Test
    public void statsGetQ3() throws FileNotFoundException
    {
        Stats s = new Stats( fName );
        assertEquals( ( s.getQ3() + "" ), "3.0" );
    }
    @Test
    public void onePropZTestNoParamConstructor()
    {
        InferenceTest sample = new OnePropZTest();
        String toStr = sample.toString();
        assertTrue( "<< Invalid OnePropZTest Constructor >>",
            toStr.contains( "int success:" + 0 ) && toStr.contains( "int failure:" + 0 )
                && toStr.contains( "double populationSuccess:" + 0.0 ) );
    }


    @Test
    public void onePropZTestConstructor()
    {
        InferenceTest sample = new OnePropZTest( success, failure, populationSuccess );
        String toStr = sample.toString();
        assertTrue( "<< Invalid OnePropZTest Constructor >>",
            toStr.contains( "int success:" + success ) && toStr.contains( "int failure:" + failure )
                && toStr.contains( "double populationSuccess:" + populationSuccess ) );
    }


    @Test
    public void onePropZTestToString()
    {
        InferenceTest sample = new OnePropZTest( success, failure, populationSuccess );
        assertNotNull( sample.toString() );
    }


    @Test
    public void onePropZTestGetSuccess()
    {
        OnePropZTest sample = new OnePropZTest( success, failure, populationSuccess );
        assertEquals( "<< success: " + sample.getSuccess() + " should be " + success + " >>",
            success,
            sample.getSuccess() );
    }


    @Test
    public void onePropZTestGetFailure()
    {
        OnePropZTest sample = new OnePropZTest( success, failure, populationSuccess );
        assertEquals( "<< failure: " + sample.getFailure() + " should be " + failure + " >>",
            failure,
            sample.getFailure() );
    }


    @Test
    public void onePropZTestGetPSuccess()
    {
        OnePropZTest sample = new OnePropZTest( success, failure, populationSuccess );
        assertEquals( sample.getPSuccess(), populationSuccess, 0.0 );
    }


    @Test
    public void onePropZTestDeviationOrError()
    {
        InferenceTest sample = new OnePropZTest( success, failure, populationSuccess );
        assertEquals( sample.deviationOrError(), 0.10954, 0.1 );
    }


    @Test
    public void onePropZTestTestStatistic()
    {
        InferenceTest sample = new OnePropZTest( success, failure, populationSuccess );
        assertEquals( sample.testStatistic(), 0.91287, 0.1 );
    }


    @Test
    public void onePropZTestCalculatePValue()
    {
        InferenceTest sample = new OnePropZTest( success, failure, populationSuccess );
        assertEquals( sample.calculatePValue(), 0.3613, 0.1 );
    }

    @Test
    public void onePropZTestPHat()
    {
        OnePropZTest sample = new OnePropZTest(success, failure, populationSuccess);
        assertEquals( sample.pHat(), success / (success + failure + 0.0), 0.0000001);
    }
    
    @Test
    public void twoPropZTestNoParamConstructor()
    {
        InferenceTest sample = new TwoPropZTest();
        String toStr = sample.toString();
        assertTrue( "<< Invalid OnePropZTest Constructor >>",
            toStr.contains( "int success1:" + 0 ) && toStr.contains( "int failure1:" + 0 )
                && toStr.contains( "int success2:" + 0 ) && toStr.contains( "int failure2:" + 0 ) );
    }


    @Test
    public void twoPropZTestConstructor()
    {
        InferenceTest sample = new TwoPropZTest( success, failure, success2, failure2 );
        String toStr = sample.toString();
        assertTrue( "<< Invalid TwoPropZTest Constructor >>",
            toStr.contains( "int success1:" + success ) && toStr.contains( "int failure1:" + failure )
                && toStr.contains( "int success2:" + success2 ) && toStr.contains( "int failure2:" + failure2 ) );
    }


    @Test
    public void twoPropZTestToString()
    {
        InferenceTest sample = new TwoPropZTest( success, failure, success2, failure2 );
        assertNotNull( sample.toString() );
    }


    @Test
    public void twoPropZTestGetSuccess1()
    {
        TwoPropZTest sample = new TwoPropZTest( success, failure, success2, failure2 );
        assertEquals( "<< success: " + sample.getSuccess1() + " should be " + success + " >>",
            success,
            sample.getSuccess1() );
    }


    @Test
    public void twoPropZTestGetFailure1()
    {
        TwoPropZTest sample = new TwoPropZTest( success, failure, success2, failure2 );
        assertEquals( "<< failure: " + sample.getFailure1() + " should be " + failure + " >>",
            failure,
            sample.getFailure1() );
    }


    @Test
    public void twoPropZTestGetSuccess2()
    {
        TwoPropZTest sample = new TwoPropZTest( success, failure, success2, failure2 );
        assertEquals( "<< success: " + sample.getSuccess2() + " should be " + success + " >>",
            success2,
            sample.getSuccess2() );
    }


    @Test
    public void twoPropZTestGetFailure2()
    {
        TwoPropZTest sample = new TwoPropZTest( success, failure, success2, failure2 );
        assertEquals( "<< failure: " + sample.getFailure2() + " should be " + failure + " >>",
            failure2,
            sample.getFailure2() );
    }


    @Test
    public void twoPropZTestDeviationOrError()
    {
        InferenceTest sample = new TwoPropZTest( success, failure, success2, failure2 );
        assertEquals( sample.deviationOrError(), 0.15632, 0.1 );
    }


    @Test
    public void twoPropZTestTestStatistic()
    {
        InferenceTest sample = new TwoPropZTest( success, failure, success2, failure2 );
        assertEquals( sample.testStatistic(), 0.95954, 0.1 );
    }


    @Test
    public void twoPropZTestCalculatePValue()
    {
        InferenceTest sample = new TwoPropZTest( success, failure, success2, failure2 );
        assertEquals( sample.calculatePValue(), 0.33723, 0.1 );
    }


    @Test
    public void oneSampleTTestNoParamConstructor()
    {
        InferenceTest sample = new OneSampleTTest();
        String toStr = sample.toString();
        assertTrue( "<< Invalid OneSampleTTest Constructor >>",
            toStr.contains( "double populationMean:" + 0.0 ) && toStr.contains( "double sampleMean:" + 0.0 )
                && toStr.contains( "double sampleSD:" + 0.0 ) && toStr.contains( "int sampleSize:" + 0 ) );
    }


    @Test
    public void oneSampleTTestConstructor() throws FileNotFoundException
    {
        InferenceTest sample = new OneSampleTTest( new Stats( "test.txt" ), 3.0 );
        String toStr = sample.toString();
        assertTrue( "<< Invalid OneSampleTTest Constructor >>",
            toStr.contains( "double populationMean:" + populationMean )
                && toStr.contains( "double sampleMean:" + sampleMean )
                && toStr.contains( "double sampleSD:" + sampleSD )
                && toStr.contains( "int sampleSize:" + sampleSize ) );
    }


    @Test
    public void oneSampleTTestToString() throws FileNotFoundException
    {
        InferenceTest sample = new OneSampleTTest( new Stats( "test.txt" ), 3.0 );
        assertNotNull( sample.toString() );
    }


    @Test
    public void oneSampleTTestGetPopMean() throws FileNotFoundException
    {
        OneSampleTTest sample = new OneSampleTTest( new Stats( "test.txt" ), 3.0 );
        assertEquals( sample.getPopulationMean(), populationMean, 0.01 );
    }


    @Test
    public void oneSampleTTestGetSampleMean() throws FileNotFoundException
    {
        OneSampleTTest sample = new OneSampleTTest( new Stats( "test.txt" ), 3.0 );
        assertEquals( sample.getSampleMean(), sampleMean, 0.01 );
    }


    @Test
    public void oneSampleTTestGetSampleSize() throws FileNotFoundException
    {
        OneSampleTTest sample = new OneSampleTTest( new Stats( "test.txt" ), 3.0 );
        assertEquals( "<< sample size: " + sample.getSampleSize() + " should be " + sampleSize + " >>",
            sampleSize,
            sample.getSampleSize() );
    }


    @Test
    public void oneSampleTTestGetSampleSD() throws FileNotFoundException
    {
        OneSampleTTest sample = new OneSampleTTest( new Stats( "test.txt" ), 3.0 );
        assertEquals( sample.getSampleSD(), sampleSD, 0.01 );
    }


    @Test
    public void oneSampleTTestDeviationOrError() throws FileNotFoundException
    {
        InferenceTest sample = new OneSampleTTest( new Stats( "test.txt" ), 3.0 );
        assertEquals( sample.deviationOrError(), .619139, 0.01 );
    }


    @Test
    public void oneSampleTTestTestStatistic() throws FileNotFoundException
    {
        InferenceTest sample = new OneSampleTTest( new Stats( "test.txt" ), 3.0 );
        assertEquals( sample.testStatistic(), -0.80757, 0.1 );
    }


    @Test
    public void oneSampleTTestCalculatePValue() throws FileNotFoundException
    {
        InferenceTest sample = new OneSampleTTest( new Stats( "test.txt" ), 3.0 );
        assertEquals( sample.calculatePValue(), 0.45602, 0.1 );
    }


    @Test
    public void twoSampleTTestNoParamConstructor()
    {
        InferenceTest sample = new TwoSampleTTest();
        String toStr = sample.toString();
        assertTrue( "<< Invalid TwoSampleTTest Constructor >>",
            toStr.contains( "double sampleMean1:" + 0.0 ) && toStr.contains( "double sampleMean2:" + 0.0 )
                && toStr.contains( "double sampleSD1:" + 0.0 ) && toStr.contains( "double sampleSD2:" + 0.0 )
                && toStr.contains( "int sampleSize1:" + 0 ) && toStr.contains( "int sampleSize2:" + 0 ) );
    }


    @Test
    public void twoSampleTTestConstructor() throws FileNotFoundException
    {
        InferenceTest sample = new TwoSampleTTest( new Stats( "test.txt" ), new Stats( "test2.txt" ) );
        String toStr = sample.toString();
        assertTrue( "<< Invalid TwoSampleTTest Constructor >>",
            toStr.contains( "double sampleMean1:" + sampleMean )
                && toStr.contains( "double sampleMean2:" + sampleMean2 )
                && toStr.contains( "double sampleSD1:" + sampleSD ) && toStr.contains( "double sampleSD2:" + sampleSD2 )
                && toStr.contains( "int sampleSize1:" + sampleSize )
                && toStr.contains( "int sampleSize2:" + sampleSize2 ) );
    }


    @Test
    public void twoSampleTTestToString() throws FileNotFoundException
    {
        InferenceTest sample = new TwoSampleTTest( new Stats( "test.txt" ), new Stats( "test2.txt" ) );
        assertNotNull( sample.toString() );
    }


    // @Test
    // public void twoSampleTTestGetPopDiff()
    // {
    // TwoSampleTTest sample = new TwoSampleTTest(new Stats("test.txt"), new
    // Stats("test2.txt"), 0.5);
    // assertEquals( sample.getPopulationDiff(), populationDiff, 0.01 );
    // }

    @Test
    public void twoSampleTTestGetSampleMean1() throws FileNotFoundException
    {
        TwoSampleTTest sample = new TwoSampleTTest( new Stats( "test.txt" ), new Stats( "test2.txt" ) );
        assertEquals( sample.getSampleMean1(), sampleMean, 0.01 );
    }


    @Test
    public void twoSampleTTestGetSampleSD1() throws FileNotFoundException
    {
        TwoSampleTTest sample = new TwoSampleTTest( new Stats( "test.txt" ), new Stats( "test2.txt" ) );
        assertEquals( sample.getSampleSD1(), sampleSD, 0.01 );
    }


    @Test
    public void twoSampleTTestGetSampleSize1() throws FileNotFoundException
    {
        TwoSampleTTest sample = new TwoSampleTTest( new Stats( "test.txt" ), new Stats( "test2.txt" ));
        assertEquals( "<< sample size: " + sample.getSampleSize1() + " should be " + sampleSize + " >>",
            sampleSize,
            sample.getSampleSize1() );
    }


    @Test
    public void twoSampleTTestGetSampleMean2() throws FileNotFoundException
    {
        TwoSampleTTest sample = new TwoSampleTTest( new Stats( "test.txt" ), new Stats( "test2.txt" ) );
        assertEquals( sample.getSampleMean2(), sampleMean2, 0.01 );
    }


    @Test
    public void twoSampleTTestGetSampleSD2() throws FileNotFoundException
    {
        TwoSampleTTest sample = new TwoSampleTTest( new Stats( "test.txt" ), new Stats( "test2.txt" ) );
        assertEquals( sample.getSampleSD2(), sampleSD2, 0.01 );
    }


    @Test
    public void twoSampleTTestGetSampleSize2() throws FileNotFoundException
    {
        TwoSampleTTest sample = new TwoSampleTTest( new Stats( "test.txt" ), new Stats( "test2.txt" ));
        assertEquals( "<< sample size: " + sample.getSampleSize2() + " should be " + sampleSize + " >>",
            sampleSize2,
            sample.getSampleSize2() );
    }


    @Test
    public void twoSampleTTestDeviationOrError() throws FileNotFoundException
    {
        InferenceTest sample = new TwoSampleTTest( new Stats( "test.txt" ), new Stats( "test2.txt" ) );
        assertEquals( sample.deviationOrError(), 0.983192080250175, 0.01 );
    }


    @Test
    public void twoSampleTTestTestStatistic() throws FileNotFoundException
    {
        InferenceTest sample = new TwoSampleTTest( new Stats( "test.txt" ), new Stats( "test2.txt" ) );
        assertEquals( sample.testStatistic(), -1.0170952554312156, 0.01 );
    }
    
    @Test
    public void twoSampleTTestCalculatePValue() throws FileNotFoundException
    {
        InferenceTest sample = new TwoSampleTTest( new Stats( "List1.txt" ), new Stats( "List2.txt" ) );
        assertEquals( sample.calculatePValue(), .7761, .001);
    }

    //Test Fun's methods
    
    @Test
    public void normalPDFNormalizedTest()
    {
        assertEquals( Fun.normalPDF( -1.1 ), 0.217852, 0.0001);
        assertEquals( Fun.normalPDF(0), 0.398942, 0.0001);
    }
    
    @Test
    public void normalCDFTest()
    {
        assertEquals( Fun.normalCDF( -1 ), 0.158655, 0.0001);
        assertEquals( Fun.normalCDF( 0 ), 0.5, 0.0001);
        assertEquals( Fun.normalCDF( 2 ), 0.97725, 0.0001);
    }
    
    @Test
    public void tPDFTest()
    {
        assertEquals( Fun.tPDF( 2.2, 2 ), 0.055901, 0.0001);
        assertEquals( Fun.tPDF( 2.2, 7 ), 0.047037, 0.0001);
        assertEquals( Fun.tPDF( 2.2, 99 ), 0.036588, 0.0001);
    }
    
    @Test
    public void tCDFTest()
    {
        assertEquals( Fun.tCDF( 2.2, 2 ), 0.920596, 0.0001);
    }
    
    @Test
    public void oldRoundTest()
    {
        assertEquals( Fun.oldRound( 7.9 ), 10, 0.01);
    }
    
    public void roundTest()
    {
        assertEquals( Fun.round( 9.98512, 2 ), 10.0, 0.01);
    }
    
    // @Test
    // public void twoSampleTTestCalculatePValue()
    // {
    // InferenceTest sample = new TwoSampleTTest(new Stats("test.txt"), new
    // Stats("test2.txt"), 0.5);
    // assertEquals(sample.calculatePValue(), 0.45602, 0.1 );
    // }
}