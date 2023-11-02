package waffles.utils.geom.spatial.maps.project.matrix;

import waffles.utils.algebra.elements.linear.matrix.Matrices;
import waffles.utils.algebra.elements.linear.matrix.Matrix;
import waffles.utils.algebra.elements.linear.matrix.types.orthogonal.Identity;
import waffles.utils.algebra.utilities.matrix.LazyMatrix;
import waffles.utils.geom.spatial.data.Watcher;
import waffles.utils.geom.spatial.maps.fixed.linear.Dilation;
import waffles.utils.geom.spatial.maps.fixed.linear.Projection;
import waffles.utils.geom.spatial.maps.fixed.linear.Rotation;
import waffles.utils.geom.spatial.maps.fixed.linear.Translation;

/**
 * The {@code WorldToCam} class constructs a {@code Camera}
 * matrix which transforms a world object to camera space.
 *
 * @author Waffles
 * @since 11 Sep 2023
 * @version 1.0
 * 
 * 
 * @see LazyMatrix
 */
public class WorldToCam extends LazyMatrix
{
	private Watcher src;

	/**
	 * Creates a new {@code WorldToCam}.
	 * 
	 * @param s  a source projectable
	 * 
	 * 
	 * @see Watcher
	 */
	public WorldToCam(Watcher s)
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
		m = project(m, dim).destroy();
		m = dilate(m, dim);

		return m;
	}
		
	Matrix translate(Matrix m, int dim)
	{
		Translation t = new Translation(src);
		Matrix mat = t.Inverse(dim).destroy();
		return mat.times(m);
	}
	
	Matrix project(Matrix m, int dim)
	{
		Projection p = new Projection(src);
		Matrix mat = p.Matrix(dim).destroy();
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