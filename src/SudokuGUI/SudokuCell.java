package SudokuGUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;


/**
 * This class draws out each of the specific cells in the Sudoku game board.
 * These can later be replicated into a 9 by 9 square to form the whole Sudoku
 * board. The first few methods are direct methods that return specific values
 * that could be accessed by other people. The draw method is basically the main
 * focus of this class; it shows how the cell is created and how it is drawn.
 * 
 * 
 * @author Eric Cheng
 * @author Kabir Kapoor
 * @date 5/23/2015
 * @author Assignment - JMCh00_SudokuFinal
 */
public class SudokuCell
{

    private boolean isInitial;

    public int maxValue;

    private int value;

    private Point cellLocation;

    private Rectangle bounds;

    private List<Integer> possibleValues;

    private int supposedValue = 0;


    /**
     * Constructor
     */
    public SudokuCell()
    {
        this.maxValue = 9;
        this.possibleValues = new ArrayList( maxValue );
        init( maxValue );
    }


    /**
     * @param n = Sets the valve to this number
     */
    public void setSupposedValue( int n )
    {
        supposedValue = n;
    }


    /**
     * @return = Gets the supposed value of the cell
     */
    public int getSupposedValue()
    {
        return supposedValue;
    }


    /**
     * Initiates the cell
     * 
     * @param maxValue = gets the maximum value
     */
    public void init( int maxValue )
    {
        this.value = 0;
        this.possibleValues.clear();
        this.isInitial = false;
        add( maxValue );
    }


    /**
     * @param myValue = Gets the value to be set
     */
    public void setInitialValue( int myValue )
    {
        this.value = myValue;
        this.possibleValues.clear();
        this.isInitial = true;
    }


    /**
     * Adds the maximum value
     * 
     * @param maxValue = the maximum value
     */
    private void add( int maxValue )
    {
        for ( int i = 1; i <= maxValue; i++ )
        {
            this.possibleValues.add( i );
        }
    }


    /**
     * @return = Gets value
     */
    public int getValue()
    {
        return value;
    }


    /**
     * @param value = Sets the value
     */
    public void setValue( int value )
    {
        this.value = value;
    }


    /**
     * @param isInitial = Set initial value
     */
    public void setIsInitial( boolean isInitial )
    {
        this.isInitial = isInitial;
    }


    /**
     * Clears value
     */
    public void clearPossibleValues()
    {
        this.possibleValues.clear();
    }


    /**
     * @return = Location of the cell
     */
    public Point getCellLocation()
    {
        return cellLocation;
    }


    /**
     * @param cellLocation = the location to be set
     */
    public void setCellLocation( Point cellLocation )
    {
        this.cellLocation = cellLocation;
    }


    /**
     * @param bounds = Sets the bounds of the cell
     */
    public void setBounds( Rectangle bounds )
    {
        this.bounds = bounds;
    }


    /**
     * See if cell contains certain number
     * 
     * @param point = New points
     * @return = true if number is inside
     */
    public boolean contains( Point point )
    {
        return getBounds().contains( point );
    }


    /**
     * @return = Gets possible values
     */
    public List<Integer> getPossibleValues()
    {
        return possibleValues;
    }


    /**
     * Sets the possible values
     * 
     * @param possibleValues = the possible values
     */
    private void setPossibleValues( List possibleValues )
    {
        this.possibleValues = possibleValues;
    }


    /**
     * @return = Return the size of the count
     */
    public int getPossibleValuesCount()
    {
        return possibleValues.size();
    }


    /**
     * @param possibleValue = If it is possible value
     * @return = true if it is possible value
     */
    public boolean isPossibleValue( int possibleValue )
    {
        return possibleValues.contains( (Integer)possibleValue );
    }


    /**
     * @param possibleValue = The value to be removed.
     */
    public void removePossibleValue( int possibleValue )
    {
        this.possibleValues.remove( (Integer)possibleValue );
    }


    /**
     * @return = makes a copy of the sudoku cell
     */
    public SudokuCell copy()
    {
        SudokuCell sudokuCell = new SudokuCell();
        sudokuCell.setIsInitial( this.isInitial );
        sudokuCell.setBounds( this.getBounds() );
        sudokuCell.setCellLocation( this.getCellLocation() );
        sudokuCell.setPossibleValues( this.copyPossibleValues() );
        sudokuCell.setValue( this.getValue() );
        return sudokuCell;
    }


