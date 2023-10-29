package waffles.utils.geom.spatial;

import waffles.utils.algebra.elements.linear.vector.Vector;
import waffles.utils.geom.spatial.data.Axial;
import waffles.utils.geom.spatial.data.unary.Positioned;
import waffles.utils.geom.spatial.data.unary.Scaled;
import waffles.utils.geom.spatial.types.Movable;
import waffles.utils.geom.spatial.types.Scalable;

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
		Scaled.Mutable src = Spatial().Mutator();
		if(src != null)
		{
			src.setSize(s);
		}
	}
	
	@Override
	public default void moveTo(Vector o)
	{
		Positioned.Mutable src = Spatial().Mutator();
		if(src != null)
		{
			src.setOrigin(o);
		}
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