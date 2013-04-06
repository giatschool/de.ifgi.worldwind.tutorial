package de.ifgi.worldwind.tutorial;

import gov.nasa.worldwind.Configuration;
import gov.nasa.worldwind.avlist.AVKey;
import gov.nasa.worldwind.geom.*;
import gov.nasa.worldwind.layers.AnnotationLayer;
import gov.nasa.worldwind.layers.LatLonGraticuleLayer;
import gov.nasa.worldwind.render.*;
import gov.nasa.worldwindx.examples.ApplicationTemplate;
import gov.nasa.worldwindx.examples.util.OpenStreetMapShapefileLoader;
import gov.nasa.worldwindx.examples.util.PowerOfTwoPaddedImage;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Point;
import java.io.FileNotFoundException;


public class SimpleScreenAnnotations extends ApplicationTemplate
{
    public static class AppFrame extends ApplicationTemplate.AppFrame
    {
        public AppFrame() throws FileNotFoundException
        {
            super(true, true, false);
                
                //Creating an annotation layer
                AnnotationLayer annotationlayer = new AnnotationLayer();
                //Creating a screen annotation, providing its x,y screen position and text  
                ScreenAnnotation screenAnnotation = new ScreenAnnotation("" +
                				"NASA World Wind SDK Tutorial -" +
                                " Creating Screen Annotations", new Point(550, 620));

                //Defining border colour
                screenAnnotation.getAttributes().setBorderColor(Color.BLACK);
                //Defining text colour
                screenAnnotation.getAttributes().setTextColor(Color.BLUE);
                //Defining background colour
                screenAnnotation.getAttributes().setBackgroundColor(Color.YELLOW);
                //Defining border width
                screenAnnotation.getAttributes().setBorderWidth(1);
                //Defining font type, size and bold.
                screenAnnotation.getAttributes().setFont(Font.decode("Arial-BOLD-17"));
                //Defining annotation size 
                screenAnnotation.getAttributes().setSize(new Dimension(710, 0));
                           
            ScreenAnnotation factScreenAnnotation = new ScreenAnnotation("Screen Position: " +
                        "Point(200, 280) \n  " +
                        "Border Color: Black \n" +
                        "Text Color: White \n" +
                        "Border Width: 1 \n" +
                        "Font: Verdana, 17, Bold \n" +
                        "Background Color: Color(0.2f, 0.2f, 0.2f, .5f) \n" +
                        "" , new Point(200, 280));
            //Creating a transparent texture for the annotation background
            factScreenAnnotation.getAttributes().setBackgroundColor(new Color(0.2f, 0.2f, 0.2f, .5f));
            factScreenAnnotation.getAttributes().setBorderColor(Color.BLACK);
            factScreenAnnotation.getAttributes().setTextColor(Color.WHITE);
            factScreenAnnotation.getAttributes().setBorderWidth(1);
            //Defining the maximum altitude for the annotation visibility. 
                        factScreenAnnotation.setMaxActiveAltitude(181941);
                        //Defining the minimum altitude for the annotation visibility.
                        factScreenAnnotation.setMinActiveAltitude(61941);
            //Making the annotation borders squared 
            factScreenAnnotation.getAttributes().setCornerRadius(0);
            factScreenAnnotation.getAttributes().setFont(Font.decode("Arial-BOLD-17"));
            factScreenAnnotation.getAttributes().setSize(new Dimension(310, 0));
            factScreenAnnotation.setAlwaysOnTop(false);
            factScreenAnnotation.getAttributes().setAdjustWidthToText(AVKey.SIZE_FIXED);

            
            ScreenAnnotation factScreenAnnotation2 = new ScreenAnnotation("Screen Position: " +
                        "Point(840, 280) \n  " +
                        "Border Color: White \n" +
                        "Text Color: White \n" +
                        "Border Width: 2 \n" +
                        "Font: Verdana, 15, Italic \n" +
                        "Background Color: Blue" +
                        "" , new Point(840, 280));
            factScreenAnnotation2.getAttributes().setBackgroundColor(Color.BLUE);
            factScreenAnnotation2.getAttributes().setBorderColor(Color.WHITE);
            factScreenAnnotation2.getAttributes().setTextColor(Color.WHITE);
            factScreenAnnotation2.getAttributes().setBorderWidth(2);
            factScreenAnnotation2.setMaxActiveAltitude(181941);
            factScreenAnnotation2.setMinActiveAltitude(61941);
            factScreenAnnotation2.getAttributes().setFont(Font.decode("Arial-ITALIC-17"));
            factScreenAnnotation2.getAttributes().setSize(new Dimension(310, 0));
            factScreenAnnotation2.setAlwaysOnTop(false);
            factScreenAnnotation2.getAttributes().setAdjustWidthToText(AVKey.SIZE_FIXED);
                                                          
                //Defining annotation background image
            PowerOfTwoPaddedImage pic = PowerOfTwoPaddedImage.fromPath("images/ifgi.jpg");   
            
            
            ScreenAnnotation logoScreenAnnotation = new ScreenAnnotation("", new Point(720, 50));
            //Adding picture to the annotation background
            logoScreenAnnotation.getAttributes().setImageSource(pic.getPowerOfTwoImage());
            logoScreenAnnotation.getAttributes().setImageRepeat(AVKey.REPEAT_NONE);
            logoScreenAnnotation.getAttributes().setAdjustWidthToText(AVKey.SIZE_FIXED); 
            logoScreenAnnotation.getAttributes().setDrawOffset(new Point(100, 0)); 
            logoScreenAnnotation.getAttributes().setHighlightScale(1);
            logoScreenAnnotation.getAttributes().setInsets(new Insets(0, 40, 0, 0));
            logoScreenAnnotation.getAttributes().setSize(new Dimension(350, 120));
                                   
            annotationlayer.addAnnotation(screenAnnotation);
            annotationlayer.addAnnotation(factScreenAnnotation2);
            annotationlayer.addAnnotation(factScreenAnnotation);
            annotationlayer.addAnnotation(logoScreenAnnotation);
                insertBeforeCompass(getWwd(), annotationlayer);
                               
            //insertBeforePlacenames(this.getWwd(), new OpenStreetMapShapefileLoader());
            
            this.getLayerPanel().update(this.getWwd());
            
        }
    }

    public static void main(String[] args)
    {
        Configuration.setValue(AVKey.INITIAL_LATITUDE, 52);
        Configuration.setValue(AVKey.INITIAL_LONGITUDE, 10);
        Configuration.setValue(AVKey.INITIAL_ALTITUDE, 150e4);
        
        ApplicationTemplate.start("NASA World Wind Tutorial - " +
                        "Creating Screen Annotations", AppFrame.class);
    }
}
