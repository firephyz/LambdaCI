
public class Lambda {
	
	private Tree parseTree;
	
	public Lambda() {
		
		//String lambdaString = "((λa.(λb.(λc.(b((a b)c)))))((λs.(λx.(s(s x))))(λm.(λn.(m n)))))";
		//String lambdaString = "((λab.ab)cd)";
		//String lambdaString = "((λw.(λn.(λm.(n((w n)m))))) (λs.(λx.(s(s x)))))";
		//String lambdaString = "((λa.(λb.(λc.(b ((a b) c)))))(λs.(λx.(s (s x)))))";
		//String lambdaString = "(((((λa.(λb.(λc.((c a)b))))1))0)(λx.(λy.y))))";
		//String lambdaString = "(((λa.(λb.((a((b(λx.(λy.x)))(λn.(λm.m))))(λc.(λd.d)))))(λt.(λf.f)))(λt.(λf.f)))";
		String lambdaString = "((((λa0.(λb0.(λc0.((c0 a0)b0))))1)0)(((λa.(λb.((a((b(λx.(λy.x)))(λn.(λm.m))))(λc.(λd.d)))))(λt.(λf.f)))(λt.(λf.f))))";
		
		parseTree = (new TreeGenerator()).parse(lambdaString);
		//System.out.print(Utils.printParseTree(parseTree));
		System.out.println(parseTree.print());
		
		Reducer red = new Reducer();
		while(!red.isNormal()) {
			red.reduce(parseTree);
			if (!red.isNormal()) System.out.println(parseTree.print());
			//System.out.print(Utils.printParseTree(parseTree));
		}
	}
	
	public static void main(String[] args) {
		new Lambda();
	}
}
