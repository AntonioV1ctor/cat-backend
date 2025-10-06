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

-- INSERT INTO users (agent, pass)
-- VALUES ('admin', '1234'), ('ac1db1t3', '83292119');

-- INSERT INTO mensagens (user_id, msg)
-- VALUES (1, 'Hi, do you want hack the FBI?'),
    --    (1, 'Because i need THIS!'),
    --    (2, 'Omagaa');
       
-- SELECT * FROM users WHERE agent = "ac1db1t3";

-- SELECT * FROM users