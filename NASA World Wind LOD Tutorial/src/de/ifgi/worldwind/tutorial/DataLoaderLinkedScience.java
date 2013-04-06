package de.ifgi.worldwind.tutorial;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import com.hp.hpl.jena.query.ARQ;
import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;

public class DataLoaderLinkedScience {

    public static void main(String[] args) throws FileNotFoundException {
            DataLoaderLinkedScience lodDataset = new DataLoaderLinkedScience();
            lodDataset.queryAmazonCells();
    }

    public ArrayList<GeometryRecordLinkedScience> queryAmazonCells() throws FileNotFoundException {
        //Defining an array list of the type GeometryRecordLinkedScience. 
        ArrayList<GeometryRecordLinkedScience> geometryRecord = new ArrayList<GeometryRecordLinkedScience>();
        //Defining SPARQL Query
        String sparqlQuery =
        "SELECT ?deforestation_2007 ?pasture_2007 ?polygon " +
        "WHERE { "+
        " ?cell a <http://linkedscience.org/lsv/ns#Item> ; " +
        " <http://spatial.linkedscience.org/context/amazon/PRECO_PASTAGEM_2007> ?pasture_2007 ; " +
        " <http://observedchange.com/tisc/ns#geometry> ?polygon ;" +
        " <http://spatial.linkedscience.org/context/amazon/DEFOR_2007> ?deforestation_2007} ";

        //Executing query towards the LinkedScience.org SPARQL Endpoint.  
        Query query = QueryFactory.create(sparqlQuery);
        QueryExecution qexec = 
        		QueryExecutionFactory.sparqlService("http://spatial.linkedscience.org/sparql", query);
        ResultSet results = qexec.execSelect();
        //Iterating over the ResultSet and adding each record into our
        //GeometryRecordLinkedScience array list.
		while (results.hasNext()) {
		
		        QuerySolution soln = results.nextSolution();
		
		        GeometryRecordLinkedScience tmpLODRecord = new GeometryRecordLinkedScience(
		                        soln.getLiteral("deforestation_2007").getDouble(), 
		                        soln.getLiteral("pasture_2007").getDouble(), 
		                        soln.getLiteral("polygon").getString());
		
		        geometryRecord.add(tmpLODRecord);
		        System.out.println(soln.getLiteral("deforestation_2007").getDouble());
		}

        qexec.close();
        //Returning an array list with our query result elements.
        return geometryRecord;
    }
}