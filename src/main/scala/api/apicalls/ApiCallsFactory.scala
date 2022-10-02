package api.apicalls

object ApiCallsFactory {
  def create(): ApiCallsImplementation ={
    new ApiCallsImplementation()
  }
}
