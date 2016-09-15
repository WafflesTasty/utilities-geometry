package zeno.util.geom.shapes.lines;

import zeno.util.algebra.Floats;
import zeno.util.algebra.vectors.fixed.Vector3;
import zeno.util.geom.IGeometry;
import zeno.util.geom.algorithms.Line3DClipper;
import zeno.util.geom.shapes.IGeometry3D;

/**
 * The {@code Line3D} class defines a three-dimensional line segment.
 * 
 * @since Jul 5, 2016
 * @author Zeno
 * 
 * @see IGeometry3D
 */
public class Line3D implements IGeometry3D
{	
	private static final Line3DClipper clipper = new Line3DClipper();
	
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
		 * @see Line3D
		 */
		public OrderBy(Line3D line)
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
	
	
	private float x1, y1, z1, x2, y2, z2;
	
	/**
	 * Creates a new {@code Line3D}.
	 * 
	 * @param x1  the line's first x-coördinate
	 * @param y1  the line's first y-coördinate
	 * @param z1  the line's first z-coördinate
	 * @param x2  the line's second x-coördinate
	 * @param y2  the line's second y-coördinate
	 * @param z2  the line's second z-coördinate
	 */
	public Line3D(float x1, float y1, float z1, float x2, float y2, float z2)
	{
		this.x1 = x1;
		this.y1 = y1;
		this.z1 = z1;
		this.x2 = x2;
		this.y2 = y2;
		this.z2 = z2;
	}
	
	/**
	 * Creates a new {@code Line3D}.
	 * 
	 * @param p1  the line's first point
	 * @param p2  the line's second point
	 * @see Vector3
	 */
	public Line3D(Vector3 p1, Vector3 p2)
	{
		this(p1.X(), p1.Y(), p1.Z(), p2.X(), p2.Y(), p2.Z());
	}

	/**
	 * Creates a new {@code Line3D}.
	 */
	public Line3D()
	{
		this(-.5f, -.5f, -.5f, .5f, .5f, .5f);
	}
	
	
	/**
	 * Returns the first point of the {@code Line3D}.
	 * 
	 * @return  the line's first point
	 * @see Vector3
	 */
	public Vector3 P1()
	{
		return new Vector3(x1, y1, z1);
	}
	
	/**
	 * Returns the second point of the {@code Line3D}.
	 * 
	 * @return  the line's second point
	 * @see Vector3
	 */
	public Vector3 P2()
	{
		return new Vector3(x2, y2, z2);
	}

	
	/**
	 * Returns the first x-coördinate of the {@code Line3D}.
	 * 
	 * @return  the line's first x-coördinate
	 */
	public float X1()
	{
		return x1;
	}
	
	/**
	 * Returns the first y-coördinate of the {@code Line3D}.
	 * 
	 * @return  the line's first y-coördinate
	 */
	public float Y1()
	{
		return y1;
	}
	
	/**
	 * Returns the first z-coördinate of the {@code Line3D}.
	 * 
	 * @return  the line's first z-coördinate
	 */
	public float Z1()
	{
		return z1;
	}
	
	/**
	 * Returns the second x-coördinate of the {@code Line3D}.
	 * 
	 * @return  the line's second x-coördinate
	 */
	public float X2()
	{
		return x2;
	}
	
	/**
	 * Returns the second y-coördinate of the {@code Line3D}.
	 * 
	 * @return  the line's second y-coördinate
	 */
	public float Y2()
	{
		return y2;
	}
	
	/**
	 * Returns the second z-coördinate of the {@code Line3D}.
	 * 
	 * @return  the line's second z-coördinate
	 */
	public float Z2()
	{
		return z2;
	}
	
	
	/**
	 * Changes the first point of the {@code Line3D}.
	 * 
	 * @param x  the point's x-coördinate
	 * @param y  the point's y-coördinate
	 * @param z  the point's z-coördinate
	 */
	public void setP1(float x, float y, float z)
	{
		x1 = x;
		y1 = y;
		z1 = z;
	}
	
	/**
	 * Changes the second point of the {@code Line3D}.
	 * 
	 * @param x  the point's x-coördinate
	 * @param y  the point's y-coördinate
	 * @param z  the point's z-coördinate
	 */
	public void setP2(float x, float y, float z)
	{
		x2 = x;
		y2 = y;
		z2 = z;
	}
	
	/**
	 * Changes the first point of the {@code Line3D}.
	 * 
	 * @param p  a point
	 * @see Vector3
	 */
	public void setP1(Vector3 p)
	{
		setP1(p.X(), p.Y(), p.Z());
	}
	
