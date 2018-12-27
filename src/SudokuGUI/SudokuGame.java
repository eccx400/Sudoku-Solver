package SudokuGUI;

import javax.swing.SwingUtilities;

import SudokuGameCode.AIBoard;
import SudokuGameCode.PlayerBoard;


/**
 * This class basically starts the sudoku game; the user runs the code from
 * here.
 * 
 * @author Eric Cheng
 * @author Kabir Kapoor
 * @date 5/23/2015
 * @author Assignment - JMCh00_SudokuFinal
 */
public class SudokuGame implements Runnable
{

    @Override
    /**
     * Runs the method
     */
    public void run()
    {
        LoginArrays arrays = new LoginArrays();
        AIBoard myAiPuzzle = new AIBoard( arrays.Easy1, arrays.finalEasy1 );
        GameFrame myGameFrame = new GameFrame( new PlayerBoard( arrays.Medium2,
            arrays.finalMedium2 ), myAiPuzzle );
        myAiPuzzle.setGameFrame( myGameFrame );
    }


    /**
     * Testing use only
     * 
     * @param args
     *            = arguments array
     */
    public static void main( String[] args )
    {
        SwingUtilities.invokeLater( new SudokuGame() );
    }
}
