package waffles.utils.geom.maps.project;

import waffles.utils.algebra.elements.linear.matrix.Matrix;
import waffles.utils.algebra.utilities.matrix.LazyMatrix;
import waffles.utils.geom.maps.linear.Dilation;
import waffles.utils.geom.maps.linear.Projection;
import waffles.utils.geom.maps.linear.Rotation;
import waffles.utils.geom.maps.linear.Translation;
import waffles.utils.geom.spatial.data.Watcher;

/**
 * The {@code CamToWorld} class constructs a {@code Camera}
 * matrix which transforms a camera object to world space.
 *
 * @author Waffles
 * @since 11 Sep 2023
 * @version 1.0
 *
 * 
 * @see LazyMatrix
 */
public class CamToWorld extends LazyMatrix
{
	private Watcher src;

	/**
	 * Creates a new {@code CamToWorld}.
	 * 
	 * @param s  a source watcher
	 * 
	 * 
	 * @see Watcher
	 */
	public CamToWorld(Watcher s)
	{
		src = s;
	}
	

	@Override
	public Matrix compute(Integer dim)
	{
		Matrix inv = new Dilation(src).Matrix(dim);
		inv = new Projection(src).Inverse(dim).times(inv);
		inv = new Rotation(src).Matrix(dim).times(inv);
		inv = new Translation(src).Matrix(dim).times(inv);
		
		return inv;
	}
}