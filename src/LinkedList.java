import java.util.HashSet;
import java.util.Stack;

public class LinkedList {
    Node head;

    static class Node{
        int data;
        Node next;
        Node(int d){
            data = d;
            next = null;
        }
    }

    public void printList(){
        Node n = head;
        while (n!= null){
            System.out.println(n.data + " ");
            n = n.next;
        }
    }

    public void push(int new_data){
        Node new_node = new Node(new_data);
        new_node.next = head;
        head = new_node;
    }

    public void insertAfter(Node prev, int new_data){
        /* 1. Check if the given Node is null */
        if(prev.next == null){
            System.out.println("prev cannot be null..!");
            return;
        }
        /* 2. Allocate the Node &
            3. Put in the data*/
        Node new_node = new Node(new_data);

        /* 4. Make next of new Node as next of prev_node */
        new_node.next = prev.next;

        /* 5. make next of prev_node as new_node */
        prev.next = new_node;

    }

    /* Appends a new node at the end. */
    public void append(int new_data){
        Node new_node = new Node (new_data);

        /* If the Linked List is empty, then make the
           new node as head */
        if(head == null){
            head = new_node;
            return;
        }

        /* This new node is going to be the last node, so
         make next of it as null */
        new_node.next = null;

        /* Else traverse till the last node */
        Node last = head;
        while (last.next != null){
            last = last.next;
        }

        /* Change the next of last node */
        last.next = new_node;
        return;
    }

    /* Given a key, deletes the first occurrence of key in linked list */
    public void deleteNode(int data){

        // Store head node
        Node temp = head, prev = null;

        // If head node itself holds the key to be deleted
        if(temp != null && temp.data == data){
            head = temp.next;
            return;
        }

        // Search for the data to be deleted, keep track of the
        // previous node as we need to change temp.next
        while (temp != null && temp.data != data){
            prev = temp;
            temp = temp.next;
        }

        // If key was not present in linked list
        if(temp == null){
            return;
        }

        // Unlink the node from linked list
        prev.next = temp.next;

    }

    public void deleteNodeFromPosition(int position){
        if(head == null){
            return;
        }

        Node temp = head;
        if(position == 0){
            head = temp.next;
            return;
        }

        // Find previous node of the node to be deleted
        for(int i = 0; temp!= null && i < position - 1; i++){
            temp = temp.next;
        }

        // If position is more than number of nodes
        if(temp == null || temp.next == null){
            return;
        }

        // Node temp->next is the node to be deleted
        // Store pointer to the next of node to be deleted
        Node next = temp.next.next;

        temp.next = next;  // Unlink the deleted node from list
    }

    /* Function deletes the entire linked list */
    public void deleteList(){
        head = null;
    }

    /* Returns count of nodes in linked list */
    public int getCount(){
        int count = 0;
        Node current = head;
        while(current != null){
            count++;
            current = current.next;
        }
        return count;
    }

    /* Returns count of nodes in linked list  by recursion */
    public int getCountRec(Node node){
        if(node == null){
            return 0;
        }
        return 1 + getCountRec(node.next);
    }

    public boolean search(int data){
        Node curr = head;
        while(curr != null){
            if(curr.data == data){
                return true;
            }
            curr = curr.next;
        }
        return false;
    }
    public boolean searchRec(Node head, int data){
        Node curr = head;
        if(curr == null){
            return false;
        }

        if(curr.data == data){
            return true;
        }

        return searchRec(curr.next, data);
    }

    public int getNth(int index){
        Node curr = head;
        int currIdx = 0;
        while (curr != null){
            if(currIdx == index){
                return curr.data;
            }
            currIdx++;
            curr = curr.next;
        }
        assert (false);
        return 0;
    }

    static int getNthRec(Node head, int index){
        int c = 1;

        if(index == 1){
            return head.data;
        }
        return getNthRec(head.next, index - 1);
    }

    void printNthFromLast(int index){
        int len = 0;
        Node temp = head;
        while (temp != null){
            len++;
            temp = temp.next;
        }

        if(len < index){
            return;
        }
        temp = head;

        // i = 1 and get the (len-index+1)th node from the beginning
        for(int i = 1; i < len - index + 1; i++){
            temp = temp.next;
        }
        System.out.println(temp.data);

    }

    void printNthFromLast2(int index){
        Node main_ptr = head;
        Node ref_ptr = head;
        int c = 0 ;
        if (head != null) {
            while (c < index){
                ref_ptr = ref_ptr.next;
                c++;
            }
            if(ref_ptr == null){
                System.out.println("index is greater than length");
                return;
            }
            while (ref_ptr != null){
                main_ptr = main_ptr.next;
                ref_ptr = ref_ptr.next;
            }
            System.out.println(main_ptr.data);
        }
    }

