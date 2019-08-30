import React from "react";
import TodoListItem from "./TodoListItem";
import Add from "./Add";
import axios from "axios";

const PATH_BASE = "http://localhost:8080/todos";

class TodoList extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      todoTextEntry: "",
      error: "",
      hasError: false,
      todos: []
    };
  }

  // Fetch data from Todo SpringBoot api
  componentDidMount() {
    axios.get(PATH_BASE).then(res => {
      const todos = res.data;
      console.log(JSON.stringify(todos));
      this.setState({ todos });
    });
  }

  deleteTodoHandler = id => {
    const isNotId = todo => todo.id !== id;
    const updatedTodo = this.state.todos.filter(isNotId);

    this.setState({ todos: updatedTodo });

    axios.delete(`${PATH_BASE}/${id}`).then(res => {
      console.log(res);
      console.log(res.data);
    });
  }; // deleteTodoHandler

  editTodoHandler = id => {
    const editedName = prompt("Enter your modified task");
    if (isNotValidEntry(editedName)) return;

    const updatedTodo = this.state.todos.map(editedtodo => {
      if (editedtodo.id === id) {
        editedtodo.name = editedName;

        axios.put(PATH_BASE, editedtodo).then(res => {
          console.log(res);
          console.log(res.data);
        });
      }
      return editedtodo;
    });

    this.setState({ todos: updatedTodo });
  }; // editTodoHandler

  checkMarkedHandler = id => {
    const updatedTodo = this.state.todos.map(todo => {
      if (todo.id === id) {
        todo.completed = !todo.completed;

        axios.put(PATH_BASE, todo).then(res => {
          console.log(res);
          console.log(res.data);
        });
      }
      return todo;
    });

    this.setState({ todos: updatedTodo });
  }; // checkMarkedHandler

  onChangHandler = event => {
    this.setState({ todoTextEntry: event.target.value });
  };

  addTodoHandler = event => {
    event.preventDefault();
    const { todoTextEntry, todos } = this.state;

    const newTodo = {
      name: todoTextEntry + ""
    };

    axios
      .post(PATH_BASE, newTodo)
      .then(res => {
        console.log(res);
        console.log(res.data);
        const updatedTodo = [...todos];
        updatedTodo.push(res.data);
        this.setState({
          todos: updatedTodo,
          todoTextEntry: "",
          error: "",
          hasError: false
        });
      })
      .catch(err => {
        console.log(err);
        if (err.response) {
          if (err.response.status === 400) {
            console.log(JSON.stringify(err.response));
            console.log("err message => " + err.response.data);
            this.setState({ hasError: true, error: err.response.data });
          }
        }
      });
  };

  render() {
    const { todoTextEntry, todos, error, hasError } = this.state;
    let todoListItems = todos.map(todo => {
      return (
        <TodoListItem
          todo={todo}
          key={todo.id}
          checkMarkedHandler={this.checkMarkedHandler}
          editTodoHandler={this.editTodoHandler}
          deleteTodoHandler={this.deleteTodoHandler}
        />
      );
    });

    return (
      <div>
        <div className="box-body">
          <ul className="todo-list ui-sortable">{todoListItems}</ul>
        </div>
        <div className="box-footer clearfix no-border">
          <Add
            value={todoTextEntry}
            onChange={this.onChangHandler}
            addTodoHandler={this.addTodoHandler}
            error={error}
            hasError={hasError}
          >
            Add item
          </Add>
        </div>
      </div>
    );
  } // render
}

const isNotValidEntry = todoText => todoText.trim().length === 0;

export default TodoList;
