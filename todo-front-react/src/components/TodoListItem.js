import React from "react";

const TodoListItem = ({
  todo,
  checkMarkedHandler,
  editTodoHandler,
  deleteTodoHandler
}) => {
  const { id, name, completed } = todo;
  const getTodoItemStyle = {
    textDecoration: completed ? "line-through" : "none"
  };

  return (
    <li>
      <span className="handle ui-sortable-handle">
        <i className="fa fa-ellipsis-v"></i>
        <i className="fa fa-ellipsis-v"></i>
      </span>{" "}
      <input
        type="checkbox"
        onChange={checkMarkedHandler.bind(this, id)}
        checked={completed}
        value=""
        name=""
      />{" "}
      <span className="text" style={getTodoItemStyle}>
        {name}
      </span>
      <div className="tools">
        <i className="fa fa-edit" onClick={editTodoHandler.bind(this, id)}></i>
        <i
          className="fa fa-trash-o"
          onClick={deleteTodoHandler.bind(this, id)}
        ></i>
      </div>
    </li>
  );
};

export default TodoListItem;
