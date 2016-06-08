package Lambda;
import TreeGen.Tree;
import TreeGen.TreeGenerator;

public class Lambda {
	
	public Lambda() {
		
		String lambdaString = "";
		
		//lambdaString = "((λa.(λb.(λc.(b((a b)c)))))((λs.(λx.(s(s x))))(λm.(λn.(m n)))))";
		//lambdaString = "((λab.ab)cd)";
		//lambdaString = "((λw.(λn.(λm.(n((w n)m))))) (λs.(λx.(s(s x)))))";
		//lambdaString = "((λa.(λb.(λc.(b ((a b) c)))))(λs.(λx.(s (s x)))))";
		//lambdaString = "(((((λa.(λb.(λc.((c a)b))))1))0)(λx.(λy.y))))";
		//lambdaString = "(((λa.(λb.((a((b(λx.(λy.x)))(λn.(λm.m))))(λc.(λd.d)))))(λt.(λf.f)))(λt.(λf.f)))";
		//lambdaString = "((((λa0.(λb0.(λc0.((c0 a0)b0))))1)0)(((λa.(λb.((a((b(λx.(λy.x)))(λn.(λm.m))))(λc.(λd.d)))))(λt.(λf.f)))(λt.(λf.f))))";
		
		Tree parseTree = (new TreeGenerator()).parse(lambdaString);
		System.out.println(parseTree.print());
		
		(new Reducer()).reduce(parseTree);
	}
	
	public static void main(String[] args) {
		new Lambda();
	}
}
