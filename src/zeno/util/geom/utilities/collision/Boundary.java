package zeno.util.geom.utilities.collision;

import zeno.util.geom.ITransformation;
import zeno.util.geom.collidables.IGeometry;
import zeno.util.geom.utilities.bounds.Bounds;

public interface Boundary
{
	public abstract Bounds bounds(ITransformation t, IGeometry g);

	
	public abstract IGeometry Type();
}