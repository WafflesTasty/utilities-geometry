package waffles.utils.geom.spatial.maps.fixed.inverse;

import waffles.utils.algebra.elements.linear.vector.Vector;
import waffles.utils.algebra.elements.linear.vector.Vectors;
import waffles.utils.geom.spatial.data.Axial;
import waffles.utils.geom.spatial.maps.axial.AxialMap;
import waffles.utils.geom.spatial.structs.Axis;

/**
 * An {@code AxialInverse} defines an inverse axial map.
 *
 * @author Waffles
 * @since 15 Sep 2023
 * @version 1.0
 * 
 * 
 * @see AxialMap
 */
public class AxialInverse implements AxialMap
{
	private AxialMap map;
	
	/**
	 * Creates a new {@code AxialInverse}.
	 * 
	 * @param m  a global map
	 * 
	 * 
	 * @see AxialMap
	 */
	public AxialInverse(AxialMap m)
	{
		map = m;
	}
	

	@Override
	public Axial Source()
	{		
		Vector sMap = map.Size();
		Vector oMap = map.Origin();
		
		
		int dim = oMap.Size();
		Vector sInv = Vectors.create(dim);
		Vector oInv = Vectors.create(dim);
		
		for(int i = 0; i < dim; i++)
		{
			float si = +1f / sMap.get(i);
			float oi = -si * oMap.get(i);
			
			sInv.set(si, i);
			oInv.set(oi, i);
		}

		return new Axis(oInv, sInv);
	}
}