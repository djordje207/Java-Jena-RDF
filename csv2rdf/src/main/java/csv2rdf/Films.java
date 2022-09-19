package csv2rdf;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.rdf.model.Resource;

import org.apache.jena.vocabulary.RDF;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Films {
	 public static final String FILE_PATH_INPUT_FILMS = "INSERT PATH HERE\\Movie.csv";
	 public static final String FILE_PATH_OUTPUT_FILMS = "INSERT PATH HERE\\Movies.rdf";
	 List<String> list;
	 RDFNode actor;
	 RDFNode actress;
	 RDFNode year;
	 RDFNode title;
	 RDFNode movie;
	 List<RDFNode> listActor;
	 List<RDFNode> listActress;
	 List<RDFNode> listYear;
	 List<RDFNode> listTitle;
	 RDFNode actorType;
	 RDFNode actressType;
	 RDFNode yearType;
	 RDFNode titleType;
	 RDFNode a;
	 RDFNode b;
	 RDFNode c;
	 RDFNode d;
	 int i=0;
	 
	 public static void main(String[] args) {
			
		 Films films = new Films();
		 films.read();
		 films.convert();
		}	 
	 private void convert(){
           
       		Model mymodel = ModelFactory.createDefaultModel(); 
       	    
       		for(int i = 0;i<listYear.size();i++) {
	       		Resource film = mymodel.createResource(QueryUtil.TEST+UUID.randomUUID().toString());	           	
	           	mymodel.add(film, mymodel.createProperty(yearType.toString()), listYear.get(i).toString());
	           	mymodel.add(film, mymodel.createProperty(titleType.toString()), listTitle.get(i).toString());
	           	mymodel.add(film, mymodel.createProperty(actorType.toString()), listActor.get(i).toString());
	           	mymodel.add(film, mymodel.createProperty(actressType.toString()), listActress.get(i).toString());
	           	mymodel.add(film, RDF.type, movie.toString());           	
       		}	
       	try 
       	{
       		Writer wr = new FileWriter(FILE_PATH_OUTPUT_FILMS);
       		mymodel.write(wr,"RDF/XML");
       	}
       	catch(Exception e) 
       	{
       		e.printStackTrace();
       	}
	 }
   
	 private void read() {
				
		list = new ArrayList<String>();
		listYear = new ArrayList<RDFNode>();
		listTitle = new ArrayList<RDFNode>();
		listActor = new ArrayList<RDFNode>();
		listActress = new ArrayList<RDFNode>();
		
		File file = new File(FILE_PATH_INPUT_FILMS);

		actor = QueryUtil.executeQuery(QueryUtil.ACTOR_QUERY); 
		actress = QueryUtil.executeQuery(QueryUtil.ACTRESS_QUERY);
		title = QueryUtil.executeQuery(QueryUtil.TITLE_QUERY);
		year = QueryUtil.executeQuery(QueryUtil.YEAR_QUERY);
 
		actorType = QueryUtil.executeQuery(QueryUtil.ACTOR_QUERY_TYPE); 
		actressType = QueryUtil.executeQuery(QueryUtil.ACTRESS_QUERY_TYPE);
		titleType = QueryUtil.executeQuery(QueryUtil.TITLE_QUERY_TYPE);
		yearType = QueryUtil.executeQuery(QueryUtil.YEAR_QUERY_TYPE);
		movie = QueryUtil.executeQuery(QueryUtil.MOVIE_QUERY);
		
		if(file.exists())
		{
			try {
                i = 0;
				FileReader fr = new FileReader(file);
				@SuppressWarnings("resource")
				BufferedReader bf = new BufferedReader(fr);
				String data = "";
				while((data = bf.readLine())!= null) {
						if(i != 0) {
							String datasplit[] = data.split(",");
							for(int j=0;j<datasplit.length;j++) {
								if(j==0) {									
									String queryYear = "";
									queryYear = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
											   	 "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +
											     "SELECT ?x \n" +
											     "WHERE{" +
											     "?x rdf:type \""+year.toString()+"\" .\n"+
											     "?y rdfs:label \""+datasplit[j]+"\"\n" +
											     "FILTER(?x = ?y)\n"+
											     "} LIMIT 1"; 
									
									a = QueryUtil.executeQuery(queryYear);
									listYear.add(a);
								}
								if(j==1) {
									String queryTitle = "";
									queryTitle = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
											   	 "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +
											     "SELECT ?x \n" +
											     "WHERE{" +
											     "?x rdf:type \""+title.toString()+ "\" .\n"+
											     "?y rdfs:label \"" +datasplit[j]+ "\"\n" +
											     "FILTER(?x = ?y)\n"+
											     "}"; 
									
									b = QueryUtil.executeQuery(queryTitle);
									listTitle.add(b);
								}
								if(j==2) {
									String queryActor = "";
									queryActor = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
											   	 "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +
											     "SELECT ?x \n" +
											     "WHERE{" +
											     "?x rdf:type \""+actor.toString()+ "\" .\n"+
											     "?y rdfs:label \"" +datasplit[j]+ "\"\n" +
											     "FILTER(?x = ?y)\n"+
											     "}"; 
									
									c = QueryUtil.executeQuery(queryActor);
									listActor.add(c);
								}
								if(j==3) {
									String queryActress = "";
									queryActress = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
											   	 "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +
											     "SELECT ?x \n" +
											     "WHERE{" +
											     "?x rdf:type \""+actress.toString()+ "\" .\n"+
											     "?y rdfs:label \"" +datasplit[j]+ "\"\n" +
											     "FILTER(?x = ?y)\n"+
											     "}"; 
									
									d = QueryUtil.executeQuery(queryActress);
									listActress.add(d);
								}
							}							 
						}
						i++;
				}	
			}
			catch( IOException io)
			{
				io.printStackTrace();
			}
		}
   }
}
