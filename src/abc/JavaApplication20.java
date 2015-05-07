package abc;

import com.google.api.client.auth.oauth2.Credential;
import com.google.gdata.client.docs.DocsService;
import com.google.gdata.client.spreadsheet.SpreadsheetService;
import com.google.gdata.data.docs.DocumentListEntry;
import com.google.gdata.data.docs.DocumentListFeed;
import com.google.gdata.data.docs.SpreadsheetEntry;
import com.google.gdata.data.spreadsheet.CellEntry;
import com.google.gdata.data.spreadsheet.CellFeed;
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
	WorksheetFeed worksheetFeed;
	 List<WorksheetEntry> worksheets;
	static  WorksheetEntry worksheet ;
	static CellFeed cellFeed1;
	 URL cellFeedUrl;
	
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
    URL cellFeedUrl = worksheet.getCellFeedUrl();
    CellFeed cellFeed = service.getFeed(cellFeedUrl, CellFeed.class);
    
 // Fetch column 4, and every row after row 1.
    URL cellFeedUrl1 = new URI(worksheet.getCellFeedUrl().toString()
        + "?min-row=2&max-row=2&min-col=4&max-col=7").toURL();
    CellFeed cellFeed1 = service.getFeed(cellFeedUrl1, CellFeed.class);
    double x=0;
    for (CellEntry cell : cellFeed1.getEntries()) {
//        System.out.println(cell.getPlainTextContent());
    	 //System.out.println("________________________________________________________");
         x=  x  +  Double.parseDouble(cell.getPlainTextContent());
         //System.out.println(x);
        }
    System.out.println(x);
    total(x);
    
//    URL listFeedUrl = worksheet.getListFeedUrl();
//    ListFeed listFeed = service.getFeed(listFeedUrl, ListFeed.class);
//
//    // TODO: Choose a row more intelligently based on your app's needs.
//    ListEntry row = listFeed.getEntries().get(0);
//
//    // Update the row's data.
//    
//    row.getCustomElements().setValueLocal("Agg_Sum", x+"");
//
//    // Save the row using the API.
//    row.update();
    
    
    
 

  }
  
  public static void total(double x) throws Exception, IOException, ServiceException
  {
	  
	  
	  
	  System.out.println("$#$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");

	  CellEntry cellEntry= new CellEntry (2, 8,  (x+"") );
	  cellFeed1.insert (cellEntry);
	  
	  System.out.println("$#$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
	  
//	  cellEntry= new CellEntry (1, 2, "headline2");
//	  cellFeed.insert (cellEntry);
	  
	  
//	  
//	  com.google.gdata.data.spreadsheet.SpreadsheetEntry spreadsheet = spreadsheets.get(0);
//	    System.out.println(spreadsheet.getTitle().getPlainText());
//
//	    // Get the first worksheet of the first spreadsheet.
//	    // TODO: Choose a worksheet more intelligently based on your
//	    // app's needs.
//	    WorksheetFeed worksheetFeed = service.getFeed(
//	        spreadsheet.getWorksheetFeedUrl(), WorksheetFeed.class);
//	    List<WorksheetEntry> worksheets = worksheetFeed.getEntries();
//	    WorksheetEntry worksheet = worksheets.get(0);
//
//	  
//	  
//	  
//	  System.out.println("$#$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
//	  
//	  URL listFeedUrl = worksheet.getListFeedUrl();
//	    ListFeed listFeed = service.getFeed(listFeedUrl, ListFeed.class);
//	    
//	    System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%555");
//	    
//	    // TODO: Choose a row more intelligently based on your app's needs.
//	    ListEntry row = listFeed.getEntries().get(0);
//
//	    System.out.println("$#******************************************");
//	    // Update the row's data.
//	   
//	    row.getCustomElements().setValueLocal("Agg_Sum", x+"");
//
//	    // Save the row using the API.
//	    row.update();
//	    
//	    System.out.println("row updated");
  }
}

