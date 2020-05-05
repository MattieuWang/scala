import akka.actor.Actor

import scala.concurrent.Future
import scala.util.{Failure, Success}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.control.NonFatal

/**
 * Do you see anything that could lead to potential problems ?
 * What would you do to fix it ?
 * Do not mind about the not implemented code
 */
class WhatsWrong3 extends Actor {

  var internalState = "internal state"

  def receive: Receive = {
    case "a query" => {
      val requestF: Future[String] = queryAsyncServer()
      val doRequest = requestF.flatMap{
        r => handleResponse(r).recover{
          case NonFatal(e) =>  r
        }
      }.recover{
        case NonFatal(e) => requestF
      }

      /*
      if the requestF fails to get a queryAsyncServer(), we return an empty requestF
      the flapMap part will not execute

      if the method handResponse fails, we just return the original response r.

      So in a measure, we can recover the error in the chain of future operation

       */

//      requestF.onComplete {
//        case Success(r) => handleResponse(r)
//
//          /*  here we should use NonFatal() */
//        case Failure(NonFatal(e)) => e.printStackTrace()
//      }
    }
  }

  def handleResponse(r: String): Future[String] = ??? // mutate internal state

  def queryAsyncServer(): Future[String] = ???
}
//        case Failure(e) => e.printStackTrace()