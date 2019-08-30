import React from "react";

const Add = ({
  value,
  onChange,
  addTodoHandler,
  error,
  hasError,
  children
}) => (
  <form onSubmit={addTodoHandler}>
    <div hidden={!hasError} className="alert alert-danger" role="alert">
      {error}
    </div>

    <input
      type="text"
      value={value}
      minLength="3"
      onChange={onChange}
      placeholder="Enter todo task "
      required
      style={{ width: "100%" }}
    />
    <br />
    <br />
    <button type="submit" className="btn btn-default pull-right">
      <i className="fa fa-plus"></i>
      {children}
    </button>
  </form>
);

export default Add;
