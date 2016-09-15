package zeno.util.geom.shapes.lines;

import zeno.util.algebra.Floats;
import zeno.util.algebra.vectors.fixed.Vector2;
import zeno.util.geom.IGeometry;
import zeno.util.geom.algorithms.Line2DClipper;
import zeno.util.geom.shapes.IGeometry2D;

/**
 * The {@code Line2D} class defines a two-dimensional line segment.
 * 
 * @since Jul 5, 2016
 * @author Zeno
 * 
 * @see IGeometry2D
 */
public class Line2D implements IGeometry2D
{
	private static final Line2DClipper clipper = new Line2DClipper();
	
	/**
	 * The {@code OrderBy} class defines vertex order methods.
	 *
	 * @since Apr 9, 2016
	 * @author Zeno
	 * 
	 * @see IGeometry
	 */
	public class OrderBy extends IGeometry.OrderBy
	{
		/**
		 * Creates a new {@code OrderBy}.
		 * 
		 * @param line  a target line
		 * @see Line2D
		 */
		public OrderBy(Line2D line)
		{
			super(line);
		}
		
		
		@Override
		public int[] LineStripAdjacency()
		{
			return LinesAdjacency();
		}
		
		@Override
		public int[] LinesAdjacency()
		{
			return new int[]
			{
				1, 0, 1, 0	
			};
		}
		
		
		@Override
		public int[] LineStrip()
		{
			return Lines();
		}
		
		@Override
		public int[] Lines()
		{
			return new int[]
			{
				0, 1	
			};
		}
	}
	
	
	private float x1, y1, x2, y2;
	
	/**
	 * Creates a new {@code Line2D}.
	 * 
	 * @param x1  the line's first x-coördinate
	 * @param y1  the line's first y-coördinate
	 * @param x2  the line's second x-coördinate
	 * @param y2  the line's second y-coördinate
	 */
	public Line2D(float x1, float y1, float x2, float y2)
	{
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
	}
	
	/**
	 * Creates a new {@code Line2D}.
	 * 
	 * @param p1  the line's first point
	 * @param p2  the line's second point
	 * @see Vector2
	 */
	public Line2D(Vector2 p1, Vector2 p2)
	{
		this(p1.X(), p1.Y(), p2.X(), p2.Y());
	}

	/**
	 * Creates a new {@code Line2D}.
	 */
	public Line2D()
	{
		this(-.5f, -.5f, .5f, .5f);
	}
	
	
	/**
	 * Returns the first point of the {@code Line2D}.
	 * 
	 * @return  the line's first point
	 * @see Vector2
	 */
	public Vector2 P1()
	{
		return new Vector2(x1, y1);
	}
	
	/**
	 * Returns the second point of the {@code Line2D}.
	 * 
	 * @return  the line's second point
	 * @see Vector2
	 */
	public Vector2 P2()
	{
		return new Vector2(x2, y2);
	}

	
	/**
	 * Returns the first x-coördinate of the {@code Line2D}.
	 * 
	 * @return  the line's first x-coördinate
	 */
	public float X1()
	{
		return x1;
	}
	
	/**
	 * Returns the first y-coördinate of the {@code Line2D}.
	 * 
	 * @return  the line's first y-coördinate
	 */
	public float Y1()
	{
		return y1;
	}
	
	/**
	 * Returns the second x-coördinate of the {@code Line2D}.
	 * 
	 * @return  the line's second x-coördinate
	 */
	public float X2()
	{
		return x2;
	}
	
	/**
	 * Returns the second y-coördinate of the {@code Line2D}.
	 * 
	 * @return  the line's second y-coördinate
	 */
	public float Y2()
	{
		return y2;
	}
	
	
	/**
	 * Changes the first point of the {@code Line2D}.
	 * 
	 * @param x  the point's x-coördinate
	 * @param y  the point's y-coördinate
	 */
	public void setP1(float x, float y)
	{
		x1 = x;
		y1 = y;
	}
	
	/**
	 * Changes the second point of the {@code Line2D}.
	 * 
	 * @param x  the point's x-coördinate
	 * @param y  the point's y-coördinate
	 */
	public void setP2(float x, float y)
	{
		x2 = x;
		y2 = y;
	}
	
	/**
	 * Changes the first point of the {@code Line2D}.
	 * 
	 * @param p  a point
	 * @see Vector2
	 */
	public void setP1(Vector2 p)
	{
		setP1(p.X(), p.Y());
	}
	
