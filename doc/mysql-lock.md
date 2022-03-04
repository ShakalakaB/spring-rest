# Mysql Transaction and Lock

----

+ MySql version: 8, view locks in table: `performance_schema.data_locks`, for versions prior to version 8, they are in
  table `information_schema.innodb_locks`.

Read Materials:

+ [InnoDB Locking](https://dev.mysql.com/doc/refman/8.0/en/innodb-locking.html#innodb-gap-locks)
+ [Transaction Isolation Levels](https://dev.mysql.com/doc/refman/8.0/en/innodb-transaction-isolation-levels.html)
+ [Understand the basics of locks and deadlocks in MySQL (Part I)](https://medium.com/codex/understand-the-basics-of-locks-and-deadlocks-in-mysql-part-i-92f229db0a)

### Transaction Isolation Level- REPEATABLE READ(mysql default level)

#### 1. lock on (normal)index

Gap lock would be created. Be careful, a gap, not just the index you are searching

> For a unique index with a unique search condition, InnoDB locks only the index record found, not the gap before it;
> For other search conditions(not unique index), InnoDB locks the index range scanned, using gap locks or next-key locks to block insertions by other sessions into the gaps covered by the range.([source](https://dev.mysql.com/doc/refman/8.0/en/innodb-transaction-isolation-levels.html))

For example:

| id  | description | state | customer\_id |
|:----|:------------|:------|:-------------|
| 1   | payment1    | 102   | 1            |
| 2   | payment2    | 100   | 1            |
| 3   | payment3    | 104   | 2            |
| 4   | payment4    | 106   | 2            |
| 5   | payment3    | 104   | 1            |


##### Example1

+ **query**:

 ```
  START TRANSACTION;
  SELECT * FROM payment WHERE state = 104 FOR UPDATE;(or UPDATE payment SET description = "updated from console" WHERE state = 104)
  SELECT OBJECT_NAME, INDEX_NAME, LOCK_TYPE, LOCK_MODE, LOCK_STATUS, LOCK_DATA FROM performance_schema.data_locks;
  COMMIT ;
+ ```

+ **lock** from `performance_schema.data_locks`:

| ENGINE | OBJECT\_NAME | INDEX\_NAME  | LOCK\_TYPE | LOCK\_MODE      | LOCK\_STATUS | LOCK\_DATA |
|:-------|:-------------|:-------------|:-----------|:----------------|:-------------|:-----------|
| INNODB | payment      | NULL         | TABLE      | IX              | GRANTED      | NULL       |
| INNODB | payment      | state\_index | RECORD     | X               | GRANTED      | 104, 3     |
| INNODB | payment      | state\_index | RECORD     | X               | GRANTED      | 104, 5     |
| INNODB | payment      | PRIMARY      | RECORD     | X,REC\_NOT\_GAP | GRANTED      | 3          |
| INNODB | payment      | PRIMARY      | RECORD     | X,REC\_NOT\_GAP | GRANTED      | 5          |
| INNODB | payment      | state\_index | RECORD     | X,GAP           | GRANTED      | 106, 4     |
+ locks break down:
  + MySql add row locks on id=3, id=5 as they have 104 state, which means others transactions can't update them.
  + Mysql not only locks rows with state 104, it also crates a gap lock between state 104 and its next value(106, not inclusive) in the table, and it means insertion with state less than 106, greater than 104 would fail. For example, an insertion with state 105 would fail, but with state 106 would succeed.
  + IX- intention lock, table-level locks that indicate which type of lock (shared or exclusive) a transaction requires
  later for a row in a
  table. [click here for more](https://dev.mysql.com/doc/refman/8.0/en/innodb-locking.html#innodb-intention-locks)
  > The intention locking protocol is as follows:
  >
  > Before a transaction can acquire a shared lock on a row in a table, it must first acquire an IS lock or stronger on
  the table.
  >
  > Before a transaction can acquire an exclusive lock on a row in a table, it must first acquire an IX lock on the
  table.

  + X- exclusive lock, permits the transaction that holds the lock to update or delete a row. [Click here for more](https://dev.mysql.com/doc/refman/8.0/en/innodb-locking.html#innodb-shared-exclusive-locks)
  + REC_NOT_GAP indicates a record-only lock, and means the S lock is a record lock.
  + GAP: gap lock, a gap between index records, or a lock on the gap before the first or after the last index record. It should be noted that the row marked as gap lock is not included as lock, so it means it still can be updated or insert.

##### Example2
+ **query**:
```
  START TRANSACTION;
  SELECT * FROM payment WHERE state = 106 FOR UPDATE;(or UPDATE payment SET description = "updated from console" WHERE state = 106)
  SELECT OBJECT_NAME, INDEX_NAME, LOCK_TYPE, LOCK_MODE, LOCK_STATUS, LOCK_DATA FROM performance_schema.data_locks;
  COMMIT ;
+ ```
+ **lock** from `performance_schema.data_locks`:

| ENGINE | OBJECT\_NAME | INDEX\_NAME  | LOCK\_TYPE | LOCK\_MODE      | LOCK\_STATUS | LOCK\_DATA             |
|:-------|:-------------|:-------------|:-----------|:----------------|:-------------|:-----------------------|
| INNODB | payment      | NULL         | TABLE      | IX              | GRANTED      | NULL                   |
| INNODB | payment      | state\_index | RECORD     | X               | GRANTED      | supremum pseudo-record |
| INNODB | payment      | state\_index | RECORD     | X               | GRANTED      | 106, 4                 |
| INNODB | payment      | PRIMARY      | RECORD     | X,REC\_NOT\_GAP | GRANTED      | 4                      |

+ locks break down:
  + The SELECT FOR UPDATE is locking rows between 106 and the next value in the payment table. Since there is no next-value, it is locking until the **supremum pseudo-record**. In the above example, any value above 106 can't be inserted or updated.







