package waffles.utils.geom.utilities.vchains;

import java.util.Iterator;

import waffles.utils.algebra.elements.linear.vector.Vector;
import waffles.utils.algebra.elements.linear.vector.fixed.Vector2;
import waffles.utils.geom.collidable.axial.spheroid.Circle;
import waffles.utils.geom.utilities.VChain;
import waffles.utils.tools.collections.iterators.counters.IntegerCounter;
import waffles.utils.tools.primitives.Floats;
import waffles.utils.tools.primitives.Integers;

/**
 * A {@code VCHCircle} generates vertex chains for a {@code Circle}.
 *
 * @author Waffles
 * @since 18 Jun 2020
 * @version 1.0
 * 
 * 
 * @see VChain
 */
public class VCHCircle implements VChain
{	
	private static int DEF_COUNT = 32;
	
	
	private Iterable<Integer> Lines()
	{
		return () -> new Iterator<>()
		{
			private int curr, add;
			
			@Override
			public boolean hasNext()
			{
				return curr < count - 1;
			}

			@Override
			public Integer next()
			{
				int next = curr + add;
				add = Integers.mod(add+1, 2);
				curr += add == 0 ? 1 : 0;
				return next % count;
			}
		};
	}
	
	private Iterable<Integer> LinesAdj()
	{
		return () -> new Iterator<>()
		{
			private int curr = -1, add;
			
			@Override
			public boolean hasNext()
			{
				return curr - 2 <= count;
			}

			@Override
			public Integer next()
			{
				int next = curr + add;
				add = Integers.mod(add+1, 4);
				curr += add == 0 ? 1 : 0;
				return next % count;
			}
		};
	}
	
	private Iterable<Integer> LineStrip()
	{
		return () -> new Iterator<>()
		{
			private int curr;
			
			@Override
			public boolean hasNext()
			{
				return curr < count;
			}

			@Override
			public Integer next()
			{
				return Integers.mod(curr++, count);
			}
		};
	}
	
	private Iterable<Integer> LineStripAdj()
	{
		return () -> new Iterator<>()
		{
			private int curr = -1;
			
			@Override
			public boolean hasNext()
			{
				return curr <= count;
			}

			@Override
			public Integer next()
			{
				return Integers.mod(curr++, count);
			}
		};
	}
	
	
	private Iterable<Integer> TriangleFan()
	{
		return () -> new Iterator<>()
		{
			private int curr = -1;
			
			@Override
			public boolean hasNext()
			{
				return curr < count;
			}

			@Override
			public Integer next()
			{
				return Integers.mod(curr++, count+1);
			}
		};
	}
	
	private Iterable<Integer> Triangles()
	{
		return () -> new Iterator<>()
		{
			private int curr = -1, add = 2;
			
			@Override
			public boolean hasNext()
			{
				return add != 2 || curr < count - 2;
			}

			@Override
			public Integer next()
			{
				if(add == 2)
				{
					curr++;
					add = Integers.mod(add+1, 3);
					return count;
				}
				
				int next = Integers.mod(curr + add, count);
				add = Integers.mod(add+1, 3);
				return next;
			}
		};
	}
	
	private Iterable<Integer> Points()
	{
		return () -> new IntegerCounter(count+1);
	}
	
	
	
	private int count;
	private Mode mode;
	private Circle src;
	private float aMin, aMax;
	
	/**
	 * Creates a new {@code VCHCircle}.
	 * 
	 * @param s  a source circle
	 * @param m  a chain mode
	 * 
	 * 
	 * @see Circle
	 */
	public VCHCircle(Circle s, Mode m)
	{
		this(s, m, DEF_COUNT);
	}
	
	/**
	 * Creates a new {@code VCHCircle}.
	 * 
	 * @param s  a source circle
	 * @param m  a chain mode
	 * @param c  a vertex count
	 * 
	 * 
	 * @see Circle
	 */
	public VCHCircle(Circle s, Mode m, int c)
	{
		this(s, 0f, 2 * Floats.PI, m, c);
	}
	
	/**
	 * Creates a new {@code VCHCircle}.
	 * 
	 * @param s  a source circle
	 * @param min   a minimum angle
	 * @param max	a maximum angle
	 * @param m  a chain mode
	 * 
	 * 
	 * @see Circle
	 */
	public VCHCircle(Circle s, float min, float max, Mode m)
	{
		this(s, min, max, m, DEF_COUNT);
	}
	
	/**
	 * Creates a new {@code VCHCircle}.
	 * 
	 * @param s  a source circle
	 * @param min   a minimum angle
	 * @param max	a maximum angle
	 * @param m  a chain mode
	 * @param c  a vertex count
	 * 
	 * 
	 * @see Circle
	 */
	public VCHCircle(Circle s, float min, float max, Mode m, int c)
	{
		count = c;
		aMin = min;
		aMax = max;
		mode  = m;
		src = s;
	}
	
	
	@Override
	public Iterable<Integer> Indices()
	{
		switch(mode)
		{
		case LINES:
			return Lines();
		case LINES_ADJ:
			return LinesAdj();
		case LINE_LOOP:
		case LINE_STRIP:
			return LineStrip();
		case LINE_STRIP_ADJ:
			return LineStripAdj();
		case TRIANGLES:
			return Triangles();
		case TRIANGLE_FAN:
			return TriangleFan();
		case POINTS:
			return Points();
		case PATCHES:
		case TRIANGLES_ADJ:
		case TRIANGLE_STRIP:
		case TRIANGLE_STRIP_ADJ:
		default:
			return null;
		}
	}
	
	@Override
	public Iterable<Vector> Vertices()
	{
		return () -> new Iterator<>()
		{
			private Iterator<Vector> norms = Normals().iterator();
			
			
			@Override
			public boolean hasNext()
			{
				return norms.hasNext();
			}

			@Override
			public Vector2 next()
			{
				return (Vector2) norms.next().plus(src.Origin());
			}
		};
	}
	
	@Override
	public Iterable<Vector> Normals()
	{
		return () -> new Iterator<>()
		{
			private int i = 0;			
			
			@Override
			public boolean hasNext()
			{
				return i < VertexCount();
			}

			@Override
			public Vector2 next()
			{
				if(i == count)
				{
					i++; return new Vector2();
				}
				
				
				float angle = aMin + (aMax - aMin) * i / (count - 1);
				float x = Floats.cos(angle) * src.Radius();
				float y = Floats.sin(angle) * src.Radius();
				
				Vector2 v = new Vector2(x, y);
				i++; return v;
			}
		};
	}
	
	
	@Override
	public int VertexCount()
	{
		return count + (hasCenter() ? 1 : 0);
	}
	
	boolean hasCenter()
	{
		switch(mode)
		{
		case TRIANGLES:
		case TRIANGLES_ADJ:
		case TRIANGLE_FAN:
		case TRIANGLE_STRIP:
		case TRIANGLE_STRIP_ADJ:
			return true;
		case LINES:
		case LINES_ADJ:
		case LINE_LOOP:
		case LINE_STRIP:
		case LINE_STRIP_ADJ:
		case PATCHES:
		case POINTS:
		default:
			return false;
		}
	}
}