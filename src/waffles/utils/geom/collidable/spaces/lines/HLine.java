package waffles.utils.geom.collidable.spaces.lines;

import waffles.utils.algebra.elements.linear.Affine;
import waffles.utils.algebra.elements.linear.matrix.Matrices;
import waffles.utils.algebra.elements.linear.matrix.Matrix;
import waffles.utils.algebra.elements.linear.vector.Vector;
import waffles.utils.algebra.elements.linear.vector.Vectors;
import waffles.utils.geom.Collidable;
import waffles.utils.geom.Collision;
import waffles.utils.geom.collidable.fixed.Point;
import waffles.utils.geom.collision.spaces.lines.CLSHLine;

/**
 * A {@code HLine} defines an affine half-line in n-dimensional space.
 * 
 * @author Waffles
 * @since Jul 5, 2016
 * @version 1.0
 * 
 * 
 * @see Collidable
 * @see Affine
 */
public class HLine implements Affine, Collidable
{
	private static Point DefaultOrigin(int dim)
	{
		return new Point(Vectors.create(dim), 1f);
	}
	
	
	private Point o;
	private Vector v;

	/**
	 * Creates a new {@code HLine}.
	 * 
	 * @param o  an origin point
	 * @param v  a vector direction
	 * 
	 * 
	 * @see Vector
	 * @see Point
	 */
	public HLine(Point o, Vector v)
	{
		this.o = o;
		this.v = v;
	}

	/**
	 * Creates a new {@code HLine}.
	 * 
	 * @param v  a vector direction
	 * 
	 * 
	 * @see Vector
	 */
	public HLine(Vector v)
	{
		this(DefaultOrigin(v.Size()), v);
	}

	
	/**
	 * Returns the origin of the {@code HLine}.
	 * 
	 * @return  an origin point
	 * 
	 * 
	 * @see Point
	 */
	public Point Origin()
	{
		return o;
	}
		
	/**
	 * Returns the direction of the {@code HLine}.
	 * 
	 * @return  a vector direction
	 * 
	 * 
	 * @see Vector
	 */
	public Vector Direction()
	{
		return v;
	}
	
	/**
	 * Returns the parent line of the {@code HLine}.
	 * 
	 * @return  a parent line
	 * 
	 * 
	 * @see Line
	 */
	public Line Line()
	{
		Vector p = Origin().Generator();
		Vector q = p.plus(Direction());

		return new Line(p, q);
	}

	
	@Override
	public int Dimension()
	{
		return o.Dimension();
	}
	
	@Override
	public Collision Collisions()
	{
		return new CLSHLine(this);
	}

	@Override
	public Factory Factory()
	{
		return (m) ->
		{
			int dim = Dimension();
						
			Vector o = m.Column(0);
			Vector v = m.Column(1);
			
			o = Vectors.resize(o, dim);
			v = Vectors.resize(v, dim);
			
			
			float mass = m.get(dim, 0);
			Point p = new Point(o, mass);
			return new HLine(p, v);
		};
	}

	@Override
	public Matrix Span()
	{
		int dim = Dimension();
		
		Matrix span = Matrices.create(dim+1, 2);
		span.set(o.Mass(), dim, 0);
		for(int r = 0; r < dim; r++)
		{
			span.set(o.get(r), r, 0);
			span.set(v.get(r), r, 1);
		}
		
		return span;
	}
}