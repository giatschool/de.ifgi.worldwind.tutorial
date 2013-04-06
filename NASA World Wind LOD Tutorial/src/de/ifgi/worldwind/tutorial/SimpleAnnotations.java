package de.ifgi.worldwind.tutorial;

import gov.nasa.worldwind.Configuration;
import gov.nasa.worldwind.avlist.AVKey;
import gov.nasa.worldwind.geom.*;
import gov.nasa.worldwind.layers.AnnotationLayer;
import gov.nasa.worldwind.render.*;
import gov.nasa.worldwindx.examples.ApplicationTemplate;
import gov.nasa.worldwindx.examples.util.PowerOfTwoPaddedImage;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.io.FileNotFoundException;

public class SimpleAnnotations extends ApplicationTemplate
{
    public static class AppFrame extends ApplicationTemplate.AppFrame
    {
        public AppFrame() throws FileNotFoundException
        {
            super(true, true, false);
            //Defining position where the annotation will pop-up
                Position position = new Position(Position.fromDegrees(52, 7.5),0.1);
                //Defining annotation background image
                PowerOfTwoPaddedImage pic = PowerOfTwoPaddedImage.fromPath("images/ifgi.jpg");                  
                //Creating an annotation layer
                AnnotationLayer annotationlayer = new AnnotationLayer();
                //Creating a globe annotation
            GlobeAnnotation ga = new GlobeAnnotation("\n\n\n\n\nNASA World Wind SDK Tutorial - " +
                        "Simple Annotations.",position, Font.decode("Arial-BOLD-13")); 

            //Defining annotation attributes, e.g. border colour, border widht and size. 
                        ga.getAttributes().setImageSource(pic.getPowerOfTwoImage());
                        ga.getAttributes().setBorderColor(Color.WHITE);
                        ga.getAttributes().setBackgroundColor(Color.WHITE);
                        ga.getAttributes().setBorderWidth(1);
                        ga.getAttributes().setImageScale(1.2);
                        ga.getAttributes().setSize(new Dimension(480,145));
                        ga.setAlwaysOnTop(true);
                //Defining the maximum altitude for the annotation visibility. 
                ga.setMaxActiveAltitude(1081941);
                //Attaching the annotation to the annotation layer.
                annotationlayer.addAnnotation(ga);
                //Inserting annotation layer to the application.
            insertBeforeCompass(getWwd(), annotationlayer);            
            this.getLayerPanel().update(this.getWwd());

        }
    }

    public static void main(String[] args)
    {
        Configuration.setValue(AVKey.INITIAL_LATITUDE, 52);
        Configuration.setValue(AVKey.INITIAL_LONGITUDE, 10);
        Configuration.setValue(AVKey.INITIAL_ALTITUDE, 150e4);

        ApplicationTemplate.start("NASA World Wind Tutorial - " +
                        "Simple Annotations", AppFrame.class);
    }
}