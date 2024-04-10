package waffles.utils.geom.spatial.maps.fixed.inverse;

import waffles.utils.algebra.elements.linear.vector.Vector;
import waffles.utils.algebra.elements.linear.vector.Vectors;
import waffles.utils.geom.spatial.data.Spatial;
import waffles.utils.geom.spatial.data.spin.Spin;
import waffles.utils.geom.spatial.maps.spatial.SpatialMap;
import waffles.utils.geom.spatial.structs.Locus;

/**
 * A {@code SpatialInverse} defines an inverse spatial map.
 *
 * @author Waffles
 * @since 15 Sep 2023
 * @version 1.1
 * 
 * 
 * @see SpatialMap
 */
public class SpatialInverse implements SpatialMap
{
	private SpatialMap map;
	
	/**
	 * Creates a new {@code SpatialInverse}.
	 * 
	 * @param m  a spatial map
	 * 
	 * 
	 * @see SpatialMap
	 */
	public SpatialInverse(SpatialMap m)
	{
		map = m;
	}
	

	@Override
	public Spatial Source()
	{		
		Vector sMap = map.Size();
		Vector oMap = map.Origin();
		Spin rMap = map.Spin();
		
		
		int dim = oMap.Size();
		Vector sInv = Vectors.create(dim);
		Vector oInv = Vectors.create(dim);
		Spin rInv = rMap.invert();
		
		for(int i = 0; i < dim; i++)
		{
			float si = sMap.get(i);
			float oi = oMap.get(i);
			
			oInv.set(-2f / si * oi, i);
			sInv.set(+4f / si, i);
		}

		
		Locus l = new Locus();
		l.setOrigin(oInv);
		l.setSize(sInv);
		l.setSpin(rInv);
		return l;
	}
}