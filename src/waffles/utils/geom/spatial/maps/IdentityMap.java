package waffles.utils.geom.spatial.maps;

import waffles.utils.alg.lin.measure.matrix.Matrices;
import waffles.utils.alg.lin.measure.matrix.Matrix;
import waffles.utils.alg.lin.measure.matrix.types.orthogonal.Identity;
import waffles.utils.alg.utilities.affine.Affine;
import waffles.utils.alg.utilities.matrix.LazyMatrix;

/**
 * An {@code IdentityMap} defines a one-to-one identity map.
 *
 * @author Waffles
 * @since 15 Sep 2023
 * @version 1.0
 * 
 * 
 * @see GlobalMap
 */
public class IdentityMap implements GlobalMap
{
	private LazyMatrix utw;
	private LazyMatrix wtu;
	
	/**
	 * Creates a new {@code IdentityMap}.
	 */
	public IdentityMap()
	{
		utw = identity();
		wtu = identity();
	}
	
		
	@Override
	public LazyMatrix UTW()
	{
		return utw;
	}
	
	@Override
	public LazyMatrix WTU()
	{
		return wtu;
	}
	
	
	@Override
	public Affine map(Affine a)
	{
		return a;
	}
	
	@Override
	public Affine unmap(Affine a)
	{
		return a;
	}
	
	LazyMatrix identity()
	{
		return new LazyMatrix(dim ->
		{
			Matrix m = Matrices.identity(dim);
			m.setOperator(Identity.Type());
			return m;
		});
	}
}