package waffles.utils.geom.spatial.maps.axial.matrix;

import waffles.utils.algebra.elements.linear.matrix.Matrices;
import waffles.utils.algebra.elements.linear.matrix.Matrix;
import waffles.utils.algebra.elements.linear.matrix.types.orthogonal.Identity;
import waffles.utils.algebra.utilities.matrix.LazyMatrix;
import waffles.utils.geom.spatial.data.Axial;
import waffles.utils.geom.spatial.maps.fixed.linear.Dilation;
import waffles.utils.geom.spatial.maps.fixed.linear.Translation;

/**
 * The {@code WorldToUnit} class constructs an {@code AffineMap}
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
	private Axial src;
	
	/**
	 * Creates a new {@code WorldToUnit}.
	 * 
	 * @param s  an axial source
	 * 
	 * 
	 * @see Axial
	 */
	public WorldToUnit(Axial s)
	{
		src = s;
	}


	@Override
	public Matrix compute(Integer dim)
	{
		Matrix m = Matrices.identity(dim);
		m.setOperator(Identity.Type());

		m = translate(m, dim).destroy();
		m = dilate(m, dim);

		return m;
	}
	
	Matrix translate(Matrix m, int dim)
	{
		Translation t = new Translation(src);
		Matrix mat = t.Inverse(dim).destroy();
		return mat.times(m);
	}

	Matrix dilate(Matrix m, int dim)
	{
		Dilation d = new Dilation(src);
		Matrix mat = d.Inverse(dim).destroy();
		return mat.times(m);
	}
}