package zeno.util.geom.tools.bounds;

import zeno.util.geom.shapes.surfaces.lateral.Rectangle;

/**
 * The {@code IBound2D} interface defines an object bound in two dimensions.
 *
 * @author Zeno
 * @since Aug 25, 2015
 */
public interface IBound2D
{
	/**
	 * Returns the object's bounding {@code Rectangle}.
	 * 
	 * @return  the object's rectangle bounds
	 * @see Rectangle
	 */
	public default Rectangle Bounds2D()
	{
		return new Rectangle
		(
			0.5f * (XMin() + XMax()),
			0.5f * (YMin() + YMax()),
			XMax() - XMin(),
			YMax() - YMin()
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
}