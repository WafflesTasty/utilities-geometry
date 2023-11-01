package waffles.utils.geom.spatial.maps.spatial.matrix;

import waffles.utils.algebra.elements.linear.matrix.Matrices;
import waffles.utils.algebra.elements.linear.matrix.Matrix;
import waffles.utils.algebra.elements.linear.matrix.types.orthogonal.Identity;
import waffles.utils.algebra.utilities.matrix.LazyMatrix;
import waffles.utils.geom.spatial.data.Spatial;
import waffles.utils.geom.spatial.maps.linear.Dilation;
import waffles.utils.geom.spatial.maps.linear.Rotation;
import waffles.utils.geom.spatial.maps.linear.Translation;

/**
 * The {@code WorldToUnit} class constructs an {@code SpatialMap}
 * matrix which transforms a world object into unit space.
 *
 * @author Waffles
 * @since 10 Sep 2023
 * @version 1.0
 * 
 * 
 * @see LazyMatrix
 */
public class WorldToUnit extends LazyMatrix
{
	private Spatial src;
	
	/**
	 * Creates a new {@code WorldToUnit}.
	 * 
	 * @param s  a spatial source
	 * 
	 * 
	 * @see Spatial
	 */
	public WorldToUnit(Spatial s)
	{
		src = s;
	}


	@Override
	public Matrix compute(Integer dim)
	{
		Matrix m = Matrices.identity(dim);
		m.setOperator(Identity.Type());

		m = translate(m, dim).destroy();
		m = rotate(m, dim).destroy();
		m = dilate(m, dim);

		return m;
	}
	
	Matrix translate(Matrix m, int dim)
	{
		Translation t = new Translation(src);
		Matrix mat = t.Inverse(dim).destroy();
		return mat.times(m);
	}
	
	Matrix rotate(Matrix m, int dim)
	{
		Rotation r = new Rotation(src);
		Matrix mat = r.Inverse(dim).destroy();
		return mat.times(m);
	}
	
	Matrix dilate(Matrix m, int dim)
	{
		Dilation d = new Dilation(src);
		Matrix mat = d.Inverse(dim).destroy();
		return mat.times(m);
	}
}