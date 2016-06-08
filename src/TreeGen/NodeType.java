package TreeGen;

public enum NodeType {
	
	None("None"),
	Term("Term"),
	Application("Application"),
	AppLeft("AppLeft"),
	AppRight("AppRight"),
	Function("Function"),
	Body("Body"),
	Variable("Variable"),
	VarDecl("VarDecl");
	
	private String str;
	
	private NodeType(String str) {
		this.str = str;
	}
	
	public String print() {
		return this.str;
	}
}
