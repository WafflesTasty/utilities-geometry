package waffles.utils.geom.spatial.maps.fixed.compose;

import waffles.utils.algebra.elements.linear.matrix.Matrices;
import waffles.utils.algebra.elements.linear.matrix.Matrix;
import waffles.utils.algebra.elements.linear.matrix.types.orthogonal.Identity;
import waffles.utils.algebra.utilities.matrix.LazyMatrix;
import waffles.utils.geom.spatial.maps.GlobalMap;

/**
 * A {@code GlobalCompose} defines a sequence of global maps.
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
	 * @param maps  a map set
	 * 
	 * 
	 * @see GlobalMap
	 */
	public GlobalCompose(GlobalMap... maps)
	{
		this.maps = maps;
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