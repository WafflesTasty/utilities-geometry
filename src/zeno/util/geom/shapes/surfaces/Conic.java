package zeno.util.geom.shapes.surfaces;

import zeno.util.algebra.Floats;
import zeno.util.algebra.analysis.Polynomial;
import zeno.util.algebra.vectors.fixed.Vector2;
import zeno.util.geom.shapes.Geometry2D;
import zeno.util.geom.Geometry;

/**
 * The {@code Conic} class defines a convex conic section.
 *
 * @author Zeno
 * @since Apr 29, 2016
 * @see Geometry2D
 */
public abstract class Conic extends Geometry2D
{
	private static final int DEF_COUNT = 64;
	
	/**
	 * The {@code OrderBy} class defines vertex order methods.
	 *
	 * @author Zeno
	 * @since Apr 9, 2016
	 * @see Geometry
	 */
	public class OrderBy extends Geometry.OrderBy
	{
		/**
		 * Creates a new {@code OrderBy}.
		 * 
		 * @param geom  a target geometry
		 * @see Conic
		 */
		public OrderBy(Conic geom)
		{
			super(geom);
		}
		
		

		@Override
		public int[] LineStripAdjacency()
		{
			int vcount = VertexCount() - 1;
			int[] order = new int[vcount + 3];
			
			for(int i = 0; i < vcount + 3; i++)
			{
				order[i] = i % vcount;
			}
			
			return order;
		}
		
		@Override
		public int[] LinesAdjacency()
		{
			int vcount = VertexCount() - 1;
			int[] order = new int[4 * vcount];
			
			for(int i = 0; i < vcount; i++)
			{
				order[4 * i + 0] = (i + 0) % vcount;
				order[4 * i + 1] = (i + 1) % vcount;
				order[4 * i + 2] = (i + 2) % vcount;
				order[4 * i + 3] = (i + 3) % vcount;
			}
			
			return order;
		}
		
		
		@Override
		public int[] TriangleFan()
		{
			int vcount = VertexCount() - 1;
			int[] order = new int[vcount + 2];
			
			order[0] = vcount;
			for(int i = 0; i <= vcount; i++)
			{
				order[i + 1] = i % vcount;
			}
			
			return order;
		}
		
		@Override
		public int[] Triangles()
		{
			int vcount = VertexCount() - 1;
			int[] order = new int[3 * vcount];
			
			for(int i = 0; i < vcount; i++)
			{
				order[3 * i + 0] = vcount;
				order[3 * i + 1] = (i + 0) % vcount;
				order[3 * i + 2] = (i + 1) % vcount;
			}
			
			return order;
		}
		
		
		@Override
		public int[] LineStrip()
		{
			int vcount = VertexCount() - 1;
			int[] order = new int[vcount + 1];
			
			for(int i = 0; i <= vcount; i++)
			{
				order[i] = i % vcount;
			}
			
			return order;
		}
		
		@Override
		public int[] LineLoop()
		{
			int vcount = VertexCount() - 1;
			int[] order = new int[vcount];
			
			for(int i = 0; i < vcount; i++)
			{
				order[i] = i % vcount;
			}
			
			return order;
		}
			
		@Override
		public int[] Lines()
		{
			int vcount = VertexCount() - 1;
			int[] order = new int[2 * vcount];
			
			for(int i = 0; i < vcount; i++)
			{
				order[i + 0] = (i + 0) % vcount;
				order[i + 1] = (i + 1) % vcount;
			}
			
			return order;
		}
	}
	
	
	private int vCount;
	
	/**
	 * Creates a new {@code Conic}.
	 */
	public Conic()
	{
		this(0, 0, 1, 1);
	}
	
	/**
	 * Creates a new {@code Conic}.
	 * 
	 * @param x  a center x-coördinate
	 * @param y  a center y-coördinate
	 * @param w  a conic width
	 * @param h  a conic height
	 */
	public Conic(float x, float y, float w, float h)
	{
		this(DEF_COUNT, x, y, w, h);
	}
	
