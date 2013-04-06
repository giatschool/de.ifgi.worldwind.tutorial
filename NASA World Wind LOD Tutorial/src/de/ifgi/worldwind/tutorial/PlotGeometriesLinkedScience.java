package de.ifgi.worldwind.tutorial;

import gov.nasa.worldwind.Configuration;
import gov.nasa.worldwind.avlist.AVKey;
import gov.nasa.worldwind.geom.*;
import gov.nasa.worldwind.layers.RenderableLayer;
import gov.nasa.worldwind.render.*;
import gov.nasa.worldwind.render.airspaces.Polygon;
import gov.nasa.worldwind.terrain.ZeroElevationModel;
import gov.nasa.worldwindx.examples.ApplicationTemplate;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class PlotGeometriesLinkedScience extends ApplicationTemplate
{
	public static class AppFrame extends ApplicationTemplate.AppFrame
	{
	    public AppFrame() throws FileNotFoundException
	    {
	        super(true, true, false);
	        //Setting the elevation model to zero
	        this.getWwd().getModel().getGlobe().setElevationModel(new ZeroElevationModel());
	        //Creating the renderable layer
	        RenderableLayer layer = new RenderableLayer();                       
	        //Creating an instance of our class DataLoaderLinkedScience            
	        DataLoaderLinkedScience dataLoader = new DataLoaderLinkedScience();            
	        //Creating an array of our predefined type GeometryRecordLinkedScience
            ArrayList<GeometryRecordLinkedScience> geometryRecord = new ArrayList<GeometryRecordLinkedScience>();             
            //Calling the function queryStates to execute our predefined SPARQL Query
            geometryRecord = dataLoader.queryAmazonCells();
            //Iterating the SPARQL result and adding its records into a Positions array 
	for (int i = 0; i < geometryRecord.size(); i++) {
	
	    ArrayList borderPositions = new ArrayList();
	    //Splitting every coordinate, which in our dataset is separated by ";"
	    String latlong[] = geometryRecord.get(i).getGeometry().split(";");
	    //Getting the deforestation and pasture price of 
	    //each municipality for further use
	    double deforestation = geometryRecord.get(i).getDeforestation();
	    double pasture = geometryRecord.get(i).getPasturePrice();
	
	    for(String str: latlong){
	
	        //Splitting the latitude / longitude, which in our dataset is separated by ","
	        String latlong2[] = str.split(",");     
	        //Adding the latitude and longitude to the Positions array
	        borderPositions.add(Position.fromDegrees(Double.parseDouble(latlong2[1]),
	                                                 Double.parseDouble(latlong2[0]),1e4));
	    }
	
	    //Creating a new renderable airspaces Polygon
	    gov.nasa.worldwind.render.airspaces.Polygon polygon = new Polygon(borderPositions);
	    //Setting polygons altitude based on the pasture price 
	    polygon.setAltitudes(pasture*220,0);
	
	    //Classifying the polygons due its amount of deforestation,
	    //giving them different colors
	    if (deforestation == 0.0) {
	            polygon.getAttributes().setMaterial(Material.GREEN);                    
	    } else if (deforestation > 0 && deforestation <= 0.005 ) {       
	            polygon.getAttributes().setMaterial(Material.BLUE);} 
	     else if (deforestation > 0.005  && deforestation <= 0.01 ) {
	          polygon.getAttributes().setMaterial(Material.YELLOW);} 
	     else if (deforestation > 0.01 && deforestation < 0.02) {
	          polygon.getAttributes().setMaterial(Material.ORANGE);} 
	     else if (deforestation > 0.02 ) {
	            polygon.getAttributes().setMaterial(Material.RED);} 
	
	    polygon.getAttributes().setDrawOutline(true);
	
	    //Creating a tooltip text for each polygon, containing their 
	    //amount of deforestation  and pasture price
	    polygon.setValue(AVKey.DISPLAY_NAME," Deforestation: " + 
	                  geometryRecord.get(i).getDeforestation() + 
	                  "km2 \n Pasture Price: R$" + geometryRecord.get(i).getPasturePrice());
	
	    //Adding the polygon to the renderable layer
	        layer.addRenderable(polygon);
	   }
	
	        // Add the layer to the model.
	        insertBeforeCompass(getWwd(), layer);
	        // Update layer panel
	        this.getLayerPanel().update(this.getWwd());
	
	    }
	}
	
	public static void main(String[] args)
	{
	    Configuration.setValue(AVKey.INITIAL_LATITUDE, -9.16);
	    Configuration.setValue(AVKey.INITIAL_LONGITUDE, -56);
	    Configuration.setValue(AVKey.INITIAL_ALTITUDE, 500e4);
	
	    ApplicationTemplate.start("NASA World Wind Tutorial - " + 
	                              "LinkedScience Amazon Rainforest Dataset", AppFrame.class);
	}
}