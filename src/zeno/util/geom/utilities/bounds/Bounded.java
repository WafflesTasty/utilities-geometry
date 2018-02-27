package zeno.util.geom.utilities.bounds;

import zeno.util.algebra.tensors.vectors.Vector;
import zeno.util.geom.dimension.any.shapes.NCuboid;
import zeno.util.geom.utilities.shapes.ICuboid;

/**
 * The {@code Bounded} interface defines an object bound in n dimensions.
 * 
 * @since Mar 24, 2017
 * @author Zeno
 */
public interface Bounded
{
	/**
	 * Returns the size of the {@code Bounded} object.
	 * 
	 * @return  the object's size
	 * @see Vector
	 */
	public abstract Vector Size();
	
	/**
	 * Returns the center of the {@code Bounded} object.
	 * 
	 * @return  the object's center
	 * @see Vector
	 */
	public abstract Vector Center();
			
	
	/**
	 * Returns the minimum of the {@code Bounded} object.
	 * 
	 * @return  the object's minimum
	 * @see Vector
	 */
	public default Vector Minimum()
	{
		return Center().minus(Size().times(0.5f));
	}
	
	/**
	 * Returns the maximum of the {@code Bounded} object.
	 * 
	 * @return  the object's maximum
	 * @see Vector
	 */
	public default Vector Maximum()
	{
		return Center().plus(Size().times(0.5f));
	}
	
	/**
	 * Returns the bounds of the {@code Bounded} object.
	 * 
	 * @return  the object's bounds
	 * @see ICuboid
	 */
	public default ICuboid Bounds()
	{
		return new NCuboid(Center(), Size());
	}

	/**
	 * Returns the dim of the {@code Bounded} object.
	 * 
	 * @return  the object's dimension
	 */
	public default int Dimension()
	{
		return Center().size();
	}
}