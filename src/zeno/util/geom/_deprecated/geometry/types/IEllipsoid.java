package zeno.util.geom._deprecated.geometry.types;

import zeno.util.geom._deprecated.geometry.IShape;
import zeno.util.geom._deprecated.geometry.shapes.NCuboid;

/**
 * The {@code Ellipsoid} interface defines behavior for n-dimensional elliptic shapes.
 * 
 * @since Mar 23, 2017
 * @author Zeno
 * 
 * @see IShape
 */
public interface IEllipsoid extends IShape
{
	@Override
	public default NCuboid Bounds()
	{
		return new NCuboid(Center(), Size());
	}
}