package SudokuGameCode;

import java.awt.Point;
import java.awt.Rectangle;

import org.junit.*;

import static org.junit.Assert.*;
import junit.framework.JUnit4TestAdapter;

import org.junit.Test;

import SudokuGUI.ButtonPanel;
import SudokuGUI.GameFrame;
import SudokuGUI.SudokuCell;


/**
 * This class is the JUnit test for the code. It checks for any errors
 * 
 * @author Eric Cheng
 * @author Kabir Kapoor
 * @date 5/23/2015
 * @author Assignment - JMCh00_SudokuFinal
 */
public class JUSudokuGameTest
{
    public int[][] Medium1 = { { 0, 0, 0, 0, 8, 2, 0, 0, 0 },
        { 0, 8, 2, 9, 0, 0, 4, 0, 0 }, { 3, 9, 0, 0, 0, 4, 6, 0, 0 },
        { 5, 0, 0, 0, 2, 0, 9, 0, 1 }, { 0, 0, 7, 0, 0, 0, 2, 0, 0 },
        { 1, 0, 0, 4, 0, 5, 0, 0, 0, 6 }, { 0, 0, 9, 8, 0, 0, 0, 6, 4 },
        { 0, 0, 6, 0, 0, 3, 7, 8, 0 }, { 0, 0, 0, 2, 4, 0, 0, 0, 0 } };

    public int[][] finalMedium1 = { { 7, 4, 1, 6, 8, 2, 5, 9, 3 },
        { 6, 8, 2, 9, 3, 5, 4, 1, 7 }, { 3, 9, 5, 7, 1, 4, 6, 2, 8 },
        { 5, 6, 8, 4, 2, 7, 9, 3, 1 }, { 9, 3, 7, 1, 6, 8, 2, 4, 5 },
        { 1, 2, 4, 3, 5, 9, 8, 7, 6 }, { 2, 5, 9, 8, 7, 1, 3, 6, 4 },
        { 4, 1, 6, 5, 9, 3, 7, 8, 2 }, { 8, 7, 3, 2, 4, 6, 1, 5, 9 } };


    // Action tests

    @Test
    public void ActionConstructor()
    {
        Action act = new Action( "login" );
        String toStr = act.toString();
        // assertTrue("<< Invalid Action Constructor >>", toStr.contains(
        // "String serialCode: " + "login" ));
        // by default works
    }


    @Test
    public void ActionGetSerialCode()
    {
        Action act = new Action( "login" );
        assertNotNull( act.getSerialCode() );
    }


    @Test
    public void ActionGetActionState()
    {
        Action act = new Action( "login", "valid" );
        assertNotNull( act.getActionState() );
    }


    // AIBoard Tests
    @Test
    public void AIBoardConstructor()
    {
        AIBoard board = new AIBoard( Medium1, finalMedium1 );
        assertNotNull( board.normalConf );
        assertNotNull( board.normalFinalConf );
    }


    @Test
    public void AIsetAiBool()
    {
        AIBoard board = new AIBoard( Medium1, finalMedium1 );
        board.setAiSolveBoolTrue();
        assertTrue( board.getAiSolveBool() );
    }


    @Test
    public void AIgetBool()
    {
        AIBoard board = new AIBoard( Medium1, finalMedium1 );
        assertNotNull( board.getAiSolveBool() );
    }


    @Test
    public void AIsetframe()
    {
        AIBoard board = new AIBoard( Medium1, finalMedium1 );
        // by default run through checks
    }


    @Test
    public void AisolveNulltest() throws Exception
    {
        AIBoard board = new AIBoard( Medium1, finalMedium1 );
        // board.solve( 0, 0 ); by exception
        assertNotNull( board.normalConf );
    }


    @Test
    public void inspectTests()
    {
        AIBoard board = new AIBoard( Medium1, finalMedium1 );
        assertFalse( board.inspectRow( 0, 0 ) );
        assertFalse( board.inspectCol( 0, 0 ) );
        assertFalse( board.inspectBox( 0, 0, 0 ) );
    }


    // Matcher tests
    @Test
    public void matcherConstructorandButton()
    {
        Matcher m = new Matcher( new ButtonPanel( null, null, null ) );
        assertNotNull( m.getButtonPanel() );
    }


    @Test
    public void playerBoardConstructor()
    {
        PlayerBoard pb = new PlayerBoard( Medium1, finalMedium1 );
        assertNotNull( pb.initialConfiguration );
        assertNotNull( pb.finalConfiguration );
    }


