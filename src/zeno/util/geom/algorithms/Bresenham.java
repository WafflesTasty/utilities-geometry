package zeno.util.geom.algorithms;

import java.util.Iterator;

import zeno.util.algebra.tensors.vectors.Vector;
import zeno.util.tools.Messenger;
import zeno.util.tools.primitives.Floats;

/**
 * The {@code Bresenham} class is a generalization of Bresenham's line algorithm.
 * <br> It generates discrete points approximating n-dimensional lines.
 * 
 * @since Dec 8, 2016
 * @author Zeno
 * 
 * @see <a href="http://en.wikipedia.org/wiki/Bresenham's_line_algorithm">Bresenham&rsquo;s Algorithm</a>
 * @see Messenger
 * @see Iterator
 * @see Vector
 */
public class Bresenham extends Messenger implements Iterator<Vector>
{
	private Vector cur, nxt;
	private Vector dif, err;
	private Vector src, dst;
	
	
	private boolean isFinite;
	
	/**
	 * Creates a new {@code Bresenham}.
	 * If {@code isFinite} is {@code true}, the
	 * algorithm ends when the provided end point
	 * is reached. If {@code false}, it
	 * continues forever.
	 * 
	 * @param isFinite  a finish state
	 */
	public Bresenham(boolean isFinite)
	{
		this.isFinite = isFinite;
	}
	
	/**
	 * Creates a new {@code Bresenham}.
	 */
	public Bresenham()
	{
		this(true);
	}
	
			
	/**
	 * Generates a new set of points.
	 * 
	 * @param p  a start point
	 * @param q  an end point
	 * @see Vector
	 */
	public void generate(Vector p, Vector q)
	{		
		dif = q.minus(p);
		
		err = new Vector(dif.size());
		src = new Vector(dif.size());
		dst = new Vector(dif.size());
		
		
		float dMax = Floats.max(dif.values()) / 2;
		for(int i = 0; i < dif.size(); i++)
		{
			float iSrc = Floats.floor(p.get(i));
			float iDst = Floats.ceil(q.get(i));
			
			err.set(dMax, i);
			src.set(iSrc, i);
			dst.set(iDst, i);
		}
		
		
		sendMessage("Source: \t" 		+ src);
		sendMessage("Destination: \t" 	+ dst);
		sendMessage("Difference: \t" 	+ dif);
		sendMessage("Error: \t\t" 		+ err);

		nxt = src;
	}

	/**
	 * Generates a new set of points.
	 * <br> The value list contains the coördinates
	 * of the first and the second point, and should
	 * therefore contain an even number of values.
	 * 
	 * @param values  a list of values
	 */
	public void generate(float... values)
	{
		Vector[] vec = Vector.split(2, values);
		generate(vec[0], vec[1]);
	}
	

	@Override
	public boolean hasNext()
	{
		return !(isFinite && dst.equals(cur));
	}

	@Override
	public Vector next()
	{		
		float dMax, iErr, iNxt;	
		dMax = Floats.max(dif.values());
		
		sendMessage("Finding next point...");
		sendMessage("Maximum diff is " + dMax + ".");
		
		for(int i = 0; i < dif.size(); i++)
		{
			iErr  = err.get(i);
			iErr -= Floats.abs(dif.get(i));
			
			sendMessage("Checking coördinate " + i + "...");
			
			if(iErr <= 0)
			{
				iNxt  = nxt.get(i);
				iNxt += Floats.sign(dif.get(i));
				iErr += Floats.max(Floats.abs(dif.values()));
				
				sendMessage("Coördinate incremented.");
				
				nxt.set(iNxt, i);
			}
			
			err.set(iErr, i);
		}
		
		sendMessage("Result: " + nxt);
		
		cur  = nxt;
		return nxt;
	}
}