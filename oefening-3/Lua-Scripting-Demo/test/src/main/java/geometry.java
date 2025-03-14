import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.TwoArgFunction;

public class geometry extends TwoArgFunction
{
	public geometry() {}

	@Override
	public LuaValue call(LuaValue moduleName, LuaValue env)
	{
		LuaValue library = tableOf();
		library.set("sin", new sin());

		env.set("geometry", library);
		return library;
	}
}
