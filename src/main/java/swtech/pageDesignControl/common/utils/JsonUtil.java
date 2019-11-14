package swtech.pageDesignControl.common.utils;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import net.sf.ezmorph.bean.MorphDynaBean;
import net.sf.json.JSONObject;


/**
 * JSON 即 JavaScript Object Natation，它是一种轻量级的数据交换格式 除了字符 "，\，/
 * 和一些控制符（\b，\f，\n，\r，\t）需要编码外，其他 Unicode 字符可以直接输出 Object 对象在 JSON 中是用 {}
 * 包含一系列无序的 Key-Value 键值对表示的，实际上此处的 Object 相当于 Java 中的 Map<String, Object>，而不是
 * Java 的 Class 。注意 Key 只能用 String 表示。
 *
 * Prototype提供了 evalJSON() 方法，能直接将服务器返回的 JSON 文本变成一个 JavaScript 变量
 */
public final class JsonUtil {
    public static String toJson(final Object o) {
        return toJson(o, false);
    }

    /**
     * 把对象封装为JSON格式
     *
     * @param o 对象
     * @param showAllProperty 是否忽略空属性
     * @return JSON格式
     */
    @SuppressWarnings("unchecked")
    public static String toJson(final Object o, boolean showAllProperty) {
        if (o == null) {
            return "null";
        }
        if (o instanceof String) // String
        {
            return string2Json((String) o);
        }
        if (o instanceof Boolean) // Boolean
        {
            return boolean2Json((Boolean) o);
        }
        if (o instanceof Number) // Number
        {
            return number2Json((Number) o);
        }
        if (o instanceof Map) // Map
        {
            return map2Json((Map<String, Object>) o, showAllProperty);
        }
        if (o instanceof Collection) // List Set
        {
            return collection2Json((Collection) o, showAllProperty);
        }
        if (o instanceof Object[]) // 对象数组
        {
            return array2Json((Object[]) o, showAllProperty);
        }

        if (o instanceof int[])// 基本类型数组
        {
            return intArray2Json((int[]) o);
        }
        if (o instanceof boolean[])// 基本类型数组
        {
            return booleanArray2Json((boolean[]) o);
        }
        if (o instanceof long[])// 基本类型数组
        {
            return longArray2Json((long[]) o);
        }
        if (o instanceof float[])// 基本类型数组
        {
            return floatArray2Json((float[]) o);
        }
        if (o instanceof double[])// 基本类型数组
        {
            return doubleArray2Json((double[]) o);
        }
        if (o instanceof short[])// 基本类型数组
        {
            return shortArray2Json((short[]) o);
        }
        if (o instanceof byte[])// 基本类型数组
        {
            return byteArray2Json((byte[]) o);
        }
        if (o instanceof MorphDynaBean) {
            throw new RuntimeException("不支持的类型: " + o.getClass().getName());
        }
        if (o instanceof Object) // 保底收尾对象
        {
            return object2Json(o, showAllProperty);
        }

        throw new RuntimeException("不支持的类型: " + o.getClass().getName());
    }

    /**
     * 将 String 对象编码为 JSON格式，只需处理好特殊字符
     *
     * @param s
     *            String 对象
     * @return JSON格式
     */
    static String string2Json(final String s) {
        final StringBuilder sb = new StringBuilder(s.length() + 20);
        sb.append('\"');
        for (int i = 0; i < s.length(); i++) {
            final char c = s.charAt(i);
            switch (c) {
                case '\"':
                    sb.append("\\\"");
                    break;
                case '\\':
                    sb.append("\\\\");
                    break;
                case '/':
                    sb.append("\\/");
                    break;
                case '\b':
                    sb.append("\\b");
                    break;
                case '\f':
                    sb.append("\\f");
                    break;
                case '\n':
                    sb.append("\\n");
                    break;
                case '\r':
                    sb.append("\\r");
                    break;
                case '\t':
                    sb.append("\\t");
                    break;
                default:
                    sb.append(c);
            }
        }
        sb.append('\"');
        return sb.toString();
    }

    /**
     * 将 Number 表示为 JSON格式
     *
     * @param number
     *            Number
     * @return JSON格式
     */
    static String number2Json(final Number number) {
        return number.toString();
    }

