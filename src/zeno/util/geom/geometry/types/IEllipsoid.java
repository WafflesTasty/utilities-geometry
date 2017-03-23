package zeno.util.geom.geometry.types;

import zeno.util.geom.geometry.IShape;
import zeno.util.geom.geometry.shapes.NCuboid;

/**
 * The {@code IEllipsoid} interface defines behavior for n-dimensional elliptic shapes.
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