package oefening_3_3;

import java.lang.reflect.Array;
import java.lang.reflect.Field;

import org.luaj.vm2.LuaTable;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.jse.CoerceJavaToLua;

public class Reflection {
    public static LuaValue toLuaValue(Object object) {
        Class<?> objectClass = object.getClass();
        Field[] fields = objectClass.getDeclaredFields();
        LuaTable table = new LuaTable();

        for (Field field : fields) {
            field.setAccessible(true);
            if (field.getType() == Float.TYPE) {
                try {
                    Object objectValue = field.get(object);
                    LuaValue value = CoerceJavaToLua.coerce(objectValue);
                    table.set(field.getName(), value);
                } catch (Exception e) {
                    System.err.println(e);
                    return null;
                }
            }
        }
        return table;
    }

    public static Object fromLuaValue(LuaValue lua, Class<?> classobj) {
        if (!classobj.isArray()) {
            throw new IllegalArgumentException("Expected an array class type");
        }

        Class<?> componentType = classobj.getComponentType();
        Object array = Array.newInstance(componentType, lua.length());

        for (int i = 0; i < lua.length(); i++) {
            try {
                Object element = componentType.getDeclaredConstructor().newInstance();
                Field ax = componentType.getDeclaredField("ax");
                Field ay = componentType.getDeclaredField("ay");
                Field vx = componentType.getDeclaredField("vx");
                Field vy = componentType.getDeclaredField("vy");

                ax.setAccessible(true);
                ay.setAccessible(true);
                vx.setAccessible(true);
                vy.setAccessible(true);

                ax.set(element, (float) lua.get(i + 1).get("ax").todouble());
                ay.set(element, (float) lua.get(i + 1).get("ay").todouble());
                vx.set(element, (float) lua.get(i + 1).get("vx").todouble());
                vy.set(element, (float) lua.get(i + 1).get("vy").todouble());

                Array.set(array, i, element);
            } catch (Exception e) {
                System.err.println("Error processing LuaValue to Java object - " + e);
            }
        }

        return array;
    }
}