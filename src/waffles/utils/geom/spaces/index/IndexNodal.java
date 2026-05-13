package waffles.utils.geom.spaces.index;

import waffles.utils.alg.lin.measure.vector.Vector;
import waffles.utils.alg.lin.measure.vector.Vectors;
import waffles.utils.geom.shapes.convex.axial.cube.HyperCuboid;
import waffles.utils.geom.shapes.points.Arrow;
import waffles.utils.geom.shapes.points.Point;
import waffles.utils.geom.spaces.arboreal.axial.AxialNodal;
import waffles.utils.sets.utilities.indexed.coords.Coordination;

/**
 * An {@code IndexNodal} defines a spatial index {@code AxialNodal}.
 * Each node represents a {@code HyperCuboid} shape in n-dimensional
 * space which envelops a subset of a spatial {@code IndexedSet}.
 *
 * @author Waffles
 * @since 31 Jul 2020
 * @version 1.0
 * 
 * 
 * @see Coordination
 * @see HyperCuboid
 * @see AxialNodal
 */
public interface IndexNodal extends AxialNodal, Coordination, HyperCuboid
{
	/**
	 * Returns the space of the {@code IndexNodal}.
	 * 
	 * @return  an index space
	 * 
	 * 
	 * @see IndexSpace
	 */
	public default IndexSpace<?, ?> Space()
	{
		return (IndexSpace<?, ?>) Arch().Set();
	}
	
	
	@Override
	public default Point Origin()
	{
		int[] min = Minimum();
		int[] max = Maximum();
		int ord = Order();
		
		
		Arrow a = Space().TileSize();
		Vector o = Vectors.create(ord);
		for(int k = 0; k < ord; k++)
		{
			float v = min[k] + max[k] + 1;
			o.set(a.aff(k) * v / 2, k);
		}
		
		return new Point(o, 1f);
	}
	
	@Override
	public default Arrow Scale()
	{
		int[] dim = Dimensions();
		int ord = Order();
		
		
		Arrow a = Space().TileSize();
		Vector s = Vectors.create(ord);
		for(int k = 0; k < ord; k++)
		{
			s.set(dim[k] * a.aff(k), k);
		}
		
		return new Arrow(s);
	}
}