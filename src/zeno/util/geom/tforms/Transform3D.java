package zeno.util.geom.tforms;

import zeno.util.algebra.tensors.matrices.fixed.Matrix4x4;
import zeno.util.algebra.tensors.matrices.ortho.Ortho3x3;
import zeno.util.algebra.tensors.vectors.fixed.Vector3;
import zeno.util.geom.interfaces.ITransformation3D;
import zeno.util.tools.primitives.Floats;

/**
 * The {@code Transform3D} class defines the parameters
 * for a transformation in 3D space.
 *
 * @since Apr 21, 2016
 * @author Zeno
 * 
 * @see ITransformation3D
 */
public final class Transform3D implements ITransformation3D
{	
	private Ortho3x3 basis;
	private Vector3 origin, scale;
	
	/**
	 * Creates a new {@code Transform3D}.
	 */
	public Transform3D()
	{
		basis = new Ortho3x3();
		origin = new Vector3();
		scale = new Vector3(1);
	}
		
	
	/**
	 * Returns the viewpoint matrix of the {@code Transform3D}.
	 * 
	 * @return  a viewpoint matrix
	 * @see Matrix4x4
	 */
	public Matrix4x4 getViewpointMatrix()
	{
		Vector3 fwd = basis.forward();
		Vector3 rwd = basis.right();
		Vector3 uwd = basis.up();
		
		float tx = origin.X();
		float ty = origin.Y();
		float tz = origin.Z();
		
		float fx = fwd.X();
		float fy = fwd.Y();
		float fz = fwd.Z();
		
		float rx = rwd.X();
		float ry = rwd.Y();
		float rz = rwd.Z();
		
		float ux = uwd.X();
		float uy = uwd.Y();
		float uz = uwd.Z();

		
		Matrix4x4 m = Matrix4x4.identity();
		
		m.set(rx, 0, 0);
		m.set(ux, 1, 0);
		m.set(fx, 2, 0);
		
		m.set(ry, 0, 1);
		m.set(uy, 1, 1);
		m.set(fy, 2, 1);
		
		m.set(rz, 0, 2);
		m.set(uz, 1, 2);
		m.set(fz, 2, 2);
		
		m.set(tx, 3, 0);
		m.set(ty, 3, 1);
		m.set(tz, 3, 2);
		
		return m;
	}
	
	/**
	 * Returns the rotation matrix of the {@code Transform3D}.
	 * 
	 * @return  a rotation matrix
	 * @see Matrix4x4
	 */
	public Matrix4x4 getRotationMatrix()
	{
		Vector3 fwd = basis.forward();
		Vector3 rwd = basis.right();
		Vector3 uwd = basis.up();
		
		float fx = fwd.X();
		float fy = fwd.Y();
		float fz = fwd.Z();
		
		float rx = rwd.X();
		float ry = rwd.Y();
		float rz = rwd.Z();
		
		float ux = uwd.X();
		float uy = uwd.Y();
		float uz = uwd.Z();

		
		Matrix4x4 m = Matrix4x4.identity();
		
		m.set(rx, 0, 0);
		m.set(ux, 1, 0);
		m.set(fx, 2, 0);
		
		m.set(ry, 0, 1);
		m.set(uy, 1, 1);
		m.set(fy, 2, 1);
		
		m.set(rz, 0, 2);
		m.set(uz, 1, 2);
		m.set(fz, 2, 2);
				
		return m;
	}
	
