package zeno.util.geom;

import zeno.util.algebra.tensors.vectors.Vector;
import zeno.util.geom.shapes.Cuboid;

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
	 * Returns the dimension of the {@code Bounded} object.
	 * 
	 * @return  the object's dimension
	 */
	public abstract int Dimension();
		
	
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
	 * @see Cuboid
	 */
	public default Cuboid Bounds()
	{
		return Cuboid.create(Center(), Size());
	}
}