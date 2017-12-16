import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;


/**
 *
 *  This class contains methods to create a window that pops up with the
 *  information the user needs to know about the results of the hypothesis.
 *  It also provides the user interface to enter in data.
 *  
 *  @author  Sinclair, Durga
 *  @version May 25, 2016
 *  @author  Period: 3
 *  @author  Assignment: Statistics_Final
 *
 *  @author  Sources: Power Team
 */
public class OnePropZTestFrame extends JFrame implements ActionListener
{
    private JLabel sdLabel = new JLabel( "Standard Deviation: " );

    private JLabel zLabel = new JLabel( "Z-score: " );

    private JLabel pValLabel = new JLabel( "P-value: " );

    JTextField answer1, answer2, answer3;


    /**
     * constructor
     */
    public OnePropZTestFrame()
    {
        super( "One Proportion Z Test" );

        JLabel testLabel1 = new JLabel( "Inference Test", JLabel.LEFT );
        JLabel greetings = new JLabel( "You are performing a 1 proportion z-test.", JLabel.LEFT );

        JPanel p1 = new JPanel();
        JPanel p2 = new JPanel();
        JPanel p3 = new JPanel();

        JLabel label1 = new JLabel( "Please enter the number of successes in your sample:", JLabel.LEFT );
        JLabel label2 = new JLabel( "Please enter the number of failures in your sample:", JLabel.LEFT );
        JLabel label3 = new JLabel( "Please enter your 'Null Hypothesis' as a proportion:", JLabel.LEFT );

        answer1 = new JTextField( 10 );
        answer2 = new JTextField( 10 );
        answer3 = new JTextField( 10 );

        p1.add( label1 );
        p1.add( answer1 );

        p2.add( label2 );
        p2.add( answer2 );

        p3.add( label3 );
        p3.add( answer3 );

        JPanel outPanel = new JPanel( new GridLayout( 1, 1, 50, 50 ) );
        outPanel.setBorder( new EmptyBorder( 50, 50, 50, 50 ) );

        JButton goButton = new JButton( "go!" );
        goButton.setPreferredSize( new Dimension( 3, 3 ) );
        goButton.addActionListener( this );

        JPanel insidePanel = new JPanel( new GridLayout( 9, 1, 50, 50 ) );
        insidePanel.setBorder( new EmptyBorder( 40, 40, 40, 40 ) );
        insidePanel.add( testLabel1 );
        insidePanel.add( greetings );
        insidePanel.add( p1 );
        insidePanel.add( p2 );
        insidePanel.add( p3 );
        insidePanel.add( goButton );

        insidePanel.add( sdLabel );
        insidePanel.add( zLabel );
        insidePanel.add( pValLabel );

        outPanel.add( insidePanel );

        Container c = getContentPane();
        c.add( outPanel );
    }


        /**
         * This method does the necessary formatting to display values 
         * calculated by our program.
         * 
         * @param ActionEvent e
         */
        public void actionPerformed( ActionEvent e )
        {
            int x = 0; 
            int y = 0; 
            double phat = 0.0;
            try
            {
                x = Integer.parseInt( answer1.getText().trim() );
                y = Integer.parseInt( answer2.getText().trim() );
                phat = Double.parseDouble( answer3.getText().trim() );
            }
            catch ( NumberFormatException ex )
            {
                JOptionPane.showMessageDialog( this,
                    "You must enter in an appropriate number",
                    "Cannot read user input",
                    JOptionPane.ERROR_MESSAGE );
            }
            
            OnePropZTest op = new OnePropZTest( x, y, phat );
            sdLabel.setText( "Standard Deviation: " + op.deviationOrError() );
            zLabel.setText( "Z-score: " + op.testStatistic() );
            pValLabel.setText( "P-value: " + op.calculatePValue() );
        }
    }