package SudokuGameCode;

import SudokuGUI.GameFrame;


/**
 * This class sets up the Sudoku puzzle for the AI to solve
 * 
 * @author Eric Cheng
 * @author Kabir Kapoor
 * @date 5/23/2015
 * @author Assignment - JMCh00_SudokuFinal
 */
public class AIBoard extends PlayerBoard
{

    public int[][] normalConf;

    public int[][] normalFinalConf;

    private GameFrame frame;

    private boolean AiSolvebool = false;

    public boolean matchbool = false;


    /**
     * Constructor
     * 
     * @param puzzleToSolve
     *            = gets the puzzle to be solved
     * @param puzzleSolved
     *            = gets the solution
     */
    public AIBoard( int[][] puzzleToSolve, int[][] puzzleSolved )
    {
        super( puzzleToSolve, puzzleSolved );
        normalConf = super.transpose( this.initialConfiguration );
        normalFinalConf = super.transpose( this.finalConfiguration );
    }


    public void setAiSolveBoolTrue()
    {
        this.AiSolvebool = true;
    }


    public boolean getAiSolveBool()
    {
        return this.AiSolvebool;
    }


    /**
     * Sets game frame
     * 
     * @param toSet
     *            = the frame to be set
     */
    public void setGameFrame( GameFrame toSet )
    {
        frame = toSet;
    }


    /**
     * Recursively solves the board, starting at a board cell
     * 
     * @param row
     *            = row of cell
     * @param col
     *            = column of cell
     * @throws Exception
     *             thrown if error detected
     */
    public void solve( int row, int col ) throws Exception
    {
        if ( row > 8 )
            throw new Exception( "Solution Identified" );

        if ( normalConf[row][col] != 0 )
        {
            matchbool = true;
            goOn( row, col );
        }
        else
        {
            for ( int num = 1; num < 10; num++ )
            {
                if ( inspectRow( row, num ) && inspectCol( col, num )
                    && inspectBox( row, col, num ) )
                {
                    normalConf[row][col] = num;
                    cells[col][row].setValue( num );
                    this.removePossibleValue( cells[col][row] );
                    cells[col][row].clearPossibleValues();
                    frame.repaintSudokuPanel();
                    goOn( row, col );
                }
            }

            normalConf[row][col] = 0;
            cells[col][row].setValue( 0 );

        }
    }


    /**
     * Solves the next piece of the board
     * 
     * @param row
     *            = row of initial cell
     * @param col
     *            = column of initial cell
     * @throws Exception
     *             thrown if error detected
     */
    public void goOn( int row, int col ) throws Exception
    {
        if ( col < 8 )
            solve( row, col + 1 );
        else
            solve( row + 1, 0 );
    }


    // -----------------------------------//
    /**
     * Inspects row for a specified number
     * 
     * @param row
     *            = row checked
     * @param num
     *            = number to be searched for
     * @return true if number is not found
     */
    public boolean inspectRow( int row, int num )
    {
        for ( int col = 0; col < 9; col++ )
            if ( normalConf[row][col] == num )
                return false;

        return true;
    }


    /**
     * Inspects column for a specified number
     * 
     * @param col
     *            = column checked
     * @param num
     *            = number to be searched for
     * @return true if number is not found
     */
    public boolean inspectCol( int col, int num )
    {
        for ( int row = 0; row < 9; row++ )
            if ( normalConf[row][col] == num )
                return false;

        return true;
    }


    /**
     * Inspects column for a specified number
     * 
     * @param row
     *            = row checked
     * @param col
     *            = column checked
     * @param num
     *            = number to be searched for
     * @return true if number is not found
     */
    public boolean inspectBox( int row, int col, int num )
    {
        row = ( row / 3 ) * 3; // returns normalized row value
        col = ( col / 3 ) * 3; // returns normalized column value

        for ( int r = 0; r < 3; r++ )
            for ( int c = 0; c < 3; c++ )
                if ( normalConf[row + r][col + c] == num )
                    return false;

        return true;
    }

}
