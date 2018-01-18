import java.io.*;

class AttributesOpinionsExtract
{
public static void main(String args[])
{
try
{
// Open the text file to be read and the text file to write the extracted attributes-opinions pair 
BufferedReader br=new BufferedReader(new FileReader("Tagged_100_Reviews.txt"));
BufferedWriter br1=new BufferedWriter(new FileWriter("Attributes_Opinions_Extract.txt", true));
String s;
//Read the entire text file line by line
while((s=br.readLine())!=null)
 {
	for(int i=0; i<s.length(); i++)
	 {
		for(int j=i+1; j<s.length(); j++)
		 {
			//Look for particular substrings such as "JJ", "JJS", "JJR" to identify the Adjectives for each Review
			if(((s.substring(i,j)).compareTo("JJ "))==0||((s.substring(i,j)).compareTo("JJS"))==0||((s.substring(i,j)).compareTo("JJR"))==0)
			 {
				for(int k=i; k>1; k--)
				 {
					if(s.charAt(k)==32)
					 {
						//Extract the particular Opinion when found and write it to the text file	
						br1.write(s.substring(k+1,j)+" ");
						System.out.println(s.substring(k+1,j)+" "); //Print the particular Adjective to the console when found
						break; //Break out once the Opinion is found and start looking for new Opinions
					 }
				 }
			 }
			//Look for particular substrings such as "NN", "NNS", "NNP", "NNPS" to identify the Nouns for each Review
			if(((s.substring(i,j)).compareTo("NN "))==0||((s.substring(i,j)).compareTo("NNS"))==0||((s.substring(i,j)).compareTo("NNP "))==0||					((s.substring(i,j)).compareTo("NNPS"))==0)
			 {
				for(int k=i; k>1; k--)
				 {
					if(s.charAt(k)==32)
					 {
						//Extract the particular Attribute when found and write it to the text file
						br1.write(s.substring(k+1,j)+" ");
						System.out.println(s.substring(k+1,j)+" "); //Printing the particular Noun to the console when found
						break; //Break out once the Attribute is found and start looking for new Attributes
					 }
				 }
			 }
		 }
	 }
br1.newLine(); 
}// end of while loop

br.close(); //Close the text file being read 
br1.close(); //Close the text file being written

} //end of try block 

//Catch any Input/Output Exceptions
catch(IOException e)
{
e.printStackTrace();
}

}//end of main
}//end of class

 