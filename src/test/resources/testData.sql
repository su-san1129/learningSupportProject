-- https://www.mockaroo.com/

-- 運営管理者のダミーインサート文
insert into admins ( name, kana, email, password, can_show_all_company) values ('Madelyn', 'Kik', 'mkik0@goodreads.com', 'AQn65b', false);
insert into admins ( name, kana, email, password, can_show_all_company) values ('Andie', 'Bortoletti', 'abortoletti1@oracle.com', 'UdKtm6', true);
insert into admins ( name, kana, email, password, can_show_all_company) values ('Ossie', 'Frances', 'ofrances2@census.gov', 'iGR7TVuNu7j', true);
insert into admins ( name, kana, email, password, can_show_all_company) values ('Kalli', 'Place', 'kplace3@theglobeandmail.com', '2oWdnm', false);
insert into admins ( name, kana, email, password, can_show_all_company) values ('Pietrek', 'Insko', 'pinsko4@freewebs.com', '3d0v8TtI', true);
insert into admins ( name, kana, email, password, can_show_all_company) values ('Wood', 'De la Yglesia', 'wdelayglesia5@360.cn', 'ulZBvVTP', false);
insert into admins ( name, kana, email, password, can_show_all_company) values ('Sutherland', 'Bourdon', 'sbourdon6@i2i.jp', 'qyKRNsF', true);
insert into admins ( name, kana, email, password, can_show_all_company) values ('Giff', 'Carbry', 'gcarbry7@yandex.ru', 'dQ7telrqmXhJ', false);
insert into admins ( name, kana, email, password, can_show_all_company) values ('Dorie', 'Alyonov', 'dalyonov8@jiathis.com', 'CmMGIHQ6aYo7', true);
insert into admins ( name, kana, email, password, can_show_all_company) values ('Dexter', 'Prosh', 'dprosh9@google.it', 'rBIJ1C0lCFe', false);

-- 担当企業のダミーインサート文
insert into admin_responsible_companies ( admin_id, company_id) values (1, 1);
insert into admin_responsible_companies ( admin_id, company_id) values (1, 2);
insert into admin_responsible_companies ( admin_id, company_id) values (1, 3);
insert into admin_responsible_companies ( admin_id, company_id) values (2, 4);
insert into admin_responsible_companies ( admin_id, company_id) values (2, 5);
insert into admin_responsible_companies ( admin_id, company_id) values (2, 6);
insert into admin_responsible_companies ( admin_id, company_id) values (3, 7);
insert into admin_responsible_companies ( admin_id, company_id) values (3, 8);
insert into admin_responsible_companies ( admin_id, company_id) values (3, 9);
insert into admin_responsible_companies ( admin_id, company_id) values (4, 10);
insert into admin_responsible_companies ( admin_id, company_id) values (4, 1);
insert into admin_responsible_companies ( admin_id, company_id) values (4, 2);
insert into admin_responsible_companies ( admin_id, company_id) values (5, 3);
insert into admin_responsible_companies ( admin_id, company_id) values (5, 4);
insert into admin_responsible_companies ( admin_id, company_id) values (5, 5);
insert into admin_responsible_companies ( admin_id, company_id) values (6, 6);
insert into admin_responsible_companies ( admin_id, company_id) values (6, 7);
insert into admin_responsible_companies ( admin_id, company_id) values (6, 8);
insert into admin_responsible_companies ( admin_id, company_id) values (7, 9);
insert into admin_responsible_companies ( admin_id, company_id) values (7, 10);

-- 企業
insert into companies ( name, kana, remarks) values ( 'Goose, canada', 'Cookley', '-1E+02');
insert into companies ( name, kana, remarks) values ('Magnificent frigate bird', 'Tin', '1;DROP TABLE users');
insert into companies ( name, kana, remarks) values ('Stork, european', 'Fix San', ' test ');
insert into companies ( name, kana, remarks) values ('Red-tailed phascogale', 'Zamit', '1E+02');
insert into companies ( name, kana, remarks) values ('Crested screamer', 'Vagram', null);
insert into companies ( name, kana, remarks) values ('Steller''s sea lion', 'Duobam', '1/2');
insert into companies ( name, kana, remarks) values ( 'African bush squirrel', 'Aerified', '1E02');
insert into companies ( name, kana, remarks) values ('Monitor, white-throated', 'Matsoft', '␢');
insert into companies ( name, kana, remarks) values ('African lynx', 'Lotstring', '1E2');
insert into companies ( name, kana, remarks) values ('Jungle kangaroo', 'Quo Lux', '₀₁₂');

