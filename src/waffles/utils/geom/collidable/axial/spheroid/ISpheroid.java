package waffles.utils.geom.collidable.axial.spheroid;

import waffles.utils.algebra.elements.linear.vector.Vector;
import waffles.utils.algebra.elements.linear.vector.Vectors;
import waffles.utils.geom.Collision;
import waffles.utils.geom.bounds.Bounds;
import waffles.utils.geom.bounds.axial.spheroid.BNDSpheroid;
import waffles.utils.geom.collidable.ConvexSet;
import waffles.utils.geom.collision.convex.CLSSpheroid;
import waffles.utils.geom.maps.GlobalMap;
import waffles.utils.geom.spatial.data.Axial;

/**
 * An {ISpheroid} defines axis-aligned spheroid geometry with a center and size.
 * 
 * @author Waffles
 * @since Mar 24, 2017
 * @version 1.0
 * 
 * 
 * @see ConvexSet
 * @see Axial
 */
public interface ISpheroid extends Axial, ConvexSet
{		
	@Override
	public default Bounds Bounds(GlobalMap map)
	{
		return new BNDSpheroid(this, map);
	}
	
	@Override
	public default Collision Collisions()
	{
		return new CLSSpheroid(this);
	}
	
	@Override
	public default Extremum Extremum()
	{
		return v ->
		{
			Vector n = Vectors.create(Dimension());
			for(int i = 0; i < Dimension(); i++)
			{
				float si = Size().get(i) / 2;
				n.set(v.get(i) * si, i);
			}
			
			float nrm = n.norm();
			Vector e = Vectors.create(Dimension());
			for(int i = 0; i < Dimension(); i++)
			{
				float si = Size().get(i) / 2;
				e.set(v.get(i) * si * si / nrm, i);
			}
			
			return Origin().plus(e);
		};
	}
}