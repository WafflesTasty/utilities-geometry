package zeno.util.geom.utilities.chains;

import java.util.Iterator;

import zeno.util.algebra.linear.vector.Vector;
import zeno.util.algebra.linear.vector.fixed.Vector2;
import zeno.util.geom.collidables.geometry.planar.Circle;
import zeno.util.geom.utilities.VChain;
import zeno.util.tools.Floats;
import zeno.util.tools.Integers;
import zeno.util.tools.helper.iterators.counters.IntegerCounter;

/**
 * The {@code VCHCircle} class defines a {@code VChain} for circular shapes.
 *
 * @author Zeno
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
				return curr < count;
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
				return curr - 2 < count;
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

	private Iterable<Integer> LineLoop()
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
	
	private Iterable<Integer> LineStrip()
	{
		return () -> new Iterator<>()
		{
			private int curr;
			
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
	
	private Iterable<Integer> LineStripAdj()
	{
		return () -> new Iterator<>()
		{
			private int curr = -1;
			
			@Override
			public boolean hasNext()
			{
				return curr - 1 <= count;
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
				return curr <= count;
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
				return add != 2 || curr < count - 1;
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
		return () -> new IntegerCounter(count);
	}
	
	
	private int count;
	private float min, max;
	private Circle shape;
	private Mode mode;
	
	/**
	 * Creates a new {@code VCHCircle}.
	 * 
	 * @param shape  a target shape
	 * @param mode   an index mode
	 * 
	 * 
	 * @see Circle
	 */
	public VCHCircle(Circle shape, Mode mode)
	{
		this(shape, mode, DEF_COUNT);
	}
	
	/**
	 * Creates a new {@code VCHCircle}.
	 * 
	 * @param shape  a target shape
	 * @param mode   an index mode
	 * @param count  a vertex count
	 * 
	 * 
	 * @see Circle
	 */
	public VCHCircle(Circle shape, Mode mode, int count)
	{
		this(shape, 0f, 2 * Floats.PI, mode, count);
	}
	
	/**
	 * Creates a new {@code VCHCircle}.
	 * 
	 * @param shape a target shape
	 * @param min   a minimum angle
	 * @param max	a maximum angle
	 * @param mode  an index mode
	 * 
	 * 
	 * @see Circle
	 */
	public VCHCircle(Circle shape, float min, float max, Mode mode)
	{
		this(shape, min, max, mode, DEF_COUNT);
	}
	
	/**
	 * Creates a new {@code VCHCircle}.
	 * 
	 * @param shape a target shape
	 * @param min   a minimum angle
	 * @param max	a maximum angle
	 * @param mode  an index mode
	 * @param count a vertex count
	 * 
	 * 
	 * @see Circle
	 */
	public VCHCircle(Circle shape, float min, float max, Mode mode, int count)
	{
		this.count = count;
		this.shape = shape;
		this.mode  = mode;
		this.min = min;
		this.max = max;
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
			return LineLoop();
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
				return (Vector2) norms.next().plus(shape.Center());
			}
		};
	}
	
	@Override
	public Iterable<Vector> Normals()
	{
		return () -> new Iterator<>()
		{
			private int i = 0;
			private boolean hasCenter = hasCenter();
			
			
			@Override
			public boolean hasNext()
			{
				return i < count + (hasCenter ? 1 : 0);
			}

			@Override
			public Vector2 next()
			{
				if(i == count)
				{
					i++; return new Vector2();
				}
				
				float angle = min + (max - min) * i / count;
				float x = Floats.cos(angle) * shape.Radius();
				float y = Floats.sin(angle) * shape.Radius();
				Vector2 v = new Vector2(x, y);
				
				i++; return v;
			}
		};
	}
	
	private boolean hasCenter()
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