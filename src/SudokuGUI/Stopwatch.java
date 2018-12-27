package SudokuGUI;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;


/**
 * This class basically creates a separate interface which holds the time (and
 * also the points) that have passed since the user started the game. It is
 * important because it helps to determine if the user is ahead of the AI. The
 * points were also an interesting addition; there are two separate conditions
 * with the control the points. If the player gets the game wrong, they will
 * lose 20 points, while if they get the number right, they will earn 100
 * points. During the end of the game, the points are then stored and then shown
 * to a user through the toString method, and later the top 10 list that the
 * user can see to compare his or her score with his or her previous scores.
 * 
 * @author Eric Cheng
 * @author Kabir Kapoor
 * @date 5/23/2015
 * @author Assignment - JMCh00_SudokuFinal
 */
public class Stopwatch extends JPanel
{
    private Timer timer;

    private Font myClockFont;

    private JButton startBtn, stopBtn, resetBtn;

    private JLabel text;

    private JPanel topPanel, middlePanel, bottomPanel;

    JLabel points;

    private int clockTick; // number of clock ticks; tick can be 1.0 s or 0.1 s

    private double clockTime; // time in seconds

    private String str;

    public static final int ONE_SEC = 1000; // time step in milliseconds

    public static final int TENTH_SEC = 100;


    /**
     * This is the constructor method. Holds all the setup and mechanisms behind
     * the machine.
     */
    public Stopwatch()
    {
        clockTick = 0; // initial clock setting in clock ticks
        clockTime = ( (double)clockTick ) / 10.0;

        str = new Double( clockTime ).toString();
        myClockFont = new Font( "Serif", Font.PLAIN, 50 );

        text = new JLabel();
        text.setFont( myClockFont );
        text.setText( str );

        points = new JLabel();
        points.setFont( myClockFont );
        points.setText( "Points: 0" ); // Change the point value later

        timer = new Timer( TENTH_SEC, new ActionListener()
        {
            public void actionPerformed( ActionEvent evt )
            {
                clockTick++;
                clockTime = ( (double)clockTick ) / 10.0;
                str = new Double( clockTime ).toString();
                text.setText( str );
            }
        } );
        timer.start();

    }


    /**
     * @return = gets the time
     */
    public double getTime()
    {
        timer.stop();
        return this.clockTime;
    }


    /**
     * @return = gets the amount of points
     */
    public JLabel getPoints()
    {
        return this.points;
    }


    /**
     * Sets up the stopwatch layout; GUI frame
     */
    public void setup()
    {
        topPanel = new JPanel();
        middlePanel = new JPanel();
        bottomPanel = new JPanel();

        topPanel.add( text );
        bottomPanel.add( points );

        this.setLayout( new BorderLayout() );

        add( topPanel, BorderLayout.NORTH );
        add( middlePanel, BorderLayout.CENTER );
        add( bottomPanel, BorderLayout.SOUTH );

        setSize( 300, 400 );

    }

}


/**
 * This class launches the code
 * 
 * @author Eric Cheng
 * @author Kabir Kapoor
 * @date 5/23/2015
 * @author Assignment - JMCh00_SudokuFinal
 */
class WatchLauncher extends JFrame
{
    Stopwatch watch;


    /**
     * Constructor
     */
    public WatchLauncher()
    {
        super( "Stopwatch" );
        Container myPane = getContentPane();
        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );

        watch = new Stopwatch();
        watch.setup();
        myPane.add( watch );
        pack();
        setVisible( true );
    }


    /**
     * Sets the points and updates
     * 
     * @param n = the points to be set
     */
    public void setPoints( int n )
    {
        this.watch.getPoints().setText( "Points: " + n );
        this.repaint();
    }


    /**
     * @return = gets the watch
     */
    public Stopwatch getWatch()
    {
        return this.watch;
    }
}