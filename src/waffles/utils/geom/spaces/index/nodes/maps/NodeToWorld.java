package waffles.utils.geom.spaces.index.nodes.maps;

import waffles.utils.algebra.elements.linear.matrix.Matrices;
import waffles.utils.algebra.elements.linear.matrix.Matrix;
import waffles.utils.algebra.utilities.matrix.LazyMatrix;
import waffles.utils.geom.spaces.index.nodes.IndexNode;

/**
 * The {@code NodeToWorld} class constructs a {@code LazyMatrix}
 * which transforms a unit node into world space.
 *
 * @author Waffles
 * @since 20 Sep 2023
 * @version 1.0
 * 
 * 
 * @see LazyMatrix
 */
public class NodeToWorld extends LazyMatrix
{
	private IndexNode src;
	
	/**
	 * Creates a new {@code NodeToWorld}.
	 * 
	 * @param src  a source node
	 * 
	 * 
	 * @see IndexNode
	 */
	public NodeToWorld(IndexNode src)
	{
		this.src = src;
	}
	
	
	@Override
	public Matrix compute(Integer dim)
	{
		int[] min = src.Minimum();
		int[] max = src.Maximum();
		
		
		float s = src.Space().TileSize();
		Matrix mat = Matrices.create(dim, dim);
		for(int i = 0; i < dim; i++)
		{
			if(src.Space().Order() <= i)
				mat.set(1f, i, i);
			else
			{
				float si = s * (max[i] - min[i] + 1);
				float ci = s * (min[i] + max[i] + 1);
				
				mat.set(ci / 2, i, dim-1);
				mat.set(si / 2, i, i);
			}
		}	

		return mat;
	}
}