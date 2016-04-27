package url;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	public Main() {

	}
	public static void main(String args[]) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Skriv en url: ");
		String myURL = scan.next();
		List<URL> URLlist = new ArrayList<URL>();
		try {
			URL url = new URL(myURL);

			LinkGetter lg = new LinkGetter();
			List<String> list = lg.getLinks(url.toString());
			int i=1;
			for (String s : list) {
				if (s.endsWith(".pdf")) {
					URL u = new URL(s);
					URLlist.add(u);
				//	String name = u.toString();
					download(s,"file"+i+".pdf");
					i++;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		scan.close();
		for (URL url : URLlist) {
			System.out.println(url.toString());
		}
	}
//	public URL getURL(){
//		return url;
//	}
	public static void download(String u,String filename){
		try{
			System.out.println("opening connection");
			URL url = new URL(u);
			InputStream in = url.openStream();
			
			FileOutputStream fos = new FileOutputStream(new File(filename));

			System.out.println("reading from resource and writing to file...");
			int length = -1;
			byte[] buffer = new byte[1024];// buffer for portion of data from connection
			while ((length = in.read(buffer)) > -1) {
			    fos.write(buffer, 0, length);
			}
			fos.close();
			in.close();
			System.out.println("File downloaded");
			
			
//			URL url = new URL(u);
//		InputStream in = url.openStream();
//		Files.copy(in, Paths.get("someFile.pdf"), StandardCopyOption.REPLACE_EXISTING);
//		in.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
}


