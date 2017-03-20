package zeno.util.geom.tools;

import zeno.util.geom.IBounds;
import zeno.util.geom.shapes.surfaces.Rectangle;

/**
 * The {@code IBounds2D} interface defines an object bound in two dimensions.
 *
 * @since Aug 25, 2015
 * @author Zeno
 * 
 * @see IBounds
 */
public interface IBounds2D extends IBounds
{	
	/**
	 * Returns the object's bounding {@code Rectangle}.
	 * 
	 * @return  the object's rectangle bounds
	 * @see Rectangle
	 */
	@Override
	public default Rectangle Bounds()
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