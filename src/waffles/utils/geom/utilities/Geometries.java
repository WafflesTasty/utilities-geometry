package waffles.utils.geom.utilities;

import waffles.utils.algebra.elements.linear.Affine;
import waffles.utils.algebra.elements.linear.matrix.Matrix;
import waffles.utils.algebra.elements.linear.vector.Vector;
import waffles.utils.algebra.elements.linear.vector.fixed.Vector2;
import waffles.utils.algebra.elements.linear.vector.fixed.Vector3;
import waffles.utils.geom.collidable.axial.cuboid.Cube;
import waffles.utils.geom.collidable.axial.cuboid.CubeND;
import waffles.utils.geom.collidable.axial.cuboid.Cuboid;
import waffles.utils.geom.collidable.axial.cuboid.CuboidND;
import waffles.utils.geom.collidable.axial.cuboid.HyperCube;
import waffles.utils.geom.collidable.axial.cuboid.HyperCuboid;
import waffles.utils.geom.collidable.axial.cuboid.Rectangle;
import waffles.utils.geom.collidable.axial.cuboid.Square;
import waffles.utils.geom.collidable.axial.spheroid.Circle;
import waffles.utils.geom.collidable.axial.spheroid.Ellipse;
import waffles.utils.geom.collidable.axial.spheroid.HyperSphere;
import waffles.utils.geom.collidable.axial.spheroid.HyperSpheroid;
import waffles.utils.geom.collidable.axial.spheroid.Sphere;
import waffles.utils.geom.collidable.axial.spheroid.SphereND;
import waffles.utils.geom.collidable.axial.spheroid.Spheroid;
import waffles.utils.geom.collidable.axial.spheroid.SpheroidND;
import waffles.utils.geom.collidable.convex.hulls.Hull2D;
import waffles.utils.geom.collidable.convex.hulls.Hull3D;
import waffles.utils.geom.collidable.convex.hulls.HullND;
import waffles.utils.geom.collidable.convex.hulls.Hull;
import waffles.utils.geom.collidable.convex.hulls.segments.Segment;
import waffles.utils.geom.collidable.convex.hulls.segments.Segment2D;
import waffles.utils.geom.collidable.convex.hulls.segments.Segment3D;
import waffles.utils.geom.collidable.convex.hulls.segments.SegmentND;
import waffles.utils.geom.collidable.convex.hulls.triangles.Triangle;
import waffles.utils.geom.collidable.convex.hulls.triangles.Triangle2D;
import waffles.utils.geom.collidable.convex.hulls.triangles.Triangle3D;
import waffles.utils.geom.collidable.convex.hulls.triangles.TriangleND;
import waffles.utils.geom.collidable.fixed.Point;
import waffles.utils.geom.collidable.fixed.Void;
import waffles.utils.geom.collidable.spaces.ASpace;
import waffles.utils.geom.collidable.spaces.VSpace;
import waffles.utils.geom.collidable.spaces.lines.Line;
import waffles.utils.geom.collidable.spaces.lines.Line2D;
import waffles.utils.geom.collidable.spaces.lines.Line3D;

/**
 * The {@code Geometries} class provides static utility methods to generate {@code Geometry} objects.
 * The functions in this class are useful if you're unsure of the dimension of your object.
 *
 * @author Waffles
 * @since Jul 23, 2019
 * @version 1.0
 */
public final class Geometries
{		
	/**
	 * Generates {@code Void} geometry.
	 * 
	 * @param dim  a space dimension
	 * @return  void geometry
	 * 
	 * 
	 * @see Void
	 */
	public static Void Void(int dim)
	{
		return new Void(dim);
	}
	
	/**
	 * Checks if objects are {@code Void}.
	 * 
	 * @param o  a target object
	 * @return  {@code true} if the object is void
	 */
	public static boolean isVoid(Object o)
	{
		return o instanceof Void;
	}
	
	/**
	 * Generates {@code ASpace} geometry.
	 * 
	 * @param p  an origin point
	 * @param v  a vector space
	 * @return  an affine space
	 * 
	 * 
	 * @see VSpace
	 * @see Affine
	 * @see Point
	 */
	public static Affine Span(Point p, VSpace v)
	{
		if(p == null)
		{
			// Without an origin, creates a void.
			return Geometries.Void(v.Dimension());
		}
		
		if(v.Dimension() == 0)
		{
			// A trivial vector space creates a point.
			return p;
		}
		
		if(v.Dimension() == 1)
		{
			// A one-dimensional vector space creates a line.
			return Line(p, (Vector) v.Generator());
		}

		return new ASpace(p, v);
	}
	
	/**
	 * Generates {@code Line} geometry.
	 * 
	 * @param p  an origin point
	 * @param v  a vector direction
	 * @return   an affine line
	 * 
	 * 
	 * @see Vector
	 * @see Affine
	 * @see Point
	 */
	public static Affine Line(Point p, Vector v)
	{
		if(v.Size() == 2)
		{
			return new Line2D(p, (Vector2) v);
		}
		
		if(v.Size() == 3)
		{
			return new Line3D(p, (Vector3) v);
		}
		
		return new Line(p, v);
	}
	
