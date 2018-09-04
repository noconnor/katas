## Representing Hierarchies in a Relational Database

There are a number of ways to represent [hierarchical data in a relational database system](https://stackoverflow.com/questions/4048151/what-are-the-options-for-storing-hierarchical-data-in-a-relational-database/4054033).


Contents:
* [Adjacency lists](#adjacency-lists)
* [Nested sets](#nested-sets-or-modified-preorder-tree-traversal---mptt)
* [Closure tables](#closure-tables)
* [Materialized Paths](#materialized-paths)


### Adjacency lists

(Ref: https://en.wikipedia.org/wiki/Adjacency_list)

Simplest approach would be represent that data as an adjacency list (single table containing `id` and `parent_id`).

*Pros* are its simple to implement, and cheap to insert/delete or move.   

*Cons* are it can be expensive to find level, ancestry & paths

[Common Table Expressions](http://malisper.me/postgres-ctes/) can help simplify the querying of hierarchical data stored in an adjacency list format.

<br />

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


<br />


### Nested sets or Modified Preorder Tree Traversal - MPTT

(Ref: http://mikehillyer.com/articles/managing-hierarchical-data-in-mysql/ )

MPTT, or modified preorder tree traversal, is an efficient way to store hierarchical data in a flat structure.

This approach adds a left and right attribute to the model data.

*Pros* it is cheap to query ancestry and paths.

*Cons* it's very expensive O(n/2) to move/insert/delete data due to volatile encoding 


<br />


### Closure tables

(Ref: http://dirtsimple.org/2010/11/simplest-way-to-do-tree-based-queries.html)

Combines the simplicity of the adjacency list table format with improved performance for move/insert/deletes


<br />
 
#### Example: [Closure table using PostgreSQL](https://dirtsimple.org/2010/11/simplest-way-to-do-tree-based-queries.html)

```
CREATE TABLE comments (
  id serial PRIMARY KEY,
  content VARCHAR(500) NOT NULL,
  parent_id INTEGER,
  foreign key (parent_id) references comments(id)
);

CREATE TABLE comments_closure (
  parent_id INTEGER NOT NULL,
  child_id INTEGER NOT NULL,
  depth INTEGER NOT NULL,
  foreign key (parent_id) references comments(id),
  foreign key (child_id) references comments(id)
);

-- set up comments
INSERT INTO comments(content, parent_id) VALUES ('Hello world', NULL);
INSERT INTO comments(content, parent_id) VALUES ('Hello', 1);
INSERT INTO comments(content, parent_id) VALUES ('Hi', 1);
INSERT INTO comments(content, parent_id) VALUES ('Hey', 2);
INSERT INTO comments(content, parent_id) VALUES ('Bye', 4);
INSERT INTO comments(content, parent_id) VALUES ('What time is it?', NULL);
INSERT INTO comments(content, parent_id) VALUES ('7 O''Clock', 6);

 id |     content      | parent_id
----+------------------+-----------
  1 | Hello world      |
  2 | Hello            |         1
  3 | Hi               |         1
  4 | Hey              |         2
  5 | Bye              |         4
  6 | What time is it? |
  7 | 7 O'Clock        |         6

-- initialise comments_closure table
INSERT INTO comments_closure( parent_id, child_id, depth) VALUES (1,1,0);
INSERT INTO comments_closure( parent_id, child_id, depth) VALUES (2,2,0);
INSERT INTO comments_closure( parent_id, child_id, depth) VALUES (3,3,0);
INSERT INTO comments_closure( parent_id, child_id, depth) VALUES (4,4,0);
INSERT INTO comments_closure( parent_id, child_id, depth) VALUES (5,5,0);
INSERT INTO comments_closure( parent_id, child_id, depth) VALUES (6,6,0);
INSERT INTO comments_closure( parent_id, child_id, depth) VALUES (7,7,0);

 parent_id | child_id | depth
-----------+----------+-------
         1 |        1 |     0
         2 |        2 |     0
         3 |        3 |     0
         4 |        4 |     0
         5 |        5 |     0
         6 |        6 |     0
         7 |        7 |     0


-- add relationship to comments closure, could be a DB trigger
-- make all parents of :PARENT also parents of all children of :CHILD
\set PARENT 1
\set CHILD 2
INSERT INTO comments_closure (parent_id, child_id, depth)
SELECT p.parent_id, c.child_id, p.depth+c.depth+1
FROM comments_closure p, comments_closure c
WHERE p.child_id = :PARENT and c.parent_id = :CHILD;

-- repeat above for all relationships in comments table

 parent_id | child_id | depth
-----------+----------+-------
         1 |        1 |     0
         2 |        2 |     0
         3 |        3 |     0
         4 |        4 |     0
         5 |        5 |     0
         6 |        6 |     0
         1 |        2 |     1
         1 |        3 |     1
         2 |        4 |     1
         1 |        4 |     2
         4 |        5 |     1
         2 |        5 |     2
         1 |        5 |     3
         7 |        7 |     0
         6 |        7 |     1



-- retrieve comment tree
SELECT c.id, c.content, c.parent_id, cl.depth FROM comments c, comments_closure cl WHERE cl.parent_id = 1 and c.id = cl.child_id;

 id |   content   | parent_id | depth
----+-------------+-----------+-------
  1 | Hello world |           |     0
  2 | Hello       |         1 |     1
  3 | Hi          |         1 |     1
  4 | Hey         |         2 |     2
  5 | Bye         |         4 |     3
  
 

SELECT c.id, c.content, c.parent_id, cl.depth FROM comments c, comments_closure cl WHERE cl.parent_id = 6 and c.id = cl.child_id;

 id |     content      | parent_id | depth
----+------------------+-----------+-------
  6 | What time is it? |           |     0
  7 | 7 O'Clock        |         6 |     1


```

<br />

### Materialized paths

(Ref:https://evileg.com/en/post/12/)

The full path to the added entry is added to the table.

<br />

#### Example: [Materialized paths using PostgreSQL](https://evileg.com/en/post/12/)

```

CREATE TABLE comments (
  id serial PRIMARY key,
  content VARCHAR(500),
  path INTEGER[]
);

\set PARENT NULL
INSERT INTO comments(content, path) 
VALUES('Hello World', (SELECT path FROM comments WHERE id = :PARENT) || (SELECT currval('comments_id_seq')::integer));

\set PARENT 1
INSERT INTO comments(content, path) 
VALUES('Hello', (SELECT path FROM comments WHERE id = :PARENT) || (SELECT currval('comments_id_seq')::integer));

\set PARENT NULL
INSERT INTO comments(content, path) 
VALUES('What time is it?', (SELECT path FROM comments WHERE id = :PARENT) || (SELECT currval('comments_id_seq')::integer));

\set PARENT 3
INSERT INTO comments(content, path) 
VALUES('7 O''Clock', (SELECT path FROM comments WHERE id = :PARENT) || (SELECT currval('comments_id_seq')::integer));

\set PARENT 2
reddit=# INSERT INTO comments(content, path)
VALUES('Bye!', (SELECT path FROM comments WHERE id = :PARENT) || (SELECT currval('comments_id_seq')::integer));


select * from comments;
 id |     content      |  path
----+------------------+---------
  1 | Hello World      | {1}
  2 | Hello            | {1,2}
  3 | What time is it? | {3}
  4 | 7 O'Clock        | {3,4}
  5 | Bye!             | {1,2,5}


SELECT * FROM comments where path && ARRAY[1];
 id |   content   |  path
----+-------------+---------
  1 | Hello World | {1}
  2 | Hello       | {1,2}
  5 | Bye!        | {1,2,5}
  
  
SELECT * FROM comments where path && ARRAY[3];
 id |     content      | path
----+------------------+-------
  3 | What time is it? | {3}
  4 | 7 O'Clock        | {3,4}


-- Delete removes all associated comments
DELETE FROM comments WHERE path && ARRAY[2];

select * from comments;
 id |     content      | path
----+------------------+-------
  1 | Hello World      | {1}
  3 | What time is it? | {3}
  4 | 7 O'Clock        | {3,4}  

```
