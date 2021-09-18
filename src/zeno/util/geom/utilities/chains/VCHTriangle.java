package zeno.util.geom.utilities.chains;

import java.util.Iterator;

import zeno.util.algebra.linear.vector.Vector;
import zeno.util.geom.collidables.geometry.generic.ISegment;
import zeno.util.geom.collidables.geometry.generic.ITriangle;
import zeno.util.geom.utilities.VChain;
import zeno.util.tools.Integers;

/**
 * The {@code VCHTriangle} class defines a {@code VChain} for triangles.
 *
 * @author Zeno
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
	private ITriangle shape;
	
	/**
	 * Creates a new {@code VCHTriangle}.
	 * 
	 * @param shape  a target shape
	 * @param mode   an index mode
	 * 
	 * 
	 * @see ISegment
	 */
	public VCHTriangle(ITriangle shape, Mode mode)
	{
		this.shape = shape;
		this.mode  = mode;
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
				if(next == 1) return shape.P1().asVector();
				if(next == 2) return shape.P2().asVector();
				if(next == 3) return shape.P3().asVector();
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
			
			private Vector p1 = shape.P1().asVector();
			private Vector p2 = shape.P2().asVector();
			private Vector p3 = shape.P3().asVector();
			
			
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
}