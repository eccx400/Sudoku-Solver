package SudokuGameCode;

import SudokuGUI.ButtonPanel;


/**
 * This class allows for the comparison of the AI and user sudoku puzzles. Under
 * the condition that if the user gets lucky, one of his squares will be the
 * same as an AI square, and he gets some points. We took this idea from the
 * game battleship; the user can gain some points or at least an advantage if it
 * manages to figure out or luck on what the AI is doing, and will get extra
 * bonus points that can be added on to his or her high score. It is really a
 * special feature. To properly implement the class, a reference to a
 * buttonpanel must be made in order to gain access to the call for
 * cross-board-checking. Furthermore, a constructor must be implemented which
 * takes the buttonpanel into account.
 * 
 * @author Eric Cheng
 * @author Kabir Kapoor
 * @date 5/23/2015
 * @author Assignment - JMCh00_SudokuFinal
 */
public class Matcher
{
    private ButtonPanel buttons;


    /**
     * Constructor
     * 
     * @param buttons = gets the buttons needed to see if they matcj
     */
    public Matcher( ButtonPanel buttons )
    {
        this.buttons = buttons;
    }


    /**
     * @return = Gets the button panel
     */
    public ButtonPanel getButtonPanel()
    {
        return this.buttons;
    }
}
