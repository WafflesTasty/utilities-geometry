package waffles.utils.geom.collidable.axial;

import waffles.utils.algebra.elements.linear.Affine;
import waffles.utils.algebra.elements.linear.vector.Vector;
import waffles.utils.geom.bounds.Bounds;
import waffles.utils.geom.bounds.axial.BNDAxial;
import waffles.utils.geom.collidable.Geometry;
import waffles.utils.geom.collidable.fixed.Point;
import waffles.utils.geom.spatial.data.Axial;
import waffles.utils.geom.spatial.maps.axial.AxialMap;

/**
 * An {@code IAxialSet} defines n-dimensional geometry through a center and size vector.
 *
 * @author Waffles
 * @since 15 Sep 2023
 * @version 1.0
 * 
 * 
 * @see Geometry
 * @see Axial
 */
public interface IAxialSet extends Axial, Geometry
{	
	/**
	 * Creates a new {@code AxialSet} given an origin and size.
	 * 
	 * @param o  an origin vector
	 * @param s  a size vector
	 * @return  an axial set
	 * 
	 * 
	 * @see Vector
	 */
	public default IAxialSet create(Vector o, Vector s)
	{
		return null;
	}
	
	/**
	 * Transforms the {@code AxialSet} along an axial map.
	 * 
	 * @param map  an axial map
	 * @return  a transformed axial set
	 * 
	 * 
	 * @see AxialMap
	 */
	public default IAxialSet map(AxialMap map)
	{
		Point o1 = new Point(Origin(), 1f);
		Affine o2 = map.map(o1);
		if(!(o2 instanceof Point))
		{
			return null;
		}

		Point s1 = new Point(Size(), 0f);
		Affine s2 = map.map(s1);
		if(!(s2 instanceof Point))
		{
			return null;
		}
		
		
		o1 = (Point) o2;
		s1 = (Point) s2;
		
		return create
		(
			o1.Generator(),
			s1.Generator()
		);
	}
	

	@Override
	public default Bounds Bounds()
	{
		return new BNDAxial(this);
	}
}