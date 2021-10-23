create table tbl_user(
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    phoneNumber VARCHAR(255),
    age INT,
    PRIMARY KEY (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;