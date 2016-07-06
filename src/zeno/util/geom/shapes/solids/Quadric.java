package zeno.util.geom.shapes.solids;

import zeno.util.algebra.vectors.fixed.Vector3;
import zeno.util.geom.shapes.Geometry3D;
import zeno.util.geom.IGeometry;

/**
 * The {@code Quadric} class defines a convex quadric surface.
 *
 * @author Zeno
 * @since Apr 29, 2016
 * @see Geometry3D
 */
public abstract class Quadric extends Geometry3D
{
	private static final int DEF_COUNT = 64;
	
	/**
	 * The {@code OrderBy} class defines vertex order methods.
	 *
	 * @author Zeno
	 * @since Apr 9, 2016
	 * @see IGeometry
	 */
	public class OrderBy extends IGeometry.OrderBy
	{
		/**
		 * Creates a new {@code OrderBy}.
		 * 
		 * @param geom  a target geometry
		 * @see Quadric
		 */
		public OrderBy(Quadric geom)
		{
			super(geom);
		}
	}
	
	
	private int vCount;
	
	/**
	 * Creates a new {@code Quadric}.
	 */
	public Quadric()
	{
		this(0, 0, 0, 1, 1, 1);
	}
	
	/**
	 * Creates a new {@code Quadric}.
	 * 
	 * @param x  a center x-coördinate
	 * @param y  a center y-coördinate
	 * @param z  a center z-coördinate
	 * @param w  a quadric width
	 * @param h  a quadric height
	 * @param d  a quadric depth
	 */
	public Quadric(float x, float y, float z, float w, float h, float d)
	{
		this(DEF_COUNT, x, y, z, w, h, d);
	}
	
	/**
	 * Creates a new {@code Quadric}.
	 * 
	 * @param vcount  a vertex count
	 * @param x  a center x-coördinate
	 * @param y  a center y-coördinate
	 * @param z  a center z-coördinate
	 * @param w  a quadric width
	 * @param h  a quadric height
	 * @param d  a quadric depth
	 */
	public Quadric(int vcount, float x, float y, float z, float w, float h, float d)
	{
		super(x, y, z, w, h, d);
		vCount = vcount;
	}
	
	
	/**
	 * Indicates if the {@code Quadric} intersects a cuboid.
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
		// Closest x.
		float cx = X();
		if(x1 > X()) cx = x1;
		else if(x2 < X()) cx = x2;
				
		// Closest y.
		float cy = Y();
		if(y1 > Y()) cy = y1;
		else if(y2 < Y()) cy = y2;
		
		// Closest y.
		float cz = Z();
		if(z1 > Z()) cz = z1;
		else if(z2 < Z()) cz = z2;
		
		// Check closest point.
		return contains(cx, cy, cz);
	}

	/**
	 * Indicates if the {@code Quadric} contains a cuboid.
	 * 
	 * @param x1  the cuboid's first x-coördinate
	 * @param y1  the cuboid's first y-coördinate
	 * @param x2  the cuboid's second x-coördinate
	 * @param y2  the cuboid's second y-coördinate
	 * @return  {@code true} if the cuboid contains
	 */
	@Override
	public boolean contains(float x1, float y1, float z1, float x2, float y2, float z2)
	{
		return contains(x1, y1, z1)
			&& contains(x2, y2, z2);
	}
	
	/**
	 * Changes the {@code Quadric}'s vertex count.
	 * 
	 * @param vcount  a new vertex count
	 */
	public void setVCount(int vcount)
	{
		vCount = vcount;
	}

	
	@Override
	public OrderBy OrderBy()
	{
		return new OrderBy(this);
	}
	
	@Override
	public Vector3[] Vertices()
	{
		return null;
	}
		
	@Override
	public boolean equals(Object o)
	{
		if(o instanceof Quadric)
		{
			return super.equals(o);
		}
		
		return false;
	}
	
	@Override
	public int VertexCount()
	{
		return vCount + 1;
	}
}