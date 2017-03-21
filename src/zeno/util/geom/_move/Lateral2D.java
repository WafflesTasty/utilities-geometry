package zeno.util.geom._move;

import zeno.util.algebra.tensors.vectors.Vector;
import zeno.util.algebra.tensors.vectors.fixed.Vector2;
import zeno.util.geom._newstuff.Hypercube;
import zeno.util.geom._refactor.shapes.Geometry2D;
import zeno.util.geom.algorithms.LineClipper;

/**
 * The {@code Lateral2D} class defines a two-dimensional four-edged shape.
 *
 * @since Aug 22, 2015
 * @author Zeno
 * 
 * @see Geometry2D
 */
public class Lateral2D extends Geometry2D
{
	private static final LineClipper clipper = new LineClipper();
	
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
		 * @param geom  a target geometry
		 * @see Lateral2D
		 */
		public OrderBy(Lateral2D geom)
		{
			super(geom);
		}
		
		

		@Override
		public int[] LineStripAdjacency()
		{
			return new int[]
			{
				2,
				0, 1, 3, 2, 0,
				1
			};
		}
		
		@Override
		public int[] LinesAdjacency()
		{
			return new int[]
			{
				2, 0, 1, 3,
				0, 1, 3, 2,
				1, 3, 2, 0,
				3, 2, 0, 1
			};
		}
		
		
		@Override
		public int[] TriangleStrip()
		{
			return new int[]
			{
				0, 1, 2, 3
			};
		}
		
		@Override
		public int[] Triangles()
		{
			return new int[]
			{
				0, 1, 3,
				0, 2, 3
			};
		}
		
		
		@Override
		public int[] LineStrip()
		{
			return new int[]
			{
				0, 1, 3, 2, 0
			};
		}
		
		@Override
		public int[] LineLoop()
		{
			return new int[]
			{
				0, 1, 3, 2
			};
		}
			
		@Override
		public int[] Lines()
		{
			return new int[]
			{
				0, 1,
				1, 3,
				3, 2,
				2, 0
			};
		}
	}
	
		
	/**
	 * Creates a new {@code Lateral2D}.
	 * 
	 * @param x  a center x-coördinate
	 * @param y  a center y-coördinate
	 * @param w  a width
	 * @param h  a height
	 */
	public Lateral2D(float x, float y, float w, float h)
	{
		super(x, y, w, h);
	}
	
	/**
	 * Creates a new {@code Lateral2D}.
	 */
	public Lateral2D()
	{
		this(0, 0, 1, 1);
	}
	
	
	/**
	 * Indicates if the {@code Lateral2D} crosses a line.
	 * 
	 * @param x1  the line's first x-coördinate
	 * @param y1  the line's first y-coördinate
	 * @param x2  the line's second x-coördinate
	 * @param y2  the line's second y-coördinate
	 * @return  {@code true} if the line intersects
	 */
	@Override
	public boolean crosses(float x1, float y1, float x2, float y2)
	{
		clipper.setBounds(new Hypercube(this));
		if(clipper.cast(Vector.split(2, x1, y1, x2, y2)) == null)
		{
			return false;
		}
		
		return true;
	}
	
	/**
	 * Indicates if the {@code Lateral2D} intersects a rectangle.
	 * 
	 * @param x1  the rectangle's first x-coördinate
	 * @param y1  the rectangle's first y-coördinate
	 * @param x2  the rectangle's second x-coördinate
	 * @param y2  the rectangle's second y-coördinate
	 * @return  {@code true} if the rectangle intersects
	 */
	@Override
	public boolean intersects(float x1, float y1, float x2, float y2)
	{
		return x1 <= XMax()
			&& x2 >= XMin()
			&& y1 <= YMax()
			&& y2 >= YMin();
	}

	/**
	 * Indicates if the {@code Lateral2D} contains a rectangle.
	 * 
	 * @param x1  the rectangle's first x-coördinate
	 * @param y1  the rectangle's first y-coördinate
	 * @param x2  the rectangle's second x-coördinate
	 * @param y2  the rectangle's second y-coördinate
	 * @return  {@code true} if the rectangle contains
	 */
	@Override
	public boolean contains(float x1, float y1, float x2, float y2)
	{
		return contains(x1, y1)
			&& contains(x2, y2);
	}
	
	/**
	 * Indicates if the {@code Lateral2D} contains a point.
	 * 
	 * @param x  the point's x-coördinate
	 * @param y  the point's y-coördinate
	 * @return  {@code true} if the point is contained
	 */
	@Override
	public boolean contains(float x, float y)
	{
		return XMin() < x && x < XMax()
			&& YMin() < y && y < YMax();
	}
	
		
	@Override
	public OrderBy OrderBy()
	{
		return new OrderBy(this);
	}

	@Override
	public Vector2[] Vertices()
	{
		float xmin = XMin();
		float xmax = XMax();
		float ymin = YMin();
		float ymax = YMax();
		
		return new Vector2[]
		{
			new Vector2(xmin, ymin),
			new Vector2(xmin, ymax),
			new Vector2(xmax, ymin),
			new Vector2(xmax, ymax)
		};
	}

	@Override
	public boolean equals(Object o)
	{
		if(o instanceof Lateral2D)
		{
			return super.equals(o);
		}
		
		return false;
	}
	
	@Override
	public int VertexCount()
	{
		return 4;
	}
}