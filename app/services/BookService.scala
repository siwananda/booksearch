package services

import repositories.BookRepository
import com.google.inject.Inject
import models.Book

import scala.concurrent.Future

class BookService @Inject()(bookRepository: BookRepository) {

  def findById(bookId: String): Future[Book] = bookRepository.findById(bookId)

  def findAll(): Future[Seq[Book]] = bookRepository.find()

  def searchText(searchString: String): Future[Seq[Book]] = bookRepository.searchText(searchString)
}
