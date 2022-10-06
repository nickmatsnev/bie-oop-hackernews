package commands

case class CommandOptions(
                           start: Int = -1,
                           end: Int = -1,
                           page: Int = -1,
                           ttl: Int = 600,
                           withComments: Int = 0, // when 1 with comments
                           showSize: Int = 10, // number of elements shown
                           showTime: Int = 10000 // miliseconds to watch
                         )
