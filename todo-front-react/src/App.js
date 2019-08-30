import React from "react";
import TodoList from "./components/TodoList";
import "./App.css";
import "bootstrap/dist/css/bootstrap.min.css";

function App() {
  return (
    <div className="row box box-aqua">
      <div className="col col-md-6 col-md-offset-3">
        <div
          className="box-header ui-sortable-handle"
          style={{ cursor: "move" }}
        >
          <i className="ion ion-clipboard"></i>
          <h3 className="box-title">To Do List</h3>
        </div>
        <TodoList />
      </div>
    </div>
  );
}

export default App;
