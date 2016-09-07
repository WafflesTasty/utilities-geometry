package zeno.util.geom.algorithms;

import zeno.util.algebra.vectors.fixed.Vector3;
import zeno.util.geom.shapes.lines.Line3D;
import zeno.util.geom.shapes.solids.Lateral3D;
import zeno.util.geom.shapes.solids.lateral.Cuboid;
import zeno.util.tools.Messenger;

/**
 * The {@code Line3DClipper} class defines an algorithm
 * that clips lines to a cuboid boundary.
 * 
 * @since Feb 16, 2015
 * @author Zeno
 * 
 * @see <a href="http://en.wikipedia.org/wiki/Line_clipping">Wikipedia - Line clipping</a>
 * @see <a href="http://www.sersc.org/journals/IJCG/vol3_no2/3.pdf">B. Ray - Clipping Algorithm</a>
 * @see Messenger
 */
public class Line3DClipper extends Messenger
{
	private Lateral3D bounds;
	private float dx, dy, dz;
	private boolean isExtending;
		
	/**
	 * Creates a new {@code Line3DClipper}.
	 */
	public Line3DClipper()
	{
		bounds = new Cuboid();
	}
	
	
	/**
	 * Changes the extension of the {@code Line3DClipper}.
	 * 
	 * @param isExtending  a new extension state
	 */
	public void setExtending(boolean isExtending)
	{
		this.isExtending = isExtending;
	}
	
	/**
	 * Changes the clip boundary of the {@code Line3DClipper}.
	 * 
	 * @param x1  a minimum x-coördinate
	 * @param y1  a minimum y-coördinate
	 * @param z1  a minimum z-coördinate
	 * @param x2  a maximum x-coördinate
	 * @param y2  a maximum y-coördinate
	 * @param z2  a maximum z-coördinate
	 */
	public void setBounds(float x1, float y1, float z1, float x2, float y2, float z2)
	{
		bounds = new Cuboid
		(
			0.5f * (x1 + x2),
			0.5f * (y1 + y2),
			0.5f * (z1 + z2),
			x2 - x1,
			y2 - y1,
			z2 - z1
		);
	}
	
	/**
	 * Changes the clip boundary of the {@code Line3DClipper}.
	 * 
	 * @param bounds  a clip boundary
	 * @see Lateral3D
	 */
	public void setBounds(Lateral3D bounds)
	{
		this.bounds = bounds;
	}
		
	
	/**
	 * Clips a line to the bounding cuboid.
	 * 
	 * @param x1  the line's first x-coördinate
	 * @param y1  the line's first y-coördinate
	 * @param z1  the line's first z-coördinate
	 * @param x2  the line's second x-coördinate
	 * @param y2  the line's second y-coördinate
	 * @param z2  the line's second z-coördinate
	 * @return  the clipped line
	 * @see Line3D
	 */
	public Line3D clip(float x1, float y1, float z1, float x2, float y2, float z2)
	{
		dx = x2 - x1;
		dy = y2 - y1;
		dz = z2 - z1;
	
		Vector3 p1 = point(x1, y1, z1, x2, y2, z2);
		if(p1 != null)
		{
			Vector3 p2 = point(x2, y2, z2, x1, y1, z1);
			if(p2 != null)
			{
				return new Line3D(p1, p2);
			}
		}

		return null;
	}
	
	/**
	 * Clips a line to the bounding cuboid.
	 * 
	 * @param p1  the line's first point
	 * @param p2  the line's second point
	 * @return  the clipped line
	 * @see Vector3
	 * @see Line3D
	 */
	public Line3D clip(Vector3 p1, Vector3 p2)
	{
		return clip(p1.X(), p1.Y(), p1.Z(), p2.X(), p2.Y(), p2.Z());
	}
	
