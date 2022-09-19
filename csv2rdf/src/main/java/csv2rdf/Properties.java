package csv2rdf;

import java.io.FileWriter;
import java.io.Writer;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.rdf.model.Resource;

import org.apache.jena.vocabulary.RDF;
import org.apache.jena.vocabulary.RDFS;

public class Properties {
	 RDFNode actor;
	 RDFNode actress;
	 RDFNode year;
	 RDFNode title;
	 RDFNode domain;
	 public static final String TEST = "http://test.org/label/";
	 public static final String YEAR = "YearOfPublication";
	 public static final String TITLE = "MovieTitle";
	 public static final String ACTOR = "ActorInTheMovie";
	 public static final String ACTRESS = "ActressInTheMovie";
	 public static final String PROPERTY = "rdfs:Property";
	 public static final String YEAR_LABEL = "Year";
	 public static final String TITLE_LABEL = "Title";
	 public static final String ACTOR_LABEL = "Actor";
	 public static final String ACTRESS_LABEL = "Actress";
	 public static final String FILE_PATH_OUTPUT_PROPERTY = "INSERT PATH HERE\\Properties.rdf";
	 
	 public static void main(String[] args) {	        
		 Properties properties = new Properties();
		 properties.read();
		 properties.convert();
	 }
	 
   private void convert(){           
       		Model mymodel = ModelFactory.createDefaultModel();       	
       	    
       		Resource propertyYear = mymodel.createResource(TEST+YEAR);
           	mymodel.add(propertyYear, RDF.type, PROPERTY);
           	mymodel.add(propertyYear, RDFS.label, YEAR_LABEL);
           	mymodel.add(propertyYear, RDFS.domain, domain.toString());
           	mymodel.add(propertyYear, RDFS.range, year.toString());
           	
           	Resource propertyTitle = mymodel.createResource(TEST+TITLE);
           	mymodel.add(propertyTitle, RDF.type, PROPERTY);
           	mymodel.add(propertyTitle, RDFS.label, TITLE_LABEL);
           	mymodel.add(propertyTitle, RDFS.domain, domain.toString());
           	mymodel.add(propertyTitle, RDFS.range, title.toString());
           	
           	Resource propertyActor = mymodel.createResource(TEST+ACTOR);
           	mymodel.add(propertyActor, RDF.type, PROPERTY);
           	mymodel.add(propertyActor, RDFS.label, ACTOR_LABEL);
           	mymodel.add(propertyActor, RDFS.domain, domain.toString());
           	mymodel.add(propertyActor, RDFS.range, actor.toString());
           	
           	Resource propertyActress = mymodel.createResource(TEST+ACTRESS);
           	mymodel.add(propertyActress, RDF.type, PROPERTY);
           	mymodel.add(propertyActress, RDFS.label, ACTRESS_LABEL);
           	mymodel.add(propertyActress, RDFS.domain, domain.toString());
           	mymodel.add(propertyActress, RDFS.range, actress.toString());		
       	try 
       	{
       		Writer wr = new FileWriter(FILE_PATH_OUTPUT_PROPERTY);
       		mymodel.write(wr,"RDF/XML");
       	}
       	catch(Exception e) 
       	{
       		e.printStackTrace();
       	}
   }
   
	private void read() {
					
		actor = QueryUtil.executeQuery(QueryUtil.ACTOR_QUERY); 
		actress = QueryUtil.executeQuery(QueryUtil.ACTRESS_QUERY);
		title = QueryUtil.executeQuery(QueryUtil.TITLE_QUERY);
		year = QueryUtil.executeQuery(QueryUtil.YEAR_QUERY);
		domain = QueryUtil.executeQuery(QueryUtil.DOMAIN_QUERY);
	}
}
