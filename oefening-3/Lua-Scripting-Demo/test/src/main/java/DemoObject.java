import org.luaj.vm2.Globals;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.jse.JsePlatform;

public class DemoObject
{
	public static void main(String[] args)
	{
		Globals globals = JsePlatform.standardGlobals();

		// Load the file and run it, this will define our function
		globals.get("dofile").call(LuaValue.valueOf("src/main/resources/coords.lua"));

		// Get the function and call it
		LuaValue coordfunc = globals.get("get_coords");
		LuaValue coords = coordfunc.call();

		int x = coords.get("x").checkint();
		int y = coords.get("y").checkint();

		System.out.println("The returned coordinate was (" + x + ", " + y + ")");
	}
}
