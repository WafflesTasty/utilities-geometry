package zeno.util.geom.algorithms;

import java.util.ArrayList;
import java.util.List;

import zeno.util.algebra.tensors.Tensor;
import zeno.util.algebra.tensors.vectors.Vector;
import zeno.util.geom._newstuff.Hypercube;
import zeno.util.tools.Messenger;

/**
 * The {@code LineClipper} class defines an algorithm
 * that clips n-dimensional lines to a boundary defined
 * by an n-dimensional hypercube.
 * 
 * @since Feb 16, 2015
 * @author Zeno
 * 
 * @see <a href="http://en.wikipedia.org/wiki/Line_clipping">Wikipedia - Line clipping</a>
 * @see <a href="http://www.sersc.org/journals/IJCG/vol3_no2/3.pdf">B. Ray - Clipping Algorithm</a>
 * @see Messenger
 */
public class LineClipper extends Messenger
{
	private Hypercube bounds;
	private List<Vector> result;
	
	/**
	 * Clips a list of points to the clipper's boundary.
	 * The result is a list of points that follow the
	 * trajectory as the original list bounded by
	 * the defined hypercube boundary. 
	 * 
	 * @param points  a list of points
	 * @return  a clipped point list
	 * @see Vector
	 * @see List
	 */
	public List<Vector> clip(Vector... points)
	{
		Vector q = null;
		result = new ArrayList<>();
		for(int i = 0; i < points.length; i++)
		{
			Vector p = points[i];
			
			// If the point is inside the boundary...
			if(bounds.contains(p))
			{
				// Don't clip it.
				addResult(p);
				continue;
			}
			
			// Unless this is the first point...
			if(i != 0)
			{
				// Fetch the previous point.
				q = points[i - 1];
				
				// Project p along previous line.
				q = clip(p, q);
				if(q != null)
				{
					addResult(q);
				}
			}
			
			// Unless this is the last point...
			if(i != points.length - 1)
			{
				// Fetch the next point.
				q = points[i + 1];
				
				// Project p along next line.
				q = clip(p, q);
				if(q != null)
				{
					addResult(q);
				}
			}
		}

		return result;
	}

	/**
	 * Changes the boundary of the {@code LineClipper}.
	 * 
	 * @param cube  a new boundary
	 * @see Hypercube
	 */
	public void setBounds(Hypercube cube)
	{
		bounds = cube;
	}
	
	
	private boolean crosses(Vector p, Vector q, float val, int i)
	{
		float val1, val2;
		for(int j = 0; j < bounds.dimension(); j++)
		{
			if(i == j) continue;
			
			val1 = (bounds.min(j) - p.get(j)) * (q.get(i) - p.get(i)) - (val - p.get(i)) * (q.get(j) - p.get(j));
			val2 = (bounds.max(j) - p.get(j)) * (q.get(i) - p.get(i)) - (val - p.get(i)) * (q.get(j) - p.get(j));
			
			if(val1 * val2 > 0) return false;
		}
		
		return true;
	}
	
	private Vector project(Vector p, Vector q, float val, int i)
	{
		Tensor v = Vector.create(bounds.dimension());
		for(int j = 0; j < bounds.dimension(); j++)
		{
			double coord = val;
			if(i != j)
			{
				coord  = q.get(j) - p.get(j);				
				coord /= q.get(i) - p.get(i);
				coord *=      val - p.get(i);
				coord +=            p.get(j);
			}
			
			v.set((float) coord, j);
		}
		
		return (Vector) v;
	}
	
	private Vector clip(Vector p, Vector q)
	{
		float min, max;
		for(int i = 0; i < bounds.dimension(); i++)
		{			
			min = bounds.min(i);
			max = bounds.max(i);

			if(p.get(i) < min)
			{
				if(q.get(i) <= min)
				{
					return null;
				}
				
				if(crosses(p, q, min, i))
				{
					return project(p, q, min, i);
				}
			}
			
			if(p.get(i) > max)
			{
				if(q.get(i) >= max)
				{
					return null;
				}
				
				if(crosses(p, q, max, i))
				{
					return project(p, q, max, i);
				}
			}
		}
		
		return null;
	}
	
	private void addResult(Vector v)
	{
		if(v != null)
		{
			if(result.isEmpty())
			{
				result.add(v);
				return;
			}
			
			int last = result.size() - 1;
			if(!v.equals(result.get(last)))
			{
				result.add(v);
				return;
			}
		}
	}
}