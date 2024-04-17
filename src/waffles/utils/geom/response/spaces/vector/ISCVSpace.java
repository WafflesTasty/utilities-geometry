package waffles.utils.geom.response.spaces.vector;

import waffles.utils.algebra.elements.linear.matrix.Matrices;
import waffles.utils.algebra.elements.linear.matrix.Matrix;
import waffles.utils.algebra.elements.linear.vector.Vector;
import waffles.utils.algebra.elements.linear.vector.Vectors;
import waffles.utils.geom.Collision.Response;
import waffles.utils.geom.collidable.fixed.Point;
import waffles.utils.geom.collidable.spaces.VSpace;
import waffles.utils.tools.primitives.Integers;

/**
 * An {@code ISCVSpace} computes the intersection response between vector spaces.
 *
 * @author Waffles
 * @since 12 Sep 2023
 * @version 1.0
 * 
 * 
 * @see Response
 */
public class ISCVSpace implements Response
{
	private VSpace isc;
	private VSpace src, tgt;
	
	/**
	 * Creates a new {@code ISCVSpace}.
	 * 
	 * @param s  a source space
	 * @param t  a target space
	 * 
	 * 
	 * @see VSpace
	 */
	public ISCVSpace(VSpace s, VSpace t)
	{
		src = s;
		tgt = t;
	}

	
	@Override
	public VSpace Shape()
	{
		if(isc == null)
		{
			isc = computeShape();
		}

		return isc;
	}

	@Override
	public boolean hasImpact()
	{			
		return true;
	}
	
	@Override
	public Vector Penetration()
	{
		int dim = src.Dimension();
		return Vectors.create(dim);
	}

	@Override
	public Vector Distance()
	{
		return null;
	}
	
	@Override
	public Point Contact()
	{
		Vector pnt = Penetration();
		return new Point(pnt, 1f);
	}
	
	@Override
	public int Cost()
	{
		int rDim = src.Dimension();
		int sDim = src.Generators();
		int tDim = tgt.Generators();

		return Integers.pow(rDim * (sDim + tDim), 4);
	}
	
	
	VSpace computeShape()
	{
		VSpace sum = src.add(tgt);
		Matrix c = sum.ColComplement();
		int rows = src.Generators();		
		int cols = c.Columns();


		c = Matrices.resize(c, rows, cols);
		return new VSpace(src.evaluate(c));
	}
}