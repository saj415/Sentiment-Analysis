import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Counter{
 private String filename;
 private LinkedList<String> keywordsList;
 private TreeMap<String, Integer> freqMap;

 Counter(String filename){
  this.filename=filename;
  freqMap=new TreeMap<String,Integer>();
  keywordsList=new LinkedList<String>();
 }
 
 public void readWords(){
  Pattern pattern=Pattern.compile("\\W+");  
  try {
   
   FileReader fr=new FileReader(filename);
   BufferedReader br = new BufferedReader(fr);
   String strLine;
   while((strLine=br.readLine())!=null){
    //split a line by spaces so we get words
    String[] words=strLine.split("[ ]+");
    for(String word:words){
     //remove all symbols except underscore
     Matcher mat=pattern.matcher(word);
     word=mat.replaceAll("");
     //add words to the list
     keywordsList.add(word.toLowerCase());
    }
   }
   
   br.close();
  } catch (Exception e) {
   
   e.printStackTrace();
  }
 }
 
 
 public void countWords(){
  int count=1;
  String word="";
  for(int i=0;i<keywordsList.size();i++){
   word=keywordsList.get(i);
   for(int j=i+1;j<keywordsList.size();j++){
    if(word.equals(keywordsList.get(j))){
     count++; //increase the number of duplicate words    
    }
   }
   //add the word and its frequency to the TreeMap
   addToMap(word,count);
   //reset the count variable
   count=1;
  }
  
 }
 
 public void addToMap(String word, int count){
  //place keyword and its frequency in TreeMap
  if(!freqMap.containsKey(word) && word.length()>=1){ 
   freqMap.put(word, count);
  }
  
 } 
 
 
 public void showResult(){
try{
  BufferedWriter br1=new BufferedWriter(new FileWriter("Attributes.txt", true));
  BufferedWriter br2=new BufferedWriter(new FileWriter("Frequency.txt", true));
  Set<String> keys=freqMap.keySet();
  int numWord=keys.size();
  Iterator<String> iterator=keys.iterator();
  while(iterator.hasNext()){
   String word=iterator.next();
   int count=freqMap.get(word); 
   br1.write(word);
   br1.newLine();
   br2.write(""+count);
   br2.newLine(); 
   System.out.format("%-20s%-5d%-2s\n", word,count,100*count/numWord+"%");
  }
   br1.close();
   br2.close();
  }catch (Exception e) {
   
   e.printStackTrace();
  }
 }
 
 public void processCounting(){
  Thread backprocess=new Thread(){
   public void run(){    
    readWords();
    countWords();
    showResult();
   }
  };
  backprocess.start();
 }
 
 
 
}

public class CountAttributesFrequency{
 
 public static void main(String[] args){
    if(args.length>0){
     Counter counter=new Counter(args[0]);
     counter.processCounting();
    }
    else
     System.out.println("No such file name");
  }
 
}