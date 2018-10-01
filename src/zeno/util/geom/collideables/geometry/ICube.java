package zeno.util.geom.collideables.geometry;

import zeno.util.algebra.linear.vector.Vector;
import zeno.util.algebra.linear.vector.fixed.Vector2;
import zeno.util.algebra.linear.vector.fixed.Vector3;
import zeno.util.geom.collideables.geometry.higher.shapes.NCube;
import zeno.util.geom.collideables.geometry.planar.shapes.Square;
import zeno.util.geom.collideables.geometry.spatial.shapes.Cube;

/**
 * The {@code ICube} interface defines the base for cube geometry.
 * 
 * @since Mar 24, 2017
 * @author Zeno
 * 
 * @see ICuboid
 */
public interface ICube extends ICuboid
{
	/**
	 * Creates a new unit {@code ICube}.
	 * 
	 * @param dim  the cube's dimension
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
	 * @param center  the cube's center
	 * @param length  the cube's length
	 * @return  a new cube
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