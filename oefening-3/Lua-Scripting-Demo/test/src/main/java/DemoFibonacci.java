import org.luaj.vm2.Globals;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.jse.JsePlatform;

public class DemoFibonacci
{
	private static final int N = 5;

	public static void main(String[] args)
	{
		Globals globals = JsePlatform.standardGlobals();

		// Load the file and run it, this will define our function
		globals.get("dofile").call(LuaValue.valueOf("src/main/resources/series.lua"));

		// Get the function and prepare the argument
		LuaValue fibfunc = globals.get("fib");
		LuaValue lua_n = LuaValue.valueOf(N);

		// Call the function and get the fibonacci number back
		LuaValue lua_fib = fibfunc.call(lua_n);

		// Convert the Lua fibonacci number to a normal Java number
		System.out.println("The " + N + "-th fibonacci number is " + lua_fib.checkint());
	}
}
