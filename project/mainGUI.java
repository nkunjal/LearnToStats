import javax.swing.*;


/**
 * This class is the one the user runs in order to go through all of the
 * questions.
 *
 * @author Neha Kunjal
 * @version May 25, 2016
 * @author Period: 3
 * @author Assignment: Statistics_Final
 *
 * @author Sources: none
 */
public class mainGUI
{
    public static void main( String[] args )
    {
        Questions questions = new Questions( "test" );
        questions.setBounds( 0, 0, 600, 700 );
        questions.setVisible( true );
        questions.setResizable( false );
    }
}
