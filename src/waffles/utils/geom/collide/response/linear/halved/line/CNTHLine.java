package waffles.utils.geom.collide.response.linear.halved.line;

import waffles.utils.alg.lin.measure.vector.Vector;
import waffles.utils.alg.utilities.Algorithmic;
import waffles.utils.geom.Collidable;
import waffles.utils.geom.collide.collision.Collision.Response;
import waffles.utils.geom.shapes.fixed.Void;
import waffles.utils.geom.shapes.linear.halved.HSpace;
import waffles.utils.geom.shapes.linear.halved.lines.HLine;
import waffles.utils.geom.shapes.points.Point;
import waffles.utils.tools.primitives.Doubles;

/**
 * A {@code CNTHLine} computes a containment {@code Response} between halflines.
 *
 * @author Waffles
 * @since 27 Sep 2024
 * @version 1.1
 *
 *
 * @see Response
 */
public class CNTHLine implements Response
{
	/**
	 * The {@code Hints} interface defines hints for a {@code CNTHLine}.
	 *
	 * @author Waffles
	 * @since 25 Nov 2025
	 * @version 1.1
	 *
	 * 
	 * @see Algorithmic
	 */
	public static interface Hints extends Algorithmic
	{		
		/**
		 * Returns the source line of the {@code Hints}.
		 * 
		 * @return  a source line
		 * 
		 * 
		 * @see HLine
		 */
		public abstract HLine S();
		
		/**
		 * Returns the target line of the {@code Hints}.
		 * 
		 * @return  a target line
		 * 
		 * 
		 * @see Point
		 */
		public abstract HLine T();
		
		
		@Override
		public default double Error()
		{
			return Doubles.pow(2, -8);
		}
	}
	
	
	private Response rsp;
	private HLine src, tgt;
	
	/**
	 * Creates a new {@code CNTHLine}.
	 * 
	 * @param s  a source line
	 * @param t  a target line
	 * 
	 * 
	 * @see HLine
	 */
	public CNTHLine(HLine s, HLine t)
	{
		Point p = s.Origin();
		Point q = t.Origin();
		
		Vector v = s.Direction();
		Vector w = t.Direction();
		
		HSpace g = new HSpace(p, v);
		HSpace h = new HSpace(q, w);
		
		rsp = g.contain(h);
		src = s; tgt = t;
	}

	
	@Override
	public HLine Source()
	{
		return src;
	}

	@Override
	public Collidable Shape()
	{
		if(hasImpact())
		{
			return tgt;
		}

		int dim = Dimension();
		return new Void(dim);
	}

	@Override
	public boolean hasImpact()
	{
		return rsp.hasImpact();
	}

	@Override
	public Point Contact()
	{
		return rsp.Contact();
	}

	@Override
	public int cost()
	{
		return rsp.cost();
	}
}