package zeno.util.geom._deprecated.geometry;

import zeno.util.algebra.tensors.vectors.Vector;

/**
 * The {@code IShape} interface defines the base for a closed geometric shape.
 * 
 * @since Mar 21, 2017
 * @author Zeno
 * 
 * @see Geometry
 */
public interface IShape extends IGeometry
{
	/**
	 * Returns the minimum of the {@code IShape}.
	 * 
	 * @return  the shape's minimum
	 * @see Vector
	 */
	public default Vector Minimum()
	{
		return Center().minus(Size().times(0.5f));
	}
	
	/**
	 * Returns the maximum of the {@code IShape}.
	 * 
	 * @return  the shape's maximum
	 * @see Vector
	 */
	public default Vector Maximum()
	{
		return Center().plus(Size().times(0.5f));
	}
	
	/**
	 * Returns the center of the {@code IShape}.
	 * 
	 * @return  the shape's center
	 * @see Vector
	 */
	public abstract Vector Center();
	
	/**
	 * Returns the size of the {@code IShape}.
	 * 
	 * @return  the shape's size
	 * @see Vector
	 */
	public abstract Vector Size();
}