	/**
	 * Clips a line to the bounding cuboid.
	 * 
	 * @param line  a line to clip
	 * @return  the clipped line
	 * @see Line3D
	 */
	public Line3D clip(Line3D line)
	{
		return clip(line.X1(), line.Y1(), line.Z1(), line.X2(), line.Y2(), line.Z2());
	}
	
	
	private Vector3 extend(float x1, float y1, float z1, float x2, float y2, float z2)
	{
		float xmin = bounds.XMin();
		float xmax = bounds.XMax();
		float ymin = bounds.YMin();
		float ymax = bounds.YMax();
		float zmin = bounds.ZMin();
		float zmax = bounds.ZMax();
		
		float dxdymin = (x2 - x1) * (ymin - y1);
		float dxdymax = (x2 - x1) * (ymax - y1);
		float dxdzmin = (x2 - x1) * (zmin - z1);
		float dxdzmax = (x2 - x1) * (zmax - z1);
		float dydxmin = (y2 - y1) * (xmin - x1);
		float dydxmax = (y2 - y1) * (xmax - x1);
		float dydzmin = (y2 - y1) * (zmin - z1);
		float dydzmax = (y2 - y1) * (zmax - z1);
		float dzdxmin = (z2 - z1) * (xmin - x1);
		float dzdxmax = (z2 - z1) * (xmax - x1);
		float dzdymin = (z2 - z1) * (ymin - y1);
		float dzdymax = (z2 - z1) * (ymax - y1);
		
		if(x2 > x1)
		{
			if((dxdymin - dydxmin) * (dxdymax - dydxmin) <= 0
			&& (dxdzmin - dzdxmin) * (dxdzmax - dzdxmin) <= 0)
			{
				return new Vector3(xmin, y1 + dydxmin / (x2 - x1), z1 + dzdxmin / (x2 - x1));
			}
		}
		else if(x2 < x1)
		{
			if((dxdymin - dydxmax) * (dxdymax - dydxmax) <= 0
			&& (dxdzmin - dzdxmax) * (dxdzmax - dzdxmax) <= 0)
			{
				return new Vector3(xmax, y1 + dydxmax / (x2 - x1), z1 + dzdxmax / (x2 - x1));
			}
		}
		
		if(y2 > y1)
		{
			if((dxdymin - dydxmin) * (dxdymin - dydxmax) <= 0
			&& (dzdymin - dydzmin) * (dzdymin - dydzmax) <= 0)
			{
				return new Vector3(x1 + dxdymin / (y2 - y1), ymin, z1 + dzdymin / (y2 - y1));
			}
		}
		else if(y2 < y1)
		{
			if((dxdymax - dydxmin) * (dxdymax - dydxmax) <= 0
			&& (dzdymax - dydzmin) * (dzdymax - dydzmax) <= 0)
			{
				return new Vector3(x1 + dxdymax / (y2 - y1), ymax, z1 + dzdymax / (y2 - y1));
			}
		}
		
		if(z2 > z1)
		{
			if((dxdzmin - dzdxmin) * (dxdzmin - dzdxmax) <= 0
			&& (dydzmin - dzdymin) * (dydzmin - dzdymax) <= 0)
			{
				return new Vector3(x1 + dxdzmin / (z2 - z1), y1 + dydzmin / (z2 - z1), zmin);
			}
		}
		else if(z2 < z1)
		{
			if((dxdzmax - dzdxmin) * (dxdzmax - dzdxmax) <= 0
			&& (dydzmax - dzdymin) * (dydzmax - dzdymax) <= 0)
			{
				return new Vector3(x1 + dxdzmax / (z2 - z1), y1 + dydzmax / (z2 - z1), zmax);
			}
		}
		
		return null;
	}

	private Vector3 point(float x1, float y1, float z1, float x2, float y2, float z2)
	{
		if(bounds.contains(x1, y1, z1))
		{
			if(!isExtending)
			{				
				return new Vector3(x1, y1, z1);
			}
			
			return extend(x1, y1, z1, x2, y2, z2);
		}
		
		return clip(x1, y1, z1);
	}
							
	private Vector3 clip(float x, float y, float z)
	{
		float xmin = bounds.XMin();
		float xmax = bounds.XMax();
		float ymin = bounds.YMin();
		float ymax = bounds.YMax();
		float zmin = bounds.ZMin();
		float zmax = bounds.ZMax();
		
		float dxdymin = dx * (ymin - y);
		float dxdymax = dx * (ymax - y);
		float dxdzmin = dx * (zmin - z);
		float dxdzmax = dx * (zmax - z);
		float dydxmin = dy * (xmin - x);
		float dydxmax = dy * (xmax - x);
		float dydzmin = dy * (zmin - z);
		float dydzmax = dy * (zmax - z);
		float dzdxmin = dz * (xmin - x);
		float dzdxmax = dz * (xmax - x);
		float dzdymin = dz * (ymin - y);
		float dzdymax = dz * (ymax - y);
		
		if(x < xmin)
		{
			if((dxdymin - dydxmin) * (dxdymax - dydxmin) <= 0
			&& (dxdzmin - dzdxmin) * (dxdzmax - dzdxmin) <= 0)
			{
				return new Vector3(xmin, y + dydxmin / dx, z + dzdxmin / dx);
			}
		}
		else if(x > xmax)
		{
			if((dxdymin - dydxmax) * (dxdymax - dydxmax) <= 0
			&& (dxdzmin - dzdxmax) * (dxdzmax - dzdxmax) <= 0)
			{
				return new Vector3(xmax, y + dydxmax / dx, z + dzdxmax / dx);
			}
		}
		
		if(y < ymin)
		{
			if((dxdymin - dydxmin) * (dxdymin - dydxmax) <= 0
			&& (dzdymin - dydzmin) * (dzdymin - dydzmax) <= 0)
			{
				return new Vector3(x + dxdymin / dy, ymin, z + dzdymin / dy);
			}
		}
		else if(y > ymax)
		{
			if((dxdymax - dydxmin) * (dxdymax - dydxmax) <= 0
			&& (dzdymax - dydzmin) * (dzdymax - dydzmax) <= 0)
			{
				return new Vector3(x + dxdymax / dy, ymax, z + dzdymax / dy);
			}
		}
		
		if(z < zmin)
		{
			if((dxdzmin - dzdxmin) * (dxdzmin - dzdxmax) <= 0
			&& (dydzmin - dzdymin) * (dydzmin - dzdymax) <= 0)
			{
				return new Vector3(x + dxdzmin / dz, y + dydzmin / dz, zmin);
			}
		}
		else if(z > zmax)
		{
			if((dxdzmax - dzdxmin) * (dxdzmax - dzdxmax) <= 0
			&& (dydzmax - dzdymin) * (dydzmax - dzdymax) <= 0)
			{
				return new Vector3(x + dxdzmax / dz, y + dydzmax / dz, zmax);
			}
		}
		
		return null;
	}
}