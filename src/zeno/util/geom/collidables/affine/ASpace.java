package zeno.util.geom.collidables.affine;

import zeno.util.algebra.linear.matrix.Matrices;
import zeno.util.algebra.linear.matrix.Matrix;
import zeno.util.algebra.linear.vector.VSpace;
import zeno.util.algebra.linear.vector.VSpaces;
import zeno.util.algebra.linear.vector.Vector;
import zeno.util.geom.Affine;
import zeno.util.geom.collidables.ICollision;
import zeno.util.geom.collidables.affine.Point.Type;
import zeno.util.geom.collidables.collisions.affine.CLSASpace;
import zeno.util.geom.utilities.Geometries;
import zeno.util.tools.helper.Array;

/**
 * The {@code ASpace} class defines a standard euclidian affine space.
 * Affine spaces are immutable objects designed to produce various bases,
 * and allow for intersection and containment checks.
 *
 * @author Zeno
 * @since Apr 8, 2019
 * @version 1.0
 * 
 * 
 * @see Affine
 */
public class ASpace implements Affine
{
	@Override
	public ICollision Collisions()
	{
		return new CLSASpace(this);
	}
	
	
	private VSpace space;
	private Point origin;
	
	/**
	 * Creates a new {@code ASpace}.
	 * 
	 * @param o  an origin point
	 * @param s  a direction space
	 * 
	 * 
	 * @see Point
	 * @see VSpace
	 */
	public ASpace(Point o, VSpace s)
	{
		origin = o;
		space = s;
	}
	
	/**
	 * Creates a new {@code ASpace}.
	 * 
	 * @param o  an origin point
	 * @param v  a direction vector
	 * 
	 * 
	 * @see Point
	 * @see Vector
	 */
	public ASpace(Point o, Vector v)
	{
		space = new VSpace(v);
		origin = o;
	}
	
	
	/**
	 * Returns the origin of the {@code Affine Space}.
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
	 * Returns the direction of the {@code Affine Space}.
	 * 
	 * @return  a vector space direction
	 * 
	 * 
	 * @see VSpace
	 */
	public VSpace Direction()
	{
		return space;
	}
	
	/**
	 * Returns the dimension of the {@code Affine Space}.
	 * 
	 * @return  a space dimension
	 */
	public int Dimension()
	{
		return Direction().Dimension();
	}
	

	@Override
	public Factory Factory()
	{
		return (m) ->
		{
			Point o = null;
			Vector[] vecs = new Vector[0];
			for(int c = 0; c < m.Columns(); c++)
			{
				Point p = new Point(m.Column(c));
				if(p.Type() == Type.VECTOR)
				{
					Vector v = p.asVector();
					vecs = Array.add.to(vecs, v);
					continue;
				}
				
				if(o != null)
				{
					Vector v = Geometries.subtract(p, o);
					vecs = Array.add.to(vecs, v);
					continue;
				}
				
				o = p;
			}
			
			VSpace space = VSpaces.create(vecs);
			return Geometries.span(o, space);
		};
	}
	
	@Override
	public Matrix Span()
	{
		Matrix span = Direction().Span();
		float m = Origin().Mass();
		Point o = Origin();
		
		int cols = span.Columns();
		int rows = span.Rows();
		
		span = Matrices.resize(span, rows + 1, cols + 1);
		for(int c = 0; c <= cols; c++)
		{
			span.set(m, rows, c);
			for(int r = 0; r < rows; r++)
			{
				float val = o.get(r) + m * span.get(r, c);
				span.set(val, r, c);
			}
		}
		
		return span;
	}
}