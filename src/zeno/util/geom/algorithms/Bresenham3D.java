package zeno.util.geom.algorithms;

import java.util.Iterator;

import zeno.util.algebra.tensors.vectors.fixed.Vector3;
import zeno.util.geom.shapes.lines.Line3D;
import zeno.util.tools.Messenger;
import zeno.util.tools.primitives.Floats;
import zeno.util.tools.primitives.Integers;

/**
 * The {@code Bresenham3D} class is based on Bresenham's line algorithm.
 * <br> It generates a list of integer points along a {@code Line3D}.
 * 
 * @since Oct 27, 2014
 * @author Zeno
 * 
 * @see <a href="http://en.wikipedia.org/wiki/Bresenham's_line_algorithm">Bresenham&rsquo;s Algorithm</a>
 * @see Messenger
 * @see Iterator
 * @see Vector3
 */
public class Bresenham3D extends Messenger implements Iterator<Vector3>
{
	private int offx, offy, offz;
	private int sx, sy, sz, tx, ty, tz;
	private float dx, dy, dz, err1, err2;
	
	/**
	 * Generates a new line between two points.
	 * 
	 * @param x1  the source's x-coördinate
	 * @param y1  the source's y-coördinate
	 * @param z1  the source's z-coördinate
	 * @param x2  the target's x-coördinate
	 * @param y2  the target's y-coördinate
	 * @param z2  the target's z-coördinate
	 */
	public void generateLine(float x1, float y1, float z1, float x2, float y2, float z2)
	{
		dx = Floats.abs(x2 - x1);
		dy = Floats.abs(y2 - y1);
		dz = Floats.abs(z2 - z1);
		
		offx = (x1 > x2 ? -1 : 1);
		offy = (y1 > y2 ? -1 : 1);
		offz = (z1 > z2 ? -1 : 1);
		
		sx = Integers.round(x1 - 0.5f * offx);
		sy = Integers.round(y1 - 0.5f * offy);
		sz = Integers.round(z1 - 0.5f * offz);
		tx = Integers.round(x2 + 0.5f * offx);
		ty = Integers.round(y2 + 0.5f * offy);
		tz = Integers.round(z2 + 0.5f * offz);
		
		err1 = Floats.max(dx, dy, dz) / 2;
		err2 = Floats.max(dx, dy, dz) / 2;
	}
	
	/**
	 * Generates a new line between two points.
	 * 
	 * @param src  the line's source
	 * @param tgt  the line's target
	 * @see Vector3
	 */
	public void generateLine(Vector3 src, Vector3 tgt)
	{
		generateLine(src.X(), src.Y(), src.Z(), tgt.X(), tgt.Y(), tgt.Z());
	}
	
	/**
	 * Generates a new line between two points.
	 * 
	 * @param line  a line to estimate
	 * @see Line3D
	 */
	public void generateLine(Line3D line)
	{
		generateLine(line.P1(), line.P2());
	}
	
	
	@Override
	public boolean hasNext()
	{
		return sx != tx && sy != ty && sz != tz;
	}
	
	@Override
	public Vector3 next()
	{
		Vector3 next = new Vector3(sx, sy, sz);
		
		// Biggest difference defines which
		// coördinate is always increased.
		if(dx > dy)
		{
			if(dx > dz)
				advanceX();
			else
				advanceZ();
		}
		else
		{
			if(dy > dz)
				advanceY();
			else
				advanceZ();
		}
		
		return next;
	}

	
	void advanceX()
	{
		err1 -= dy;
		if(err1 < 0)
		{
			sy += offy;
			err1 += dx;
		}
		
		err2 -= dz;
		if(err2 < 0)
		{
			sz += offz;
			err2 += dx;
		}
		
		sx += offx;
	}
	
	void advanceY()
	{
		err1 -= dx;
		if(err1 < 0)
		{
			sx += offx;
			err1 += dy;
		}
		
		err2 -= dz;
		if(err2 < 0)
		{
			sz += offz;
			err2 += dy;
		}
		
		sy += offy;
	}
	
	void advanceZ()
	{
		err1 -= dx;
		if(err1 < 0)
		{
			sx += offx;
			err1 += dz;
		}
		
		err2 -= dy;
		if(err2 < 0)
		{
			sy += offy;
			err2 += dz;
		}
		
		sz += offz;
	}
}