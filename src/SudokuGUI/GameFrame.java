package SudokuGUI;

import java.awt.FlowLayout;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

import SudokuGameCode.AIBoard;
import SudokuGameCode.Matcher;
import SudokuGameCode.PlayerBoard;


/**
 * This class basically covers all of the visible parts of the GUI interface
 * except for the Stopwatch clock, which is set in its own specific class. The
 * layout is set as follows: the “West” holds the player’s board, in which the
 * player can interact and do the game; the center holds the ‘Start Game’ button
 * in which the user can start his game, and the ‘East’ holds the AI board which
 * the user cannot interact with but where the AI is trying to solve its own
 * puzzle.
 * 
 * @author Eric Cheng
 * @author Kabir Kapoor
 * @date 5/23/2015
 * @author Assignment - JMCh00_SudokuFinal
 */
public class GameFrame
{
    private ButtonPanel buttonPanel;

    private JFrame frame;

    private SingleSudokuPanel sudokuPanel;

    private SingleSudokuPanel AIsudokuPanel;

    private SingleSudokuPanel AIPanel;

    private PlayerBoard model;

    private AIBoard aimodel;

    private Stopwatch clock = new Stopwatch();

    private Matcher m;


    /**
     * Constructor
     * 
     * @param model
     *            = Gets kind of model for player
     * @param Aimodel
     *            = Gets kind of model for AI
     */
    public GameFrame( PlayerBoard model, AIBoard Aimodel )
    {
        this.model = model;
        this.aimodel = Aimodel;
        createPartControl();

    }


    /**
     * This is the control for the frame; the layout and setup are placed here.
     */
    private void createPartControl()
    {

        buttonPanel = new ButtonPanel( this, model, aimodel );
        sudokuPanel = new SingleSudokuPanel( this, model, buttonPanel );
        AIsudokuPanel = new SingleSudokuPanel( this, aimodel, buttonPanel );

        frame = new JFrame();
        frame.setTitle( "Sudoku Game" );
        frame.setDefaultCloseOperation( JFrame.DO_NOTHING_ON_CLOSE );
        frame.addWindowListener( new WindowAdapter()
        {
            @Override
            public void windowClosing( WindowEvent event )
            {
                exitProcedure();
            }
        } );

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout( new BoxLayout( mainPanel, BoxLayout.X_AXIS ) );
        mainPanel.add( sudokuPanel );

        JPanel holderPanel = new JPanel();
        holderPanel.setLayout( new FlowLayout() );
        holderPanel.add( buttonPanel.getPanel() );
        mainPanel.add( holderPanel );

        JPanel AIPanel = new JPanel();
        AIPanel.setLayout( new FlowLayout() );
        AIPanel.add( AIsudokuPanel );
        AIPanel.add( holderPanel );

        frame.setLayout( new FlowLayout() );
        frame.add( mainPanel );
        frame.add( holderPanel );
        frame.add( AIPanel );
        frame.setBounds( getBounds() );
        frame.pack();
        frame.setVisible( true );
    }


    /**
     * Exits the code
     */
    public void exitProcedure()
    {
        frame.dispose();
        System.exit( 0 );
    }


    /**
     * @return = Returns the bounds of the Frame
     */
    protected Rectangle getBounds()
    {
        Rectangle f = frame.getBounds();
        Rectangle w = GraphicsEnvironment.getLocalGraphicsEnvironment()
            .getMaximumWindowBounds();
        f.x = ( w.width - f.width ) / 2;
        f.y = ( w.height - f.height ) / 2;
        return f;
    }


    /**
     * @return = Returns the frame
     */
    public JFrame getFrame()
    {
        return frame;
    }


    /**
     * @return = returns the sudoku panel
     */
    public JPanel getSudokuPanel()
    {
        return sudokuPanel;
    }


    /**
     * Repaints and refreshes to a new screen
     */
    public void repaintSudokuPanel()
    {
        sudokuPanel.repaint();
    }

}