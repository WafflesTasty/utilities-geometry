package zeno.util.geom.tools;

import java.awt.Point;

import zeno.util.algebra.tensors.vectors.fixed.Vector2;
import zeno.util.geom.IGeometry2D;
import zeno.util.geom.tools.iterators.PerimeterIterator;
import zeno.util.geom.tools.iterators.SurfaceIterator;

/**
 * The {@code Iterators} class defines geometry iterators.
 *
 * @since Sep 12, 2015
 * @author Zeno
 */
public final class Iterators
{
	/**
	 * Iterates over the perimeter of an {@code Geometry2D}.
	 * 
	 * @param geom  a geometry to use
	 * @return  a perimeter iterator
	 * @see IGeometry2D
	 * @see Iterable
	 * @see Point
	 */
	public static Iterable<Vector2> perimeter(IGeometry2D geom)
	{
		return () -> new PerimeterIterator(geom);
	}
	
	/**
	 * Iterates over the surface of an {@code Geometry2D}.
	 * 
	 * @param geom  a geometry to use
	 * @return  a surface iterator
	 * @see IGeometry2D
	 * @see Iterable
	 * @see Point
	 */
	public static Iterable<Vector2> surface(IGeometry2D geom)
	{
		return () -> new SurfaceIterator(geom);
	}
	
	
	private Iterators()
	{
		// NOT APPLICABLE
	}
}