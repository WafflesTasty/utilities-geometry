package zeno.util.geom.utilities;

import zeno.util.algebra.linear.matrix.Matrices;
import zeno.util.algebra.linear.matrix.Matrix;
import zeno.util.algebra.linear.vector.VSpace;
import zeno.util.algebra.linear.vector.Vector;
import zeno.util.algebra.linear.vector.fixed.Vector2;
import zeno.util.algebra.linear.vector.fixed.Vector3;
import zeno.util.geom.Affine;
import zeno.util.geom.ICollidable;
import zeno.util.geom.ITransformation;
import zeno.util.geom.collidables.ICollision;
import zeno.util.geom.collidables.IShapeable;
import zeno.util.geom.collidables.affine.ASpace;
import zeno.util.geom.collidables.affine.Point;
import zeno.util.geom.collidables.affine.lines.Line;
import zeno.util.geom.collidables.affine.lines.Line2D;
import zeno.util.geom.collidables.affine.lines.Line3D;
import zeno.util.geom.collidables.bounds.Bounds;
import zeno.util.geom.collidables.collisions.affine.CLSVoid;
import zeno.util.geom.collidables.geometry.generic.IConvex;
import zeno.util.geom.collidables.geometry.generic.ICube;
import zeno.util.geom.collidables.geometry.generic.ICuboid;
import zeno.util.geom.collidables.geometry.generic.IEllipsoid;
import zeno.util.geom.collidables.geometry.generic.ISegment;
import zeno.util.geom.collidables.geometry.generic.ISphere;
import zeno.util.geom.collidables.geometry.generic.ITriangle;
import zeno.util.geom.collidables.geometry.higher.NCube;
import zeno.util.geom.collidables.geometry.higher.NCuboid;
import zeno.util.geom.collidables.geometry.higher.NEllipsoid;
import zeno.util.geom.collidables.geometry.higher.NSegment;
import zeno.util.geom.collidables.geometry.higher.NSphere;
import zeno.util.geom.collidables.geometry.higher.NTriangle;
import zeno.util.geom.collidables.geometry.planar.Circle;
import zeno.util.geom.collidables.geometry.planar.Ellipse;
import zeno.util.geom.collidables.geometry.planar.Rectangle;
import zeno.util.geom.collidables.geometry.planar.Segment2D;
import zeno.util.geom.collidables.geometry.planar.Square;
import zeno.util.geom.collidables.geometry.planar.Triangle2D;
import zeno.util.geom.collidables.geometry.spatial.Cube;
import zeno.util.geom.collidables.geometry.spatial.Cuboid;
import zeno.util.geom.collidables.geometry.spatial.Ellipsoid;
import zeno.util.geom.collidables.geometry.spatial.Segment3D;
import zeno.util.geom.collidables.geometry.spatial.Sphere;
import zeno.util.geom.collidables.geometry.spatial.Triangle3D;
import zeno.util.geom.utilities.spin.Spin;

/**
 * The {@code Geometries} class defines static-access geometry operations.
 *
 * @author Zeno
 * @since Jul 23, 2019
 * @version 1.0
 */
public final class Geometries
{
	/**
	 * Defines an empty {@code Affine} object.
	 */
	public static Affine VOID = new Void();
	
	
	/**
	 * The {@code SpinError} defines an error thrown when
	 * two {@code Spin} objects are not compatible.
	 *
	 * @author Zeno
	 * @since Jan 22, 2020
	 * @version 1.0
	 * 
	 *
	 * @see RuntimeException
	 */
	public static class SpinError extends RuntimeException
	{
		private static final long serialVersionUID = 6899503424285607547L;

		
		/**
		 * Creates a new {@code SpinError}.
		 * 
		 * @param s1  a spin object
		 * @param s2  a spin object
		 * 
		 * 
		 * @see Spin
		 */
		public SpinError(Spin s1, Spin s2)
		{
			super("Spins are not compatible for (" + s1 + ", " + s2 + ").");
		}
	}
		
	/**
	 * The {@code EqualityError} defines an error thrown when
	 * {@link ICollidable#equals(ICollidable, int)} has not been implemented.
	 *
	 * @author Zeno
	 * @since Jul 23, 2019
	 * @version 1.0
	 * 
	 * 
	 * @see RuntimeException
	 */
 	public static class EqualityError extends RuntimeException
	{
		private static final long serialVersionUID = 8869287804826489223L;

		
		/**
		 * Creates a new {@code EqualityError}.
		 * 
		 * @param c1  a collidable object
		 * @param c2  a collidable object
		 * 
		 * 
		 * @see ICollidable
		 */
		public EqualityError(ICollidable c1, ICollidable c2)
		{
			super("Equality not implemented for (" + c1 + ", " + c2 + ").");
		}
	}
	
