package com.mcxiaoke.koi.ext

/**
 * User: mcxiaoke
 * Date: 16/1/30
 * Time: 11:29
 */

fun Double.sin(): Double = Math.sin(this)

fun Double.cos(): Double = Math.cos(this)
fun Double.tan(): Double = Math.tan(this)
fun Double.asin(): Double = Math.asin(this)
fun Double.acos(): Double = Math.acos(this)
fun Double.atan(): Double = Math.atan(this)
fun Double.toRadians(): Double = Math.toRadians(this)
fun Double.toDegrees(): Double = Math.toDegrees(this)
fun Double.exp(): Double = Math.exp(this)
fun Double.log(): Double = Math.log(this)
fun Double.log10(): Double = Math.log10(this)
fun Double.sqrt(): Double = Math.sqrt(this)
fun Double.cbrt(): Double = Math.cbrt(this)
fun Double.IEEEremainder(divisor: Double): Double = Math.IEEEremainder(this, divisor)
fun Double.ceil(): Double = Math.ceil(this)
fun Double.floor(): Double = Math.floor(this)
fun Double.rint(): Double = Math.rint(this)
fun Double.atan2(x: Double): Double = Math.atan2(this, x)
fun Double.pow(exp: Double): Double = Math.pow(this, exp)
fun Double.round(): Long = Math.round(this)
fun Double.abs(): Double = Math.abs(this)
fun Double.ulp(): Double = Math.ulp(this)
fun Double.signum(): Double = Math.signum(this)
fun Double.sinh(): Double = Math.sinh(this)
fun Double.cosh(): Double = Math.cosh(this)
fun Double.tanh(): Double = Math.tanh(this)
fun Double.expm1(): Double = Math.expm1(this)
fun Double.log1p(): Double = Math.log1p(this)
fun Double.copySign(sign: Double): Double = Math.copySign(this, sign)
fun Double.exponent(): Int = Math.getExponent(this)
fun Double.next(direction: Double): Double = Math.nextAfter(this, direction)
fun Double.nextUp(): Double = Math.nextUp(this)
fun Double.scalb(scaleFactor: Int): Double = Math.scalb(this, scaleFactor)
fun Double.clamp(min: Double, max: Double): Double = Math.max(min, Math.min(this, max))
