package csv2rdf;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import org.apache.jena.rdf.model.Resource;
import org.apache.jena.vocabulary.RDF;
import org.apache.jena.vocabulary.RDFS;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;

import java.util.ArrayList;
import java.util.List;


public class Classes {
	 public static final String FILE_PATH_INPUT_CLASS = "INSERT PATH HERE\\Class.csv";
	 public static final String FILE_PATH_OUTPUT_CLASS = "INSERT PATH HERE\\Classes.rdf";
	 List<String> list;
	 public static final String CLASS = "rdfs:Class";
	
	 public static void main(String[] args) {		
		Classes classes = new Classes();
		classes.read();
		classes.convert();
	 }
	
	 private void convert(){            
        	Model mymodel = ModelFactory.createDefaultModel();
        	
        	for(int i = 0;i<list.size();i++) 
        	{
        		Resource classRes = mymodel.createResource(QueryUtil.TEST+list.get(i));
            	mymodel.add(classRes, RDF.type, CLASS);
            	mymodel.add(classRes, RDFS.label, list.get(i));
        	}
        	try 
        	{
        		Writer wr = new FileWriter(FILE_PATH_OUTPUT_CLASS);
        		mymodel.write(wr,"RDF/XML");
        	}
        	catch(Exception e) 
        	{
        		e.printStackTrace();
        	}
    }
    private void read() {
    		
    		list = new ArrayList<String>();    		
    		File file = new File(FILE_PATH_INPUT_CLASS);
    		if(file.exists())
    		{
    			try {
    				FileReader fr = new FileReader(file);
    				@SuppressWarnings("resource")
    				BufferedReader bf = new BufferedReader(fr);
    				String data = "";
    				while((data = bf.readLine())!= null)
    					list.add(data); 				
    			}
    			catch( IOException io)
    			{
    				io.printStackTrace();
    			}    			
    		}
            else
            	System.out.print("File doesn't exist");
    }
}
