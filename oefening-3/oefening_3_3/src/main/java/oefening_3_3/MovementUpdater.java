package oefening_3_3;

import org.luaj.vm2.Globals;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.jse.JsePlatform;

public class MovementUpdater {
    public MovementComponent[] update(final MovementComponent[] components) {
        // To lua value
        LuaValue[] componentArr = new LuaValue[components.length];
        for (int i = 0; i < components.length; i++)
            componentArr[i] = Reflection.toLuaValue(components[i]);
        LuaValue componentList = LuaValue.listOf(componentArr);

        // from lua value
        Globals globals = JsePlatform.standardGlobals();
        globals.get("dofile").call(LuaValue.valueOf("oefening-3/oefening_3_3/src/main/java/resources/MovementUpdater.lua"));
        LuaValue updateFunction = globals.get("update");
        LuaValue updatedComponents = updateFunction.call(componentList);

        return (MovementComponent[]) Reflection.fromLuaValue(updatedComponents, MovementComponent[].class);
    }
}
