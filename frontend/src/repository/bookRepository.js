import axios from "../custom-axios/axios"

const BookService = {
    fetchBooks: () => {
        return axios.get("/books");
    },

    fetchCategories: () => {
        return axios.get("/books/categories");
    },

    deleteBook : (id) => {
        return axios.delete(`/books/delete/${id}`);
    },

    borrowCopy: (id) => {
        return axios.put(`/books/borrow/${id}`);
    },

    addBook: (name, availableCopies, category, author) => {
        return axios.post("/books/add", {
            "name": name,
            "availableCopies": availableCopies,
            "category": category,
            "authorId": author
        });
    },

    fetchAuthors: () => {
        return axios.get("/authors");
    },

    editBook: (id, name, availableCopies, category, author) => {
        return axios.put(`/books/edit/${id}`, {
            "name": name,
            "availableCopies": availableCopies,
            "category": category,
            "authorId": author
        });
    },

    getBook: (id) => {
        return axios.get(`/books/${id}`);
    }

}

export default BookService;