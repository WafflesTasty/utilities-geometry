package waffles.utils.geom.spatial.maps.fixed;

import waffles.utils.algebra.elements.linear.Affine;
import waffles.utils.algebra.elements.linear.matrix.Matrices;
import waffles.utils.algebra.elements.linear.matrix.Matrix;
import waffles.utils.algebra.elements.linear.matrix.types.orthogonal.Identity;
import waffles.utils.algebra.utilities.matrix.LazyMatrix;
import waffles.utils.geom.spatial.data.Axial;
import waffles.utils.geom.spatial.maps.axial.AxialMap;
import waffles.utils.geom.spatial.structs.Axis;

/**
 * An {@code IdentityMap} defines a global one-to-one identity map.
 *
 * @author Waffles
 * @since 15 Sep 2023
 * @version 1.0
 * 
 * 
 * @see AxialMap
 */
public class IdentityMap implements AxialMap
{	
	@Override
	public Affine unmap(Affine val)
	{
		return val;
	}
	
	@Override
	public Affine map(Affine val)
	{
		return val;
	}

	@Override
	public Axial Source()
	{
		return new Axis();
	}
	

	@Override
	public LazyMatrix UTW()
	{
		return new LazyMatrix(dim ->
		{
			Matrix m = Matrices.identity(dim);
			m.setOperator(Identity.Type());
			return m;
		});
	}

	@Override
	public LazyMatrix WTU()
	{
		return new LazyMatrix(dim ->
		{
			Matrix m = Matrices.identity(dim);
			m.setOperator(Identity.Type());
			return m;
		});
	}
}