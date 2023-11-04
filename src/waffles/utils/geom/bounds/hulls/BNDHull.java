package waffles.utils.geom.bounds.hulls;

import waffles.utils.algebra.elements.linear.matrix.Matrix;
import waffles.utils.algebra.elements.linear.vector.Vector;
import waffles.utils.algebra.elements.linear.vector.Vectors;
import waffles.utils.geom.bounds.Bounds;
import waffles.utils.geom.collidable.convex.hulls.Hull;
import waffles.utils.tools.primitives.Floats;

/**
 * A {@code BNDHull} creates a bounding volume around an {@code Hull} object.
 *
 * @author Waffles
 * @since 15 Sep 2023
 * @version 1.0
 * 
 * 
 * @see Bounds
 */
public class BNDHull implements Bounds
{
	private Hull src;
	
	/**
	 * Creates a new {@code BNDHull}.
	 * 
	 * @param s  a source hull
	 * 
	 * 
	 * @see Hull
	 */
	public BNDHull(Hull s)
	{
		src = s;
	}
	
	
	@Override
	public Vector Minimum()
	{
		Matrix m = src.Generator();
		int cols = m.Columns();
		int rows = m.Rows();
		
		
		Vector min = Vectors.create(Floats.MAX_VALUE, rows);
		for(int r = 0; r < rows; r++)
		{
			for(int c = 0; c < cols; c++)
			{
				float val = m.get(r, c);
				if(val < min.get(r))
				{
					min.set(val, r);
				}
			}
		}
		
		return min;
	}
	
	@Override
	public Vector Maximum()
	{
		Matrix m = src.Generator();
		int cols = m.Columns();
		int rows = m.Rows();
		
		
		Vector max = Vectors.create(Floats.MIN_VALUE, rows);
		for(int r = 0; r < rows; r++)
		{
			for(int c = 0; c < cols; c++)
			{
				float val = m.get(r, c);
				if(val > max.get(r))
				{
					max.set(val, r);
				}
			}
		}
		
		return max;
	}
	
	@Override
	public float Radius()
	{
		Vector cen = Center();
		Matrix m = src.Generator();
		int cols = m.Columns();
		
		
		float rMax = Floats.MIN_VALUE;
		for(int c = 0; c < cols; c++)
		{
			Vector w = m.Column(c).minus(cen);
			float dist = w.normSqr();
			if(rMax < dist)
			{
				rMax = dist;
			}
		}
		
		rMax = Floats.sqrt(rMax);
		return rMax;
	}
}