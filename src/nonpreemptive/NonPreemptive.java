
package nonpreemptive;


import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;
import java.util.Comparator;
import java.lang.Math;

/*
 *
 * @author faizan
 */

public class NonPreemptive {
    

// ----------------------- FUNCTIONALITY DEFINED FOR FCFS -------------------------------------
    
    public static void FCFS(Process processes[], double avgtime, double completiontime)     
    {
        Process[] arr= sort(processes);
          for(int i=0; i<arr.length; i++)
                    {
                        if(arr[i].arrival_Time<completiontime)
                        {
                            avgtime = avgtime + (completiontime -arr[i].arrival_Time);
                            completiontime=completiontime+arr[i].brust_Time;
                        }
                        else if(arr[i].arrival_Time==completiontime)
                        {
                            completiontime = completiontime + arr[i].brust_Time;
                        }
                        else
                        {
                            completiontime = arr[i].arrival_Time;
                            completiontime = completiontime + arr[i].brust_Time;
                        }
                    }
    
  // ---------------- PRINTING THE AVERAGE TIME AFTER CALCULATION -------------------
           
           System.out.println("AVERAGE WAITING TIME IS :"+(avgtime/processes.length));
    }
    
  // ----------------------- FUNCTIONALITY DEFINED FOR SJF -------------------------------------
   
    public static void SJF(Process processes[], double avgtime, double completiontime, int input1)    
    {
                    //completiontime=0;
                    //avgtime=0;
                    Process[] arry= sort(processes);
                    ArrayList<Process> readyque = new ArrayList(input1);  
                    int count=0;
                    while(count<input1)
                    {
                        while(true)
                        {
                            if(count<input1 && completiontime>=arry[count].arrival_Time )
                            { 
                               readyque.add(arry[count]);
                               count++;
                            }
                            else
                            {
                                break;
                            }
                        }                    
                        if(!readyque.isEmpty())
                        {
                            Collections.sort(readyque,new ComparingBursttTime());
                            Process temp = readyque.remove(0);
                            avgtime=avgtime+(completiontime-temp.arrival_Time);
                            completiontime = completiontime+temp.brust_Time;
                        }
                        else
                        {
                            completiontime=arry[count].arrival_Time;
                        }
                    }          
                    Collections.sort(readyque,new ComparingBursttTime());
                    while(!readyque.isEmpty())
                    {
                        Process temp = readyque.remove(0);
                        avgtime = avgtime+(completiontime-temp.arrival_Time);
                        completiontime = completiontime+temp.brust_Time;
                    }    
                    System.out.println("AVERAGE WAITING TIME IS :"+(avgtime/processes.length));     
    }
    
  // ----------------------- FUNCYIONALITY DEFINED FOR PRIORITY SCHEDULING -------------------------------------
    
    public static void priority(Process processes[], double avgtime, double completiontime, int input1)  
    { 
                   // completiontime=0;
                   // avgtime=0;
                    Process[] arry2= sort(processes);
                    ArrayList<Process> readyque2 = new ArrayList(input1);  
                    int count2=0;
                    while(count2<input1)
                    {
                        while(true)
                        {
                            if(count2<input1 && completiontime>=arry2[count2].arrival_Time )
                            { 
                               readyque2.add(arry2[count2]);
                               count2++;
                            }
                            else
                            {
                                break;
                            }
                        }
                        if(!readyque2.isEmpty())
                        {
                            Collections.sort(readyque2,new ComparingPriority());
                            Process temp = readyque2.remove(0);
                            avgtime=avgtime+(completiontime-temp.arrival_Time);
                            completiontime=completiontime+temp.brust_Time;
                        }
                        else
                        {
                            completiontime=arry2[count2].arrival_Time;
                        }
                    }
                    Collections.sort(readyque2,new ComparingPriority());
                    while(!readyque2.isEmpty())
                    {
                        Process temp = readyque2.remove(0);
                        avgtime=avgtime+(completiontime-temp.arrival_Time);
                        completiontime=completiontime+temp.brust_Time;
                    }    
                    System.out.println("AVERAGE WAITING TIME IS : "+(avgtime/processes.length));
               
    }
    
// -------------------- SORTING THE ARRAY ------------------------
    
