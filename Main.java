class one{
  int num, top;
  one left, right;
  one(int d){
    num = d;
    top = 1;
  }
}
class Main{
  one root;
  int top(one c){
    if (c == null)
    return 0;
    return c.top;
  }
  int max(int a, int b){
    return (a > b) ? a : b;
  }
  one rTurn(one y){
    one x = y.left;
    one w = x.right;
    x.right = y;
    y.left = w;
    y.top = max(top(y.left), top(y.right)) + 1;
    x.top = max(top(x.left), top(x.right)) + 1;
    return x;
  }
  one lTurn(one x){
    one y = x.right;
    one w = y.left;
    y.left = x;
    x.right = w;
    x.top = max(top(x.left), top(x.right)) + 1;
    y.top = max(top(y.left), top(y.right)) + 1;
    return y;
  }
  int getBalance(one c){
    if (c == null)
    return 0;
    return top(c.left) - top(c.right);
  }
  one insert(one two, int num){
    if (two == null)
    return (new one(num)); 
    if (num < two.num)
    two.left = insert(two.left, num);
    else if (num > two.num)
    two.right = insert(two.right, num);
    else 
    return two;
    two.top = 1 + max(top(two.left),
    top(two.right));
    int balance = getBalance(two);
    if (balance > 1 && num < two.left.num)
    return rTurn(two);
    if (balance < -1 && num > two.right.num)
    return lTurn(two);
    if (balance > 1 && num > two.left.num){
      two.left = lTurn(two.left);
      return rTurn(two);
    }
    if (balance < -1 && num < two.right.num){
      two.right = rTurn(two.right);
      return lTurn(two);
    }
    return two;
  }
  void preOrder(one two){
    if (two != null){
      System.out.print(two.num + " ");
      preOrder(two.left);
      preOrder(two.right);
    }
  }
  public static void main(String[] args){
    AVLTree tree = new AVLTree();
    tree.root= tree.insert(tree.root, 2);
    tree.root= tree.insert(tree.root, 5);
    tree.root= tree.insert(tree.root, 10);
    tree.root= tree.insert(tree.root, 15);
    tree.root= tree.insert(tree.root, 20);
    tree.root= tree.insert(tree.root, 25);
    System.out.println("The AVL tree is: ");
    tree.preOrder(tree.root);
  }
}