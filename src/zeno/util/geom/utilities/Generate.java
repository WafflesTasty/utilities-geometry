package zeno.util.geom.utilities;

import zeno.util.algebra.linear.vector.Vector;
import zeno.util.algebra.linear.vector.fixed.Vector2;
import zeno.util.algebra.linear.vector.fixed.Vector3;
import zeno.util.geom.collidables.geometry.generic.ICube;
import zeno.util.geom.collidables.geometry.generic.ICuboid;
import zeno.util.geom.collidables.geometry.generic.IEllipsoid;
import zeno.util.geom.collidables.geometry.generic.ISegment;
import zeno.util.geom.collidables.geometry.generic.ISphere;
import zeno.util.geom.collidables.geometry.higher.NCube;
import zeno.util.geom.collidables.geometry.higher.NCuboid;
import zeno.util.geom.collidables.geometry.higher.NEllipsoid;
import zeno.util.geom.collidables.geometry.higher.NSegment;
import zeno.util.geom.collidables.geometry.higher.NSphere;
import zeno.util.geom.collidables.geometry.planar.Circle;
import zeno.util.geom.collidables.geometry.planar.Ellipse;
import zeno.util.geom.collidables.geometry.planar.Rectangle;
import zeno.util.geom.collidables.geometry.planar.Segment2D;
import zeno.util.geom.collidables.geometry.planar.Square;
import zeno.util.geom.collidables.geometry.spatial.Cube;
import zeno.util.geom.collidables.geometry.spatial.Cuboid;
import zeno.util.geom.collidables.geometry.spatial.Ellipsoid;
import zeno.util.geom.collidables.geometry.spatial.Segment3D;
import zeno.util.geom.collidables.geometry.spatial.Sphere;

/**
 * The {@code Generate} class provides static access methods to
 * generate various basic geometry objects of the appropriate class.
 *
 * @author Zeno
 * @since Jul 17, 2019
 * @version 1.0
 */
public final class Generate
{	
	/**
	 * Creates a new {@code ICube}.
	 * 
	 * @param center  a cube center
	 * @param length  a cube length
	 * @return  a new cube
	 * 
	 * 
	 * @see ICube
	 * @see Vector
	 */
	public static ICube cube(Vector center, float length)
	{
		if(center.Size() == 2)
			return new Square((Vector2) center, length);
		if(center.Size() == 3)
			return new Cube((Vector3) center, length);
		
		return new NCube(center, length);
	}
	
	/**
	 * Creates a new {@code ICuboid}.
	 * 
	 * @param center  a cuboid center
	 * @param size    a cuboid size
	 * @return  a new cuboid
	 * 
	 * 
	 * @see ICuboid
	 * @see Vector
	 */
	public static ICuboid cuboid(Vector center, Vector size)
	{
		if(center.Size() == 2)
			return new Rectangle((Vector2) center, (Vector2) size);
		if(center.Size() == 3)
			return new Cuboid((Vector3) center, (Vector3) size);
		
		return new NCuboid(center, size);
	}
	
	/**
	 * Creates a new {@code IEllipsoid}.
	 * 
	 * @param center  an ellipsoid center
	 * @param size    an ellipsoid size
	 * @return  a new ellipsoid
	 * 
	 * 
	 * @see IEllipsoid
	 * @see Vector
	 */
	public static IEllipsoid ellipsoid(Vector center, Vector size)
	{
		if(center.Size() == 2)
			return new Ellipse((Vector2) center, (Vector2) size);
		if(center.Size() == 3)
			return new Ellipsoid((Vector3) center, (Vector3) size);
		
		return new NEllipsoid(center, size);
	}
		
	/**
	 * Creates a new {@code ISphere}.
	 * 
	 * @param center  a sphere center
	 * @param radius  a sphere radius
	 * @return  a new sphere
	 * 
	 * 
	 * @see ISphere
	 * @see Vector
	 */
	public static ISphere sphere(Vector center, float radius)
	{
		if(center.Size() == 2)
			return new Circle((Vector2) center, radius);
		if(center.Size() == 3)
			return new Sphere((Vector3) center, radius);
		
		return new NSphere(center, radius);
	}
	
	/**
	 * Creates a new {@code ISegment}.
	 * 
	 * @param p1  the line's first point
	 * @param p2  the line's second point
	 * @return  a new line
	 * 
	 * 
	 * @see ISegment
	 * @see Vector
	 */
	public static ISegment segment(Vector p1, Vector p2)
	{
		if(p1.Size() == 2)
			return new Segment2D((Vector2) p1, (Vector2) p2);
		if(p1.Size() == 3)
			return new Segment3D((Vector3) p1, (Vector3) p2);
		
		return new NSegment(p1, p2);
	}
	
		
	/**
	 * Creates a unit {@code ISphere}.
	 * 
	 * @param dim  a sphere dimension
	 * @return  a unit sphere
	 * 
	 * 
	 * @see ISphere
	 */
	public static ISphere sphere(int dim)
	{
		if(dim <= 1) return null;
		if(dim == 2) return new Circle();
		if(dim == 3) return new Sphere();
		return new NSphere(dim);
	}
	
	/**
	 * Creates a unit {@code ICube}.
	 * 
	 * @param dim  a cube dimension
	 * @return  a unit cube
	 * 
	 * 
	 * @see ICube
	 */
	public static ICube cube(int dim)
	{
		if(dim <= 1) return null;
		if(dim == 2) return new Square();
		if(dim == 3) return new Cube();
		return new NCube(dim);
	}

	
	private Generate()
	{
		// NOT APPLICABLE
	}
}