    @Test
    public void pointDataTest()
    {
        PlayerBoard pb = new PlayerBoard( Medium1, finalMedium1 );
        assertNotNull( pb.getPointData() );
    }


    @Test
    public void setNotGetPointsTest()
    {
        PlayerBoard pb = new PlayerBoard( Medium1, finalMedium1 );
        pb.setNotGetPoints();
        assertFalse( pb.canGetPoints() );
    }


    @Test
    public void canGetPointsTest()
    {
        PlayerBoard pb = new PlayerBoard( Medium1, finalMedium1 );
        assertTrue( pb.canGetPoints() );
    }


    @Test
    public void transposeTest()
    {
        PlayerBoard pb = new PlayerBoard( Medium1, finalMedium1 );
        assertNotNull( pb.transpose( Medium1 ) );
    }


    @Test
    public void setTest()
    {
        PlayerBoard pb = new PlayerBoard( Medium1, finalMedium1 );
        pb.set( 2 );
        assertNotNull( pb.puzzleWidth );
    }


    @Test
    public void initTest()
    {
        PlayerBoard pb = new PlayerBoard( Medium1, finalMedium1 );
        pb.init();
        assertNotNull( pb.getCells()[0][0] );
    }


    @Test
    public void allInitsTest()
    {
        PlayerBoard pb = new PlayerBoard( Medium1, finalMedium1 );
        pb.setAllInitials();
        assertNotNull( pb.cells[0][0].getSupposedValue() );
    }


    @Test
    public void isSetValuesTest()
    {
        PlayerBoard pb = new PlayerBoard( Medium1, finalMedium1 );
        assertNotNull( pb.isSetValues() );
    }


    @Test
    public void setSetValuesTest()
    {
        PlayerBoard pb = new PlayerBoard( Medium1, finalMedium1 );
        pb.setSetValues( true );
        assertTrue( pb.isSetValues() );
    }


    @Test
    public void getCellsNullTest()
    {
        PlayerBoard pb = new PlayerBoard( Medium1, finalMedium1 );
        assertNotNull( pb.getCells() );
    }


    @Test
    public void getTests()
    {
        PlayerBoard pb = new PlayerBoard( Medium1, finalMedium1 );
        assertNotNull( pb.getDrawWidth() );
        assertNotNull( pb.getPuzzleWidth() );
        assertNotNull( pb.getSudokuCell( new Point() ) );
        assertNotNull( pb.getSmallestPossibleValuesList() );
    }


    @Test
    public void getSmallestTest()
    {
        PlayerBoard pb = new PlayerBoard( Medium1, finalMedium1 );
        assertNotNull( pb.getSmallestPossibleValuesList() );
    }


    @Test
    public void removePossibleValueTest()
    {
        PlayerBoard pb = new PlayerBoard( Medium1, finalMedium1 );
        pb.removePossibleValue( pb.getCells()[0][0] );
        assertNotNull( pb.getCells()[0][0].getPossibleValues() );
    }


    @Test
    public void isInTest()
    {
        PlayerBoard pb = new PlayerBoard( Medium1, finalMedium1 );
        assertTrue( pb.isIncomplete() );
        assertFalse( pb.isInaccurate() );
    }


    @Test
    public void getSinglePossibleValueTest()
    {
        PlayerBoard pb = new PlayerBoard( Medium1, finalMedium1 );
        assertNull( pb.getSinglePossibleValue() );
    }


    // Point data tests
    @Test
    public void pdConstructorTest()
    {
        PointData pd = new PointData();
        assertNotNull( pd.playerPoints );
    }


    @Test
    public void getPPDataTest()
    {
        PointData pd = new PointData();
        assertNotNull( pd.getPlayerPointsData() );
    }


    @Test
    public void addToPlayerTest()
    {
        PointData pd = new PointData();
        pd.addToPlayer( ( new Action( "match", "valid" ) ) );
        assertNotNull( pd.getTotalPoints() );
    }


    @Test
    public void gtPTest()
    {
        PointData pd = new PointData();
        assertNotNull( pd.getTotalPoints() );
        // points should be set at nonnull zero at end
    }


    // SudokuCell Tests

    @Test
    public void scconstTest()
    {
        SudokuCell sc = new SudokuCell();
        assertNotNull( sc.maxValue );
    }


    @Test
    public void scGetterTests()
    {
        SudokuCell sc = new SudokuCell();
        assertNull( sc.getCellLocation() );
        assertNotNull( sc.getPossibleValues() );
        assertNotNull( sc.getSupposedValue() );
        sc.setBounds( new Rectangle() );
        assertNotNull( sc.getBounds() );
    }

}
