import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.io.FileNotFoundException;

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
*  @author  Durga
*  @version May 25, 2016
*  @author  Period: 3
*  @author  Assignment: Statistics_Final
*
*  @author  Sources: Power Team
*/
public class TwoSampleTTestFrame extends JFrame implements ActionListener 
{    
    private JLabel seLabel = new JLabel("Standard Error: ");
    private JLabel tLabel = new JLabel("t-value: ");
    private JLabel pValLabel = new JLabel("P-value: ");
    
    JTextField answer1, answer2;
    
    /**
     * constructor
     */
    public TwoSampleTTestFrame()
    {
        super("Two Sample T Test");
        
        //ButtonListener buttListener = new ButtonListener();

        JLabel testLabel1  = new JLabel("Inference Test", JLabel.LEFT);
        JLabel greetings = new JLabel("You are performing a 2 sample t-test.", JLabel.LEFT); 
        
        JPanel p1 = new JPanel();
        JPanel p2 = new JPanel();
        
        JLabel label1 = new JLabel("Please enter the name of the first text file with yor data:", JLabel.LEFT);
        JLabel label2 = new JLabel("Please enter the name of the second text file with yor data:", JLabel.LEFT);
        
        answer1 = new JTextField(10);
        answer2 = new JTextField(10);
        
        p1.add( label1 );
        p1.add( answer1 );
        
        p2.add( label2 );
        p2.add( answer2 );
        
        
        JPanel outPanel = new JPanel(new GridLayout(1, 1, 50, 50));
        outPanel.setBorder(new EmptyBorder(50, 50, 50, 50));
        
        JButton goButton = new JButton("go!");
        goButton.setPreferredSize(new Dimension(3, 3));
        goButton.addActionListener(this);
        
        JPanel insidePanel = new JPanel(new GridLayout(9, 1, 50, 50));
        insidePanel.setBorder(new EmptyBorder(40, 40, 40, 40));
        insidePanel.add( testLabel1 );
        insidePanel.add( greetings );
        insidePanel.add( p1 );
        insidePanel.add( p2 );
        insidePanel.add( goButton );
        
        insidePanel.add( seLabel );
        insidePanel.add( tLabel );
        insidePanel.add( pValLabel );
        
        outPanel.add( insidePanel );
        
        Container c = getContentPane();
        c.add(outPanel);
    }


        TwoSampleTTest op;
        /**
         * This method does the necessary formatting to display values 
         * calculated by our program.
         * 
         * @param ActionEvent e
         */
        public void actionPerformed(ActionEvent e)
      {
                              
        try
        {
            String x = answer1.getText().toString();
            String y = answer2.getText().toString();
            op = new TwoSampleTTest(new Stats(x), new Stats(y));
        }
        catch ( FileNotFoundException e1 )
        {
            {
                JOptionPane.showMessageDialog( this,
                    "Make sure your file is attached to your program"
                    + " and not the desktop. Otherwise the program won't run",
                    "Cannot read user input",
                    JOptionPane.ERROR_MESSAGE );
            }
        }
        
        catch (NumberFormatException ex )
        {
            JOptionPane.showMessageDialog( this,
                "You must enter in an appropriate number",
                "Cannot read user input",
                JOptionPane.ERROR_MESSAGE );
        }

          seLabel.setText( "Standard Error: " + op.deviationOrError() );
          tLabel.setText( "T-value: " + op.testStatistic() );
          pValLabel.setText( "P-value: " + op.calculatePValue() );
      }
    }