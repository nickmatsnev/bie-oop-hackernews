object Main {
  // process args
  def main(args: Array[String]): Unit = {
    // You pass any thing at runtime
    // that will be print on the console
    for(arg<-args)
      println(arg)
  }
}