	/**
	 * Generates {@code Hull} geometry.
	 * 
	 * @param gen  a generating matrix
	 * @return  a convex hull
	 * 
	 * 
	 * @see Matrix
	 * @see Hull
	 */
	public static Hull Hull(Matrix gen)
	{
		int rows = gen.Rows();
		int cols = gen.Columns();

		if(cols == 1)
		{
			Vector v = gen.Column(0);
			return new Point(v, 1f);
		}
		
		if(cols == 2)
		{
			Vector p = gen.Column(0);
			Vector q = gen.Column(1);
			return Segment(p,q);
		}
		
		if(cols == 3)
		{
			Vector p = gen.Column(0);
			Vector q = gen.Column(1);
			Vector r = gen.Column(2);
			return Triangle(p,q,r);
		}
		
		
		if(rows == 2)
		{
			return new Hull2D(gen);
		}
		
		if(rows == 3)
		{
			return new Hull3D(gen);
		}
		
		return new HullND(gen);
	}
	
	
	/**
	 * Generates {@code HyperCube} geometry.
	 * 
	 * @param c  a cube center
	 * @param l  a cube length
	 * @return   a cube
	 * 
	 * 
	 * @see Vector
	 * @see HyperCube
	 */
	public static HyperCube Cube(Vector c, float l)
	{
		if(c.Size() == 2)
			return new Square((Vector2) c, l);
		if(c.Size() == 3)
			return new Cube((Vector3) c, l);
		
		return new CubeND(c, l);
	}
	
	/**
	 * Generates {@code HyperCuboid} geometry.
	 * 
	 * @param c  a cuboid center
	 * @param s  a cuboid size
	 * @return   a cuboid
	 * 
	 * 
	 * @see HyperCuboid
	 * @see Vector
	 */
	public static HyperCuboid Cuboid(Vector c, Vector s)
	{
		if(c.Size() == 2)
			return new Rectangle((Vector2) c, (Vector2) s);
		if(c.Size() == 3)
			return new Cuboid((Vector3) c, (Vector3) s);
		
		return new CuboidND(c, s);
	}
		
	/**
	 * Generates {@code Segment} geometry.
	 * 
	 * @param p  a point vector
	 * @param q  a point vector
	 * @return   a segment
	 * 
	 * 
	 * @see Segment
	 * @see Vector
	 */
	public static Segment Segment(Vector p, Vector q)
	{
		if(p.Size() == 2)
			return new Segment2D((Vector2) p, (Vector2) q);
		if(p.Size() == 3)
			return new Segment3D((Vector3) p, (Vector3) q);
		
		return new SegmentND(p, q);
	}
	
	/**
	 * Generates {@code Triangle} geometry.
	 * 
	 * @param a  a point vector
	 * @param b  a point vector
	 * @param c  a point vector
	 * @return   a triangle
	 * 
	 * 
	 * @see Triangle
	 * @see Vector
	 */
	public static Triangle Triangle(Vector a, Vector b, Vector c)
	{
		if(a.Size() == 2)
			return new Triangle2D((Vector2) a, (Vector2) b, (Vector2) c);
		if(a.Size() == 3)
			return new Triangle3D((Vector3) a, (Vector3) b, (Vector3) c);

		return new TriangleND(a, b, c);
	}
	
	/**
	 * Generates {@code HyperSpheroid} geometry.
	 * 
	 * @param c  a spheroid center
	 * @param s  a spheroid size
	 * @return   a spheroid
	 * 
	 * 
	 * @see HyperSpheroid
	 * @see Vector
	 */
	public static HyperSpheroid Ellipsoid(Vector c, Vector s)
	{
		if(c.Size() == 2)
			return new Ellipse((Vector2) c, (Vector2) s);
		if(c.Size() == 3)
			return new Spheroid((Vector3) c, (Vector3) s);
		
		return new SpheroidND(c, s);
	}
	
	/**
	 * Generates {@code HyperSphere} geometry.
	 * 
	 * @param c  a sphere center
	 * @param r  a sphere radius
	 * @return   a sphere
	 * 
	 * 
	 * @see HyperSphere
	 * @see Vector
	 */
	public static HyperSphere Sphere(Vector c, float r)
	{
		if(c.Size() == 2)
			return new Circle((Vector2) c, r);
		if(c.Size() == 3)
			return new Sphere((Vector3) c, r);
		
		return new SphereND(c, r);
	}
			
	
	/**
	 * Generates unit {@code HyperSphere} geometry.
	 * 
	 * @param dim  a space dimension
	 * @return  a unit sphere
	 * 
	 * 
	 * @see HyperSphere
	 */
	public static HyperSphere Sphere(int dim)
	{
		if(dim <= 1) return null;
		if(dim == 2) return new Circle();
		if(dim == 3) return new Sphere();
		return new SphereND(dim);
	}
	
	/**
	 * Generates unit {@code HyperCube} geometry.
	 * 
	 * @param dim  a space dimension
	 * @return  a unit cube
	 * 
	 * 
	 * @see HyperCube
	 */
	public static HyperCube Cube(int dim)
	{
		if(dim <= 1) return null;
		if(dim == 2) return new Square();
		if(dim == 3) return new Cube();
		return new CubeND(dim);
	}
	
	
	private Geometries()
	{
		// NOT APPLICABLE
	}
}