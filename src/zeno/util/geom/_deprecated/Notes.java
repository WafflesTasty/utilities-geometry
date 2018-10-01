package zeno.util.geom._deprecated;

public interface Notes
{
	/*
	 * Each transformable (or subinterface?) has a transform and a shape.
	 * Each transformation has a matrix and an inverse matrix.
	 * 
	 * You allow containment and intersection of points and lines in transformables.
	 * Implement contains/intersects functions by inverse transforming the points/lines.
	 * 
	 * Objects can be bounded by a cuboid (min/max values) or a sphere (center + radius).
	 * Does each transformable have to be bounded? Not at first glance. However each transformation
	 * can have a boundary defined by transforming unit cubes/spheres. In that regard transformables
	 * could easily take this as their same default boundary as well. But is this in any way useful?
	 * 
	 * When creating bounded objects, what are the abstract components and what are the default ones?
	 * You could make center/scale abstract, then the actual bounds default, or you could make the bounds
	 * abstract, and then have the center/scale be default. The latter seems to imply more waste of memory
	 * space at first glance.
	 */
}