	/**
	 * Changes the second point of the {@code Line3D}.
	 * 
	 * @param p  a point
	 * @see Vector3
	 */
	public void setP2(Vector3 p)
	{
		setP2(p.X(), p.Y(), p.Z());
	}

	
	/**
	 * Indicates if the {@code Line3D} crosses another line.
	 * 
	 * @param x1  the line's first x-coördinate
	 * @param y1  the line's first y-coördinate
	 * @param z1  the line's first z-coördinate
	 * @param x2  the line's second x-coördinate
	 * @param y2  the line's second y-coördinate
	 * @param z2  the line's second z-coördinate
	 * @return  {@code true} if the lines intersect
	 */
	@Override
	public boolean crosses(float x1, float y1, float z1, float x2, float y2, float z2)
	{
		float dpx = X2() - X1();
		float dpy = Y2() - Y1();
		float dpz = Z2() - Z1();
		
		float dqx = x2 - x1;
		float dqy = y2 - y1;
		float dqz = z2 - z1;
		
		
		float num = dpx * dqy - dpy * dqx;
		
		if(Floats.isZero(num))
		{
			return false;
		}
				
			
		float s = (dpx * (Y1() - y1) - dpy * (X1() - x1)) / num;		
		if(s < 0 || 1 < s)
		{
			return false;
		}
		
		float t = (dqx * (Y1() - y1) - dqy * (X1() - x1)) / num;
		if(t < 0 || 1 < t)
		{
			return false;
		}
		
		return Floats.isEqual(Z1() - z1, dqz * s - dpz * t);
	}
		
	/**
	 * Indicates if the {@code Line3D} intersects a cuboid.
	 * 
	 * @param x1  the cuboid's first x-coördinate
	 * @param y1  the cuboid's first y-coördinate
	 * @param z1  the cuboid's first z-coördinate
	 * @param x2  the cuboid's second x-coördinate
	 * @param y2  the cuboid's second y-coördinate
	 * @param z2  the cuboid's second z-coördinate
	 * @return  {@code true} if the line intersects the cuboid
	 */
	@Override
	public boolean intersects(float x1, float y1, float z1, float x2, float y2, float z2)
	{
		clipper.setBounds(x1, y1, z1, x2, y2, z2);
		if(clipper.clip(this) == null)
		{
			return false;
		}
		
		return true;
	}
	
	/**
	 * Indicates if the {@code Line3D} contains a cuboid.
	 * 
	 * @param x1  the cuboid's first x-coördinate
	 * @param y1  the cuboid's first y-coördinate
	 * @param z1  the cuboid's first z-coördinate
	 * @param x2  the cuboid's second x-coördinate
	 * @param y2  the cuboid's second y-coördinate
	 * @param z2  the cuboid's second z-coördinate
	 * @return  {@code true} if the line contains the cuboid
	 */
	@Override
	public boolean contains(float x1, float y1, float z1, float x2, float y2, float z2)
	{
		return false;
	}
	
	/**
	 * Indicates if the {@code Line3D} contains a point.
	 * 
	 * @param x  the point's x-coördinate
	 * @param y  the point's y-coördinate
	 * @param z  the point's z-coördinate
	 * @return  {@code true} if the line contains the point
	 */
	@Override
	public boolean contains(float x, float y, float z)
	{
		float xVal = (x - x1) / (x2 - x1);
		float yVal = (y - y1) / (y2 - y1);
		float zVal = (z - z1) / (z2 - z1);
		
		return Floats.isEqual(xVal, yVal)
			&& Floats.isEqual(xVal, zVal)
			&& x1 <= x && x <= x2
			&& y1 <= y && y <= y2
			&& z1 <= y && y <= z2;
	}
	
	
	@Override
	public OrderBy OrderBy()
	{
		return new OrderBy(this);
	}
	
	@Override
	public Vector3[] Vertices()
	{
		return new Vector3[]
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
		if(o instanceof Line3D)
		{
			Line3D oLine = (Line3D) o;
			return Floats.isEqual(XMin(), oLine.XMin())
				&& Floats.isEqual(XMax(), oLine.XMax())
				&& Floats.isEqual(YMin(), oLine.YMin())
				&& Floats.isEqual(YMax(), oLine.YMax())
				&& Floats.isEqual(ZMin(), oLine.ZMin())
				&& Floats.isEqual(ZMax(), oLine.ZMax());
		}
		
		return false;
	}
	
	@Override
	public int hashCode()
	{
		int code = 1;
		
		code = code * 17 + Floats.hashCode(x1);
		code = code * 37 + Floats.hashCode(y1);
		code = code * 13 + Floats.hashCode(z1);
		code = code * 43 + Floats.hashCode(x2);
		code = code * 23 + Floats.hashCode(y2);
		code = code * 31 + Floats.hashCode(z2);
		
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
	
	@Override
	public float ZMin()
	{
		return Floats.min(z1, z2);
	}
	
	@Override
	public float ZMax()
	{
		return Floats.max(z1, z2);
	}
}