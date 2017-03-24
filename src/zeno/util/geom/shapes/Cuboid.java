package zeno.util.geom.shapes;

import zeno.util.algebra.tensors.vectors.Vector;
import zeno.util.geom.Geometry;

public interface Cuboid extends Geometry
{
	public boolean contains(Vector p);

	public static Cuboid create(Vector center, Vector size)
	{
		// TODO Auto-generated method stub
		return null;
	}
}