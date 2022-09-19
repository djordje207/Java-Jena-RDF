package csv2rdf;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.vocabulary.RDF;
import org.apache.jena.vocabulary.RDFS;
import java.util.List;
import java.util.UUID;

public class Values {
	 public static final String FILE_PATH_INPUT_VALUES = "C:\\INSERT PATH HERE\\Movie.csv";
	 public static final String FILE_PATH_OUTPUT_VALUES = "C:\\INSERT PATH HERE\\Values.rdf";
	 List<String> list;
	 List<String> listActor;
	 List<String> listActress;
	 List<String> listYear;
	 List<String> listTitle;
	 RDFNode actor;
	 RDFNode actress;
	 RDFNode year;
	 RDFNode title;

	 public static void main(String[] args) {
			
		 Values values = new Values();
		 values.read();
		 values.convert();
		}
	 
   private void convert(){
           
       		Model mymodel = ModelFactory.createDefaultModel();       	
       	    
       		for(int i = 0;i<listYear.size();i++) {
	       		Resource propertyYear = mymodel.createResource(QueryUtil.TEST+UUID.randomUUID().toString());
	           	mymodel.add(propertyYear, RDF.type, year.toString());
	           	mymodel.add(propertyYear, RDFS.label, listYear.get(i));
       		}
       		for(int i = 0;i<listTitle.size();i++) {
	           	Resource propertyTitle = mymodel.createResource(QueryUtil.TEST+UUID.randomUUID().toString());
	           	mymodel.add(propertyTitle, RDF.type, title.toString());
	           	mymodel.add(propertyTitle, RDFS.label, listTitle.get(i));
       		}
	           	for(int i = 0;i<listActor.size();i++) {
	           	Resource propertyActor = mymodel.createResource(QueryUtil.TEST+UUID.randomUUID().toString());
	           	mymodel.add(propertyActor, RDF.type, actor.toString());
	           	mymodel.add(propertyActor, RDFS.label, listActor.get(i));
       		}
	           	for(int i = 0;i<listActress.size();i++) {
	           	Resource propertyActress = mymodel.createResource(QueryUtil.TEST+UUID.randomUUID().toString());
	           	mymodel.add(propertyActress, RDF.type, actress.toString());
	           	mymodel.add(propertyActress, RDFS.label, listActress.get(i));
       		}
       	try 
       	{
       		Writer wr = new FileWriter(FILE_PATH_OUTPUT_VALUES);
       		mymodel.write(wr,"RDF/XML");
       	}
       	catch(Exception e) 
       	{
       		e.printStackTrace();
       	}
   }
   
   private void read() {
		int i=0;
		
		list = new ArrayList<String>();
		listYear = new ArrayList<String>();
		listTitle = new ArrayList<String>();
		listActor = new ArrayList<String>();
		listActress = new ArrayList<String>();
		
		File file = new File(FILE_PATH_INPUT_VALUES);
		if(file.exists())
		{
			try {
                              
				FileReader fr = new FileReader(file);
				@SuppressWarnings("resource")
				BufferedReader bf = new BufferedReader(fr);
				String data = "";
				while((data = bf.readLine())!= null) {
						if(i != 0) {
							String datasplit[] = data.split(",");
							for(int j=0;j<datasplit.length;j++) {
								if(j==0)
									if(!listYear.contains(datasplit[j]))
										listYear.add(datasplit[j]);
								if(j==1)
									if(!listTitle.contains(datasplit[j]))
										listTitle.add(datasplit[j]);
								if(j==2)
									if(!listActor.contains(datasplit[j]))
										listActor.add(datasplit[j]);
								if(j==3)
									if(!listActress.contains(datasplit[j]))
										listActress.add(datasplit[j]);
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
		actor = QueryUtil.executeQuery(QueryUtil.ACTOR_QUERY); 
		actress = QueryUtil.executeQuery(QueryUtil.ACTRESS_QUERY);
		title = QueryUtil.executeQuery(QueryUtil.TITLE_QUERY);
		year = QueryUtil.executeQuery(QueryUtil.YEAR_QUERY);
   }
}