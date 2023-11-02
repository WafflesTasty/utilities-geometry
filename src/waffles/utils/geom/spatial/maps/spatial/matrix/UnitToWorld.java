package waffles.utils.geom.spatial.maps.spatial.matrix;

import waffles.utils.algebra.elements.linear.matrix.Matrices;
import waffles.utils.algebra.elements.linear.matrix.Matrix;
import waffles.utils.algebra.elements.linear.matrix.types.orthogonal.Identity;
import waffles.utils.algebra.utilities.matrix.LazyMatrix;
import waffles.utils.geom.spatial.data.Spatial;
import waffles.utils.geom.spatial.maps.fixed.linear.Dilation;
import waffles.utils.geom.spatial.maps.fixed.linear.Rotation;
import waffles.utils.geom.spatial.maps.fixed.linear.Translation;

/**
 * The {@code UnitToWorld} class constructs a {@code SpatialMap}
 * matrix which transforms a unit object into world space.
 *
 * @author Waffles
 * @since 10 Sep 2023
 * @version 1.0
 * 
 * 
 * @see LazyMatrix
 */
public class UnitToWorld extends LazyMatrix
{
	private Spatial src;
	
	/**
	 * Creates a new {@code UnitToWorld}.
	 * 
	 * @param s  a source spatial
	 * 
	 * 
	 * @see Spatial
	 */
	public UnitToWorld(Spatial s)
	{
		src = s;
	}
	
	
	@Override
	public Matrix compute(Integer dim)
	{
		Matrix m = Matrices.identity(dim);
		m.setOperator(Identity.Type());

		m = dilate(m, dim).destroy();
		m = rotate(m, dim).destroy();
		m = translate(m, dim);

		return m;
	}
	
	Matrix translate(Matrix m, int dim)
	{
		Translation t = new Translation(src);
		Matrix mat = t.Matrix(dim).destroy();
		return mat.times(m);
	}
	
	Matrix rotate(Matrix m, int dim)
	{
		Rotation r = new Rotation(src);
		Matrix mat = r.Matrix(dim).destroy();
		return mat.times(m);
	}
	
	Matrix dilate(Matrix m, int dim)
	{
		Dilation d = new Dilation(src);
		Matrix mat = d.Matrix(dim).destroy();
		return mat.times(m);
	}
}