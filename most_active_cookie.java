import java.io.*;
import java.util.*;
public class most_active_cookie {
    static HashMap<String,Integer> datemaxpair=new HashMap<>();// holds the max cookies of each day


    public static HashMap<String,ArrayList<Cookie>> readfileintomap(String filepath){
        String line = "";
        HashMap<String,ArrayList<Cookie>> hmap=new HashMap<>();
        
    try {
      //parsing a CSV file into BufferedReader class constructor  
      BufferedReader br = new BufferedReader(new FileReader(filepath));
      br.readLine();
      while ((line = br.readLine()) != null)
      //returns a Boolean value  
      {
        String[] array = line.split(","); //parse the line with ',' as delimiter
        String[] time=array[1].split("T"); 
        String keyformap=time[0];    // take the date out as a key for the mapping
          
        if (hmap.containsKey(keyformap)) {  // if map contains the date look through the arraylist of cookies
            ArrayList<Cookie> list=hmap.get(keyformap);
           boolean flag=false;
            
         for (int i=0;i<list.size();i++){
              if (list.get(i).getid().equals(array[0])){ // if another cookie is of the same id is found increase frequency by 1
                flag=true;
                list.get(i).raisefrequency();
                datemaxpair.put(keyformap,Math.max(datemaxpair.get(keyformap),list.get(i).getfrequency())); // keep track of the max frequency of cookie for that day
                
              } 
          }
          if (flag==true) continue; // if we already used up this cookie to update the map continue searching other cookies in the file
            Cookie cookie1=new Cookie(array[0]); // if same cookie was not found create a new one and add to the existing list for that day
            list.add(cookie1);
            datemaxpair.put(keyformap,Math.max(datemaxpair.get(keyformap),cookie1.getfrequency())); // keep track of the max frequency of cookie for that day
            hmap.put(keyformap,list); // add the updated list to the map
            
        }
        else {
            ArrayList<Cookie> list1=new ArrayList<>(); // if date is not in the map create a new one 
            Cookie cookie2=new Cookie(array[0]); // create a new cookie 
                list1.add(cookie2); // add it to the list
                datemaxpair.put(keyformap,cookie2.getfrequency()); // keep track of the max for that day
            hmap.put(keyformap,list1); // add the new list to the map
      }
    }
    }
    catch(IOException e) { // catch exception in case of IO error
      e.printStackTrace();
    }
    return hmap; // return the complete hashmap with cookies for each day and its respective frequencies
    }

    public static Set<String> mostactivecookie(ArrayList<Cookie> list,int max){ // returns those cookies with frequencies = max value for that day
        Set<String> result=new HashSet<>();
        for (Cookie items:list){
            if (items.getfrequency()==max)
            result.add(items.getid());
        }
        return result;
    }

    public static boolean checkvalidfilepath( String filepath){ // checks for valid filepath
        File f = new File(filepath);
        try {
           f.getCanonicalPath();
           return true;
        }
        catch (IOException e) {
           return false;
        }
    
    }

    
  public static void main(String[] args) {
    boolean isvalid=checkvalidfilepath(args[0]);
    if (isvalid){ // if path is valid
      HashMap<String,ArrayList<Cookie>> map=readfileintomap(args[0]); // fill the hashmap with the csv file
      Set<String> result=mostactivecookie(map.get(args[2]),datemaxpair.get(args[2]));
      for (String items: result)
      System.out.println(items);// returns the set of most used cookies
    }  
    else { 
      System.out.print ("invalid input");
    }
  }
}