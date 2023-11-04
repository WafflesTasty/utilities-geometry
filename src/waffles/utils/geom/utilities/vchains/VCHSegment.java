package waffles.utils.geom.utilities.vchains;

import java.util.Iterator;

import waffles.utils.algebra.elements.linear.vector.Vector;
import waffles.utils.geom.collidable.convex.hulls.segments.Segment;
import waffles.utils.geom.utilities.VChain;
import waffles.utils.tools.primitives.Integers;

/**
 * A {@code VCHSegment} generates vertex chains for an {@code Segment}.
 *
 * @author Waffles
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
	private Segment src;
	
	/**
	 * Creates a new {@code VCHSegment}.
	 * 
	 * @param s  a source segment
	 * @param m  a chain mode
	 * 
	 * 
	 * @see Segment
	 */
	public VCHSegment(Segment s, Mode m)
	{
		mode = m;
		src = s;
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
				if(next == 1) return src.P1().Generator();
				if(next == 2) return src.P2().Generator();
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
				Vector p1 = src.P1().Generator();
				Vector p2 = src.P2().Generator();
				
				next++;
				if(next == 1) return p1.minus(p2);
				if(next == 2) return p2.minus(p1);
				return null;
			}
		};
	}
}