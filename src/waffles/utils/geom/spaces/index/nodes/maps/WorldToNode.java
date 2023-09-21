package waffles.utils.geom.spaces.index.nodes.maps;

import waffles.utils.algebra.elements.linear.matrix.Matrices;
import waffles.utils.algebra.elements.linear.matrix.Matrix;
import waffles.utils.algebra.utilities.matrix.LazyMatrix;
import waffles.utils.geom.spaces.index.nodes.IndexNode;

/**
 * The {@code WorldToNode} class constructs a {@code LazyMatrix}
 * which transforms a world space node onto the unit cube.
 *
 * @author Waffles
 * @since 20 Sep 2023
 * @version 1.0
 * 
 * 
 * @see LazyMatrix
 */
public class WorldToNode extends LazyMatrix
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
	public WorldToNode(IndexNode src)
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
				float ui = max[i] + min[i];
				float di = max[i] - min[i];
				
				float si = 2 / (s * di);
				float ci = -ui / di;
				
				mat.set(ci, i, dim-1);
				mat.set(si, i, i);
			}
		}	

		return mat;
	}
}