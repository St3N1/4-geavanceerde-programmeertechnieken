import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.OneArgFunction;

public class sin extends OneArgFunction
{
	@Override
	public LuaValue call(LuaValue x)
	{
		return LuaValue.valueOf(
				Math.sin(
						x.checkdouble()
				)
		);
	}
}
