package zeno.util.geom.collidables.geometry.generic;

import zeno.util.algebra.linear.matrix.Matrices;
import zeno.util.algebra.linear.matrix.Matrix;
import zeno.util.algebra.linear.vector.Vector;
import zeno.util.algebra.linear.vector.Vectors;
import zeno.util.geom.Affine;
import zeno.util.geom.ITransformation;
import zeno.util.geom.collidables.ICollision;
import zeno.util.geom.collidables.affine.Point;
import zeno.util.geom.collidables.affine.Point.Type;
import zeno.util.geom.collidables.bounds.Bounds;
import zeno.util.geom.collidables.collisions.geometry.CLSConvex;
import zeno.util.geom.utilities.Geometries;
import zeno.util.tools.Floats;

/**
 * The {@code ITriangle} interface defines the collision operations for triangle geometry.
 * 
 * @author Zeno
 * @since 13 Jan 2021
 * @version 1.0 
 * 
 * 
 * @see IConvex
 * @see Affine
 */
public interface ITriangle extends Affine, IConvex
{
	/**
	 * Returns the first point of the {@code ITriangle}.
	 * 
	 * @return  the triangle's first point
	 * 
	 * 
	 * @see Vector
	 */
	public abstract Vector P1();
	
	/**
	 * Returns the second point of the {@code ITriangle}.
	 * 
	 * @return  the triangle's second point
	 * 
	 * 
	 * @see Vector
	 */
	public abstract Vector P2();
	
	/**
	 * Returns the third point of the {@code ITriangle}.
	 * 
	 * @return  the triangle's third point
	 * 
	 * 
	 * @see Vector
	 */
	public abstract Vector P3();
	
	
	@Override
	public default Vector Extremum(Vector v)
	{
		Vector c = Center();
		
		float d1 = v.dot(P1().minus(c));
		float d2 = v.dot(P2().minus(c));
		float d3 = v.dot(P3().minus(c));
		
		if(d1 < d2)
		{
			if(d2 < d3)
			{
				return P3();
			}
			
			return P2();
		}

		return P1();
	}
	
	@Override
	public default Bounds Bounds(ITransformation map)
	{
		return ((ITriangle) map.map(this)).Bounds();
	}
	
	@Override
	public default ICollision Collisions()
	{
		return new CLSConvex(this);
	}
	
	@Override
	public default Factory Factory()
	{
		return (m) ->
		{
			if(m.Columns() == 0)
			{
				return Geometries.VOID;
			}
			
			if(m.Columns() == 3)
			{
				Point p1 = new Point(m.Column(0));
				Point p2 = new Point(m.Column(1));
				Point p3 = new Point(m.Column(2));
				
				Vector v1 = p1.asVector();
				Vector v2 = p2.asVector();
				Vector v3 = p3.asVector();
				
				if(p1.Type() != Type.AFFINE) return null;
				if(p2.Type() != Type.AFFINE) return null;
				if(p3.Type() != Type.AFFINE) return null;
				
				return Geometries.triangle(v1, v2, v3);
			}
			
			return null;
		};
	}
	
	@Override
	public default Matrix Span()
	{
		return Matrices.fromCols(P1(), P2(), P3());
	}
	
	// Obligatory Bounds overrides.
	
	@Override
	public default Vector Minimum()
	{
		Vector min = Vectors.create(Dimension());
		for(int i = 0; i < Dimension(); i++)
		{
			float mi = Floats.min(P1().get(i), P2().get(i), P3().get(i));
			min.set(mi, i);
		}
		
		return min;
	}
	
	@Override
	public default Vector Maximum()
	{
		Vector max = Vectors.create(Dimension());
		for(int i = 0; i < Dimension(); i++)
		{
			float mi = Floats.max(P1().get(i), P2().get(i), P3().get(i));
			max.set(mi, i);
		}
		
		return max;
	}
}