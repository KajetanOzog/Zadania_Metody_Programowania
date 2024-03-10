public class Main {
    public static class Node {
        double value;
        Node left;
        Node right;

        public Node(double value) {
            this.value = value;
            left = null;
            right = null;
        }
    }

    public static class Tree {
        private Node root;

        public Tree()
        {
            root = null;
        }

        public void insert(double value) {
            root = ins(root, value);
        }

        public Node ins(Node node, double value) {
            if (node == null)
            {
                return new Node(value);
            }

            if (value < node.value)
            {
                node.left = ins(node.left, value);
            }
            else if (value > node.value)
            {
                node.right = ins(node.right, value);
            }

            return node;
        }

        public void delete(double value)
        {
            root = del(root, value);
        }

        public Node del(Node node, double value)
        {
            if (node == null) {
                return null;
            }

            if (value < node.value)
            {
                node.left = del(node.left, value);
            }
            else if (value > node.value)
            {
                node.right = del(node.right, value);
            }
            else
            {
                if (node.left == null)
                {
                    return node.right;
                }
                else if (node.right == null)
                {
                    return node.left;
                }
                Node temp = minimum(node.right);
                node.value = temp.value;
                node.right = del(node.right, temp.value);
            }

            return node;
        }

        private Node minimum(Node node)
        {
            if (node.left == null)
            {
                return node;
            }
            return minimum(node.left);
        }

        public void preorder()
        {
            pre(root);
        }

        public void pre(Node node) {
            if (node != null) {
                System.out.print(node.value + " ");
                pre(node.left);
                pre(node.right);
            }
        }

        public void inorder() {
            in(root);
        }

        private void in(Node node) {
            if (node != null) {
                in(node.left);
                System.out.print(node.value + " ");
                in(node.right);
            }
        }

        public void postorder() {
            post(root);
        }

        private void post(Node node) {
            if (node != null) {
                post(node.left);
                post(node.right);
                System.out.print(node.value + " ");
            }
        }

        public double minimum()
        {
            if (root == null) {
                return 0.0;
            }
            return fmin(root);
        }

        private double fmin(Node node) {
            if (node.left == null) {
                return node.value;
            }

            return fmin(node.left);
        }

        public double maximum() {
            if (root == null) {
                return 0.0;
            }

            return fmax(root);
        }

        private double fmax(Node node) {
            if (node.right == null) {
                return node.value;
            }

            return fmax(node.right);
        }
    }


    public static void main(String[] args)
    {
        Tree tree = new Tree();


        tree.insert(10.0);
        tree.insert(5.0);
        tree.insert(15.0);
        tree.insert(3.0);
        tree.insert(7.0);


        System.out.print("Preorder: ");
        tree.preorder();
        System.out.println();

        tree.delete(5.0);

        System.out.print("Inorder: ");
        tree.inorder();
        System.out.println();

        System.out.print("Postorder: ");
        tree.postorder();
        System.out.println();

        System.out.println("Min: " + tree.minimum());

        System.out.println("Max: " + tree.maximum());
    }
}