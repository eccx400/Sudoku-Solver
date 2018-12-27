package SudokuGameCode;

import java.util.*;


/**
 * This class basically does what its name suggests: it stores all the moves the
 * user and the AI will make in the form of Queues, making them quick and easy
 * to access without much confusion or troubles.
 * 
 * @author Eric Cheng
 * @author Kabir Kapoor
 * @date 5/23/2015
 * @author Assignment - JMCh00_SudokuFinal
 */
public class PointData
{
    public Queue<Integer> playerPoints;


    /**
     * Constructor
     */
    public PointData()
    {
        playerPoints = new LinkedList<Integer>();
    }


    /**
     * @return = returns a linkedlist
     */
    public LinkedList<Integer> getPlayerPointsData()
    {
        return (LinkedList<Integer>)this.playerPoints;
    }


    /**
     * This is basically the point decider. It shows what points the user can
     * earn or lose under certain circumstances.
     * 
     * @param act
     *            = new Action
     */
    public void addToPlayer( Action act )
    {
        if ( act.getSerialCode().equals( "insertNumber" )
            && act.getActionState().equals( "valid" ) )
        {
            playerPoints.add( 100 );
        }
        else if ( act.getSerialCode().equals( "insertNumber" )
            && act.getActionState().equals( "invalid" ) )
        {
            playerPoints.add( -200 );
        }
        else if ( act.getSerialCode().equals( "match" )
            && act.getActionState().equals( "valid " ) )
        {
            playerPoints.add( 30 );
        }
        else if ( act.getSerialCode().equals( "match" )
            && act.getActionState().equals( "invalid " ) )
        {
            playerPoints.add( -10 );
        }

    }


    /**
     * @return = Gets total amount of points
     */
    public int getTotalPoints()
    {
        Iterator<Integer> iter = playerPoints.iterator();
        int total = 0;
        while ( iter.hasNext() )
        {
            total += iter.next().intValue();
        }
        return total;
    }

}
