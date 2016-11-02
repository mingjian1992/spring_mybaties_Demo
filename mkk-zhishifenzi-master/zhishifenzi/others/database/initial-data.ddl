-- set encoding
set names utf8;

-- Initial database data
-- admin, password is wdcy2013
insert into user_(id,guid,create_time,password,username,default_user,type_,user_role)
values
(21,'29f6004fb1b0466f9572b02bf2ac1be8',now(),'ed30a0f893f2fc70733490d9b6ec202f','admin',true,'User','ADMIN');


-- Initial head photos
insert into gecko_file(guid,type_,url,description,head_photo)
values
('dda17d5357e844e29fd753ed52dbfb2d1407314526566','Photo','http://zsfz.qiniudn.com/bb.jpg','其实, 我们是一群穿着橙色球衣的人, 在那些时候.',1),
('f85a0c4449624b739bd50c2edf3110131407314526566','Photo','http://zsfz.qiniudn.com/cc.jpg','我们在默默哀悼, 祝福一切平安.',1),
('9886ee3a3e6143d68b6b1be382a923201407314526566','Photo','http://zsfz.qiniudn.com/aa.jpg','一起欢笑着的时光, 短暂且美好.',1),
('8cb38d5554c24438930f7082df28f3331407314526566','Photo','http://zsfz.qiniudn.com/ee.jpg','这二人, 这背景, 这场地...',1);


-- Initial global setting, only one data
insert into global_setting(id,guid,use_default_front_photos)
values
(21,'156a159882a5430e89d5dc5dfbacb36f1408008217676',1);
