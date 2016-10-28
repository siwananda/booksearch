package bootstrap

import com.google.inject.Inject
import models.{Book, BookProvider}
import org.mongodb.scala.BulkWriteResult
import play.Logger
import repositories.{BookRepository, Mongo}


class BootstrapBooks @Inject()(mongo: Mongo, bookRepository: BookRepository) {

  def preMessages(): Unit = {
    Logger.info("Bootstrapping book list..")
  }

  /**
    * Bootstraps initial list of books into the database.
    */
  def bootstrapBooks(): Unit = {
    val bookList: List[Book] = List(
      Book(
        "1607741180",
        "The blue bottle craft of coffee",
        "One of the country's most celebrated roasters explains how to choose, brew, and enjoy the new breed of artisan coffees at home, along with 40 inventive recipes that incorporate coffee or taste good with a cup.",
        "random author",
        List(
          BookProvider(
            "AMZN",
            "amazon",
            "https://www.amazon.com/The-Blue-Bottle-Craft-Coffee/dp/1607741180"
          ),
          BookProvider(
            "GBK",
            "google book",
            "https://books.google.com/books?isbn=1607741180"
          )
        )
      ),
      Book(
        "1535283556",
        "101 Coolest Things to Do in Thailand",
        "Forget those long and boring guidebooks! 101 Coolest Things to Do in Thailand cuts out the nonsense and gives you all the essential information for traveling in Thailand that you really want. ",
        "somewhere",
        List(
          BookProvider(
            "AMZN",
            "amazon",
            "https://www.amazon.com/Thailand-Travel-Coolest-Things-Bangkok/dp/1535283556"
          )
        )
      ),
      Book(
        "1477830146",
        "Pastel Orphans",
        "In 1930s Berlin, young Henrik, the son of a Jewish father and Aryan mother, watches the world around him crumbling: people are rioting in the streets, a strange yellow star begins appearing in shop windows, and friends are forced to move—or they simply disappear.",
        "Gemma Liviero",
        List(
          BookProvider(
            "AMZN",
            "amazon",
            "https://www.amazon.com/gp/product/1477830146"
          ),
          BookProvider(
            "GBK",
            "google book",
            "https://books.google.com/books?isbn=1477830146"
          )
        )
      ),
      Book(
        "030746489X",
        "Cooking for Jeffrey: A Barefoot Contessa Cookbook",
        "For America’s bestselling cookbook author Ina Garten there is no greater pleasure than cooking for the people she loves—and particularly for her husband, Jeffrey. She has been cooking for him ever since they were married forty-eight years ago, and the comforting, delicious meals they shared became the basis for her extraordinary career in food.",
        "Ina Garten",
        List(
          BookProvider(
            "AMZN",
            "amazon",
            "https://www.amazon.com/gp/product/030746489X"
          )
        )
      ),

      Book(
        "1419723448",
        "Double Down (Diary of a Wimpy Kid #11)",
        "The pressure’s really piling up on Greg Heffley. His mom thinks video games are turning his brain to mush, so she wants her son to put down the controller and explore his “creative side.”\n \nAs if that’s not scary enough, Halloween’s just around the corner and the frights are coming at Greg from every angle.\n \nWhen Greg discovers a bag of gummy worms, it sparks an idea. Can he get his mom off his back by making a movie . . . and will he become rich and famous in the process? Or will doubling down on this plan just double Greg’s troubles?",
        "Jeff Kinney",
        List(
          BookProvider(
            "AMZN",
            "amazon",
            "https://www.amazon.com/gp/product/1419723448"
          ),
          BookProvider(
            "GBK",
            "google book",
            "https://books.google.com/books?isbn=1419723448"
          )
        )
      ),
      Book(
        "0718079183",
        "The Magnolia Story",
        "These famous words are now synonymous with the dynamic husband-and-wife team Chip and Joanna Gaines, stars of HGTV’s Fixer Upper. As this question fills the airwaves with anticipation, their legions of fans continue to multiply and ask a different series of questions, like—Who are these people?What’s the secret to their success? And is Chip actually that funny in real life? By renovating homes in Waco, Texas, and changing lives in such a winsome and engaging way, Chip and Joanna have become more than just the stars of Fixer Upper, they have become America’s new best friends.",
        "Chip Gaines",
        List(
          BookProvider(
            "AMZN",
            "amazon",
            "https://www.amazon.com/gp/product/0718079183"
          )
        )
      ),

      Book(
        "0062409956",
        "Appetites: A Cookbook",
        "Anthony Bourdain is man of many appetites. And for many years, first as a chef, later as a world-traveling chronicler of food and culture on his CNN series Parts Unknown, he has made a profession of understanding the appetites of others. These days, however, if he’s cooking, it’s for family and friends.",
        "Anthony Bourdain",
        List(
          BookProvider(
            "AMZN",
            "amazon",
            "https://www.amazon.com/gp/product/0062409956"
          ),
          BookProvider(
            "GBK",
            "google book",
            "https://books.google.com/books?isbn=0062409956"
          )
        )
      ),
      Book(
        "1101907479",
        "Not Dead Yet: The Memoir",
        "Phil Collins pulls no punches—about himself, his life, or the ecstasy and heartbreak that’s inspired his music. In his much-awaited memoir, Not Dead Yet, he tells the story of his epic career, with an auspicious debut at age 11 in a crowd shot from the Beatles’ legendary film A Hard Day’s Night. A drummer since almost before he could walk, Collins received on the job training in the seedy, thrilling bars and clubs of 1960s swinging London before finally landing the drum seat in Genesis. Soon, he would step into the spotlight on vocals after the departure of Peter Gabriel and begin to stockpile the songs that would rocket him to international fame with the release of Face Value and “In the Air Tonight.” ",
        "Phil Collins",
        List(
          BookProvider(
            "AMZN",
            "amazon",
            "https://www.amazon.com/gp/product/1101907479"
          )
        )
      ),

      Book(
        "1682612880",
        "The Cellulite Myth: It's Not Fat, It's Fascia",
        "Forget everything you’ve ever been told about cellulite—it’s a myth!  Ashley Black, fascia pioneer, and body guru to the stars, unveils never before known secrets to obliterating cellulite and changing your personal health paradigm.  For years we’ve been conditioned to believe that cellulite is a fat problem, yet skinny girls have it, active girls have it, sedentary girls have it, curvy girls have it, older women have it and, guess what, so do younger women. In fact, 90% of women struggle with it . . . you are not alone! ",
        "Ashley Black",
        List(
          BookProvider(
            "AMZN",
            "amazon",
            "https://www.amazon.com/gp/product/1682612880"
          ),
          BookProvider(
            "GBK",
            "google book",
            "https://books.google.com/books?isbn=1682612880"
          )
        )
      ),
      Book(
        "0545790352",
        "Harry Potter and the Sorcerer's Stone: The Illustrated Edition ",
        "For the first time, J.K. Rowling's beloved Harry Potter books will be presented in lavishly illustrated full-color editions. Kate Greenaway-award-winning artist Jim Kay has created over 100 stunning illustrations, making this deluxe format a perfect gift as much for a child being introduced to the series, as for the dedicated fan.\n \nHarry Potter has never been the star of a Quidditch team, scoring points while riding a broom far above the ground. He knows no spells, has never helped to hatch a dragon, and has never worn a cloak of invisibility.",
        "J.K. Rowling",
        List(
          BookProvider(
            "AMZN",
            "amazon",
            "https://www.amazon.com/gp/product/0545790352"
          )
        )
      ),

      Book(
        "0761169083",
        "Atlas Obscura: An Explorer's Guide to the World's Hidden Wonders",
        "It's time to get off the beaten path. Inspiring equal parts wonder and wanderlust, Atlas Obscura celebrates over 700 of the strangest and most curious places in the world.\n\nTalk about a bucket list: here are natural wonders—the dazzling glowworm caves in New Zealand, or a baobob tree in South Africa that's so large it has a pub inside where 15 people can drink comfortably. Architectural marvels, including the M.C. Escher-like stepwells in India. Mind-boggling events, like the Baby Jumping Festival in Spain, where men dressed as devils literally vault over rows of squirming",
        "Joshua Foer",
        List(
          BookProvider(
            "AMZN",
            "amazon",
            "https://www.amazon.com/gp/product/0761169083"
          ),
          BookProvider(
            "GBK",
            "google book",
            "https://books.google.com/books?isbn=0761169083"
          )
        )
      )
    )

    bookRepository.bulkWrite(bookList).subscribe(
      (bwr: BulkWriteResult) => Logger.info(bwr.getInsertedCount.toString),
      (e: Throwable) => Logger.error(e.getMessage),
      () => Logger.info("Finished writing")
    )

  }

  def postMessages(): Unit = {
    Logger.info("Finished bootstraping book list..")
  }


  preMessages()
  bootstrapBooks()
  postMessages()

}
