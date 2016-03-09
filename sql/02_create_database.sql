CREATE DATABASE `bank_db` DEFAULT CHARACTER SET utf8;

GRANT SELECT,INSERT,UPDATE,DELETE
ON `bank_db`.*
TO bank_user@localhost
IDENTIFIED BY 'bank_password';

GRANT SELECT,INSERT,UPDATE,DELETE
ON `bank_db`.*
TO bank_user@'%'
IDENTIFIED BY 'bank_password';