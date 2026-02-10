package waffles.utils.geom.spatial.bounds;

import waffles.utils.alg.lin.measure.vector.Vector;
import waffles.utils.alg.utilities.affine.LinearMap;
import waffles.utils.geom.shapes.convex.axial.cube.HyperCuboid;
import waffles.utils.geom.shapes.convex.axial.sphere.HyperSphere;
import waffles.utils.geom.shapes.points.Arrow;
import waffles.utils.geom.shapes.points.Point;
import waffles.utils.geom.spatial.maps.data.Axial;
import waffles.utils.geom.utilities.tform.LinearCompose;
import waffles.utils.tools.patterns.Constructible;

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
 * @see Constructible
 * @see Axial
 */
@FunctionalInterface
public interface Bounds extends Axial, Constructible
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
		 * A bounding box.
		 */
		BOX,
		/**
		 * A bounding orb.
		 */
		ORB;
	}

	/**
	 * A {@code Bounds.Factory} creates transformed {@code Bounds}.
	 *
	 * @author Waffles
	 * @since 08 Feb 2026
	 * @version 1.1
	 *
	 * 
	 * @see LinearMap
	 */
	@FunctionalInterface
	public static interface Factory extends Workshop<LinearMap>
	{
		/**
		 * Constructs a {@code Bounds} in the {@code Factory}.
		 * 
		 * @param map  a linear map
		 * @return  a transformed bouds
		 * 
		 * 
		 * @see LinearMap
		 * @see Bounds
		 */
		public abstract Bounds create(LinearMap map);
		
		@Override
		public default Bounds create(LinearMap... set)
		{
			return create(new LinearCompose(set));
		}
	}
	
	@Override
	public abstract Factory Factory();
	
	
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
	 * Returns a bounding minimum {@code Point}.
	 * 
	 * @return  a minimum point
	 * 
	 * 
	 * @see Point
	 */
	public default Point Minimum()
	{
		return Origin().plus(Scale().times(-0.5f));
	}
	
	/**
	 * Returns a bounding maximum {@code Point}.
	 * 
	 * @return  a maximum point
	 * 
	 * 
	 * @see Point
	 */
	public default Point Maximum()
	{
		return Origin().plus(Scale().times(+0.5f));
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
		return HyperCuboid.create(Origin(), Scale());
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
		return HyperSphere.create(Origin(), Radius());
	}
	
		
	@Override
	public default int Dimension()
	{
		return Origin().Dimension();
	}
	
	@Override
	public default Point Origin()
	{
		Point min = Minimum();
		Point max = Maximum();
		
		Point o = max.plus(min);
		return o.times(0.5f);
	}
	
	@Override
	public default Arrow Scale()
	{
		Point min = Minimum();
		Point max = Maximum();
		
		Point s = max.minus(min);
		Vector v = s.Vector();
		return new Arrow(v);
	}
}