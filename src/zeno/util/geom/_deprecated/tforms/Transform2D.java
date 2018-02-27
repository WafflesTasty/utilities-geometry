package zeno.util.geom.tforms;

import zeno.util.algebra.tensors.matrices.fixed.Matrix4x4;
import zeno.util.algebra.tensors.matrices.ortho.Ortho2x2;
import zeno.util.algebra.tensors.vectors.fixed.Vector2;
import zeno.util.geom.shapes.surfaces.Rectangle;
import zeno.util.geom.tforms.types.ITransformation2D;
import zeno.util.tools.primitives.Floats;

/**
 * The {@code Transform2D} class defines the parameters
 * for a geometric transformation in 2D space.
 *
 * @since Apr 21, 2016
 * @author Zeno
 * 
 * @see ITransformation2D
 */
public final class Transform2D implements ITransformation2D
{
	private Ortho2x2 basis;
	private Vector2 origin, scale;
	
	/**
	 * Creates a new {@code Transform2D}.
	 */
	public Transform2D()
	{
		basis = new Ortho2x2();
		origin = new Vector2();
		scale = new Vector2(1);
	}


	/**
	 * Returns the viewpoint matrix of the {@code Transform2D}.
	 * 
	 * @return  a viewpoint matrix
	 * @see Matrix4x4
	 */
	public Matrix4x4 getViewpointMatrix()
	{
		Vector2 rwd = basis.right();
		Vector2 uwd = basis.up();
		
		float tx = origin.X();
		float ty = origin.Y();
		
		float rx = rwd.X();
		float ry = rwd.Y();
		
		float ux = uwd.X();
		float uy = uwd.Y();

		
		Matrix4x4 m = Matrix4x4.identity();
		
		m.set(rx, 0, 0);
		m.set(ux, 1, 0);
		
		m.set(ry, 0, 1);
		m.set(uy, 1, 1);
		
		m.set(tx, 3, 0);
		m.set(ty, 3, 1);
		
		return m;
	}
	
	/**
	 * Returns the rotation matrix of the {@code Transform2D}.
	 * 
	 * @return  a rotation matrix
	 * @see Matrix4x4
	 */
	public Matrix4x4 getRotationMatrix()
	{
		Vector2 rwd = basis.right();
		Vector2 uwd = basis.up();
		
		float rx = rwd.X();
		float ry = rwd.Y();
		
		float ux = uwd.X();
		float uy = uwd.Y();

		
		Matrix4x4 m = Matrix4x4.identity();
		
		m.set(rx, 0, 0);
		m.set(ux, 1, 0);
		
		m.set(ry, 0, 1);
		m.set(uy, 1, 1);
		
		return m;
	}
	
	/**
	 * Returns the local matrix of the {@code Transform2D}.
	 * 
	 * @return  a local matrix
	 * @see Matrix4x4
	 */
	public Matrix4x4 getLocalMatrix()
	{
		Vector2 rwd = basis.right();
		Vector2 uwd = basis.up();

		float sx = scale.X();
		float sy = scale.Y();
		
		float rx = rwd.X();
		float ry = rwd.Y();
		
		float ux = uwd.X();
		float uy = uwd.Y();

		
		Matrix4x4 m = Matrix4x4.identity();
		
		m.set(rx * sx, 0, 0);
		m.set(ux * sx, 1, 0);
		
		m.set(ry * sy, 0, 1);
		m.set(uy * sy, 1, 1);
		
		return m;
	}
	
	
	/**
	 * Returns the viewpoint inverse of the {@code Transform2D}.
	 * 
	 * @return  a viewpoint inverse
	 * @see Matrix4x4
	 */
	public Matrix4x4 getViewpointInverse()
	{
		Vector2 rwd = basis.right();
		Vector2 uwd = basis.up();
				
		float tx = -origin.dot(rwd);
		float ty = -origin.dot(uwd);
		
		float rx = rwd.X();
		float ry = rwd.Y();
		
		float ux = uwd.X();
		float uy = uwd.Y();

		
		Matrix4x4 m = Matrix4x4.identity();
		
		m.set(rx, 0, 0);
		m.set(ry, 1, 0);
		
		m.set(ux, 0, 1);
		m.set(uy, 1, 1);
		
		m.set(tx, 3, 0);
		m.set(ty, 3, 1);
		
		return m;
	}
	
	/**
	 * Returns the rotation inverse of the {@code Transform2D}.
	 * 
	 * @return  a rotation inverse
	 * @see Matrix4x4
	 */
	public Matrix4x4 getRotationInverse()
	{
		Vector2 rwd = basis.right();
		Vector2 uwd = basis.up();
				
		float rx = rwd.X();
		float ry = rwd.Y();
		
		float ux = uwd.X();
		float uy = uwd.Y();

		
		Matrix4x4 m = Matrix4x4.identity();
		
		m.set(rx, 0, 0);
		m.set(ry, 1, 0);
		
		m.set(ux, 0, 1);
		m.set(uy, 1, 1);
		
		return m;
	}
	
