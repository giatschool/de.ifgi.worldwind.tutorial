package de.ifgi.worldwind.tests;

import gov.nasa.worldwind.avlist.*;
import gov.nasa.worldwind.geom.*;
import gov.nasa.worldwind.layers.BasicTiledImageLayer;
import gov.nasa.worldwind.util.*;

/**
 * Open Street Map layer. Try layers 'osm-map', 'basic' and 'sigma' for global coverage (more or less in progress).
 * See also 'boston-freemap-remote-mapserver' and 'boston' for Boston, USA area.
 * Ref: http://labs.metacarta.com/wms-c/Basic.py?L=0&request=metadata
 *
 * Usage: new OpenStreetMapLayer(layerName);  // See above
 *
 * @author Patrick Murris
 * @version $Id$
 */
public class OpenStreetMapWMSLayer extends BasicTiledImageLayer
{
    private String layerName;

    public OpenStreetMapWMSLayer(String layerName)
    {
        super(makeLevels(layerName));
        this.layerName = layerName;
    }

    private static LevelSet makeLevels(String layerName)
    {
        AVList params = new AVListImpl();

        params.setValue(AVKey.TILE_WIDTH, 256);
        params.setValue(AVKey.TILE_HEIGHT, 256);
        params.setValue(AVKey.DATA_CACHE_NAME, "Earth/OSM/" + layerName);
        params.setValue(AVKey.SERVICE, "http://labs.metacarta.com/wms-c/Basic.py");
        params.setValue(AVKey.DATASET_NAME, layerName);
        params.setValue(AVKey.FORMAT_SUFFIX, ".dds");
        params.setValue(AVKey.NUM_LEVELS, 20);
        params.setValue(AVKey.NUM_EMPTY_LEVELS, 0);
        params.setValue(AVKey.LEVEL_ZERO_TILE_DELTA, new LatLon(Angle.fromDegrees(180d), Angle.fromDegrees(180d)));
        params.setValue(AVKey.SECTOR, Sector.FULL_SPHERE);

        return new LevelSet(params);
    }

    @Override
    public String toString()
    {
        return "OpenStreetMap: " + layerName;
    }
}