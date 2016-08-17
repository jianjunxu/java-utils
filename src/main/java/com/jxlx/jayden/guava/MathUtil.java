package com.jxlx.jayden.guava;

/**
 * @author jianjun.xu
 * @descriptionGuava Math针对各种不常见的溢出情况都有充分的测试；对溢出语义，Guava文档也有相应的说明；如果运算的溢出检查不能通过，将导致快速失败；
 * Guava Math的性能经过了精心的设计和调优；虽然性能不可避免地依据具体硬件细节而有所差异，但Guava Math的速度通常可以与Apache Commons的MathUtils相比，在某些场景下甚至还有显著提升；
 * Guava Math在设计上考虑了可读性和正确的编程习惯；IntMath.log2(x, CEILING) 所表达的含义，即使在快速阅读时也是清晰明确的。而32-Integer.numberOfLeadingZeros(x – 1)对于阅读者来说则不够清晰。
 *
 * http://ifeve.com/google-guava-math/
 * @email jianjun.xu@vipshop.com
 * @date 16/8/17 下午11:04
 */
public class MathUtil {
}
