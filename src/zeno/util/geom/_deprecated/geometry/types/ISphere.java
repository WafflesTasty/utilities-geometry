package zeno.util.geom._deprecated.geometry.types;

/**
 * The {@code Sphere} interface defines behavior for n-dimensional spheres.
 * 
 * @since Mar 23, 2017
 * @author Zeno
 * 
 * @see Ellipsoid
 */
public interface ISphere extends IEllipsoid
{
	/**
	 * Returns the diameter of the {@code Sphere}.
	 * 
	 * @return  the sphere's diameter
	 */
	public default float Diameter()
	{
		return Size().get(0);
	}
	
	/**
	 * Returns the radius of the {@code Sphere}.
	 * 
	 * @return  the sphere's radius
	 */
	public default float Radius()
	{
		return Diameter() / 2;
	}
}