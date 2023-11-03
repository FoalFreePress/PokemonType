/*
 * This is free and unencumbered software released into the public domain.
 *
 * Anyone is free to copy, modify, publish, use, compile, sell, or
 * distribute this software, either in source code form or as a compiled
 * binary, for any purpose, commercial or non-commercial, and by any
 * means.
 *
 * In jurisdictions that recognize copyright laws, the author or authors
 * of this software dedicate any and all copyright interest in the
 * software to the public domain. We make this dedication for the benefit
 * of the public at large and to the detriment of our heirs and
 * successors. We intend this dedication to be an overt act of
 * relinquishment in perpetuity of all present and future rights to this
 * software under copyright law.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
 * IN NO EVENT SHALL THE AUTHORS BE LIABLE FOR ANY CLAIM, DAMAGES OR
 * OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE,
 * ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
 * OTHER DEALINGS IN THE SOFTWARE.
 *
 * For more information, please refer to <https://unlicense.org>
 */

package net.shonx.pokemontype;

public enum Effectiveness {
    IMMUNE,
    ULTRA_RESISTS,
    RESISTS,
    EFFECTIVE,
    SUPER_EFFECTIVE,
    ULTRA_EFFECTIVE;

    public static Effectiveness get(double damage) {
        if (damage == 0)
            return IMMUNE;
        if (damage == 0.25)
            return ULTRA_RESISTS;
        if (damage == 0.5)
            return RESISTS;
        if (damage == 1)
            return EFFECTIVE;
        if (damage == 2)
            return SUPER_EFFECTIVE;
        if (damage == 4)
            return ULTRA_EFFECTIVE;
        throw new IllegalStateException("Invalid damage " + damage);
    }
}
