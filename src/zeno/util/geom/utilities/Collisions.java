package zeno.util.geom.utilities;

import java.util.HashMap;
import java.util.Map;

import zeno.util.coll._deprecated.DoubleMap;
import zeno.util.geom.ICollidable;
import zeno.util.geom.ITransformation;
import zeno.util.geom.collidables.IGeometry;
import zeno.util.geom.collidables.IShapeable;
import zeno.util.geom.utilities.bounds.Bounds;
import zeno.util.geom.utilities.collision.Boundary;
import zeno.util.geom.utilities.collision.Containment;
import zeno.util.geom.utilities.collision.Intersection;
import zeno.util.geom.utilities.collision.affine.CNTASetASet;
import zeno.util.geom.utilities.collision.affine.CNTASetASpace;
import zeno.util.geom.utilities.collision.affine.CNTASetPoint;
import zeno.util.geom.utilities.collision.affine.CNTASpaceASet;
import zeno.util.geom.utilities.collision.affine.CNTASpaceASpace;
import zeno.util.geom.utilities.collision.affine.CNTASpacePoint;
import zeno.util.geom.utilities.collision.affine.CNTPointASet;
import zeno.util.geom.utilities.collision.affine.CNTPointASpace;
import zeno.util.geom.utilities.collision.affine.CNTPointPoint;
import zeno.util.geom.utilities.collision.affine.INTASetASet;
import zeno.util.geom.utilities.collision.affine.INTASetASpace;
import zeno.util.geom.utilities.collision.affine.INTASetPoint;
import zeno.util.geom.utilities.collision.affine.INTASpaceASet;
import zeno.util.geom.utilities.collision.affine.INTASpaceASpace;
import zeno.util.geom.utilities.collision.affine.INTASpacePoint;
import zeno.util.geom.utilities.collision.affine.INTPointASet;
import zeno.util.geom.utilities.collision.affine.INTPointASpace;
import zeno.util.geom.utilities.collision.affine.INTPointPoint;

public final class Collisions
{
	private static Map<IGeometry, Boundary> bMap;
	private static DoubleMap<ICollidable, Containment> cMap;
	private static DoubleMap<ICollidable, Intersection> iMap;

		
	public static Bounds bounds(IShapeable s)
	{
		IGeometry shape = s.Shape();
		ITransformation tform = s.Transform();
		
		Boundary bnd = bMap.get(shape.Type());
		if(bnd != null)
		{
			return bnd.bounds(tform, shape);
		}
		
		throw new RuntimeException("Boundary not implemented for " + shape.Type() + ".");
	}
	
	public static ICollidable intersect(ICollidable c1, ICollidable c2)
	{
		Intersection isc = iMap.get(c1.Type(), c2.Type());
		if(isc != null)
		{
			return isc.intersect(c1, c2);
		}
		
		throw new RuntimeException("Intersection not implemented for (" + c1.Type() + ", " + c2.Type() + ").");
	}
	
	public static boolean equals(ICollidable c1, ICollidable c2, int ulps)
	{
		Containment cnt = cMap.get(c1.Type(), c2.Type());
		if(cnt != null)
		{
			return cnt.equals(c1, c2, ulps);
		}
		
		throw new RuntimeException("Containment not implemented for (" + c1.Type() + ", " + c2.Type() + ").");
	}
			
	public static boolean intersects(ICollidable c1, ICollidable c2)
	{
		Intersection isc = iMap.get(c1.Type(), c2.Type());
		if(isc != null)
		{
			return isc.intersects(c1, c2);
		}
		
		throw new RuntimeException("Intersection not implemented for (" + c1.Type() + ", " + c2.Type() + ").");
	}
	
	public static boolean contains(ICollidable c1, ICollidable c2)
	{
		Containment cnt = cMap.get(c1.Type(), c2.Type());
		if(cnt != null)
		{
			return cnt.contains(c1, c2);
		}
		
		throw new RuntimeException("Containment not implemented for (" + c1.Type() + ", " + c2.Type() + ").");
	}
	
	
	public static void add(Boundary b)
	{
		bMap.put(b.Type(), b);
	}
	
	public static void add(Intersection i)
	{
		iMap.put(i.SRCType(), i.TGTType(), i);
	}
	
	public static void add(Containment c)
	{
		cMap.put(c.SRCType(), c.TGTType(), c);
	}
	
	
	public static void initialize()
	{
		bMap = new HashMap<>();
		// Add generic boundary methods.
		
		
		cMap = new DoubleMap<>();
		// Add affine containment methods.
		add(new CNTASetASet());
		add(new CNTASetASpace());
		add(new CNTASetPoint());
		add(new CNTASpaceASet());
		add(new CNTASpaceASpace());
		add(new CNTASpacePoint());
		add(new CNTPointASet());
		add(new CNTPointASpace());
		add(new CNTPointPoint());
		
		iMap = new DoubleMap<>();
		// Add affine intersection methods.
		add(new INTASetASet());
		add(new INTASetASpace());
		add(new INTASetPoint());
		add(new INTASpaceASet());
		add(new INTASpaceASpace());
		add(new INTASpacePoint());
		add(new INTPointASet());
		add(new INTPointASpace());
		add(new INTPointPoint());
	}
	
	
	private Collisions()
	{
		// NOT APPLICABLE
	}
}