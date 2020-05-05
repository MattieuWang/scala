package com.example.akkaTest
import akka.actor.{Actor, ActorSystem, Props}
import akka.event.Logging

/**
 * Question about Akka framework http://akka.io
 *
 * When receiving a message that says "Hello", BasicActor must print "Hello there."
 * It must print "What?" when receiving any other message
 */
class BasicActor extends Actor {
  val log = Logging(context.system, this)
  override def receive: Receive = {
    case "Hello" => log.info("Hello there!");
    case _ => log.info("What?")

  }
}

object FireActor extends App {

  /**
   * Create an instance of BasicActor
   *
   * Make it print "Hello there." and "What?"
   */
  def fireActor(): Unit = {
    val system = ActorSystem("ActorSystem")
    val basic_actor = system.actorOf(Props[BasicActor], name = "basicActor")
    basic_actor ! "Hello"
    basic_actor ! "test"
    system.terminate()
  }
  fireActor()
}