-- 企業担当者
insert into company_members ( name, kana, email, password, company_id) values ('White spoonbill', 'Matsoft', 'jjorck0@artisteer.com', '8X44nxwpfVAx', 1);
insert into company_members ( name, kana, email, password, company_id) values ('Cormorant, javanese', 'It', 'dpickard1@icio.us', 'ZP1oujYRBavG', 2);
insert into company_members ( name, kana, email, password, company_id) values ('Burmese black mountain tortoise', 'Bamity', 'gtimlin2@nps.gov', 'EniB1Iu', 3);
insert into company_members ( name, kana, email, password, company_id) values ('Vulture, griffon', 'Duobam', 'jfranz3@wix.com', 'f44BykD16XT', 4);
insert into company_members ( name, kana, email, password, company_id) values ('Verreaux''s sifaka', 'Zamit', 'cvinnick4@odnoklassniki.ru', 'bk3CGNIC', 5);
insert into company_members ( name, kana, email, password, company_id) values ('Marshbird, brown and yellow', 'Pannier', 'gsproston5@i2i.jp', '7WswTXGSc', 6);
insert into company_members ( name, kana, email, password, company_id) values ('American black bear', 'Fixflex', 'nivantsov6@wikispaces.com', 'GfmGqKTi34t', 7);
insert into company_members ( name, kana, email, password, company_id) values ('Horned puffin', 'Zontrax', 'bfisbey7@amazon.co.jp', 'SFyStp69WH', 8);
insert into company_members ( name, kana, email, password, company_id) values ('Tortoise, indian star', 'Zontrax', 'ddoni8@newsvine.com', 'Ju3ZVkYF', 9);
insert into company_members ( name, kana, email, password, company_id) values ('European wild cat', 'Latlux', 'dbullon9@state.gov', 'PWWI4v7JM', 10);

insert into company_members ( name, kana, email, password, company_id) values ('Snake, racer', 'Fintone', 'malabaster0@goodreads.com', 'r2V3paaCe3zt', 1);
insert into company_members ( name, kana, email, password, company_id) values ('Common nighthawk', 'Trippledex', 'dbelvard1@archive.org', 'obZR4hIGVp4B', 2);
insert into company_members ( name, kana, email, password, company_id) values ('Macaw, red and blue', 'Opela', 'kpaal2@spiegel.de', 'tl2DZBerchDB', 3);
insert into company_members ( name, kana, email, password, company_id) values ('Vulture, oriental white-backed', 'Subin', 'tpetkens3@gmpg.org', 'EYX5bC', 4);
insert into company_members ( name, kana, email, password, company_id) values ('Pygmy possum', 'Trippledex', 'tmaddy4@tinypic.com', '1mRkS3', 5);
insert into company_members ( name, kana, email, password, company_id) values ('Squirrel, smith''s bush', 'Latlux', 'zkirtley5@bing.com', 'kA33jpDj', 6);
insert into company_members ( name, kana, email, password, company_id) values ('Frog (unidentified)', 'Stringtough', 'asheaber6@sohu.com', '10Nj4Tfay4P', 7);
insert into company_members ( name, kana, email, password, company_id) values ('Tern, white-winged', 'Sub-Ex', 'ffranklin7@webeden.co.uk', 'nMgsYOtH', 8);
insert into company_members ( name, kana, email, password, company_id) values ('Yellow-necked spurfowl', 'Fix San', 'aminchin8@shareasale.com', 'I6qzPTX1tFv', 9);
insert into company_members ( name, kana, email, password, company_id) values ('Whale, long-finned pilot', 'Tresom', 'ynassau9@freewebs.com', 'ALVNGbSM43xZ', 10);

insert into company_members ( name, kana, email, password, company_id) values ('Elk, Wapiti', 'It', 'sbowton0@aol.com', 'dGmj2ShGyX0', 1);
insert into company_members ( name, kana, email, password, company_id) values ('Rhinoceros, square-lipped', 'Quo Lux', 'jpollicote1@cnn.com', 'T3ImZR8NX', 2);
insert into company_members ( name, kana, email, password, company_id) values ('Goose, knob-nosed', 'Bamity', 'smacieja2@icq.com', 'p4BkcC', 3);
insert into company_members ( name, kana, email, password, company_id) values ('Stork, yellow-billed', 'Konklux', 'fhallett3@wordpress.org', 'wSwerat', 4);
insert into company_members ( name, kana, email, password, company_id) values ('Denham''s bustard', 'Treeflex', 'bmeriott4@typepad.com', 'dBHl2IOCb', 5);
insert into company_members ( name, kana, email, password, company_id) values ('Arctic lemming', 'Trippledex', 'hbabidge5@nasa.gov', 'ntfiGs3x', 6);
insert into company_members ( name, kana, email, password, company_id) values ('Mongoose, small indian', 'Matsoft', 'atomasek6@pbs.org', 'ICkyK1M', 7);
insert into company_members ( name, kana, email, password, company_id) values ('Galapagos penguin', 'Daltfresh', 'deynaud7@blogtalkradio.com', 'Fm1mZW', 8);
insert into company_members ( name, kana, email, password, company_id) values ('Antelope ground squirrel', 'Otcom', 'jdavys8@theatlantic.com', '93AT4TRF', 9);
insert into company_members ( name, kana, email, password, company_id) values ('Levaillant''s barbet', 'Aerified', 'cbiagioni9@friendfeed.com', 'cLLUhUyykI', 10);