    /**
     * @return = Copies the possible values
     */
    private List copyPossibleValues()
    {
        List list = new ArrayList();
        for ( Integer number : this.getPossibleValues() )
        {
            list.add( number );
        }
        return list;
    }


    @Override
    public String toString()
    {
        String s = getBounds().toString();
        int sPos = s.indexOf( "[" ) + 1;
        int ePos = s.indexOf( "]", sPos );

        StringBuilder builder = new StringBuilder();
        builder.append( "Cell located at " );
        builder.append( cellLocation.x );
        builder.append( "," );
        builder.append( cellLocation.y );
        builder.append( "; drawing coordinates " );
        builder.append( s.substring( sPos, ePos ) );
        builder.append( "; value: " );
        builder.append( value );
        builder.append( ", possible values: " );

        for ( int i = 0; i < possibleValues.size(); i++ )
        {
            builder.append( possibleValues.get( i ) );
            if ( i < ( possibleValues.size() - 1 ) )
            {
                builder.append( ", " );
            }
        }
        return builder.toString();
    }


    /**
     * Draws out the cell
     * 
     * @param g = Graphics object
     * @param x = x coordinates
     * @param y = y coordinates
     * @param width = the width of the cell
     * @param cellPosition = position of the cell
     */
    public void draw( Graphics g, int x, int y, int width, int cellPosition )
    {
        g.setColor( Color.WHITE );
        g.fillRect( x, y, width, width );
        g.setColor( Color.BLACK );
        g.drawRect( x, y, width, width );
        drawBorder( g, x, y, width, cellPosition );
        Font font = g.getFont();
        FontRenderContext frc = new FontRenderContext( null, true, true );
        if ( value > 0 )
        {
            String s = Integer.toString( value );

            BufferedImage image = createImage( font, frc, width, s );

            int xx = x + ( width - image.getWidth() ) / 2;
            int yy = y + ( width - image.getHeight() ) / 2;
            g.drawImage( image, xx, yy, null );

        }
        else
        {
            List<String> list = concatenatePossibleValues();

            double imageWidth = 0.0D;
            double imageHeight = 0.0D;
            double stringHeight = 0.0D;
            for ( String s : list )
            {
                Rectangle2D r = font.getStringBounds( s, frc );
                imageWidth = Math.max( imageWidth, r.getWidth() );
                imageHeight += r.getHeight();
                stringHeight = Math.max( stringHeight, r.getHeight() );
            }

            BufferedImage image = createImage( list,
                imageWidth,
                imageHeight,
                stringHeight );

            int xx = x + ( width - image.getWidth() ) / 2;
            int yy = y + ( width - image.getHeight() ) / 2;
            g.drawImage( image, xx, yy, null );
        }
    }


    /**
     * Draws out the border of the cell
     * 
     * @param g = Graphics object
     * @param x = x coordinates
     * @param y = y coordinates
     * @param width = the width of the cell
     * @param cellPosition = position of the cell
     */
    private void drawBorder(
        Graphics g,
        int x,
        int y,
        int width,
        int cellPosition )
    {
        switch ( cellPosition )
        {
            case 1:
                drawLeftBorder( g, x, y, width );
                drawTopBorder( g, x, y, width );
                break;
            case 2:
                drawTopBorder( g, x, y, width );
                break;
            case 3:
                drawTopBorder( g, x, y, width );
                drawRightBorder( g, x, y, width );
                break;
            case 4:
                drawLeftBorder( g, x, y, width );
                break;
            case 6:
                drawRightBorder( g, x, y, width );
                break;
            case 7:
                drawLeftBorder( g, x, y, width );
                drawBottomBorder( g, x, y, width );
                break;
            case 8:
                drawBottomBorder( g, x, y, width );
                break;
            case 9:
                drawBottomBorder( g, x, y, width );
                drawRightBorder( g, x, y, width );
                break;
        }
    }


    /**
     * Draws out the top border of the cell
     * 
     * @param g = Graphics object
     * @param x = x coordinates
     * @param y = y coordinates
     * @param width = the width of the cell
     */
    private void drawTopBorder( Graphics g, int x, int y, int width )
    {
        g.drawLine( x, y + 1, x + width, y + 1 );
        g.drawLine( x, y + 2, x + width, y + 2 );
    }


