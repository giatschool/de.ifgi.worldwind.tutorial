package de.ifgi.worldwind.tutorial;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import com.hp.hpl.jena.query.ARQ;
import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.query.ResultSetFormatter;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;


public class TestXMLJena {
     
    public static void main(String[] args) throws FileNotFoundException {

        TestXMLJena jenaTest = new TestXMLJena();
        jenaTest.listTriples();        
        
    }

    public void listTriples() throws FileNotFoundException {
    	//Creates a model
        Model model = ModelFactory.createDefaultModel();
        //Loads the triple files to the model
    File dir = new File("turtleFiles/");
    File[] fileList = dir.listFiles();
   
    for (File f : fileList) {
    	InputStream in = null;

    	in = new FileInputStream(f);                                                         
           
    	if(in != null)
    		model.read(in, null, "TURTLE");
	}

        //Create the SPARQL Query 
        String sparqlQueryString =
        		"PREFIX foaf: <http://xmlns.com/foaf/0.1/> " +
        		"PREFIX ifgi: <http://ifgi.uni-muenster.de/> " +
        		" " +
        		"SELECT * WHERE { " +
        		"   ?subject ?predicate ?friends ." +
        		"	ifgi:jones foaf:knows ?friends . " +
        		"}";
            
        Query query = QueryFactory.create(sparqlQueryString);
        ARQ.getContext().setTrue(ARQ.useSAX);        


        QueryExecution qexec = QueryExecutionFactory.create(query, model);
        ResultSet results = qexec.execSelect();
        String resultString = new String();
        
        resultString = ResultSetFormatter.asXMLString(results);
        
        System.out.println(resultString);
        while (results.hasNext()) {

            QuerySolution soln = results.nextSolution();   

            System.out.println(soln.get("friends"));   
        }
        qexec.close();
    }
}