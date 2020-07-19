import java.util.HashSet; 
import java.util.LinkedList; 
import java.util.Queue;
import java.util.Scanner; 
  
  
class FIFO 
{ 
    static int pageFaults(int pages[], int n, int capacity) 
    { 

        HashSet<Integer> s = new HashSet<>(capacity); 
               Queue<Integer> indexes = new LinkedList<>() ; 
       
        int page_faults = 0; 
        for (int i=0; i<n; i++) 
        { 
            if (s.size() < capacity) 
            { 
                if (!s.contains(pages[i])) 
                { 
                    s.add(pages[i]); 
                    page_faults++; 
                    indexes.add(pages[i]); 
                } 
            } 
            else
            { 
                if (!s.contains(pages[i])) 
                { 
                    int val = indexes.peek();       
                    indexes.poll(); 
                    s.remove(val); 
                    s.add(pages[i]); 
                    indexes.add(pages[i]); 
                    page_faults++; 
                } 
            } 
        } 
       
        return page_faults; 
    } 
      
    public static void main(String args[]) 
    { 
    	Scanner reader = new Scanner(System.in);
        
        int pages[], capacity, npages;
        
        System.out.print("Capacity of frame window: ");
        capacity = reader.nextInt();
        System.out.print("Number of pages in the sequence: ");
        npages = reader.nextInt();
        pages = new int[npages];
        
        for(int i=0;i<npages;i++){
            int data;
            System.out.print("Enter the frame value: ");
            data = reader.nextInt();
            pages[i] = data;
        }
        reader.close();
        System.out.println(pageFaults(pages, pages.length, capacity)); 
    } 
}