//Kajetan Ozog
import java.util.Scanner;

/*
W zaleznosci czy liczby sa podane w kolejnosci preorder czy postorder to wykonuje jedna z dwoch funkcji.
pre_in:
Pierwszy element w tablicy preorder to root drzewa, szukam tego elementu w tablicy inorder, dziele obie tablie na lewa i
prawa czesc, i rekurencyjnie wykonuje ta sama funkcje dla nich
post_in:
Dokladnie to samo tylko od konca
*/
public class Source
{
   static class PriorityQueue
   {
       private static class Info
       {
           Node node;
           Info next;
           Info(Node n)
           {
               node = n;
           }
       }

       Info first;

       PriorityQueue()
       {
           first = null;
       }

       public boolean isEmpty()
       {
           return first == null;
       }
       public void insert(Node n)
       {
           Info p = new Info(n);
           if(isEmpty())
           {
               p.next = first;
               first = p;
           }
           else
           {
               Info a = first;
               while (a.next != null)
               {
                   a = a.next;
               }
               p.next = null;
               a.next = p;
           }
       }

       public Node remove()
       {
           if(isEmpty())
           {
               return null;
           }
           else
           {
               Node p = first.node;
               first = first.next;
               return p;
           }
       }
   }
    static class Tree
    {
        Node root;

        String postorderS;

        String preorderS;

        String levelorderS;

        Tree(Node r)
        {
            root = r;
            postorderS = "";
            preorderS = "";
            levelorderS = "";
        }

        void postorder(Node p)
        {
            if(p != null)
            {
                postorder(p.left);
                postorder(p.right);
                this.postorderS += (" " + p.value);
                //System.out.print(p.value + " ");
            }
        }

        void preorder(Node p)
        {
            if(p != null)
            {
                this.preorderS += (" " + p.value);
                //System.out.print(p.value + " ");
                preorder(p.left);
                preorder(p.right);
            }
        }

        void levelorder(Node s)
        {
            PriorityQueue queue = new PriorityQueue();
            queue.insert(s);

            while (!queue.isEmpty())
            {
                Node u = queue.remove();
                this.levelorderS += (" " + u.value);
                //System.out.print(u.value + " ");

                if(u.left != null)
                {
                    queue.insert(u.left);
                }
                if(u.right != null)
                {
                    queue.insert(u.right);
                }
            }
        }
    }
    static class Node
    {
        Node left;
        Node right;
        int value;
        Node(int val)
        {
            value = val;
            right = null;
            left = null;
        }

    }

    static Node pre_in(int[] preorder, int[] inorder, int preBeg, int preEnd, int inBeg, int inEnd)
    {
        if(preBeg > preEnd  || inBeg > inEnd)
        {
            return null;
        }

        int value = preorder[preBeg];
        Node node = new Node(value);

        int place = -1;
        for(int i = inBeg; i <= inEnd; i++)
        {
            if(inorder[i] == value)
            {
                place = i;
                break;
            }
        }

        node.left = pre_in(preorder, inorder, preBeg + 1, preBeg + place - inBeg, inBeg,place -1);

        node.right = pre_in(preorder, inorder, preBeg + place - inBeg + 1, preEnd, place + 1, inEnd);

        return node;

    }

    static Node post_in(int[] postorder, int[] inorder, int postBeg, int postEnd, int inBeg, int inEnd)
    {

        if(postBeg > postEnd  || inBeg > inEnd)
        {
            return null;
        }

        int value = postorder[postEnd];
        Node node = new Node(value);

        int place = -1;
        for(int i = inBeg; i <= inEnd; i++)
        {
            if(inorder[i] == value)
            {
                place = i;
                break;
            }
        }

        node.right = post_in(postorder,inorder,postEnd - inEnd + place, postEnd - 1, place + 1, inEnd);
        node.left = post_in(postorder,inorder, postBeg, postEnd - inEnd + place - 1, inBeg, place - 1);

        return node;
    }




    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        int sets = in.nextInt();
        for(int i = 0; i < sets; i++)
        {
            int size = in.nextInt();
            int[] order = new int[size];
            int[] inorder = new int[size];
            in.nextLine();
            String type = in.nextLine();
            for(int j = 0; j < size; j++)
            {
                order[j] = in.nextInt();
            }
            in.nextLine();
            in.nextLine();
            for(int j = 0; j < size; j++)
            {
                inorder[j] = in.nextInt();
            }

            if(type.equals("PREORDER"))
            {
                Tree tree = new Tree(pre_in(order,inorder,0,order.length - 1, 0, order.length - 1));
                System.out.println("ZESTAW " + (i+1));
                System.out.println("POSTORDER");
                tree.postorder(tree.root);
                System.out.println(tree.postorderS.substring(1));
                System.out.println("LEVELORDER");
                tree.levelorder(tree.root);
                System.out.println(tree.levelorderS.substring(1));
            }
            else
            {
                Tree tree = new Tree(post_in(order,inorder,0,order.length - 1, 0, order.length - 1));
                System.out.println("ZESTAW " + (i+1));
                System.out.println("PREORDER");
                tree.preorder(tree.root);
                System.out.println(tree.preorderS.substring(1));
                System.out.println("LEVELORDER");
                tree.levelorder(tree.root);
                System.out.println(tree.levelorderS.substring(1));
            }
        }
    }
}

//Testy:

/*
in:

4
11
PREORDER
1 2 4 8 5 9 3 6 7 10 11
INORDER
8 4 2 5 9 1 6 3 10 7 11
9
POSTORDER
4 2 7 5 9 8 6 3 1
INORDER
4 2 1 5 7 3 6 8 9
7
PREORDER
1 2 4 5 3 6 7
INORDER
4 2 5 1 6 3 7
7
POSTORDER
4 5 2 6 7 3 1
INORDER
4 2 5 1 6 3 7

out:

ZESTAW 1
POSTORDER
8 4 9 5 2 6 10 11 7 3 1
LEVELORDER
1 2 3 4 5 6 7 8 9 10 11
ZESTAW 2
PREORDER
1 2 4 3 5 7 6 8 9
LEVELORDER
1 2 3 4 5 6 7 8 9
ZESTAW 3
POSTORDER
4 5 2 6 7 3 1
LEVELORDER
1 2 3 4 5 6 7
ZESTAW 4
PREORDER
1 2 4 5 3 6 7
LEVELORDER
1 2 3 4 5 6 7

*/