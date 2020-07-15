
删除生日不为空的user
DELETE FROM `demo`.`user`
WHERE birthday is not null;
解除数据库safe——update
SET SQL_SAFE_UPDATES = 0;

不要让分页超过9999