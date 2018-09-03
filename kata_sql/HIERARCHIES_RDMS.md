## Representing Hierarchies in a Relational Database

There are a number of ways to represent [hierarchical data in a relational database system](https://stackoverflow.com/questions/4048151/what-are-the-options-for-storing-hierarchical-data-in-a-relational-database/4054033).


### [Adjacency lists](https://en.wikipedia.org/wiki/Adjacency_list)

Simplest approach would be represent that data as an adjacency list (single table containing `id` and `parent_id`).

*Pros* are its simple to implement, and cheap to insert/delete or move.   

*Cons* are it can be expensive to find level, ancestry & paths

[Common Table Expressions](http://malisper.me/postgres-ctes/) can help simplify the querying of hierarchical data stored in an adjacency list format.


#### Example: [Adjacency list using PostgreSQL](http://malisper.me/understanding-postgres-recursive-ctes/)

```
CREATE TABLE comments (
  id serial PRIMARY KEY,
  content VARCHAR(500) NOT NULL,
  parent_id INTEGER,
  foreign key (parent_id) references comments(id)
);

INSERT INTO comments (content, parent_id) VALUES ('Hello world', NULL);
INSERT INTO comments (content, parent_id) VALUES ('Hello', 1);
INSERT INTO comments (content, parent_id) VALUES ('Hi', 1);
INSERT INTO comments (content, parent_id) VALUES ('Hey', 3);
INSERT INTO comments (content, parent_id) VALUES ('BYE', 4);
INSERT INTO comments (content, parent_id) VALUES ('What time is it', NULL);
INSERT INTO comments (content, parent_id) VALUES ('It''s 7 O''Clock ', 6);

SELECT * FROM comments;

 id |     content     | parent_id
----+-----------------+-----------
  1 | Hello world     |
  2 | Hello           |         1
  3 | Hi              |         1
  4 | Hey             |         3
  5 | BYE             |         4
  6 | What time is it |
  7 | It's 7 O'Clock  |         6
  

WITH RECURSIVE comment_tree(id, content, parent_id, depth) AS (
  SELECT id, content, parent_id, 0 FROM comments where id = 1
  UNION ALL
  SELECT comments.id, comments.content, comments.parent_id, comment_tree.depth+1
  FROM comments, comment_tree
  WHERE comments.parent_id = comment_tree.id
)
SELECT * FROM  comment_tree;

 id |   content   | parent_id | depth
----+-------------+-----------+-------
  1 | Hello world |           |     0
  2 | Hello       |         1 |     1
  3 | Hi          |         1 |     1
  4 | Hi          |         3 |     2
  5 | BYE         |         4 |     3

```



### [Nested sets](http://mikehillyer.com/articles/managing-hierarchical-data-in-mysql/) (or Modified Preorder Tree Traversal - MPTT)

MPTT, or modified preorder tree traversal, is an efficient way to store hierarchical data in a flat structure.

This approach adds a left and right attribute to the model data.

*Pros* it is cheap to query ancestry and paths.

*Cons* it's very expensive O(n/2) to move/insert/delete data due to volatile encoding 



### [Closure tables](http://dirtsimple.org/2010/11/simplest-way-to-do-tree-based-queries.html) 

Combines the simplicity of the adjacency list table format with improved performance for move/insert/deletes

 
