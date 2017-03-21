package zeno.util.geom.tools;

import zeno.util.geom.IBounds;
import zeno.util.geom.shapes.solids.Cuboid;

/**
 * The {@code IBounds3D} interface defines an object bound in three dimensions.
 *
 * @since Aug 25, 2015
 * @author Zeno
 * 
 * @see IBounds
 */
public interface IBounds3D extends IBounds
{	
	@Override
	public default int Dimension()
	{
		return 3;
	}
	
	@Override
	public default Cuboid Bounds()
	{
		return new Cuboid
		(
			0.5f * (XMin() + XMax()),
			0.5f * (YMin() + YMax()),
			0.5f * (YMin() + YMax()),
			XMax() - XMin(),
			YMax() - YMin(),
			ZMax() - ZMin()
		);
	}
	
	
	/**
	 * Returns the minimum x-coördinate of the object.
	 * 
	 * @return  the object's minimum x
	 */
	public abstract float XMin();
	
	/**
	 * Returns the maximum x-coördinate of the object.
	 * 
	 * @return  the object's maximum x
	 */
	public abstract float XMax();
	
	/**
	 * Returns the minimum y-coördinate of the object.
	 * 
	 * @return  the object's minimum y
	 */
	public abstract float YMin();
	
	/**
	 * Returns the maximum y-coördinate of the object.
	 * 
	 * @return  the object's maximum y
	 */
	public abstract float YMax();
	
	/**
	 * Returns the minimum z-coördinate of the object.
	 * 
	 * @return  the object's minimum z
	 */
	public abstract float ZMin();
	
	/**
	 * Returns the maximum z-coördinate of the object.
	 * 
	 * @return  the object's maximum z
	 */
	public abstract float ZMax();
}