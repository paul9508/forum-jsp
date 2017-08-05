CREATE TABLE users (
 user_id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
 username VARCHAR(50) NOT NULL,
 password VARCHAR(100) NOT NULL,
 PRIMARY KEY (username)
);

CREATE TABLE user_roles (
 user_role_id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
 username VARCHAR(50) NOT NULL,
 role VARCHAR(50) NOT NULL,
 PRIMARY KEY (user_role_id),
 FOREIGN KEY (username) REFERENCES users(username) 
);



INSERT INTO users(username, password) VALUES ('admin', '$2a$10$Jc102pEGSpVeCFaLYVE40OBza4w9znZyFoaHTraHpUGS3nZb7le7.');
INSERT INTO user_roles(username, role) VALUES ('admin', 'ROLE_ADMIN');
INSERT INTO user_roles(username, role) VALUES ('admin', 'ROLE_USER');
INSERT INTO users(username, password) VALUES ('test', '$2a$10$owYRgu91sLzOYjZx74zlG.GZtwEXDAAP8x67EXFcSnoRj1ViiX.zm');
INSERT INTO user_roles(username, role) VALUES ('test', 'ROLE_USER');

CREATE TABLE thread (
 thread_id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
 thread_title VARCHAR(50) NOT NULL,
 thread_message_topic VARCHAR(500) NOT NULL,
 category VARCHAR(50) NOT NULL,
 time TIMESTAMP,
 username VARCHAR(50) NOT NULL,
 PRIMARY KEY (thread_id),
 FOREIGN KEY (username) REFERENCES users(username) on delete cascade
);

INSERT INTO thread(thread_title, thread_message_topic, category, time, username) VALUES ('Lecture', 'Lecture', 'Lecture', '2017-04-13 15:14:38.586', 'admin');

CREATE TABLE thread_reply (
 thread_reply_id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
 thread_reply_content VARCHAR(500) NOT NULL,
 time TIMESTAMP,
 thread_reply_user VARCHAR(50) NOT NULL,
 thread_reply_thread INTEGER NOT NULL,
 PRIMARY KEY (thread_reply_id),
 FOREIGN KEY (thread_reply_user) REFERENCES users(username) on delete cascade,
 FOREIGN KEY (thread_reply_thread) REFERENCES thread(thread_id) on delete cascade
);

INSERT INTO thread_reply(thread_reply_content, time, thread_reply_user, thread_reply_thread) VALUES ('test message', '2017-04-13 15:20:05.012', 'admin', 1);

CREATE TABLE thread_file (
 thread_file_id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
 thread_file_name VARCHAR(50) NOT NULL,
 thread_file_mimeContentType VARCHAR(50) NOT NULL,
 thread_file_content BLOB NOT NULL, 
 thread_file_thread INTEGER NOT NULL,
 PRIMARY KEY (thread_file_id),
 FOREIGN KEY (thread_file_thread) REFERENCES thread(thread_id) on delete cascade
);

CREATE TABLE reply_file (
 reply_file_id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
 reply_file_name VARCHAR(50) NOT NULL,
 reply_file_mimeContentType VARCHAR(50) NOT NULL,
 reply_file_content BLOB NOT NULL, 
 reply_file_reply INTEGER NOT NULL,
 PRIMARY KEY (reply_file_id),
 FOREIGN KEY (reply_file_reply) REFERENCES thread_reply(thread_reply_id) on delete cascade
);

CREATE TABLE poll (
 poll_id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
 Question VARCHAR(500) NOT NULL,
 Answer1 VARCHAR(500) NOT NULL,
 count1  INTEGER DEFAULT 0,
 Answer2 VARCHAR(500) NOT NULL,
 count2  INTEGER DEFAULT 0,
 Answer3 VARCHAR(500) NOT NULL,
 count3  INTEGER DEFAULT 0,
 Answer4 VARCHAR(500) NOT NULL,
 count4  INTEGER DEFAULT 0,
 PRIMARY KEY (poll_id)
);

INSERT INTO poll(Question, Answer1, Answer2, Answer3, Answer4) VALUES ('How old are you?', '>10', '>20', '>30', '>40');

CREATE TABLE user_poll (
 user_poll_id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
 username VARCHAR(50) NOT NULL,
 poll_id INTEGER NOT NULL,
 PRIMARY KEY (user_poll_id),
 FOREIGN KEY (username) REFERENCES users(username) on delete cascade,
 FOREIGN KEY (poll_id) REFERENCES poll(poll_id) on delete cascade
);
