public class Main {
    public static class Lista {
        private int rozmiar;
        private Element head;

        private static class Element {
            private String nazwa;
            private Element prev;
            private Element next;

            public Element(String data) {
                this.nazwa = data;
            }
        }

        public Lista() {
            head = null;
            rozmiar = 0;
        }

        public void add(String data) {
            Element nowy = new Element(data);
            if (head == null) {
                head = nowy;
                head.prev = head;
                head.next = head;
                rozmiar++;
            }
            else
            {
                Element tail = head.prev;
                nowy.prev = tail;
                tail.next = nowy;
                head.prev = nowy;
                nowy.next = head;
                rozmiar++;
            }

        }

        public void remove(int numer) {
            if (numer < 0 || numer >= rozmiar)
            {
                System.out.println("Nie ma takiego");
            }
            else
            {
                if (rozmiar == 1) {
                    head = null;
                    rozmiar--;
                } else {
                    Element current = head;
                    for (int i = 0; i < numer; i++) {
                        current = current.next;
                    }
                    current.prev.next = current.next;
                    current.next.prev = current.prev;
                    if (current == head) {
                        head = current.next;
                    }
                    rozmiar--;
                }
            }


        }

        public void display() {
            if (head == null) {
                System.out.println(" ");
            } else {
                Element pom = head;
                do {
                    System.out.print(pom.nazwa + " ");
                    pom = pom.next;
                } while (pom != head);
                System.out.println();
            }
        }

        public void pokazZeCyklicznieDziala()
        {
            Element k = head;
            for(int i = 0; i < 10; i++)
            {
                System.out.print(k.nazwa + " ");
                k = k.next;
            }
        }
        public void pokazZeCyklicznieDziala2()
        {
            Element k = head;
            for(int i = 0; i < 10; i++)
            {
                System.out.print(k.nazwa + " ");
                k = k.prev;
            }
        }
    }
    public static void main(String[] args) {
       Lista lista = new Lista();
       lista.add("Jeden");
       lista.add("Dwa");
       lista.add("Trzy");
       lista.add("Cztery");
       lista.display();
       lista.remove(1);
       lista.display();
       lista.pokazZeCyklicznieDziala();
       System.out.println();
       lista.pokazZeCyklicznieDziala2();
    }
}