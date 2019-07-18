package zeno.util.geom.utilities.collision;

import zeno.util.geom.ICollidable;

public interface Containment
{
	public abstract boolean equals(ICollidable c1, ICollidable c2, int ulps);

	public abstract boolean contains(ICollidable c1, ICollidable c2);

	
	public abstract ICollidable SRCType();
	
	public abstract ICollidable TGTType();
}