package SudokuGUI;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;

import SudokuGameCode.Action;
import SudokuGameCode.PlayerBoard;


/**
 * This class, in comparison to toggle, stores the Mouse Listeners and the get
 * value methods of the cell than the Button listeners. It provides the same
 * purpose as the ToggleListener Class, as they are both used to aide the
 * SingleSudokuPanel class with functional improvements, such as mouse actions
 * and button actions, and also is used to get the values within the specific
 * cells on the sudoku board.
 * 
 * @author Eric Cheng
 * @author Kabir Kapoor
 * @date 5/23/2015
 * @author Assignment - JMCh00_SudokuFinal
 */
public class UserTypeInputListener implements MouseListener
{

    private GameFrame frame;

    private PlayerBoard model;

    private ButtonPanel buttons;


    /**
     * Constructor
     * 
     * @param frame = Gets the frame
     * @param model = Gets the model of the playerboard
     * @param buttons = Gets buttons
     */
    public UserTypeInputListener(
        GameFrame frame,
        PlayerBoard model,
        ButtonPanel buttons )
    {
        this.frame = frame;
        this.model = model;
        this.buttons = buttons;
    }


    @Override
    public void mouseClicked( MouseEvent event )
    {

    }


    @Override
    public void mouseEntered( MouseEvent event )
    {

    }


    @Override
    public void mouseExited( MouseEvent event )
    {

    }


    @Override
    public void mousePressed( MouseEvent event )
    {
        if ( model.canGetPoints() )
        {
            if ( model.isSetValues() )
            {
                SudokuCell sudokuCell = model.getSudokuCellLocation( event.getPoint() );
                if ( sudokuCell != null )
                {
                    int value = getValue( sudokuCell );
                    if ( value > 0 )
                    {
                        sudokuCell.setValue( value );
                        sudokuCell.setIsInitial( true );
                        model.removePossibleValue( sudokuCell );
                        sudokuCell.clearPossibleValues();
                        frame.repaintSudokuPanel();
                    }
                }
            }
        }
    }


    @Override
    public void mouseReleased( MouseEvent event )
    {

    }


    /**
     * Gets the value of cell
     * 
     * @param sudokuCell = a certain cell
     * @return the value
     */
    private int getValue( SudokuCell sudokuCell )
    {
        int value = 0;
        boolean bool = false;
        while ( value == 0 )
        {
            String inputValue = JOptionPane.showInputDialog( frame.getFrame(),
                "Type a value from 1 to 9" );

            if ( inputValue == null )
            { // Cancel button
                return 0;
            }

            try
            {
                value = Integer.parseInt( inputValue );
                if ( sudokuCell.getSupposedValue() == value )
                {
                    bool = true;
                }
                value = testValue( sudokuCell, value );
            }
            catch ( NumberFormatException e )
            {
                value = 0;
            }
        }
        if ( bool == true )
        {
            model.getPointData().addToPlayer( new Action( "insertNumber",
                "valid" ) );
            if ( model.getPointData().getTotalPoints() > 100 )
            {
                // model.drawUpdated( g, 1, 1 );
            }
        }
        if ( bool == false )
        {
            model.getPointData().addToPlayer( new Action( "insertNumber",
                "invalid" ) );
            this.buttons.changePoints( model.getPointData().getTotalPoints() );
            return 0;
        }
        this.buttons.changePoints( model.getPointData().getTotalPoints() );
        System.out.println( "test" );
        return value;
    }


    /**
     * See if conditions are met
     * 
     * @param sudokuCell = given cell
     * @param value = integer value
     * @return = the test value
     */
    private int testValue( SudokuCell sudokuCell, int value )
    {
        if ( value < 1 || value > 9 )
        {
            value = 0;
        }
        else if ( !sudokuCell.isPossibleValue( value ) )
        {
            value = 0;
        }
        return value;
    }

}