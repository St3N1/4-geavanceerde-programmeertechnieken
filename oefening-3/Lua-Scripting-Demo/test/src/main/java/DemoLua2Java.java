import org.luaj.vm2.Globals;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.jse.JsePlatform;

public class DemoLua2Java
{
	public static void main(String[] args)
	{
		Globals globals = JsePlatform.standardGlobals();

		LuaValue chunk = globals.loadfile("src/main/resources/geom.lua");
		chunk.call();
	}
}

