package Lambda;
import TreeGen.NodeType;
import TreeGen.TerminalNode;
import TreeGen.Tree;

public class Reducer {
	
	public boolean isDone;
	
	public Reducer() {
		this.isDone = false;
	}
	
	public boolean isNormal() {return this.isDone;}
	
	public void reduce(Tree tree) {
		
		// Assume no application is needed; we are done
		this.isDone = true;
		
		// Process anyway and see if that assumption should change
		this.reduceHelper(tree);
	}
	
	private boolean reduceHelper(Tree tree) {
		
		boolean beta = false;
		
		if (tree.getNode().getType() == NodeType.Application) {
			Tree left = tree.getChild(0).getChild(0);
			Tree right = tree.getChild(1).getChild(0);
			if (left.getNode().getType() == NodeType.Function) {
				this.isDone = false;
				this.processApplication(tree, left, right);
				return true;
			}
			else {
				for(Tree sub : tree.getChildren()) {
					beta = this.reduceHelper(sub);
					if (beta) break;
				}
			}
		}
		else {
			for(Tree sub : tree.getChildren()) {
				beta = this.reduceHelper(sub);
				if (beta) break;
			}
		}
		
		if (beta) return true;
		else return false;
	}
	
	// tree is the branch of the root_tree that corresponds to this application
	// function is the left side of the application that holds the function
	// subst is the right side of the application that holds what is to be substituted
	private void processApplication(Tree tree, Tree function, Tree subst) {
		
		// Variable to be substituted
		String var = ((TerminalNode)function.getChild(0).getNode()).getVar();
		Tree body = function.getChild(1).getChild(0); // Function body
		
		// Does the actual substitution in the tree
		performApplication(var, body, subst);
		
		// Reorganize tree
		tree.getParent().replaceChild(0, function.getChild(1).getChild(0)); // Child 0 of the parent tree is the application tree
	}
	
	private void performApplication(String var, Tree body, Tree subst) {
		
		// For each variable matching the given var, replace it with the subst tree
		if (body.getNode().getType() == NodeType.Variable &&
			((TerminalNode)body.getNode()).getVar().equals(var)) {
			// Replacing child 0 because the only case in which a NodeType.Variable
			// can occur is when it is the only and terminal child.
			// Must replace with a copy so that future applications on one copy
			// don't bleed over into another copy
			body.getParent().replaceChild(0, subst.copy());
		}
		else {
			for(Tree sub : body.getChildren()) {
				this.performApplication(var, sub, subst);
			}
		}
	}
}
