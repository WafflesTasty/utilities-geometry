package zeno.util.geom.utilities.algorithms;

import java.util.Iterator;
import java.util.function.Consumer;

import zeno.util.algebra.linear.vector.Vector;
import zeno.util.algebra.linear.vector.Vectors;
import zeno.util.tools.Floats;
import zeno.util.tools.patterns.properties.Messenger;

/**
 * The {@code Bresenham} class is a generalization of Bresenham's line algorithm.
 * <br> It generates discrete points approximating n-dimensional lines.
 * 
 * @author Zeno
 * @since Dec 8, 2016
 * @version 1.0
 * 
 * 
 * @see <a href="http://en.wikipedia.org/wiki/Bresenham's_line_algorithm">Bresenham&rsquo;s Algorithm</a>
 * @see Messenger
 * @see Iterator
 * @see Vector
 */
public class Bresenham implements Messenger, Iterator<Vector>
{
	private Vector cur, nxt;
	private Vector dif, err;
	private Vector src, dst;
	
	
	private boolean isFinite;
	private Consumer<String> output;
	
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
	 * Changes the output of the {@code Bresenham}.
	 * 
	 * @param output  a new output
	 * 
	 * 
	 * @see Consumer
	 * @see String
	 */
	public void setOutput(Consumer<String> output)
	{
		this.output = output;
	}
			
	/**
	 * Generates a new set of points.
	 * 
	 * @param p  a start point
	 * @param q  an end point
	 * 
	 * 
	 * @see Vector
	 */
	public void generate(Vector p, Vector q)
	{		
		dif = q.minus(p);
		
		err = new Vector(dif.Size());
		src = new Vector(dif.Size());
		dst = new Vector(dif.Size());
		
		
		float dMax = Floats.max(dif.Values()) / 2;
		for(int i = 0; i < dif.Size(); i++)
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
	 * <br> The value list contains the co�rdinates
	 * of the first and the second point, and should
	 * therefore contain an even number of values.
	 * 
	 * @param values  a list of values
	 */
	public void generate(float... values)
	{
		Vector[] vec = Vectors.split(2, values);
		generate(vec[0], vec[1]);
	}
	
	
	@Override
	public Consumer<String> Output()
	{
		return output;
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
		dMax = Floats.max(dif.Values());
		
		sendMessage("Finding next point...");
		sendMessage("Maximum diff is " + dMax + ".");
		
		for(int i = 0; i < dif.Size(); i++)
		{
			iErr  = err.get(i);
			iErr -= Floats.abs(dif.get(i));
			
			sendMessage("Checking coordinate " + i + "...");
			
			if(iErr <= 0)
			{
				iNxt  = nxt.get(i);
				iNxt += Floats.sign(dif.get(i));
				iErr += Floats.max(Floats.abs(dif.Values()));
				
				sendMessage("Coordinate incremented.");
				
				nxt.set(iNxt, i);
			}
			
			err.set(iErr, i);
		}
		
		sendMessage("Result: " + nxt);
		
		cur  = nxt;
		return nxt;
	}
}