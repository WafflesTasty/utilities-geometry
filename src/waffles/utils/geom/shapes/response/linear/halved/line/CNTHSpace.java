package waffles.utils.geom.shapes.response.linear.halved.line;

import waffles.utils.alg.lin.measure.vector.Vector;
import waffles.utils.alg.utilities.Algorithmic;
import waffles.utils.geom.Collidable;
import waffles.utils.geom.shapes.collision.Collision.Response;
import waffles.utils.geom.shapes.fixed.Void;
import waffles.utils.geom.shapes.linear.halved.HSpace;
import waffles.utils.geom.shapes.linear.halved.lines.HLine;
import waffles.utils.geom.shapes.points.Arrow;
import waffles.utils.geom.shapes.points.Point;
import waffles.utils.tools.primitives.Doubles;

/**
 * A {@code CNTHSpace} computes a containment {@code Response} between a halfline and a halfspace.
 *
 * @author Waffles
 * @since 27 Sep 2024
 * @version 1.1
 *
 *
 * @see Response
 */
public class CNTHSpace implements Response
{
	/**
	 * The {@code Hints} interface defines hints for a {@code CNTHSpace}.
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
		public abstract HLine L();
		
		/**
		 * Returns the target point of the {@code Hints}.
		 * 
		 * @return  a target point
		 * 
		 * 
		 * @see Point
		 */
		public abstract Point P();
		
		
		@Override
		public default double Error()
		{
			return Doubles.pow(2, -8);
		}
	}
	
	
	private HLine src;
	private HSpace tgt;
	private Response rsp;
	
	/**
	 * Creates a new {@code CNTHSpace}.
	 * 
	 * @param s  a source line
	 * @param t  a target space
	 * 
	 * 
	 * @see HSpace
	 * @see HLine
	 */
	public CNTHSpace(HLine s, HSpace t)
	{
		Vector x = s.Direction();
		Arrow v = new Arrow(x);
		Point o = s.Origin();
		
		
		HSpace spc = new HSpace(o, v);

		rsp = t.contain(spc);
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
		if(Dimension() == 1)
		{
			return rsp.hasImpact();
		}
		
		return false;
	}

	@Override
	public Point Contact()
	{
		return tgt.Origin();
	}

	@Override
	public int cost()
	{
		return rsp.cost();
	}
}