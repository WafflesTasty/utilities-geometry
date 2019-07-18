package zeno.util.geom.utilities.collision;

import zeno.util.geom.ICollidable;

public interface Intersection
{
	public abstract ICollidable intersect(ICollidable c1, ICollidable c2);
	
	public abstract boolean intersects(ICollidable c1, ICollidable c2);

	
	public abstract ICollidable SRCType();
	
	public abstract ICollidable TGTType();
}