    private static Process[] sort(Process[] arr)          
    {
        for(int i=0; i<arr.length-1; i++)
        {
            int sm = i;
            for(int j=i+1; j<arr.length; j++)
            {    
                if(arr[j].arrival_Time<arr[sm].arrival_Time)
                       sm=j;    
            }
            Process temp = arr[i];
            arr[i] = arr[sm];
            arr[sm] = temp;
        }
        return arr;
    }
    
 //--------------- MAIN METHOD FOR USER INPUT ---------------------------------------------
    
        public static void main(String[] args)
    {
        
        Scanner sc = new Scanner(System.in);
        NonPreemptive obj = new NonPreemptive();
        
   // ---------------- ASKING USER FOR NUMBER OF PROCESSES ----------------------------------
        
        System.out.println("ENTER NUMBER OF PROCESSES : ");       
        int input1 = sc.nextInt(); 
        
  // ---------------- CREATING AN ARRAY OF TYPE PROCESS OF SIZE OF NUMBER OF PROCESS ENTERD BY USER ---------------
        
        Process processes[] = new Process[input1];                
        int c = 0;
        while(c < input1)
        {
           System.out.println( "PROCESS "+ (c+1) +" NAME, "
                   + "BURST TIME, "
                   + "ARRIVAL TIME, "
                   + "PRIORITY");
           String name = sc.next();
           double bt = sc.nextDouble();
           double at = sc.nextDouble();
           double pp = sc.nextDouble();
           processes[c] = new Process(name,bt,at,pp);
           c++;
        }
    // ---------------------- PROMPTING THE USER FOR HIS CHOICE OF ALGORITHM CALCULATION ------------------------
        while(true)                             
        {
           System.out.println("MAKE YOUUR CHOICE :");
           System.out.println("PRESS 1 FOR FCFS : ");
           System.out.println("PRESS 2 FOR SJF : ");
           System.out.println("PRESS 3 FOR PRIORITY SCHEDULING : ");
           int input2 = sc.nextInt();
           double avgtime=0;
           double completiontime=0;
 // ---------------------- USER CHOOSE FOR FCFS CALCULATION ----------------------------------
           if(input2 == 1)                
           {
               obj.FCFS(processes, avgtime, completiontime);
           }
 // ---------------------- USER CHOOSE FOR SJF CALCULATION ----------------------------------
           if(input2 == 2)              
           {
               obj.SJF(processes, avgtime, completiontime, input1);
           }
 // ---------------------- USER CHOOSE FOR PRIORITY CALCULATION ----------------------------------
           if(input2 == 3)               
           {
              obj.priority(processes, avgtime, completiontime, input1);
           }
           System.out.println("DO YOU WANT TO PROCEED? ENTER '1' FOR CALCULATION OF OTHER ALGORITHMS OR PRESS 0 TO EXIT");
           if(!(sc.nextInt()==1))
           {
              break;
           }
           else
           {
              System.out.println("INVALID INPUT ! PLEASE TRY AGAIN ");
           }
        }
      }
  
}

class Process
{
    
  public String process_Name ;
  public double brust_Time;
  public double arrival_Time;
  public double priority;

    public Process(String process_Name, double brust_Time, double arival_Time, double priority) {
        this.process_Name = process_Name;
        this.brust_Time = brust_Time;
        this.arrival_Time = arival_Time;
        this.priority = priority;
    } 

}

// -------------------- COMPARING AND SORTING ARRAY ON THE BASIS OF BURST TIME FOR SJF ALGORITHM --------------------

class ComparingBursttTime implements Comparator<Process> 
{
     public int compare(Process a1,Process a2){
         String a= a1.brust_Time+"";
         String b= a2.brust_Time+"";
         return a.compareTo(b);      
    }
}

// ------------------ COMPARING AND SORTING ARRAY ON THE BASIS OF BURST TIME FOR PRIORITY ALGORITHM --------------------

class ComparingPriority implements Comparator<Process>
{

    public int compare(Process a1,Process a2){
         String a= a1.priority+"";
         String b= a2.priority+"";
         return a.compareTo(b);      
   }
}



// --------------------------------------------- THE END ---------------------------------------------------------------------