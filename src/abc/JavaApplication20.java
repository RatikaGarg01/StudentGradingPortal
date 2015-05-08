package abc;

import com.google.api.client.auth.oauth2.Credential;
import com.google.gdata.client.docs.DocsService;
import com.google.gdata.client.spreadsheet.SpreadsheetService;
import com.google.gdata.data.docs.DocumentListEntry;
import com.google.gdata.data.docs.DocumentListFeed;
import com.google.gdata.data.docs.SpreadsheetEntry;
import com.google.gdata.data.spreadsheet.CellEntry;
import com.google.gdata.data.spreadsheet.CellFeed;
import com.google.gdata.data.spreadsheet.CustomElementCollection;
import com.google.gdata.data.spreadsheet.ListEntry;
import com.google.gdata.data.spreadsheet.ListFeed;
import com.google.gdata.data.spreadsheet.SpreadsheetFeed;
import com.google.gdata.data.spreadsheet.WorksheetEntry;
import com.google.gdata.data.spreadsheet.WorksheetFeed;
import com.google.gdata.util.ServiceException;







// ...
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;
// ...

public class JavaApplication20 {
  // …
	static SpreadsheetService service; 
	static WorksheetFeed worksheetFeed;
    static List<WorksheetEntry> worksheets;
	static  WorksheetEntry worksheet ;
	static CellFeed cellFeed1;
	static URL cellFeedUrl;
	static SpreadsheetFeed feed;
	
	static List<com.google.gdata.data.spreadsheet.SpreadsheetEntry> spreadsheets;

