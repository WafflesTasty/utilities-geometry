package waffles.utils.geom.shapes.response.convex.spheres;

import waffles.utils.alg.lin.measure.vector.Vector;
import waffles.utils.alg.lin.measure.vector.Vectors;
import waffles.utils.alg.utilities.Algorithmic;
import waffles.utils.geom.shapes.collision.Collision.Response;
import waffles.utils.geom.shapes.convex.axial.sphere.HyperSphere;
import waffles.utils.geom.shapes.linear.affine.lines.Line;
import waffles.utils.geom.shapes.points.Point;
import waffles.utils.tools.primitives.Doubles;
import waffles.utils.tools.primitives.Floats;

/**
 * An {@code ISCLine} computes an intersection {@code Response} between a sphere and a linear space.
 *
 * @author Waffles
 * @since 12 May 2021
 * @version 1.0
 *
 *
 * @see Response
 */
public class ISCLine implements Response
{
	/**
	 * The {@code Hints} interface defines hints for a {@code ISCLine}.
	 *
	 * @author Waffles
	 * @since 25 Nov 2025
	 * @version 1.1
	 *
	 * 
	 * @see Algorithmic
	 */
	public static interface Hints extends Algorithmic
	{		
		/**
		 * Returns the target line of the {@code Hints}.
		 * 
		 * @return  a target line
		 * 
		 * 
		 * @see Line
		 */
		public abstract Line T();

		/**
		 * Returns the source sphere of the {@code Hints}.
		 * 
		 * @return  a source sphere
		 * 
		 * 
		 * @see HyperSphere
		 */
		public abstract HyperSphere S();
		
		
		@Override
		public default double Error()
		{
			return Doubles.pow(2, -8);
		}
	}
	
	
	private Float d, s;
	private Float d1, d2;
	private Point x, y, z;
	private Hints hints;

	/**
	 * Creates a new {@code ISCLine}.
	 * 
	 * @param h  response hints
	 * 
	 * 
	 * @see Hints
	 */
	public ISCLine(Hints h)
	{
		hints = h;
	}
	
	/**
	 * Creates a new {@code ISCLine}.
	 *
	 * @param s  a source sphere
	 * @param t  a target line
	 *
	 *
	 * @see HyperSphere
	 * @see Line
	 */
	public ISCLine(HyperSphere s, Line t)
	{
		this(new Hints()
		{
			@Override
			public HyperSphere S()
			{
				return s;
			}
			
			@Override
			public Line T()
			{
				return t;
			}
		});
	}
	
	/**
	 * Returns {@code Response} hints.
	 * 
	 * @return  response hints
	 * 
	 * 
	 * @see Hints
	 */
	public Hints Hints()
	{
		return hints;
	}

	
	/**
	 * Returns a target {@code Point} x.
	 * This represents a point on the line
	 * which is the closest to the center
	 * of the {@code HyperSphere}.
	 * 
	 * @return  a target point
	 * 
	 * 
	 * @see Point
	 */
	public Point X()
	{
		if(x == null)
		{
			Vector v = Hints().T().Direction();
			Point p = Hints().T().Origin();
			
			x = p.plus(v.times(K()));
		}
		
		return x;
	}
	
	/**
	 * Returns a target {@code Point} y.
	 * This represents an endpoint on the
	 * intersection {@code Segment}.
	 * 
	 * @return  a target point
	 * 
	 * 
	 * @see Point
	 */
	public Point Y()
	{
		if(y == null)
		{
			Vector v = Hints().T().Direction();
			Point p = Hints().T().Origin();
			
			y = p.plus(v.times(L()));
		}
		
		return y;
	}
	
	/**
	 * Returns a target {@code Point} z.
	 * This represents an endpoint on the
	 * intersection {@code Segment}.
	 * 
	 * @return  a target point
	 * 
	 * 
	 * @see Point
	 */
	public Point Z()
	{
		if(z == null)
		{
			Vector v = Hints().T().Direction();
			Point p = Hints().T().Origin();
			
			z = p.plus(v.times(M()));
		}
		
		return z;
	}
	
	
	/**
	 * Returns a {@code Discriminant}.
	 * 
	 * @return  a discriminant
	 */
	public float D()
	{
		if(d == null)
		{
			compute();
		}
		
		return d;
	}
	
	/**
	 * Returns a target {@code Kappa}.
	 * This is the coefficient for the point
	 * on the line closest to the center
	 * of the {@code HyperSphere}.
	 * 
	 * @return  a kappa value
	 */
	public float K()
	{
		if(d == null)
		{
			compute();
		}
		
		double e = Hints().Error();
		if(e < Floats.abs(d1))
		{
			return d2 / d1;
		}
		
		return 0.5f;
	}
	
	/**
	 * Returns a target {@code Lambda}.
	 * This is the coefficient for an endpoint
	 * on the intersection {@code Segment}.
	 * 
	 * @return  a lambda value
	 */
	public float L()
	{
		return (d2 - S()) / d1;
	}
	
	/**
	 * Returns a target {@code Mu}.
	 * This is the coefficient for an endpoint
	 * on the intersection {@code Segment}.
	 * 
	 * @return  a mu value
	 */
	public float M()
	{
		return (d2 + S()) / d1;
	}
	

	private float S()
	{
		if(s == null)
		{
			s = Floats.sqrt(D());
		}
		
		return s;
	}
	
	private void compute()
	{
		double e = Hints().Error();
		float r = Hints().S().Radius();
		Point o = Hints().S().Origin();
		
		Point p = Hints().T().P1();
		Point q = Hints().T().P2();
		
		
		Point op = o.minus(p);
		Point qp = q.minus(p);
		
		d1 = qp.dot(qp);
		d2 = op.dot(qp);		
		
		d = op.dot(op) - r * r;
		d = 4 * (d2 * d2 - d1 * d);
	}

	@Override
	public boolean hasImpact()
	{
		double e = Hints().Error();
		return D() >= -e;
	}
	
	@Override
	public HyperSphere Source()
	{
		return Hints().S();
	}

	@Override
	public Vector Penetration()
	{
		if(hasImpact())
		{
			float r = Hints().S().Radius();
			Point o = Hints().S().Origin();
			
			Point dst = o.minus(x);
			float d = dst.norm();

			dst = dst.times((r - d) / d);
			return dst.Vector();
		}

		int n = Dimension();
		return Vectors.create(n);
	}

	@Override
	public Vector Distance()
	{
		if(!hasImpact())
		{
			float r = Hints().S().Radius();
			Point o = Hints().S().Origin();
			
			Point dst = o.minus(x);
			float d = dst.norm();

			dst = dst.times((r - d) / d);
			return dst.Vector();
		}

		int n = Dimension();
		return Vectors.create(n);
	}

	@Override
	public Point Contact()
	{
		return X();
	}

	@Override
	public int cost()
	{
		return 8 * Dimension() + 4;
	}
}