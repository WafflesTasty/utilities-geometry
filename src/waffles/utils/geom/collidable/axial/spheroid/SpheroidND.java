package waffles.utils.geom.collidable.axial.spheroid;

import waffles.utils.algebra.elements.linear.vector.Vector;
import waffles.utils.geom.bounds.Bounds;
import waffles.utils.geom.bounds.axial.spheroid.BNDSpheroid;
import waffles.utils.geom.collidable.axial.AxialSet;
import waffles.utils.geom.collidable.fixed.Point;
import waffles.utils.geom.spatial.maps.GlobalMap;
import waffles.utils.tools.primitives.Floats;

/**
 * A {@code SpheroidND} defines an n-dimensional spheroid shape.
 * 
 * @author Waffles
 * @since Apr 29, 2016
 * @version 1.0
 * 
 * 
 * @see HyperSpheroid
 * @see AxialSet
 */
public class SpheroidND extends AxialSet implements HyperSpheroid
{	
	/**
	 * Creates a new {@code SpheroidND}.
	 * 
	 * @param c  a point center
	 * @param s  a size vector
	 * 
	 * 
	 * @see Vector
	 * @see Point
	 */
	public SpheroidND(Point c, Vector s)
	{
		this(c.Generator(), s);
	}
	
	/**
	 * Creates a new {@code SpheroidND}.
	 * 
	 * @param c  a center vector
	 * @param s  a size vector
	 * 
	 * 
	 * @see Vector
	 */
	public SpheroidND(Vector c, Vector s)
	{
		super(c, s);
	}
	
	/**
	 * Creates a new {@code SpheroidND}.
	 * 
	 * @param s  a size vector
	 * 
	 * 
	 * @see Vector
	 */
	public SpheroidND(Vector s)
	{
		super(s);
	}
	
	/**
	 * Creates a new {@code SpheroidND}.
	 * 
	 * @param dim  a space dimension
	 */
	public SpheroidND(int dim)
	{
		super(dim);
	}
	
	
	@Override
	public SpheroidND create(Vector o, Vector s)
	{
		int dim = s.Size();
		
		float avg = s.norm1() / dim;
		for(int i = 0; i < dim; i++)
		{
			float val = s.get(i);
			if(!Floats.isEqual(val, avg, 3))
			{
				return new SpheroidND(o, s);
			}
		}
		
		return new SphereND(o, avg / 2);
	}
	
	@Override
	public Bounds Bounds(GlobalMap map)
	{
		return new BNDSpheroid(this, map);
	}
	
	@Override
	public boolean equals(Object o)
	{
		if(o instanceof SpheroidND)
		{
			return super.equals(o);
		}
		
		return false;
	}
}