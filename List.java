/** A linked list of character data objects.
 *  (Actually, a list of Node objects, each holding a reference to a character data object.
 *  However, users of this class are not aware of the Node objects. As far as they are concerned,
 *  the class represents a list of CharData objects. Likwise, the API of the class does not
 *  mention the existence of the Node objects). */
public class List {

    // Points to the first node in this list
    private Node first;

    // The number of elements in this list
    private int size;
	
    /** Constructs an empty list. */
    public List() {
        first = null;
        size = 0;
    }
    
    /** Returns the number of element in this list. */
    public int getSize() {
 	      return size;
    }

    /** Returns the CharData of the first element in this list. */
    public CharData getFirst() {
        if (first == null) {
            return null;
        }
        return first.cp;
    }

    /** GIVE Adds a CharData object with the given character to the beginning of this list. */
    public void addFirst(char chr) {
        CharData Nchr = new CharData(chr);
        Node N = new Node(Nchr, first);
        first = N;
        size ++;
    }
    
    /** GIVE Textual representation of this list. */
    public String toString() {
       if(size == 0){return "()";}
       StringBuilder str = new StringBuilder("(");
       Node curNode = first;
       while(curNode!=null){
        str.append(curNode.cp.toString()).append(" ");
        curNode = curNode.next;
       }
        return str.substring(0,str.length()-1) + ")";
    }

    /** Returns the index of the first CharData object in this list
     *  that has the same chr value as the given char,
     *  or -1 if there is no such object in this list. */
    public int indexOf(char chr) {
        if(size == 0){return -1;}
        Node Curnode = first;
        int index = 0;
        while(Curnode!=null){
            if(Curnode.cp.chr == chr){
                return index;
            }index++;
            Curnode = Curnode.next;
        }
        return -1;
    }

    /** If the given character exists in one of the CharData objects in this list,
     *  increments its counter. Otherwise, adds a new CharData object with the
     *  given chr to the beginning of this list. */
   public void update(char chr) {
    int index = indexOf(chr); 

    if (index != -1) {
        CharData data = get(index);
        data.count++;
    } else {
        addFirst(chr);
    }
}

    /** GIVE If the given character exists in one of the CharData objects
     *  in this list, removes this CharData object from the list and returns
     *  true. Otherwise, returns false. */
    public boolean remove(char chr) {
        if(first == null){return false;} 
        if(first.cp.chr==chr){
            first = first.next;
            size--;
            return true;
        }
        Node prev = first;
        while(prev.next!=null){
            if(prev.next.cp.chr == chr){
                prev.next = prev.next.next;
                size--;
                return true;
            }
            prev = prev.next;
        }
        return false;
    }

    /** Returns the CharData object at the specified index in this list. 
     *  If the index is negative or is greater than the size of this list, 
     *  throws an IndexOutOfBoundsException. */
    public CharData get(int index) {
        if(index >= size || index < 0){
            throw new IndexOutOfBoundsException();
        }
        Node Curnode = first;
        int cindex = 0;
         while(Curnode!=null){
             if(cindex == index){
                return Curnode.cp;
             }Curnode = Curnode.next;
             cindex++;
            }return null;
    }

    /** Returns an array of CharData objects, containing all the CharData objects in this list. */
    public CharData[] toArray() {
	    CharData[] arr = new CharData[size];
	    Node current = first;
	    int i = 0;
        while (current != null) {
    	    arr[i++]  = current.cp;
    	    current = current.next;
        }
        return arr;
    }

    /** Returns an iterator over the elements in this list, starting at the given index. */
  public ListIterator listIterator(int index) { 
    Node current = first;
    int i = 0;
    while (i < index) {
        current = current.next;
        i++;
    }
    return new ListIterator(current); 
}
}