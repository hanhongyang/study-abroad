
mybatis generator执行命令：
mvn -Dmybatis.generator.overwrite=true mybatis-generator:generate

删除生日不为空的user
DELETE FROM `demo`.`user`
WHERE birthday is not null;
解除数据库safe——update
SET SQL_SAFE_UPDATES = 0;