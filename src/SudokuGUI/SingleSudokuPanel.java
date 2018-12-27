package SudokuGUI;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import SudokuGameCode.PlayerBoard;


/**
 * This class basically creates a single sudoku panel, which is where the sudoku
 * board should go. The single sudoku panel controls the type of puzzle used,
 * the model, and the buttons that go on the panel. It also implements the mouse
 * listeners and button listeners whose methods are stored in the Toggle and the
 * UserTypeInput classes. The single sudoku panel and the other button panels
 * will together form the SudokuGame class, where the whole game can be started
 * and played.
 * 
 * @author Eric Cheng
 * @author Kabir Kapoor
 * @date 5/23/2015
 * @author Assignment - JMCh00_SudokuFinal
 */
public class SingleSudokuPanel extends JPanel
{

    private static final long serialVersionUID = 1L;

    private GameFrame frame;

    private PlayerBoard model;

    private ButtonPanel buttons;

    boolean bool = true;


    /**
     * Constructor
     * 
     * @param frame = gets sort of frame
     * @param model = gets kind of board
     * @param buttons = gets the buttons
     */
    public SingleSudokuPanel(
        GameFrame frame,
        PlayerBoard model,
        ButtonPanel buttons )
    {
        this.frame = frame;
        this.model = model;
        this.buttons = buttons;
        createPartControl();
    }


    /**
     * If equal, then its true
     * 
     * @param boole = true or false
     */
    public void setBoolean( boolean boole )
    {
        this.bool = boole;
    }


    /**
     * Setup
     */
    private void createPartControl()
    {
        new JPanel();
        int width = model.getDrawWidth() * model.getPuzzleWidth() + 1;
        addMouseListener( new UserTypeInputListener( frame, model, this.buttons ) );
        setPreferredSize( new Dimension( width, width ) );
    }


    @Override
    protected void paintComponent( Graphics g )
    {
        super.paintComponent( g );
        if ( bool )
        {
            model.draw( g );
        }

    }

}