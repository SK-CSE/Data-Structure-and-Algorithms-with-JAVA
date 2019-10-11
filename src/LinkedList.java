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

    public static void main(String[] args){
        LinkedList ll = new LinkedList();

        ll.append(6);
        ll.push(7);
        ll.push(1);
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

    }
}
