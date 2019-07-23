package zeno.util.geom.collidables.collisions.affine;

import zeno.util.geom.collidables.ICollision;
import zeno.util.algebra.linear.matrix.Matrices;
import zeno.util.algebra.linear.matrix.Matrix;
import zeno.util.algebra.linear.vector.VSpace;
import zeno.util.algebra.linear.vector.VSpaces;
import zeno.util.algebra.linear.vector.Vector;
import zeno.util.algebra.linear.vector.Vectors;
import zeno.util.geom.ICollidable;
import zeno.util.geom.collidables.Affine;
import zeno.util.geom.collidables.Affine.Space;
import zeno.util.geom.collidables.affine.ASpace;
import zeno.util.geom.collidables.affine.ASpaces;
import zeno.util.geom.collidables.affine.Point;

/**
 * The {@code CLSASpace} class defines collision for an {@code Affine} {@link Space}.
 *
 * @author Zeno
 * @since Jul 23, 2019
 * @version 1.0
 * 
 * 
 * @see ICollision
 */
public class CLSASpace implements ICollision
{
	private Affine.Space s;
	
	/**
	 * Creates a new {@code CLSASpace}.
	 * 
	 * @param s  a source space
	 * 
	 * 
	 * @see ASpace
	 */
	public CLSASpace(Affine.Space s)
	{
		this.s = s;
	}

	
	@Override
	public ICollidable intersect(ICollidable c)
	{
		if(c instanceof Affine.Space)
		{
			Affine.Space t = (Affine.Space) c;
			
			
			VSpace dir = s.Direction().add(t.Direction());
			Vector pq = s.Origin().minus(t.Origin());
			int size = s.Origin().Size();			
			
			// If p-q not in V+W...
			Vector x = dir.coordinates(pq);
			if(x == null)
			{
				// The intersection is empty.
				return ASpaces.trivial(size);
			}
			
			// Otherwise, a common point is found.
			x = Vectors.resize(x, size);
			x = s.Direction().Span().times(x);
			x = s.Origin().VMatrix().plus(x);
			
			// Calculate the direction intersection.
			Matrix m = dir.RowComplement();
			m = Matrices.resize(m, s.Dimension(), m.Columns());
			m = dir.Span().times(m);
			
			// Create the new affine subspace.
			Point o = new Point(x);
			VSpace v = VSpaces.create(m);
			return ASpaces.span(o, v);
		}
		
		return null;
	}
	
	@Override
	public Boolean equals(ICollidable c, int ulps)
	{
		if(c instanceof Affine.Space)
		{
			Affine.Space t = (Affine.Space) c;
			
			
			Point p = s.Origin();
			VSpace dir1 = s.Direction();
			VSpace dir2 = t.Direction();
			return dir1.equals(dir2, ulps)
				&& t.contains(p);
		}
		
		return null;
	}
	
	@Override
	public Boolean intersects(ICollidable c)
	{
		if(c instanceof Affine.Space)
		{
			Affine.Space t = (Affine.Space) c;
			
			
			VSpace dir = s.Direction().add(t.Direction());		
			Vector pq = s.Origin().minus(t.Origin());
			return dir.contains(pq); 
		}
		
		return null;
	}

	@Override
	public Boolean contains(ICollidable c)
	{		
		if(c instanceof Affine.Space)
		{
			Affine.Space t = (Affine.Space) c;
			
			
			Vector pq = s.Origin().minus(t.Origin());
			VSpace dir = s.Direction().add(t.Direction());		
			return dir.Dimension() == t.Dimension()
				&& dir.contains(pq);
		}
		
		return null;
	}
}