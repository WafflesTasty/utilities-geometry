package waffles.utils.geom.maps.affine.standard;

import waffles.utils.algebra.elements.linear.matrix.Matrices;
import waffles.utils.algebra.elements.linear.matrix.Matrix;
import waffles.utils.algebra.elements.linear.matrix.types.orthogonal.Identity;
import waffles.utils.algebra.elements.linear.vector.Vector;
import waffles.utils.algebra.utilities.matrix.LazyMatrix;
import waffles.utils.geom.maps.linear.Dilation;
import waffles.utils.geom.maps.linear.Rotation;
import waffles.utils.geom.maps.linear.Translation;
import waffles.utils.geom.spatial.Spatial;
import waffles.utils.geom.spatial.spin.Spin;

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
		Vector o = src.Origin();
		if(o != null)
		{
			Matrix t = new Translation(o).Inverse(dim);
			return t.destroy().times(m);
		}
		
		return m;
	}
	
	Matrix rotate(Matrix m, int dim)
	{
		Spin s = src.Spin();
		if(s != null)
		{
			Matrix r = new Rotation(s).Inverse(dim);
			return r.destroy().times(m);
		}
		
		return m;
	}
	
	Matrix dilate(Matrix m, int dim)
	{
		Vector s = src.Size();
		if(s != null)
		{
			// Divided by two because it scales in both
			// the positive and negative direction of axes.
			s = s.times(0.5f);
			Matrix d = new Dilation(s).Inverse(dim);
			return d.destroy().times(m);
		}
		
		return m;
	}
}