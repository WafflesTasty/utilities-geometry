package zeno.util.geom.shapes;

import zeno.util.algebra.tensors.vectors.Vector;

public class NCube extends NCuboid
{
	public NCube(Vector center, float size)
	{
		super(center, Vector.create(size, center.size()));
	}
}