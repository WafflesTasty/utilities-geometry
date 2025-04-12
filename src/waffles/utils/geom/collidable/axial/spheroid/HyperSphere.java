package waffles.utils.geom.collidable.axial.spheroid;

import waffles.utils.algebra.elements.linear.vector.Vector;
import waffles.utils.geom.Collision;
import waffles.utils.geom.bounds.Bounds;
import waffles.utils.geom.bounds.axial.sphere.BNDSphere;
import waffles.utils.geom.bounds.axial.sphere.BNDSphere2D;
import waffles.utils.geom.bounds.axial.sphere.BNDSphere3D;
import waffles.utils.geom.collision.convex.CLSSphere;

/**
 * An {HyperSphere} defines axis-aligned spherical geometry with a center and radius.
 * 
 * @author Waffles
 * @since Mar 24, 2017
 * @version 1.0
 * 
 * 
 * @see HyperSpheroid
 */
public interface HyperSphere extends HyperSpheroid
{	
	/**
	 * Returns the radius of the {@code HyperSphere}.
	 * 
	 * @return  a sphere radius
	 */
	public default float Radius()
	{
		return Diameter() / 2;
	}
	
	/**
	 * Returns the diameter of the {@code HyperSphere}.
	 * 
	 * @return  a sphere diameter
	 */
	public default float Diameter()
	{
		return Scale().get(0);
	}
		
			
	@Override
	public default Extremum Extremum()
	{
		return v ->
		{
			float r = Radius();
			Vector w = v.normalize();
			Vector o = Origin();
			
			return o.plus(w.times(r));
		};
	}

	@Override
	public default Collision Collisions()
	{
		return new CLSSphere(this);
	}
	
	@Override
	public default Bounds Bounds()
	{
		if(Dimension() == 2)
			return new BNDSphere2D(this);
		if(Dimension() == 3)
			return new BNDSphere3D(this);
		return new BNDSphere(this);
	}
}