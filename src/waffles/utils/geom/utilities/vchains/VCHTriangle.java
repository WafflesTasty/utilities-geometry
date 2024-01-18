package waffles.utils.geom.utilities.vchains;

import java.util.Iterator;

import waffles.utils.algebra.elements.linear.vector.Vector;
import waffles.utils.geom.collidable.convex.hulls.triangles.Triangle;
import waffles.utils.geom.utilities.VChain;
import waffles.utils.tools.primitives.Integers;

/**
 * A {@code VCHTriangle} generates vertex chains for an {@code Triangle}.
 *
 * @author Waffles
 * @since 18 Jun 2020
 * @version 1.0
 * 
 * 
 * @see VChain
 */
public class VCHTriangle implements VChain
{	
	private Iterable<Integer> Points()
	{
		return () -> new Iterator<>()
		{
			private int curr = 0;
			
			@Override
			public boolean hasNext()
			{
				return curr < 3;
			}

			@Override
			public Integer next()
			{
				return curr++;
			}
		};
	}
	
	private Iterable<Integer> Lines()
	{
		return () -> new Iterator<>()
		{
			private int curr = 1;
			
			@Override
			public boolean hasNext()
			{
				return curr < 7;
			}

			@Override
			public Integer next()
			{
				return (curr++ % 6) / 2;
			}
		};
	}
	
	private Iterable<Integer> LineStrip()
	{
		return () -> new Iterator<>()
		{
			private int curr = 0;
			
			@Override
			public boolean hasNext()
			{
				return curr < 4;
			}

			@Override
			public Integer next()
			{
				return curr++ % 3;
			}
		};
	}
	
	private Iterable<Integer> LinesAdj()
	{
		return () -> new Iterator<>()
		{
			private int curr = -1;
			
			@Override
			public boolean hasNext()
			{
				return curr < 11;
			}

			@Override
			public Integer next()
			{
				return Integers.mod(curr++, 3);
			}
		};
	}

	
	
	private Mode mode;
	private Triangle src;
	
	/**
	 * Creates a new {@code VCHTriangle}.
	 * 
	 * @param s  a source triangle
	 * @param m  a chain mode
	 * 
	 * 
	 * @see Triangle
	 */
	public VCHTriangle(Triangle s, Mode m)
	{
		mode = m;
		src = s;
	}
	
	
	@Override
	public Iterable<Integer> Indices()
	{
		switch(mode)
		{
		case POINTS:
		case LINE_LOOP:
		case TRIANGLES:
		case TRIANGLE_FAN:
		case TRIANGLE_STRIP:
			return Points();
		case LINES:
			return Lines();
		case LINE_STRIP:
			return LineStrip();
		case LINES_ADJ:
			return LinesAdj();
		case LINE_STRIP_ADJ:
		case TRIANGLE_STRIP_ADJ:
		case TRIANGLES_ADJ:
		case PATCHES:
		default:
			return null;
		}
	}
	
	@Override
	public Iterable<Vector> Vertices()
	{
		return () -> new Iterator<>()
		{
			private int next = 0;
			
			
			@Override
			public boolean hasNext()
			{
				return next < 3;
			}

			@Override
			public Vector next()
			{
				next++;
				if(next == 1) return src.P1().Generator();
				if(next == 2) return src.P2().Generator();
				if(next == 3) return src.P3().Generator();
				return null;
			}
		};
	}
	
	@Override
	public Iterable<Vector> Normals()
	{
		return () -> new Iterator<>()
		{
			private int next = 0;
			
			private Vector p1 = src.P1().Generator();
			private Vector p2 = src.P2().Generator();
			private Vector p3 = src.P3().Generator();
			
			
			@Override
			public boolean hasNext()
			{
				return next < 3;
			}

			@Override
			public Vector next()
			{
				next++;
				if(next == 1) return p1.minus(p2.plus(p3).times(0.5f));
				if(next == 2) return p2.minus(p3.plus(p1).times(0.5f));
				if(next == 3) return p3.minus(p1.plus(p2).times(0.5f));
				return null;
			}
		};
	}
	
	@Override
	public int VertexCount()
	{
		return 3;
	}
}