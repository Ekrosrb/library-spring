SET FOREIGN_KEY_CHECKS = 0;
TRUNCATE TABLE users;
TRUNCATE TABLE books;
TRUNCATE TABLE orders;
SET FOREIGN_KEY_CHECKS = 1;

# INSERT INTO users(first_name, last_name, email, password, birthday, phone, role, block)
# VALUES
# ('Admin', 'Admin', 'admin@mail.com', '827ccb0eea8a706c4c34a16891f84e7b', '2000-05-11', '123456', 0, false),
# ('Librarian', 'Librarian', 'lib@mail.com', '827ccb0eea8a706c4c34a16891f84e7b', '2000-01-01', '54265624', 1, false),
# ('Mail', 'Mail', 'mail@mail.com', '827ccb0eea8a706c4c34a16891f84e7b', '2001-02-01', '76513735', 2, false),
# ('Mail2', 'Mail2', 'mail2@mail.com', '827ccb0eea8a706c4c34a16891f84e7b', '2000-01-03', '2156674', 2, false);


INSERT INTO books(id, name, author, edition, description, description_ru, count)
VALUES
(4, 'Harry Potter and the Half-Blood Prince (Harry Potter #6)', 'J.K. Rowling/Mary GrandPré', 'book edition inc', 'Harry Potter and the Half-Blood Prince is a fantasy novel written by British author J.K. Rowling and the sixth and penultimate novel in the Harry Potter series. Set during Harry Potter''s sixth year at Hogwarts, the novel explores the past of the boy wizard''s nemesis, Lord Voldemort, and Harry''s preparations for the final battle against Voldemort alongside his headmaster and mentor Albus Dumbledore.', 'Гарри Поттер и принц-полукровка - роман в жанре фэнтези, написанный британским писателем Дж.К. Роулинг и шестой и предпоследний роман в серии о Гарри Поттере. Действие романа происходит на шестом курсе Гарри Поттера в Хогвартсе. В нем рассказывается о прошлом заклятого врага мальчика-волшебника, лорда Волдеморта, и подготовки Гарри к финальной битве против Волдеморта вместе с его директором и наставником Альбусом Дамблдором.', 2),
(5,'Harry Potter and the Order of the Phoenix (Harry Potter #5)', 'J.K. Rowling/Mary GrandPré', 'book edition', 'Harry Potter and the Order of the Phoenix is a fantasy novel written by British author J. K. Rowling and the fifth novel in the Harry Potter series. It follows Harry Potter''s struggles through his fifth year at Hogwarts School of Witchcraft and Wizardry, including the surreptitious return of the antagonist Lord Voldemort, O.W.L. exams, and an obstructive Ministry of Magic.', 'Гарри Поттер и Орден Феникса - это фантастический роман британской писательницы Дж. К. Роулинг, пятый роман из серии о Гарри Поттере. Он следует за борьбой Гарри Поттера в течение его пятого года в школе чародейства и волшебства Хогвартс, включая тайное возвращение лорда-антагониста Волан-де-Морта, С.О.В. экзамены и мешающее Министерство Магии.', 5),
(6,'Harry Potter and the Chamber of Secrets (Harry Potter #2)', 'J.K. Rowling', 'book inc', 'Harry Potter and the Chamber of Secrets is a fantasy novel written by British author J. K. Rowling and the second novel in the Harry Potter series. The plot follows Harry''s second year at Hogwarts School of Witchcraft and Wizardry, during which a series of messages on the walls of the school''s corridors warn that the "Chamber of Secrets" has been opened and that the "heir of Slytherin" would kill all pupils who do not come from all-magical families. These threats are found after attacks that leave residents of the school petrified. Throughout the year, Harry and his friends Ron and Hermione investigate the attacks.', 'Гарри Поттер и Тайная комната - это фантастический роман британской писательницы Дж. К. Роулинг, второй роман из серии о Гарри Поттере. Сюжет следует за вторым годом Гарри в Школе чародейства и волшебства Хогвартс, в течение которого на стенах школьных коридоров появляется серия сообщений, предупреждающих, что «Тайная комната» открыта и что «наследник Слизерина» убьет всех учеников. которые не происходят из чисто магических семей. Эти угрозы обнаруживаются после нападений, в результате которых жители школы приходят в ужас. В течение года Гарри и его друзья Рон и Гермиона расследуют нападения.', 3),
(7,'Harry Potter and the Prisoner of Azkaban (Harry Potter #3)', 'J.K. Rowling/Mary GrandPré', ' edition inc', 'Harry Potter and the Prisoner of Azkaban is a fantasy novel written by British author J. K. Rowling and is the third in the Harry Potter series. The book follows Harry Potter, a young wizard, in his third year at Hogwarts School of Witchcraft and Wizardry. Along with friends Ronald Weasley and Hermione Granger, Harry investigates Sirius Black, an escaped prisoner from Azkaban, the wizard prison, believed to be one of Lord Voldemort''s old allies.', 'Гарри Поттер и узник Азкабана - это фантастический роман британской писательницы Дж. К. Роулинг, третий в серии о Гарри Поттере. Книга рассказывает о Гарри Поттере, молодом волшебнике, который учится на третьем курсе школы чародейства и волшебства Хогвартс. Вместе с друзьями Рональдом Уизли и Гермионой Грейнджер Гарри исследует Сириуса Блэка, сбежавшего заключенного из Азкабана, волшебной тюрьмы, который, как полагают, был одним из старых союзников лорда Волан-де-Морта.', 7),
(8,'Unauthorized Harry Potter Book Seven News: "Half-Blood Prince" Analysis and Speculation', 'W. Frederick Zimmerman', 'book edition inc', 'This book contains the results of my research. Over the years and especially after publication of "Half-Blood Prince", J. K. Rowling and others have let slip quite a bit of information about the series. I think I was able to pull together a lot of interesting information. You will notice that there are many quotations and that wherever possible I have provided an "attribution"that is, I have identified author, title, date, and place of publication (often Internet). That way, you can judge for yourself whether my sources are solid.', 'В этой книге собраны результаты моих исследований. Спустя годы, особенно после публикации «Принца-полукровки», Дж. К. Роулинг и другие упустили довольно много информации о сериале. Думаю, мне удалось собрать много интересной информации. Вы заметите, что существует много цитат и что я везде, где это возможно, указывал «авторство», то есть указал автора, название, дату и место публикации (часто в Интернете). Таким образом, вы можете сами судить, надежны ли мои источники.', 2),
(9,'The Ultimate Hitchhiker''s Guide: Five Complete Novels and One Story (Hitchhiker''s Guide to the Galax...', 'Douglas Adams', 'book inc', 'super book3', 'супер книга 3', 3),
(10,'The Ultimate Hitchhiker''s Guide to the Galaxy (Hitchhiker''s Guide to the Galaxy #1-5)', 'Douglas Adams', ' edition inc', 'super book4', 'супер книга 4', 7),
(11,'The Hitchhiker''s Guide to the Galaxy (Hitchhiker''s Guide to the Galaxy #1)', 'Douglas Adams', 'book edit', 'super book5', 'супер книга 5', 1),
(12,'The Hitchhiker''s Guide to the Galaxy (Hitchhiker''s Guide to the Galaxy #1', 'Douglas Adams/Stephen Fry', 'book edition inc', 'super book', 'супер книга', 2),
(13,'A Short History of Nearly Everything', 'Bill Bryson', 'book inc', 'super book3', 'супер книга 3', 3),
(14,'Bill Bryson''s African Diary', 'Bill Bryson', ' edition inc', 'super book4', 'супер книга 4', 7),
(15,'Bryson''s Dictionary of Troublesome Words: A Writer''s Guide to Getting It Right', 'Bill Bryson', 'book edit', 'super book5', 'супер книга 5', 1),
(16,'In a Sunburned Country', 'Bill Bryson', 'book edition inc', 'super book', 'супер книга', 2),
(17,'I''m a Stranger Here Myself: Notes on Returning to America After Twenty Years Away', 'Bill Bryson', 'book edition', 'super book2', 'супер книга 2', 5),
(18,'The Lost Continent: Travels in Small Town America', 'Bill Bryson', 'book inc', 'super book3', 'супер книга 3', 3),
(19,'Neither Here nor There: Travels in Europe', 'Bill Bryson', ' edition inc', 'super book4', 'супер книга 4', 7),
(20,'Notes from a Small Island', 'Bill Bryson', 'book edit', 'super book5', 'супер книга 5', 1),
(21,'The Mother Tongue: English and How It Got That Way', 'Bill Bryson', 'book edition inc', 'super book', 'супер книга', 2),
(22,'J.R.R. Tolkien 4-Book Boxed Set: The Hobbit and The Lord of the Rings', 'J.R.R. Tolkien', 'book edition', 'super book2', 'супер книга 2', 5),
(23,'The Fellowship of the Ring (The Lord of the Rings #1)', 'J.R.R. Tolkien', ' edition inc', 'super book4', 'супер книга 4', 7),
(24,'The Lord of the Rings: Weapons and Warfare', 'Chris Smith/Christopher Lee/Richard Taylor', 'book edition inc', 'Describes in detail, with over one thousand photographs, the battles, armies, and armor used in the War of the Ring based on J.R.R. Tolkien''s "Lord of the Rings" trilogy, including battle plans and character histories and descriptions.', 'Подробно описывает сражения, армии и доспехи, использованные в Войне Кольца, с более чем тысячей фотографий, на основе книги Дж. Р. Р. Р. Трилогия Толкина «Властелин колец», включая планы сражений, истории и описания персонажей.', 2),
(25,'The Lord of the Rings: Complete Visual Companion', 'Jude Fisher', 'book edition', 'super book2', 'супер книга 2', 5),
(26,'Agile Web Development with Rails: A Pragmatic Guide', 'Dave Thomas/David Heinemeier Hansson/Leon Breedt/Mike Clark/Thomas Fuchs/Andreas Schwarz', 'book inc', 'super book3', 'супер книга 3', 3),
(27,'Hatchet (Brian''s Saga #1)', 'Gary Paulsen', ' edition inc', 'super book4', 'супер книга 4', 7),
(28,'Hatchet: A Guide for Using "Hatchet" in the Classroom', 'Donna Ickes/Edward Sciranko/Keith Vasconcelles', 'book edit', 'super book5', 'супер книга 5', 1),
(29,'Guts: The True Stories behind Hatchet and the Brian Books', 'Gary Paulsen', 'book edition inc', 'super book', 'супер книга', 2),
(30,'Molly Hatchet - 5 of the Best', 'Molly Hatchet', 'book edition', 'super book2', 'супер книга 2', 0),
(31,'Hatchet Jobs: Writings on Contemporary Fiction', 'Dale Peck', 'book inc', 'super book3', 'супер книга 3', 3),
(32,'A Changeling for All Seasons (Changeling Seasons #1)', 'Angela Knight/Sahara Kelly/Judy Mays/Marteeka Karland/Kate Douglas/Shelby Morgen/Lacey Savage/Kate H...', ' edition inc', 'super book4', 'супер книга 4', 7),
(33,'Changeling (Changeling #1)', 'Delia Sherman', 'book edit', 'super book5', 'супер книга 5', 1),
(34,'The Changeling Sea', 'Patricia A. McKillip', 'book edition inc', 'super book', 'супер книга', 2),
(35,'The Changeling', 'Zilpha Keatley Snyder', 'book edition', 'Martha is incredibly unpopular. She’s overweight, buck-toothed, and shy. Ivy is an outcast. Her family lives on the outskirts of town amid a field of derelict orchards. But starting in second grade, the girls form a bond that allows them to take control of their own lives. It all begins when Ivy tells Martha that she is no ordinary girl: She claims she’s a changeling, switched with the real Ivy at birth. With the strength of Ivy’s friendship, Martha becomes more confident and sure of herself. And through their bond, Ivy gains the normalcy she needs, away from life with her tumultuous family. When the two girls play, they enter an elaborate fantasy world all their own. But when the real world threatens to split them apart, their friendship becomes more important than ever. This ebook features an extended biography of Zilpha Keatley Snyder.', 'Марта невероятно непопулярна. Она толстая, зубастая и застенчивая. Айви - изгой. Ее семья живет на окраине города среди заброшенных садов. Но, начиная со второго класса, у девочек возникает связь, которая позволяет им контролировать свою жизнь. Все начинается с того, что Айви говорит Марте, что она не обычная девушка: она утверждает, что она подменыш, поменявшийся с настоящей Айви при рождении. Благодаря дружбе Айви, Марта становится более уверенной и уверенной в себе. И благодаря их связи Айви обретает необходимую ей нормальность, вдали от жизни с ее беспокойной семьей. Когда две девушки играют, они попадают в сложный мир фантазий. Но когда реальный мир угрожает разделить их, их дружба становится важнее, чем когда-либо. В этой электронной книге представлена расширенная биография Зильфы Китли Снайдер.', 5),
(36,'The Changeling', 'Kate Horsley', 'book inc', 'super book3', 'супер книга 3', 3),
(37,'The Changeling (Daughters of England #15)', 'Philippa Carr', ' edition inc', 'super book4', 'супер книга 4', 7),
(38,'The Known World', 'Edward P. Jones', 'book edit', 'super book5', 'супер книга 5', 1);

# INSERT INTO orders(user_id, book_id, term, order_date, fine, status)
# VALUES
# (4, 1, '2021-03-03','2021-05-05', 27, 3),
# (4, 5, '2021-03-11', '2021-05-05', 17, 3),
# (4, 2, '2021-04-11', '2021-05-05', 14, 3),
# (4, 8, '2021-03-12', '2021-05-05', 21, 3),
# (3, 17, '2021-03-03', '2021-05-05', 0, 2),
# (3, 9, '2021-03-03', '2021-05-05', 0, 2),
# (3, 4, '2021-03-03', '2021-05-05', 0, 0),
# (3, 5, '2021-03-03', '2021-05-05', 0, 1),
# (3, 16, '2021-03-03', '2021-05-05', 0, 4),
# (1, 16, '2021-03-03', '2021-05-05', 0, 4),
# (1, 8, '2021-03-03', '2021-05-05', 0, 5),
# (1, 9, '2021-03-03', '2021-05-05', 0, 5),
# (3, 12, '2021-03-03', '2021-05-05', 0, 2);

