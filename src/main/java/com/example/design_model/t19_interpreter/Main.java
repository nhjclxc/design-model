package com.example.design_model.t19_interpreter;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * 解释器模式 interpreter pattern：
 *  1、在编译原理中，一个算术表达式通过词法分析器形成词法单元，而后这些词法单元再通过语法分析器构建语法分析树
 *      最终形成一颗抽象的语法分析树。这里的词法分析器和语法分析器都可以看作是解析器
 *  2、解释器模式：是指给定一个语言（表达式），定义它的文法的一种表示，并定义一个解释器，
 *      使用该解释器来解释语言中的句子（表达式）
 *  3、应用场景：
 *      将一个需要解释指向的语言中的句子表示为一个抽象语法树
 *      一个重复出现的问题可以用一种简单的语言来表达
 *    如：编译器、运算表达式、正则表达式、机器人。。。
 *
 * @author LuoXianchao
 * @since 2023/5/14 21:10
 */
public class Main {
    public static void main(String[] args) {
        // 构建表达式
        String expreString = "a+b-c+d"; //"a+b-c";

        // 构建表达式对应的值
        Map<String, Integer> var = new HashMap<>();
        var.put("a", 10);
        var.put("b", 20);
        var.put("c", -20);
        var.put("d", 50);

        // 执行计算
        int result = CalculatorUtils.excute(expreString, var);
        System.out.println(result);

    }
}

/**
 * 4、计算器类
 */
class CalculatorUtils {
    private CalculatorUtils() {}

    /**
     * 分析字符串表达式， 得到对象的Expression对象
     * @param expreString 字符串表达式
     * @return Expression对象
     */
    private static Expression analyticExpression(String expreString){
        // 栈用来存储表达式，可能是变量表达式，也可能是操作符表达式
        Stack<Expression> stack = new Stack<>();
        // 将字符串表达式转化为字符数组，用于遍历每一个字符，的到其对应表达式
        char[] chars = expreString.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if ('+' == chars[i]){
                // 加法左边的值：弹出栈顶的一个数据，用于和当前的加法表达式结合成一个新的表达式，
                Expression leftVar = stack.pop();
                // 加法右边的值：获取加法表达式右边的一个数据，
                Expression rightVar = new VarExpression(String.valueOf(chars[++i]));
                // 将上面的两个遍历表达式构建到加法表达式里面，同时将加法表达式压栈，这里一下就消耗了字符数组的三个值
                // 将者三个值构建成一个新的表达式放入栈中，便于和后面的表达式结合
                stack.push(new AddSymbolExpression(leftVar, rightVar));
            }else if ('-' == chars[i]){
                Expression leftVar = stack.pop();
                Expression rightVar = new VarExpression(String.valueOf(chars[++i]));
                stack.push(new SubSymbolExpression(leftVar, rightVar));
            }else {
                // 如果当前的字符数组值不是操作符，那么就是数值，直接压栈
                stack.push(new VarExpression(String.valueOf(chars[i])));
            }
        }
        // 当遍历完字符数组之后，当且仅当栈内只剩一个元素时，表示解析表达式正确，否则表达式解析失败异常
        Expression expression = stack.pop();
        if (stack.size() > 0){
            throw new UnsupportedOperationException("表达式解析错误");
        }
        return expression;
    }

    /**
     * 执行计算
     * @param expreString 字符串表达式
     * @param var 对应的值
     * @return 计算结果
     */
    public static int excute(String expreString, Map<String, Integer> var){
        return analyticExpression(expreString).interpreter(var);
    }
}

/**
 * 1、抽象表达式类
 */
abstract class Expression{
    /**
     * a+b-c+d，10+20-(-20)+50
     * Map<key, value>存储着表达式对象的具体数据
     *  key：就是"a"、"b"、"c"、"d"
     *  value： 10、20、-20、50
     *      即  {"a": 10, "b": 20, "c": -20, "d": 50}
     */
    public abstract int interpreter(Map<String, Integer> var);
}
/**
 * 2、变量表达式
 */
class VarExpression extends Expression{
    /**
     * key表示下面的那个map的key，之后要通过key到map里面获取对应的数据
     */
    private final String key;
    public VarExpression(String key) {
        this.key = key;
    }
    /**
     * 数值解析器的interpreter方法就是获取该key再map中对应的值
     * @param var map保存所有表达式变量对应的值
     * @return 本对象的key对应的值
     */
    @Override
    public int interpreter(Map<String, Integer> var) {
        return var.get(this.key);
    }
}
/**
 * 3、符号表达式
 */
abstract class SymbolExpression extends Expression{
    /**
     * 符号表达式 要保存这个符号的左右两个变量的值
     */
    protected final Expression left;
    protected final Expression right;
    public SymbolExpression(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }
    @Override
    public abstract int interpreter(Map<String, Integer> var);
}
/**
 * 3.1、加法符号表达式
 */
class AddSymbolExpression extends SymbolExpression{
    public AddSymbolExpression(Expression left, Expression right) {
        super(left, right);
    }
    @Override
    public int interpreter(Map<String, Integer> var) {
        return super.left.interpreter(var) + super.right.interpreter(var);
    }
}
/**
 * 3.2、减法符号表达式
 */
class SubSymbolExpression extends SymbolExpression{
    public SubSymbolExpression(Expression left, Expression right) {
        super(left, right);
    }
    @Override
    public int interpreter(Map<String, Integer> var) {
        return super.left.interpreter(var) - super.right.interpreter(var);
    }
}