	/**
	 * The {@code IntersectingError} defines an error thrown when
	 * {@link ICollidable#intersects(ICollidable)} has not been implemented.
	 *
	 * @author Zeno
	 * @since Jul 23, 2019
	 * @version 1.0
	 * 
	 * 
	 * @see RuntimeException
	 */
	public static class IntersectingError extends RuntimeException
	{
		private static final long serialVersionUID = -1595472890688569318L;

		
		/**
		 * Creates a new {@code IntersectingError}.
		 * 
		 * @param c1  a collidable object
		 * @param c2  a collidable object
		 * 
		 * 
		 * @see ICollidable
		 */
		public IntersectingError(ICollidable c1, ICollidable c2)
		{
			super("Intersection not implemented for (" + c1 + ", " + c2 + ").");
		}
	}
	
	/**
	 * The {@code IntersectionError} defines an error thrown when
	 * {@link ICollidable#intersect(ICollidable)} has not been implemented.
	 *
	 * @author Zeno
	 * @since Jul 23, 2019
	 * @version 1.0
	 * 
	 * 
	 * @see RuntimeException
	 */
	public static class IntersectionError extends RuntimeException
	{
		private static final long serialVersionUID = 5708078836030861842L;

		
		/**
		 * Creates a new {@code IntersectionError}.
		 * 
		 * @param c1  a collidable object
		 * @param c2  a collidable object
		 * 
		 * 
		 * @see ICollidable
		 */
		public IntersectionError(ICollidable c1, ICollidable c2)
		{
			super("Intersection not implemented for (" + c1 + ", " + c2 + ").");
		}
	}
	
	/**
	 * The {@code ContainError} defines an error thrown when
	 * {@link ICollidable#contains(ICollidable)} has not been implemented.
	 *
	 * @author Zeno
	 * @since Jul 23, 2019
	 * @version 1.0
	 * 
	 * 
	 * @see RuntimeException
	 */
	public static class ContainError extends RuntimeException
	{		
		private static final long serialVersionUID = -4374565720733619149L;
		

		/**
		 * Creates a new {@code ContainError}.
		 * 
		 * @param c1  a collidable object
		 * @param c2  a collidable object
		 * 
		 * 
		 * @see ICollidable
		 */
		public ContainError(ICollidable c1, ICollidable c2)
		{
			super("Containment not implemented for (" + c1 + ", " + c2 + ").");
		}
	}
	
	/**
	 * The {@code TypeError} defines an error thrown when
	 * a {@link Point} is of the wrong subtype.
	 *
	 * @author Zeno
	 * @since Aug 24, 2019
	 * @version 1.0
	 * 
	 * 
	 * @see RuntimeException
	 */
	public static class TypeError extends RuntimeException
	{
		private static final long serialVersionUID = -6128377063726175060L;
		
		
		/**
		 * Creates a new {@code TypeError}.
		 * 
		 * @param p  a point object
		 * @param type  a point type
		 * 
		 * 
		 * @see Point
		 */
		public TypeError(Point p, Point.Type type)
		{
			super("Point " + p + " is not of the " + type + " type.");
		}
	}
	
	private static class Void implements Affine
	{
		@Override
		public ICollision Collisions()
		{
			return new CLSVoid();
		}

		@Override
		public Matrix Span()
		{
			return Matrices.identity(0);
		}
	}
	
		
	/**
	 * Computes the subtraction {@code Vector} between two affine {@code Points}.
	 * Requires both points to have non-zero mass.
	 * 
	 * @param p  an affine point
	 * @param q  an affine point
	 * @return  the vector p-q
	 * 
	 * 
	 * @see Vector
	 * @see Point
	 */
	public static Vector subtract(Point p, Point q)
	{
		return p.asVector().minus(q.asVector());
	}
	
