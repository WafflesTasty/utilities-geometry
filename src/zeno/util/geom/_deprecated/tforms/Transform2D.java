package zeno.util.geom._deprecated.tforms;

import zeno.util.algebra.linear.matrix.fixed.Matrix4x4;
import zeno.util.algebra.linear.vector.fixed.Vector2;
import zeno.util.geom._deprecated.Ortho2x2;
import zeno.util.geom._deprecated.tforms.types.ITransformation2D;
import zeno.util.tools.patterns.properties.Copyable;

public final class Transform2D implements ITransformation2D, Copyable<Transform2D>
{
	private Ortho2x2 basis;
	private Vector2 origin, scale;
	private float zDepth;
	
	public Transform2D()
	{
		basis = new Ortho2x2();
		scale = new Vector2(1, 1);
		origin = new Vector2();
	}

	
	public Matrix4x4 PositionMatrix()
	{
		return Matrix4x4.translate3D(origin.X(), origin.Y(), 0);
	}
	
	public Matrix4x4 RotationMatrix()
	{
		return basis.rotate3D();
	}
	
	public Matrix4x4 ScaleMatrix()
	{
		return Matrix4x4.scale3D(scale.X(), scale.Y(), 0);
	}

	public void setZDepth(float depth)
	{
		zDepth = depth;
	}
	
	
	@Override
	public Matrix4x4 Inverse()
	{
		Vector2 rwd = basis.right();
		Vector2 uwd = basis.up();
				
		float tx = -origin.dot(rwd);
		float ty = -origin.dot(uwd);
		float tz = zDepth;
		
		float sx = scale.X();
		float sy = scale.Y();
		
		float rx = rwd.X();
		float ry = rwd.Y();
		
		float ux = uwd.X();
		float uy = uwd.Y();

		
		Matrix4x4 m = Matrix4x4.identity();
		
		m.set(rx / sx, 0, 0);
		m.set(ry / sy, 0, 1);
		
		m.set(ux / sx, 1, 0);
		m.set(uy / sy, 1, 1);
		
		m.set(tx / sx, 0, 3);
		m.set(ty / sy, 1, 3);
		m.set(tz / 1f, 2, 3);
		
		return m;
	}

	@Override
	public Matrix4x4 Matrix()
	{
		float sx = scale.X();
		float sy = scale.Y();
		
		Vector2 rwd = basis.right();
		Vector2 uwd = basis.up();
		
		float tx = origin.X();
		float ty = origin.Y();
		float tz = zDepth;
		
		float rx = rwd.X();
		float ry = rwd.Y();
		
		float ux = uwd.X();
		float uy = uwd.Y();

		
		Matrix4x4 m = Matrix4x4.identity();
		
		m.set(rx * sx, 0, 0);
		m.set(ux * sy, 0, 1);
		
		m.set(ry * sx, 1, 0);
		m.set(uy * sy, 1, 1);
		
		m.set(tx, 0, 3);
		m.set(ty, 1, 3);
		m.set(tz, 2, 3);
		
		return m;
	}
	
	
	@Override
	public Ortho2x2 Basis()
	{
		return basis;
	}
	
	@Override
	public Vector2 Origin()
	{
		return origin;
	}

	@Override
	public Vector2 Scale()
	{
		return scale;
	}

	public void setBasis(Ortho2x2 basis)
	{
		this.basis = basis;
	}
	
	@Override
	public void setOrigin(Vector2 vec)
	{
		origin = vec;
	}
	
	@Override
	public void setScale(Vector2 vec)
	{
		scale = vec;
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
	public Transform2D instance()
	{
		return new Transform2D();
	}
	
	@Override
	public Transform2D copy()
	{
		Transform2D copy = Copyable.super.copy();
		
		copy.setBasis(basis.copy());
		copy.setOrigin(origin.copy());
		copy.setScale(scale.copy());
		
		return copy;
	}


	public float ZDepth()
	{
		return zDepth;
	}


	
	public void rotateFor(float rad)
	{
		basis.rotateFor(rad);
	}

	public void rotateTo(float rad)
	{
		basis.rotateTo(rad);
	}

	public Vector2 Right()
	{
		return basis.right();
	}
	
	public Vector2 Up()
	{
		return basis.up();
	}
}