package waffles.utils.geom.collidable.axial;

import waffles.utils.algebra.elements.linear.Affine;
import waffles.utils.algebra.elements.linear.vector.Vector;
import waffles.utils.algebra.elements.linear.vector.Vectors;
import waffles.utils.geom.bounds.Bounds;
import waffles.utils.geom.bounds.axial.BNDAxial;
import waffles.utils.geom.collidable.Geometry;
import waffles.utils.geom.collidable.fixed.Point;
import waffles.utils.geom.maps.GlobalMap;
import waffles.utils.geom.spatial.data.Axial;

/**
 * An {@code AxialSet} defines n-dimensional geometry through a center and size vector.
 *
 * @author Waffles
 * @since 15 Sep 2023
 * @version 1.0
 * 
 * 
 * @see Geometry
 * @see Axial
 */
public abstract class AxialSet implements Axial.Mutable, Geometry
{
	private Vector size;
	private Vector origin;
	
	/**
	 * Creates a new {@code AxialSet}.
	 * 
	 * @param dim  a space dimension
	 */
	public AxialSet(int dim)
	{
		this(Vectors.create(2f, dim));
	}
	
	/**
	 * Creates a new {@code AxialSet}.
	 * 
	 * @param s  a size vector
	 * 
	 * 
	 * @see Vector
	 */
	public AxialSet(Vector s)
	{
		this(Vectors.create(s.Size()), s);
	}
	
	
	/**
	 * Creates a new {@code AxialSet}.
	 * 
	 * @param p  an origin point
	 * @param s  a size vector
	 * 
	 * 
	 * @see Vector
	 * @see Point
	 */
	public AxialSet(Point p, Vector s)
	{
		this(p.Generator(), s);
	}
	
	/**
	 * Creates a new {@code AxialSet}.
	 * 
	 * @param o  an origin vector
	 * @param s  a size vector
	 * 
	 * 
	 * @see Vector
	 */
	public AxialSet(Vector o, Vector s)
	{
		size = s.absolute();
		origin = o;
	}
	
	
	/**
	 * Creates a new {@code AxialSet} given the origin and size.
	 * 
	 * @param o  an origin vector
	 * @param s  a size vector
	 * @return  an axial set
	 * 
	 * 
	 * @see Vector
	 */
	public abstract AxialSet create(Vector o, Vector s);
	
	/**
	 * Maps the {@code AxialSet} along a basic map.
	 * 
	 * @param map  a basic map
	 * @return  a mapped axial set
	 * 
	 * 
	 * @see GlobalMap
	 */
	public AxialSet map(GlobalMap map)
	{
		Point o1 = new Point(Origin(), 1f);
		Affine o2 = map.map(o1);
		if(!(o2 instanceof Point))
		{
			return null;
		}

		Point s1 = new Point(Size(), 0f);
		Affine s2 = map.map(s1);
		if(!(s2 instanceof Point))
		{
			return null;
		}
		
		
		o1 = (Point) o2;
		s1 = (Point) s2;
		
		return create
		(
			o1.Generator(),
			s1.Generator()
		);
	}
	
	
	@Override
	public boolean equals(Object o)
	{
		if(o instanceof AxialSet)
		{
			AxialSet s = (AxialSet) o;
			return Origin().equals(s.Origin())
				&& Size().equals(s.Size());
		}
		
		return false;
	}
	
	@Override
	public void setOrigin(Vector o)
	{
		origin = o;
	}
		
	@Override
	public void setSize(Vector s)
	{
		size = s;
	}
	
	
	@Override
	public Bounds Bounds()
	{
		return new BNDAxial(this);
	}
	
	@Override
	public Vector Origin()
	{
		return origin;
	}
	
	@Override
	public Vector Size()
	{
		return size;
	}
}