	/**
	 * Computes the addition of an affine {@code Point} with a {@code Vector}.
	 * 
	 * @param p  a point to add
	 * @param v  a vector to add
	 * @return  the point p+v
	 * 
	 * 
	 * @see Vector
	 * @see Point
	 */
	public static Point add(Point p, Vector v)
	{
		return p.plus(new Point(v, 0f).times(p.Mass()));
	}
	
	
	/**
	 * Defines an affine space expanded to a new coördinate size.
	 * If the coördinate size increases the dimension increases as well.
	 * 
	 * @param s  a space to expand
	 * @param coords  a coördinate count to use
	 * @return  an expanded affine space
	 * 
	 * 
	 * @see Affine
	 */
	public static Affine expand(Affine s, int coords)
	{
		Matrix mat = s.Span();
		int cols = mat.Columns();
		int rows = mat.Rows();
		
		Matrix m = mat;
		if(rows > coords)
		{
			m = Matrices.resize(m, coords + 1, cols);
			for(int c = 0; c < cols; c++)
			{
				// Move the original homogenous co�rdinates.
				m.set(mat.get(rows - 1, c), coords, c);
			}
		}
		else
		{
			m = Matrices.resize(m, coords + 1, coords + cols - rows + 1);
			for(int c = 0; c < cols; c++)
			{
				// Move the original homogenous co�rdinates.
				m.set(mat.get(rows - 1, c), coords, c);
				m.set(0f, rows - 1, c);
			}
			
			float val = mat.get(rows - 1, cols - 1);
			for(int c = cols; c <= coords + cols - rows; c++)
			{
				m.set(val, coords, c);
				m.set(val, rows + c - cols - 1, c);
				for(int r = 0; r < rows - 1; r++)
				{
					// Copy the final affine point.
					m.set(mat.get(r, cols - 1), r, c);
				}
			}
		}
		
		return s.Factory().create(m);
	}
	
	/**
	 * Defines an affine set that occupies a new coördinate count.
	 * The amount of co�rdinates change but this does not increase dimension.
	 * 
	 * @param s  a set to occupy
	 * @param coords  a co�rdinate count to use
	 * @return  a new occupying affine set
	 * 
	 * 
	 * @see Affine
	 */
	public static Affine occupy(Affine s, int coords)
	{
		Matrix mat = s.Span();
		int cols = mat.Columns();
		int rows = mat.Rows();
		
		Matrix m = Matrices.resize(mat, coords + 1, cols);
		for(int c = 0; c < cols; c++)
		{
			// Move the original homogenous co�rdinates.
			m.set(mat.get(rows - 1, c), coords, c);
			if(rows <= coords)
			{
				m.set(0f, rows - 1, c);
			}
		}

		return s.Factory().create(m);
	}
	
	/**
	 * Generates an {@code Affine} subspace from a {@code VSpace}.
	 * 
	 * @param p  an origin point
	 * @param v  a standard vector space
	 * @return  the affine subspace p+v
	 * 
	 * 
	 * @see VSpace
	 * @see Affine
	 * @see Point
	 */
	public static Affine span(Point p, VSpace v)
	{
		if(p == null)
			return Geometries.VOID;
		if(v.Dimension() == 0)
			return p;
		if(v.Dimension() == 1)
			return span(p, (Vector) v.Span());

		return new ASpace(p, v);
	}
	
	/**
	 * Generates an {@code Affine} line from a point and a vector.
	 * 
	 * @param p  an origin point
	 * @param v  a vector direction
	 * @return  the affine line p+v
	 * 
	 * 
	 * @see Vector
	 * @see Affine
	 * @see Point
	 */
	public static Affine span(Point p, Vector v)
	{
		if(v.Size() == 2)
			return new Line2D(p, (Vector2) v);
		if(v.Size() == 3)
			return new Line3D(p, (Vector3) v);
		return new Line(p, v);
	}
	
	
	/**
	 * Generates a new {@code ICube} geometry.
	 * 
	 * @param c  a cube center
	 * @param l  a cube length
	 * @return  a new cube
	 * 
	 * 
	 * @see ICube
	 * @see Vector
	 */
	public static ICube cube(Vector c, float l)
	{
		if(c.Size() == 2)
			return new Square((Vector2) c, l);
		if(c.Size() == 3)
			return new Cube((Vector3) c, l);
		
		return new NCube(c, l);
	}
	
	/**
	 * Generates a new {@code ICuboid} geometry.
	 * 
	 * @param c  a cuboid center
	 * @param s  a cuboid size
	 * @return  a new cuboid
	 * 
	 * 
	 * @see ICuboid
	 * @see Vector
	 */
	public static ICuboid cuboid(Vector c, Vector s)
	{
		if(c.Size() == 2)
			return new Rectangle((Vector2) c, (Vector2) s);
		if(c.Size() == 3)
			return new Cuboid((Vector3) c, (Vector3) s);
		
		return new NCuboid(c, s);
	}
		