-- 研修リスト
insert into trainings (id, start_date, end_date, name, instructor_id, sub_instructor_id1, sub_instructor_id2, sub_instructor_id3) values (1, '2020-02-06 20:23:44', '2020-02-22 12:09:58', 'hmursell0@symantec.com', 1, 2, 3, 4);
insert into trainings (id, start_date, end_date, name, instructor_id, sub_instructor_id1, sub_instructor_id2, sub_instructor_id3) values (2, '2020-02-08 04:01:52', '2020-02-27 14:59:14', 'dloughren1@facebook.com', 2, 3, 4, 5);
insert into trainings (id, start_date, end_date, name, instructor_id, sub_instructor_id1, sub_instructor_id2, sub_instructor_id3) values (3, '2020-02-06 13:03:52', '2020-02-28 09:29:21', 'tgapper2@dagondesign.com', 3, 4, 5, 6);
insert into trainings (id, start_date, end_date, name, instructor_id, sub_instructor_id1, sub_instructor_id2, sub_instructor_id3) values (4, '2020-02-08 04:18:34', '2020-02-19 20:20:22', 'kpover3@opensource.org', 4, 5, 6, 7);
insert into trainings (id, start_date, end_date, name, instructor_id, sub_instructor_id1, sub_instructor_id2, sub_instructor_id3) values (5, '2020-02-02 13:27:11', '2020-02-27 00:28:14', 'lskehens4@skype.com', 5, 6, 7, 8);
insert into trainings (id, start_date, end_date, name, instructor_id, sub_instructor_id1, sub_instructor_id2, sub_instructor_id3) values (6, '2020-02-03 07:22:10', '2020-02-17 14:32:31', 'hbuckingham5@nsw.gov.au', 6, 7, 8, 9);
insert into trainings (id, start_date, end_date, name, instructor_id, sub_instructor_id1, sub_instructor_id2, sub_instructor_id3) values (7, '2020-02-07 09:09:48', '2020-02-15 07:12:06', 'bshearme6@nhs.uk', 7, 8, 9, 10);
insert into trainings (id, start_date, end_date, name, instructor_id, sub_instructor_id1, sub_instructor_id2, sub_instructor_id3) values (8, '2020-02-03 11:09:49', '2020-02-24 17:03:22', 'mbriddock7@hostgator.com', 8, 9, 10, 11);
insert into trainings (id, start_date, end_date, name, instructor_id, sub_instructor_id1, sub_instructor_id2, sub_instructor_id3) values (9, '2020-02-07 21:22:00', '2020-02-21 12:57:49', 'edelacote8@soup.io', 9, 10, 11, 12);
insert into trainings (id, start_date, end_date, name, instructor_id, sub_instructor_id1, sub_instructor_id2, sub_instructor_id3) values (10, '2020-02-04 01:40:08', '2020-02-20 04:56:00', 'dcapner9@answers.com', 10, 11, 12, 13);

