package waffles.utils.geom.spatial.maps.fixed.compose;

import waffles.utils.algebra.elements.linear.vector.Vector;
import waffles.utils.geom.spatial.maps.axial.AxialMap;
import waffles.utils.geom.spatial.structs.Axis;

/**
 * A {@code BiAxialCompose} defines a composition of two {@code AxialMaps}.
 *
 * @author Waffles
 * @since 15 Sep 2023
 * @version 1.0
 * 
 * 
 * @see AxialMap
 */
public class BiAxialCompose implements AxialMap
{
	private AxialMap m2, m1;
	
	/**
	 * Creates a new {@code BiAxialCompose}.
	 * 
	 * @param m2  an axial map
	 * @param m1  an axial map
	 * 
	 * 
	 * @see AxialMap
	 */
	public BiAxialCompose(AxialMap m2, AxialMap m1)
	{
		this.m2 = m2;
		this.m1 = m1;
	}

	@Override
	public Axis Source()
	{
		Vector s2 = m2.Size();
		Vector s1 = m1.Size();
		
		Vector o2 = m2.Origin();
		Vector o1 = m1.Origin();
		
		
		Vector s = s2.ltimes(s1);
		Vector o = o2.plus(o1.ltimes(s2));
		return new Axis(o, s);
	}
}