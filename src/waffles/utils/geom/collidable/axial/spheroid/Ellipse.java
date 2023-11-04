package waffles.utils.geom.collidable.axial.spheroid;

import waffles.utils.algebra.elements.linear.vector.Vector;
import waffles.utils.algebra.elements.linear.vector.fixed.Vector2;
import waffles.utils.geom.bounds.Bounds2D;
import waffles.utils.geom.bounds.axial.spheroid.BNDSpheroid2D;
import waffles.utils.geom.collidable.axial.AxialSet2D;
import waffles.utils.geom.collidable.fixed.Point;
import waffles.utils.geom.spatial.maps.GlobalMap;
import waffles.utils.tools.primitives.Floats;

/**
 * An {@code Ellipse} defines a two-dimensional spheroid shape.
 *
 * @author Waffles
 * @since Apr 29, 2016
 * @version 1.0
 * 
 * 
 * @see AxialSet2D
 * @see HyperSpheroid
 */
public class Ellipse extends AxialSet2D implements HyperSpheroid
{	
	/**
	 * Creates a new {@code Ellipse}.
	 * 
	 * @param x  a center x-coordinate
	 * @param y  a center y-coordinate
	 * @param w  a width
	 * @param h  a height
	 */
	public Ellipse(float x, float y, float w, float h)
	{
		this(new Vector2(x, y), new Vector2(w, h));
	}
	
	/**
	 * Creates a new {@code Ellipse}.
	 * 
	 * @param c  a center vector
	 * @param s  a size vector
	 * 
	 * 
	 * @see Vector2
	 */
	public Ellipse(Vector2 c, Vector2 s)
	{
		super(c, s);
	}
	
	/**
	 * Creates a new {@code Ellipse}.
	 * 
	 * @param c  a point center
	 * @param s  a size vector
	 * 
	 * 
	 * @see Vector2
	 * @see Point
	 */
	public Ellipse(Point c, Vector2 s)
	{
		super(c.Generator(), s);
	}
	
	/**
	 * Creates a new {@code Ellipse}.
	 * 
	 * @param w  a width
	 * @param h  a height
	 */
	public Ellipse(float w, float h)
	{
		this(0, 0, w, h);
	}
	
	/**
	 * Creates a new {@code Ellipse}.
	 * 
	 * @param s  a size vector
	 * 
	 * 
	 * @see Vector2
	 */
	public Ellipse(Vector2 s)
	{
		super(s);
	}
	
	/**
	 * Creates a new {@code Ellipse}.
	 */
	public Ellipse()
	{
		super();
	}
	
	
	@Override
	public Ellipse create(Vector o, Vector s)
	{
		int dim = s.Size();
		
		float avg = s.norm1() / dim;
		for(int i = 0; i < dim; i++)
		{
			float val = s.get(i);
			if(!Floats.isEqual(val, avg, 3))
			{
				return new Ellipse
				(
					(Vector2) o, 
					(Vector2) s
				);
			}
		}
		
		return new Circle((Vector2) o, avg / 2);
	}

	@Override
	public Bounds2D Bounds(GlobalMap map)
	{
		return new BNDSpheroid2D(this, map);
	}
	
	@Override
	public boolean equals(Object o)
	{
		if(o instanceof Ellipse)
		{
			return super.equals(o);
		}
		
		return false;
	}
}