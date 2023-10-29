package waffles.utils.geom.maps.project;

import waffles.utils.algebra.elements.linear.matrix.Matrix;
import waffles.utils.algebra.utilities.matrix.LazyMatrix;
import waffles.utils.geom.maps.linear.Dilation;
import waffles.utils.geom.maps.linear.Projection;
import waffles.utils.geom.maps.linear.Rotation;
import waffles.utils.geom.maps.linear.Translation;
import waffles.utils.geom.spatial.data.Watcher;

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
		Matrix mat = new Translation(src).Inverse(dim);
		mat = new Rotation(src).Inverse(dim).times(mat);
		mat = new Projection(src).Matrix(dim).times(mat);
		mat = new Dilation(src).Inverse(dim).times(mat);
		
		return mat;
	}
}