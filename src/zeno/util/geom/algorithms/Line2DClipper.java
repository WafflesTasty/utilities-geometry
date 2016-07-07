package zeno.util.geom.algorithms;

import zeno.util.algebra.vectors.fixed.Vector2;
import zeno.util.geom.shapes.lines.Line2D;
import zeno.util.geom.shapes.surfaces.lateral.Rectangle;
import zeno.util.tools.Messenger;

/**
 * The {@code Line2DClipper} class defines an algorithm
 * that clips lines to a rectangular boundary.
 * 
 * @author Zeno
 * @since Feb 16, 2015
 * @see <a href="http://en.wikipedia.org/wiki/Line_clipping">Wikipedia - Line clipping</a>
 * @see <a href="http://www.sersc.org/journals/IJCG/vol3_no2/3.pdf">B. Ray - Clipping Algorithm</a>
 * @see Messenger
 */
public class Line2DClipper extends Messenger
{
	private float dx, dy;
	private Rectangle bounds;
	private boolean isExtending;
		
	/**
	 * Creates a new {@code Line2DClipper}.
	 */
	public Line2DClipper()
	{
		bounds = new Rectangle();
	}
	
	
	/**
	 * Changes the extension state of the {@code Line2DClipper}.
	 * 
	 * @param isExtending  a new extension state
	 */
	public void setExtending(boolean isExtending)
	{
		this.isExtending = isExtending;
	}
	
	/**
	 * Changes the clip boundary of the {@code Line2DClipper}.
	 * 
	 * @param x1  a minimum x-coördinate
	 * @param y1  a minimum y-coördinate
	 * @param x2  a maximum x-coördinate
	 * @param y2  a maximum y-coördinate
	 */
	public void setBounds(float x1, float y1, float x2, float y2)
	{
		bounds = new Rectangle
		(
			0.5f * (x1 + x2),
			0.5f * (y1 + y2),
			x2 - x1,
			y2 - y1
		);
	}
	
	/**
	 * Changes the clip boundary of the {@code Line2DClipper}.
	 * 
	 * @param bounds  a clip boundary
	 * @see Rectangle
	 */
	public void setBounds(Rectangle bounds)
	{
		this.bounds = bounds;
	}
		
	
	/**
	 * Clips a line to the bounding rectangle.
	 * 
	 * @param x1  the line's first x-coördinate
	 * @param y1  the line's first y-coördinate
	 * @param x2  the line's second x-coördinate
	 * @param y2  the line's second y-coördinate
	 * @return  the clipped line
	 * @see Line2D
	 */
	public Line2D clip(float x1, float y1, float x2, float y2)
	{
		dx = x2 - x1;
		dy = y2 - y1;
	
		Vector2 p1 = point(x1, y1, x2, y2);
		if(p1 != null)
		{
			Vector2 p2 = point(x2, y2, x1, y1);
			if(p2 != null)
			{
				return new Line2D(p1, p2);
			}
		}

		return null;
	}
	
	/**
	 * Clips a line to the bounding rectangle.
	 * 
	 * @param p1  the line's first point
	 * @param p2  the line's second point
	 * @return  the clipped line
	 * @see Vector2
	 * @see Line2D
	 */
	public Line2D clip(Vector2 p1, Vector2 p2)
	{
		return clip(p1.X(), p1.Y(), p2.X(), p2.Y());
	}
	
	/**
	 * Clips a line to the bounding rectangle.
	 * 
	 * @param line  a line to clip
	 * @return  the clipped line
	 * @see Line2D
	 */
	public Line2D clip(Line2D line)
	{
		return clip(line.X1(), line.Y1(), line.X2(), line.Y2());
	}
	
	
	private Vector2 extend(float x1, float y1, float x2, float y2)
	{
		float xmin = bounds.XMin();
		float xmax = bounds.XMax();
		float ymin = bounds.YMin();
		float ymax = bounds.YMax();
		
		float dxdymin = (x2 - x1) * (ymin - y1);
		float dxdymax = (x2 - x1) * (ymax - y1);
		float dydxmin = (y2 - y1) * (xmin - x1);
		float dydxmax = (y2 - y1) * (xmax - x1);
				
		if(x2 > x1)
		{
			if((dxdymin - dydxmin) * (dxdymax - dydxmin) <= 0)
			{
				return new Vector2(xmin, y1 + dydxmin / (x2 - x1));
			}
		}
		else if(x2 < x1)
		{
			if((dxdymin - dydxmax) * (dxdymax - dydxmax) <= 0)
			{
				return new Vector2(xmax, y1 + dydxmax / (x2 - x1));
			}
		}
		
		if(y2 > y1)
		{
			if((dxdymin - dydxmin) * (dxdymin - dydxmax) <= 0)
			{
				return new Vector2(x1 + dxdymin / (y2 - y1), ymin);
			}
		}
		else if(y2 < y1)
		{
			if((dxdymax - dydxmin) * (dxdymax - dydxmax) <= 0)
			{
				return new Vector2(x1 + dxdymax / (y2 - y1), ymax);
			}
		}
		
		return null;
	}

	private Vector2 point(float x1, float y1, float x2, float y2)
	{
		if(bounds.contains(x1, y1))
		{
			if(!isExtending)
			{				
				return new Vector2(x1, y1);
			}
			
			return extend(x1, y1, x2, y2);
		}
		
		return clip(x1, y1);
	}
							
	private Vector2 clip(float x, float y)
	{
		float xmin = bounds.XMin();
		float xmax = bounds.XMax();
		float ymin = bounds.YMin();
		float ymax = bounds.YMax();
		
		float dxmin = dy * (xmin - x);
		float dxmax = dy * (xmax - x);
		float dymin = dx * (ymin - y);
		float dymax = dx * (ymax - y);
		
		if(x < xmin)
		{
			if((dymin - dxmin) * (dymax - dxmin) <= 0)
			{
				return new Vector2(xmin, y + dxmin / dx);
			}
		}
		else if(x > xmax)
		{
			if((dymin - dxmax) * (dymax - dxmax) <= 0)
			{
				return new Vector2(xmax, y + dxmax / dx);
			}
		}
		
		if(y < ymin)
		{
			if((dxmin - dymin) * (dxmax - dymin) <= 0)
			{
				return new Vector2(x + dymin / dy, ymin);
			}
		}
		else if(y > ymax)
		{
			if((dxmin - dymax) * (dxmax - dymax) <= 0)
			{
				return new Vector2(x + dymax / dy, ymax);
			}
		}
		
		return null;
	}
}