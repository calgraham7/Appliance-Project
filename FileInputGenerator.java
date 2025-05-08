import java.util.Scanner;
import java.util.StringTokenizer;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.io.BufferedWriter;

public class FileInputGenerator {
	public static int LOCATION_COUNT=100, 
				APPLIANCES_PER_LOCATION_LOW=15, APPLIANCES_PER_LOCATION_HIGH=20;
	public static class Appliance {
		public String name;
		public int onW, offW; 
		public double probOn; 
		public boolean smart; 
		public double probSmart;

		public Appliance (String n, int on, int off, double pOn, boolean s, double pSmart)
		{ name=n; onW=on; offW=off; probOn=pOn; smart=s; probSmart=pSmart; }

		public String toString () {
			return name + "," + onW + "," + offW + "," + probOn + "," + smart + "," + probSmart;
		}
	}

	public static void main( String [] args ) throws IOException {
		Appliance [] app = new Appliance[100];  // default 100 possible appliance types
		File inputFile = new File( "ApplianceDetail.txt" );
		Scanner scan = new Scanner( inputFile );
		int count=0;
		while ( scan.hasNext( ) ) {
			StringTokenizer stringToken = new StringTokenizer(scan.nextLine());
			app[count] = new Appliance(stringToken.nextToken(","),
						Integer.parseInt(stringToken.nextToken(",")),
						Integer.parseInt(stringToken.nextToken(",")),
						Double.parseDouble(stringToken.nextToken(",")),
						Boolean.parseBoolean(stringToken.nextToken(",")),
						Double.parseDouble(stringToken.nextToken()));
			count++;
		}
/*
output a comma delimited file
the location (represented by an 8 digit numeric account number)
type (string)
"on" wattage used (integer)
"off" wattage used (integer)
probability (floating point, i.e..01=1%) that the appliance is "on" at any time
smart (boolean) 
Smart appliances (if "on") power reduction percent (floating point, i.e..33=33%).
*/
		try
		{
			FileWriter fw = new FileWriter( "output.txt", false);
			BufferedWriter bw = new BufferedWriter( fw );
			for (long location=1;location<=LOCATION_COUNT ;location++ ) {
				int applianceCount=(int)(Math.random()
					* (APPLIANCES_PER_LOCATION_HIGH - APPLIANCES_PER_LOCATION_LOW+1))
					+ APPLIANCES_PER_LOCATION_LOW;
				for (int i=1;i<=applianceCount;i++ ){
					int index=(int)(Math.random()*count);  // pick an appliance randomly
					bw.write(String.valueOf(10000000+location));
					bw.write( "," );		
					bw.write(app[index].name);
					bw.write( "," );		
					bw.write(String.valueOf(app[index].onW));
					bw.write( "," );		
					bw.write(String.valueOf(app[index].offW));
					bw.write( "," );		
					bw.write(String.valueOf(app[index].probOn));
					bw.write( "," );		
					bw.write(String.valueOf(app[index].smart));
					bw.write( "," );
					bw.write(String.valueOf(app[index].probSmart));
					bw.newLine( );
					bw.flush();
				}
			}
		}
		catch( IOException ioe )
		{
			ioe.printStackTrace( );
		}
	}
}
