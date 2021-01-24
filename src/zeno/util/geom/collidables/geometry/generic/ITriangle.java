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
	 * @see Point
	 */
	public abstract Point P1();
	
	/**
	 * Returns the second point of the {@code ITriangle}.
	 * 
	 * @return  the triangle's second point
	 * 
	 * 
	 * @see Point
	 */
	public abstract Point P2();
	
	/**
	 * Returns the third point of the {@code ITriangle}.
	 * 
	 * @return  the triangle's third point
	 * 
	 * 
	 * @see Point
	 */
	public abstract Point P3();
	
	
	@Override
	public default Vector Extremum(Vector v)
	{
		Vector p1 = P1().asVector();
		Vector p2 = P2().asVector();
		Vector p3 = P3().asVector();
		
		float d1 = p2.minus(p1).normalize().dot(v.normalize());
		float d2 = p3.minus(p2).normalize().dot(v.normalize());
		float d3 = p1.minus(p3).normalize().dot(v.normalize());

		if(d1 < 0)
		{
			if(d3 > 0)
			{
				return p1;
			}
						
			return p3;
		}
		
		if(d2 < 0)
		{
			return p2;
		}
		
		return p3;
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

				if(p1.Type() != Type.AFFINE) return null;
				if(p2.Type() != Type.AFFINE) return null;
				if(p3.Type() != Type.AFFINE) return null;
				
				return Geometries.triangle(p1, p2, p3);
			}
			
			return null;
		};
	}
	
	@Override
	public default Matrix Span()
	{
		return Matrices.fromCols(P1().Span(), P2().Span(), P3().Span());
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
	
	@Override
	public default int Dimension()
	{
		return P1().Size();
	}
}