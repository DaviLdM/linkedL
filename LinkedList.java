
import java.util.NoSuchElementException;

public class LinkedList {

    private Node head;
    private Node tail;
    private int size;

    public LinkedList(){
        this.size = 0;
    }

    public boolean isEmpty(){
        if (this.head == null)return true;
        return false;
    }

    public void addLast(int valor){
        Node NovoNode = new Node(valor);

        if (isEmpty()) {
            this.head = NovoNode;
            this.tail = NovoNode;
        }else{
            this.tail.next = NovoNode;
            NovoNode.prev = tail;
            tail = NovoNode;

        }
        this.size +=1;
    }

    public void addFirst(int valor){

        Node NovoNode = new Node(valor);
        
        if (isEmpty()) {
            this.head = NovoNode;
            this.tail = NovoNode;
        }else{
            NovoNode.next = this.head;
            this.head.prev = NovoNode;
            this.head = NovoNode;
        }
        size ++;
    }

    public void add(int index, int valor){

        Node NovoNode = new Node(valor);

        if(index < 0 || index >= size ){
            throw new IndexOutOfBoundsException();
        }
        if (index == 0) addFirst(valor);
        else if (index == size -1) addLast(valor);
        else {
            Node aux = this.head;

            for (int i =0; i < index -1; i++){
                aux = aux.next;

            }
            NovoNode.next = aux.next;
            aux.next = NovoNode;
            NovoNode.prev = aux;
            NovoNode.next.prev = NovoNode;

            
        }
    }
   
    public int getFirst(){
        if (this.isEmpty()) return -1;
        else{
            return this.head.valor;
        }

    }

    public int getLast(){
        if (this.isEmpty()) return -1;
        else{
            return this.tail.valor;
        }

    }
    
    public int getValor(int index){
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
        else {
            Node aux = this.head;
            for (int i = 0; i < index; i++){
                aux = aux.next;
            }
            return aux.valor;
        }
    }

    public int getIndexOf(int valor){
        Node aux = this.head;
        int contador = 0;

        while (aux != null) {
            if (aux.valor == valor) {
                return contador;
            }
            contador ++;
            aux = aux.next;

            
        }
        return -1;
    }

    public boolean contains(int valor){
        return getIndexOf(valor) != -1;
    }

    public int removeFisrt(){
        if (this.isEmpty()) throw new NoSuchElementException();
        
        int valor = this.head.valor;

        if (this.head.next == null) {
            this.head = null;
            this.tail = null;
            
        }else{
            this.head = this.head.next;
            this.head.prev = null;
        }

        size --;
        return valor;
        
    }

    public int removeLast(){
        if (this.isEmpty()) throw new NoSuchElementException();
        int valor = this.tail.valor;

        if (this.head.next == null) {
            this.head = null;
            this.tail = null;
        }else{
            this.tail = this.tail.prev;
            this.tail.next = null;
        }
        size --;
        return valor;
    }

    public int removeByIndex(int index){
        if (index < 0 || index >= this.size) throw new IndexOutOfBoundsException();
        if (index == 0) return this.removeFisrt();
        if (index == size -1) return this.removeLast();

        Node aux = this.head;

        for(int i = 0; i < index; index ++){
            aux = aux.next;
        }

        aux.prev.next = aux.next;
        aux.next.prev = aux.prev;
        size --;
        return aux.valor;

    }

    public boolean removeByValue(int valor){
        Node aux = this.head;
        for(int i=0; i < size; i++){
            if (aux.valor == valor) {
                if(i==0) removeFisrt();
                else if(i==size-1) removeLast();
                else{
                    aux.prev.next = aux.next;
                    aux.next.prev = aux.prev;
                    size --;
                }
                return true;
                
            }
            aux = aux.next;

        }
        return false;

    }




}

class Node {

    int valor;
    Node next;
    Node prev;

    public Node(int v){
        this.valor = v;
    }

}
