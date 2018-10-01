package zeno.util.geom.collideables;

import zeno.util.algebra.linear.vector.Vector;
import zeno.util.geom.ICollideable;
import zeno.util.geom.ITransformable;
import zeno.util.geom.collideables.geometry.Line;
import zeno.util.geom.collideables.geometry.higher.lines.NLine;

/**
 * The {@code IShapeable} interface defines an object that has a geometric shape.
 * It can be collided with through lines and vectors and can be transformed.
 *
 * @author Zeno
 * @since Feb 27, 2018
 * @version 1.0
 * 
 * 
 * @see ITransformable
 * @see ICollideable
 */
public interface IShapeable extends ICollideable, ITransformable
{
	/**
	 * Returns the shape of the {@code ICollideable}.
	 * 
	 * @return  the object's shape
	 * 
	 * 
	 * @see IGeometry
	 */
	public abstract IGeometry Shape();

	
	@Override
	public default boolean contains(Line l)
	{
		Vector p1 = Transform().Inverse().times(l.P1());
		Vector p2 = Transform().Inverse().times(l.P2());
		return Shape().contains(new NLine(p1, p2));
	}

	@Override
	public default boolean contains(Vector v)
	{
		Vector tv = Transform().Inverse().times(v);
		return Shape().contains(tv);
	}

	@Override
	public default boolean intersects(Line l)
	{
		Vector p1 = Transform().Inverse().times(l.P1());
		Vector p2 = Transform().Inverse().times(l.P2());
		return Shape().intersects(new NLine(p1, p2));
	}
}