-- 受講生リスト
insert into students (id, name, kana, email, password, company_id) values (1, 'Georas', 'Genny', 'gcrookall0@sun.com', 'pT8OdR', 1);
insert into students (id, name, kana, email, password, company_id) values (2, 'Vivyanne', 'Esme', 'erosedale1@privacy.gov.au', 'lSL8RoYF', 2);
insert into students (id, name, kana, email, password, company_id) values (3, 'Emilio', 'Cathryn', 'csancias2@nationalgeographic.com', 'KNY4zp', 3);
insert into students (id, name, kana, email, password, company_id) values (4, 'Christel', 'Dynah', 'dcudde3@netscape.com', 'O4hVDd', 4);
insert into students (id, name, kana, email, password, company_id) values (5, 'Rosana', 'Rania', 'rnapleton4@jigsy.com', 'aQeDismN', 5);
insert into students (id, name, kana, email, password, company_id) values (6, 'Cletis', 'Rollie', 'rtootal5@usgs.gov', 'RB1vxDOTrP', 6);
insert into students (id, name, kana, email, password, company_id) values (7, 'Ashlan', 'Fanya', 'fdust6@example.com', 'YrHDYb9K', 7);
insert into students (id, name, kana, email, password, company_id) values (8, 'Teddy', 'Leicester', 'lsybry7@tiny.cc', '8qe25re4', 8);
insert into students (id, name, kana, email, password, company_id) values (9, 'Pierrette', 'Cissy', 'cowbridge8@fastcompany.com', '1HH3QXH4', 9);
insert into students (id, name, kana, email, password, company_id) values (10, 'Dela', 'Milicent', 'msalzburger9@odnoklassniki.ru', 'nujyla8TN', 10);
insert into students (id, name, kana, email, password, company_id) values (11, 'Janna', 'Lenard', 'lprettyjohn0@reuters.com', 'g7xxrVJZ3FQe', 1);
insert into students (id, name, kana, email, password, company_id) values (12, 'Bernetta', 'Jacky', 'jdelany1@newyorker.com', 'U7TajPHn', 2);
insert into students (id, name, kana, email, password, company_id) values (13, 'Addia', 'Jamey', 'jranby2@posterous.com', 'XYWG8U', 3);
insert into students (id, name, kana, email, password, company_id) values (14, 'Trent', 'Yorgo', 'yarnowitz3@ucoz.com', '3p9te3uNb2V', 4);
insert into students (id, name, kana, email, password, company_id) values (15, 'Elisa', 'Timofei', 'timlock4@gmpg.org', 'Hf2wAJ1zJ', 5);
insert into students (id, name, kana, email, password, company_id) values (16, 'Johnette', 'Xerxes', 'xgoranov5@51.la', '88SDaa8XL5', 6);
insert into students (id, name, kana, email, password, company_id) values (17, 'Isidro', 'Jolene', 'jcanto6@home.pl', 'Wfq1Rgils37e', 7);
insert into students (id, name, kana, email, password, company_id) values (18, 'Fowler', 'Beverlie', 'bpostians7@un.org', 'us1HRMc7', 8);
insert into students (id, name, kana, email, password, company_id) values (19, 'Britt', 'Tobie', 'tspandley8@fotki.com', 'LyTu184K', 9);
insert into students (id, name, kana, email, password, company_id) values (20, 'Saxe', 'Heinrick', 'hrivelon9@wiley.com', 'hTUppY6N1I', 10);
insert into students (id, name, kana, email, password, company_id) values (21, 'Harmony', 'Quintilla', 'qsaurat0@nba.com', 'HbGj3oEP', 1);
insert into students (id, name, kana, email, password, company_id) values (22, 'Hebert', 'Sherye', 'smcguffie1@t-online.de', 'lb2gpRB', 2);
insert into students (id, name, kana, email, password, company_id) values (23, 'Joshuah', 'Kathryne', 'kpifford2@myspace.com', 'Rq5P1K', 3);
insert into students (id, name, kana, email, password, company_id) values (24, 'Judah', 'Anabella', 'ayendle3@go.com', 'mgaxWi', 4);
insert into students (id, name, kana, email, password, company_id) values (25, 'Killian', 'Bailie', 'bstobbe4@slate.com', 'ngicobJdfvg9', 5);
insert into students (id, name, kana, email, password, company_id) values (26, 'Elia', 'Barth', 'bmatteo5@techcrunch.com', 'yFF7mNM9NT9H', 6);
insert into students (id, name, kana, email, password, company_id) values (27, 'Lombard', 'Gilburt', 'gmarlin6@europa.eu', 'rw60Uue7c8R', 7);
insert into students (id, name, kana, email, password, company_id) values (28, 'Nevile', 'Lefty', 'lcrank7@google.de', 'ERFxve', 8);
insert into students (id, name, kana, email, password, company_id) values (29, 'Myrwyn', 'Abdel', 'akornes8@google.ru', 'tbyvDIgU3cF', 9);
insert into students (id, name, kana, email, password, company_id) values (30, 'Luther', 'Codi', 'cchattey9@tmall.com', 'mpKsTzp', 10);

