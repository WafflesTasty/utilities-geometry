package zeno.util.geom.collidables.geometry.bounds;

import zeno.util.algebra.linear.matrix.Matrices;
import zeno.util.algebra.linear.matrix.Matrix;
import zeno.util.algebra.linear.matrix.types.banded.Diagonal;
import zeno.util.algebra.linear.vector.Vector;
import zeno.util.geom.AffineMap;
import zeno.util.geom.collidables.affine.Point;
import zeno.util.geom.collidables.bounds.Bounds;
import zeno.util.geom.collidables.geometry.generic.IEllipsoid;
import zeno.util.geom.collidables.geometry.higher.NEllipsoid;
import zeno.util.tools.Floats;

/**
 * The {@code BNDEllipsoid} class defines the bounds of a transformed {@link NEllipsoid}.
 *
 * @author Zeno
 * @since Sep 11, 2019
 * @version 1.0
 * 
 *
 * @see Bounds
 */
public class BNDEllipsoid implements Bounds
{
	private AffineMap map;
	private IEllipsoid ellipse;
	
	/**
	 * Creates a new {@code BNDEllipsoid}.
	 * 
	 * @param e  a target ellipsoid
	 * @param m  an affine map
	 * 
	 * 
	 * @see IEllipsoid
	 * @see AffineMap
	 */
	public BNDEllipsoid(IEllipsoid e, AffineMap m)
	{
		ellipse = e;
		map = m;
	}
	
	
	@Override
	public float Radius()
	{
		Matrix e = Matrices.diagonal(ellipse.Size().times(0.5f));
		e.setOperator(Diagonal.Type());
		
		int dim = Dimension();
		Matrix p = map.Matrix(dim);
		p = Matrices.resize(p, dim, dim);
		p = e.times(p);
		
		float rad = 0f;
		for(int i = 0; i < dim; i++)
		{
			rad = Floats.max(rad, p.Row(i).norm());
		}
		
		return rad;
	}
	
	@Override
	public Vector Center()
	{
		Point p = new Point(ellipse.Center(), 1f);
		return ((Point) map.map(p)).asVector();
	}
	
	@Override
	public Vector Size()
	{
		Matrix e = Matrices.diagonal(ellipse.Size().times(0.5f));
		e.setOperator(Diagonal.Type());
		e = e.times(e);
		
		int dim = Dimension();
		Matrix p = map.Matrix(dim);
		p = p.times(p.transpose());
		p = Matrices.resize(p, dim, dim);
		p = e.times(p).times(e);
		
		Vector s = new Vector(dim);
		for(int i = 0; i < dim; i++)
		{
			s.set(2 * Floats.sqrt(p.get(i, i)), i);
		}
		
		return s;
	}
}