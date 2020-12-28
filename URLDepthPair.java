
public class URLDepthPair
{
	
	public static final String URL_PREFIX = "http://";
	
	private String url;
	private int depth;
	
	/*
	URL 
	1) метод доступа к ресурсу; 
	2) доменное имя 
	3) путь к файлу 
	4) данные о файле 

	*/
	public URLDepthPair(String addres, int count)
	{
		this.url = addres;
		this.depth = count;
	}
	
	public String toString()
	{
		return url + " depth = " + Integer.toString(depth);
	}
	
	//методы разбиения/работы с URL-адресами:
	
	public String getHost()
	{
		String addres = new String(this.url);
		//System.out.println(addres);
		addres = addres.substring(addres.indexOf("//") + 2);
		addres = addres.substring(0, addres.indexOf("/"));
		return addres;
	}
	
	public String getAddres()
	{
		return this.url;
	}
	
	public int getDepth()
	{
		return this.depth;
	}
}