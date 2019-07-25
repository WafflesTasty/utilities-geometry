package zeno.util.geom.collidables.affine.points;

import zeno.util.algebra.linear.matrix.Matrix;
import zeno.util.geom.collidables.Affine;
import zeno.util.geom.collidables.affine.ASpaces;

/**
 * The {@code VPoints} class defines an affine set from a vectorized matrix.
 *
 * @author Zeno
 * @since Jul 14, 2019
 * @version 1.0
 * 
 * 
 * @see Affine
 */
public class VPoints implements Affine.Set
{
	private Matrix vmat;
	
	/**
	 * Creates a new {@code VPoints}.
	 * 
	 * @param m  an affine matrix
	 * 
	 * 
	 * @see Matrix
	 */
	public VPoints(Matrix m)
	{
		vmat = m;
	}
	
	
	@Override
	public float get(int r, int c)
	{
		int rows = vmat.Rows();
		if(r < rows)
		{
			return vmat.get(r, c);
		}
		
		return 0f;
	}	
	
	@Override
	public Matrix VMatrix()
	{
		return vmat;
	}

	@Override
	public Matrix HMatrix()
	{
		return ASpaces.homogenize(vmat);
	}

	@Override
	public int Size()
	{
		return vmat.Columns();
	}
}