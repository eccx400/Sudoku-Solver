package SudokuGUI;

import java.awt.Component;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import SudokuGameCode.Action;
import SudokuGameCode.AIBoard;
import SudokuGameCode.Matcher;
import SudokuGameCode.PlayerBoard;


/**
 * This class stores all the buttons that the player will use, and their
 * functions in the game.
 * 
 * @author Eric Cheng
 * @author Kabir Kapoor
 * @date 5/23/2015
 * @author Assignment - JMCh00_SudokuFinal
 */
public class ButtonPanel
{

    protected static final Insets buttonInsets = new Insets( 10, 10, 0, 10 );

    private JToggleButton startGameButton;

    private JToggleButton setValuesButton;

    private JToggleButton solveButton;

    private JToggleButton matchButton;

    private JPanel panel;

    private GameFrame frame;

    private PlayerBoard model;

    private AIBoard AiModel;

    private WatchLauncher tester;

    protected boolean AiSolvebool = false;

    private Matcher m;


    /**
     * Constructor
     * 
     * @param frame
     *            = gets frame
     * @param model
     *            = gets the user board
     * @param AiModel
     *            = gets the ai board
     */
    public ButtonPanel( GameFrame frame, PlayerBoard model, AIBoard AiModel )
    {
        this.frame = frame;
        this.model = model;
        this.AiModel = AiModel;
        tester = new WatchLauncher();
        m = new Matcher( this );
        createPartControl();

    }


    /**
     * @return = launches the StopWatch class
     */
    public WatchLauncher getWatchLauncher()
    {
        return this.tester;
    }


    /**
     * Changes the amount of points
     * 
     * @param n
     *            = get integer
     */
    public void changePoints( int n )
    {
        this.tester.setPoints( n );
    }


    /**
     * Gets the setup of the button panel
     */
    private void createPartControl()
    {
        panel = new JPanel();
        panel.setLayout( new GridBagLayout() );

        int gridy = 0;

        startGameButton = new JToggleButton( "Action" );
        startGameButton.addChangeListener( new ChangeListener()
        {

            @Override
            public void stateChanged( ChangeEvent event )
            {
                if ( startGameButton.isSelected() )
                {
                    AiModel.setAllInitials();
                    model.setAllInitials();
                    frame.getFrame().repaint();
                    startGameButton.setSelected( false );
                    setValuesButton.setSelected( true );
                    if ( AiModel.getAiSolveBool() == true )
                    {
                        try
                        {
                            AiModel.solve( 0, 0 );
                            model.setNotGetPoints();
                            frame.getFrame().repaint();

                        }
                        catch ( Exception e )
                        {
                            System.out.println( "AI done" );
                        }
                    }

                }
            }
        } );
        addComponent( panel,
            startGameButton,
            0,
            gridy++,
            1,
            1,
            buttonInsets,
            GridBagConstraints.LINE_START,
            GridBagConstraints.HORIZONTAL );

        setValuesButton = new JToggleButton( "Able to Set Values" );
        setValuesButton.addChangeListener( new ChangeListener()
        {
            @Override
            public void stateChanged( ChangeEvent event )
            {
                model.setSetValues( setValuesButton.isSelected() );
            }
        } );
        solveButton = new JToggleButton( "Done!" );
        solveButton.addChangeListener( new ChangeListener()
        {
            @Override
            public void stateChanged( ChangeEvent event )
            {
                AiModel.setAiSolveBoolTrue();
            }
        } );
        addComponent( panel,
            solveButton,
            0,
            gridy++,
            1,
            1,
            buttonInsets,
            GridBagConstraints.LINE_START,
            GridBagConstraints.HORIZONTAL );

        matchButton = new JToggleButton( "Match if Done" );
        matchButton.addChangeListener( new ChangeListener()
        {

            @Override
            public void stateChanged( ChangeEvent event )
            {
                for ( int i = 0; i < 9; i++ )
                {
                    for ( int j = 0; j < 9; j++ )
                    {
                        if ( model.getCells()[i][j].getValue() == AiModel.getCells()[i][j].getValue() )
                        {
                            System.out.println( model.getCells()[i][j] );
                            model.getPointData().playerPoints.add( 10 );

                        }
                    }
                }
                model.getPointData().playerPoints.add( -100 );
                m.getButtonPanel().changePoints( model.getPointData()
                    .getTotalPoints() );
                double d = m.getButtonPanel()
                    .getWatchLauncher()
                    .getWatch()
                    .getTime();
                System.out.println( d );
                model.getPointData().playerPoints.add( -1 * (int)d + 1 );
                m.getButtonPanel().changePoints( model.getPointData()
                    .getTotalPoints() );
                if ( model.getPointData().getTotalPoints() > 3000 )
                {
                    m.getButtonPanel()
                        .getWatchLauncher()
                        .getWatch()
                        .getPoints()
                        .setText( m.getButtonPanel()
                            .getWatchLauncher()
                            .getWatch()
                            .getPoints()
                            .getText()
                            + " You Win" );
                }
                else
                {
                    m.getButtonPanel()
                        .getWatchLauncher()
                        .getWatch()
                        .getPoints()
                        .setText( m.getButtonPanel()
                            .getWatchLauncher()
                            .getWatch()
                            .getPoints()
                            .getText()
                            + " Computer Wins" );
                }

            }
        } );
        addComponent( panel,
            matchButton,
            0,
            gridy++,
            1,
            1,
            buttonInsets,
            GridBagConstraints.LINE_START,
            GridBagConstraints.HORIZONTAL );
    }


    /**
     * Adds the component
     * 
     * @param container
     *            = The given container
     * @param component
     *            = The given component
     * @param gridx
     *            = gets the x value
     * @param gridy
     *            = gets the y value
     * @param gridwidth
     *            = gets the grid width
     * @param gridheight
     *            = gets the grid height
     * @param insets
     *            = gets the insets
     * @param anchor
     *            = gets the anchors
     * @param fill
     *            = fills the components
     */
    private void addComponent(
        Container container,
        Component component,
        int gridx,
        int gridy,
        int gridwidth,
        int gridheight,
        Insets insets,
        int anchor,
        int fill )
    {
        GridBagConstraints gbc = new GridBagConstraints( gridx,
            gridy,
            gridwidth,
            gridheight,
            1.0D,
            1.0D,
            anchor,
            fill,
            insets,
            0,
            0 );
        container.add( component, gbc );
    }


    /**
     * @return = the panel
     */
    public JPanel getPanel()
    {
        return panel;
    }

}
