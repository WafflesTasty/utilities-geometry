package zeno.util.geom.collidables.geometry;

import zeno.util.algebra.linear.vector.Vector;
import zeno.util.algebra.linear.vector.fixed.Vector2;
import zeno.util.algebra.linear.vector.fixed.Vector3;
import zeno.util.geom.collidables.geometry.higher.NCube;
import zeno.util.geom.collidables.geometry.planar.Square;
import zeno.util.geom.collidables.geometry.spatial.Cube;

/**
 * The {@code ICube} interface defines the base for cube geometry.
 * 
 * @author Zeno
 * @since Mar 24, 2017
 * @version 1.0
 * 
 * 
 * @see ICuboid
 */
public interface ICube extends ICuboid
{
	/**
	 * Creates a unit {@code ICube}.
	 * 
	 * @param dim  a cube dimension
	 * @return  a unit cube
	 */
	public static ICube unit(int dim)
	{
		if(dim <= 1) return null;
		if(dim == 2) return new Square();
		if(dim == 3) return new Cube();
		return new NCube(dim);
	}
	
	/**
	 * Creates a new {@code ICube}.
	 * 
	 * @param center  a cube center
	 * @param length  a cube length
	 * @return  a new cube
	 * 
	 * 
	 * @see Vector
	 */
	public static ICube create(Vector center, float length)
	{
		if(center.Size() == 2)
			return new Square((Vector2) center, length);
		if(center.Size() == 3)
			return new Cube((Vector3) center, length);
		
		return new NCube(center, length);
	}
	
	
	/**
	 * Returns the length of the {@code ICube}.
	 * 
	 * @return  the cube's length
	 */
	public default float Length()
	{
		return Size().get(0);
	}
}