    /**
     * 将 Boolean 表示为 JSON格式
     *
     * @param bool
     *            Boolean
     * @return JSON格式
     */
    static String boolean2Json(final Boolean bool) {
        return bool.toString();
    }

    /**
     * 将 Collection 编码为 JSON 格式 (List,Set)
     *
     * @param c
     * @return
     */
    static String collection2Json(final Collection<Object> c, boolean showAllProperty) {
        final Object[] arrObj = c.toArray();
        return toJson(arrObj, showAllProperty);
    }

    /**
     * 将 Map<String, Object> 编码为 JSON 格式
     *
     * @param map
     * @return
     */
    static String map2Json(final Map<String, Object> map, boolean showAllProperty) {
        if (map.isEmpty()) {
            return "{}";
        }
        final StringBuilder sb = new StringBuilder(map.size() << 4); // 4次方
        sb.append('{');
        final Set<String> keys = map.keySet();
        for (final String key : keys) {
            final Object value = map.get(key);
            if (!showAllProperty && value == null) {
                continue;
            }
            sb.append('\"');
            sb.append(key); // 不能包含特殊字符
            sb.append('\"');
            sb.append(':');
            sb.append(toJson(value, showAllProperty)); // 循环引用的对象会引发无限递归
            sb.append(',');
        }
        // 将最后的 ',' 变为 '}':
        sb.setCharAt(sb.length() - 1, '}');
        return sb.toString();
    }

    /**
     * 将数组编码为 JSON 格式
     *
     * @param array
     *            数组
     * @return JSON 格式
     */
    static String array2Json(final Object[] array, boolean showAllProperty) {
        if (array.length == 0) {
            return "[]";
        }
        final StringBuilder sb = new StringBuilder(array.length << 4); // 4次方
        sb.append('[');
        for (final Object o : array) {
            sb.append(toJson(o, showAllProperty));
            sb.append(',');
        }
        // 将最后添加的 ',' 变为 ']':
        sb.setCharAt(sb.length() - 1, ']');
        return sb.toString();
    }

    static String intArray2Json(final int[] array) {
        if (array.length == 0) {
            return "[]";
        }
        final StringBuilder sb = new StringBuilder(array.length << 4);
        sb.append('[');
        for (final int o : array) {
            sb.append(Integer.toString(o));
            sb.append(',');
        }
        // set last ',' to ']':
        sb.setCharAt(sb.length() - 1, ']');
        return sb.toString();
    }

    static String longArray2Json(final long[] array) {
        if (array.length == 0) {
            return "[]";
        }
        final StringBuilder sb = new StringBuilder(array.length << 4);
        sb.append('[');
        for (final long o : array) {
            sb.append(Long.toString(o));
            sb.append(',');
        }
        // set last ',' to ']':
        sb.setCharAt(sb.length() - 1, ']');
        return sb.toString();
    }

    static String booleanArray2Json(final boolean[] array) {
        if (array.length == 0) {
            return "[]";
        }
        final StringBuilder sb = new StringBuilder(array.length << 4);
        sb.append('[');
        for (final boolean o : array) {
            sb.append(Boolean.toString(o));
            sb.append(',');
        }
        // set last ',' to ']':
        sb.setCharAt(sb.length() - 1, ']');
        return sb.toString();
    }

    static String floatArray2Json(final float[] array) {
        if (array.length == 0) {
            return "[]";
        }
        final StringBuilder sb = new StringBuilder(array.length << 4);
        sb.append('[');
        for (final float o : array) {
            sb.append(Float.toString(o));
            sb.append(',');
        }
        // set last ',' to ']':
        sb.setCharAt(sb.length() - 1, ']');
        return sb.toString();
    }

    static String doubleArray2Json(final double[] array) {
        if (array.length == 0) {
            return "[]";
        }
        final StringBuilder sb = new StringBuilder(array.length << 4);
        sb.append('[');
        for (final double o : array) {
            sb.append(Double.toString(o));
            sb.append(',');
        }
        // set last ',' to ']':
        sb.setCharAt(sb.length() - 1, ']');
        return sb.toString();
    }

    static String shortArray2Json(final short[] array) {
        if (array.length == 0) {
            return "[]";
        }
        final StringBuilder sb = new StringBuilder(array.length << 4);
        sb.append('[');
        for (final short o : array) {
            sb.append(Short.toString(o));
            sb.append(',');
        }
        // set last ',' to ']':
        sb.setCharAt(sb.length() - 1, ']');
        return sb.toString();
    }

