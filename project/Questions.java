import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.EmptyBorder;


/**
 * Creates a interactive question display for all the tests that we have coded
 * for. (One sample t test, two sample t test, one proportion z test, two
 * proportion z test.
 *
 * @author nehakunjal
 * @version May 24, 2016
 * @author Period: 3
 * @author Assignment: Statistics_Final
 *
 * @author Sources: none
 */
public class Questions extends JFrame implements ActionListener
{
    private JLabel definitions = new JLabel( "If you need definitions, look for the text file 'vocabularylist.txt'",
        JLabel.CENTER );

    /**
     * If true, means that the appropriate test is one proportion z test
     */
    private boolean one = false;

    /**
     * If true, means that the appropriate test is two proportion z test
     */
    private boolean two = false;

    /**
     * If true, means that the appropriate test is one sample t test
     */
    private boolean oneTtest = false;

    /**
     * If true, means that the appropriate test is two sample t test
     */
    private boolean twoTtest = false;

    /**
     * Question that determines if the user know the test or not
     */
    private final String q0 = "<html><br><center> Do you know your test? <br><br><center> If you do, click the corresponding test. <br><br><center> Otherwise, click 'I don't know.'</html>";

    // quantitative questions
    /**
     * Question that determines whether the data is quantitative or categorical
     */
    private final String q1 = "Is your data quantitative or categorical?";

    /**
     * Question that adds a text.txt to a Stats object
     */
    private final String qQ2 = "Please enter in the name of your file (must be .txt)";

    /**
     * Question that adds a text.txt to a second Stats object if user has one
     */
    private final String qQ3 = "<html><center>If you have another list enter name below.<br><br><center> Your file must be a '.txt' file.<br><br><center>If you don't, enter in 'N/A'</html>";

    /**
     * Question that opens up a box plot if user wants to
     */
    private final String boxPlot = "Would you like to see a box plot of your data?";

    /**
     * Question that checks if Assumption 1 on one sample t test and one
     * proportion z test
     */
    private final String qQ1_4 = "Are the individuals/data points independent?";

    /**
     * Question that checks condition 1 of one sample t test and one proportion
     * z test
     */
    private final String qQ1_5 = "<html><center>C1: Is your data a random sample and is your sample size<br><br><center> less than 10% of the population?</html>";

    /**
     * Question that checks assumption 2 of one sample t test
     */
    private final String qQ1_6 = "Is your population normal?";

    /**
     * Question that checks condition 2 of one sample t test
     */
    private final String qQ1_7 = "<html><center>C2: Does your histogram have <br><br><center>no outliers, is unimodal, and symmetric?</html>";

    /**
     * Question that checks assumption one of two proportion z test and two
     * sample t test
     */
    private final String qQ2_4 = "Are the groups independent?";

    /**
     * Question that checks assumption two of two proportion z test and two
     * sample t test
     */
    private final String qQ2_5 = "Are the individuals/data independent in each group?";

    /**
     * Question that checks condition two of two proportion z test and two
     * sample t test
     */
    private final String qQ2_6 = "<html><center>C2: Is your data a random sample and is your sample size<br><br><center> less than 10% of the population?</html>";

    /**
     * Question that checks assumption 3 of two proportion z test
     */
    private final String qQ2_7 = "A3: Is the population of both groups normal?";

    /**
     * Question that checks condition 3 of two proportion z test and two sample
     * t test
     */
    private final String qQ2_8 = "<html><center>C3: Does your histograms have <br><br><center>no outliers, and are unimodal, and symmetric?</html>";

    // categorical questions
    /**
     * Question that checks whether the categorical test is one proportion or
     * two proportion
     */
    private final String qC2 = "Is your data one sample or two sample?";

    /**
     * Question that asks user to input number of successes in first sample
     */
    private final String qC2_3 = "<html><center>How many successes are in your first sample?<br><br><center>Your input must be a number</html>";

    /**
     * Question that asks user to input number of failures in first sample
     */
    private final String qC2_4 = "<html><center>How many failures are in your first sample?<br><br><center>Your input must be a number</html>";

    /**
     * Question that asks user to input number of successes in second sample
     */
    private final String qC2_5 = "<html><center>How many successes are in your second sample?<br><br><center>Your input must be a number</html>";

