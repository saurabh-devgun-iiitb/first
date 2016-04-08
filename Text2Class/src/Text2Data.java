import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.lang.Exception;

public class Text2Data {

	public static void main(String[] args) throws Exception {
		File f= new File("C:\\Data\\DRM\\Test project\\Project\\Text2Class\\src\\spec.sc");
		//getNextLexiom(f);
		FileInputStream fis = new FileInputStream(f);
		DataInputStream dis = new DataInputStream(fis);
		String s = "";
		//do{
		s = getNextLexiom(dis);
		//}while(s!="");
		
		if(s.equals("STATECHART"))
		{
			s = getNextLexiom(dis);
			while(s.equals("STATE"))
			{
				getNextLexiom(dis);	//unnecessary s1 state, do nth with that
				s = getNextLexiom(dis);
				//create a node
				while(!s.equals("STATE") && !s.equals("TRANSITION"))
				{	
					System.out.println(s);
					switch(s)
					{
						case "NAME":
							s = getNextLexiom(dis);
							System.out.println("Name:"+s);
							//add this name into Node object
							break;
						case "DESC":
							s = getNextLexiom(dis);
							System.out.println("desc:"+s);
							break;
						case "INPUT":
							s = getNextLexiom(dis);
							System.out.println("input:"+s);
							break;
						case "CLICKABLE":
							s = getNextLexiom(dis);
							System.out.println("clickable:"+s);
							//ADD this to arraylist of clickable in Node object
							break;
						default:
							System.out.println("Error Somewhere in State");
					}	
					s = getNextLexiom(dis);
				}
			}
			
			System.out.println(s);
			
			while(s.equals("TRANSITION"))
			{
				getNextLexiom(dis);	//unnecessary s1 state, do nth with that
				s = getNextLexiom(dis);
				//create an Edge Object
				while(!s.equals("STATE") && !s.equals("TRANSITION"))
				{	
					System.out.println(s);
					switch(s)
					{
						case "DESC":
							s = getNextLexiom(dis);
							System.out.println("desc:"+s);
							break;
						case "SRC":
							s = getNextLexiom(dis);
							System.out.println("Src:"+s);
							//add this name into Node object
							break;
						case "DEST":
							s = getNextLexiom(dis);
							System.out.println("Dest:"+s);
							//add this name into Node object
							break;
						
						case "TRIGGER":
							s = getNextLexiom(dis);
							System.out.println("Trigger:"+s);	
							break;
						case "GUARD":
							s = getNextLexiom(dis);
							System.out.println("Guard:"+s);
							//ADD TO TRIGGER ARRAYLIST
							break;
						case "ACTION":
							s = getNextLexiom(dis);
							System.out.println("Action:"+s);
							//ADD this to arraylist of clickable in Node object
							break;
						default:
							System.out.println("Error somewhere");
					}	
					s = getNextLexiom(dis);
				}
			}
		}
		
		else
			System.out.println("Syntax Error: STATECHART expected");
	}
	
	public static String getNextLexiom(DataInputStream dis) throws Exception	//return String and line and } as a struct/class
	{
		int r = dis.read();
		//int line = 0;
		//int i=0;
		char c = (char) r;
		//System.out.println(""+r+c+i);
		while(c==' '||r==13||r==10||r==9||c=='{'||c=='}')	
		{
			r = dis.read();//i++;
			/*if(r==13)
				line++;*/
			c = (char)r;
		}
		//System.out.println(r+" "+i);
		String s = "";
		
		if(r == 34)
		{	
			r = dis.read();
			c = (char)r;
			while(r!=34)
			{
				s += c;
				r = dis.read();
				c = (char)r;
			}
			return s;
		}
		while(c != ' ' && c != '\n' && c != '\t' && c != '{' && c!='}' && c!=':'){
			s += c;
			c = (char)dis.read();
		}
		return s;
	}
}
