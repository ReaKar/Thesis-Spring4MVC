-- MySQL Workbench Forward Engineering

/* SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0; */
/* SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0; */
/* SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES'; */

-- -----------------------------------------------------


-- -----------------------------------------------------
-- Table `mydb`.`Role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS mydb.Role (
  idRole INT NOT NULL,
  description VARCHAR(45) NOT NULL,
  PRIMARY KEY (idRole))
;


-- -----------------------------------------------------
-- Table `mydb`.`User`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS mydb.User (
  idUser VARCHAR(45) NOT NULL ,
  firstname VARCHAR(45) NOT NULL,
  lastname VARCHAR(45) NOT NULL,
  pass VARCHAR(45) NOT NULL,
  verified SMALLINT NOT NULL,
  idRole INT NOT NULL ,
  PRIMARY KEY (idUser)
 ,
  CONSTRAINT fk_User_Role1
    FOREIGN KEY (idRole)
    REFERENCES mydb.Role (idRole)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
;

CREATE INDEX fk_User_Role1_idx ON mydb.User (idRole ASC);


-- -----------------------------------------------------
-- Table `mydb`.`Lesson`
-- -----------------------------------------------------
CREATE SEQUENCE mydb.Lesson_seq;

CREATE TABLE IF NOT EXISTS mydb.Lesson (
  idLesson INT NOT NULL DEFAULT NEXTVAL ('mydb.Lesson_seq'),
  semester VARCHAR(45) NOT NULL,
  title VARCHAR(200) NOT NULL,
  description TEXT NULL,
  filepath VARCHAR(4000) NULL,
  active SMALLINT NULL,
  PRIMARY KEY (idLesson))
;


-- -----------------------------------------------------
-- Table `mydb`.`Project`
-- -----------------------------------------------------
CREATE SEQUENCE mydb.Project_seq;

CREATE TABLE IF NOT EXISTS mydb.Project (
  idProject INT NOT NULL DEFAULT NEXTVAL ('mydb.Project_seq'),
  title VARCHAR(45) NOT NULL,
  description VARCHAR(4000) NOT NULL,
  idLesson INT NOT NULL,
  opendate TIMESTAMP(0) NOT NULL,
  closedate TIMESTAMP(0) NOT NULL,
  PRIMARY KEY (idProject)
 ,
  CONSTRAINT fk_Project_Lesson1
    FOREIGN KEY (idLesson)
    REFERENCES mydb.Lesson (idLesson)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
;

CREATE INDEX fk_Project_Lesson1_idx ON mydb.Project (idLesson ASC);


-- -----------------------------------------------------
-- Table `mydb`.`User_has_Project`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS mydb.User_has_Project (
  idUser VARCHAR(45) NOT NULL,
  idProject INT NOT NULL,
  when TIMESTAMP(0) NOT NULL,
  PRIMARY KEY (idUser, idProject)
 ,
  CONSTRAINT fk_User_has_Project_User1
    FOREIGN KEY (idUser)
    REFERENCES mydb.User (idUser)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_User_has_Project_Project1
    FOREIGN KEY (idProject)
    REFERENCES mydb.Project (idProject)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
;

CREATE INDEX fk_User_has_Project_Project1_idx ON mydb.User_has_Project (idProject ASC);
CREATE INDEX fk_User_has_Project_User1_idx ON mydb.User_has_Project (idUser ASC);


/* SET SQL_MODE=@OLD_SQL_MODE; */
/* SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS; */
/* SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS; */

-- -----------------------------------------------------
-- Data for table `mydb`.`Role`
-- -----------------------------------------------------
START TRANSACTION;
USE mydb;
INSERT INTO mydb.Role (idRole, description) VALUES (1, 'admin');
INSERT INTO mydb.Role (idRole, description) VALUES (2, 'user');

COMMIT;


-- -----------------------------------------------------
-- Data for table `mydb`.`User`
-- -----------------------------------------------------
START TRANSACTION;
USE mydb;
INSERT INTO mydb.User (idUser, firstname, lastname, pass, verified, idRole) VALUES ('delis@di.uoa.gr', 'alex', 'Delis', 'alex', 1, 1);
INSERT INTO mydb.User (idUser, firstname, lastname, pass, verified, idRole) VALUES ('bob@di.uoa.gr', 'bob', 'bob', 'bob', 1, 2);
INSERT INTO mydb.User (idUser, firstname, lastname, pass, verified, idRole) VALUES ('eva@di.uoa.gr', 'eva', 'eva', 'eva', 1, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `mydb`.`Lesson`
-- -----------------------------------------------------
START TRANSACTION;
USE mydb;
INSERT INTO mydb.Lesson (idLesson, semester, title, description, filepath, active) VALUES (1, '3', 'Operating systems', 'diskolo mathima', '/home/ubundistas/Desktop/os', 1);
INSERT INTO mydb.Lesson (idLesson, semester, title, description, filepath, active) VALUES (2, '4', 'System Programming', 'pio diskolo mathima, diavaste kala', '/home/ubundistas/Desktop/syspro', 0);

COMMIT;


-- -----------------------------------------------------
-- Data for table `mydb`.`Project`
-- -----------------------------------------------------
START TRANSACTION;
USE mydb;
INSERT INTO mydb.Project (idProject, title, description, idLesson, opendate, closedate) VALUES (1, 'Skiplist', 'Implement a skiplist and a hashtable', 1, '2017-10-01', '2017-10-30');
INSERT INTO mydb.Project (idProject, title, description, idLesson, opendate, closedate) VALUES (2, 'Something', 'Learn fork and exec', 1, '2017-11-01', '2017-11-30');
INSERT INTO mydb.Project (idProject, title, description, idLesson, opendate, closedate) VALUES (3, 'Restaurant', 'Learn semaphores and memory segments', 1, '2017-12-01', '2017-12-30');
INSERT INTO mydb.Project (idProject, title, description, idLesson, opendate, closedate) VALUES (4, 'Shell', 'Learn system calls', 1, '2018-01-01', '2018-01-30');

COMMIT;



