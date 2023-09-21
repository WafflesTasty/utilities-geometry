package waffles.utils.geom.spaces.binary.kd.maps;

import waffles.utils.algebra.elements.linear.matrix.Matrices;
import waffles.utils.algebra.elements.linear.matrix.Matrix;
import waffles.utils.algebra.elements.linear.vector.Vector;
import waffles.utils.algebra.utilities.matrix.LazyMatrix;
import waffles.utils.geom.spaces.binary.kd.KDNode;
import waffles.utils.tools.primitives.Floats;

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
	private KDNode<?> src;
	private Vector min, max;
	
	/**
	 * Creates a new {@code NodeToWorld}.
	 * 
	 * @param src  a source node
	 * 
	 * 
	 * @see KDNode
	 */
	public NodeToWorld(KDNode<?> src)
	{
		this.src = src;
	}
	
	
	private void computeExtrema()
	{
		min = src.Set().Bounds().Minimum();
		max = src.Set().Bounds().Maximum();
		
		
		KDNode<?> node = src;
		while(node.Parent() != null)
		{
			KDNode<?> p = node.Parent();
			int dim = p.Cut().Dimension();
			float val = p.Cut().Value();
			
			
			if(node.equals(p.LChild()))
				max.set(Floats.min(val, max.get(dim)), dim);
			else
				min.set(Floats.max(val, min.get(dim)), dim);
			
			node = p;
		}
	}
	
	@Override
	public Matrix compute(Integer dim)
	{		
		computeExtrema();

		Vector c = min.plus(max);
		Vector s = max.minus(min);

		
		Matrix mat = Matrices.create(dim, dim);
		for(int i = 0; i < dim; i++)
		{
			if(src.Set().Dimension() <= i)
				mat.set(1f, i, i);
			else
			{
				float si = s.get(i) / 2;
				float ci = c.get(i) / 2;
				
				mat.set(ci, i, dim-1);
				mat.set(si, i, i);
			}
		}	

		return mat;
	}
}