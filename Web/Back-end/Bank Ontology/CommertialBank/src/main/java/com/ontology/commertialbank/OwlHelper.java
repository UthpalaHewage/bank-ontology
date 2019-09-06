package com.ontology.commertialbank;

import java.io.ByteArrayOutputStream;
import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.query.ResultSetFormatter;
import org.apache.jena.rdf.model.Literal;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.util.FileManager;
import org.json.simple.JSONObject;



public class OwlHelper {
public static String OwlToJson(String sparqlQuery ) {
		
		String r = null;
		
		FileManager.get().addLocatorClassLoader(OwlHelper.class.getClassLoader());
		Model model=FileManager.get().loadModel("D:\\Bank Ontology\\CommertialBank\\src\\main\\java\\com\\ontology\\commertialbank\\data.rdf");
		
		String queryString = sparqlQuery;
			
		  Query query=QueryFactory.create(queryString);
		  QueryExecution quexec=QueryExecutionFactory.create(query,model);
		  try {
			  ResultSet results=quexec.execSelect();
			  
			  ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

			  ResultSetFormatter.outputAsJSON(outputStream, results);

			  String json = new String(outputStream.toByteArray());

			 r=json;
			  
		  }catch(Exception e) {
			  System.out.println(e);
			  
		  }
			  finally {
				  
				  quexec.close();
				 
			  }
		  return r;
	}
}
