package zeno.util.geom.utilities.iterators;

import java.util.Iterator;

import zeno.util.algebra.linear.vector.fixed.Vector2;
import zeno.util.geom.collideables.geometry.planar.IGeometry2D;
import zeno.util.geom.utilities.cardinal.Cardinal2D;
import zeno.util.tools.Integers;

/**
 * The {@code PerimeterIterator} class defines an iterator
 * over the perimeter of a {@code Geometry2D}.
 *
 * @since Sep 12, 2015
 * @author Zeno
 * 
 * @see Iterator
 * @see Vector2
 */
public class PerimeterIterator implements Iterator<Vector2>
{
	private Vector2 v;
	private IGeometry2D geom;
	private int x1, x2, y1, y2;
		
	/**
	 * Creates a new {@code PerimeterIterator}.
	 * 
	 * @param geom  a geometry to use
	 * @see IGeometry2D
	 */
	public PerimeterIterator(IGeometry2D geom)
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
		
		Vector2 vNew = new Vector2();
		for(Cardinal2D car : Cardinal2D.getAll())
		{
			if(car == Cardinal2D.CENTER)
			{
				continue;
			}
			
			vNew = v.plus(car);
			if(!geom.contains(vNew))
			{
				return v;
			}
		}
		
		return createNext();
	}
}