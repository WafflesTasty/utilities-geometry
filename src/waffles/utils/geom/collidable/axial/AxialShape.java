package waffles.utils.geom.collidable.axial;

import waffles.utils.algebra.elements.linear.Affine;
import waffles.utils.algebra.elements.linear.vector.Vector;
import waffles.utils.geom.bounds.Bounds;
import waffles.utils.geom.bounds.axial.BNDAxial;
import waffles.utils.geom.bounds.axial.BNDAxial2D;
import waffles.utils.geom.bounds.axial.BNDAxial3D;
import waffles.utils.geom.collidable.Geometry;
import waffles.utils.geom.collidable.fixed.Point;
import waffles.utils.geom.spatial.data.Axial;
import waffles.utils.geom.spatial.maps.axial.AxialMap;

/**
 * An {@code AxialShape} defines n-dimensional geometry through a center and size vector.
 *
 * @author Waffles
 * @since 15 Sep 2023
 * @version 1.0
 * 
 * 
 * @see Geometry
 * @see Axial
 */
public interface AxialShape extends Axial, Geometry
{	
	/**
	 * Creates a new {@code AxialShape} given an origin and size.
	 * 
	 * @param o  an origin vector
	 * @param s  a size vector
	 * @return  an axial set
	 * 
	 * 
	 * @see Vector
	 */
	public default AxialShape create(Vector o, Vector s)
	{
		return null;
	}
	
	/**
	 * Transforms the {@code AxialShape} along an axial map.
	 * 
	 * @param map  an axial map
	 * @return  a transformed axial set
	 * 
	 * 
	 * @see AxialMap
	 */
	public default AxialShape map(AxialMap map)
	{
		Point o1 = new Point(Origin(), 1f);
		Affine o2 = map.map(o1);
		if(!(o2 instanceof Point))
		{
			return null;
		}

		Point s1 = new Point(Scale(), 0f);
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
		if(Dimension() == 2)
			return new BNDAxial2D(this);
		if(Dimension() == 3)
			return new BNDAxial3D(this);
		return new BNDAxial(this);
	}
}