package zeno.util.geom.algorithms.vertices;

import zeno.util.algebra.tensors.vectors.fixed.Vector2;
import zeno.util.geom.algorithms.VertexArray;
import zeno.util.geom.interfaces.IShape2D;
import zeno.util.geom.shapes.solids.Ellipsoid;
import zeno.util.geom.shapes.surfaces.Circle;
import zeno.util.geom.shapes.surfaces.Ellipse;
import zeno.util.tools.primitives.Floats;

/**
 * The {@code EllipseArray} class defines a vertex generator for ellipses.
 * 
 * @since Mar 22, 2017
 * @author Zeno
 * 
 * @see VertexArray
 */
public class EllipseArray extends VertexArray
{
	private static final int DEF_COUNT = 64;
	
	/**
	 * The {@code OrderBy} class defines vertex order methods.
	 *
	 * @since Apr 9, 2016
	 * @author Zeno
	 * 
	 * @see VertexArray
	 * @see Ellipsoid
	 */
	public class OrderBy extends VertexArray.OrderBy
	{
		/**
		 * Creates a new {@code OrderBy}.
		 * 
		 * @param gen  a target generator
		 * @see EllipseArray
		 */
		public OrderBy(EllipseArray gen)
		{
			super(gen);
		}
		
		
		@Override
		public int[] LineStripAdjacency()
		{
			int vcount = Count() - 1;
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
			int vcount = Count() - 1;
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
			int vcount = Count() - 1;
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
			int vcount = Count() - 1;
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
			int vcount = Count() - 1;
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
			int vcount = Count() - 1;
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
			int vcount = Count() - 1;
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
	 * Creates a new {@code EllipseArray}.
	 */
	public EllipseArray()
	{
		this(DEF_COUNT);
	}
	
	/**
	 * Creates a new {@code EllipseArray}.
	 * 
	 * @param count  the vertex count
	 */
	public EllipseArray(int count)
	{
		vCount = count;
	}
	
	/**
	 * Generates vertices for an {@code Ellipse}.
	 * 
	 * @param e  an ellipse to generate
	 * @return  a list of vertices
	 * @see Vector2
	 * @see Ellipse
	 */
	public Vector2[] generate(Ellipse e)
	{
		return generate((IShape2D) e);
	}
	
	/**
	 * Generates vertices for a {@code Circle}.
	 * 
	 * @param c  a circle to generate
	 * @return  a list of vertices
	 * @see Vector2
	 * @see Circle
	 */
	public Vector2[] generate(Circle c)
	{
		return generate((IShape2D) c);
	}
	
	/**
	 * Changes the generator's vertex count.
	 * 
	 * @param count  a new vertex count
	 */
	public void setVCount(int count)
	{
		vCount = count;
	}
	
	
	Vector2[] generate(IShape2D s)
	{
		Vector2[] vertices = new Vector2[vCount + 1];
		for(int i = 0; i < vCount; i++)
		{
			float angle = i * 2 * Floats.PI / vCount;
			float x = Floats.cos(angle) * s.Width() / 2;
			float y = Floats.sin(angle) * s.Height() / 2;
			
			vertices[i] = new Vector2(x + s.X(), y + s.Y());
		}
		
		vertices[vCount] = s.Center();
		return vertices;
	}
	
	@Override
	public OrderBy OrderBy()
	{
		return new OrderBy(this);
	}
	
	@Override
	public int Count()
	{
		return vCount + 1;
	}
}