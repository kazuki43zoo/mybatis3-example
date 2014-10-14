DROP FUNCTION IF EXISTS sales_item( INT4 );
DROP FUNCTION IF EXISTS findTodo( CHAR );
CREATE FUNCTION findTodo(pTodoId CHAR)
  RETURNS TABLE(
  todo_id CHAR,
  title VARCHAR,
  finished BOOLEAN,
  created_at TIMESTAMP,
  version BIGINT
  ) AS $$ BEGIN RETURN QUERY
SELECT
  t.todo_id,
  t.title,
  t.finished,
  t.created_at,
  t.version
FROM
  t_todo t
WHERE
  t.todo_id = pTodoId;
END;
$$ LANGUAGE plpgsql/
