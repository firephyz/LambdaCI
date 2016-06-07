
public class TreeGenerator {
	
	private Tree rootTree;
	
	public TreeGenerator() {
		rootTree = new Tree(NodeType.Term);
	}
	
	public Tree parse(String string) {
		
		resolveVariableNames(string);
		
		return parseTerm(rootTree, string);
	}
	
	public void resolveVariableNames(String string) {
		
	}
	
	public Tree parseTerm(Tree tree, String string) {
		
		if (string.charAt(0) == '(') {
			int match = Utils.findMatchingParens(string);
			String term = string.substring(1, match);
			
			if (term.charAt(0) == 'λ') {
				tree.addChild(new Node(NodeType.Function));
				parseFunction(tree.getChild(0), term);
			}
			else {
				tree.addChild(new Node(NodeType.Application));
				parseApplication(tree.getChild(0), term);
			}
		}
		else {
			tree.addChild(new TerminalNode(string, NodeType.Variable));
		}
		
		return this.rootTree;
	}
	
	public void parseFunction(Tree tree, String string) {
		
		// Remove lambda character
		string = string.substring(1);
		
		// Add variable to tree
		int periodIndex = string.indexOf('.');
		String var = string.substring(0, periodIndex);
		String body = string.substring(periodIndex + 1);
		tree.addChild(new TerminalNode(var, NodeType.VarDecl));
		tree.addChild(new Node(NodeType.Body));
		
		// Parse the term
		parseTerm(tree.getChild(1), body);
	}
	
	public void parseApplication(Tree tree, String string) {
		
		String term = null;
		int match = -1;
		
		if (string.charAt(0) == '(') {
			match = Utils.findMatchingParens(string);
			term = string.substring(0, match + 1);
			string = string.substring(match + 1).trim();
		}
		else {
			// The parens and space indicies are used to decide if this is an application 
			// like "x(y)" or if its like "x y"
			int parensIndex = string.indexOf('(');
			int spaceIndex = string.indexOf(' ');
			int index = parensIndex;
			
			if (parensIndex < 0) {
				index = spaceIndex;
			}
			else if (spaceIndex < parensIndex && spaceIndex >= 0) {
				index = spaceIndex + 1;
			}

			term = string.substring(0, index);
			string = string.substring(index).trim();
		}
		
		// Handles multiplie nested parenthesis
		if (string.length() == 0) {
			tree.getParent().getChildren().remove(0); // Remove the incorrent application node
			parseTerm(tree.getParent(), term);
			return;
		}
		
		tree.addChild(new Node(NodeType.AppLeft));
		tree.addChild(new Node(NodeType.AppRight));
		
		if (string.charAt(0) == '(') {
			parseTerm(tree.getChild(1), string);
		}
		else {
			tree.getChild(1).addChild(new TerminalNode(string, NodeType.Variable));
		}
		
		parseTerm(tree.getChild(0), term);
	}
}
