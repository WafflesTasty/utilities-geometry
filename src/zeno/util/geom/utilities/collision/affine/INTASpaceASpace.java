package zeno.util.geom.utilities.collision.affine;

import zeno.util.algebra.linear.matrix.Matrices;
import zeno.util.algebra.linear.matrix.Matrix;
import zeno.util.algebra.linear.vector.VSpace;
import zeno.util.algebra.linear.vector.VSpaces;
import zeno.util.algebra.linear.vector.Vector;
import zeno.util.algebra.linear.vector.Vectors;
import zeno.util.geom.ICollidable;
import zeno.util.geom.collidables.Affine;
import zeno.util.geom.collidables.affine.ASpaces;
import zeno.util.geom.collidables.affine.Point;
import zeno.util.geom.utilities.collision.Intersection;

/**
 * The {@code INTASpaceASpace} defines intersection between {@code Affine.Space} objects.
 *
 * @author Zeno
 * @since Jul 18, 2019
 * @version 1.0
 * 
 * 
 * @see Intersection
 */
public class INTASpaceASpace implements Intersection
{	
	@Override
	public Affine.Space intersect(ICollidable c1, ICollidable c2)
	{
		Affine.Space s1 = (Affine.Space) c1;
		Affine.Space s2 = (Affine.Space) c2;
		
		
		VSpace dir = s1.Direction().add(s2.Direction());
		Vector pq = s1.Origin().minus(s2.Origin());
		int size = s1.Origin().Size();			
		
		// If p-q not in V+W...
		Vector x = dir.coordinates(pq);
		if(x == null)
		{
			// The intersection is empty.
			return ASpaces.trivial(size);
		}
		
		// Otherwise, a common point is found.
		x = Vectors.resize(x, size);
		x = s1.Direction().Span().times(x);
		x = s1.Origin().VMatrix().plus(x);
		
		// Calculate the direction intersection.
		Matrix m = dir.RowComplement();
		m = Matrices.resize(m, s1.Dimension(), m.Columns());
		m = dir.Span().times(m);
		
		// Create the new affine subspace.
		Point o = new Point(x);
		VSpace v = VSpaces.create(m);
		return ASpaces.span(o, v);
	}

	@Override
	public boolean intersects(ICollidable c1, ICollidable c2)
	{
		Affine.Space s1 = (Affine.Space) c1;
		Affine.Space s2 = (Affine.Space) c2;
		
		
		VSpace dir = s1.Direction().add(s2.Direction());		
		Vector pq = s1.Origin().minus(s2.Origin());
		return dir.contains(pq); 
	}

	
	@Override
	public ICollidable SRCType()
	{
		return Affine.Space.TYPE;
	}
	
	@Override
	public ICollidable TGTType()
	{
		return Affine.Space.TYPE;
	}
}