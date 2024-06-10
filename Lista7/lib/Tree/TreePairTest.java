public class TreePairTest
{
  public static void main(String[] args)
  {
    Tree<Pair> d = new Tree<Pair>();

    d.insert(new Pair(1.0,1.0));
    d.insert(new Pair(-1.0,1.0));
    d.insert(new Pair(1.0,-1.0));
    System.out.println(d.isElement(new Pair(1.0,-1.0)));
    System.out.println(d.isElement(new Pair(-1.0,-1.0)));
    System.out.println(d);
  }
}
