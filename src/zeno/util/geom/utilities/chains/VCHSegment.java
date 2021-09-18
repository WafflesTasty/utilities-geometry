package zeno.util.geom.utilities.chains;

import java.util.Iterator;

import zeno.util.algebra.linear.vector.Vector;
import zeno.util.geom.collidables.geometry.generic.ISegment;
import zeno.util.geom.utilities.VChain;
import zeno.util.tools.Integers;

/**
 * The {@code VCHSegment} class defines a {@code VChain} for line segments.
 *
 * @author Zeno
 * @since 18 Jun 2020
 * @version 1.0
 * 
 * 
 * @see VChain
 */
public class VCHSegment implements VChain
{	
	private Iterable<Integer> Lines()
	{
		return () -> new Iterator<>()
		{
			private int curr;
			
			@Override
			public boolean hasNext()
			{
				return curr < 2;
			}

			@Override
			public Integer next()
			{
				return curr++;
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
				return curr < 3;
			}

			@Override
			public Integer next()
			{
				return Integers.mod(curr++, 2);
			}
		};
	}

	
	private Mode mode;
	private ISegment shape;
	
	/**
	 * Creates a new {@code VCHSegment}.
	 * 
	 * @param shape  a target shape
	 * @param mode   an index mode
	 * 
	 * 
	 * @see ISegment
	 */
	public VCHSegment(ISegment shape, Mode mode)
	{
		this.shape = shape;
		this.mode  = mode;
	}
	
	
	@Override
	public Iterable<Integer> Indices()
	{
		switch(mode)
		{
		case LINES:
		case LINE_STRIP:
		case POINTS:
			return Lines();
		case LINES_ADJ:
		case LINE_STRIP_ADJ:
			return LinesAdj();
		case LINE_LOOP:
		case PATCHES:
		case TRIANGLE_FAN:
		case TRIANGLE_STRIP:
		case TRIANGLE_STRIP_ADJ:
		case TRIANGLES:
		case TRIANGLES_ADJ:
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
				return next < 2;
			}

			@Override
			public Vector next()
			{
				next++;
				if(next == 1) return shape.P1();
				if(next == 2) return shape.P2();
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
			
			
			@Override
			public boolean hasNext()
			{
				return next < 2;
			}

			@Override
			public Vector next()
			{
				next++;
				if(next == 1) return shape.P1().minus(shape.P2());
				if(next == 2) return shape.P2().minus(shape.P1());
				return null;
			}
		};
	}
}