	/**
	 * Changes the second point of the {@code Line2D}.
	 * 
	 * @param p  a point
	 * @see Vector2
	 */
	public void setP2(Vector2 p)
	{
		setP2(p.X(), p.Y());
	}

	
	/**
	 * Indicates if the {@code Line2D} crosses another line.
	 * 
	 * @param x1  the line's first x-coördinate
	 * @param y1  the line's first y-coördinate
	 * @param x2  the line's second x-coördinate
	 * @param y2  the line's second y-coördinate
	 * @return  {@code true} if the lines intersect
	 */
	@Override
	public boolean crosses(float x1, float y1, float x2, float y2)
	{
		float rot1, rot2;
		
		rot1 = (X1() - x1) * (y2 - y1) - (Y1() - y1) * (x2 - x1);
		rot2 = (X2() - x1) * (y2 - y1) - (Y2() - y1) * (x2 - x1);
		
		if(Floats.sign(rot1) == Floats.sign(rot2))
		{
			return false;
		}
		
		rot1 = (X1() - X2()) * (y1 - Y2()) - (Y1() - Y2())*(x1 - X2());
		rot2 = (X1() - X2()) * (y2 - Y2()) - (Y1() - Y2())*(x2 - X2());
		
		if(Floats.sign(rot1) == Floats.sign(rot2))
		{
			return false;
		}
		
		return true;
	}
		
	/**
	 * Indicates if the {@code Line2D} intersects a rectangle.
	 * 
	 * @param x1  the rectangle's first x-coördinate
	 * @param y1  the rectangle's first y-coördinate
	 * @param x2  the rectangle's second x-coördinate
	 * @param y2  the rectangle's second y-coördinate
	 * @return  {@code true} if the line intersects the rectangle
	 */
	@Override
	public boolean intersects(float x1, float y1, float x2, float y2)
	{
		clipper.setBounds(x1, y1, x2, y2);
		if(clipper.clip(this) == null)
		{
			return false;
		}
		
		return true;
	}
	
	/**
	 * Indicates if the {@code Line2D} contains a rectangle.
	 * 
	 * @param x1  the rectangle's first x-coördinate
	 * @param y1  the rectangle's first y-coördinate
	 * @param x2  the rectangle's second x-coördinate
	 * @param y2  the rectangle's second y-coördinate
	 * @return  {@code true} if the line contains the rectangle
	 */
	@Override
	public boolean contains(float x1, float y1, float x2, float y2)
	{
		return false;
	}
	
	/**
	 * Indicates if the {@code Line2D} contains a point.
	 * 
	 * @param x  the point's x-coördinate
	 * @param y  the point's y-coördinate
	 * @return  {@code true} if the line contains the point
	 */
	@Override
	public boolean contains(float x, float y)
	{
		float xVal = (x - x1) / (x2 - x1);
		float yVal = (y - y1) / (y2 - y1);
		
		return Floats.isEqual(xVal, yVal)
			&& x1 <= x && x <= x2
			&& y1 <= y && y <= y2;
	}
	
	
	@Override
	public OrderBy OrderBy()
	{
		return new OrderBy(this);
	}
	
	@Override
	public Vector2[] Vertices()
	{
		return new Vector2[]
		{
			P1(), P2()	
		};
	}
		
	@Override
	public int VertexCount()
	{
		return 2;
	}

	
	@Override
	public boolean equals(Object o)
	{
		if(o instanceof Line2D)
		{
			Line2D oLine = (Line2D) o;
			return Floats.isEqual(XMin(), oLine.XMin())
				&& Floats.isEqual(XMax(), oLine.XMax())
				&& Floats.isEqual(YMin(), oLine.YMin())
				&& Floats.isEqual(YMax(), oLine.YMax());
		}
		
		return false;
	}
	
	@Override
	public int hashCode()
	{
		int code = 1;
		
		code = code * 17 + Floats.hashCode(x1);
		code = code * 37 + Floats.hashCode(y1);
		code = code * 43 + Floats.hashCode(x2);
		code = code * 23 + Floats.hashCode(y2);
		
		return code;
	}
	
	
	@Override
	public float XMin()
	{
		return Floats.min(x1, x2);
	}
	
	@Override
	public float XMax()
	{
		return Floats.max(x1, x2);
	}
	
	@Override
	public float YMin()
	{
		return Floats.min(y1, y2);
	}
	
	@Override
	public float YMax()
	{
		return Floats.max(y1, y2);
	}
}