package SudokuGUI;

import javax.swing.JToggleButton;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


/**
 * SetToggleButtons: This method is used to store the values of the Button array
 * in a JToggleButton. The difference between a JButton and a JToggleButton is
 * that the JToggleButton, when pressed, stays pressed even when the mouse is
 * removed. The JButton, on the other hand, returns back to normal after the
 * mouse is removed from the button. The reason we want the JToggleButton is
 * because that when we start the game, we want the game to continue until
 * either the AI or the user has won, and not just stop right after the mouse
 * leaves the start game button.
 * 
 * @author Eric Cheng
 * @author Kabir Kapoor
 * @date 5/23/2015
 * @author Assignment - JMCh00_SudokuFinal
 */
public class ToggleListener implements ChangeListener
{

    private JToggleButton[] toggleButtons;


    /**
     * Sets the toggled buttons = Does not bounce back when mouse removed
     * 
     * @param buttons
     *            = the buttons
     */
    public void setToggleButtons( JToggleButton... buttons )
    {
        toggleButtons = new JToggleButton[buttons.length];
        for ( int i = 0; i < buttons.length; i++ )
        {
            toggleButtons[i] = buttons[i];
        }
    }


    @Override
    public void stateChanged( ChangeEvent event )
    {
        JToggleButton toggleButton = (JToggleButton)event.getSource();
        if ( toggleButton.isSelected() )
        {
            for ( JToggleButton button : toggleButtons )
            {
                if ( !button.equals( toggleButton ) )
                {
                    button.setSelected( false );
                }
            }
        }
    }
}