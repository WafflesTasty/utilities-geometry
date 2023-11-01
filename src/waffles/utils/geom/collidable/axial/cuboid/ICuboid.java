package waffles.utils.geom.collidable.axial.cuboid;

import waffles.utils.algebra.elements.linear.matrix.Matrices;
import waffles.utils.algebra.elements.linear.matrix.Matrix;
import waffles.utils.algebra.elements.linear.vector.Vector;
import waffles.utils.geom.Collision;
import waffles.utils.geom.bounds.Bounds;
import waffles.utils.geom.bounds.axial.BNDAxial;
import waffles.utils.geom.bounds.axial.cuboid.BNDCuboid;
import waffles.utils.geom.collidable.axial.IAxialSet;
import waffles.utils.geom.collidable.convex.hulls.IHull;
import waffles.utils.geom.collision.convex.hulls.CLSCuboid;
import waffles.utils.geom.spatial.maps.GlobalMap;
import waffles.utils.geom.utilities.VChain;
import waffles.utils.geom.utilities.VChain.Mode;
import waffles.utils.tools.primitives.Integers;

/**
 * An {ICuboid} defines axis-aligned cuboid geometry with a center and size.
 * 
 * @author Waffles
 * @since Mar 24, 2017
 * @version 1.0
 * 
 * 
 * @see IAxialSet
 * @see IHull
 */
public interface ICuboid extends IAxialSet, IHull
{		
	@Override
	public default Bounds Bounds()
	{
		return new BNDAxial(this);
	}
	
	@Override
	public default Bounds Bounds(GlobalMap map)
	{
		return new BNDCuboid(this, map);
	}
	
	@Override
	public default Collision Collisions()
	{
		return new CLSCuboid(this);
	}
	
	@Override
	public default Matrix Generator()
	{
		VChain spx = VChain.of(this, Mode.POINTS);
		int cols = Integers.pow(2, Dimension());
		int rows = Dimension();
		
		
		int c = 0;
		Matrix span = Matrices.create(rows, cols);
		for(Vector v : spx.Vertices())
		{
			for(int r = 0; r < rows; r++)
			{
				span.set(v.get(r), r, c); 
			}
			
			c++;
		}
		
		return span;
	}
}