package TreeGen;
import java.util.ArrayList;

public class Tree {
	
	private Tree parent;
	private ArrayList<Tree> children;
	private Node node;
	
	public Tree(Node node) {
		this.parent = null;
		children = new ArrayList<>();
		this.node = node;
	}
	
	public Tree getChild(int i) 			{return children.get(i);}
	public ArrayList<Tree> getChildren() 	{return this.children;}
	public int getChildCount() 				{return this.children.size();}
	public Node getNode() 					{return this.node;}
	public Tree getParent() 				{return this.parent;}
	public void setParent(Tree tree) 		{this.parent = tree;}
	
	public void addChild(Node node) {
		Tree newTree = new Tree(node);
		newTree.setParent(this);
		children.add(newTree);
		
	}
	
	public void addChild(Tree tree) {
		tree.setParent(this);
		this.children.add(tree);
	}
	
	public void replaceChild(int i, Tree tree) {
		tree.setParent(this);
		this.children.add(i, tree);
		this.children.remove(i + 1);
	}
	
	public Tree copy() {
		
		Tree newTree = new Tree(this.getNode());
		newTree.setParent(this.getParent());
		
		for(Tree child : this.children) {
			newTree.addChild(child.copy());
		}
		
		return newTree;
	}
	
	public String print() {
		
		String string = printBranch("", this);
		
		return string;
	}
	
	public String printFormatted() {

		String result = "[";
		
		result += this.node.getType().print() + " ";
		if (this.node instanceof TerminalNode) {
			result += ((TerminalNode)this.node).getVar() + " ";
		}
		
		for(int i = 0; i < getChildCount(); ++i) {
			result += this.getChild(i).printFormatted();
		}
		
		result += "]";
		
		return result;
	}
	
	////////////////////// Private Helper Functions ///////////////////////////
	
	private String printBranch(String string, Tree tree) {
		
		switch(tree.getNode().getType()) {
		case Function:
			string += "(λ";
			string += ((TerminalNode)tree.getChild(0).getNode()).getVar();
			string += ".";
			string = printChildren(string, tree);
			string += ")";
			break;
		case Application:
			string += "(";
			string = printBranch(string, tree.getChild(0));
			string += " ";
			string = printBranch(string, tree.getChild(1));
			string += ")";
			break;
		case Variable:
			String var = ((TerminalNode)tree.getNode()).getVar();
			string += var;
			break;
		default:
			string = printChildren(string, tree);
		}
		
		return string;
	}
	
	private String printChildren(String string, Tree tree) {
		for(Tree child : tree.getChildren()) {
			string = printBranch(string, child);
		}
		
		return string;
	}
}