    /**
     * Question that asks user to input number of failures in second sample
     */
    private final String qC2_6 = "<html><center>How many failures are in your second sample?<br><br><center>Your input must be a number</html>";

    /**
     * Question that asks user to input number of successes
     */
    private final String qC1_3 = "<html><center>How many successes are in your sample?<br><br><center>Your input must be a number</html>";

    /**
     * Question that asks user to input number of failures
     */
    private final String qC1_4 = "<html><center>How many failures are in your sample?<br><br><center>Your input must be a number</html>";

    /**
     * Question that checks if assumption 2/condition 2 is valid with these data
     * points (one proportion z test)
     */
    private final String qCc2 = "<html><center>A2: The sample size must be large enough to approximate<br><center> the Sample Distribution Model with a normal Model<br>C2: Are the number of successes and failures<br><center> greater than or equal to 10?</html>";

    /**
     * Question that checks if assumption 3/condition 3 is valid with these data
     * points (two proportion z test)
     */
    private final String qCc3 = "<html><center>A3: Both groups must be large enough.<br><br><center>C3: Are the number of successes and failures<br><br><center> greater than or equal to 10?</html>";

    /**
     * Question that asks user to input true difference of proportions of
     * success
     */
    private final String qCFinal = "<html><center>What is the true difference of proportions of success?<br><br><center>Your input must be a decimal number.</html>";

    /**
     * error statement that says why the test is not valid
     */
    private String error = "<html><center>This assumption/condition must be true in order <br>for the test to be valid and appropriate for this data. <br>We cannot perform this hypothesis test."
        + "<br> We recommend that you perform your experiment or study again <br> while making sure that this assumption/condition is met.</html>";

    /**
     * arraylist of numbers to store values for categorical tests
     */
    private ArrayList<Object> catNumbers;

    /**
     * first list as a Stats object for quantitative tests
     */
    private Stats list1;

    /**
     * first list as a Stats object for two sample t tests
     */
    private Stats list2;

    /**
     * entire panel
     */
    private JPanel panel;

    /**
     * combo box used so that user can choose appropriate test
     */
    private JComboBox<String> answers;

    /**
     * panel in which question and choices is changed after slide change
     */
    private JPanel inside;

    /**
     * field used to input any lines of strings by the users
     */
    private JTextField textFile;

    /**
     * allows to switch slides, changes to exit when user has no more slides to
     * continue to.
     */
    private JButton cont;


    /**
     * Sets up background and initializes appropriate objects for use later in
     * program. Calls the zero slide which is the first question the user sees.
     * 
     * @param title
     *            = title of the window that pops up
     */
    public Questions( String title )
    {
        catNumbers = new ArrayList<Object>();
        panel = new JPanel( new GridLayout( 1, 1, 50, 50 ) );
        panel.setBorder( new EmptyBorder( 50, 50, 50, 50 ) );
        this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );

