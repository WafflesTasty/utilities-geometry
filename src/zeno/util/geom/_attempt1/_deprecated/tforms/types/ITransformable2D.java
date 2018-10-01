package zeno.util.geom._attempt1._deprecated.tforms.types;

import zeno.util.geom._attempt1._deprecated.tforms.types.movement.IVantage2D;
import zeno.util.geom._attempt1._deprecated.tforms.types.scale.IScalable2D;
import zeno.util.geom._attempt1.utilities.bounds.Bounded2D;
import zeno.util.tools.Floats;

/**
 * The {@code ITransformable2D} interface defines an object
 * capable of being transformed in 2D space.
 *
 * @since Apr 22, 2016
 * @author Zeno
 * 
 * @see IScalable2D
 * @see IVantage2D
 * @see Bounded2D
 */
public interface ITransformable2D extends Bounded2D, IScalable2D, IVantage2D
{
	@Override
	public default float XMin()
	{
		float ox = Origin().X();
		float rx = Basis().right().X();
		float ux = Basis().up().X();
		float sx = Scale().X();
		
		rx = rx * sx / 2;
		ux = ux * sx / 2;
		
		return ox + Floats.min(-rx, -ux, rx, ux);
	}
	
	@Override
	public default float XMax()
	{
		float ox = Origin().X();
		float rx = Basis().right().X();
		float ux = Basis().up().X();
		float sx = Scale().X();
		
		rx = rx * sx / 2;
		ux = ux * sx / 2;
		
		return ox + Floats.max(-rx, -ux, rx, ux);
	}
	
	@Override
	public default float YMin()
	{
		float oy = Origin().Y();
		float ry = Basis().right().Y();
		float uy = Basis().up().Y();
		float sy = Scale().Y();
		
		ry = ry * sy / 2;
		uy = uy * sy / 2;
		
		return oy + Floats.min(-ry, -uy, ry, uy);
	}
	
	@Override
	public default float YMax()
	{
		float oy = Origin().Y();
		float ry = Basis().right().Y();
		float uy = Basis().up().Y();
		float sy = Scale().Y();
		
		ry = ry * sy / 2;
		uy = uy * sy / 2;
		
		return oy + Floats.max(-ry, -uy, ry, uy);
	}
}