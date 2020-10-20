---旅行信息表
INSERT INTO T_T_TRAVEL_INFO(TITLE,SUMMARY,URL,TYPE,COLUMN_ID,UID,CODE,READ_COUNT,COMMENT_COUNT,NICE_COUNT,PV,PUBLISHTIME,UPDATETIME)
VALUES(
'去上海旅行怎么玩？您想要的攻略都在这了！去上海就这么玩？',
'让您在最短的时间里找的适合您的最佳旅行方案,快速了解这座神奇的城市，最大化节省您宝贵的时间，让旅行变得更加简单,更加的有意义。',
'https://pic4.zhimg.com/50/v2-df4b91357a77c2776f75e15402792188_400x224.jpg',
'1','1','10000','','900','800','888','10000','1595833763','1595833763'
);

---网站推荐表
INSERT INTO T_R_WEBSITE_RECOMMEND (NAME,NAME_EN,URL,NICE)
VALUES('影猫电影推荐网','MVCAT','https://www.mvcat.com/','<p>影猫电影推荐网是一个专门的电影推荐平台,通过收集经典电影,电影专题以及网友互动分享好看电影,为广大电影爱好者提供优质的电影推荐服务<p>');
--广告信息表
insert into T_A_ADVERT(logo,brand,title,ADVERIMG,SUMMARY,url,CREATEDATE,term,`STATUS`)
VALUES('https://pic1.zhimg.com/v2-dc86f8f1c52b9cbdae0084413b3a1d80_250x250.jpeg','小雨伞保险','30岁左右，求推荐一款轻重中症多次赔付的重疾险？要便宜的','https://pic3.zhimg.com/v2-42fada4897927d37617b5f512a2e1bfa_400x224.png',
'最近新上市的守卫者3号，保195种疾病，累计赔付高达390万，缴费20年可保终身，免费测算保费','https://www.baidu.com/','1589438064','1691766000','3');
insert into T_A_ADVERT(logo,brand,title,ADVERIMG,SUMMARY,url,CREATEDATE,term,`STATUS`)
VALUES('https://pic4.zhimg.com/v2-7801050046a14548d5ede82d7e398973_250x250.jpeg','尚品宅配','2020定制一套家具多少钱？提前算一算，不怕坑！','https://pic2.zhimg.com/v2-02c38899a232c8043d4cb599ee153029_400x224.jpeg',
'材料费、人工费、设计费……各种隐形价格总怕被坑？试试尚品宅配，10s免费拿报价，费用透明，一目了然','https://www.baidu.com/','1589438064','1691766000','3');


