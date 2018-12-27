package SudokuGameCode;

/**
 * This class basically is used to setup the puzzle that the player is going to
 * play on: the frames, the model of the type of board the player is playing,
 * and the different buttons the players can access to start the game, get
 * hints, or find the solution.
 * 
 * @author Eric Cheng
 * @author Kabir Kapoor
 * @date 5/23/2015
 * @author Assignment - JMCh00_SudokuFinal
 */
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

import SudokuGUI.SudokuCell;


/**
 * This board holds all the parts of the puzzle for the player to solve
 * 
 * @author Eric Cheng
 * @author Kabir Kapoor
 * @date 5/23/2015
 * @author Assignment - JMCh00_SudokuFinal
 */
public class PlayerBoard
{

    public boolean canGetPoints;

    private boolean isSetValues;

    public int drawWidth;

    protected int puzzleWidth;

    private int[][] cellPosition = { { 1, 4, 7, 1, 4, 7, 1, 4, 7 },
        { 2, 5, 8, 2, 5, 8, 2, 5, 8 }, { 3, 6, 9, 3, 6, 9, 3, 6, 9 },
        { 1, 4, 7, 1, 4, 7, 1, 4, 7 }, { 2, 5, 8, 2, 5, 8, 2, 5, 8 },
        { 3, 6, 9, 3, 6, 9, 3, 6, 9 }, { 1, 4, 7, 1, 4, 7, 1, 4, 7 },
        { 2, 5, 8, 2, 5, 8, 2, 5, 8 }, { 3, 6, 9, 3, 6, 9, 3, 6, 9 } };

    protected SudokuCell[][] cells;

    protected int[][] initialConfiguration;

    protected int[][] finalConfiguration;

    protected PointData pointdata = new PointData();


    /**
     * Constructor
     * 
     * @param puzzleToSolve
     *            = the new problem board
     * @param puzzleSolved
     *            = the solution
     */
    public PlayerBoard( int[][] puzzleToSolve, int[][] puzzleSolved )
    {
        initialConfiguration = transpose( puzzleToSolve );
        finalConfiguration = transpose( puzzleSolved );
        canGetPoints = true;
        this.drawWidth = 80;
        this.puzzleWidth = 9;
        this.cells = new SudokuCell[puzzleWidth][puzzleWidth];
        set( puzzleWidth );
    }


    /**
     * @return = gets the point data
     */
    public PointData getPointData()
    {
        return this.pointdata;
    }

    /**
     * @return = see if points could be aquired
     */
    public boolean canGetPoints()
    {
        return this.canGetPoints;
    }

    /**
     * @return = sets not to get points
     */
    public void setNotGetPoints()
    {
        this.canGetPoints = false;
    }


    /**
     * Switches the puzzle so that it works on the GUI coordinate system
     * 
     * @param isqArray
     *            = gets array
     * @return Transposed array
     */
    public int[][] transpose( int[][] isqArray )
    {
        int transpose[][] = new int[isqArray.length][isqArray[0].length];
        {
            for ( int i = 0; i < isqArray.length; i++ )
            {
                for ( int j = 0; j < isqArray[0].length; j++ )
                {
                    transpose[j][i] = isqArray[i][j];
                }
            }
        }
        return transpose;
    }


    /**
     * Sets all the numbers in the cell
     * 
     * @param puzzleWidth
     *            = Gets the width of the puzzle
     */
    public void set( int puzzleWidth )
    {
        for ( int i = 0; i < puzzleWidth; i++ )
        {
            for ( int j = 0; j < puzzleWidth; j++ )
            {
                cells[i][j] = new SudokuCell();
                cells[i][j].setCellLocation( new Point( i, j ) );
            }
        }
    }


    /**
     * Gets the initial state of the puzzle
     */
    public void init()
    {
        for ( int i = 0; i < puzzleWidth; i++ )
        {
            for ( int j = 0; j < puzzleWidth; j++ )
            {
                cells[i][j].init( puzzleWidth );
            }
        }
    }


    /**
     * Sets the initial configuration of the game
     */
    public void setAllInitials()
    {
        for ( int i = 0; i < puzzleWidth; i++ )
        {
            for ( int j = 0; j < puzzleWidth; j++ )
            {
                if ( initialConfiguration[i][j] != 0 )
                {
                    cells[i][j].setInitialValue( initialConfiguration[i][j] );
                    this.removePossibleValue( cells[i][j] );
                }
                cells[i][j].setSupposedValue( finalConfiguration[i][j] );
            }
        }
    }


    /**
     * @return true if value is set; false if not
     */
    public boolean isSetValues()
    {
        return isSetValues;
    }


    /**
     * Check if value is set
     * 
     * @param isSetValues
     *            = set value
     */
    public void setSetValues( boolean isSetValues )
    {
        this.isSetValues = isSetValues;
    }


    /**
     * @return = gets a copy of the board
     */
    public SudokuCell[][] getCells()
    {
        SudokuCell[][] cellcopy = new SudokuCell[puzzleWidth][puzzleWidth];
        for ( int i = 0; i < puzzleWidth; i++ )
        {
            for ( int j = 0; j < puzzleWidth; j++ )
            {
                cellcopy[i][j] = cells[i][j].copy();
            }
        }
        return cellcopy;
    }


