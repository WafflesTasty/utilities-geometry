package zeno.util.geom.shapes.lines;

import zeno.util.algebra.tensors.vectors.Vector;
import zeno.util.geom.Geometry;
import zeno.util.geom.shapes.Ellipsoid;

public interface Line extends Geometry
{
	public abstract Vector P1();
	
	public abstract Vector P2();

	public static Ellipsoid create(Vector p1, Vector p2)
	{
		// TODO Auto-generated method stub
		return null;
	}
}