package waffles.utils.geom.shapes.convex.axial.sphere;

import waffles.utils.geom.collide.collision.Collision;
import waffles.utils.geom.collide.collision.convex.spheres.CLSSphere;
import waffles.utils.geom.shapes.convex.axial.sphere.base.Circle;
import waffles.utils.geom.shapes.convex.axial.sphere.base.Sphere;
import waffles.utils.geom.shapes.convex.axial.sphere.base.SphereND;
import waffles.utils.geom.shapes.points.Point;

/**
 * A {HyperSphere} defines axis-aligned spherical geometry with an origin and radius.
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
	 * Creates a {@code HyperSphere} from an origin and radius.
	 * 
	 * @param o  a sphere origin
	 * @param r  a sphere radius
	 * @return   a sphere
	 * 
	 * 
	 * @see Point
	 */
	public static HyperSphere create(Point o, float r)
	{
		if(o.Dimension() == 2)
			return new Circle(o, r);
		if(o.Dimension() == 3)
			return new Sphere(o, r);
		
		return new SphereND(o, r);
	}
	
	/**
	 * Creates a unit {@code HyperSphere} from a dimension.
	 * 
	 * @param dim  a sphere dimension
	 * @return  a unit sphere
	 */
	public static HyperSphere unit(int dim)
	{
		if(dim == 2)
			return new Circle(dim);
		if(dim == 3)
			return new Sphere(dim);
		
		return new SphereND(dim);
	}
	
	
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
		return Scale().aff(0);
	}


	@Override
	public default Collision Collision()
	{
		return new CLSSphere(this);
	}
	
	@Override
	public default Extremum Extremum()
	{
		return p ->
		{
			Point o = Origin();
			float r = Radius();
			float n = p.norm();
			
			return o.plus(p.times(r / n));
		};
	}
}