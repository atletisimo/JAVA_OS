/*Потребно е да имплементирате Windows Explorer со помош на дрво. Јазлите треба да ви бидат фолдери/датотеки. Почетно ќе имате само еден фолдер c: и тој ви е тековен фолдер.
Ќе ви биде дадена низа од команди што можат да бидат во еден од следните типови:
CREATE [име на фолдер/датотека] - треба да креирате нов фолдер/датотека во тековниот. 
Треба да внимавате при додавањето, новиот фолдер/датотека треба да се смести на онаа позиција така што сите фолдери/датотеки во тековниот фолдер ќе бидат подредени лексикографски

OPEN [име на фолдер/датотека] - го отварате фолдерот во тековниот фолдер и се менува тековниот фолдер

DELETE [име на фолдер/датотека] - го бришете фолдерот/датотеката

BACK - се враќате назад во претходниот фолдер

PATH - се печати патеката на тековниот фолдер на пример: c:\users\darko\mydocuments

PRINT - се печати целата структура на датотечниот систем така што секој фолдер/датотека се печати во еден ред со онолку празни места колку што е нивото на тој фолдер/датотека

Забелешка: Имињата на фолдерите/датотеките ќе бидат составени само од еден збор што содржи мали латинични букви. Сите операции ќе бидат валидни.

Име на класата: WindowsExplore*/
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Iterator;
import java.util.NoSuchElementException;

interface Tree<E> {
    ////////////Accessors ////////////

    public Node<E> root();

    public Node<E> parent(Node<E> node);

    public int childCount(Node<E> node);

    ////////////Transformers ////////////
    public void makeRoot(E elem);

    public Node<E> addChild(Node<E> node, E elem);

    public void remove(Node<E> node);

    ////////////Iterator ////////////
    public Iterator<E> children(Node<E> node);

}

interface Node<E> {

    public E getElement();

    public void setElement(E elem);
}

class SLLTree<E> implements Tree<E> {
    
    public SLLNode<E> root;

    public SLLTree() {
        root = null;
    }

    public Node<E> root() {
        return root;
    }

    public Node<E> parent(Node<E> node) {
        return ((SLLNode<E>) node).parent;
    }

    public int childCount(Node<E> node) {
        SLLNode<E> tmp = ((SLLNode<E>) node).firstChild;
        int num = 0;
        while (tmp != null) {
            tmp = tmp.sibling;
            num++;
        }
        return num;
    }

    public void makeRoot(E elem) {
        root = new SLLNode<E>(elem);
    }

    public Node<E> addChild(Node<E> node, E elem) {
        SLLNode<E> tmp = new SLLNode<E>(elem);
        SLLNode<E> curr = (SLLNode<E>) node;
        tmp.sibling = curr.firstChild;
        curr.firstChild = tmp;
        tmp.parent = curr;
        return tmp;
    }

    public void remove(Node<E> node) {
        SLLNode<E> curr = (SLLNode<E>) node;
        if (curr.parent != null) {
            if (curr.parent.firstChild == curr) {
                // The node is the first child of its parent
                // Reconnect the parent to the next sibling
                curr.parent.firstChild = curr.sibling;
            } else {
                // The node is not the first child of its parent
                // Start from the first and search the node in the sibling list and remove it
                SLLNode<E> tmp = curr.parent.firstChild;
                while (tmp.sibling != curr) {
                    tmp = tmp.sibling;
                }
                tmp.sibling = curr.sibling;
            }
        } else {
            root = null;
        }
    }

    class SLLTreeIterator<T> implements Iterator<T> {

        SLLNode<T> start, current;

        public SLLTreeIterator(SLLNode<T> node) {
            start = node;
            current = node;
        }

        public boolean hasNext() {
            return (current != null);
        }

        public T next() throws NoSuchElementException {
            if (current != null) {
                SLLNode<T> tmp = current;
                current = current.sibling;
                return tmp.getElement();
            } else {
                throw new NoSuchElementException();
            }
        }

        public void remove() {
            if (current != null) {
                current = current.sibling;
            }
        }
    }

    public Iterator<E> children(Node<E> node) {
        return new SLLTreeIterator<E>(((SLLNode<E>) node).firstChild);
    }
    
    void printTreeRecursive(Node<E> node, int level) {
        if (node == null)
            return;
        int i;
        SLLNode<E> tmp;

        for (i=0;i<level;i++)
            System.out.print(" ");
        System.out.println(node.getElement().toString());
        tmp = ((SLLNode<E>)node).firstChild;
        while (tmp != null) {
            printTreeRecursive(tmp, level+1);
            tmp = tmp.sibling;
        }
    }
    
    public void printTree() {
        printTreeRecursive(root, 0);
    }
    
}

class SLLNode<P> implements Node<P> {

    // Holds the links to the needed nodes
    public SLLNode<P> parent, sibling, firstChild;
    // Hold the data
    public P element;

    public SLLNode(P o) {
        element = o;
        parent = sibling = firstChild = null;
    }

    public P getElement() {
        return element;
    }

    public void setElement(P o) {
        element = o;
    }
}

public class WindowsExplorer {
    
    public static void main(String[] args) throws Exception {
        int i,j,k;
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine());
        String commands[] = new String[N];
        
        for (i=0;i<N;i++)
            commands[i] = br.readLine();
            
        br.close();
        
        SLLTree<String> tree = new SLLTree<String>();
        tree.makeRoot("c:");
//vasiot kod ovde
        
        
        String a="\\";
        SLLNode<String> curr=(SLLNode<String>) tree.root;
        for(i=0;i<N;i++){
            String pom[]=commands[i].split(" ");
          switch(pom[0]){
              case "CREATE": SLLNode<String> tmp1=curr.firstChild;
                             SLLNode<String> tmp2=null;
                                while(tmp1!=null)
                                    if(tmp1.getElement().compareTo(pom[1])<0){
                                        tmp2=tmp1;
                                        tmp1=tmp1.sibling;
                                    }
                                    else break;
                                if(tmp2==null)
                                    tree.addChild(curr,pom[1]);
                                else
                                {
                                    SLLNode<String> node=new SLLNode(pom[1]);
                                    node.sibling=tmp2.sibling;
                                    tmp2.sibling=node;
                                    node.parent=curr;
                                }
                                break;
              case "OPEN":   SLLNode tmp=curr.firstChild;
                             while(tmp!=null){
                                 if(pom[1].compareTo(tmp.element.toString())==0){
                                     curr=tmp;
                                     break;}
                                 tmp=tmp.sibling;
                             } break;
              case "DELETE": SLLNode<String> tmp3=curr.firstChild;
                                while(!tmp3.getElement().equals(pom[1]))
                                    tmp3=tmp3.sibling;
                                    tree.remove(tmp3);
                             break;
              case "BACK":   curr=curr.parent; break;
              case "PATH":  SLLNode<String>  pomosen=curr; 
                            String st="";
                                while(pomosen!=tree.root){ 
                                    st=pomosen.element+a+st;
                                    pomosen=pomosen.parent;
                                } 
                                System.out.print("c:"+a);
                                System.out.print(st);
                                System.out.println(""); 
                             break;
              case "PRINT":  tree.printTree(); break;
          }
        }      
    }   
}
