package waffles.utils.geom.bounds.axial.spheroid;

import waffles.utils.algebra.elements.linear.matrix.Matrices;
import waffles.utils.algebra.elements.linear.matrix.Matrix;
import waffles.utils.algebra.elements.linear.matrix.types.banded.Diagonal;
import waffles.utils.algebra.elements.linear.vector.Vector;
import waffles.utils.algebra.elements.linear.vector.Vectors;
import waffles.utils.geom.bounds.Bounds;
import waffles.utils.geom.collidable.axial.spheroid.ISpheroid;
import waffles.utils.geom.collidable.fixed.Point;
import waffles.utils.geom.maps.GlobalMap;
import waffles.utils.tools.primitives.Floats;

/**
 * A {@code BNDSpheroid} defines {@code Bounds} for a transformed {@code ISpheroid}.
 *
 * @author Waffles
 * @since Sep 11, 2019
 * @version 1.0
 * 
 *
 * @see Bounds
 */
public class BNDSpheroid implements Bounds
{
	private ISpheroid src;
	private GlobalMap map;
	
	/**
	 * Creates a new {@code BNDSpheroid}.
	 * 
	 * @param e  a spheroid
	 * @param m  a global map
	 * 
	 * 
	 * @see GlobalMap
	 * @see ISpheroid
	 */
	public BNDSpheroid(ISpheroid e, GlobalMap m)
	{
		src = e;
		map = m;
	}
	
	
	@Override
	public float Radius()
	{
		Vector d = src.Size().times(0.5f);
		Matrix e = Matrices.diagonal(d);
		e.setOperator(Diagonal.Type());
		
		
		int dim = Dimension();
		Matrix a = map.Matrix(dim+1);
		a = Matrices.resize(a, dim, dim);

	
		float rMax = 0f;
		for(int i = 0; i < dim; i++)
		{
			float si = d.get(i);
			Vector ai = a.Column(i).times(si);
			rMax = Floats.max(rMax, ai.normSqr());
		}
		
		return Floats.sqrt(rMax);
	}
	
	@Override
	public Vector Center()
	{
		Vector o = src.Origin();
		Point p = new Point(o, 1f);
		p = (Point) map.map(p);
		return p.Generator();
	}
	
	@Override
	public Vector Size()
	{
		Vector d = src.Size().times(0.5f);
		Matrix e = Matrices.diagonal(d);
		e.setOperator(Diagonal.Type());
		
		
		int dim = Dimension();
		Matrix a = map.Matrix(dim+1);
		a = Matrices.resize(a, dim, dim);
		a = a.times(a.transpose());
		a = e.times(a).times(e);
		
		
		Vector s = Vectors.create(dim);
		for(int i = 0; i < dim; i++)
		{
			float val = Floats.sqrt(a.get(i, i));
			s.set(2 * val, i);
		}

		return s;
	}
}