        inside = new JPanel( new GridLayout( 3, 1, 50, 50 ) );
        inside.setBorder( new EmptyBorder( 40, 20, 40, 40 ) );
        panel.add( inside );
        zeroSlide();
    }


    /**
     * Sets up format so user can answer the question if they know the test of
     * not (q0)
     */
    private void zeroSlide()
    {
        JLabel quest1 = new JLabel( q0, JLabel.CENTER );
        quest1.setSize( quest1.getPreferredSize() );
        String[] responses = { "", "One Proportion Z Test", "Two Proportion Z Test", "One Sample T Test",
            "Two Sample T Test", "I don't know" };
        answers = new JComboBox<String>( responses );
        answers.setPreferredSize( new Dimension( 50, 10 ) );

        answers.addActionListener( this );
        answers.setActionCommand( "firstResponse" );

        cont = new JButton( "Continue" );
        cont.setMaximumSize( new Dimension( 30, 15 ) );
        cont.setPreferredSize( new Dimension( 50, 10 ) );
        cont.addActionListener( this );
        cont.setActionCommand( "response0" );

        inside.add( quest1 );
        inside.add( answers );
        inside.add( cont );

        Container c = getContentPane();
        c.add( panel );
    }


    /**
     * Sets up format so user can answer the question if they know whether their
     * test is quantitative or categorical (q1)
     */
    private void firstSlide()
    {
        JLabel quest1 = new JLabel( q1, JLabel.CENTER );
        String[] responses = { "", "Quantitative", "Categorical" };
        answers = new JComboBox<String>( responses );

        answers.addActionListener( this );
        cont.setActionCommand( "response1" );

        inside.add( quest1 );
        inside.add( answers );
        inside.add( cont );

    }


    // quantitative slides
    /**
     * Sets up format so user can enter in input for first Stats list(qQ2)
     */
    private void secondQSlide()
    {
        JLabel q2 = new JLabel( qQ2, JLabel.CENTER );
        textFile = new JTextField();
        textFile.addActionListener( this );

        cont.setActionCommand( "Q2" );

        inside.add( q2 );
        inside.add( textFile );
        inside.add( cont );
    }


    /**
     * Sets up format so user can enter in input for second Stats list if user
     * has one(qQ3)
     */
    private void secondListCheck()
    {
        JLabel q2 = new JLabel( qQ3, JLabel.CENTER );
        textFile.setText( "" );
        textFile.addActionListener( this );

        cont.setActionCommand( "Q3" );

        inside.add( q2 );
        inside.add( textFile );
        inside.add( cont );
    }


    /**
     * Sets up format so user can answer the question whether assumption 1 is
     * valid or not(qQ2)
     */
    private void assump1Slide()
    {
        JLabel quest;
        if ( oneTtest || one )
        {
            quest = new JLabel( "A1: " + qQ1_4, JLabel.CENTER );
        }
        else
        {
            quest = new JLabel( "A1: " + qQ2_4, JLabel.CENTER );
        }
        String[] responses = { "", "Yes", "No" };
        answers = new JComboBox<String>( responses );

        answers.addActionListener( this );

        cont.setActionCommand( "a1" );

        inside.add( quest );
        inside.add( answers );
        inside.add( cont );
    }


    /**
     * Sets up format so user can answer whether the user wants to see a boxplot
     * or not (boxplot)
     */
    private void boxPlotSlide()
    {
        JLabel quest = new JLabel( boxPlot, JLabel.CENTER );

        String[] responses = { "", "Yes", "No" };
        answers = new JComboBox<String>( responses );

        answers.addActionListener( this );

        cont.setActionCommand( "boxPlot" );

        inside.add( quest );
        inside.add( answers );
        inside.add( cont );
    }


    /**
     * Sets up format so user can answer the question whether for condition one
     * or assumption 2 (based off of test) is valid or not(qQ1_5 for one sample
     * t test and one proportion z test and qQ2_5 for the other two)
     */
    private void c1_a2Slide()
    {
        JLabel quest;
        if ( oneTtest || one )
        {
            quest = new JLabel( qQ1_5, JLabel.CENTER );
        }
        else
        {
            quest = new JLabel( "A2: " + qQ2_5, JLabel.CENTER );
        }
        String[] responses = { "", "Yes", "No" };
        answers = new JComboBox<String>( responses );

        answers.addActionListener( this );

        cont.setActionCommand( "c1/a2" );

        inside.add( quest );
        inside.add( answers );
        inside.add( cont );
    }


    /**
     * Sets up format so user can answer the question whether for condition 2 or
     * assumption 2 (based off of test) is valid or not(qQ1_6 for one sample t
     * test and qCc2 for one proportion z test and qQ2_6 for the other two)
     */
    private void a2_c2Slide()
    {
        JLabel quest;
        if ( oneTtest )
        {
            quest = new JLabel( "A2: " + qQ1_6, JLabel.CENTER );
        }
        else if ( one )
        {
            quest = new JLabel( qCc2, JLabel.CENTER );
        }
        else
        {
            quest = new JLabel( qQ2_6, JLabel.CENTER );
        }
        String[] responses = { "", "Yes", "No" };
        answers = new JComboBox<String>( responses );

        answers.addActionListener( this );

        cont.setActionCommand( "a2/c2" );

        inside.add( quest );
        inside.add( answers );
        inside.add( cont );
    }


    /**
     * Sets up format so user can answer the question whether for condition 2 or
     * assumption 3 (based off of test) is valid or not(qQ1_7 for one sample t
     * test and qCc3 for one proportion z test and qQ2_7 for the other two)
     */
    private void c2_a3Slide()
    {
        JLabel quest;
        if ( oneTtest )
        {
            quest = new JLabel( qQ1_7, JLabel.CENTER );
        }
        else if ( one )
        {
            quest = new JLabel( qCc3, JLabel.CENTER );
        }
        else
        {
            quest = new JLabel( qQ2_7, JLabel.CENTER );
        }
        String[] responses = { "", "Yes", "No" };
        answers = new JComboBox<String>( responses );

        answers.addActionListener( this );

        cont.setActionCommand( "c2/a3" );

        inside.add( quest );
        inside.add( answers );
        inside.add( cont );
    }


    /**
     * Sets up format so user can answer the question whether for condition 2 or
     * assumption 2 (based off of test) is valid or not(qQ2_8 for two sample t
     * test and qCc2 for two proportion z test )
     */
    private void a3Slide()
    {
        JLabel quest;
        if ( two )
        {
            quest = new JLabel( qCc3, JLabel.CENTER );
        }
        else
        {
            quest = new JLabel( qQ2_8, JLabel.CENTER );
        }

        String[] responses = { "", "Yes", "No" };
        answers = new JComboBox<String>( responses );

        answers.addActionListener( this );

        cont.setActionCommand( "a3" );

        inside.add( quest );
        inside.add( answers );
        inside.add( cont );

    }


    /**
     * Makes the button continue the exit button, and tells user that the test
     * their data requires cannot qork because one of the assumptions/conditions
     * are not valid
     */
    private void errorSlide()
    {
        JLabel err = new JLabel( error, JLabel.CENTER );
        cont.setText( "exit" );
        cont.setActionCommand( "exit" );
        inside.add( err );
        inside.add( cont );
    }


    // categorical slides
    /**
     * Sets up format so user can answer the question whether their test is one
     * sample or two sample.
     */
    private void secondCSlide()
    {
        JLabel quest2 = new JLabel( qC2, JLabel.CENTER );
        String[] responses = { "", "one sample", "two sample" };
        answers = new JComboBox<String>( responses );

        answers.addActionListener( this );

        cont.setActionCommand( "response2Cat" );

        inside.add( quest2 );
        inside.add( answers );
        inside.add( cont );
    }


    /**
     * Sets up format so user can enter in input for the number of successes
     * that the user has ( exact question varies based off of type of test)
     */
    private void thirdAndFiveSlideCat()
    {
        JLabel quest;
        if ( one )
        {
            quest = new JLabel( qC1_3, JLabel.CENTER );
        }
        else if ( catNumbers.size() == 0 )
        {
            quest = new JLabel( qC2_3, JLabel.CENTER );
        }
        else
        {
            quest = new JLabel( qC2_5, JLabel.CENTER );

        }
        textFile = new JTextField();
        textFile.addActionListener( this );

        cont.setActionCommand( "response3" );

        inside.add( quest );
        inside.add( textFile );
        inside.add( cont );
    }


    /**
     * Sets up format so user can enter in input for the number of failures that
     * the user has ( exact question varies based off of type of test)
     */
    private void fourthAndSixthSlideCat()
    {
        JLabel quest;
        if ( one )
        {
            quest = new JLabel( qC1_4, JLabel.CENTER );
        }
        else if ( catNumbers.size() == 1 )
        {
            quest = new JLabel( qC2_4, JLabel.CENTER );
        }
        else
        {
            quest = new JLabel( qC2_6, JLabel.CENTER );

        }
        textFile = new JTextField();
        textFile.addActionListener( this );

        cont.setActionCommand( "response4" );

        inside.add( quest );
        inside.add( textFile );
        inside.add( cont );
    }


    /**
     * Sets up format so user can enter in input for the true difference of
     * proportions for one proportion z test ( exact question varies based off
     * of type of test)
     */
    private void trueDiffOfPropSlide()
    {
        JLabel quest = new JLabel( qCFinal, JLabel.CENTER );
        textFile = new JTextField();
        textFile.addActionListener( this );

        cont.setActionCommand( "responseCatFinal" );

        inside.add( quest );
        inside.add( textFile );
        inside.add( cont );
    }


    /**
     * Tells user what the appropriate test for their data is and what their
     * values are for the test. Changes the continue button to the exit button
     */
    private void resultSlide()
    {
        String res = "";
        String str = "The appropriate test for you data is: ";
        if ( one )
        {
            str += "One proportion Z test.";
            OnePropZTest test = new OnePropZTest( (int)catNumbers.get( 0 ),
                (int)catNumbers.get( 1 ),
                (double)catNumbers.get( 2 ) );
            res = "<html><center>Standard Deviation: " + test.deviationOrError() + "<br><br><center>Z-score: "
                + test.testStatistic() + "<br><br><center>P-value: " + test.calculatePValue() + "</html>";

            JFrame w = new JFrame( "Normal Curve" );
            w.setBounds( 100, 100, 640, 480 );
            NormalCurve n = new NormalCurve( test.getPSuccess(), test.deviationOrError(), test.pHat() );
            w.getContentPane().add( n );
            w.setVisible( true );
        }
        else if ( two )
        {
            str += "Two proportion Z test.";
            TwoPropZTest test = new TwoPropZTest( (int)catNumbers.get( 0 ),
                (int)catNumbers.get( 1 ),
                (int)catNumbers.get( 2 ),
                (int)catNumbers.get( 3 ) );
            res = "<html><center>Standard Error (pooled): " + test.deviationOrError() + "<br><br><center>Z-score: "
                + test.testStatistic() + "<br><br><center>P-value: " + test.calculatePValue() + "</html>";

            JFrame w = new JFrame( "Normal Curve" );
            w.setBounds( 100, 100, 640, 480 );
            NormalCurve n = new NormalCurve( 0,
                test.deviationOrError(),
                test.deviationOrError() * test.testStatistic() );
            w.getContentPane().add( n );
            w.setVisible( true );
        }
        else if ( oneTtest )
        {
            str += "One sample T test.";
            OneSampleTTest test = new OneSampleTTest( list1, 0.0 );
            res = "<html><center>Standard Error (of the mean):  " + test.deviationOrError()
                + "<br><br><center>T-value: " + test.testStatistic() + "<br><br><center>P-value: "
                + test.calculatePValue() + "</html>";
        }
        else
        {
            str += "Two sample T test.";
            TwoSampleTTest test = new TwoSampleTTest( list1, list2 );
            res = "<html><center>Standard Error:  " + test.deviationOrError() + "<br><br><center>T-value: "
                + test.testStatistic() + "<br><br><center>P-value: " + test.calculatePValue() + "</html>";

        }
        JLabel result = new JLabel( str, JLabel.CENTER );
        JLabel resultNum = new JLabel( res, JLabel.CENTER );
        inside.add( result );
        inside.add( resultNum );
        cont.setText( "exit" );
        cont.setActionCommand( "exit" );
        inside.add( cont );
    }


    /**
     * Sets up format so user can enter in input for the true population mean
     */
    private void truMeanSlide()
    {
        JLabel quest = new JLabel( "What is your population mean?", JLabel.CENTER );

        textFile = new JTextField();
        textFile.addActionListener( this );

        textFile.addActionListener( this );

        cont.setActionCommand( "trueMean" );

        inside.add( quest );
        inside.add( textFile );
        inside.add( cont );
    }


    /**
     * Based off of the Action command, changes slide to the appropriate slide.
     * Opens up histogram and boxplot when the users needs them to answer the
     * questions that follow.
     * 
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    public void actionPerformed( ActionEvent e )
    {
        if ( e.getActionCommand().equals( "response0" ) )

        {
            String type = (String)answers.getSelectedItem();
            if ( type.equals( "I don't know" ) ) // goes to Q/A to figure out
                                                 // appropriate test
            {
                inside.removeAll();
                inside.setLayout( new GridLayout( 4, 1, 50, 50 ) );
                inside.add( definitions );
                firstSlide();
                inside.validate();
            }
            else if ( type.equals( "" ) )
            {
                // stay on same slide
            }
            else if ( type.equals( "One Proportion Z Test" ) )
            {
                this.setVisible( false );
                OnePropZTestFrame i = new OnePropZTestFrame();
                i.setBounds( 800, 0, 750, 900 );
                i.setVisible( true );
                i.setResizable( false );
            }
            else if ( type.equals( "Two Proportion Z Test" ) )
            {
                this.setVisible( false );
                TwoPropZTestFrame i = new TwoPropZTestFrame();
                i.setBounds( 800, 0, 1000, 900 );
                i.setVisible( true );
                i.setResizable( true );
            }
            else if ( type.equals( "One Sample T Test" ) )
            {
                this.setVisible( false );
                OneSampleTTestFrame i = new OneSampleTTestFrame();
                i.setBounds( 800, 0, 750, 900 );
                i.setVisible( true );
                i.setResizable( false );
            }
            else if ( type.equals( "Two Sample T Test" ) )
            {
                this.setVisible( false );
                TwoSampleTTestFrame i = new TwoSampleTTestFrame();
                i.setBounds( 800, 0, 750, 900 );
                i.setVisible( true );
                i.setResizable( false );
            }

        }
        else if ( e.getActionCommand().equals( "response1" ) )
        {
            String type = (String)answers.getSelectedItem();
            inside.removeAll();
            inside.add( definitions );
            if ( type.equals( "Quantitative" ) ) // moves on to quantitative
                                                 // questions
            {
                secondQSlide();

            }
            else if ( type.equals( "" ) )
            {
                firstSlide();// stay on same slide
            }
            else // moves on to categorical questions
            {
                secondCSlide();

            }
            inside.validate();

        }
        else if ( e.getActionCommand().equals( "response2Cat" ) )
        {
            inside.removeAll();
            String type = (String)answers.getSelectedItem();
            inside.add( definitions );
            if ( type.equals( "one sample" ) )
            {
                one = true;
                thirdAndFiveSlideCat();

            }
            else if ( type.equals( "" ) )
            {
                secondCSlide();// stay on same slide
            }
            else
            {
                two = true;
                thirdAndFiveSlideCat();

            }
            inside.validate();

        }
        else if ( e.getActionCommand().equals( "response3" ) )
        {
            try
            {
                int value = Integer.parseInt( textFile.getText().trim() );
                catNumbers.add( value );
                inside.removeAll();
                inside.add( definitions );
                fourthAndSixthSlideCat();
                inside.validate();

            }
            catch ( NumberFormatException ex )
            {
                JOptionPane.showMessageDialog( this,
                    "You must enter in a number",
                    "Cannot read user input",
                    JOptionPane.ERROR_MESSAGE );
            }
        }
        else if ( e.getActionCommand().equals( "response4" ) )
        {
            try
            {
                int value = Integer.parseInt( textFile.getText() );
                catNumbers.add( value );
                if ( two && catNumbers.size() < 4 )
                {
                    inside.removeAll();
                    inside.add( definitions );
                    thirdAndFiveSlideCat();
                    inside.validate();
                }
                else if ( one )
                {
                    inside.removeAll();
                    inside.add( definitions );
                    trueDiffOfPropSlide();
                    inside.validate();
                }
                else
                {
                    inside.removeAll();
                    inside.add( definitions );
                    int value1 = Integer.parseInt( textFile.getText().trim() );
                    catNumbers.add( value1 );
                    // TwoPropZTest test = new TwoPropZTest(
                    // (int)catNumbers.get( 0 ),
                    // (int)catNumbers.get( 1 ),
                    // (int)catNumbers.get( 2 ),
                    // (int)catNumbers.get( 3 ) );
                    assump1Slide();
                    inside.validate();
                }
            }
            catch ( NumberFormatException ex )
            {
                JOptionPane.showMessageDialog( this,
                    "You must enter in a number",
                    "Cannot read user input",
                    JOptionPane.ERROR_MESSAGE );
            }
        }
        else if ( e.getActionCommand().equals( "responseCatFinal" ) )
        {
            try
            {
                double value = Double.parseDouble( textFile.getText().trim() );
                catNumbers.add( value );
                // go to assumptions
                inside.removeAll();
                inside.add( definitions );
                assump1Slide();
                inside.validate();

            }
            catch ( NumberFormatException ex )
            {
                JOptionPane.showMessageDialog( this,
                    "You must enter in a number",
                    "Cannot read user input",
                    JOptionPane.ERROR_MESSAGE );
            }
        }
        else if ( e.getActionCommand().equals( "Q2" ) )
        {
            try
            {
                String str = textFile.getText().trim();
                list1 = new Stats( str );
                if ( list1.getSize() == 0 )
                {
                    throw new NumberFormatException();
                }
                inside.removeAll();
                inside.add( definitions );
                secondListCheck();

                inside.validate();
            }
            catch ( FileNotFoundException ex )
            {
                textFile.setText( "" );
                JOptionPane.showMessageDialog( this,
                    "You must enter in a valid file name. Check to see if your file is attached to this program.",
                    "Cannot read user input",
                    JOptionPane.ERROR_MESSAGE );
            }
            catch ( NumberFormatException ex )
            {
                JOptionPane.showMessageDialog( this,
                    "Your file must have at least one number in it. Your inputed file was empty.",
                    "Empty File",
                    JOptionPane.ERROR_MESSAGE );
            }
        }
        else if ( e.getActionCommand().equals( "Q3" ) )
        {
            try
            {
                String str = textFile.getText().trim();
                if ( str.equals( "N/A" ) || str.equals( "n/a" ) || str.equals( "n/A" ) || str.equals( "N/a" ) )
                {
                    // the test will be one sample t test if all assumptions
                    // work out
                    oneTtest = true;
                    inside.removeAll();
                    inside.add( definitions );
                    truMeanSlide();
                    inside.validate();
                }
                else
                {
                    list2 = new Stats( str );
                    if ( list1.getSize() < 2 )
                    {
                        throw new NumberFormatException();
                    } // the test will be a two sample t if list is not zero
                    twoTtest = true; // test if all assumptions work out
                    inside.removeAll();
                    inside.add( definitions );
                    boxPlotSlide();
                    inside.validate();
                }

            }
            catch ( FileNotFoundException ex )
            {
                textFile.setText( "" );
                JOptionPane.showMessageDialog( this,
                    "You must enter in a valid file name. Check to see if your file is attached to this program.",
                    "Cannot read user input",
                    JOptionPane.ERROR_MESSAGE );
            }
            catch ( NumberFormatException ex )
            {
                JOptionPane.showMessageDialog( this,
                    "Your file must have at least two number in it. Your inputed file was smaller than 2 numbers.",
                    "Empty File",
                    JOptionPane.ERROR_MESSAGE );
            }
        }
        else if ( e.getActionCommand().equals( "boxPlot" ) )
        {
            String type = (String)answers.getSelectedItem();
            if ( type.equals( "Yes" ) )
            {
                if ( twoTtest )
                {
                    JFrame w = new JFrame( "Test" );
                    w.setBounds( 100, 100, 640, 480 );
                    Boxplot one = new Boxplot( list1 );
                    one.setBackground( Color.white );
                    one.setBounds( 800, 0, 750, 900 );
                    one.setVisible( true );
                    w.getContentPane().add( one );
                    w.setVisible( true );
                    w.setResizable( true );

                    JFrame w2 = new JFrame( "Test" );

                    w2.setBounds( 100, 100, 640, 480 );
                    Boxplot two = new Boxplot( list2 );
                    two.setBackground( Color.white );
                    two.setBounds( 800, 0, 750, 900 );
                    two.setVisible( true );
                    w2.getContentPane().add( two );
                    w2.setResizable( true );
                    w2.setVisible( true );
                }
                else
                {
                    JFrame w = new JFrame( "Test" );
                    w.setBounds( 100, 100, 640, 480 );
                    Boxplot one = new Boxplot( list1 );
                    one.setBackground( Color.white );
                    one.setBounds( 800, 0, 750, 900 );
                    one.setVisible( true );
                    w.getContentPane().add( one );
                    w.setResizable( true );
                    w.setVisible( true );
                }
                inside.removeAll();
                inside.add( definitions );
                assump1Slide();
                inside.validate();
            }
            else if ( type.equals( "" ) )
            {
                // stay on same slide
            }
            else
            {
                inside.removeAll();
                inside.add( definitions );
                assump1Slide();
                inside.validate();
            }
        }
        else if ( e.getActionCommand().equals( "a1" ) )
        {
            String type = (String)answers.getSelectedItem();
            if ( type.equals( "Yes" ) )
            {
                inside.removeAll();
                inside.add( definitions );
                c1_a2Slide();
                inside.validate();
            }
            else if ( type.equals( "" ) )
            {
                // stay on same slide
            }
            else
            {
                inside.removeAll();
                inside.add( definitions );
                errorSlide();
                inside.validate();
            }
        }
        else if ( e.getActionCommand().equals( "c1/a2" ) )
        {
            String type = (String)answers.getSelectedItem();
            if ( type.equals( "Yes" ) )
            {// FIX THIS DOES NOT WORK

                inside.removeAll();
                inside.add( definitions );
                a2_c2Slide();
                if ( oneTtest )
                {
                    Histogram h = new Histogram( list1 );
                    JFrame w = new JFrame( "Histogram 1" );
                    w.setBounds( 100, 100, 640, 480 );
                    w.getContentPane().add( h );
                    w.setResizable( true );
                    w.setVisible( true );
                }
                inside.validate();
            }
            else if ( type.equals( "" ) )
            {
                // stay on same slide
            }
            else
            {
                inside.removeAll();
                inside.add( definitions );
                errorSlide();
                inside.validate();
            }
        }
        else if ( e.getActionCommand().equals( "a2/c2" ) )
        {
            String type = (String)answers.getSelectedItem();
            if ( type.equals( "Yes" ) )
            {
                inside.removeAll();
                inside.add( definitions );
                if ( !one )
                {
                    c2_a3Slide();
                }
                else if ( two )
                {
                    a3Slide();
                }
                else
                {
                    resultSlide();
                }
                if ( twoTtest )
                {
                    Histogram h = new Histogram( list1 );
                    JFrame w = new JFrame( "Histogram 1" );
                    w.setBounds( 200, 200, 640, 480 );
                    w.getContentPane().add( h );
                    w.setResizable( true );
                    w.setVisible( true );

                    Histogram h2 = new Histogram( list1 );
                    JFrame w2 = new JFrame( "Histogram 2" );
                    w2.setBounds( 100, 100, 640, 480 );
                    w2.getContentPane().add( h2 );
                    w2.setResizable( true );
                    w2.setVisible( true );
                }
                inside.validate();
            }
            else if ( type.equals( "" ) )
            {
                // stay on same slide
            }
            else
            {
                inside.removeAll();
                inside.add( definitions );
                errorSlide();
                inside.validate();
            }
        }
        else if ( e.getActionCommand().equals( "c2/a3" ) )
        {
            String type = (String)answers.getSelectedItem();
            if ( type.equals( "Yes" ) )
            {
                if ( twoTtest || two )
                {
                    inside.removeAll();
                    inside.add( definitions );
                    a3Slide();
                    inside.validate();
                }
                else
                {
                    inside.removeAll();
                    inside.add( definitions );
                    resultSlide();
                    inside.validate();
                }
            }
            else if ( type.equals( "" ) )
            {
                // stay on same slide
            }
            else
            {
                inside.removeAll();
                inside.add( definitions );
                errorSlide();
                inside.validate();
            }
        }
        else if ( e.getActionCommand().equals( "a3" ) )
        {
            String type = (String)answers.getSelectedItem();
            if ( type.equals( "Yes" ) )
            {
                inside.removeAll();
                inside.add( definitions );
                resultSlide();
                inside.validate();

            }
            else if ( type.equals( "" ) )
            {
                // stay on same slide
            }
            else
            {
                inside.removeAll();
                inside.add( definitions );
                errorSlide();
                inside.validate();
            }
        }
        else if ( e.getActionCommand().equals( "exit" ) )
        {
            System.exit( 0 );
        }
        else if ( e.getActionCommand().equals( "trueMean" ) )
        {
            try
            {
                double value = Double.parseDouble( textFile.getText().trim() );
                catNumbers.add( value );
                // go to assumptions
                inside.removeAll();
                inside.add( definitions );
                boxPlotSlide();
                inside.validate();

            }
            catch ( NumberFormatException ex )
            {
                JOptionPane.showMessageDialog( this,
                    "You must enter in a number",
                    "Cannot read user input",
                    JOptionPane.ERROR_MESSAGE );
            }
        }
    }
}
