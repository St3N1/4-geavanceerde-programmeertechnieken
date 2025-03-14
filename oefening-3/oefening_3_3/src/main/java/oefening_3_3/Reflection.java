package oefening_3_3;

import org.luaj.vm2.LuaTable;
import org.luaj.vm2.LuaValue;

public class Reflection {
    public static LuaValue toLuaValue(Object object) {
        Class<?> class = object;
        Class.forName(object);
        LuaTable table = LuaTable.tableOf(object);
        return null;
    }
}