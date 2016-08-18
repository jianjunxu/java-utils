package com.jxlx.jayden.guava;

import com.google.common.base.*;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import java.util.List;
import java.util.Set;

/**
 * User : jianjun.xu
 * Date : 2016/8/17
 * Time : 16:03
 * Desc : 字符串处理：分割，连接，填充
 */
public class StringOper {
    /**
     * 常用.连接器[Joiner]
     * joiner实例总是不可变的。用来定义joiner目标语义的配置方法总会返回一个新的joiner实例。这使得joiner实例都是线程安全的，你可以将其定义为static final常量。
     */
    public static void joinArr() {
        List<String> list = Lists.newArrayList("Harry", null, "Ron", "Hermione");
        String joinSkipNull = Joiner.on(",").skipNulls().join(list).toString();
        String joinUseForNull = Joiner.on(",").useForNull("null").join(list).toString();
        System.out.println("joinSkipNull:" + joinSkipNull + "\r\njoinUseForNull:" + joinUseForNull);
    }

    /**
     * 拆分器[Splitter]
     * Splitter可以被设置为按照任何模式(正则)、字符、字符串或字符匹配器拆分。
     */
    public static void splitterStr() {
        String str = "Harry,null,,Ron,Hermione";
        Iterable<String> iterator = Splitter.on(",")
                .trimResults() // 移除结果字符串的前导空白和尾部空白
                .omitEmptyStrings() // 从结果中自动忽略空字符串
                .split(str);
        List<String> list = Lists.newArrayList(iterator);
        System.out.println("result:" + list);
    }

    /**
     * CharMatcher实例就是对字符的布尔判断
     * ——CharMatcher确实也实现了Predicate<Character>
     * ——但类似”所有空白字符”或”所有小写字母”的需求太普遍了，Guava因此创建了这一API。
     */
    public static void charMatcher() {
        String string = "  sada 5sSd  dss2R 3OJ43  ";
        String noControl = CharMatcher.JAVA_ISO_CONTROL.removeFrom(string); //移除control字符
        String theDigits = CharMatcher.DIGIT.retainFrom(string); //只保留数字字符
        String spaced = CharMatcher.WHITESPACE.trimAndCollapseFrom(string, ' ');//去除两端的空格，并把中间的连续空格替换成单个空格
        String noDigits = CharMatcher.JAVA_DIGIT.replaceFrom(string, "*"); //用*号替换所有数字
        String lowerAndDigit = CharMatcher.JAVA_DIGIT.or(CharMatcher.JAVA_LOWER_CASE).retainFrom(string);// 只保留数字和小写字母

        System.out.println("noControl:" + noControl);
        System.out.println("theDigits:" + theDigits);
        System.out.println("spaced:" + spaced);
        System.out.println("noDigits:" + noDigits);
        System.out.println("lowerAndDigit:" + lowerAndDigit);
    }

    /**
     * Charsets
     * Charsets针对所有Java平台都要保证支持的六种字符集提供了常量引用。尝试使用这些常量，而不是通过名称获取字符集实例。
     */
    public static void charsets() {
        String string = "  sada 5sSd  dss2R 3OJ43  ";
        byte[] bytes = string.getBytes(Charsets.UTF_8);
        System.out.println(bytes);
    }

    /**
     * CaseFormat被用来方便地在各种ASCII大小写规范间转换字符串——比如，编程语言的命名规范。
     * 场景：如http请求中参数可能是user_id之类的命名规则，而在对象中是userId，这时需要转换。
     * <p/>
     * LOWER_CAMEL	lowerCamel
     * LOWER_HYPHEN	lower-hyphen
     * LOWER_UNDERSCORE	lower_underscore
     * UPPER_CAMEL	UpperCamel
     * UPPER_UNDERSCORE	UPPER_UNDERSCORE
     */
    public static void caseFormat() {
        String result = CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, "CONSTANT_NAME"); // returns "constantName"
        System.out.println("result:" + result);
    }

	/**
	 * Strings相关
	 * Strings.isNullOrEmpty
	 * Strings.emptyToNull
	 * Strings.nullToEmpty
	 * Strings.repeat 复制
	 * Strings.padStart 可用于日期补充
	 * Strings.commonPrefix 最长前缀
	 */
	public static void strings() {
		boolean isNullOrEmpty = Strings.isNullOrEmpty("");
		System.out.println("isNullOrEmpty:" + isNullOrEmpty);
		String emptyToNull = Strings.emptyToNull("");
		System.out.println("emptyToNull:" + emptyToNull);
		String nullToEmpty = Strings.nullToEmpty(null);
		System.out.println("nullToEmpty:" + nullToEmpty);
		String repeat = Strings.repeat("hey", 3);
		System.out.println("repeat:" + repeat);
		String padStart = Strings.padStart("6",2, '0');
		System.out.println("padStart:" + padStart);
		String commonPrefix = Strings.commonPrefix("commonPrefix","comm");
		System.out.println("commonPrefix:" + commonPrefix);
	}

    public static void main(String[] args) {
		Set<String> stringSet = Sets.newTreeSet();
		stringSet.add("山西");
		stringSet.add("北京");
		stringSet.add("上海");
		stringSet.add("天津");
		stringSet.add("云南");
		System.out.println(stringSet);
		System.out.println(Joiner.on(",").skipNulls().join(stringSet));
		//上海,云南,北京,天津,山西

        strings();
    }
}
