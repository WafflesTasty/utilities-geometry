package waffles.utils.geom.collision.hulls.general;

import waffles.utils.algebra.elements.linear.vector.Vector;
import waffles.utils.algebra.elements.linear.vector.Vectors;
import waffles.utils.geom.collidable.hulls.IHull;
import waffles.utils.geom.collidable.spaces.lines.Line;

/**
 * An {@code INHLine} computes an inhabiting response of a convex hull and a line.
 *
 * @author Waffles
 * @since 12 May 2021
 * @version 1.0
 * 
 * 
 * @see INHASpace
 */
public class INHLine extends INHASpace
{
	private int dim;
	
	/**
	 * Creates a new {@code INHLine}.
	 * 
	 * @param s  a source hull
	 * @param t  a target line
	 * 
	 * 
	 * @see IHull
	 * @see Line
	 */
	public INHLine(IHull s, Line t)
	{
		super(s, t);
		dim = s.Dimension();
	}


	@Override
	public Vector Penetration()
	{
		if(hasImpact())
		{
			return Vectors.create(dim);
		}
		
		return null;
	}
	
	@Override
	public Vector Distance()
	{
		if(!hasImpact())
		{
			return Vectors.create(dim);
		}
		
		return null;
	}
}