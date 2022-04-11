INSERT INTO GENRES (GENRE_ID, GENRE_NAME)
VALUES
(1, 'Детектив'), (2, 'Фентези');

INSERT INTO AUTHORS (AUTHOR_ID, AUTHOR_NAME)
VALUES
(1, 'Агата Кристи'), (2, 'Джон Толкин');

INSERT INTO BOOKS (BOOK_ID, BOOK_NAME, AUTHOR_ID, GENRE_ID)
VALUES
(1, 'Пуаро', 1, 1), (2, 'Властелин Колец', 2, 2);

INSERT INTO COMMENTS (COMMENT_TEXT, BOOK_ID)
VALUES
('Пример коментария - 1', 1), ('Пример коментария - 2', 1);

