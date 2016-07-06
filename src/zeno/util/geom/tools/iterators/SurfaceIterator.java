package zeno.util.geom.tools.iterators;

import java.util.Iterator;

import zeno.util.algebra.Integers;
import zeno.util.algebra.vectors.fixed.Vector2;
import zeno.util.geom.shapes.IGeometry2D;

/**
 * The {@code SurfaceIterator} class defines an iterator
 * over the surface of a {@code Geometry2D}.
 *
 * @author Zeno
 * @since Sep 12, 2015
 * @see Iterator
 * @see Vector2
 */
public class SurfaceIterator implements Iterator<Vector2>
{
	private Vector2 v;
	private IGeometry2D geom;
	private int x1, x2, y1, y2;
		
	/**
	 * Creates a new {@code SurfaceIterator}.
	 * 
	 * @param geom  a geometry to use
	 * @see IGeometry2D
	 */
	public SurfaceIterator(IGeometry2D geom)
	{
		this.geom = geom;
		
		x1 = Integers.floor(geom.XMin());
		y1 = Integers.floor(geom.YMin());
		
		x2 = Integers.ceil(geom.XMax()) + 1;
		y2 = Integers.ceil(geom.YMax()) + 1;
		
		v = new Vector2(x1 - 1, y1);
		v = createNext();
	}

	
	@Override
	public Vector2 next()
	{
		Vector2 next = v;
		v = createNext();
		return next;
	}
	
	@Override
	public boolean hasNext()
	{
		return v != null;
	}
	
	Vector2 createNext()
	{
		v.setX(v.X() + 1);
		if(v.X() == x2)
		{
			v.setX(x1);
			v.setY(v.Y() + 1);
			if(v.Y() == y2)
			{
				return null;
			}
		}
		
		if(!geom.contains(v))
		{
			return createNext();
		}

		return v;
	}
}