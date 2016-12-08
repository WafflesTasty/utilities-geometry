package zeno.util.geom.shapes.solids;

import zeno.util.geom.shapes.Geometry3D;
import zeno.util.algebra.tensors.vectors.fixed.Vector3;
import zeno.util.geom.IGeometry;
import zeno.util.geom.algorithms.Line3DClipper;

/**
 * The {@code Lateral3D} class defines a three-dimensional six-sided shape.
 *
 * @since Aug 22, 2015
 * @author Zeno
 * 
 * @see Geometry3D
 */
public class Lateral3D extends Geometry3D
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
		 * @param geom  a target geometry
		 * @see Lateral3D
		 */
		public OrderBy(Lateral3D geom)
		{
			super(geom);
		}
	

		@Override
		public int[] TriangleStrip()
		{
			return new int[]
			{
				2, 0, 6, 4, 7, 5,
				4, 0, 5, 1, 7, 3,
				1, 0, 3, 2, 7, 6
			};
		}
		
		@Override
		public int[] Triangles()
		{
			return new int[]
			{
				2, 0, 6, 0, 4, 6,
				6, 4, 7, 4, 5, 7,
				5, 1, 7, 1, 3, 7,
				3, 2, 7, 2, 6, 7,
				4, 0, 5, 0, 1, 5,
				1, 0, 3, 0, 2, 3
			};
		}
		
		@Override
		public int[] Lines()
		{
			return new int[]
			{
				2, 0, 0, 4, 4, 6, 6, 2,
				6, 4, 4, 5, 5, 7, 7, 6,
				4, 0, 0, 1, 1, 5, 5, 4,
				5, 1, 1, 3, 3, 7, 7, 5,
				1, 0, 0, 2, 2, 3, 3, 1,
				3, 2, 2, 6, 6, 7, 7, 3
			};
		}
	}
	
		
	/**
	 * Creates a new {@code Lateral3D}.
	 * 
	 * @param x  a center x-coördinate
	 * @param y  a center y-coördinate
	 * @param z  a center z-coördinate
	 * @param w  a width
	 * @param h  a height
	 * @param d  a depth
	 */
	public Lateral3D(float x, float y, float z, float w, float h, float d)
	{
		super(x, y, z, w, h, d);
	}
	
	/**
	 * Creates a new {@code Lateral3D}.
	 */
	public Lateral3D()
	{
		this(0, 0, 0, 1, 1, 1);
	}
	
	
	/**
	 * Indicates if the {@code Lateral3D} crosses a line.
	 * 
	 * @param x1  the line's first x-coördinate
	 * @param y1  the line's first y-coördinate
	 * @param z1  the line's first z-coördinate
	 * @param x2  the line's second x-coördinate
	 * @param y2  the line's second y-coördinate
	 * @param z2  the line's second z-coördinate
	 * @return  {@code true} if the line intersects
	 */
	@Override
	public boolean crosses(float x1, float y1, float z1, float x2, float y2, float z2)
	{
		clipper.setBounds(this);
		if(clipper.clip(x1, y1, z1, x2, y2, z2) == null)
		{
			return false;
		}
		
		return true;
	}
	
	/**
	 * Indicates if the {@code Lateral3D} intersects a cuboid.
	 * 
	 * @param x1  the cuboid's first x-coördinate
	 * @param y1  the cuboid's first y-coördinate
	 * @param z1  the cuboid's first z-coördinate
	 * @param x2  the cuboid's second x-coördinate
	 * @param y2  the cuboid's second y-coördinate
	 * @param z2  the cuboid's second z-coördinate
	 * @return  {@code true} if the cuboid intersects
	 */
	@Override
	public boolean intersects(float x1, float y1, float z1, float x2, float y2, float z2)
	{
		return x1 <= XMax()
			&& x2 >= XMin()
			&& y1 <= YMax()
			&& y2 >= YMin()
			&& z1 <= ZMax()
			&& z2 >= ZMin();
	}

	/**
	 * Indicates if the {@code Lateral3D} contains a cuboid.
	 * 
	 * @param x1  the cuboid's first x-coördinate
	 * @param y1  the cuboid's first y-coördinate
	 * @param z1  the cuboid's first z-coördinate
	 * @param x2  the cuboid's second x-coördinate
	 * @param y2  the cuboid's second y-coördinate
	 * @param z2  the cuboid's second z-coördinate
	 * @return  {@code true} if the cuboid contains
	 */
	@Override
	public boolean contains(float x1, float y1, float z1, float x2, float y2, float z2)
	{
		return contains(x1, y1, z1)
			&& contains(x2, y2, z2);
	}
	
	/**
	 * Indicates if the {@code Lateral3D} contains a point.
	 * 
	 * @param x  the point's x-coördinate
	 * @param y  the point's y-coördinate
	 * @param z  the point's z-coördinate
	 * @return  {@code true} if the point is contained
	 */
	@Override
	public boolean contains(float x, float y, float z)
	{
		return XMin() < x && x < XMax()
			&& YMin() < y && y < YMax()
			&& ZMin() < z && z < ZMax();
	}
	
	
	@Override
	public OrderBy OrderBy()
	{
		return new OrderBy(this);
	}

	@Override
	public Vector3[] Vertices()
	{
		float xmin = XMin();
		float xmax = XMax();
		float ymin = YMin();
		float ymax = YMax();
		float zmin = ZMin();
		float zmax = ZMax();
		
		return new Vector3[]
		{
			new Vector3(xmin, ymin, zmin),
			new Vector3(xmin, ymin, zmax),
			new Vector3(xmin, ymax, zmin),
			new Vector3(xmin, ymax, zmax),
			new Vector3(xmax, ymin, zmin),
			new Vector3(xmax, ymin, zmax),
			new Vector3(xmax, ymax, zmin),
			new Vector3(xmax, ymax, zmax)
		};
	}

	@Override
	public boolean equals(Object o)
	{
		if(o instanceof Lateral3D)
		{
			return super.equals(o);
		}
		
		return false;
	}
	
	@Override
	public int VertexCount()
	{
		return 8;
	}
}