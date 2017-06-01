import java.util.ArrayList;

public class Test {
	
	String p[]={"sid","bla","calm","clever"};
	ArrayList<String> as = new ArrayList<String>();
	Test()
	{
		as.add("sid");
		as.add("bla");
		as.add("calm");
		as.add("clever");
		for(int i=0;i<p.length;i++)
		{
			if(as.contains(p[i]))
				System.out.println("yes");
		}
	}
	public static void main(String asd[])
	{
		new Test();
	}
}
