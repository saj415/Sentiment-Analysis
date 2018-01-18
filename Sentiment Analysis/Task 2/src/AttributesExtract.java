import java.io.*;

class AttributesExtract
{
public static void main(String args[])
{
try
{
// Open the text file to be read and the text file to write the extracted attributes
BufferedReader br=new BufferedReader(new FileReader("Tagged_100_Reviews.txt"));
BufferedWriter br1=new BufferedWriter(new FileWriter("Attributes_Extract.txt", true));
String s;
//Read the entire text file line by line
while((s=br.readLine())!=null)
 {
	for(int i=0; i<s.length(); i++)
	 {
		for(int j=i+1; j<s.length(); j++)
		 {
			//Look for particular substrings such as "NN", "NNS", "NNP", "NNPS" to identify the Nouns
			if(((s.substring(i,j)).compareTo("NN "))==0||((s.substring(i,j)).compareTo("NNS"))==0||((s.substring(i,j)).compareTo("NNP "))==0||					((s.substring(i,j)).compareTo("NNPS"))==0)
			 {
				for(int k=i; k>1; k--)
				 {
					if(s.charAt(k)==32)
					 {
						//Extract the particular Attribute when found and write it to the text file	
						System.out.println(s.substring(k+1,j)); //Extracting and printing the found attribute
						br1.write(s.substring(k+1,j)); //Writing the attribute to the file
						br1.newLine(); 
						break; //Break out once the Attribute is found and start looking for new Attributes
					 }
				 }
			 }
		 }
	 }
 }//end of while loop
br.close(); //Close the text file being read 
br1.close(); //Close the text file being written

}//end of try block

//Catch any Input/Output Exceptions
catch(IOException e)
{
e.printStackTrace();
}

}//end of main
}//end of class

 