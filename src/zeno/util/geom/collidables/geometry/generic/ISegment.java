package zeno.util.geom.collidables.geometry.generic;

import zeno.util.algebra.linear.matrix.Matrices;
import zeno.util.algebra.linear.matrix.Matrix;
import zeno.util.algebra.linear.vector.Vector;
import zeno.util.geom.ITransformation;
import zeno.util.geom.collidables.affine.Point;
import zeno.util.geom.collidables.affine.Point.Type;
import zeno.util.geom.collidables.bounds.Bounds;
import zeno.util.geom.collidables.collisions.geometry.CLSSegment;
import zeno.util.geom.utilities.Geometries;

/**
 * The {@code ISegment} interface defines the collision operations for line segment geometry.
 * 
 * @author Zeno
 * @since Mar 25, 2017
 * @version 1.0
 * 
 * 
 * @see IHull
 */
public interface ISegment extends IHull
{		
	/**
	 * Returns the first point of the {@code ISegment}.
	 * 
	 * @return  the segment's first point
	 * 
	 * 
	 * @see Vector
	 */
	public abstract Vector P1();
	
	/**
	 * Returns the second point of the {@code ISegment}.
	 * 
	 * @return  the segment's second point
	 * 
	 * 
	 * @see Vector
	 */
	public abstract Vector P2();
	
	
//	@Override
//	public default Vector Extremum(Vector v)
//	{
//		Vector cen = Center();
//		Vector min = Minimum();
//		Vector max = Maximum();
//		
//		Vector e = Vectors.create(Dimension());
//		for(int i = 0; i < Dimension(); i++)
//		{
//			if(Floats.isZero(v.get(i), 1))
//				e.set(cen.get(i), i);
//			else if(v.get(i) < 0)
//				e.set(min.get(i), i);
//			else
//				e.set(max.get(i), i);
//		}
//
//		return e;
//	}
	
	@Override
	public default Bounds Bounds(ITransformation map)
	{
		return ((ISegment) map.map(this)).Bounds();
	}
	
	@Override
	public default CLSSegment Collisions()
	{
		return new CLSSegment(this);
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
			
			if(m.Columns() == 2)
			{
				Point p1 = new Point(m.Column(0));
				Point p2 = new Point(m.Column(1));
				
				Vector v1 = p1.asVector();
				Vector v2 = p2.asVector();
				
				if(p1.Type() == Type.AFFINE)
				{
					if(p2.Type() == Type.AFFINE)
					{		
						return Geometries.segment(v1, v2);
					}
					
					return Geometries.span(p1, v2);
				}
				
				if(p2.Type() == Type.AFFINE)
				{
					return Geometries.span(p2, v1);
				}
				
				return Geometries.VOID;
			}
			
			return null;
		};
	}
	
	@Override
	public default Matrix Vertices()
	{
		return Matrices.fromCols(P1(), P2());
	}
	
	
	// Obligatory Bounds overrides.
	
	@Override
	public default Vector Center()
	{
		return P1().plus(P2()).times(0.5f);
	}
	
	@Override
	public default Vector Size()
	{
		return P2().minus(P1()).Absolute();
	}
}