-- 研修と受講生の中間テーブル
insert into training_student (training_id, student_id) values (1, 1);
insert into training_student (training_id, student_id) values (1, 2);
insert into training_student (training_id, student_id) values (1, 3);
insert into training_student (training_id, student_id) values (1, 4);
insert into training_student (training_id, student_id) values (1, 5);
insert into training_student (training_id, student_id) values (1, 6);
insert into training_student (training_id, student_id) values (1, 7);
insert into training_student (training_id, student_id) values (1, 8);
insert into training_student (training_id, student_id) values (1, 9);
insert into training_student (training_id, student_id) values (1, 10);
insert into training_student (training_id, student_id) values (2, 1);
insert into training_student (training_id, student_id) values (2, 2);
insert into training_student (training_id, student_id) values (2, 3);
insert into training_student (training_id, student_id) values (2, 4);
insert into training_student (training_id, student_id) values (2, 5);
insert into training_student (training_id, student_id) values (2, 6);
insert into training_student (training_id, student_id) values (2, 7);
insert into training_student (training_id, student_id) values (2, 8);
insert into training_student (training_id, student_id) values (2, 9);
insert into training_student (training_id, student_id) values (2, 10);
insert into training_student (training_id, student_id) values (3, 1);
insert into training_student (training_id, student_id) values (3, 2);
insert into training_student (training_id, student_id) values (3, 3);
insert into training_student (training_id, student_id) values (3, 4);
insert into training_student (training_id, student_id) values (3, 5);
insert into training_student (training_id, student_id) values (3, 6);
insert into training_student (training_id, student_id) values (3, 7);
insert into training_student (training_id, student_id) values (3, 8);
insert into training_student (training_id, student_id) values (3, 9);
insert into training_student (training_id, student_id) values (3, 10);

-- 週報
insert into weekly_reports (id, start_date, instructor_name, content, training_id) values (1, '2020-02-10 00:56:25', 'Photofeed', 'WA1CGCFE1BD313920', 1);
insert into weekly_reports (id, start_date, instructor_name, content, training_id) values (2, '2020-02-05 12:28:51', 'Rhynoodle', 'WAULT68E35A399976', 2);
insert into weekly_reports (id, start_date, instructor_name, content, training_id) values (3, '2020-02-02 22:16:19', 'Fatz', '1G6AB5R33E0047459', 3);
insert into weekly_reports (id, start_date, instructor_name, content, training_id) values (4, '2020-02-03 10:11:43', 'Yamia', 'WVGAV7AX5FW598601', 4);
insert into weekly_reports (id, start_date, instructor_name, content, training_id) values (5, '2020-02-01 11:47:20', 'Thoughtbridge', 'JH4DC53804S469248', 5);
insert into weekly_reports (id, start_date, instructor_name, content, training_id) values (6, '2020-02-09 11:03:48', 'Npath', '3D73M4EL8AG366156', 6);
insert into weekly_reports (id, start_date, instructor_name, content, training_id) values (7, '2020-02-10 09:55:47', 'InnoZ', 'WAUEF78E18A743508', 7);
insert into weekly_reports (id, start_date, instructor_name, content, training_id) values (8, '2020-02-08 13:15:11', 'Skivee', '3VW467AT9CM166467', 8);
insert into weekly_reports (id, start_date, instructor_name, content, training_id) values (9, '2020-02-07 02:48:07', 'Kamba', 'TRUWT28N811619492', 9);
insert into weekly_reports (id, start_date, instructor_name, content, training_id) values (10, '2020-02-07 17:56:06', 'Innojam', '1GKUKGEJ8AR377375', 10);
insert into weekly_reports (id, start_date, instructor_name, content, training_id) values (11, '2020-02-09 00:20:32', 'Tazz', 'W04GY5GV7B1476694', 1);
insert into weekly_reports (id, start_date, instructor_name, content, training_id) values (12, '2020-02-10 02:06:37', 'Gigabox', 'TRUUT28N321267172', 2);
insert into weekly_reports (id, start_date, instructor_name, content, training_id) values (13, '2020-02-04 00:37:53', 'Jabberbean', 'WAUMFAFLXAN336523', 3);
insert into weekly_reports (id, start_date, instructor_name, content, training_id) values (14, '2020-02-01 22:00:37', 'Miboo', '2LMDJ6JK2DB119397', 4);
insert into weekly_reports (id, start_date, instructor_name, content, training_id) values (15, '2020-02-07 20:03:12', 'Divanoodle', '3N1AB7AP3FY609037', 5);
insert into weekly_reports (id, start_date, instructor_name, content, training_id) values (16, '2020-02-02 13:32:19', 'DabZ', 'SALAB2D47CA558345', 6);
insert into weekly_reports (id, start_date, instructor_name, content, training_id) values (17, '2020-02-02 14:09:44', 'Bluezoom', '1GYUKFEJ4AR243351', 7);
insert into weekly_reports (id, start_date, instructor_name, content, training_id) values (18, '2020-02-03 15:20:12', 'Feedbug', '5LMJJ2H58CE185928', 8);
insert into weekly_reports (id, start_date, instructor_name, content, training_id) values (19, '2020-02-02 07:29:55', 'Oyondu', 'WBABN33482P082612', 9);
insert into weekly_reports (id, start_date, instructor_name, content, training_id) values (20, '2020-02-06 19:06:54', 'Minyx', 'WA1YD64B73N887474', 10);
insert into weekly_reports (id, start_date, instructor_name, content, training_id) values (21, '2020-02-10 16:04:49', 'Tagpad', 'WBAVT13506A722790', 1);
insert into weekly_reports (id, start_date, instructor_name, content, training_id) values (22, '2020-02-01 12:21:43', 'Livefish', 'WBAGL63483D207706', 2);
insert into weekly_reports (id, start_date, instructor_name, content, training_id) values (23, '2020-02-01 15:03:22', 'Jamia', 'ZFBCFABH2FZ234202', 3);
insert into weekly_reports (id, start_date, instructor_name, content, training_id) values (24, '2020-02-05 12:07:15', 'Muxo', 'WBABN73493P955664', 4);
insert into weekly_reports (id, start_date, instructor_name, content, training_id) values (25, '2020-02-02 17:01:04', 'Vimbo', 'WAUAF78E15A942519', 5);
insert into weekly_reports (id, start_date, instructor_name, content, training_id) values (26, '2020-02-07 05:30:49', 'Yakitri', 'WBAYB6C55DD175914', 6);
insert into weekly_reports (id, start_date, instructor_name, content, training_id) values (27, '2020-02-08 21:30:40', 'Yadel', '3D73M4HL0BG928094', 7);
insert into weekly_reports (id, start_date, instructor_name, content, training_id) values (28, '2020-02-01 16:52:33', 'Blogpad', '1G6AA5RX6D0134731', 8);
insert into weekly_reports (id, start_date, instructor_name, content, training_id) values (29, '2020-02-10 19:08:24', 'Skivee', '1GKS1HE01CR143783', 9);
insert into weekly_reports (id, start_date, instructor_name, content, training_id) values (30, '2020-02-01 02:52:07', 'Latz', 'WAUVC58E85A286222', 10);