	/**
	 * Creates a new {@code Conic}.
	 * 
	 * @param vcount  a vertex count
	 * @param x  a center x-coördinate
	 * @param y  a center y-coördinate
	 * @param w  a conic width
	 * @param h  a conic height
	 */
	public Conic(int vcount, float x, float y, float w, float h)
	{
		super(x, y, w, h);
		vCount = vcount;
	}
	
	
	/**
	 * Indicates if the {@code Conic} intersects a rectangle.
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
		// Closest x.
		float cx = X();
		if(x1 > X()) cx = x1;
		else if(x2 < X()) cx = x2;
				
		// Closest y.
		float cy = Y();
		if(y1 > Y()) cy = y1;
		else if(y2 < Y()) cy = y2;
		
		// Check closest point.
		return contains(cx, cy);
	}

	/**
	 * Indicates if the {@code Conic} contains a rectangle.
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
	 * Calculates the tangents to the {@code Conic}.
	 * 
	 * @param v  a tangent source vector
	 * @return  an array of two tangents
	 * @see Vector2
	 */
	public Vector2[] tangentsTo(Vector2 v)
	{
		float p = v.X() - X();
		float q = v.Y() - Y();
		float s = Height() / 2;
		float r = Width() / 2;
		
		
		float rr = Floats.pow(r, 2);
		float ss = Floats.pow(s, 2);
		
		float ps = Floats.pow(p * s, 2);
		float qr = Floats.pow(q * r, 2);
		float rs = Floats.pow(r * s, 2);
				
		
		float a = ps + qr;
		float b = -2 * p * rs;
		float c = rr * (rs - qr);
		
		
		Polynomial poly = new Polynomial(c, b, a);
		float[] roots = poly.RealRoots();
		if(roots.length == 0)
		{
			return new Vector2[0];
		}
		
		
		float[] tangentx = new float[2];
		float[] tangenty = new float[2];

		tangentx[0] = roots.length == 1 ? roots[0] : Math.min(roots[0], roots[1]);
		tangentx[1] = roots.length == 1 ? roots[0] : Math.max(roots[0], roots[1]);
		
		tangenty[0] = Floats.sqrt(ss * (1 - tangentx[0] * tangentx[0] / rr));
		tangenty[1] = Floats.sqrt(ss * (1 - tangentx[1] * tangentx[1] / rr));
		
		tangentx[0] += X();
		tangentx[1] += X();
		
		
		boolean isSigned = false;
		if(-r < p && 0 < q)
		{
			isSigned = true;
			if(r <= p || 0 >= q)
			{
				tangenty[1] *= -1;
			}
		}
		
		if(!isSigned)
		{
			if(r > p && 0 > q)
			{
				isSigned = true;
				tangenty[1] *= -1;
				if(-r <= p || 0 <= q)
				{
					tangenty[0] *= -1;
				}
			}
		}
		
		if(!isSigned)
		{
			tangenty[0] *= -1;
		}

		tangenty[0] += Y();
		tangenty[1] += Y();
				
		return new Vector2[]
		{
			new Vector2(tangentx[0], tangenty[0]),
			new Vector2(tangentx[1], tangenty[1]),
		};
	}
	
	/**
	 * Changes the {@code Conic}'s vertex count.
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
	public Vector2[] Vertices()
	{
		Vector2[] vertices = new Vector2[vCount + 1];
		for(int i = 0; i < vCount; i++)
		{
			float angle = i * 2 * Floats.PI / vCount;
			float x = Floats.cos(angle) * Width() / 2;
			float y = Floats.sin(angle) * Height() / 2;
			
			vertices[i] = new Vector2(x + X(), y + Y());
		}
		
		vertices[vCount] = Center();
		return vertices;
	}
		
	@Override
	public boolean equals(Object o)
	{
		if(o instanceof Conic)
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