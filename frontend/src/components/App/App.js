import './App.css';
import React, {Component} from "react";
import {BrowserRouter as Router, Redirect, Route} from 'react-router-dom';
import Books from "../Books/BooksList/books";
import BookService from "../../repository/bookRepository";
import Categories from "../Categories/categories";
import Header from "../Header/header";
import BookAdd from "../Books/BookAdd/bookAdd";
import BookEdit from "../Books/BookEdit/bookEdit";


class App extends Component {

    constructor(props) {
        super(props);
        this.state = {
            books: [],
            categories: [],
            authors: [],
            selectedProduct: {}
        }
    }

  render() {
    return (
        <Router>
          <Header/>
          <main>
            <div className="container">
              <Route path={"/books/add"} exact render={() =>
                  <BookAdd categories={this.state.categories}
                           authors={this.state.authors}
                           onAddBook={this.addBook}/>}/>
              <Route path={"/books/edit/:id"} exact render={() =>
                  <BookEdit categories={this.state.categories}
                            authors={this.state.authors}
                            onEditBook={this.editBook}
                            product={this.state.selectedProduct}/>}/>
              <Route path={"/books"} exact render={() =>
                  <Books books={this.state.books}
                         onDelete={this.deleteBook}
                         onBorrow={this.borrowBook}/>}/>
              <Route path={"/categories"} exact render={() =>
              <Categories categories={this.state.categories}/>}/>
            </div>
          </main>
          <Redirect to={"/books"}/>
        </Router>

    );
  }

    loadBooks = () => {
        BookService.fetchBooks()
            .then((data) => {
                this.setState({
                    books: data.data
                })
            });
    }

    loadCategories = () => {
        BookService.fetchCategories()
            .then((data) => {
                this.setState({
                    categories: data.data
                })
            })
    }

    deleteBook = (id) => {
        BookService.deleteBook(id)
            .then(() => {
                this.loadBooks()
            })
    }

    borrowBook = (id) => {
        BookService.borrowCopy(id)
            .then(() => {
                this.loadBooks()
            })
    }

    addBook = (name, availableCopies, category, author) => {
        BookService.addBook(name, availableCopies, category, author)
            .then(() => this.loadBooks());
    }

    loadAuthors = () => {
        BookService.fetchAuthors()
            .then((data) => {
                this.setState({
                    authors: data.data
                })
            })
    }

    getBook = (id) => {
        BookService.getBook(id)
            .then((data) => {
                this.setState({
                    selectedProduct: data.data
                });
            });
    }

    editBook = (id, name, availableCopies, category, author) => {
        BookService.editBook(id, name, availableCopies, category, author)
            .then(() => this.loadBooks());
    }



  componentDidMount() {
    this.loadBooks();
    this.loadCategories();
    this.loadAuthors();
  }

}

export default App;
