package waffles.utils.geom.spatial.owners;

import waffles.utils.algebra.elements.linear.vector.Vector;
import waffles.utils.geom.spatial.Axial;
import waffles.utils.geom.spatial.owners.types.Movable;
import waffles.utils.geom.spatial.owners.types.Scalable;

/**
 * An {@code Aligned} object can be axis-aligned in a vector space.
 *
 * @author Waffles
 * @since Feb 10, 2019
 * @version 1.0
 * 
 * 
 * @see Scalable
 * @see Movable
 */
public interface Aligned extends Scalable, Movable
{
	/**
	 * Returns an {@code Axial} data source.
	 * 
	 * @return  an axial source
	 * 
	 * 
	 * @see Axial
	 */
	public abstract Axial Spatial();
	

	@Override
	public default void scaleTo(Vector s)
	{
		Spatial().setSize(s);
	}
	
	@Override
	public default void moveTo(Vector o)
	{
		Spatial().setOrigin(o);
	}
	
		
	@Override
	public default Vector Origin()
	{
		return Spatial().Origin();
	}
		
	@Override
	public default Vector Size()
	{
		return Spatial().Size();
	}
}