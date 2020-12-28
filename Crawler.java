
import java.net.*;
import java.util.*;
import java.io.*;

public class Crawler
{
	public static final String URL_ATTRIBUTE = "href=";
	
	private LinkedList<URLDepthPair> urlList = new LinkedList<URLDepthPair>(); 
	//private LinkedList<URLDepthPair> uncheckedURLList = new LinkedList<URLDepthPair>(); 
	
	
	//args: начальный URL-адрес, максимальная глубина
	public static void main(String[] args)
	{
		if(args.length == 2)
		{
			try
			{
				
				Crawler craw1 = new Crawler();
				URLDepthPair startUrl = new URLDepthPair(args[0], 0);
				craw1.urlList.add(startUrl);
				//craw1.uncheckedURLList.add(startUrl);
				
				for(int i = 0; i < craw1.urlList.size(); i++)
				{
					try
					{
						URL url = new URL(craw1.urlList.get(i).getAddres());
						BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
						while(true)
						{
							String line = reader.readLine();
							if(line == null)
							{
								break;
							}
							//System.out.println(line);
							if(line.indexOf(URL_ATTRIBUTE) != -1)
							{
								line = line.substring(line.indexOf(URL_ATTRIBUTE) + URL_ATTRIBUTE.length() + 1);
								if(line.indexOf('"') != -1)
								{
									line = line.substring(0, line.indexOf('"'));
								}
								if(line.indexOf("'") != -1)
								{
									line = line.substring(0, line.indexOf("'"));
								}
								
								//System.out.println(line);
								if(line.startsWith(URLDepthPair.URL_PREFIX))
								{
									//System.out.println(line);
									if(craw1.urlList.get(i).getDepth() + 1 < Integer.parseInt(args[1]))
									{
										
										craw1.urlList.add(new URLDepthPair(line, craw1.urlList.get(i).getDepth() + 1));
									}
								}
							}
						}
						//System.out.println(i);
					}
					catch(SocketException socketExc)
					{
						System.out.println("SocketException");
						continue;
					}
					catch(IOException ioExc)
					{
						System.out.println("IOException");
						continue;
					}
					
				}
				craw1.getSites();
				//System.out.println("error?");
				
			}
			catch(NumberFormatException numExc)
			{
				System.out.println("usage: java Crawler <URL> <depth>");
			}
			
		}
		else
		{
			System.out.println("usage: java Crawler <URL> <depth>");
		}
	}
	
	//возврат всех пар url-depth, которые были посещены
	public void getSites()
	{
		for(URLDepthPair pair : this.urlList)
		{
			System.out.println(pair.toString());
		}
	}
}
