
public class TerminalNode extends Node {
	
	private String var;
	
	public TerminalNode(String string, NodeType type) {
		super(type);
		this.var = string;
	}
	
	public String getVar() {return this.var;}
	public void setVar(String string) {this.var = string;}
}