    void printMiddle(){
        Node curr = head;
        int c = 0;
        while(curr != null){
            curr = curr.next;
            c++;
        }

        curr = head;

        for(int i = 0; i < c/2 ; i++){
            curr = curr.next;
        }
        System.out.println("middle element11111 "+ curr.data);
    }

    void printMiddle2(){
        Node  slow_ptr = head;
        Node fast_ptr = head;

        if(head != null){

            while(fast_ptr != null && fast_ptr.next != null){
                fast_ptr = fast_ptr.next.next;
                slow_ptr = slow_ptr.next;
            }
            System.out.println("midle element "+ slow_ptr.data);
        }
    }

    static boolean detectLoop(Node n){
        HashSet s = new HashSet<Node>();
        while (n != null){
            if(s.contains(n)){
                return true;
            }
            s.add(n);
            n = n.next;
        }
        return false;
    }

     boolean detectLoop2(Node n){
        Node fast_ptr = head;
        Node slow_ptr = head;
        while (fast_ptr != null && fast_ptr.next != null && slow_ptr != null){
            fast_ptr = fast_ptr.next.next;
            slow_ptr = slow_ptr.next;

            if(slow_ptr == fast_ptr){
                System.out.println("loop detected..!");
                return true;
            }
        }
        return false;
    }

    static int countNodesInLoop(Node n){
        Node fast_ptr = n, slow_ptr = n;
        int count = 1;

        while (fast_ptr != null && fast_ptr.next != null && slow_ptr != null){
            fast_ptr = fast_ptr.next.next;
            slow_ptr = slow_ptr.next;
            if(slow_ptr == fast_ptr){
                Node temp = slow_ptr;
                while(temp.next != n){
                    count++;
                    temp = temp.next;
                }
            }
        }
        return count;
    }

    static boolean isPalindrome(Node n){
        Node temp = n;
        boolean result = true;
        Stack<Integer> s = new Stack<Integer>();
        while (temp != null){
            s.push(temp.data);
            temp = temp.next;
        }

        while (n != null){
            int i = s.pop();
            if(i != n.data){
                result = false;
                break;
            }
            n = n.next;
        }
        return  result;
    }

    void removeDuplicatesFromSortedLL(){
        Node curr = head;
        while(curr != null){
            Node temp = curr;
            while(temp != null && temp.data == curr.data){
                temp = temp.next;
            }
            curr.next = temp;
            curr = curr.next;
        }
    }
    static Node removeDuplicatesFromSortedLL2(Node n){
//        temp to store pointer that need to be free
        Node temp;
        if(n == null){
            return null;
        }
        if(n.next!= null){
            if(n.data == n.next.data){
                temp = n.next;
                n.next = n.next.next;
                removeDuplicatesFromSortedLL2(n);
            }else{
                removeDuplicatesFromSortedLL2(n.next);
            }
        }
        return n;
    }

    void removeDuplicatesFromUnsortedLL(){
        Node curr = head;
        Node prev = null;
        HashSet<Integer> s = new HashSet<Integer>();
        while (curr != null){
            if(s.contains(curr.data)){
                prev.next = curr.next;
            }else{
                s.add(curr.data);
                prev = curr;
            }
            curr = curr.next;
        }
    }

    void moveToFront(){
        Node curr = head;
        Node prev = null;
        while(curr.next != null){
            prev = curr;
            curr = curr.next;
        }
        prev.next = null;
        curr.next = head;
        head = curr;
    }

    public static void main(String[] args){
        LinkedList ll = new LinkedList();

        ll.append(6);
        ll.push(7);
        ll.push(1);
        ll.push(2);
        ll.push(3);
        ll.append(4);
        ll.insertAfter(ll.head.next,8);

//        ll.head = new Node(1);
//        Node second = new Node(2);
//        Node third = new Node(3);
//        ll.head.next = second;
//        second.next = third;
        ll.printList();
        System.out.println("===========");

        ll.deleteNodeFromPosition(4);  // Delete node at position 4
        ll.printList();
        ll.deleteNode(1);
        System.out.println("===========");
        ll.printList();
        int count = ll.getCount();
        int countRec = ll.getCountRec(ll.head);

        System.out.println("count : "+count);
        System.out.println("countRec : "+countRec);
        System.out.println(ll.search(6));

        System.out.println(ll.getNth(5));
        System.out.println(ll.getNthRec(ll.head,5));
        System.out.println("===========");
        ll.printNthFromLast(2);
        System.out.println("===========");
        ll.printNthFromLast2(2);
        System.out.println("===========");
        ll.printMiddle();
        ll.printMiddle2();



    }
}
