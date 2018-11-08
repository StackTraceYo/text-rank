package scala.org.stacktrace.yo.textrank

import java.util.Properties

import edu.stanford.nlp.ling.CoreAnnotations.{PartOfSpeechAnnotation, SentencesAnnotation, TextAnnotation, TokensAnnotation}
import edu.stanford.nlp.ling.CoreLabel
import edu.stanford.nlp.pipeline.{Annotation, StanfordCoreNLP}
import edu.stanford.nlp.util.CoreMap

import scala.collection.JavaConverters._

object TextRank extends App {


  val props = new Properties()
  props.put("annotators", "tokenize, ssplit, pos")
  val pipeline = new StanfordCoreNLP(props)

  val doc = "Harry Potter awakens from a nightmare wherein a man named Frank Bryce is killed after overhearing Lord Voldemort conspiring with Peter Pettigrew and another man. While Harry attends the Quidditch World Cup between Ireland and Bulgaria with the Weasleys and Hermione Granger. Death Eaters terrorise the camp, and the man who appeared in Harry's dream summons the Dark Mark.\nAt Hogwarts, Albus Dumbledore introduces ex-Auror Alastor \"Mad-Eye\" Moody as the new Defense Against the Dark Arts teacher. He also announces that the school will host the legendary event known as the Triwizard Tournament, in which three magical schools compete across three dangerous challenges. The Goblet of Fire selects \"champions\" to take part in the competition: Cedric Diggory of Hufflepuff representing Hogwarts, Viktor Krum representing the Durmstrang Institute from Central Europe, and Fleur Delacour representing Beauxbatons Academy of Magic from France. The Goblet then unexpectedly selects Harry as a fourth champion. Dumbledore is unable to pull the underage Harry out of the tournament, as Ministry official Barty Crouch Sr. insists that the champions are bound by a contract after being selected.\nFor the first task, each champion must retrieve a golden egg guarded by the dragon they pick. Harry succeeds in retrieving the egg, which contains information about the second challenge. Shortly after, a formal dance event known as the Yule Ball takes place; Harry's crush Cho Chang attends with Cedric, and Hermione attends with Viktor, making Ron jealous. During the second task, the champions must dive underwater to rescue their mates. Harry finishes third, but is promoted to second behind Cedric due to his \"moral fibre\", after saving Fleur's sister Gabrielle as well as Ron. Afterwards, Harry discovers the corpse of Crouch Sr. in the forest. Later, while waiting for Dumbledore in his office, Harry discovers a Pensieve, which holds Dumbledore's memories. Harry witnesses a trial in which Igor confesses to the Ministry of Magic names of other Death Eaters, after Voldemort's defeat. When he names Severus Snape as one, Dumbledore vouches for Snape's innocence; Snape turned spy against Voldemort before the latter's downfall. After Karkaroff names Barty Crouch Jr., a devastated Crouch Sr. imprisons his son in Azkaban. Exiting the Pensieve, Harry realizes that Crouch Jr. is the man he saw in his dream.\nFor the final task, the champions must reach the Triwizard Cup, located in a hedge maze. Viktor, under the influence of the Imperius Curse, accidentally incapacitates Fleur. After Harry saves Cedric when the maze attacks him, the two claim a draw and together grab the cup, which turns out to be a Portkey and transports them to a graveyard where Pettigrew and Voldemort are waiting. Pettigrew kills Cedric with the Killing Curse, and performs a ritual that rejuvenates Voldemort, who then summons the Death Eaters. Voldemort releases Harry in order to beat him in a duel and prove he is the better wizard. Unable to defend himself, Harry tries the Expelliarmus charm at the same moment that Voldemort attempts the Killing Curse. The beams from their wands entwine, and Voldemort's wand disgorges the last spells it performed. The spirits of the people he murdered materialize in the graveyard, including Harry's parents and Cedric. This distracts Voldemort and his Death Eaters, allowing Harry to escape with Cedric's body by grabbing the Portkey.\nHarry tells Dumbledore that Voldemort returned and killed Cedric. Moody takes Harry back to his office to interrogate him about Voldemort, but inadvertently blows his cover by asking Harry whether there were \"others in the graveyard\", despite Harry not mentioning a graveyard. Moody reveals that he submitted Harry's name to the Goblet of Fire and manipulated Harry throughout the tournament to ensure he would win. Moody attempts to attack Harry, but Dumbledore, Snape, and Minerva McGonagall intervene and subdue him. The teachers force Moody to drink Veritaserum, a truth-telling potion, and he reveals that the real Moody is imprisoned in a magical trunk. The impostor's Polyjuice Potion wears off, revealing him as Crouch Jr., who is then returned to Azkaban.\nDumbledore reveals to the students that Voldemort killed Cedric, although the Ministry of Magic opposes the revelation. Later, Dumbledore visits Harry in his dormitory, apologizing to him for the dangers he endured. Harry reveals that he saw his parents in the graveyard; Dumbledore names this effect as \"Priori Incantatem\". Soon after Hogwarts, Durmstrang, and Beauxbatons bid farewell to each other."

  val doc_an = new Annotation(doc)
  pipeline.annotate(doc_an)

  val sentences = doc_an.get(classOf[SentencesAnnotation]).asScala.toList


  (for {
    sentence: CoreMap <- sentences
    token: CoreLabel <- sentence.get(classOf[TokensAnnotation]).asScala.toList
    word: String = token.get(classOf[TextAnnotation])
    pos: String = token.get(classOf[PartOfSpeechAnnotation])

  } yield (token, word, pos)) foreach (t => println("token: " + t._1 + " word: " + t._2 + " pos: " + t._3))

}