	/**
	 * Returns the local inverse of the {@code Transform2D}.
	 * 
	 * @return  a local inverse
	 * @see Matrix4x4
	 */
	public Matrix4x4 getLocalInverse()
	{
		Vector2 rwd = basis.right();
		Vector2 uwd = basis.up();
		
		float sx = scale.X();
		float sy = scale.Y();
		
		float rx = rwd.X();
		float ry = rwd.Y();
		
		float ux = uwd.X();
		float uy = uwd.Y();

		
		Matrix4x4 m = Matrix4x4.identity();
		
		m.set(rx / sx, 0, 0);
		m.set(ry / sy, 1, 0);
		
		m.set(ux / sx, 0, 1);
		m.set(uy / sy, 1, 1);
		
		return m;
	}
		
	
	/**
	 * Returns the origin of the {@code Transform2D}.
	 * 
	 * @return  the transform's origin
	 * @see Vector2
	 */
	public Vector2 getOrigin()
	{
		return origin;
	}
	
	/**
	 * Returns the scale of the {@code Transform2D}.
	 * 
	 * @return  the transform's scale
	 * @see Vector2
	 */
	public Vector2 getScale()
	{
		return scale;
	}
	
	
	@Override
	public void scaleTo(Vector2 vec)
	{
		scale = vec;
	}
	
	@Override
	public void rotateFor(float rad)
	{
		basis.rotateFor(rad);
	}
		
	@Override
	public void rotateTo(float rad)
	{
		basis.rotateTo(rad);
	}
	
	@Override
	public void moveTo(Vector2 vec)
	{
		origin = vec;
	}
	

	@Override
	public Matrix4x4 getInverse()
	{
		Vector2 rwd = basis.right();
		Vector2 uwd = basis.up();
				
		float tx = -origin.dot(rwd);
		float ty = -origin.dot(uwd);
		
		float sx = scale.X();
		float sy = scale.Y();
		
		float rx = rwd.X();
		float ry = rwd.Y();
		
		float ux = uwd.X();
		float uy = uwd.Y();

		
		Matrix4x4 m = Matrix4x4.identity();
		
		m.set(rx / sx, 0, 0);
		m.set(ry / sy, 1, 0);
		
		m.set(ux / sx, 0, 1);
		m.set(uy / sy, 1, 1);
		
		m.set(tx / sx, 3, 0);
		m.set(ty / sy, 3, 1);
		
		return m;
	}

	@Override
	public Matrix4x4 getMatrix()
	{
		Vector2 rwd = basis.right();
		Vector2 uwd = basis.up();
		
		float tx = origin.X();
		float ty = origin.Y();
		
		float sx = scale.X();
		float sy = scale.Y();
		
		float rx = rwd.X();
		float ry = rwd.Y();
		
		float ux = uwd.X();
		float uy = uwd.Y();

		
		Matrix4x4 m = Matrix4x4.identity();
		
		m.set(rx * sx, 0, 0);
		m.set(ux * sx, 1, 0);
		
		m.set(ry * sy, 0, 1);
		m.set(uy * sy, 1, 1);
		
		m.set(tx, 3, 0);
		m.set(ty, 3, 1);
		
		return m;
	}
	
	@Override
	public Vector2 getRight()
	{
		return basis.right();
	}
	
	@Override
	public Vector2 getUp()
	{
		return basis.up();
	}
	
	
	@Override
	public boolean equals(Object o)
	{
		if(o instanceof Transform2D)
		{
			Transform2D tform = (Transform2D) o;
			return basis.equals(tform.basis)
				&& origin.equals(tform.origin)
				&& scale.equals(tform.scale);
		}
		
		return false;
	}
	
	@Override
	public int hashCode()
	{
		final int prime = 31;
		
		int result = 1;
		result = prime * result + basis.hashCode();
		result = prime * result + origin.hashCode();
		result = prime * result + scale.hashCode();
		return result;
	}
	
		
	@Override
	public float getHeight()
	{
		return scale.Y();
	}
	
	@Override
	public float getWidth()
	{
		return scale.X();
	}
	
	@Override
	public float getX()
	{
		return origin.X();
	}
	
	@Override
	public float getY()
	{
		return origin.Y();
	}

	
	@Override
	public float XMin()
	{
		float ox = origin.X();
		float rx = basis.right().X();
		float ux = basis.up().X();
		float sx = scale.X();
		
		rx = rx * sx / 2;
		ux = ux * sx / 2;
		
		return ox + Floats.min(-rx, -ux, rx, ux);
	}
	
	@Override
	public float XMax()
	{
		float ox = origin.X();
		float rx = basis.right().X();
		float ux = basis.up().X();
		float sx = scale.X();
		
		rx = rx * sx / 2;
		ux = ux * sx / 2;
		
		return ox + Floats.max(-rx, -ux, rx, ux);
	}
	
	@Override
	public float YMin()
	{
		float oy = origin.Y();
		float ry = basis.right().Y();
		float uy = basis.up().Y();
		float sy = scale.Y();
		
		ry = ry * sy / 2;
		uy = uy * sy / 2;
		
		return oy + Floats.min(-ry, -uy, ry, uy);
	}
	
	@Override
	public float YMax()
	{
		float oy = origin.Y();
		float ry = basis.right().Y();
		float uy = basis.up().Y();
		float sy = scale.Y();
		
		ry = ry * sy / 2;
		uy = uy * sy / 2;
		
		return oy + Floats.max(-ry, -uy, ry, uy);
	}


	@Override
	public Rectangle Bounds()
	{
		return new Rectangle(origin, scale);
	}
	
	@Override
	public Vector2 Center()
	{
		return origin;
	}
	
	@Override
	public Vector2 Size()
	{
		return scale;
	}
}