	/**
	 * Generates a new {@code IEllipsoid} geometry.
	 * 
	 * @param c  an ellipsoid center
	 * @param s  an ellipsoid size
	 * @return  a new ellipsoid
	 * 
	 * 
	 * @see IEllipsoid
	 * @see Vector
	 */
	public static IEllipsoid ellipsoid(Vector c, Vector s)
	{
		if(c.Size() == 2)
			return new Ellipse((Vector2) c, (Vector2) s);
		if(c.Size() == 3)
			return new Ellipsoid((Vector3) c, (Vector3) s);
		
		return new NEllipsoid(c, s);
	}
	
	/**
	 * Generates a new {@code ITriangle} geometry.
	 * 
	 * @param a  a first triangle point
	 * @param b  a second triangle point
	 * @param c  a third triangle point
	 * @return  a new triangle
	 * 
	 * 
	 * @see ITriangle
	 * @see Vector
	 */
	public static ITriangle triangle(Vector a, Vector b, Vector c)
	{
		if(a.Size() == 2)
			return new Triangle2D((Vector2) a, (Vector2) b, (Vector2) c);
		if(a.Size() == 3)
			return new Triangle3D((Vector3) a, (Vector3) b, (Vector3) c);

		return new NTriangle(a, b, c);
	}
	
	/**
	 * Generates a new {@code ISegment} geometry.
	 * 
	 * @param p  a segment endpoint
	 * @param q  a segment endpoint
	 * @return  a new segment
	 * 
	 * 
	 * @see ISegment
	 * @see Vector
	 */
	public static ISegment segment(Vector p, Vector q)
	{
		if(p.Size() == 2)
			return new Segment2D((Vector2) p, (Vector2) q);
		if(p.Size() == 3)
			return new Segment3D((Vector3) p, (Vector3) q);
		
		return new NSegment(p, q);
	}
	
	/**
	 * Generates a new {@code ISphere} geometry.
	 * 
	 * @param c  a sphere center
	 * @param r  a sphere radius
	 * @return  a new sphere
	 * 
	 * 
	 * @see ISphere
	 * @see Vector
	 */
	public static ISphere sphere(Vector c, float r)
	{
		if(c.Size() == 2)
			return new Circle((Vector2) c, r);
		if(c.Size() == 3)
			return new Sphere((Vector3) c, r);
		
		return new NSphere(c, r);
	}
		
	
	/**
	 * Generates a new {@code IConvex} geometry.
	 * </b> This method returns a Minkowski difference used for collision.
	 * 
	 * @param a   a convex geometry
	 * @param b   a convex geometry
	 * @return  a convex difference
	 * 
	 * 
	 * @see IConvex
	 */
	public static IConvex diff(IConvex a, IConvex b)
	{
		return new IConvex()
		{
			@Override
			public Bounds Bounds(ITransformation map)
			{
				Vector min = a.Minimum().minus(b.Maximum());
				Vector max = a.Maximum().minus(b.Minimum());
				
				return Geometries.cuboid
				(
					min.plus(max).times(0.5f),
					max.minus(min)
				);
			}
			
			@Override
			public Vector Extremum(Vector v)
			{
				return a.Extremum(v).minus(b.Extremum(v.times(-1f)));
			}
		};
	}
	
	/**
	 * Generates a new {@code IConvex} geometry.
	 * 
	 * @param s  a target shapeable
	 * @return  a convex object
	 * 
	 * 
	 * @see IConvex
	 */
	public static IConvex convex(IShapeable s)
	{
		return new IConvex()
		{	
			@Override
			public Bounds Bounds(ITransformation map)
			{
				return s.Shape().Bounds(map);
			}
			
			@Override
			public Vector Extremum(Vector v)
			{
				Point p = (Point) s.Transform().unmap(new Point(v, 0f));
				Vector w = ((IConvex) s.Shape()).Extremum(p.asVector());
				p = (Point) s.Transform().map(new Point(w, 0f));
				return p.asVector();
			}
		};
	}
		
	/**
	 * Creates a unit {@code ISphere} geometry.
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
	 * Creates a unit {@code ICube} geometry.
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
	
	
	private Geometries()
	{
		// NOT APPLICABLE
	}
}