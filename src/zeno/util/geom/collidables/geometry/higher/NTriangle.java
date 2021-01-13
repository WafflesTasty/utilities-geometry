package zeno.util.geom.collidables.geometry.higher;

import zeno.util.algebra.linear.vector.Vector;
import zeno.util.geom.collidables.geometry.generic.ITriangle;

/**
 * 
 * The {@code NTriangle} class defines an n-dimensional triangle.
 *
 * @author Zeno
 * @since 13 Jan 2021
 * @version 1.0
 * 
 * 
 * @see ITriangle
 */
public class NTriangle implements ITriangle
{
	private Vector p1, p2, p3;
	
	/**
	 * Creates a new {@code NTriangle}.
	 * 
	 * @param p1  a first point
	 * @param p2  a second point
	 * @param p3  a third point
	 * 
	 * 
	 * @see Vector
	 */
	public NTriangle(Vector p1, Vector p2, Vector p3)
	{
		this.p1 = p1;
		this.p2 = p2;
		this.p3 = p3;
	}

	
	@Override
	public int hashCode()
	{
		int code = 1;
		
		code = code * 17 + p1.hashCode();
		code = code * 43 + p2.hashCode();
		code = code * 73 + p3.hashCode();
		
		return code;
	}
	
	@Override
	public boolean equals(Object o)
	{
		if(o instanceof NTriangle)
		{
			NTriangle oTriangle = (NTriangle) o;
			if(p1.equals(oTriangle.p1))
			{
				return (p2.equals(oTriangle.p2)
					 && p3.equals(oTriangle.p3))
					 ||(p2.equals(oTriangle.p3)
					 && p3.equals(oTriangle.p2));
			}
			
			if(p1.equals(oTriangle.p2))
			{
				return (p2.equals(oTriangle.p1)
					 && p3.equals(oTriangle.p3))
					 ||(p2.equals(oTriangle.p3)
					 && p3.equals(oTriangle.p1));
			}
			
			if(p1.equals(oTriangle.p3))
			{
				return (p2.equals(oTriangle.p2)
					 && p3.equals(oTriangle.p1))
					 ||(p2.equals(oTriangle.p1)
					 && p3.equals(oTriangle.p2));
			}
		}
		
		return false;
	}
	

	@Override
	public Vector P1()
	{
		return p1;
	}

	@Override
	public Vector P2()
	{
		return p2;
	}

	@Override
	public Vector P3()
	{
		return p3;
	}
}