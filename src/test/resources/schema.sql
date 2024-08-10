CREATE TABLE Course(
    Id INT PRIMARY KEY ,
    Name varchar(20),
    Description varchar(40),
    Credit INT
);

CREATE TABLE Rating (
                        Id INT PRIMARY KEY,
                        rating NUMERIC(3, 2)
);
CREATE TABLE Author(
                       Id INT PRIMARY KEY ,
                       Name varchar(30),
                       Email varchar(30),
                       Birthdate date
);
CREATE TABLE Assessment(
                           Id INT PRIMARY KEY ,
                           Content VARCHAR(40)
);