	/**
	 * Returns the local matrix of the {@code Transform3D}.
	 * 
	 * @return  a local matrix
	 * @see Matrix4x4
	 */
	public Matrix4x4 getLocalMatrix()
	{
		Vector3 fwd = basis.forward();
		Vector3 rwd = basis.right();
		Vector3 uwd = basis.up();
		
		float sx = scale.X();
		float sy = scale.Y();
		float sz = scale.Z();
		
		float fx = fwd.X();
		float fy = fwd.Y();
		float fz = fwd.Z();
		
		float rx = rwd.X();
		float ry = rwd.Y();
		float rz = rwd.Z();
		
		float ux = uwd.X();
		float uy = uwd.Y();
		float uz = uwd.Z();

		
		Matrix4x4 m = Matrix4x4.identity();
		
		m.set(rx * sx, 0, 0);
		m.set(ux * sy, 1, 0);
		m.set(fx * sz, 2, 0);
		
		m.set(ry * sx, 0, 1);
		m.set(uy * sy, 1, 1);
		m.set(fy * sz, 2, 1);
		
		m.set(rz * sx, 0, 2);
		m.set(uz * sy, 1, 2);
		m.set(fz * sz, 2, 2);
		
		return m;
	}
	
	
	/**
	 * Returns the viewpoint inverse of the {@code Transform3D}.
	 * 
	 * @return  a viewpoint inverse
	 * @see Matrix4x4
	 */
	public Matrix4x4 getViewpointInverse()
	{
		Vector3 fwd = basis.forward();
		Vector3 rwd = basis.right();
		Vector3 uwd = basis.up();
				
		float tx = -origin.dot(rwd);
		float ty = -origin.dot(uwd);
		float tz = -origin.dot(fwd);
		
		float fx = fwd.X();
		float fy = fwd.Y();
		float fz = fwd.Z();
		
		float rx = rwd.X();
		float ry = rwd.Y();
		float rz = rwd.Z();
		
		float ux = uwd.X();
		float uy = uwd.Y();
		float uz = uwd.Z();

		
		Matrix4x4 m = Matrix4x4.identity();
		
		m.set(rx, 0, 0);
		m.set(ry, 1, 0);
		m.set(rz, 2, 0);
		
		m.set(ux, 0, 1);
		m.set(uy, 1, 1);
		m.set(uz, 2, 1);
		
		m.set(fx, 0, 2);
		m.set(fy, 1, 2);
		m.set(fz, 2, 2);
		
		m.set(tx, 3, 0);
		m.set(ty, 3, 1);
		m.set(tz, 3, 2);
		
		return m;
	}
	
	/**
	 * Returns the rotation inverse of the {@code Transform3D}.
	 * 
	 * @return  a rotation inverse
	 * @see Matrix4x4
	 */
	public Matrix4x4 getRotationInverse()
	{
		Vector3 fwd = basis.forward();
		Vector3 rwd = basis.right();
		Vector3 uwd = basis.up();
		
		float fx = fwd.X();
		float fy = fwd.Y();
		float fz = fwd.Z();
		
		float rx = rwd.X();
		float ry = rwd.Y();
		float rz = rwd.Z();
		
		float ux = uwd.X();
		float uy = uwd.Y();
		float uz = uwd.Z();

		
		Matrix4x4 m = Matrix4x4.identity();
		
		m.set(rx, 0, 0);
		m.set(ry, 1, 0);
		m.set(rz, 2, 0);
		
		m.set(ux, 0, 1);
		m.set(uy, 1, 1);
		m.set(uz, 2, 1);
		
		m.set(fx, 0, 2);
		m.set(fy, 1, 2);
		m.set(fz, 2, 2);
		
		return m;
	}
	
