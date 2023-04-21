import React from "react";
import {Link} from "react-router-dom";

const bookTerm = (props) => {

    return(
        <tr>
            <td>{props.term.name}</td>
            <td>{props.term.category}</td>
            <td>{props.term.author.name} {props.term.author.surname}</td>
            <td>{props.term.availableCopies}</td>
            <td className={"text-right"}>
                <button title={"Delete"} className={"btn btn-outline-danger"}
                   onClick={() => props.onDelete(props.term.id)}>
                    Delete
                </button>
                <Link className={"btn btn-outline-primary ml-3"} onClick={() => props.onEdit(props.term.id)}
                      to={`/books/edit/${props.term.id}`}>
                    Edit
                </Link>
                <button title={"Borrow"} className={"btn btn-outline-info ml-3"}
                   onClick={() => props.onBorrow(props.term.id)} disabled={props.term.availableCopies < 1}>
                    Borrow
                </button>
            </td>
        </tr>
    );
}

export default bookTerm;