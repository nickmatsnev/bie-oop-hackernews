package commands

/**
 * @param start - number of first wanted to be shown item
 * @param end - number of last wanted to be shown item
 * @param page - number of wanted to be shown item
 * @param ttl - time to live(used for cache)
 * @param withComments - 1 if comments shoud be on, 0 if not
 * @param showSize - integer for amount of items wanted to be shown per period
 * @param showTime - integer for the length of period in seconds
 */
case class CommandOptions(
                           start: Int = -1,
                           end: Int = -1,
                           page: Int = -1,
                           ttl: Int = 600, // time to live
                           withComments: Int = 0, // when 1 with comments
                           showSize: Int = 10, // number of elements shown
                           showTime: Int = 10000 // miliseconds to watch
                         )
