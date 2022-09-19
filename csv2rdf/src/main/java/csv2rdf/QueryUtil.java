package csv2rdf;

import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.RDFNode;

public class QueryUtil {
	 public static final String FUSEKI_SERVER = "http://localhost:3030/test/sparql";
	 public static final String TEST = "http://test.org/label/";
	 public static final String ACTOR_QUERY = "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +
			  "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
			  "SELECT ?x \n" +
			  "WHERE{" +
			  "?x rdfs:label \"Actor\" .\n"+
			  "?y rdf:type \"rdfs:Class\""+
			  "FILTER(?x = ?y)\n"+
			  "}";
	 public static final String ACTRESS_QUERY = "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +
				"PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
				"SELECT ?x \n" +
				"WHERE{" +
				"?x rdfs:label \"Actress\" .\n"+
				"?y rdf:type \"rdfs:Class\""+
				"FILTER(?x = ?y)\n"+
				"}";
	 public static final String TITLE_QUERY = "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +
			  "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
			  "SELECT ?x \n" +
			  "WHERE{" +
			  "?x rdfs:label \"Title\" .\n"+
			  "?y rdf:type \"rdfs:Class\""+
			  "FILTER(?x = ?y)\n"+
			  "}";
	 public static final String YEAR_QUERY = "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +
				 "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
				 "SELECT ?x \n" +
				 "WHERE{" +
				 "?x rdfs:label \"Year\" .\n"+
				 "?y rdf:type \"rdfs:Class\""+
				 "FILTER(?x = ?y)\n"+
				 "}";
	 public static final String DOMAIN_QUERY = "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +
			   "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
			   "SELECT ?x \n" +
			   "WHERE{" +
			   "?x rdfs:label \"Movie\" .\n"+
			   "?y rdf:type \"rdfs:Class\""+
			   "FILTER(?x = ?y)\n"+
			   "}";
	 
	 public static final String ACTOR_QUERY_TYPE = "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +
			  "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
			  "SELECT ?x \n" +
			  "WHERE{" +
			  "?x rdfs:label \"Actor\" .\n"+
			  "?y rdf:type \"rdfs:Property\""+
			  "FILTER(?x = ?y)\n"+
			  "}"; 
	 public static final String ACTRESS_QUERY_TYPE = "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +
				 "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
				 "SELECT ?x \n" +
				 "WHERE{" +
				 "?x rdfs:label \"Actress\" .\n"+
				 "?y rdf:type \"rdfs:Property\""+
				 "FILTER(?x = ?y)\n"+
				 "}";
	 public static final String TITLE_QUERY_TYPE = "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +
			   "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
			   "SELECT ?x \n" +
			   "WHERE{" +
			   "?x rdfs:label \"Title\" .\n"+
			   "?y rdf:type \"rdfs:Property\""+
			   "FILTER(?x = ?y)\n"+
			   "}";
	 public static final String YEAR_QUERY_TYPE = "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +
			  "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
			  "SELECT ?x \n" +
			  "WHERE{" +
			  "?x rdfs:label \"Year\" .\n"+
			  "?y rdf:type \"rdfs:Property\""+
			  "FILTER(?x = ?y)\n"+
			  "}";
	 public static final String MOVIE_QUERY = "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +
			  "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
			  "SELECT ?x \n" +
			  "WHERE{" +
			  "?x rdfs:label \"Movie\"" +
			  "}";
	 public static RDFNode executeQuery(String queryStr) {
		QuerySolution u; 
		Query query = QueryFactory.create(queryStr);
		QueryExecution qExe = QueryExecutionFactory.sparqlService( FUSEKI_SERVER, query );
		ResultSet results = qExe.execSelect();	
		u = results.next();	
		qExe.close();
		return u.get("x"); 		
	 }
}