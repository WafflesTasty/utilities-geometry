package zeno.util.geom.collidables.geometry.generic;

import zeno.util.algebra.linear.vector.Vector;
import zeno.util.geom.ITransformation;
import zeno.util.geom.collidables.bounds.Bounds;
import zeno.util.geom.utilities.Geometries;

/**
 * The {@code DConvex} class defines a Minkowski difference of convex shapes.
 *
 * @author Waffles
 * @since 17 Apr 2022
 * @version 1.0
 * 
 * 
 * @see IConvex
 */
public class DConvex implements IConvex
{
	private IConvex a, b;
	
	/**
	 * Creates a new {@code DConvex}.
	 * 
	 * @param a  a convex shape
	 * @param b  a convex shape
	 */
	public DConvex(IConvex a, IConvex b)
	{
		this.a = a;
		this.b = b;
	}
	
	
	@Override
	public Bounds Bounds(ITransformation map)
	{
		Vector min = Minimum();
		Vector max = Maximum();
		
		return Geometries.cuboid
		(
			min.plus(max).times(0.5f),
			max.minus(min)
		);
	}
	
	@Override
	public Extremum Extremum()
	{			
		return v ->
		{
			Extremum aExt = a.Extremum();
			Extremum bExt = b.Extremum();
			
			Vector v1 = aExt.along(v.times(+1f));
			if(v1 == null) return null;
			Vector v2 = bExt.along(v.times(-1f));
			if(v2 == null) return null;
			return v1.minus(v2);
		};
	}
	
	
	@Override
	public Vector Minimum()
	{
		return a.Minimum().minus(b.Maximum());
	}
	
	@Override
	public Vector Maximum()
	{
		return a.Maximum().minus(b.Minimum());
	}
}