-- 講師
insert into instructors (id, name, kana, email, password, affiliation, remarks) values (1, 'Dov', 'methylphenidate hydrochloride', 'dkobisch0@rakuten.co.jp', 'GxZ1uH1', 'Mazda', 'GTQ');
insert into instructors (id, name, kana, email, password, affiliation, remarks) values (2, 'Baxy', 'equaline flu and severe cold and cough', 'bbendel1@yelp.com', 'ZTV0tKgl9lU', 'Toyota', 'RUB');
insert into instructors (id, name, kana, email, password, affiliation, remarks) values (3, 'Jehu', 'Potassium Chloride', 'jbett2@behance.net', 'PwcrUZEl5', 'Chevrolet', 'EUR');
insert into instructors (id, name, kana, email, password, affiliation, remarks) values (4, 'Curry', 'BLACKBERRY', 'csayward3@uiuc.edu', 'gkP2kR', 'Subaru', 'USD');
insert into instructors (id, name, kana, email, password, affiliation, remarks) values (5, 'Elberta', 'PHOSPHORUS', 'eladd4@chron.com', 'VSmIqgAKYfI', 'Mercedes-Benz', 'EUR');
insert into instructors (id, name, kana, email, password, affiliation, remarks) values (6, 'Tarrah', 'PROCORT', 'tpoller5@netlog.com', '8Yj9byyg0yq', 'Morgan', 'SEK');
insert into instructors (id, name, kana, email, password, affiliation, remarks) values (7, 'Xylina', 'Fexmid', 'xgoodburn6@upenn.edu', 'qbDuF4', 'Toyota', 'IDR');
insert into instructors (id, name, kana, email, password, affiliation, remarks) values (8, 'Catrina', 'LANOXIN', 'ctooth7@house.gov', 'gIaht1', 'Honda', 'ZWL');
insert into instructors (id, name, kana, email, password, affiliation, remarks) values (9, 'Carla', 'Benicar Hct', 'cpieterick8@wikia.com', 'VpZIt87OsziI', 'Chevrolet', 'CNY');
insert into instructors (id, name, kana, email, password, affiliation, remarks) values (10, 'Marilin', 'Canadian Blue Grass', 'mallston9@upenn.edu', '1KYWxLr', 'Mercury', 'VND');
insert into instructors (id, name, kana, email, password, affiliation, remarks) values (11, 'Lindsy', 'Mirtazapine', 'lcircuita@whitehouse.gov', 'yOV43IK', 'Honda', 'IDR');
insert into instructors (id, name, kana, email, password, affiliation, remarks) values (12, 'Adey', 'Theracodophen-325', 'ahaquardb@vkontakte.ru', 'aZfhSphskbb', 'Honda', 'UAH');
insert into instructors (id, name, kana, email, password, affiliation, remarks) values (13, 'Dalia', 'Junior Pain Relief', 'dhartleyc@w3.org', 'cA3l9d5ZbW', 'Toyota', 'USD');
insert into instructors (id, name, kana, email, password, affiliation, remarks) values (14, 'Kelsy', 'Chaetomium globosum', 'kgommd@fda.gov', 'yz0SKfYUrKcH', 'Dodge', 'CNY');
insert into instructors (id, name, kana, email, password, affiliation, remarks) values (15, 'Neron', 'Formu Care Anti Itch', 'nbenwelle@illinois.edu', 'zFMUwl', 'Cadillac', 'JPY');
insert into instructors (id, name, kana, email, password, affiliation, remarks) values (16, 'Raffaello', 'amlodipine besylate', 'rgarettf@theglobeandmail.com', 'VE9Qwh2jTqGh', 'Tesla', 'CNY');
insert into instructors (id, name, kana, email, password, affiliation, remarks) values (17, 'Salomi', 'DIVALPROEX SODIUM', 'svinallg@census.gov', 'hyCXrxsilD', 'Chevrolet', 'IDR');
insert into instructors (id, name, kana, email, password, affiliation, remarks) values (18, 'Merrill', 'Listerine Ultraclean Antiseptic', 'mrammellh@mysql.com', '8jMMwpB', 'Ford', 'IDR');
insert into instructors (id, name, kana, email, password, affiliation, remarks) values (19, 'Pattie', 'Losortan Potassium', 'peedei@mit.edu', 't6xZtdJmJ', 'Cadillac', 'EUR');
insert into instructors (id, name, kana, email, password, affiliation, remarks) values (20, 'Brewster', 'Zolpidem Tartrate', 'bpeterj@barnesandnoble.com', '3CeaZvQOVW', 'Mitsubishi', 'CNY');

