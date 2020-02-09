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
