package waffles.utils.geom.spaces.axial.or;

import waffles.utils.alg.lin.measure.vector.Vector;
import waffles.utils.alg.lin.measure.vector.Vectors;
import waffles.utils.geom.shapes.convex.axial.cube.HyperCuboid;
import waffles.utils.geom.shapes.points.Arrow;
import waffles.utils.geom.shapes.points.Point;
import waffles.utils.geom.spaces.axial.AxialNodal;
import waffles.utils.geom.spatial.bounds.owners.Bounded;
import waffles.utils.sets.arboreal.Arboreal;
import waffles.utils.tools.primitives.Integers;

/**
 * An {@code OrtoNodal} defines an orthogonal {@code AxialNodal}.
 * Each node represents a {@code HyperCuboid} shape in n-dimensional
 * space, and can be partitioned in 2^n equal sized child cuboids.
 *
 * @author Waffles
 * @since 31 Jul 2020
 * @version 1.0
 * 
 * 
 * @see HyperCuboid
 * @see AxialNodal
 */
@FunctionalInterface
public interface OrtoNodal extends AxialNodal, HyperCuboid
{		
	@Override
	public abstract OrtoNode Arch();
		
	/**
	 * Queries an index in the {@code OrtoNodal}.
	 * If one of its children supports the object,
	 * its index is returned. Otherwise, this
	 * method returns -1.
	 * 
	 * @param b  a bounded object
	 * @return   a child index
	 * 
	 * 
	 * @see Bounded
	 */
	public default int index(Bounded b)
	{
		Point o = Origin();
		Point min = b.Bounds().Minimum();
		Point max = b.Bounds().Maximum();
		
	
		int idx = 0;
		for(int k = 0; k < Dimension(); k++)
		{
			float ok = o.aff(k);
			if(ok < min.aff(k))
			{
				idx += Integers.pow(2, k);
				continue;
			}
			
			if(ok < max.aff(k))
			{
				return -1;
			}
		}
		
		return idx;
	}
	
	/**
	 * Queries an index in the {@code OrtoNodal}.
	 * If one of its children contains the point,
	 * its index is returned. Otherwise, this
	 * method returns -1.
	 * 
	 * @param p  a target point
	 * @return   a child index
	 * 
	 * 
	 * @see Bounded
	 */
	public default int index(Point p)
	{
		int idx = 0;
		for(int k = 0; k < Dimension(); k++)
		{
			float ok = Origin().aff(k);
			if(ok < p.aff(k))
			{
				idx += Integers.pow(2, k);
			}
		}
		
		return idx;
	}

	/**
	 * Splits the {@code OrtoNodal}.
	 */
	public default void split()
	{
		int d = Dimension();
		Arrow s = Scale().times(0.5f);
		Arboreal.Factory fct = Arch().Set().Factory();
		for(int i = 0; i < Integers.pow(2, d); i++)
		{
			Vector v = Vectors.create(d);
			for(int j = 0; j < d; j++)
			{
				float val = Origin().aff(j);
				if(Integers.bitAt(i, j) == 0)
					val -= s.aff(j) / 2;
				else
					val += s.aff(j) / 2;
				
				
				v.set(val, j);
			}
			
			Point o = new Point(v, 1f);			
			Arch().addChild(fct.node(o, s));
		}
	}
}