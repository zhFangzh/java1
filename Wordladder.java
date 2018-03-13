/*
 * this is a JAVA program for wordladder
*/


import java.io.*;
import java.util.*;

/*
 * @author Zhu Fangzhou 516030910146
*/

public class Wordladder {
    
    public static void main(String[] args) {
        try {
            ArrayList dic = new ArrayList();                                    //dictionary
            Scanner input  = new Scanner(System.in);                            
            FileReader file = new FileReader("dictionary.txt");
            BufferedReader buff = new BufferedReader(file);                     //
            
            boolean eof = false;
            while (!eof){                                                       //
                String word = buff.readLine();
                if (word == null){
                    eof = true;
                }
                else {
                    dic.add(word);
                }
            }                   
            String first,last;
            while (true){                                                       //
                System.out.println("Word #1 (or Enter to quit): ");
                first = input.next();
                if ("\n".equals(first)){
                    break;
                }
                System.out.println("Word #2 (or Enter to quit): ");
                last = input.next();
                if ("\n".equals(last)){
                    break;
                }
                if(first.length()!=last.length()){                              //
                    System.out.println("The two words must be different. ");
                }
                if(!dic.contains(first)||!dic.contains(last)){                  //
                    System.out.println("The two words must be found in the dictionary.");
                }
                getladder(first,last,dic);
            }
            System.out.println("Have a nice day. ");
        } catch (IOException e) {
            System.out.println("Error -- "+e.toString());
        }
    }
    
    //
    static void getladder(String first,String last, ArrayList dic){             
        Queue<String> Bfs = new LinkedList<>();                                 //
        ArrayList getwords = new ArrayList();                                   //
        Bfs.offer(first);
        getwords.add(first);
        if(null==findpath(dic,getwords,Bfs,last)){
            System.out.println("No word ladder found from azure back to metal.");
        }
    }
    
    //
    static String findpath(ArrayList dic,ArrayList words,Queue<String> Bfs,String last ){      
        
        ArrayList father = new ArrayList();
        String head = Bfs.poll();
        String son;
        if (head == null){
            return head;
        }
        father.add(head);
        for(int i=0;i<head.length();i++){ 
            for (int j = 0;j<26;j++){
                char[] arr = head.toCharArray();
                arr[i] = (char) ('a' + j);
                son = new String (arr);
                if (words.contains(son)){
                    continue;
                }
                if (dic.contains(son)){
                    father.add(son);
                    Bfs.offer(son);
                    words.add(son);
                }
                if(last.equals(son)){
                    System.out.println(last + head);
                    return head;
                }
            }
        }
        String pathwords = findpath(dic,words,Bfs,last);
        if(father.contains(pathwords)){
            System.out.println(head);
            return head;
        }
        else{
            return pathwords;
        }
    }
    
}