-- 日報
insert into daily_reports (id, date, training_id, student_id, content, intelligibility, detail_intelligibility, about_instructor, question) values (1, '2020-02-18 19:13:03', 1, 1, 'Treeflex', 1, 'Tampflex', 1, 'Transcof');
insert into daily_reports (id, date, training_id, student_id, content, intelligibility, detail_intelligibility, about_instructor, question) values (2, '2020-02-08 18:47:26', 2, 2, 'Sub-Ex', 1, 'Fixflex', 1, 'Lotlux');
insert into daily_reports (id, date, training_id, student_id, content, intelligibility, detail_intelligibility, about_instructor, question) values (3, '2020-02-01 05:24:07', 3, 3, 'Voyatouch', 1, 'Treeflex', 1, 'Regrant');
insert into daily_reports (id, date, training_id, student_id, content, intelligibility, detail_intelligibility, about_instructor, question) values (4, '2020-02-11 12:11:25', 4, 4, 'Tin', 1, 'Viva', 1, 'Sonsing');
insert into daily_reports (id, date, training_id, student_id, content, intelligibility, detail_intelligibility, about_instructor, question) values (5, '2020-02-28 16:26:03', 5, 5, 'Zontrax', 1, 'Bitchip', 1, 'Namfix');
insert into daily_reports (id, date, training_id, student_id, content, intelligibility, detail_intelligibility, about_instructor, question) values (6, '2020-02-14 17:57:49', 6, 6, 'It', 1, 'Quo Lux', 1, 'Viva');
insert into daily_reports (id, date, training_id, student_id, content, intelligibility, detail_intelligibility, about_instructor, question) values (7, '2020-02-11 05:20:18', 7, 7, 'Sonair', 1, 'Otcom', 1, 'Zoolab');
insert into daily_reports (id, date, training_id, student_id, content, intelligibility, detail_intelligibility, about_instructor, question) values (8, '2020-02-24 03:29:13', 8, 8, 'Fix San', 1, 'Cardguard', 1, 'Ronstring');
insert into daily_reports (id, date, training_id, student_id, content, intelligibility, detail_intelligibility, about_instructor, question) values (9, '2020-02-07 12:22:20', 9, 9, 'Konklab', 1, 'Redhold', 1, 'Sonsing');
insert into daily_reports (id, date, training_id, student_id, content, intelligibility, detail_intelligibility, about_instructor, question) values (10, '2020-02-16 20:41:35', 10, 10, 'Kanlam', 1, 'Sub-Ex', 1, 'Alpha');
insert into daily_reports (id, date, training_id, student_id, content, intelligibility, detail_intelligibility, about_instructor, question) values (11, '2020-02-05 07:55:15', 1, 1, 'Namfix', 1, 'Stronghold', 1, 'Vagram');
insert into daily_reports (id, date, training_id, student_id, content, intelligibility, detail_intelligibility, about_instructor, question) values (12, '2020-02-18 11:59:02', 2, 2, 'Zoolab', 1, 'Span', 1, 'Hatity');
insert into daily_reports (id, date, training_id, student_id, content, intelligibility, detail_intelligibility, about_instructor, question) values (13, '2020-02-15 00:08:05', 3, 3, 'Subin', 1, 'Sonsing', 1, 'Otcom');
insert into daily_reports (id, date, training_id, student_id, content, intelligibility, detail_intelligibility, about_instructor, question) values (14, '2020-02-24 11:58:05', 4, 4, 'Asoka', 1, 'Flexidy', 1, 'Stringtough');
insert into daily_reports (id, date, training_id, student_id, content, intelligibility, detail_intelligibility, about_instructor, question) values (15, '2020-02-05 05:14:51', 5, 5, 'Regrant', 1, 'Otcom', 1, 'Namfix');
insert into daily_reports (id, date, training_id, student_id, content, intelligibility, detail_intelligibility, about_instructor, question) values (16, '2020-02-17 00:41:32', 6, 6, 'Otcom', 1, 'Fix San', 1, 'Trippledex');
insert into daily_reports (id, date, training_id, student_id, content, intelligibility, detail_intelligibility, about_instructor, question) values (17, '2020-02-22 02:25:11', 7, 7, 'Tampflex', 1, 'Zontrax', 1, 'Duobam');
insert into daily_reports (id, date, training_id, student_id, content, intelligibility, detail_intelligibility, about_instructor, question) values (18, '2020-02-27 19:04:42', 8, 8, 'Opela', 1, 'Zaam-Dox', 1, 'Wrapsafe');
insert into daily_reports (id, date, training_id, student_id, content, intelligibility, detail_intelligibility, about_instructor, question) values (19, '2020-02-05 08:31:54', 9, 9, 'Konklab', 1, 'Voyatouch', 1, 'Flowdesk');
insert into daily_reports (id, date, training_id, student_id, content, intelligibility, detail_intelligibility, about_instructor, question) values (20, '2020-02-03 05:59:35', 10, 10, 'Y-find', 1, 'Flexidy', 1, 'Biodex');
insert into daily_reports (id, date, training_id, student_id, content, intelligibility, detail_intelligibility, about_instructor, question) values (21, '2020-02-20 00:14:27', 1, 1, 'Sonair', 1, 'Opela', 1, 'It');
insert into daily_reports (id, date, training_id, student_id, content, intelligibility, detail_intelligibility, about_instructor, question) values (22, '2020-02-10 17:30:54', 2, 2, 'Prodder', 1, 'Bitchip', 1, 'Sub-Ex');
insert into daily_reports (id, date, training_id, student_id, content, intelligibility, detail_intelligibility, about_instructor, question) values (23, '2020-02-11 12:00:04', 3, 3, 'Lotstring', 1, 'Lotstring', 1, 'Zoolab');
insert into daily_reports (id, date, training_id, student_id, content, intelligibility, detail_intelligibility, about_instructor, question) values (24, '2020-02-09 01:42:39', 4, 4, 'Asoka', 1, 'Kanlam', 1, 'Redhold');
insert into daily_reports (id, date, training_id, student_id, content, intelligibility, detail_intelligibility, about_instructor, question) values (25, '2020-02-25 16:44:40', 5, 5, 'Stringtough', 1, 'Latlux', 1, 'Wrapsafe');
insert into daily_reports (id, date, training_id, student_id, content, intelligibility, detail_intelligibility, about_instructor, question) values (26, '2020-02-22 15:35:32', 6, 6, 'Treeflex', 1, 'Subin', 1, 'Keylex');
insert into daily_reports (id, date, training_id, student_id, content, intelligibility, detail_intelligibility, about_instructor, question) values (27, '2020-02-26 01:15:48', 7, 7, 'Home Ing', 1, 'Prodder', 1, 'Overhold');
insert into daily_reports (id, date, training_id, student_id, content, intelligibility, detail_intelligibility, about_instructor, question) values (28, '2020-02-22 18:52:51', 8, 8, 'Ronstring', 1, 'Aerified', 1, 'Ventosanzap');
insert into daily_reports (id, date, training_id, student_id, content, intelligibility, detail_intelligibility, about_instructor, question) values (29, '2020-02-23 11:46:21', 9, 9, 'Alphazap', 1, 'Tresom', 1, 'Veribet');
insert into daily_reports (id, date, training_id, student_id, content, intelligibility, detail_intelligibility, about_instructor, question) values (30, '2020-02-19 20:37:29', 10, 10, 'Lotstring', 1, 'Stim', 1, 'Subin');
