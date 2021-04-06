package zeno.util.geom.collidables.geometry.generic;

import zeno.util.geom.collidables.affine.HSpace;

/**
 * The {@code IPolytope} interface defines collision operations for polytope geometry.
 *
 * @author Waffles
 * @since 07 Apr 2021
 * @version 1.0
 * 
 * 
 * @see IConvex
 */
public interface IPolytope extends IConvex
{
	/**
	 * Returns the faces of the {@code IPolytope}.
	 * 
	 * @return  a set of faces
	 * 
	 * 
	 * @see Iterable
	 * @see HSpace
	 */
	public abstract Iterable<HSpace> Faces();
}