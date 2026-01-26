package waffles.utils.geom.shapes.convex.axial.cube;

import waffles.utils.geom.shapes.convex.axial.cube.base.Cube;
import waffles.utils.geom.shapes.convex.axial.cube.base.CubeND;
import waffles.utils.geom.shapes.convex.axial.cube.base.Square;
import waffles.utils.geom.shapes.points.Point;

/**
 * An {HyperCube} defines axis-aligned cube geometry with an origin and length.
 * 
 * @author Waffles
 * @since Mar 24, 2017
 * @version 1.0
 * 
 * 
 * @see HyperCuboid
 */
public interface HyperCube extends HyperCuboid
{
	/**
	 * Creates a {@code HyperCube} from an origin and a length.
	 * 
	 * @param o  a cube origin
	 * @param l  a cube length
	 * @return   a cube
	 * 
	 * 
	 * @see Point
	 */
	public static HyperCube create(Point o, float l)
	{
		if(o.Dimension() == 2)
			return new Square(o, l);
		if(o.Dimension() == 3)
			return new Cube(o, l);
		
		return new CubeND(o, l);
	}
	
	/**
	 * Creates a {@code HyperCube} from a dimension and a length.
	 * 
	 * @param l  a cube length
	 * @param n  a cube dimension 
	 * @return  a cube
	 */
	public static HyperCube create(float l, int n)
	{
		return create(new Point(n), l);
	}
	
	/**
	 * Creates a unit {@code HyperCube} from a dimension.
	 * 
	 * @param n  a cube dimension
	 * @return  a unit cube
	 */
	public static HyperCube unit(int n)
	{
		return create(new Point(n), 1f);
	}
	
	
	/**
	 * Returns the length of the {@code HyperCube}.
	 * 
	 * @return  a cube length
	 */
	public default float Length()
	{
		return Scale().aff(0);
	}
}