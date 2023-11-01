package waffles.utils.geom.spatial.maps.axial.matrix;

import waffles.utils.algebra.elements.linear.matrix.Matrices;
import waffles.utils.algebra.elements.linear.matrix.Matrix;
import waffles.utils.algebra.elements.linear.matrix.types.orthogonal.Identity;
import waffles.utils.algebra.utilities.matrix.LazyMatrix;
import waffles.utils.geom.spatial.data.Axial;
import waffles.utils.geom.spatial.maps.linear.Dilation;
import waffles.utils.geom.spatial.maps.linear.Translation;

/**
 * The {@code UnitToWorld} class constructs an {@code AxialMap}
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
	private Axial src;
	
	/**
	 * Creates a new {@code UnitToWorld}.
	 * 
	 * @param s  a source axial
	 * 
	 * 
	 * @see Axial
	 */
	public UnitToWorld(Axial s)
	{
		src = s;
	}
	
	
	@Override
	public Matrix compute(Integer dim)
	{
		Matrix m = Matrices.identity(dim);
		m.setOperator(Identity.Type());

		m = dilate(m, dim).destroy();
		m = translate(m, dim);

		return m;
	}
	
	Matrix translate(Matrix m, int dim)
	{
		Translation t = new Translation(src);
		Matrix mat = t.Matrix(dim).destroy();
		return mat.times(m);
	}

	Matrix dilate(Matrix m, int dim)
	{
		Dilation d = new Dilation(src);
		Matrix mat = d.Matrix(dim).destroy();
		return mat.times(m);
	}
}