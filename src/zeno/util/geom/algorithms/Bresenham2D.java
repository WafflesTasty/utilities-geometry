package zeno.util.geom.algorithms;

import java.util.Iterator;

import zeno.util.algebra.Floats;
import zeno.util.algebra.Integers;
import zeno.util.algebra.vectors.fixed.Vector2;
import zeno.util.geom.shapes.lines.Line2D;
import zeno.util.tools.Messenger;

/**
 * The {@code Bresenham2D} class is based on Bresenham's line algorithm.
 * <br> It generates a list of integer points along a {@code Line2D}.
 * 
 * @author Zeno
 * @since Oct 27, 2014
 * @see <a href="http://en.wikipedia.org/wiki/Bresenham's_line_algorithm">Bresenham&rsquo;s Algorithm</a>
 * @see Messenger
 * @see Iterator
 * @see Vector2
 */
public class Bresenham2D extends Messenger implements Iterator<Vector2>
{
	private int offx, offy;
	private int sx, sy, tx, ty;
	private float dx, dy, err;
	
	/**
	 * Generates a new line between two points.
	 * 
	 * @param x1  the source's x-coördinate
	 * @param y1  the source's y-coördinate
	 * @param x2  the target's x-coördinate
	 * @param y2  the target's y-coördinate
	 */
	public void generateLine(float x1, float y1, float x2, float y2)
	{
		dx = Floats.abs(x2 - x1);
		dy = Floats.abs(y2 - y1);
		
		offx = (x1 > x2 ? -1 : 1);
		offy = (y1 > y2 ? -1 : 1);
		
		sx = Integers.round(x1 - 0.5f * offx);
		sy = Integers.round(y1 - 0.5f * offy);
		tx = Integers.round(x2 + 0.5f * offx);
		ty = Integers.round(y2 + 0.5f * offy);
		
		err = Floats.max(dx, dy) / 2;
	}
	
	/**
	 * Generates a new line between two points.
	 * 
	 * @param src  the line's source
	 * @param tgt  the line's target
	 * @see Vector2
	 */
	public void generateLine(Vector2 src, Vector2 tgt)
	{
		generateLine(src.X(), src.Y(), tgt.X(), tgt.Y());
	}
	
	/**
	 * Generates a new line between two points.
	 * 
	 * @param line  a line to estimate
	 * @see Line2D
	 */
	public void generateLine(Line2D line)
	{
		generateLine(line.P1(), line.P2());
	}
	
	
	@Override
	public boolean hasNext()
	{
		return sx != tx && sy != ty;
	}
	
	@Override
	public Vector2 next()
	{
		Vector2 next = new Vector2(sx, sy);
		
		// Biggest difference defines which
		// coördinate is always increased.
		if(dx > dy)
			advanceX();
		else
			advanceY();
		
		return next;
	}

	
	void advanceX()
	{
		err -= dy;
		if(err < 0)
		{
			sy += offy;
			err += dx;
		}
		
		sx += offx;
	}
	
	void advanceY()
	{
		err -= dx;
		if(err < 0)
		{
			sx += offx;
			err += dy;
		}
		
		sy += offy;
	}
}