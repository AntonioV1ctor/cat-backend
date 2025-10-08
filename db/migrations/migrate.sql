CREATE TABLE users (
id INT PRIMARY KEY AUTO_INCREMENT, 
agent varchar(32) NOT NULL,
pass varchar(16) NOT NULL);

CREATE TABLE mensagens (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    msg TEXT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES usuarios(id)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);