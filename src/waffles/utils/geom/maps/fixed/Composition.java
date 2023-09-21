package waffles.utils.geom.maps.fixed;

import waffles.utils.algebra.elements.linear.matrix.Matrix;
import waffles.utils.algebra.utilities.matrix.LazyMatrix;
import waffles.utils.geom.maps.GlobalMap;

/**
 * A {@code Composition} defines a composed global map.
 *
 * @author Waffles
 * @since 15 Sep 2023
 * @version 1.0
 * 
 * 
 * @see GlobalMap
 */
public class Composition implements GlobalMap
{
	private GlobalMap m2, m1;
	
	/**
	 * Creates a new {@code Composition}.
	 * 
	 * @param m2  a global map
	 * @param m1  a global map
	 * 
	 * 
	 * @see GlobalMap
	 */
	public Composition(GlobalMap m2, GlobalMap m1)
	{
		this.m2 = m2;
		this.m1 = m1;
	}
	
	
	@Override
	public LazyMatrix UTW()
	{
		return new LazyMatrix(dim ->
		{
			Matrix utw2 = m2.UTW().Value(dim);
			Matrix utw1 = m1.UTW().Value(dim);

			return utw2.times(utw1);
		});
	}

	@Override
	public LazyMatrix WTU()
	{
		return new LazyMatrix(dim ->
		{
			Matrix utw2 = m2.WTU().Value(dim);
			Matrix utw1 = m1.WTU().Value(dim);

			return utw1.times(utw2);
		});
	}
}