    /**
     * Draws out the right border of the cell
     * 
     * @param g = Graphics object
     * @param x = x coordinates
     * @param y = y coordinates
     * @param width = the width of the cell
     */
    private void drawRightBorder( Graphics g, int x, int y, int width )
    {
        g.drawLine( x + width - 1, y, x + width - 1, y + width );
        g.drawLine( x + width - 2, y, x + width - 2, y + width );
    }


    /**
     * Draws out the bottom border of the cell
     * 
     * @param g = Graphics object
     * @param x = x coordinates
     * @param y = y coordinates
     * @param width = the width of the cell
     */
    private void drawBottomBorder( Graphics g, int x, int y, int width )
    {
        g.drawLine( x, y + width - 1, x + width, y + width - 1 );
        g.drawLine( x, y + width - 2, x + width, y + width - 2 );
    }


    /**
     * Draws out the left border of the cell
     * 
     * @param g = Graphics object
     * @param x = x coordinates
     * @param y = y coordinates
     * @param width = the width of the cell
     */
    private void drawLeftBorder( Graphics g, int x, int y, int width )
    {
        g.drawLine( x + 1, y, x + 1, y + width );
        g.drawLine( x + 2, y, x + 2, y + width );
    }


    /**
     * Creates an image
     * 
     * @param font = the font of the words
     * @param frc = font context
     * @param width = the width of the image
     * @param s = get string
     * @return = the buffered image
     */
    private BufferedImage createImage(
        Font font,
        FontRenderContext frc,
        int width,
        String s )
    {
        int margin = 6;
        double extra = (double)margin + margin;

        Font largeFont = font.deriveFont( (float)( width * 2 / 3 ) );
        Rectangle2D r = largeFont.getStringBounds( s, frc );

        BufferedImage image = new BufferedImage( (int)Math.round( r.getWidth()
            + extra ),
            (int)Math.round( extra - r.getY() ),
            BufferedImage.TYPE_INT_RGB );
        Graphics gg = image.getGraphics();
        gg.setColor( Color.WHITE );
        gg.fillRect( 0, 0, image.getWidth(), image.getHeight() );

        if ( isInitial )
        {
            gg.setColor( Color.BLUE );
        }
        else
        {
            gg.setColor( Color.RED );
        }

        int x = margin;
        int y = -(int)Math.round( r.getY() );
        gg.setFont( largeFont );
        gg.drawString( s, x, y );
        gg.dispose();
        return image;
    }


    /**
     * @return = The total possible values in a list
     */
    private List concatenatePossibleValues()
    {
        List list = new ArrayList();
        StringBuilder builder = new StringBuilder();

        int stringIndex = 0;

        for ( int index = 0; index < getPossibleValuesCount(); index++ )
        {
            builder.append( possibleValues.get( index ) );
            if ( stringIndex < 2 )
            {
                builder.append( ' ' );
                stringIndex++;
            }
            else
            {
                list.add( builder.toString() );
                builder.delete( 0, builder.length() );
                stringIndex = 0;
            }
        }
        if ( builder.length() > 0 )
        {
            list.add( builder.toString() );
        }

        return list;
    }


    /**
     * @param list = a list
     * @param imageWidth = Gets the width of the image
     * @param imageHeight = Gets the height of the image
     * @param stringHeight = Gets the string height
     * @return = Generated images
     */
    private BufferedImage createImage(
        List<String> list,
        double imageWidth,
        double imageHeight,
        double stringHeight )
    {
        int margin = 6;
        double extra = (double)margin + margin;
        BufferedImage image = new BufferedImage( (int)Math.round( imageWidth
            + extra ),
            (int)Math.round( imageHeight + extra ),
            BufferedImage.TYPE_INT_RGB );
        Graphics gg = image.getGraphics();
        gg.setColor( Color.WHITE );
        gg.fillRect( 0, 0, image.getWidth(), image.getHeight() );

        gg.setColor( Color.DARK_GRAY );
        int x = margin;
        int y = margin / 2 + (int)Math.round( stringHeight );
        for ( String s : list )
        {
            gg.drawString( s, x, y );
            y += (int)Math.round( stringHeight );
        }
        gg.dispose();
        return image;
    }


    /**
     * @return = the bounds
     */
    public Rectangle getBounds()
    {
        return bounds;
    }

}