  static void printDocuments(Credential credential) throws Exception, IOException, ServiceException {
    // Instantiate and authorize a new SpreadsheetService object.
System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%5");
     SpreadsheetService service =
            new SpreadsheetService("Aplication-name");
     service.setOAuth2Credentials(credential);
    // Send a request to the Documents List API to retrieve document entries.
    URL SPREADSHEET_FEED_URL = new URL(
        "https://spreadsheets.google.com/feeds/spreadsheets/private/full");
    // Make a request to the API and get all spreadsheets.
    SpreadsheetFeed feed = service.getFeed(SPREADSHEET_FEED_URL,
        SpreadsheetFeed.class);
    List<com.google.gdata.data.spreadsheet.SpreadsheetEntry> spreadsheets = feed.getEntries();
     if (spreadsheets.isEmpty()) {
      System.out.println("No spreadhseets found! ");
    }
com.google.gdata.data.spreadsheet.SpreadsheetEntry spreadsheet = spreadsheets.get(0);
    System.out.println(spreadsheet.getTitle().getPlainText());
    
    
// Get the first worksheet of the first spreadsheet.
    // TODO: Choose a worksheet more intelligently based on your
    // app's needs.
    WorksheetFeed worksheetFeed = service.getFeed(
        spreadsheet.getWorksheetFeedUrl(), WorksheetFeed.class);
    List<WorksheetEntry> worksheets = worksheetFeed.getEntries();
    WorksheetEntry worksheet = worksheets.get(0);

    // Fetch the cell feed of the worksheet.

    
 // Fetch column 4, and every row after row 1.
  URL cellFeedUrl = worksheet.getCellFeedUrl();
  CellFeed cellFeed = service.getFeed(cellFeedUrl, CellFeed.class);
  
  
    for(int a=2;a<=19;a++)
    {
    
    
    for (CellEntry cell : cellFeed.getEntries())
    {
    	
    	//System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
    	
    	boolean f=cell.getTitle().getPlainText().equals("H"+a);

        if (cell.getTitle().getPlainText().equals("H"+a))
        {
        	System.out.println("*******************************************");
          cell.changeInputValueLocal("=SUM(D"+a+"*0.5,E"+a+"*0.5,F"+a+"*0.6,G"+a+"*0.5)");
          
         
          
          System.out.println("");
          cell.update();
        } 
     }
    
    System.out.println(a);
    }
    
    

    
    
    double x=0; 
  URL cellFeedUrl1 = new URI(worksheet.getCellFeedUrl().toString()
      + "?min-row=2&max-row=19&min-col=8&max-col=8").toURL();
  
  
  CellFeed cellFeed1 = service.getFeed(cellFeedUrl1, CellFeed.class);
  
  for (CellEntry cell : cellFeed1.getEntries()) 
  		{

  	x= x+ (Double.parseDouble(cell.getPlainTextContent()));
     
      }
  
  x=x/18;
     
CellEntry cellEntry= new CellEntry (20, 8,"Mean:"+ x+"");
cellFeed1.insert (cellEntry);
  System.out.println("Mean:"+x); 
  
  
  //Standard Deviation
  double sd=0;
  for (CellEntry cell : cellFeed1.getEntries()) 
	{

sd= sd+ ( (x-(Double.parseDouble(cell.getPlainTextContent())) )*  (x-(Double.parseDouble(cell.getPlainTextContent())) )  );



}
  
  sd=Math.sqrt(sd/96);
  CellEntry cellEntry1= new CellEntry (21, 8, "Standard Dev.:"+sd+"");
  cellFeed1.insert (cellEntry1);
    System.out.println("Standard Deviation:"+sd); 
    
    
    
    
    // step size = standard deviation /2;
 
  
    double step_size= sd/2;
    CellEntry cellEntry2= new CellEntry (22, 8, "Step Size:"+step_size+"");
    cellFeed1.insert (cellEntry2);
      System.out.println("StepSize:"+step_size); 
      
      
      //Grading 
      // Mean(+-)step size - BC 
    
      
     
     double f=0;
     String g="";  
     int c=2;
       for (CellEntry cell : cellFeed1.getEntries()) 
 		{

 	f= (Double.parseDouble(cell.getPlainTextContent()));
 	
 	
 	if(f>= (x- step_size ) && f<  (x+ step_size) )
 	{
 		
 		
 		
 		g="BC";
 		
 	   	
     }
 	else if(f>= (x- (3*step_size)) && f<  (x- step_size))
 	{
 		g="C";
 	}
 	
 	else if(f>= (x- (5*step_size)) && f<  (x- (3*step_size)))
 	{
 		g="CD";
 	}
 	else if(f>= (x- (7*step_size)) && f<  (x- (5*step_size)))
 	{
 		g="D";
 	}
 	else if(f <  (x- (7*step_size)))
 	{
 		g="FAIL";
 	}
 	else if(f>= (x+ step_size) && f<  (x+ (3*step_size)))
 	{
 		g="B";
 	}
 	else if(f>= (x+ (3*step_size)) && f<  (x+ (5*step_size)) )
 	{
 		g="AB";
 	}
 	else if(f>= (x+ (5*step_size)))
 	{
 		
 		g="A";
 	}
 	
 	CellEntry cellEntry3= new CellEntry (c, 9, g);
     cellFeed1.insert (cellEntry3);
     c++;
     
 	
 	}
 	
       CellEntry cellEntry4= new CellEntry (24, 8, ("marks < " + (x- (7*step_size)) + "---->  FAIL"));
 		cellFeed1.insert (cellEntry4);
 		System.out.println("marks < " + (x- (7*step_size)) + "---->  FAIL");
   
   CellEntry cellEntry5= new CellEntry (25, 8, ((x- (7*step_size))+" <= marks < " +(x- (5*step_size)) + "---->  D"));
   	cellFeed1.insert (cellEntry5);
   	System.out.println((x- (7*step_size))+" <= marks < " +(x- (5*step_size)) + "---->  D");
     
   CellEntry cellEntry6= new CellEntry (26, 8, ((x- (5*step_size))+" <= marks < " +(x- (3*step_size)) + "---->  CD"));
     	cellFeed1.insert (cellEntry6);
     	System.out.println((x- (5*step_size))+" <= marks < " +(x- (3*step_size)) + "---->  CD");
   CellEntry cellEntry7= new CellEntry (27, 8, ((x- (3*step_size))+" <= marks < " +(x- step_size ) + "---->  C"));
     	cellFeed1.insert (cellEntry7);
       System.out.println((x- (3*step_size))+" <= marks < " +(x- step_size ) + "---->  C");
       
   CellEntry cellEntry8= new CellEntry (28, 8, ((x- step_size )+" <= marks < " +(x+ step_size) + "---->  BC"));
       cellFeed1.insert (cellEntry8);
       System.out.println((x- step_size )+" <= marks < " +(x+ step_size) + "---->  BC");
         
   CellEntry cellEntry9= new CellEntry (29, 8, ((x+ step_size)+" <= marks < " +(x+ (3*step_size)) + "---->  B"));
         cellFeed1.insert (cellEntry9);
         System.out.println(((x+ step_size)+" <= marks < " +(x+ (3*step_size)) + "---->  B"));
   CellEntry cellEntry10= new CellEntry (30, 8, ((x+ (5*step_size))+" <= marks < " +(x+ (7*step_size)) + "---->  AB"));
         cellFeed1.insert (cellEntry10);
         System.out.println((x+ (5*step_size))+" <= marks < " +(x+ (7*step_size)) + "---->  AB");
           
   CellEntry cellEntry11= new CellEntry (31, 8, ((x+ (7*step_size)) +" <= marks  " + "---->  A"));
           cellFeed1.insert (cellEntry11);
           System.out.println((x+ (7*step_size)) +" <= marks  " + "---->  A");

    }
}
  


