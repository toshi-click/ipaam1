package click.toshi.ipaam1.util.ext

import click.toshi.ipaam1.model.Speaker

/**
 * Make sort key for search speakers list.
 *
 * sort key is speaker name that is upper cased and applied ignore-prefixes.
 * ex) @mike@github.net -> MIKE@GITHUB.NET
 */
fun Speaker.toSortKey(vararg ignorePrefixes: Char): String {
    return this.name
            .dropWhile { c -> ignorePrefixes.any { it.equals(c, true) } }
            .toUpperCase()
}
