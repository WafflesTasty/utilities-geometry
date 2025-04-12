package waffles.utils.geom.collidable.axial.spheroid;

import waffles.utils.algebra.elements.linear.vector.Vector;
import waffles.utils.algebra.elements.linear.vector.Vectors;
import waffles.utils.geom.Collision;
import waffles.utils.geom.bounds.Bounds;
import waffles.utils.geom.bounds.axial.spheroid.BNDSpheroid;
import waffles.utils.geom.bounds.axial.spheroid.BNDSpheroid2D;
import waffles.utils.geom.bounds.axial.spheroid.BNDSpheroid3D;
import waffles.utils.geom.collidable.axial.AxialShape;
import waffles.utils.geom.collidable.convex.ConvexSet;
import waffles.utils.geom.collision.convex.CLSSpheroid;
import waffles.utils.geom.spatial.maps.GlobalMap;

/**
 * An {HyperSpheroid} defines axis-aligned spheroid geometry with a center and size.
 * 
 * @author Waffles
 * @since Mar 24, 2017
 * @version 1.0
 * 
 * 
 * @see AxialShape
 * @see ConvexSet
 */
public interface HyperSpheroid extends AxialShape, ConvexSet
{		
	@Override
	public default Bounds Bounds(GlobalMap map)
	{
		if(Dimension() == 2)
			return new BNDSpheroid2D(this, map);
		if(Dimension() == 3)
			return new BNDSpheroid3D(this, map);
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
				float si = Scale().get(i) / 2;
				n.set(v.get(i) * si, i);
			}
			
			float nrm = n.norm();
			Vector e = Vectors.create(Dimension());
			for(int i = 0; i < Dimension(); i++)
			{
				float si = Scale().get(i) / 2;
				e.set(v.get(i) * si * si / nrm, i);
			}
			
			return Origin().plus(e);
		};
	}
}