package waffles.utils.geom.collidable.spaces;

import waffles.utils.algebra.elements.linear.Affine;
import waffles.utils.algebra.elements.linear.matrix.Matrices;
import waffles.utils.algebra.elements.linear.matrix.Matrix;
import waffles.utils.algebra.elements.linear.vector.Vector;
import waffles.utils.algebra.elements.linear.vector.Vectors;
import waffles.utils.geom.Collidable;
import waffles.utils.geom.Collision;
import waffles.utils.geom.collidable.fixed.Point;
import waffles.utils.geom.response.spaces.CLSASpace;
import waffles.utils.geom.spatial.maps.fixed.linear.Translation;
import waffles.utils.geom.utilities.Geometries;

/**
 * An {@code ASpace} defines a real-valued euclidian affine space.
 * Affine spaces are designed to produce various bases, and
 * implement {@code Collidable} for collision checks.
 * 
 * @author Waffles
 * @since Apr 8, 2019
 * @version 1.0
 * 
 * 
 * @see Collidable
 * @see Affine
 */
public class ASpace implements Affine, Collidable
{	
	/**
	 * Creates a default {@code ASpace}.
	 * The origin is chosen at zero.
	 * 
	 * @param v  a vector space
	 * @return  an affine space
	 * 
	 * 
	 * @see VSpace
	 */
	public static ASpace Default(VSpace v)
	{
		int dim = v.Dimension();
		Vector o = Translation.Default(dim);
		return new ASpace(o, v);
	}
	
	
	private VSpace space;
	private Point origin;
	
	/**
	 * Creates a new {@code ASpace}.
	 * 
	 * @param o  an affine origin
	 * @param s  a vector space
	 * 
	 * 
	 * @see VSpace
	 * @see Point
	 */
	public ASpace(Point o, VSpace s)
	{
		origin = o;
		space = s;
	}
	
	/**
	 * Creates a new {@code ASpace}.
	 * 
	 * @param o  a vector origin
	 * @param s  a vector space
	 * 
	 * 
	 * @see VSpace
	 * @see Vector
	 */
	public ASpace(Vector o, VSpace s)
	{
		this(new Point(o, 1f), s);
	}
	
	/**
	 * Creates a new {@code ASpace}.
	 * 
	 * @param o  a vector origin
	 * @param v  a vector direction
	 * 
	 * 
	 * @see Vector
	 */
	public ASpace(Vector o, Vector v)
	{
		this(o, new VSpace(v));
	}
	
	/**
	 * Creates a new {@code ASpace}.
	 * 
	 * @param o  an affine origin
	 * @param v  a vector direction
	 * 
	 * 
	 * @see Vector
	 * @see Point
	 */
	public ASpace(Point o, Vector v)
	{
		this(o, new VSpace(v));
	}
	
	
	/**
	 * Returns the origin of the {@code ASpace}.
	 * 
	 * @return  an origin point
	 * 
	 * 
	 * @see Point
	 */
	public Point Origin()
	{
		return origin;
	}
	
	/**
	 * Returns the direction of the {@code ASpace}.
	 * 
	 * @return  a vector space
	 * 
	 * 
	 * @see VSpace
	 */
	public VSpace Direction()
	{
		return space;
	}
		
	/**
	 * Returns a generating point of the {@code ASpace}.
	 * 
	 * @param i  a point index
	 * @return   a generating point
	 * 
	 * 
	 * @see Point
	 */
	public Point Generator(int i)
	{
		Vector v = space.Generator(i);
		return origin.plus(v);
	}

	/**
	 * Returns the generator count of the {@code ASpace}.
	 * 
	 * @return  a generator count
	 */
	public int Generators()
	{
		return space.Generators();
	}


	@Override
	public Factory Factory()
	{
		return m ->
		{
			int rows = m.Rows();
			int cols = m.Columns();
			
			Vector o = m.Column(cols-1);
			o = Vectors.resize(o, rows-1);
			float mass = m.get(rows-1, cols-1);
			Point p = new Point(o, mass);
			
			
			Matrix g = Matrices.resize(m, rows-1, cols-1);
			return Geometries.Span(p, new VSpace(g));
		};
	}
	
	@Override
	public Collision Collisions()
	{
		return new CLSASpace(this);
	}

	@Override
	public int Dimension()
	{
		return Direction().Dimension();
	}
	
	@Override
	public Matrix Span()
	{
		Matrix span = Direction().Span();
		int cols = span.Columns();
		int rows = span.Rows();
		
		
		span = Matrices.resize(span, rows, cols+1);
		span.set(origin.Mass(), rows-1, cols);
		for(int r = 0; r < rows-1; r++)
		{
			float val = origin.get(r);
			span.set(val, r, cols);
		}
		
		return span;
	}
}