package SudokuGameCode;

/**
 * This class is separated into different methods that see if the actions are
 * valid and can be used in the code. Methods such as setActionState and
 * setRunstate help to see if the action is legitimate; a string is returned if
 * it is right, and the code continues to function. It is important that several
 * serialcodes are set up in an array. These codes provide the string password
 * for communication between different classes. Additionally, the Action class
 * must contain a parameter for the state of an action (true/ false or
 * valid/invalid). These states can be stored as strings or booleans, but they
 * must be implemented in a separate constructor.
 * 
 * @author Eric Cheng
 * @author Kabir Kapoor
 * @date 5/23/2015
 * @author Assignment - JMCh00_SudokuFinal
 */
public class Action
{
    private String serialCode;

    private String[] validSerialCodes = { "login", "insertNumber", "pause",
        "match" };

    private String[] validActionStates = { "valid", "invalid" };

    private String actionState;


    /**
     * Constructor
     * 
     * @param s = Gets an Action
     */
    public Action( String s )
    {
        for ( String validSerialCode : validSerialCodes )
        {
            if ( s.equals( validSerialCode ) )
            {
                serialCode = s;
            }
        }
        actionState = "not assigned";
    }

    /**
     * Constructor
     * 
     * @param s = Gets an Action
     * @param state = Gets the state of the action
     */
    public Action( String s, String state )
    {
        for ( String validSerialCode : validSerialCodes )
        {
            if ( s.equals( validSerialCode ) )
            {
                serialCode = s;
            }
        }
        actionState = state;

    }


    /**
     * @return = gets the serial code
     */
    public String getSerialCode()
    {
        return serialCode;
    }

    /**
     * @return = gets the state of action
     */
    public String getActionState()
    {
        return actionState;
    }
}