    /**
     * Gets layout of the cell
     * 
     * @param cells
     *            = A given sudoku cell
     */
    public void setCells( SudokuCell[][] cells )
    {
        for ( int i = 0; i < puzzleWidth; i++ )
        {
            for ( int j = 0; j < puzzleWidth; j++ )
            {
                this.cells[i][j] = cells[i][j].copy();
            }
        }
    }


    /**
     * @param cell
     *            = given sudoku cell
     */
    public void setCell( SudokuCell cell )
    {
        Point point = cell.getCellLocation();
        this.cells[point.x][point.y] = cell;
    }


    /**
     * @return = returns draw width
     */
    public int getDrawWidth()
    {
        return drawWidth;
    }


    /**
     * @return = returns puzzle width
     */
    public int getPuzzleWidth()
    {
        return puzzleWidth;
    }


    /**
     * Searches for Sudoku cell location
     * 
     * @param point
     *            = gets point
     * @return = returns the value of the cell
     */
    public SudokuCell getSudokuCellLocation( Point point )
    {
        for ( int i = 0; i < puzzleWidth; i++ )
        {
            for ( int j = 0; j < puzzleWidth; j++ )
            {
                if ( cells[i][j].contains( point ) )
                {
                    return cells[i][j];
                }
            }
        }
        return null;
    }


    /**
     * @param cellPosition
     *            = gets cell position
     * @return = the sudoku cell
     */
    public SudokuCell getSudokuCell( Point cellPosition )
    {
        return cells[cellPosition.x][cellPosition.y];
    }


    // public void setSudokuCell(SudokuCell sudokuCell, Point point) {
    // cells[point.x][point.y] = sudokuCell;
    // }

    /**
     * Gets the smallest possibele value
     * 
     * @return = smallest value
     */
    public SudokuCell getSmallestPossibleValuesList()
    {
        int minCount = Integer.MAX_VALUE;
        Point point = new Point( -1, -1 );
        for ( int i = 0; i < puzzleWidth; i++ )
        {
            for ( int j = 0; j < puzzleWidth; j++ )
            {
                if ( cells[i][j].getValue() <= 0 )
                {
                    int count = cells[i][j].getPossibleValuesCount();
                    if ( ( count > 1 ) && ( count < minCount ) )
                    {
                        minCount = count;
                        point.x = i;
                        point.y = j;
                    }
                    if ( count == 2 )
                    {
                        return cells[i][j];
                    }
                }
            }
        }
        if ( ( point.x < 0 ) || ( point.y < 0 ) )
        {
            return null;
        }
        else
        {
            return cells[point.x][point.y];
        }
    }


    /**
     * Removes a value from the cell
     * 
     * @param cell
     *            = contains value to be removed
     */
    public void removePossibleValue( SudokuCell cell )
    {
        int value = cell.getValue();
        Point point = cell.getCellLocation();

        for ( int i = 0; i < puzzleWidth; i++ )
        {
            cells[i][point.y].removePossibleValue( value );
        }
        for ( int j = 0; j < puzzleWidth; j++ )
        {
            cells[point.x][j].removePossibleValue( value );
        }

        int ii = point.x / 3;
        int jj = point.y / 3;
        for ( int i = ii * 3; i < ( ii + 1 ) * 3; i++ )
        {
            for ( int j = jj * 3; j < ( jj + 1 ) * 3; j++ )
            {
                cells[i][j].removePossibleValue( value );
            }
        }
    }


    /**
     * @return true if it's incomplete
     */
    public boolean isIncomplete()
    {
        for ( int i = 0; i < puzzleWidth; i++ )
        {
            for ( int j = 0; j < puzzleWidth; j++ )
            {
                if ( cells[i][j].getValue() <= 0 )
                {
                    return true;
                }
            }
        }
        return false;
    }


    /**
     * @return true if it's inaccurate
     */
    public boolean isInaccurate()
    {
        for ( int i = 0; i < puzzleWidth; i++ )
        {
            for ( int j = 0; j < puzzleWidth; j++ )
            {
                if ( ( cells[i][j].getValue() <= 0 )
                    && ( cells[i][j].getPossibleValuesCount() <= 0 ) )
                {
                    return true;
                }
            }
        }
        return false;
    }


    /**
     * @return = single possible value
     */
    public Point getSinglePossibleValue()
    {
        for ( int i = 0; i < puzzleWidth; i++ )
        {
            for ( int j = 0; j < puzzleWidth; j++ )
            {
                if ( cells[i][j].getValue() <= 0 )
                {
                    if ( cells[i][j].getPossibleValuesCount() == 1 )
                    {
                        return cells[i][j].getCellLocation();
                    }
                }
            }
        }
        return null;
    }


    /**
     * Draws out the puzzle
     * 
     * @param g
     *            = Graphics object
     */
    public void draw( Graphics g )
    {
        int x = 0;
        for ( int i = 0; i < puzzleWidth; i++ )
        {
            int y = 0;
            for ( int j = 0; j < puzzleWidth; j++ )
            {
                Rectangle r = new Rectangle( x, y, drawWidth, drawWidth );
                cells[i][j].setBounds( r );
                cells[i][j].draw( g, x, y, drawWidth, cellPosition[i][j] );
                y += drawWidth;
            }
            x += drawWidth;
        }
    }

}