    static String byteArray2Json(final byte[] array) {
        if (array.length == 0) {
            return "[]";
        }
        final StringBuilder sb = new StringBuilder(array.length << 4);
        sb.append('[');
        for (final byte o : array) {
            sb.append(Byte.toString(o));
            sb.append(',');
        }
        // set last ',' to ']':
        sb.setCharAt(sb.length() - 1, ']');
        return sb.toString();
    }

    public static String object2Json(final Object bean, boolean showAllProperty) {
        // 数据检查
        if (bean == null) {
            return "{}";
        }
        final Method[] methods = bean.getClass().getMethods(); // 方法数组
        final StringBuilder sb = new StringBuilder(methods.length << 4); // 4次方
        sb.append('{');

        for (final Method method : methods) {
            try {
                final String name = method.getName();

                String key = "";
                if (name.startsWith("get")) {
                    key = name.substring(3);

                    // 防死循环
                    final String[] arrs = { "Class"};
                    boolean bl = false;
                    for (final String s : arrs) {
                        if (s.equals(key)) {
                            bl = true;
                            continue;
                        }
                    }
                    if (bl) {
                        continue; // 防死循环
                    }
                } else if (name.startsWith("is")) {
                    key = name.substring(2);
                }
                if (key.length() > 0 && Character.isUpperCase(key.charAt(0))
                        && method.getParameterTypes().length == 0) {
                    if (key.length() == 1) {
                        key = key.toLowerCase();
                    } else if (!Character.isUpperCase(key.charAt(1))) {
                        key = key.substring(0, 1).toLowerCase()
                                + key.substring(1);
                    }

                    final Object elementObj = method.invoke(bean);
                    if (!showAllProperty && elementObj == null) {
                        continue;
                    }

                    sb.append('\"');
                    sb.append(key); // 不能包含特殊字符
                    sb.append('\"');
                    sb.append(':');
                    sb.append(toJson(elementObj, showAllProperty)); // 循环引用的对象会引发无限递归
                    sb.append(',');
                }
            } catch (final Exception e) {
                e.printStackTrace();
                throw new RuntimeException("在将bean封装成JSON格式时异常："
                        + e.getMessage(), e);
            }
        }
        if (sb.length() == 1) {
            return bean.toString();
        } else {
            sb.setCharAt(sb.length() - 1, '}');
            return sb.toString();
        }
    }


    /**
     * 从json HASH表达式中获取一个map，该map支持嵌套功能
     *
     * @param jsonString
     * @return
     */
    public static Map getMap4Json(String jsonString) {
        JSONObject jsonObject = JSONObject.fromObject(jsonString);
        Iterator keyIter = jsonObject.keys();
        String key;
        Object value;
        Map valueMap = new HashMap();

        while (keyIter.hasNext()) {
            key = (String) keyIter.next();
            value = jsonObject.get(key);
            valueMap.put(key, value);
        }

        return valueMap;
    }

    /***
     * 将JSON对象转换为传入类型的对象
     * @param <T>
     * @param jsonObject
     * @param beanClass
     * @return
     */
    public static <T> T toBean(JSONObject jsonObject, Class<T> beanClass)
    {
        return (T) JSONObject.toBean(jsonObject, beanClass);
    }

    /***
     * 将将对象转换为传入类型的对象
     * @param <T>
     * @param object
     * @param beanClass
     * @return
     */
    public static <T> T toBean(Object object, Class<T> beanClass)
    {
        JSONObject jsonObject = JSONObject.fromObject(object);

        return (T) JSONObject.toBean(jsonObject, beanClass);
    }


    /***
     * 将JSON文本反序列化为主从关系的实体
     * @param <T> 主实体类型
     * @param jsonString JSON文本
     * @param mainClass 主实体类型
     * @param detailClass 存放了多个从实体在主实体中属性名称和类型
     * @return
     */
    public static <T> T toBean(String json, Class<T> mainClass,
                               HashMap<String, Class> detailClass)
    {
        JSONObject jsonObject = JSONObject.fromObject(json);

        return (T) JSONObject.toBean(jsonObject, mainClass, detailClass);
    }


    private JsonUtil() {
    }

}