	/**
	 * Returns the local inverse of the {@code Transform3D}.
	 * 
	 * @return  a local inverse
	 * @see Matrix4x4
	 */
	public Matrix4x4 getLocalInverse()
	{
		Vector3 fwd = basis.forward();
		Vector3 rwd = basis.right();
		Vector3 uwd = basis.up();

		float sx = scale.X();
		float sy = scale.Y();
		float sz = scale.Z();
		
		float fx = fwd.X();
		float fy = fwd.Y();
		float fz = fwd.Z();
		
		float rx = rwd.X();
		float ry = rwd.Y();
		float rz = rwd.Z();
		
		float ux = uwd.X();
		float uy = uwd.Y();
		float uz = uwd.Z();

		
		Matrix4x4 m = Matrix4x4.identity();
		
		m.set(rx / sx, 0, 0);
		m.set(ry / sx, 1, 0);
		m.set(rz / sx, 2, 0);
		
		m.set(ux / sy, 0, 1);
		m.set(uy / sy, 1, 1);
		m.set(uz / sy, 2, 1);
		
		m.set(fx / sz, 0, 2);
		m.set(fy / sz, 1, 2);
		m.set(fz / sz, 2, 2);
		
		return m;
	}
	
	
	/**
	 * Returns the origin of the {@code Transform3D}.
	 * 
	 * @return  the transform's origin
	 * @see Vector3
	 */
	public Vector3 getOrigin()
	{
		return origin;
	}
	
	/**
	 * Returns the scale of the {@code Transform3D}.
	 * 
	 * @return  the transform's scale
	 * @see Vector3
	 */
	public Vector3 getScale()
	{
		return scale;
	}
	
		
	@Override
	public void rotateTo(Vector3 rwd, Vector3 uwd)
	{
		basis.rotateTo(rwd, uwd);
	}
	
	@Override
	public void rotateFor(Vector3 v, float rad)
	{
		if(rad != 0)
		{
			basis.rotateFor(v, rad);
		}
	}
			
	
	@Override
	public void scaleTo(Vector3 vec)
	{
		scale = vec;
	}
	
	@Override
	public void moveTo(Vector3 vec)
	{
		origin = vec;
	}
	
	
	@Override
	public void pitchFor(float rad)
	{
		if(rad != 0)
		{
			basis.pitchFor(rad);
		}
	}
	
	@Override
	public void rollFor(float rad)
	{
		if(rad != 0)
		{
			basis.rollFor(rad);
		}
	}
	
	@Override
	public void yawFor(float rad)
	{
		if(rad != 0)
		{
			basis.yawFor(rad);
		}
	}
	
			
	@Override
	public Matrix4x4 getInverse()
	{
		Vector3 fwd = basis.forward();
		Vector3 rwd = basis.right();
		Vector3 uwd = basis.up();
				
		float tx = -origin.dot(rwd);
		float ty = -origin.dot(uwd);
		float tz = -origin.dot(fwd);
		
		float sx = scale.X();
		float sy = scale.Y();
		float sz = scale.Z();
		
		float fx = fwd.X();
		float fy = fwd.Y();
		float fz = fwd.Z();
		
		float rx = rwd.X();
		float ry = rwd.Y();
		float rz = rwd.Z();
		
		float ux = uwd.X();
		float uy = uwd.Y();
		float uz = uwd.Z();

		
		Matrix4x4 m = Matrix4x4.identity();
		
		m.set(rx / sx, 0, 0);
		m.set(ry / sx, 1, 0);
		m.set(rz / sx, 2, 0);
		
		m.set(ux / sy, 0, 1);
		m.set(uy / sy, 1, 1);
		m.set(uz / sy, 2, 1);
		
		m.set(fx / sz, 0, 2);
		m.set(fy / sz, 1, 2);
		m.set(fz / sz, 2, 2);
		
		m.set(tx / sx, 3, 0);
		m.set(ty / sy, 3, 1);
		m.set(tz / sz, 3, 2);
		
		return m;
	}

