package de.ifgi.worldwind.tutorial;

import gov.nasa.worldwind.Configuration;
import gov.nasa.worldwind.avlist.AVKey;
import gov.nasa.worldwind.formats.shapefile.Shapefile;
import gov.nasa.worldwind.formats.shapefile.ShapefileRecord;
import gov.nasa.worldwind.layers.RenderableLayer;
import gov.nasa.worldwind.render.*;
import gov.nasa.worldwind.util.BasicDragger;
import gov.nasa.worldwind.util.VecBuffer;
import gov.nasa.worldwindx.examples.ApplicationTemplate;
import java.io.File;


public class PolygonFromShapefile extends ApplicationTemplate
{
    public static class AppFrame extends ApplicationTemplate.AppFrame
    {
        public AppFrame()
        {
            super(true, true, false);

            // Enable shape dragging, if you want.
            this.getWwd().addSelectListener(new BasicDragger(this.getWwd()));
            
            //Create the layer where you will place your polygons
            RenderableLayer layer = new RenderableLayer();            
            //Creating a shapefile based on an ordinary File object            
            File file = new File("/home/jones/pernambuco/pernambuco.shp");
            Shapefile shapeFile = new Shapefile(file);            
            
            //Setting attributes for the loaded shapefile
            ShapeAttributes normalAttributes = new BasicShapeAttributes();
            normalAttributes.setInteriorMaterial(Material.ORANGE);
            normalAttributes.setOutlineWidth(1);           

            //Performing a loop into the shapefile to get all its polygons
            while (shapeFile.hasNext()) {
            	//Reading the shapefile current record and storing in a 
            	//temporary variable of the type ShapefileRecord
            	ShapefileRecord record = shapeFile.nextRecord();	
                VecBuffer vectorBuffer = record.getPointBuffer(0);
                //Setting height
                Double height = 500.50;
                //Creating an instance of ExtrudedPolygon
                ExtrudedPolygon polygon = new ExtrudedPolygon();
                //Setting the polygon outer boundary bases on the current shapefile record
                polygon.setOuterBoundary(vectorBuffer.getLocations(),height);
                //Setting polygon attributes 
                polygon.setAttributes(normalAttributes);
                //Attaching the polygon to a layer
                layer.addRenderable(polygon);               
			}
            
            //Closing shape file
            shapeFile.close();
                      
            insertBeforeCompass(getWwd(), layer);
            // Update layer panel
            this.getLayerPanel().update(this.getWwd());
            

        }
    }

    public static void main(String[] args)
    {
        //Set the initial configurations of your NASA World Wind App
    	//Altitute, logitude and latitute, and window caption. 
    	Configuration.setValue(AVKey.INITIAL_LATITUDE, -8.5);
        Configuration.setValue(AVKey.INITIAL_LONGITUDE, -37);
        Configuration.setValue(AVKey.INITIAL_ALTITUDE, 120e4);
    	ApplicationTemplate.start("NASA World Wind Tutorial - Loading Shapefiles", AppFrame.class);
    }
}