package com.example.akkaTest

import akka.actor.ActorSystem
import akka.event.Logging
import akka.{Done, NotUsed, stream}
import akka.stream.{ActorMaterializer, FlowShape, SourceShape, scaladsl}
import akka.stream.scaladsl.{BidiFlow, Flow, Keep, RunnableGraph, Sink, Source}
import akka.util.ByteString

import scala.concurrent.Future


/**
 * Question about Akka Stream framework https://doc.akka.io/docs/akka/current/stream/index.html
 *
 * Complete the code (replace the ???)
 */

object BasicStream {
  def main(args: Array[String]): Unit = {
    implicit val system = ActorSystem("ActorSystem")
    implicit val materializer = ActorMaterializer()



    val numbers = 1 to 1000

    val numberSource: Source[Int, NotUsed] = Source.fromIterator(() => numbers.iterator)

    //Only let pass even number through the flow
    val isEvenFlow: Flow[Int, Int, NotUsed] = Flow[Int].filter(n=>n%2==0)

    //Create a Source of even numbers by combining the number Source with the even Flow
    val evenNumberSource: Source[Int, NotUsed] = numberSource.via(isEvenFlow)

    //A Sink that will write its input onto the console
    val consoleSink: Sink[Int, Future[Done]] = Sink.foreach[Int](x=>println(x))

    //Connect the Source with the Sink and run it
    val graph : RunnableGraph[NotUsed] = numberSource.via(isEvenFlow).to(consoleSink)
    val graph2 : RunnableGraph[NotUsed] = evenNumberSource.to(consoleSink)

    graph.run()

//    graph2.run()

    system.terminate()

  }
}