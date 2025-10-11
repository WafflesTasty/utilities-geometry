package waffles.utils.geom.utilities.tform;

import waffles.utils.alg.lin.measure.matrix.Matrices;
import waffles.utils.alg.lin.measure.matrix.Matrix;
import waffles.utils.alg.lin.measure.matrix.types.orthogonal.Identity;
import waffles.utils.alg.utilities.matrix.LazyMatrix;
import waffles.utils.geom.spatial.maps.GlobalMap;

/**
 * A {@code GlobalCompose} defines a {@code GlobalMap} composition.
 *
 * @author Waffles
 * @since 15 Sep 2023
 * @version 1.0
 * 
 * 
 * @see GlobalMap
 */
public class GlobalCompose implements GlobalMap
{
	private GlobalMap[] maps;
	
	/**
	 * Creates a new {@code GlobalCompose}.
	 * 
	 * @param set  a map set
	 * 
	 * 
	 * @see GlobalMap
	 */
	public GlobalCompose(GlobalMap... set)
	{
		maps = set;
	}
	
	
	@Override
	public LazyMatrix UTW()
	{
		return new LazyMatrix(dim ->
		{
			Matrix utw = Matrices.identity(dim);
			utw.setOperator(Identity.Type());
			
			Matrix mat;
			for(GlobalMap map : maps)
			{
				mat = map.UTW().Value(dim);
				utw = utw.times(mat);
			}

			return utw;
		});
	}

	@Override
	public LazyMatrix WTU()
	{
		return new LazyMatrix(dim ->
		{			
			Matrix wtu = Matrices.identity(dim);
			wtu.setOperator(Identity.Type());
			
			Matrix mat;
			for(GlobalMap map : maps)
			{
				mat = map.WTU().Value(dim);
				wtu = mat.times(wtu);
			}

			return wtu;
		});
	}
}