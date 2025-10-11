package waffles.utils.geom.spatial.bounds;

import waffles.utils.alg.lin.measure.vector.Vector;
import waffles.utils.geom.spatial.data.Axial;
import waffles.utils.geom.utilities.Dimensional;
import waffles.utils.geomold.collidable.axial.cuboid.HyperCuboid;
import waffles.utils.geomold.collidable.axial.spheroid.HyperSphere;
import waffles.utils.geomold.utilities.Geometries;

/**
 * The {@code Bounds} interface defines bounding volumes in n-dimensional space.
 * It defines all the spatial data to generate a bounding box and bounding sphere.
 * In order for an implemenation to work, make sure to override either the 
 * minimum/maximum or center/size vectors, and either
 * radius or diameter.
 * 
 * @author Waffles
 * @since Apr 06, 2019
 * @version 1.0
 * 
 * 
 * @see Dimensional
 * @see Axial
 */
public interface Bounds extends Axial, Dimensional
{
	/**
	 * The {@code Type} enum defines bounding volume types.
	 *
	 * @author Waffles
	 * @since 20 Apr 2024
	 * @version 1.1
	 */
	public static enum Type
	{
		/**
		 * A bounding ball.
		 */
		BALL,
		/**
		 * A bounding box.
		 */
		BOX;
	}

	
	/**
	 * Returns a bounding radius {@code Float}.
	 * 
	 * @return  a spherical radius
	 */
	public default float Radius()
	{
		return Diameter() / 2;
	}
	
	/**
	 * Returns a bounding diameter {@code Float}.
	 * 
	 * @return  a spherical diameter
	 */
	public default float Diameter()
	{
		return Radius() * 2;
	}
		
	
	/**
	 * Returns a bounding minimum {@code Vector}.
	 * 
	 * @return  a minimum vector
	 * 
	 * 
	 * @see Vector
	 */
	public default Vector Minimum()
	{
		return Origin().plus(Scale().times(-0.5f));
	}
	
	/**
	 * Returns a bounding maximum {@code Vector}.
	 * 
	 * @return  a maximum vector
	 * 
	 * 
	 * @see Vector
	 */
	public default Vector Maximum()
	{
		return Origin().plus(Scale().times(0.5f));
	}
			
				
	/**
	 * Returns a bounding {@code HyperCuboid}.
	 * 
	 * @return  a bounding box
	 * 
	 * 
	 * @see HyperCuboid
	 */
	public default HyperCuboid Box()
	{
		return Geometries.Cuboid(Origin(), Scale());
	}
	
	/**
	 * Returns a bounding {@code HyperSphere}.
	 * 
	 * @return  a bounding sphere
	 * 
	 * 
	 * @see HyperSphere
	 */
	public default HyperSphere Orb()
	{
		return Geometries.Sphere(Origin(), Radius());
	}
	
	
	@Override
	public default int Dimension()
	{
		return Origin().Size();
	}
	
	@Override
	public default Vector Origin()
	{
		return Minimum().plus(Maximum()).times(0.5f);
	}
	
	@Override
	public default Vector Scale()
	{
		return Maximum().minus(Minimum());
	}
}