package zeno.util.geom;

import zeno.util.algebra.tensors.vectors.Vector;
import zeno.util.geom.dimension.any.lines.NLine;
import zeno.util.geom.utilities.shapes.Line;

/**
 * The {@code IShapeable} interface defines an object with a concrete shape.
 * <br> It is capable of being transformed and allows basic collision detection.
 *
 * @author Zeno
 * @since Feb 27, 2018
 * @version 1.0
 * 
 * @see ICollideable
 * @see ITransformable
 */
public interface IShapeable extends ICollideable, ITransformable
{
	/**
	 * Returns the shape of the {@code IShapeable}.
	 * 
	 * @return  the object's shape
	 * @see IGeometry
	 */
	public abstract IGeometry Shape();
		
	
	@Override
	public default boolean intersects(Line l)
	{
		Vector p1 = Transform().Inverse().times(l.P1());
		Vector p2 = Transform().Inverse().times(l.P2());
		return Shape().intersects(new NLine(p1, p2));
	}
	
	@Override
	public default boolean contains(Vector v)
	{
		Vector tv = Transform().Inverse().times(v);
		return Shape().contains(tv);
	}
	
	@Override
	public default boolean contains(Line l)
	{
		Vector p1 = Transform().Inverse().times(l.P1());
		Vector p2 = Transform().Inverse().times(l.P2());
		return Shape().contains(new NLine(p1, p2));
	}
}