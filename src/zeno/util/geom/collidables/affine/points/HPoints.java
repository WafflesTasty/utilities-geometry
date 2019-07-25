package zeno.util.geom.collidables.affine.points;

import zeno.util.algebra.linear.matrix.Matrix;
import zeno.util.geom.collidables.Affine;
import zeno.util.geom.collidables.affine.ASpaces;
import zeno.util.tools.Floats;

/**
 * The {@code HPoints} class defines an affine set from a homogeneous matrix.
 *
 * @author Zeno
 * @since Jul 14, 2019
 * @version 1.0
 * 
 * 
 * @see Affine
 */
public class HPoints implements Affine.Set
{
	private Matrix hmat;
	
	/**
	 * Creates a new {@code HPoints}.
	 * 
	 * @param m  an affine matrix
	 * 
	 * 
	 * @see Matrix
	 */
	public HPoints(Matrix m)
	{
		hmat = m;
	}

	
	@Override
	public float get(int r, int c)
	{	
		int rows = hmat.Rows() - 1;		
		if(r < rows)
		{
			float val = hmat.get(r, c);
			if(!Floats.isZero(hmat.get(rows, c), 1))
			{
				val /= hmat.get(rows, c);
			}
			
			return val;
		}
		
		return 0f;
	}
	
	@Override
	public Matrix VMatrix()
	{
		return ASpaces.vectorize(hmat);
	}

	@Override
	public Matrix HMatrix()
	{
		return hmat;
	}

	@Override
	public int Size()
	{
		return hmat.Columns();
	}
}