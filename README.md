# Java-RDF-project

The purpose of this project is modeling of flexible and expandable data model from input tabular data. RDF/XML is one of the most popular RDF formats of serialization for data storage and transmission.
Before running:
- Download latest jena-fuseki (https://jena.apache.org/download/index.cgi) in order to be able to run local fuseki server (usually localhost:3030). Create dataset on local fuseki server
- Open this project as existing maven project
- Change the routes of FILE_INPUTs and FILE_OUTPUTs in the following files: Classes.java, Properties.java, Values.java, Films.java. In other words, put the path to CLass.csv and Movie.csv files which you download from here and put the path to desired destination for rdf files which will be generated when running code.
- Change the name of dataset in QueryUtil.java (in this code the name is "test" (line 12), it is important that name is the same as one of the dataset created on localhost:3030)
The way to run the project:
1) run "Classes.java" file
2) upload file "Classes.rdf" to previously created dataset
3) run "Properties.java" file
4) upload file "Properties.rdf" to previously created dataset
5) run "Values.java" file
6) upload file "Values.rdf" to previously created dataset
7) run "Films.java" file
8) upload file "Films.rdf" to previously created dataset

 ![image](https://user-images.githubusercontent.com/113367561/191038657-d506b993-41e3-4cc9-9968-37ae34b34902.png)

Now that there are triples in dataset, it is possible to run some queries which will give us some information about movies:

![image](https://user-images.githubusercontent.com/113367561/191038733-67ec5429-c1b9-4822-9a98-e8a1a814dcbc.png)
![image](https://user-images.githubusercontent.com/113367561/191038747-58e71a14-5269-4994-8c7f-ce1362ebb7f4.png)