	@Override
	public Matrix4x4 getMatrix()
	{
		Vector3 fwd = basis.forward();
		Vector3 rwd = basis.right();
		Vector3 uwd = basis.up();
		
		float tx = origin.X();
		float ty = origin.Y();
		float tz = origin.Z();
		
		float sx = scale.X();
		float sy = scale.Y();
		float sz = scale.Z();
		
		float fx = fwd.X();
		float fy = fwd.Y();
		float fz = fwd.Z();
		
		float rx = rwd.X();
		float ry = rwd.Y();
		float rz = rwd.Z();
		
		float ux = uwd.X();
		float uy = uwd.Y();
		float uz = uwd.Z();

		
		Matrix4x4 m = Matrix4x4.identity();
		
		m.set(rx * sx, 0, 0);
		m.set(ux * sy, 1, 0);
		m.set(fx * sz, 2, 0);
		
		m.set(ry * sx, 0, 1);
		m.set(uy * sy, 1, 1);
		m.set(fy * sz, 2, 1);
		
		m.set(rz * sx, 0, 2);
		m.set(uz * sy, 1, 2);
		m.set(fz * sz, 2, 2);
		
		m.set(tx, 3, 0);
		m.set(ty, 3, 1);
		m.set(tz, 3, 2);
		
		return m;
	}
	
	@Override
	public Vector3 getForward()
	{
		return basis.forward();
	}
	
	@Override
	public Vector3 getRight()
	{
		return basis.right();
	}
	
	@Override
	public Vector3 getUp()
	{
		return basis.up();
	}

	
	@Override
	public boolean equals(Object o)
	{
		if(o instanceof Transform3D)
		{
			Transform3D tform = (Transform3D) o;
			return basis.equals(tform.basis)
				&& origin.equals(tform.origin)
				&& scale.equals(tform.scale);
		}
		
		return false;
	}
	
	@Override
	public int hashCode()
	{
		final int prime = 37;
		
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
	public float getDepth()
	{
		return scale.Z();
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
	public float getZ()
	{
		return origin.Z();
	}
	
	
	@Override
	public float XMin()
	{
		float ox = origin.X();
		float rx = basis.right().X();
		float fx = basis.forward().X();
		float ux = basis.up().X();
		float sx = scale.X();
		
		rx = rx * sx / 2;
		fx = fx * sx / 2;
		ux = ux * sx / 2;
		
		return ox + Floats.min(-rx, -fx, -ux, rx, fx, ux);
	}

	@Override
	public float XMax()
	{
		float ox = origin.X();
		float rx = basis.right().X();
		float fx = basis.forward().X();
		float ux = basis.up().X();
		float sx = scale.X();
		
		rx = rx * sx / 2;
		fx = fx * sx / 2;
		ux = ux * sx / 2;
		
		return ox + Floats.max(-rx, -fx, -ux, rx, fx, ux);
	}

	@Override
	public float YMin()
	{
		float oy = origin.Y();
		float ry = basis.right().Y();
		float fy = basis.forward().Y();
		float uy = basis.up().Y();
		float sy = scale.Y();
		
		ry = ry * sy / 2;
		fy = fy * sy / 2;
		uy = uy * sy / 2;
		
		return oy + Floats.min(-ry, -fy, -uy, ry, fy, uy);
	}

	@Override
	public float YMax()
	{
		float oy = origin.Y();
		float ry = basis.right().Y();
		float fy = basis.forward().Y();
		float uy = basis.up().Y();
		float sy = scale.Y();
		
		ry = ry * sy / 2;
		fy = fy * sy / 2;
		uy = uy * sy / 2;
		
		return oy + Floats.max(-ry, -fy, -uy, ry, fy, uy);
	}

	@Override
	public float ZMin()
	{
		float oz = origin.Z();
		float rz = basis.right().Z();
		float fz = basis.forward().Z();
		float uz = basis.up().Z();
		float sz = scale.Z();
		
		rz = rz * sz / 2;
		fz = fz * sz / 2;
		uz = uz * sz / 2;
		
		return oz + Floats.min(-rz, -fz, -uz, rz, fz, uz);
	}

	@Override
	public float ZMax()
	{
		float oz = origin.Z();
		float rz = basis.right().Z();
		float fz = basis.forward().Z();
		float uz = basis.up().Z();
		float sz = scale.Z();
		
		rz = rz * sz / 2;
		fz = fz * sz / 2;
		uz = uz * sz / 2;
		
		return oz + Floats.max(-rz, -fz, -uz, rz, fz, uz);
	}
}