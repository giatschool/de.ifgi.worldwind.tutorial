package de.ifgi.worldwind.tutorial;

import gov.nasa.worldwind.Configuration;
import gov.nasa.worldwind.avlist.AVKey;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.layers.LatLonGraticuleLayer;
import gov.nasa.worldwind.layers.RenderableLayer;
import gov.nasa.worldwind.render.*;
import gov.nasa.worldwind.util.BasicDragger;
import gov.nasa.worldwind.util.UnitsFormat;
import gov.nasa.worldwind.util.measure.MeasureTool;
import gov.nasa.worldwind.util.measure.MeasureToolController;
import gov.nasa.worldwindx.examples.ApplicationTemplate;

import java.util.ArrayList;

public class MeasuringFeaturesExample extends ApplicationTemplate
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

            // Set the coordinates (in degrees) to draw your polyline
            // To radians just change the method the class Position
            // to fromRadians().
            ArrayList<Position> positions = new ArrayList<Position>();
            positions.add(Position.fromDegrees(52, 8));
            positions.add(Position.fromDegrees(52, 13));
            positions.add(Position.fromDegrees(50, 19));
            positions.add(Position.fromDegrees(49, 19));
            
            Polyline polyline = new Polyline(positions,3e4);
            
            polyline.setColor(getBackground().ORANGE);
            polyline.setLineWidth(3);
                        
            //Tooltip text of the polygon
            polyline.setValue(AVKey.DISPLAY_NAME, "My first polyline"); 
            //Add the just created polygon to a renderable layer
            layer.addRenderable(polyline);
            // Add the layer to the model.
            insertBeforeCompass(getWwd(), layer);
            // Update layer panel
            this.getLayerPanel().update(this.getWwd());
            
            //Create the measure tool instance
            MeasureTool measure = new MeasureTool(this.getWwd());
            //Add a controller to the measure tool instance.
            measure.setController(new MeasureToolController());
            //Define the unit in which the measurements will be made.
            measure.getUnitsFormat().setLengthUnits(UnitsFormat.KILOMETERS);
            //Forces the line to follow the globe's surface
            measure.setFollowTerrain(true);
            //Create a polyline based on the given coordinates and
            //attaches it to the measure instance.
            measure.setMeasureShape(new Polyline(positions));
            insertBeforePlacenames(getWwd(), new LatLonGraticuleLayer());
            //A control output of the total polyline length
            System.out.println(measure.getLength()/1000 + " km ");
        }
    }

    public static void main(String[] args)
    {
        //Set the initial configurations of your NASA World Wind App
    	//Altitute, logitude and latitute, and window caption. 
    	Configuration.setValue(AVKey.INITIAL_LATITUDE, 51);
        Configuration.setValue(AVKey.INITIAL_LONGITUDE, 16);
        Configuration.setValue(AVKey.INITIAL_ALTITUDE, 160e4);
    	ApplicationTemplate.start("NASA World Wind Tutorial - Measuring Polylines", AppFrame.class);
    }
}