package Lambda;
import TreeGen.Tree;

public class Utils {
	
	// Finds the index of the matching parenthesis given a string starting
	// with the opening parenthesis
	public static int findMatchingParens(String string) {
		
		int depthCount = 0;
		
		for(int i = 0; i < string.length(); ++i) {
			if (string.charAt(i) == '(') {
				++depthCount;
			}
			else if (string.charAt(i) == ')') {
				--depthCount;
			}
			
			if (depthCount == 0) {
				return i;
			}
		}
		
		return -1;
	}
	
	/*
	 * Prints a nicely formatted parse tree
	 */
	public static String printParseTree(Tree root_tree) {
		
		String printedTree = root_tree.printFormatted();
		int depthCount = -1; // Tab depth count for current line
		
		for(int i = 0; i < printedTree.length(); ++i) {
			char character = printedTree.charAt(i);
			
			// If a parenthesis is encountered, we must add a line break and 
			// the correct number of tab indents
			if (character == '[') {
				++depthCount;
				
				// Split parse tree string along the parenthesis just found
				String start = printedTree.substring(0, i);
				String end = printedTree.substring(i);
				printedTree = start + "\n";
				
				// Insert correct number of tabs
				for(int j = 0; j < depthCount; ++j) {
					printedTree = printedTree + "\t";
					++i;
				}
				
				// Catenate strings and remove start bracket
				printedTree = printedTree + end.substring(1);
				++i;
			}
			// Decrease tab depth count
			else if (character == ']') {
				--depthCount;
				
				// Remove end bracket
				printedTree = printedTree.substring(0, i) + printedTree.substring(i+1);
				--i;
			}
		}
		
		return printedTree + "\n";
	}
}
